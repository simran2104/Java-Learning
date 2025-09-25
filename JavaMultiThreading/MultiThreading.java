import java.util.*;
import java.lang.*;
import java.io.*;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;


class Counter {
    private int count = 0;
    
    private final Lock lock = new ReentrantLock();
    
    public void increment() {
        try {
           if(lock.tryLock(2000, TimeUnit.MILLISECONDS)) {
                count++;
                System.out.println(Thread.currentThread().getName() + " Sleep Started !"); 
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " Done !"); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " Lock Aquired!");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public int getCount() {
        return count;
    }
}

class ReentrantExp {
    private final Lock lock = new ReentrantLock();
    
    private void innerMethod(){
        lock.lock();
        try {
            System.out.println("Inner Method!");
        } finally{
            lock.unlock();
        }
    }
    
    public void outerMethod() {
        lock.lock();
        try {
            System.out.println("Outer Method!");
            innerMethod();
        } finally {
            lock.unlock();
        }
    }
}

class ReadWriteExp {
    private int count = 0;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();
    
    
    public int getCount() {
        readLock.lock();
        
        try {
            return count;
        } finally {
            readLock.unlock();
        }
    }
    
    public void increment() {
        writeLock.lock();
        
        try {
            count++;
            Thread.sleep(50);
        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            writeLock.unlock();
        }
    }
    
}

class Paper {
    public synchronized void WriteWithPenAndPaper(Pen pen) {
        System.out.println(Thread.currentThread().getName() + " is Waiting for Pen ");
        pen.finishWriting();
    }
    
    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " Finshed Writing!");
    }
}

class Pen {
    public synchronized void WriteWithPenAndPaper(Paper paper) {
        System.out.println(Thread.currentThread().getName() + " is Waiting for Paper ");
        paper.finishWriting();
    }
    
    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " Finshed Writing!");
    }
}

class Task1 implements Runnable {
    private Paper paper;
    private Pen pen;
    
    public Task1(Paper paper, Pen pen) {
        this.paper = paper;
        this.pen = pen;
    }
    
    public void run() {
        synchronized(paper) {
            pen.WriteWithPenAndPaper(paper);    
        }
    }
}

class Task2 implements Runnable {
    private Paper paper;
    private Pen pen;
    
    public Task2(Paper paper, Pen pen) {
        this.paper = paper;
        this.pen = pen;
    }
    
    public void run() {
        synchronized(pen) {
            paper.WriteWithPenAndPaper(pen);    
        }
    }
}

class SharedResource {
    private int data;
    private boolean hasData;
    
    public synchronized void produce(int value) {
        while(hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Thread Intruptted! " + Thread.currentThread().getName());
                Thread.currentThread().interrupt();
            }
        }
        data = value;
        hasData = true;
        notify();
        System.out.println("Produced!");
    }
    
    public synchronized int consume() {
        while(!hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Thread Intruptted! " + Thread.currentThread().getName());
                Thread.currentThread().interrupt();
            }
        }
        hasData = false;
        notify();
        System.out.println("Consumed!");
        return data;
    }
}

class Producer implements Runnable {
    private SharedResource resource;
    public Producer(SharedResource resource) {
        this.resource = resource;
    }
    
    @Override
    public void run() {
        for(int i=0; i<10; i++) {
            resource.produce(i);
        }
    }
}

class Consumer implements Runnable {
    private SharedResource resource;
    public Consumer(SharedResource resource) {
        this.resource = resource;
    }
    
