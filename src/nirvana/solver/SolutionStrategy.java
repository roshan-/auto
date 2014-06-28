package nirvana.solver;

import java.security.InvalidParameterException;

public abstract class SolutionStrategy {
	public abstract void execute(ParameterSet params);
	public abstract String getAnswer();
	protected String strategyName;
	protected ProblemDefinition problemDefinition;

	public abstract void verifyConstantParameters() throws InvalidParameterException;
	public abstract void verifyVariableEnumeratedParameters() throws InvalidParameterException;

	public String getStrategyName() {
		return strategyName;
	}

	public ProblemDefinition getProblemDefinition() {
		return problemDefinition;
	}

	protected SolutionStrategy(String strategyName, ProblemDefinition problemDefinition) {
		this.strategyName= strategyName;
		this.problemDefinition= problemDefinition;
		verifyConstantParameters();
		verifyVariableEnumeratedParameters();
	}
}
