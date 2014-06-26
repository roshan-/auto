package nirvana.solver.euler.p1;

import nirvana.solver.CircularList;
import nirvana.solver.ParameterSet;

public class MultipleOfXandYUsingCircularList extends
		MultipleOfXandY {

	public MultipleOfXandYUsingCircularList(ParameterSet params) {
		super("Circular List", params);
	}

	@Override
	public void execute() {
		CircularList<Integer> circle= new CircularList<>();
		int lcm= x*y;
		for (int i=1; i<=lcm; i++) {
			circle.add((i%x==0||i%y==0)?1:0);
		}
		for (Long i=1L;i<getInputSize();i++) {
			if (circle.peek()==1) {
				sum= sum+i;
			}
			circle.forward();
		}
	}
}
