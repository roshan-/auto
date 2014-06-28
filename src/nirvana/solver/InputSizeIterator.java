package nirvana.solver;

import java.util.Iterator;

public class InputSizeIterator implements Iterator<Long> {

	long begin;
	long curr;
	long next;
	long end;
	Growth growth;
	double scaleFactor;
	InputSizeIterator(long begin, long end, Growth growth, double scaleFactor ) {
		this.begin= begin;
		this.curr= begin;
		this.next= begin;
		this.end= end;
		this.growth= growth;
		this.scaleFactor= scaleFactor;
	}
	@Override
	public boolean hasNext() {
		return next<=end;
	}

	@Override
	public Long next() {
		Long ret= null;
		if (curr<=end) {
			ret= curr;
			curr= next;
			if (growth == Growth.LINEAR) {
				next += scaleFactor;
			} else if (growth == Growth.BINARY_EXPONENTIAL) {
				next *= 2;
			} else if (growth == Growth.NATURAL_EXPONENTIAL) {
				next *= Math.E;
			} else if (growth == Growth.QUADRATIC) {
				next += 2*next-1;
			}
		}
		return ret;
	}

	@Override
	public void remove() {
	}

}
