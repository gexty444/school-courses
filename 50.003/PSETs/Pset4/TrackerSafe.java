package com.example.javatest;

import java.util.HashMap;
import java.util.Map;

class testSafe extends Thread {
    TrackerSafe tracker;

    public testSafe (TrackerSafe tra) {
        this.tracker = tra;
    }

    public void run() {
        TrackerSafe.MutablePoint loc = null;
        try{
            loc = tracker.getLocation("somestring");
        } catch (Exception e){
            e.printStackTrace();
        }

        loc.x = -1212000;
    }
}
    //is this class thread-safe?
public class TrackerSafe {
    //@guarded by "this"
    private final Map<String, MutablePoint> locations;

    // helper function to deep copy
    private final Map<String, MutablePoint> copy(final Map<String, MutablePoint> input){
        // create the a new instance of the same map class and copy over all elements
        Map<String, MutablePoint> copiedMap = new HashMap<String, MutablePoint>();
        for (String x: input.keySet()){
            MutablePoint newPoint = new MutablePoint(input.get(x));
            copiedMap.put(x, newPoint);
        }
        return copiedMap;
    }


    //the reference locations, is it going to be an escape?
    // Yes, a reference to the locations is created, the locations can thus be
    // modified.
    // do a deep copy of the locations instead
    public TrackerSafe(Map<String, MutablePoint> locations) {
//        this.locations = locations;
        this.locations = copy(locations);
    }

    //is this an escape?
    // Yes, a reference to the locations object is returned which can be modified
    // return a deep copy instead & synchronise so locations set will be visible
    public synchronized Map<String, MutablePoint> getLocations (){
        return copy(locations);
    }

    //is this an escape?
    // Yes, a reference to the MutablePoint object is returned
    // User can thus modify the attributes of the MutablePoint
    // return a new instance of MutablePoint & synchronise so locations set will be visible
    public synchronized MutablePoint getLocation (String id) {
        MutablePoint loc = locations.get(id);
        MutablePoint newPoint = new MutablePoint(loc.x,loc.y);
        return newPoint;
    }

    // synchronise method
    public synchronized void setLocation (String id, int x, int y) {
        MutablePoint loc = locations.get(id);

        if (loc == null) {
            throw new IllegalArgumentException ("No such ID: " + id);
        }

        loc.x = x;
        loc.y = y;
    }

    //this class is not thread-safe (why?) and keep it unmodified.
    class MutablePoint {
        public int x, y;

        public MutablePoint (int x, int y) {
            this.x = x;
            this.y = y;
        }

        public MutablePoint (MutablePoint p) {
            this.x = p.x;
            this.y = p.y;
        }
    }
}

