package nirvana.solver.euler.p1;

import nirvana.solver.ParameterSet;
import nirvana.solver.ProblemSet;
import nirvana.solver.Solve;

public abstract class MultipleOfXandY extends Solve{
	protected Integer x;
	protected Integer y;
	protected Long sum= 0L;

	MultipleOfXandY(String problemStrategy, ParameterSet params) {
		super(ProblemSet.PROJECT_EULER, 1, "Multiple of X and Y", problemStrategy, params);
	}

	@Override
	public void prepareParameters() {
		x= getParams().getParameter("x", Integer.class);
		y= getParams().getParameter("y", Integer.class);
		if (getParams().getNumberOfParameters()!=3 || x == null || y == null) {
			throw new RuntimeException("ParameterSet Error -- Usage: (x : int, y : int, n : Long)");
		}
	}

	@Override
	public void printAnswer() {
		System.out.println("Sum= " + sum);
	}
}
