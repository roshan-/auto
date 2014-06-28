package nirvana.solver;

import java.net.URL;
import java.util.List;

public abstract class ProblemDefinition {

	protected String problemName;
	protected int problemId;
	protected ProblemSet problemSet;
	protected URL problemUrl;

	protected ParameterSet constantParameters;
	protected ParameterSet problemParameters;

	protected Long extremeInputSize;
	protected Long sampleSize;
	protected Long simpleInputSize;
	protected Growth inputSizeGrowth;
	private double scaleFactor= 1.0;
	protected InputSizeIterator iter;


	private int variableSimpleCaseParametersIndex= 0;
	private int variableAverageCaseParametersIndex= 0;
	private int variableExtremeCaseParametersIndex= 0;
	private List<ParameterSet> variableEnumeratedParameterList;

	public abstract List<ParameterSet> generateEnumeratedParameterList();
	public abstract void setDefaults();

	protected ParameterSet getSimpleCaseParameterSet() {
		return variableEnumeratedParameterList.get(variableSimpleCaseParametersIndex);
	}

	protected ParameterSet getAverageCaseParameterSet() {
		return variableEnumeratedParameterList.get(variableAverageCaseParametersIndex);
	}

	protected ParameterSet getExtremeCaseParameterSet() {
		return variableEnumeratedParameterList.get(variableExtremeCaseParametersIndex);
	}



	protected void findScaleFactor() {
		if (inputSizeGrowth == Growth.BINARY_EXPONENTIAL) {
			scaleFactor= 2;
		} else if (inputSizeGrowth == Growth.EXPONENTIAL) {
			scaleFactor= Math.log(extremeInputSize-simpleInputSize)/Math.log(sampleSize);
		} else if (inputSizeGrowth == Growth.LINEAR) {
			scaleFactor= (extremeInputSize-simpleInputSize)/(sampleSize);
		}
	}

	public ProblemDefinition(ProblemSet problemSet, String problemName, int problemId,
			ParameterSet constantParameters, ParameterSet problemParameters) {
		this.problemSet= problemSet;
		this.problemName= problemName;
		this.problemId= problemId;
		this.constantParameters= constantParameters;
		this.problemParameters= problemParameters;

		if (problemParameters != null) {
			extremeInputSize= problemParameters.getParameter("extremeInputSize", Long.class);
			simpleInputSize= problemParameters.getParameter("simpleInputSize", Long.class);
			sampleSize= problemParameters.getParameter("sampleSize", Long.class);
			inputSizeGrowth= problemParameters.getParameter("inputSizeGrowth", Growth.class);
		}
		setDefaults();
		findScaleFactor();
		iter= new InputSizeIterator(simpleInputSize, extremeInputSize, inputSizeGrowth, scaleFactor);

		variableEnumeratedParameterList= generateEnumeratedParameterList();
		variableSimpleCaseParametersIndex= 0;
		variableAverageCaseParametersIndex= variableEnumeratedParameterList.size()/2;
		variableExtremeCaseParametersIndex= variableEnumeratedParameterList.size()-1;

	}

	@Override
	public String toString() {
		return problemName;
	}

	public void println() {
		synchronized(Analyzer.printLineLock) {
			System.out.println(this.toString());
		}
	}


	/**
	 * @hidden
	 */
	public String getProblemName() {
		return problemName;
	}
	/**
	 * @hidden
	 */
	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}
	/**
	 * @hidden
	 */
	public int getProblemId() {
		return problemId;
	}
	/**
	 * @hidden
	 */
	public void setProblemId(int problemId) {
		this.problemId = problemId;
	}
	/**
	 * @hidden
	 */
	public ProblemSet getProblemSet() {
		return problemSet;
	}
	/**
	 * @hidden
	 */
	public void setProblemSet(ProblemSet problemSet) {
		this.problemSet = problemSet;
	}
	/**
	 * @hidden
	 */
	public URL getProblemUrl() {
		return problemUrl;
	}
	/**
	 * @hidden
	 */
	public void setProblemUrl(URL problemUrl) {
		this.problemUrl = problemUrl;
	}

	/**
	 * @hidden
	 */
	public ParameterSet getConstantParameters() {
		return constantParameters;
	}
	/**
	 * @hidden
	 */
	public void setConstantParameters(ParameterSet constantParameters) {
		this.constantParameters = constantParameters;
	}

	/**
	 * @hidden
	 */
	public List<ParameterSet> getVariableEnumeratedParameterList() {
		return variableEnumeratedParameterList;
	}

	/**
	 * @hidden
	 */
	public void setVariableEnumeratedParameterList(
			List<ParameterSet> variableEnumeratedParameterList) {
		this.variableEnumeratedParameterList = variableEnumeratedParameterList;
	}

}
