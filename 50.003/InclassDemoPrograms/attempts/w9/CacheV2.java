package w9;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CacheV2 {
	private final ConcurrentHashMap<Integer, Future<List<Integer>>> results = new ConcurrentHashMap<Integer, Future<List<Integer>>>(); 
	
	public List<Integer> service (final int input) throws Exception {
		Future<List<Integer>> f = results.get(input);
				
		if (f == null) {	// if not in results table, set up Callable to factor the number. (declare to be done but dont actl do the factor yet, so statement is quick)
			Callable<List<Integer>> eval = new Callable<List<Integer>>() {
				public List<Integer> call () throws InterruptedException {
					return factor(input);
				}
			};
			// a way of delaying the computation
			FutureTask<List<Integer>> ft = new FutureTask<List<Integer>>(eval);
			f = ft;
			results.put(input, ft);		// put in table, havent computed
			ft.run();
		}

		return f.get();			// waits until result is in (from other thread) (but u can't put same key twice)
	}

	public List<Integer> factor(int n) {		
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