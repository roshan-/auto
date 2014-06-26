package nirvana.solver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import nirvana.solver.euler.p1.MultipleOfXandYUsingCircularList;
import nirvana.solver.euler.p1.MultipleOfXandYUsingCounter;
import nirvana.solver.euler.p1.MultipleOfXandYUsingMod;

public class Solutions {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor= Executors.newFixedThreadPool(1);

		List<Solve> solutions= new ArrayList<Solve>();

		// Euler 1
		ParameterSet euler1MultipleOfXandYParams= new ParameterSet();
		euler1MultipleOfXandYParams.setParameter("x", Integer.class, 3);
		euler1MultipleOfXandYParams.setParameter("y", Integer.class, 5);
		euler1MultipleOfXandYParams.setParameter("n", Long.class, 900000000L);

		solutions.add(new MultipleOfXandYUsingCounter(euler1MultipleOfXandYParams));
		solutions.add(new MultipleOfXandYUsingMod(euler1MultipleOfXandYParams));
		solutions.add(new MultipleOfXandYUsingCircularList(euler1MultipleOfXandYParams));

		List<Future<?>> futures= new ArrayList<Future<?>>(solutions.size());
		for (Solve solver: solutions) {
			futures.add(executor.submit(solver));
		}
		for (Future<?> future : futures) {
			try {
				future.get();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

}
