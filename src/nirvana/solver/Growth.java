package nirvana.solver;

public enum Growth {
	/**
	 * @tag y=c
	 */
	CONSTANT,
	/**
	 * @tag y=m*log(x)
	 */
	LOGARITHMIC,
	/**
	 * @tag y=mx+c
	 */
	LINEAR,
	/**
	 * @tag y=mx*log(x)
	 */
	LOG_LINEAR,
	/**
	 * @tag y=mx^2 + nx + c
	 */
	QUADRATIC,
	/**
	 * @tag y=2^x
	 */
	BINARY_EXPONENTIAL,
	/**
	 * @tag y=e^x
	 */
	NATURAL_EXPONENTIAL,
	/**
	 * @tag y=m^x
	 */
	EXPONENTIAL
}
