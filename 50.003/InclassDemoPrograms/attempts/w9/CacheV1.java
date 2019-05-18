package w9;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheV1 {
	private final ConcurrentHashMap<Integer, List<Integer>> results = new ConcurrentHashMap<Integer, List<Integer>>(); //the factors must be the factors of the corresponding number

	public List<Integer> service (int input) {		// no synchronisation.
		List<Integer> factors = results.get(input);
		
		if (factors == null) {
			factors = factor(input);
			results.put(input, factors);			// needs thread-safe data-structure --> ConcurrentHashMap
		}
		
		return factors;
	}
	
	public List<Integer> factor(int n) {			//time-consuming task, to avoid if possible
		List<Integer> factors = new ArrayList<Integer>();
		for (int i = 2; i <= n; i++) {
			while (n % i == 0) {
		        factors.add(i);
		        n /= i;
		    }
		}
		
		return factors;
	}
}