    @Override
    public void run() {
        for(int i=0; i<10; i++) {
            resource.consume();
        }
    }
}
class MultiThreading
{
	public static void main (String[] args) throws java.lang.Exception
	{
	    ExecutorService executor = Executors.newSingleThreadExecutor();
	    
	    Future<?> future = executor.submit(()->42);
	    try {
	        System.out.println(future.get());
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    
	    if(future.isDone()) {
	        System.out.println("Task is Done!");
	    }
	    
	    executor.shutdown();
	    
	    System.out.println(executor.isTerminated());
	    
	   long startTime = System.currentTimeMillis(); // 1 Jan 1970
	   Thread[] threads = new Thread[10];
	   for(int i=1; i<=10; i++) {
	       int finalI = i;
	       threads[i-1] = new Thread(() -> {
	           System.out.println("Factorial: " + factorial(finalI) + Thread.currentThread().getName());
	       }, " Thread " + i);
	       threads[i-1].start();
	   }
	   for(int i=0; i<10; i++) {
	       threads[i].join();
	   }
	   System.out.println("Time Taken: " + (System.currentTimeMillis() - startTime));
	    
	    
	  // Executors  
	   long startTime = System.currentTimeMillis(); // 1 Jan 1970
	   ExecutorService executor = Executors.newFixedThreadPool(9);
	   for(int i=1; i<=10; i++) {
	       int finalI = i;
	       executor.submit(() -> {
	           System.out.println("Factorial: " + factorial(finalI) + " " +Thread.currentThread().getName());
	       });
	   }
	   executor.shutdown();
	   try {
	      executor.awaitTermination(100, TimeUnit.MILLISECONDS); // Fixed Time
	       
	      // Unlimited Time wait
	      while(!executor.awaitTermination(1, TimeUnit.MILLISECONDS)) {
	          System.out.println("Waiting....");
	      }
	   } catch (InterruptedException e) {
	       System.out.println("Exception: " + e);
	       Thread.currentThread().interrupt();
	   }
	   System.out.println("Time Taken: " + (System.currentTimeMillis() - startTime));
	    
	    
	   SharedResource resource = new SharedResource();
	    
	   Thread th1 = new Thread(new Consumer(resource), "Consumer");
	   Thread th2 = new Thread(new Producer(resource), "Producer");
	    
	   th1.start();
	   th2.start();
	    
	  // Anonymous Function that do not require class to make it
	   Runnable task = new Runnable() {
	     public void run() {
	         /* 
	         **/
	     }
	   };
	    
	   // Lmabda Expression -> only when we have Single Abstract Method of any Class
	   Runnable task = () -> {
	       // run code 
	   }
	    
	   // We can reomve the paranthesies when having single parameter and bracket when having single statements
	   Runnable task = parameter -> System.out.println();
	    
	   // we cannont write return with return keyword without curly brackets
	   Runnable task = param -> return param + "Hi"; // ERROR
	   Runnable task = param -> param + "HI"; // It itself take it as return 
	    
	   // Now if want to use with Thread
	   Thread thTest = new Thread(task);
	   // instead of this 
	   Thread thTest = new Thread((param) -> param + "Hi");
	   // incase of multiple lines
	   Thread thTest = new Thread((param) -> {
	      // code 
	   });
	    
	    
	   Paper paper = new Paper();
	   Pen pen = new Pen();

	   Thread th1 = new Thread(new Task1(paper, pen), "Thread1");
	   Thread th2 = new Thread(new Task2(paper, pen), "Thread2");
	   
	   th1.start();
	   th2.start();
	    
	   Counter countObj = new Counter();
	    
	   Runnable task = new Runnable() {
	       public void run() {
	           countObj.increment();
	       }
	   };
	    
	   Thread th1 = new Thread(task);
	   Thread th2 = new Thread(task);
	    
	   th1.start();
	   th2.start();
		
		
		ReentrantExp rentExp = new ReentrantExp();
		
		rentExp.outerMethod();

        ReadWriteExp readWriteExp = new ReadWriteExp();
        
        Runnable task1 = new Runnable() {
            public void run() {
                for(int i=0; i<10; i++) {
                    System.out.println("Read: " + readWriteExp.getCount());
                }
            }
        };
        
        Runnable task2 = new Runnable() {
            public void run() {
                for(int i=0; i<10; i++) {
                    readWriteExp.increment();
                    System.out.println("Writing...");
                }
            }
        };
        
        Thread th1 = new Thread(task1);
        Thread th2 = new Thread(task1);
        Thread th3 = new Thread(task2);
        
        
        th3.start();
        th1.start();
        th2.start();
        
        try {
            th1.join();
            th2.join();
            th3.join();
        } finally {
            System.out.println("Final Count: " + readWriteExp.getCount());
        }
	}
    public static int factorial(int n) {
        if(n==0 || n==1) return 1;
        
        return n*factorial(n-1);
    }
}
