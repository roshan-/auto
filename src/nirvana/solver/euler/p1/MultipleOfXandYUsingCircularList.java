package nirvana.solver.euler.p1;

import nirvana.solver.CircularList;
import nirvana.solver.ParameterSet;
import nirvana.solver.ProblemDefinition;

public class MultipleOfXandYUsingCircularList extends
		MultipleOfXandYStrategy {

	public MultipleOfXandYUsingCircularList(ProblemDefinition multipleOfXandYProblem) {
		super("Circular List", multipleOfXandYProblem);
	}

	@Override
	public void execute(ParameterSet params) {
		CircularList<Integer> circle= new CircularList<>();
		int lcm= x*y;
		for (int i=1; i<=lcm; i++) {
			circle.add((i%x==0||i%y==0)?1:0);
		}
		long inputSize= params.getParameter("inputSize", Long.class);

		for (long i=1L;i<inputSize;i++) {
			if (circle.peek()==1) {
				sum= sum+i;
			}
			circle.forward();
		}
	}
}
