package com.Jlink.util;

import java.security.MessageDigest;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * MD5加密工具类
 */
public class MD5 {

	/**
	 * 对传入的String进行MD5加密
	 */
	public static final String getMd5(String s) {
		// 16进制数组
		char hexDigits[] = { '5', '0', '5', '6', '2', '9', '6', '2', '5', 'q', 'b', 'l', 'e', 's', 's', 'y' };
		try {
			char str[];
			//将传入的字符串转换成byte数组
			byte strTemp[] = s.getBytes();
			//获取MD5加密对象
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			//传入需要加密的目标数组
			mdTemp.update(strTemp);
			//获取加密后的数组
			byte md[] = mdTemp.digest();
			int j = md.length;
			str = new char[j * 2];
			int k = 0;
			//将数组做位移
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			//转换成String并返回
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	public volatile int inc = 0;
	public void increase() {
		inc++;
	}

	public static class ThreadPrinter implements Runnable{
		private String name;
		private Object prev;
		private Object self;
		private int state0;

		private ThreadPrinter(String name, Object prev, Object self, int state0){
			this.name = name;
			this.prev = prev;
			this.self = self;
			this.state0 = state0;
		}

		@Override
		public void run() {
			int count = 10;
			while(count > 0){
//				while(state % 3 == state0){
				synchronized (prev){
					while (state % 3 != state0) {
						try {
							prev.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					synchronized(self){
						System.out.print(name);
						count--;
						state++;
						self.notifyAll();
					}
					try{
						if(count == 0){
							prev.notifyAll();
						}
						else{
							prev.wait();
						}
					}catch (InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		}
	}

	private static Lock lock = new ReentrantLock();
	private static int state = 0;

	public static class LockState implements Runnable{
		private int state0;
		private String name;
		public LockState(String name, int state0){
			this.name = name;
			this.state0 = state0;
		}
		@Override
		public void run() {
			for (int i = 0; i < 10; ) {
				try{
					lock.lock();
					while(state%3 == state0){
						System.out.print(name);
						state++;
						i++;
					}
				}
				finally {
					lock.unlock();
				}
			}
		}
	}

	public static class ThreadA extends Thread{
		private String name;
		private int state0;
		public ThreadA(String name, int state0){
			this.name = name;
			this.state0 = state0;
		}

		@Override
		public void run(){
			for (int i = 0; i < 10; ) {
				try{
					lock.lock();
					while(state % 3 == state0){
						System.out.print(name);
						state++;
						i++;
					}
				}
				finally {
					lock.unlock();
				}
			}
		}
	}


	public static class ConditionABC implements Runnable{

		private Condition prev;
		private Condition self;
		private String name;
		private int state0;

		public ConditionABC(String name, Condition prev, Condition self, int state0){
			this.name = name;
			this.prev = prev;
			this.self = self;
			this.state0 = state0;
		}

		@Override
		public void run() {
			try {
				lock.lock();
				for (int i = 0; i < 10; i++) {
					while(state % 3 != state0){
						prev.await();
					}
					System.out.print(name);
					state++;
					self.signal();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}

	public static class SemaphoreABC extends Thread{
	    private Semaphore prev;
	    private Semaphore self;
	    private String name;
	    public SemaphoreABC(Semaphore prev, Semaphore self, String name){
	        this.name = name;
	        this.prev = prev;
	        this.self = self;
        }
        @Override
        public void run(){
	        try{
                for (int i = 0; i < 10; i++) {
                    prev.acquire();
                    System.out.print(name);
                    self.release();
                }
            } catch (InterruptedException e){
	            e.printStackTrace();
            }
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++){
            if(i != 0 && nums[i] == nums[i-1]){
                continue;
            }
            int val = nums[i];
            for(int j = i+1, k = nums.length-1; j < k; ){
                if(val + nums[j] + nums[k] == 0){
                    List<Integer> ans1 = new ArrayList<>(3);
                    ans1.add(val);
                    ans1.add(nums[j]);
                    ans1.add(nums[k]);
                    ans.add(ans1);
                    while(j < k && nums[j] == nums[j+1]){
                        j++;
                    }
                    while(k > j && nums[k] == nums[k-1]){
                        k--;
                    }
                }
                else if(val + nums[j] + nums[k] < 0){
                    while(j < k && nums[j] == nums[j+1]){
                        j++;
                    }
                }
                else{
                    while(k > j && nums[k] == nums[k-1]){
                        k--;
                    }
                }
            }
        }
        return ans;
    }

	private int x;
	public static void main(String[] args) throws Exception {
		System.out.println(MD5.getMd5("admin"));
		MD5 md5 = new MD5();
		System.out.println(md5.x);
		String a = "123";
		String b = "1";
		b += "23";
		String c = a;
		c += "4";
		System.out.println(a + " " + c);
		System.out.println(a.equals(b));
		System.out.println(a == b);
		Stack<Integer> s = new Stack();
		for(int i = 0; i < 3; i++){
			s.push(i);
		}
		System.out.println(s.peek());

		final MD5 test = new MD5();
		for (int i = 0; i < 10; i++) {
			new Thread(){
				public void run(){
					for (int j = 0; j < 1000; j++) {
						test.increase();
					}
				}
			}.start();
		}



//		while(Thread.activeCount() > 1){
//			Thread.yield();
//			System.out.println(test.inc);
//		}
//		System.out.println(test.inc);

//		Object o1 = new Object();
//		Object o2 = new Object();
//		Object o3 = new Object();
//		ThreadPrinter t1 = new ThreadPrinter("A", o3, o1, 0);
//		ThreadPrinter t2 = new ThreadPrinter("B", o1, o2, 1);
//		ThreadPrinter t3 = new ThreadPrinter("C", o2, o3, 2);
//		new Thread(t1).start();
//		new Thread(t2).start();
//		new Thread(t3).start();

//		LockState l1 = new LockState("A", 0);
//		LockState l2 = new LockState("B", 1);
//		LockState l3 = new LockState("C", 2);
//		new Thread(l1).start();
//		new Thread(l2).start();
//		new Thread(l3).start();

//		ThreadA threadA = new ThreadA("A", 0);
//		ThreadA threadB = new ThreadA("B", 1);
//		ThreadA threadC = new ThreadA("C", 2);
//		threadA.start();
//		threadB.start();
//		threadC.start();

//        Condition c1 = lock.newCondition();
//        Condition c2 = lock.newCondition();
//        Condition c3 = lock.newCondition();
//
//		ConditionABC ca1 = new ConditionABC("A", c1, c2, 0);
//		ConditionABC ca2 = new ConditionABC("B", c2, c3, 1);
//		ConditionABC ca3 = new ConditionABC("C", c3, c1, 2);
//		new Thread(ca1).start();
//		new Thread(ca2).start();
//		new Thread(ca3).start();

//		Semaphore s1 = new Semaphore(1);
//		Semaphore s2 = new Semaphore(0);
//		Semaphore s3 = new Semaphore(0);
//
//        SemaphoreABC sa1 = new SemaphoreABC(s1, s2, "A");
//        SemaphoreABC sa2 = new SemaphoreABC(s2, s3, "B");
//        SemaphoreABC sa3 = new SemaphoreABC(s3, s1, "C");
//
//        sa3.start();
//        sa2.start();
//        sa1.start();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> ans = test.threeSum(nums);

	}
}
