package nirvana.solver;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 * @author Roshan Diwakar
 */

public class ParameterSet implements ParameterContainable {
	private Map<String,ImmutablePair<Class<?>, Object>> parameters= new HashMap<String,ImmutablePair<Class<?>, Object>>();

	@Override
	public Map<String, ImmutablePair<Class<?>, Object>> getParameters() {
		return parameters;
	}

	@Override
	public ParameterContainable mergeParameters(ParameterContainable more) {
		parameters.putAll(more.getParameters());
		return this;
	}

	@Override
	public <T> ParameterSet setParameter(String name, Class<T> type, T value) {
		if (name.length() == 0 || type==null) {
			throw new InvalidParameterException();
		} else {
			ImmutablePair<Class<?>, Object> pair= new ImmutablePair<Class<?>, Object>(type, value);
			parameters.put(name, pair);
		}
		return this;
	}

	@Override
	public <T> T getParameter(String name, Class<T> type) {
		ImmutablePair<Class<?>, Object> pair= parameters.get(name);
		if (pair == null) {
			return null;
		}
		if (pair.getLeft()==type) {
			return type.cast(pair.getRight());
		} else {
			throw new InvalidParameterException();
		}
	}

	@Override
	public int getNumberOfParameters() {
		return parameters.size();
	}

	@Override
	public Set<String> getParameterNames() {
		return parameters.keySet();
	}
	/**
	 * @hidden
	 */
	@Override
	public String toString() {
		StringBuilder returnStr= new StringBuilder("Parameters[");
		for (Entry<String, ImmutablePair<Class<?>, Object>> entry : parameters.entrySet()) {
			returnStr.append(entry.getKey()).append("= ").append(entry.getValue().getRight().toString()).append("; ");
		}
		return returnStr.append("]").toString();

	}
	/**
	 * @hidden
	 */
	public void println() {
		synchronized(Analyzer.printLineLock) {
			System.out.println(this.toString());
		}
	}
	/**
	 * @hidden
	 */
	public void print() {
		synchronized(Analyzer.printLineLock) {
			System.out.print(this.toString());
		}
	}



}
