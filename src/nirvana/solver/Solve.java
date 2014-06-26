package nirvana.solver;

import java.lang.management.ManagementFactory;
import java.net.URL;

// Framework to solve, instrument any algorithm that takes dynamic named, dynamic, unordered set of parameters
// Must include n -- indicating inputSize
public abstract class Solve implements Runnable {
	public abstract void execute();
	public abstract void prepareParameters();
	public abstract void printAnswer();

	private ParameterSet params;
	private Long inputSize;
	private Long executionTime;
	private Long userTime;
	private String problemName;
	private int problemId;
	private ProblemSet problemSet;
	private URL problemUrl;
	private String problemStrategy;
	// Ensures logs are not scrambled.
	private static Object printLineLock= new Object();

	protected Solve(ProblemSet problemSet, int problemId, String problemName, String problemStrategy, ParameterSet params) {
		this.problemSet= problemSet;
		this.problemId= problemId;
		this.problemName= problemName;
		this.problemStrategy= problemStrategy;
		this.setParams(params);
		inputSize= params.getParameter("n", Long.class);
		if (inputSize==null) {
			throw new RuntimeException("Need InputSize 'n' in the Parameter Set");
		}
	}

	private void printSolveInfo() {
		System.out.printf("%s: %d %s using %s -- ", problemSet, problemId, problemName, problemStrategy);
	}

	private void printStats() {
		System.out.printf("[Input Size: %d; Time/Input %dns; Time: %dms; User Time: %dms]\n", inputSize, executionTime/inputSize, executionTime/1000000, userTime/1000000);
	}

	@Override
	public void run() {
		// All Algorithms run parallel
		// The only resource they share is an STDOUT line.
		executionTime= ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
		userTime= ManagementFactory.getThreadMXBean().getCurrentThreadUserTime();
		synchronized(printLineLock) {
			printSolveInfo();
			params.print();
		}
		prepareParameters();
		execute();
		synchronized(printLineLock) {
			printSolveInfo();
			printAnswer();
		}
		executionTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() - executionTime;
		userTime= ManagementFactory.getThreadMXBean().getCurrentThreadUserTime() - userTime;
		synchronized(printLineLock) {
			printSolveInfo();
			printStats();
		}
	}

	// Java Bean Getter/Setter
	public Long getExecutionTime() {
		return executionTime;
	}
	public void setExecutionTime(Long executionTime) {
		this.executionTime = executionTime;
	}
	public String getProblemName() {
		return problemName;
	}
	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}
	public int getProblemId() {
		return problemId;
	}
	public void setProblemId(int problemId) {
		this.problemId = problemId;
	}
	public ProblemSet getProblemSet() {
		return problemSet;
	}
	public void setProblemSet(ProblemSet problemSet) {
		this.problemSet = problemSet;
	}
	public URL getProblemUrl() {
		return problemUrl;
	}
	public void setProblemUrl(URL problemUrl) {
		this.problemUrl = problemUrl;
	}

	public ParameterSet getParams() {
		return params;
	}
	public void setParams(ParameterSet params) {
		this.params = params;
	}
	public Long getInputSize() {
		return inputSize;
	}
	public void setInputSize(Long inputSize) {
		this.inputSize = inputSize;
	}
	public String getProblemStrategy() {
		return problemStrategy;
	}
	public void setProblemStrategy(String problemStrategy) {
		this.problemStrategy = problemStrategy;
	}
}
