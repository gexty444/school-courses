package Week12;

public class StripedMapWithSize {
	// Synchronization policy: buckets[n] guarded by locks[n%N_LOCKS]
	private static final int N_LOCKS = 16;
    private final Node[] buckets;
    private final Object[] locks;

    class Node {
        Node next;
        Object key;
        Object value;
        Node(Object key, Object value, Node next) {
            this.next = next;
            this.key = key;
            this.value = value;
        }
    }

    public StripedMapWithSize (int numBuckets) {
        buckets = new Node[numBuckets];
        locks = new Object[N_LOCKS];
        for (int i = 0; i < N_LOCKS; i++) {
            locks[i] = new Object();
        }
    }

    private final int hash(Object key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    public Object get(Object key) {
        int hash = hash(key);
        synchronized (locks[hash % N_LOCKS]) {
            for (Node m = buckets[hash]; m != null; m = m.next)
                if (m.key.equals(key))
                    return m.value;
        }
        return null;
    }

    public Object put(Object key, Object value) {
        int hash = hash(key);
        synchronized (locks[hash % N_LOCKS]) {
            for (Node m = buckets[hash]; m != null; m = m.next)
                if (m.key.equals(key)) {
                    m.value = value;
                    return m.value;
                }
            buckets[hash] = new Node(key,value,buckets[hash]);
        }
        return null;
    }

    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            synchronized (locks[i % N_LOCKS]) {
                buckets[i] = null;
            }
        }
    }

    // will not be completely accurate: while counting up the total, other
    // threads might be changing the parts of the table already viewed
    public int size() {
        int num = 0;
        for (int i = 0; i < buckets.length; i++) {
            synchronized (locks[i % N_LOCKS]) {
                for (Node m = buckets[i]; m != null; m = m.next)
                    num++;
            }
        }
        return num;
    }        
    
    public int size2() {
        int num = 0;
        
        for (int i = 0; i < N_LOCKS; i++) {
        	synchronized (locks[i]) {
        		for (int j = 0; j < buckets.length; j++) {
        			if (j%N_LOCKS == i) {
        				for (Node m = buckets[i]; m != null; m = m.next)
                            num++;
        			}
        		}
        	}
        }
        
        return num;
    }     
}