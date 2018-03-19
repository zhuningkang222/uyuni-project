package com.uyuni.main.zookeeper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 使用curator实现Zookeeper分布式锁 (失败，原因不明，InterProcessMutex lock = new InterProcessMutex(client, "/curator/lock"); 这一步阻塞了)
 * 已解决，不能将InterProcessMutex lock = new InterProcessMutex(client, "/curator/lock")执行多次
 * InterProcessMutex 为可重入锁，不明白为何不能执行多次？？？
 * @author Administrator
 *
 */
public class CuratorLock {
	
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(5);
		String zkConnConfig = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
		//String zkConnConfig = "127.0.0.1:2181";
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);  
		CuratorFramework client = CuratorFrameworkFactory.newClient(zkConnConfig, retryPolicy);  
		client.start();  
		System.out.println("客户端启动...");
		ExecutorService exec = Executors.newCachedThreadPool();  
		InterProcessMutex lock = new InterProcessMutex(client, "/curator/lock");
		for (int i = 0; i < 5; i++) {  
			exec.submit(new LockThread("client" + i, client, latch, lock));  
		}  
		exec.shutdown();  
		latch.await();  
		System.out.println("所有任务执行完毕");  
		client.close();  
		System.out.println("客户端关闭......");  
	}
	
	static class LockThread implements Runnable {
		private String name;  
		private CuratorFramework client;  
		private CountDownLatch latch;  
		private InterProcessMutex lock;
		public LockThread(String name, CuratorFramework client, CountDownLatch latch, InterProcessMutex lock) {  
			this.name = name;  
			this.client = client;  
			this.latch = latch;  
			this.lock = lock;
		}  
		public String getName() {  
			return name;  
		}  
		public void setName(String name) {  
			this.name = name;  
		} 

		@Override
		public void run() {
			//System.out.println("线程启动......");
			//InterProcessMutex lock = new InterProcessMutex(client, "/curator/lock");
			//System.out.println("创建lock......");
			try {
				//if (lock.acquire(120, TimeUnit.SECONDS)) {
				if (lock.acquire(120, TimeUnit.SECONDS)) {
					// do some work inside of the critical section here  
					System.out.println("----------" + this.name  
					    + "获得资源----------");  
					System.out.println("----------" + this.name  
					    + "正在处理资源----------");  
					Thread.sleep(2 * 1000);  
					System.out.println("----------" + this.name  
					    + "资源使用完毕----------");  
					latch.countDown(); 
					/*lock.release();*/
				} else {
					System.out.println("----------" + this.name  
						    + "获取资源超时----------"); 
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					lock.release();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
