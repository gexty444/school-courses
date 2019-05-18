package Week10;

import java.util.Stack;

//this is done through composition
public class SafeStack<E> {
	private Stack<E> stack = new Stack<E>();
	
	public synchronized boolean pushIfNotFull (E e) {
		if (stack.size() != stack.capacity()) {
			stack.push(e);
			return true;
		}
		
		return false;
	}
	
	public synchronized E popIfNotEmpty () {
		if (!stack.isEmpty()) {
			return stack.pop();
		}
		
		return null;
	}
	
	public synchronized boolean empty () {
		return stack.isEmpty();
	}
	
	public synchronized boolean add (E e) {
		return stack.add(e);
	}
	
	//the following are other methods in Stack class which are omitted.
}

//this is done through client-side locking
class SafeStack2<E> {
	public Stack<E> stack = new Stack<E>();
	
	public boolean pushIfNotFull (E e) {
		synchronized (stack) {
			if (stack.size() != stack.capacity()) {
				stack.push(e);
				return true;
			}
		
			return false;
		}
	}
	
	public E popIfNotEmpty () {
		synchronized (stack) {
			if (!stack.isEmpty()) {
				return stack.pop();
			}
		
			return null;
		}
	}
}