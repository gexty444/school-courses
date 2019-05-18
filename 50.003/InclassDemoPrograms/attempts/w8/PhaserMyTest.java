package w8;

import java.util.concurrent.Phaser;

public class PhaserMyTest {

    public static void main(String[] args) throws InterruptedException {

        Phaser phaser = new Phaser();
        phaser.register();

        System.out.println(phaser.getRegisteredParties() + " have registered.");

        phaser.arriveAndDeregister();
        System.out.println("deregphasecount="+phaser.getPhase()+ " reg="+phaser.getRegisteredParties() + "arr="+phaser.getArrivedParties());
        Thread.sleep(5000);
        System.out.println("arrived parties = " + phaser.getArrivedParties());
        System.out.println("reg parties = "+ phaser.getRegisteredParties());
        int phasecount = phaser.getPhase();
        System.out.println("Phasecount is "+ phasecount);

    }


    private void phaserthread(final Phaser phaser)
    {
        phaser.register();
        System.out.println(phaser.getRegisteredParties() + " have registered.");
        System.out.println("phasecount="+phaser.getPhase()+ " reg="+phaser.getRegisteredParties() + "arr="+phaser.getArrivedParties());

        new Thread(){
            @Override
            public void run()
            {
                try
                {
                    System.out.println(Thread.currentThread().getName()+" arrived");
                    phaser.arriveAndAwaitAdvance();//threads register arrival to the phaser.
                    //phaser.arrive();
                    //System.out.println(Thread.currentThread().getName()+" pass arrived");
                    System.out.println("phasecount="+phaser.getPhase()+ " reg="+phaser.getRegisteredParties() + "arr="+phaser.getArrivedParties());
                    Thread.sleep(1000);
                }

                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" after passing barrier");
            }
        }.start();
    }
}
