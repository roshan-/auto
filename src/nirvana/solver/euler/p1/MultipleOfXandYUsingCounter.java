package nirvana.solver.euler.p1;

import nirvana.solver.ParameterSet;
import nirvana.solver.ProblemDefinition;

public class MultipleOfXandYUsingCounter extends
		MultipleOfXandYStrategy {

	public MultipleOfXandYUsingCounter(ProblemDefinition multipleOfXandYProblem) {
		super("Circular Counter", multipleOfXandYProblem);
	}

	@Override
	public void execute(ParameterSet params) {
		int reset= x*y;
		int xcount= 1;
		int ycount= 1;
		int xycount= 1;
		long inputSize= params.getParameter("inputSize", Long.class);
		for (long i=1L;i<inputSize;i++) {
			if (xycount==reset) {
				sum= sum+i;
				xcount= 0;
				ycount= 0;
				xycount= 0;
			} else if (xcount==x) {
				sum= sum+i;
				xcount=0;
			} else if (ycount==y) {
				sum= sum+i;
				ycount=0;
			}
			xcount++;
			ycount++;
			xycount++;
		}
	}
}
