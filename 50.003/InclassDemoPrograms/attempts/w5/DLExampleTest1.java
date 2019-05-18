package w5;

import org.junit.Test;

public class DLExampleTest1{
    @Test public void test1() throws InterruptedException{
        Dispatcher dis=new Dispatcher();
        Taxi tax=new Taxi(dis);
        ThreadTest thread=new ThreadTest();
        thread.taxi=tax;
        ThreadTest2 thread2=new ThreadTest2();
        thread2.dis=dis;
        thread2.dis.addTaxi(tax);
        thread.start();
        thread2.start();
        thread.join();
        thread2.join();

    }
}
class ThreadTest extends Thread{
    Taxi taxi;
    public void run(){
//        taxi.setDestination(new Point());
        taxi.setLocation(new Point());
    }
}
class ThreadTest2 extends Thread{
    Dispatcher dis;
    public void run(){
        dis.getImage();
    }
}
