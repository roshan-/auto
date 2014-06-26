package nirvana.solver.euler.p1;

import nirvana.solver.ParameterSet;

public class MultipleOfXandYUsingCounter extends
		MultipleOfXandY {

	public MultipleOfXandYUsingCounter(ParameterSet params) {
		super("Circular Counter", params);
	}

	@Override
	public void execute() {
		int reset= x*y;
		int xcount= 1;
		int ycount= 1;
		int xycount= 1;
		for (Long i=1L;i<getInputSize();i++) {
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
