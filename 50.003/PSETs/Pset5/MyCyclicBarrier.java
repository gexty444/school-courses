package w8;

//this class must be thread-safe. why?
public class MyCyclicBarrier {
	private int count = 0; 
	private Runnable torun;
	private int arrived = 0;
	
	public MyCyclicBarrier (int count, Runnable torun) {
		this.count = count;
		this.torun = torun;
	}

	public MyCyclicBarrier (int count) {
		this.count = count;
	}
	
	//complete the implementation below.
	//hint: use wait(), notifyAll()
	public void await () {
	    synchronized (this){
	        try{
	            arrived++;
	            if (arrived == count){
	                this.notifyAll();
	                torun.run();    // go do what you must
                }
                else {
                    this.wait();
                }
            }
            catch(InterruptedException e){
	            e.printStackTrace();
            }
        }
	}
}
