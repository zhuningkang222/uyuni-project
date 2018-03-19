package com.uyuni.main.lock;

public class SecKillThread extends Thread{
	
	private SecKillService2 service;

    public SecKillThread(SecKillService2 service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.secKill();
    }

    public static void main(String[] args) {
    	SecKillService2 service = new SecKillService2();
         for (int i = 0; i < 10; i++) {
        	 SecKillThread thread = new SecKillThread(service);
        	 thread.start();
         }
	}
}
