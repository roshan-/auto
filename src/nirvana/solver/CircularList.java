package nirvana.solver;

/**
* @author Roshan Diwakar
* @notes CircularLinkedList for fast Modular Arithmetic
* and other uses
* @composed 1..* has 1..* Node
*/
public class CircularList<T> {
	protected Node<T> head= null;
	protected Node<T> tail= null;

	public void add(T item) {
		Node<T> node= new Node<T>();
		node.setNodeValue(item);
		if (head==null) {
			head= node;
			tail= node;
			head.setNext(node);
		} else {
			node.setNext(head);
			tail.setNext(node);
			tail= node;
		}
	}

	public T forward() {
		head= head.getNext();
		tail= tail.getNext();
		return head.getNodeValue();
	}

	public T peek() {
		return head.getNodeValue();
	}

	public void print() {
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		StringBuilder printString= new StringBuilder(32);
		if (head==null) {
			printString.append("null");
		} else {
			Node<T> curr= head;
			printString.append("[").append(curr.getNodeValue());
			while(curr.getNext() != head) {
				printString.append(", ").append(curr.getNext().getNodeValue());
				curr= curr.getNext();
			}
			printString.append("]");
		}
		return printString.toString();
	}
}
