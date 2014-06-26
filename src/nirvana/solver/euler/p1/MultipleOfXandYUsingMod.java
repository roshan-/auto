package nirvana.solver.euler.p1;

import nirvana.solver.ParameterSet;

public class MultipleOfXandYUsingMod extends
		MultipleOfXandY {

	public MultipleOfXandYUsingMod(ParameterSet params) {
		super("Math Mod", params);
	}

	@Override
	public void execute() {
		for (Long i=1L;i<getInputSize();i++) {
			if (i%x==0 || i%y==0) {
				sum= sum+i;
			}
		}
	}
}
