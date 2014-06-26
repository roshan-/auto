package nirvana.solver;

import java.util.Set;

// API to pass Named, Typed Dynamic Unordered Set of parameters between two objects
public interface ParameterContainable {
	public <T> void setParameter(String name, Class<T> type, T value);
	public <T> T getParameter(String name, Class<T> type);
	public int getNumberOfParameters();
	public Set<String> getParameterNames();

}
