package nirvana.solver.euler.p1;

import java.util.ArrayList;
import java.util.List;

import nirvana.solver.Growth;
import nirvana.solver.ParameterSet;
import nirvana.solver.ProblemDefinition;
import nirvana.solver.ProblemSet;

public class MultipleOfXandYProblem extends ProblemDefinition{
	public MultipleOfXandYProblem(ParameterSet constantParameters, ParameterSet problemParameters) {
		super(ProblemSet.PROJECT_EULER, "Multiple Of X and Y", 1, constantParameters, problemParameters);
	}

	@Override
	public List<ParameterSet> generateEnumeratedParameterList() {
		List<ParameterSet> retList= new ArrayList<ParameterSet>();
		while(iter.hasNext()) {
			ParameterSet params= new ParameterSet();
			retList.add(params.setParameter("inputSize", Long.class, iter.next()));
		}
		return retList;
	}

	@Override
	public void setDefaults() {
		//		Default values if the service user doesn't pass them
		if (this.constantParameters == null) {
			this.constantParameters= new ParameterSet();
		}
		if (this.constantParameters.getParameter("x", Integer.class) == null)
			this.constantParameters.setParameter("x", Integer.class, 3);
		if (this.constantParameters.getParameter("y", Integer.class) == null)
			this.constantParameters.setParameter("y", Integer.class, 5);
		if (extremeInputSize == null) extremeInputSize= 9000000000L;
		if (simpleInputSize == null) simpleInputSize= 1000L;
		if (sampleSize == null) sampleSize= 250L;
		if (inputSizeGrowth == null) inputSizeGrowth= Growth.BINARY_EXPONENTIAL;
	}
}
