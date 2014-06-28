package nirvana.solver.euler.p1;

import java.security.InvalidParameterException;

import nirvana.solver.ParameterSet;
import nirvana.solver.ProblemDefinition;
import nirvana.solver.SolutionStrategy;

public abstract class MultipleOfXandYStrategy extends SolutionStrategy{
	protected Integer x;
	protected Integer y;
	protected long sum= 0L;

	MultipleOfXandYStrategy(String strategyName, ProblemDefinition problemDefinition) {
		super(strategyName, problemDefinition);
	}

	@Override
	public void verifyConstantParameters() {
		if (problemDefinition.getConstantParameters() == null) {
			throw new InvalidParameterException();
		}
		x= problemDefinition.getConstantParameters().getParameter("x", Integer.class);
		y= problemDefinition.getConstantParameters().getParameter("y", Integer.class);
		if (x == null || y == null) {
			throw new InvalidParameterException();
		}
	}

	@Override
	public String getAnswer() {
		return "Sum: " + sum;
	}

	@Override
	public void verifyVariableEnumeratedParameters()
			throws InvalidParameterException {
		for (ParameterSet params : problemDefinition.getVariableEnumeratedParameterList()) {
			if (params.getParameter("inputSize", Long.class)==null) {
				throw new InvalidParameterException();
			}
		}
	}
}
