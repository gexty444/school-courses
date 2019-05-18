package w6;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

public class SafeStack<E>{
    // used composition
    // added all other methods of java.util.Stack
    // and inherited methods from java.util.Vector

    private Stack<E> stack = new Stack<>();

    public synchronized void pushIfNotFull(E e){
        while (isFull()){
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        stack.push(e);
        notifyAll();
    }

    public synchronized E popIfNotEmpty(){
        E toReturn;
        while (isEmpty()){
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        toReturn = stack.pop();
        notifyAll();
        return toReturn;
    }

    // Stack methods
    public synchronized boolean isEmpty() {
        return stack.isEmpty();
    }

    public synchronized boolean isFull() {
        return stack.size() == stack.capacity();
    }

    public synchronized E peek(){
        return stack.peek();
    }

    public synchronized int search(E e){
        return stack.search(e);
    }


    // inherited methods
    public synchronized int size(){
        return stack.size();
    }

    public synchronized boolean add(E e){
        return stack.add(e);
    }

    public synchronized void add(int index, E element){
        stack.add(index, element);
    }

    public synchronized E remove(int index){
        return stack.remove(index);
    }

    public synchronized boolean remove(Object o) {
        return stack.remove(o);
    }

    public synchronized E set(int index, E element){
        return stack.set(index, element);
    }

    public synchronized void clear(){
        stack.clear();
    }

    public synchronized boolean addAll(Collection<? extends E> c) {
        return stack.addAll(c);
    }

    public synchronized boolean addAll(int index, Collection<? extends E> c) {
        return stack.addAll(index, c);
    }

    public synchronized boolean contains(Object o) {
        return stack.contains(o);
    }

    public synchronized boolean containsAll(Collection<?> c) {
        return stack.containsAll(c);
    }

    public synchronized E get(int index) {
        return stack.get(index);
    }

    public synchronized int indexOf(Object o) {
        return stack.indexOf(o);
    }


    public synchronized Iterator<E> iterator() {
        return stack.iterator();
    }

    public synchronized int lastIndexOf(Object o) {
        return stack.lastIndexOf(o);
    }

    public synchronized ListIterator<E> listIterator() {
        return stack.listIterator();
    }

    public synchronized ListIterator<E> listIterator(int index) {
        return stack.listIterator(index);
    }

    public synchronized boolean removeAll(Collection<?> c) {
        return stack.removeAll(c);
    }

    public synchronized boolean retainAll(Collection<?> c) {
        return stack.retainAll(c);
    }

    public synchronized List<E> subList(int fromIndex, int toIndex) {
        return stack.subList(fromIndex, toIndex);
    }

    public synchronized Object[] toArray() {
        return stack.toArray();
    }

    public synchronized <T> T[] toArray(T[] a) {
        return stack.toArray(a);
    }


}
