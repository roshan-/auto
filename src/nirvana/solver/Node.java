package nirvana.solver;

public class Node<T> {
	private T nodeValue;
	private Node<T> next;

	public T getNodeValue() {
		return nodeValue;
	}
	public void setNodeValue(T v) {
		nodeValue= v;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}
}

