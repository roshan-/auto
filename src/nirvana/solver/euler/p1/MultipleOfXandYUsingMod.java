package nirvana.solver.euler.p1;

import nirvana.solver.ParameterSet;
import nirvana.solver.ProblemDefinition;

public class MultipleOfXandYUsingMod extends
		MultipleOfXandYStrategy {

	public MultipleOfXandYUsingMod(ProblemDefinition multipleOfXandYProblem) {
		super("Math Mod", multipleOfXandYProblem);
	}

	@Override
	public void execute(ParameterSet params) {
		long inputSize= params.getParameter("inputSize", Long.class);
		for (long i=1L;i<inputSize;i++) {
			if (i%x==0 || i%y==0) {
				sum= sum+i;
			}
		}
	}
}
