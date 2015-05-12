package com.thread.jdk5;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

	public static void main(String[] args) {

		// Executors 有关executor中的工具类
//		ExecutorService executorService = Executors.newCachedThreadPool();
//		ExecutorService executorService = Executors.newFixedThreadPool(3);
//		ExecutorService executorService = Executors.newScheduledThreadPool(3);
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
		
		// ExecutorService
//		for (int i = 0; i < 4; i ++) {
//			
//			executorService.execute(new Runnable() {
//				
//				@Override
//				public void run() {
//					int count = 0;
//					while (count < 10) {
//						try {
//							Thread.sleep(100);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//						System.out.println(Thread.currentThread().getName() + "--->" + count++);
//					}
//				}
//			});
//		}
		
		//  {}
		
		executorService.schedule(new Runnable() {
			
			@Override
			public void run() {
				int count = 0;
				while (count < 10) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + "--->" + count++);
				}
			}
		}, 5, TimeUnit.SECONDS);
		
		
//		executorService.shutdown();
//		executorService.shutdownNow();
		
	}

}
