package nirvana.solver;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

// Implements a Typed, Named, Unordered Set of Dynamic/Run-time parameters to be passed between two objects
public class ParameterSet implements ParameterContainable {
	private Map<String,Pair<Class<?>, Object>> parameters= new HashMap<String,Pair<Class<?>, Object>>();

	@Override
	public String toString() {
		StringBuilder returnStr= new StringBuilder("Parameters[");
		for (Entry<String, Pair<Class<?>, Object>> entry : parameters.entrySet()) {
			returnStr.append(entry.getKey()).append("= ").append(entry.getValue().getRight().toString()).append("; ");
		}
		return returnStr.append("]").toString();

	}

	public void print() {
		System.out.print(this.toString());
	}

	@Override
	public <T> void setParameter(String name, Class<T> type, T value) {
		if (name.length() == 0 || type==null) {
			throw new InvalidParameterException();
		} else {
			Pair<Class<?>, Object> pair= new MutablePair<Class<?>, Object>(type, value);
			parameters.put(name, pair);
		}
	}

	@Override
	public <T> T getParameter(String name, Class<T> type) {
		Pair<Class<?>, Object> pair= parameters.get(name);
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
}
