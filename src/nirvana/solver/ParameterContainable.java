package nirvana.solver;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.tuple.ImmutablePair;
/**
 * @author Roshan Diwakar

 * @notes Interface for a Typed, Named, Unordered Set of
 * Dynamic/Run-time parameters
 * to be passed between two objects
 */
public interface ParameterContainable {
	public <T> ParameterContainable setParameter(String name, Class<T> type, T value);
	public <T> T getParameter(String name, Class<T> type);
	public int getNumberOfParameters();
	public Set<String> getParameterNames();
	public ParameterContainable mergeParameters(ParameterContainable more);
	public Map<String,ImmutablePair<Class<?>, Object>> getParameters();

}
