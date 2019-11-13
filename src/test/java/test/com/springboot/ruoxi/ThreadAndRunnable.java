package test.com.springboot.ruoxi;

public class ThreadAndRunnable {
  public static void main(String[] args) throws InterruptedException {
    Runnable demo1 = new ThreadDemo1();
    //    Runnable demo2 = new ThreadDemo1();
    Thread thread1 = new Thread(demo1);
    Thread thread2 = new Thread(demo1);
    thread1.start();
    System.out.println(thread1.hashCode());
    thread2.start();
    System.out.println(thread2.hashCode());
    //    thread1.join();
    //    thread2.join();
    System.out.println("火箭发射倒计时:");
  }
}

class ThreadDemo1 implements Runnable {
  static Integer countDown = 100;

  @Override
  public void run() {
    synchronized (this) {
      while (countDown-- > 0) {
        try {
          Thread.sleep(300);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "(" + countDown + ")");
      }
    }
  }
}
