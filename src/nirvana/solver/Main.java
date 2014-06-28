package nirvana.solver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import nirvana.solver.euler.p1.MultipleOfXandYProblem;
import nirvana.solver.euler.p1.MultipleOfXandYUsingCircularList;
import nirvana.solver.euler.p1.MultipleOfXandYUsingCounter;
import nirvana.solver.euler.p1.MultipleOfXandYUsingMod;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor= Executors.newFixedThreadPool(30);
		List<Analyzer> solutions= new ArrayList<Analyzer>();

		// Euler 1
		MultipleOfXandYProblem multipleOfXandYProblem= new MultipleOfXandYProblem(null,null);
		solutions.add(new Analyzer(new MultipleOfXandYUsingCircularList(multipleOfXandYProblem)));
		solutions.add(new Analyzer(new MultipleOfXandYUsingCounter(multipleOfXandYProblem)));
		solutions.add(new Analyzer(new MultipleOfXandYUsingMod(multipleOfXandYProblem)));

		List<Future<?>> futures= new ArrayList<Future<?>>(solutions.size());
		for (Analyzer solver: solutions) {
			futures.add(executor.submit(solver));
		}
		for (Future<?> future : futures) {
			try {
				future.get();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		executor.shutdown();
	}
}
