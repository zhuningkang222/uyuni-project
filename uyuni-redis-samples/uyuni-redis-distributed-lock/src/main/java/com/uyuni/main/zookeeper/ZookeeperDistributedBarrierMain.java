package com.uyuni.main.zookeeper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 分布式栅栏，基于curator Zookeeper 的实现 barrier.waitOnBarrier()直接阻塞，一直等不到所有进程运行完，原因不明
 * @author Administrator
 *
 */
public class ZookeeperDistributedBarrierMain {
	
	public static void main(String[] args) throws Exception {
		//创建zookeeper客户端
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new ExponentialBackoffRetry(1000, 3));
        client.start();

        //指定锁路径
        String lockPath = "/curator/barrier";
        //创建分布式栅栏
        DistributedBarrier distributedBarrier = new DistributedBarrier(client, lockPath);
        distributedBarrier.setBarrier();

        //生成线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        Consumer<DistributedBarrier> consumer = (DistributedBarrier barrier)->{
            try{
            	List<Callable<Boolean>> callList = new ArrayList<>();
                Callable<Boolean> call = () -> {
                    try{
                        System.out.println(Thread.currentThread() + "  rearch barrier, waiting");
                        barrier.waitOnBarrier(1, TimeUnit.SECONDS);
                        //barrier.waitOnBarrier();
                        System.out.println(Thread.currentThread() + "  do next");
                    }catch (Exception e){
                    	e.printStackTrace();
                    }
                    return true;
                };
                //5个并发线程
                for (int i = 0; i < 5; i++) {
                    callList.add(call);
                }
                List<Future<Boolean>> futures = executor.invokeAll(callList);
            }catch (Exception e){
            	e.printStackTrace();
            }
        };

        //栅栏测试(多个线程栅栏测试)
        System.out.println("5个并发线程,栅栏测试");
        consumer.accept(distributedBarrier);

        distributedBarrier.removeBarrier();
        executor.shutdown();
        client.close();
	}

}
