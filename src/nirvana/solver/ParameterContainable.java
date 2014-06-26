package nirvana.solver;

import java.util.Set;

// API for a Typed, Named, Unordered Set of Dynamic/Run-time parameters to be passed between two objects
public interface ParameterContainable {
	public <T> void setParameter(String name, Class<T> type, T value);
	public <T> T getParameter(String name, Class<T> type);
	public int getNumberOfParameters();
	public Set<String> getParameterNames();

}
