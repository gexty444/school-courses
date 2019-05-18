package w5;


import org.junit.Test;

public class DLExampleTest {

//    Potentially cause deadlock because we have 2 different threads that both
//    need 2 different locks.
//    For the Taxi Thread, when it calls the .setLocation(Point p)
//    method, it would be holding the Taxi.class lock and when it calls for dispatcher.notifyAvailable()
//    it will try to acquire the Dispatcher.class lock.
//    On the other hand, when the Dispatcher Thread will call .getImage() method, it will be holding
//    on to the Dispatcher.class lock and when it calls for t.getLocation(), it will try to acquire the
//    Taxi.class lock.
//    Thus, when the Taxi Thread is holding on to the Taxi.class lock and tries to acquire the Dispatcher.class
//    lock, and at the same time the Dispatcher Thread is holding on to the Dispatcher.class lock and tries
//    to acquire the Taxi.class lock, a dead lock would happen.

    @Test
    public void test1() throws InterruptedException {
        Dispatcher d = new Dispatcher();
        Taxi t = new Taxi(d);
        TaxiThread taxiThread = new TaxiThread(t);

        d.addTaxi(t);
        DispatcherThread dispatcherThread = new DispatcherThread(d);

        taxiThread.start();
        dispatcherThread.start();

        taxiThread.join();
        dispatcherThread.join();
    }

    class TaxiThread extends Thread {

        private Taxi taxi;

        public TaxiThread(Taxi taxi) {
            this.taxi = taxi;
        }

        public void run() {
            System.out.println("Taxi Thread is running!");
            taxi.setLocation(new Point());
        }
    }

    class DispatcherThread extends Thread {

        private Dispatcher dispatcher;

        public DispatcherThread(Dispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        public void run() {
            System.out.println("Dispatcher Thread is running!");
            dispatcher.getImage();
        }
    }
}
