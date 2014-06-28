package nirvana.solver;

import java.lang.management.ManagementFactory;

/**
 *
 * @author Roshan Diwakar
 * @note Framework to solve, instrument
 * and analyze any algorithm that takes
 * dynamic, named, unordered set of parameters
 * @composed 1..1 has 1..1 ProblemDefinition
 * @composed 1..1 has 1..1 SolutionStrategy
 */
public class Analyzer implements Runnable {
	protected SolutionStrategy solutionStrategy;
	protected ProblemDefinition problemDefintion;
	protected long executionTime;
	private long userTime;
	private long inputSize;

	// Ensures logs are not scrambled.
	public static Object printLineLock= new Object();

	protected Analyzer(SolutionStrategy solutionStrategy) {
		this.solutionStrategy= solutionStrategy;
		this.problemDefintion= solutionStrategy.getProblemDefinition();
	}

	private void println() {
		executionTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - executionTime;
		userTime= ManagementFactory.getThreadMXBean().getCurrentThreadUserTime() - userTime;
		synchronized(printLineLock) {
			System.out.printf("Strategy: %s; %s; Input Size: %d; Time/Input %dns; Time: %dms; UserTime: %dms]\n", solutionStrategy.getStrategyName(),
					solutionStrategy.getAnswer(),inputSize, executionTime/inputSize, executionTime/1000000, userTime/1000000);
		}
	}

	/**
	 * @note The core framework method, where all the algorithms gets executed.
	 * Strategy, Problem Definition and Parameters are injected into this method
	 */
	@Override
	public void run() {
		for (ParameterSet params : problemDefintion.getVariableEnumeratedParameterList()) {
			inputSize= params.getParameter("inputSize", Long.class);
			executionTime= ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
			userTime= ManagementFactory.getThreadMXBean().getCurrentThreadUserTime();
			solutionStrategy.execute(params);
			println();
		}
	}
}
