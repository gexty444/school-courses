package w6;

public class MyStackThreadSafe {
	private final int maxSize;
	private long[] stackArray; //guarded by "this"
	private int top; //invariant: top < stackArray.length && top >= -1; guarded by "this"	

    // locking policy: synchronise the methods within this class
    // so that the methods will not interleave and changes done to the
    // attributes of the Stack by one method would be made visible to
    // all threads.
    //
    // wait is used when we want to push() but stack is full or we want to
    // pop()/peek() but stack is empty.
    // notifyAll is used when attributes of the stack is changed and thus
    // we need to notify other threads.


	//pre-condition: s > 0
	//post-condition: maxSize == s && top == -1 && stackArray != null
	public MyStackThreadSafe(int s) { //Do we need "synchronized" for the constructor?
		maxSize = s;
	    stackArray = new long[maxSize];
	    top = -1;
	}
	
	//pre-condition: top >= 0
	//post-condition: the top element is removed
	public synchronized long pop() {
		long toReturn; 
		
		while (isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		toReturn = stackArray[top--];
		notifyAll();			
	    return toReturn;
	}	

	//pre-condition: true
	//post-condition: the elements are un-changed. the return value is true iff the stack is empty.
	public synchronized boolean isEmpty() {
		return (top == -1);
	}

    //pre-condition: top >= -1
    //post-condition: elements are un-changed. the return value is true iff the stack is full.
    public synchronized boolean isFull() {
        return (top == maxSize - 1);
    }

	//pre-condition: top < maxSize-1(stack is not full)
    //post-condition: the top element is j, top ++, top < maxSize
	public synchronized void push(long j) {
        while (isFull()) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        stackArray[++top] = j;
        // notify other threads that attributes have changed
        notifyAll();
	}

    //pre-condition: top >= 0
    //post condition: elements are unchanged. the return value is the element at the top of the stack.
    public synchronized long peek() {
	    while (isEmpty()){
	        try{
	            wait();
            } catch (InterruptedException e){
	            e.printStackTrace();
            }
        }
        return stackArray[top];
	    // do not need to notify since elements are unchanged.
    }

}