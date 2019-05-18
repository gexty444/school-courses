package Week8;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//is this class thread-safe?
public class TrackerFixed {
	//@guarded by this
	private final Map<String, MutablePoint> locations;
	
	//the reference locations, is it going to be an escape?
	public TrackerFixed(Map<String, MutablePoint> locations) {
		this.locations = deepClone(locations);
	}
	
	//is this an escape?
	public synchronized Map<String, MutablePoint> getLocations () {
		return deepClone(locations);
	}
	
	//is this an escape?
	public synchronized MutablePoint getLocation (String id) {
		MutablePoint loc = locations.get(id);
		return new MutablePoint(loc.x, loc.y);
	}
	
	public synchronized void setLocation (String id, int x, int y) {
		MutablePoint loc = locations.get(id);
		
		if (loc == null) {
			throw new IllegalArgumentException ("No such ID: " + id);			
		}
		
		loc.x = x;
		loc.y = y;
	}
	
	private static Map<String, MutablePoint> deepClone (Map<String, MutablePoint> map) {
		Map<String, MutablePoint> toReturn = new ConcurrentHashMap<String, MutablePoint> ();
		for (String key: map.keySet()) {
			MutablePoint newPoint = new MutablePoint(map.get(key).x, map.get(key).y); 
			toReturn.put(key, newPoint);
		}
		
		return toReturn;
	}
}

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
