package w9;

public class StripedMapSolution {
    //synchronization policy: buckets[n] guarded by locks[n%N_LOCKS]
    private static final int N_LOCKS = 16;
    private final Node[] buckets;
    private final Object[] locks;

    public StripedMapSolution (int numBuckets) {
        buckets = new Node[numBuckets];
        locks = new Object[N_LOCKS];

        for (int i = 0; i < N_LOCKS; i++) {
            locks[i] = new Object();
        }
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

    public Object get (Object key) {
        //todo: get the item with the given key in the map
        int hashedkey = hash(key);
        synchronized (locks[hashedkey % N_LOCKS]){          // lock based on hashed key
            for (Node m = buckets[hashedkey]; m != null; m = m.next){       // linked list go till you get null (end)
                if (m.key.equals(key)){
                    return m.value;
                }
            }
        }
        return null;
    }

    private final int hash (Object key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    public void clear () {
        //todo: remove all objects in the map

        // more efficient method
//        for (int i=0; i<N_LOCKS; i++){
//            synchronized (locks[i]){                    // take lock 16 times only
//                for (int j=0; j<buckets.length/N_LOCKS; j++){
//                    buckets[i + j*N_LOCKS] = null;       // i=0 -> 0, 16, 32;   i=1 -> 1, 17, 33
//                }
//            }
//        }

        // less efficient method (more locking)
        for (int i = 0; i < buckets.length; i++) {
            synchronized (locks[i % N_LOCKS]) {
                buckets[i] = null;
            }
        }
    }

    public int size () {
        //todo: count the number of elements in the map
        int count = 0;

        // more efficient method
//        for (int i=0; i<N_LOCKS; i++){
//            synchronized (locks[i]){                    // take lock 16 times only
//                for (int j=0; j<buckets.length/N_LOCKS; j++){
//                    if (buckets[i + j*N_LOCKS] != null){
//                        count ++;
//                    }
//                }
//            }
//        }

        // less efficient method (more locking)
        for (int i = 0; i < buckets.length; i++) {
            synchronized (locks[i % N_LOCKS]) {
                if (buckets[i] != null) {
                    count++;
                }
            }
        }

        return count;
    }

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

}
