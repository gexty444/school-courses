package w6;

public class MyStack {
	private int maxSize;
	private long[] stackArray;
	private int top; 
	
	public MyStack(int s) { 
		maxSize = s;
	    stackArray = new long[maxSize];
	    top = -1;
	}

    //pre-condition: top < maxSize
    //post-condition: the top element is j
	public void push(long j) {
		stackArray[++top] = j;
	}

    //pre-condition: top >= 0
    //post-condition: the top element is removed
	public long pop() {		
	    return stackArray[top--];
	}

	//pre-condition: top >=0
    //post condition: elements are unchanged. the return value is the element at the top of the stack.
	public long peek() {
	    return stackArray[top];
	}

    //pre-condition: true
    //post-condition: the elements are un-changed. the return value is true iff the stack is empty.
    public boolean isEmpty() {
		return (top == -1);
	}

	//pre-condition: true
    //post condition: the elements are un-changed. the return value is true iff stack is full
	public boolean isFull() {
		return (top == maxSize - 1);
	}
}