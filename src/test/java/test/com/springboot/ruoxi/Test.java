package test.com.springboot.ruoxi;

public class Test {
  static Integer i = 100;

  public static void main(String[] args) {
    //
    new Thread(new Wait(i)).start();
    //    new Thread(new Wait(i)).start();
    new Thread(new Notify(i)).start();
  }
}

class Wait implements Runnable {
  private Integer i;

  public Wait(Integer i) {
    this.i = i;
  }

  @Override
  public void run() {
    String name = Thread.currentThread().getName();
    System.out.println(name + " started");
    try {
      synchronized (i) {
        i.wait();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

class Notify implements Runnable {
  private Integer i;

  public Notify(Integer i) {
    this.i = i;
  }

  @Override
  public void run() {
    String name = Thread.currentThread().getName();
    System.out.println(name + " started");
    synchronized (i) {
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      i.notify();
    }
  }
}
