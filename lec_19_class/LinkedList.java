package lec_19_class;

public class LinkedList<T> {
	private class Node {
		T data;
		Node next;

		Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	private int size;
	private Node head;
	private Node tail;

	public LinkedList() {
		// TODO Auto-generated constructor stub
		this.size = 0;
		this.head = null;
		this.tail = null;
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size() == 0;
	}

	public void addFirst(T item) {
		Node node = new Node(item, this.head);

		this.head = node;
		if (this.isEmpty()) {
			this.tail = node;
		}
		this.size++;
	}

	public void addLast(T item) {
		Node node = new Node(item, null);
		if (this.isEmpty()) {
			this.head = node;
			this.tail = node;
		} else {
			this.tail.next = node;
			this.tail = node;
		}
		this.size++;
	}

	private Node getNode(int index) throws Exception {
		if (index < 0 || index >= this.size()) {
			throw new Exception("Index out of Range");
		}

		Node temp = this.head;
		int counter = 0;
		while (counter < index) {
			temp = temp.next;
			counter++;
		}

		return temp;
	}

	public void addAt(int index, T item) throws Exception {
		if (index < 0 || index > this.size()) {
			throw new Exception("Index out of Range");
		}

		if (index == 0) {
			this.addFirst(item);
		} else if (index == this.size()) {
			this.addLast(item);
		} else {
			Node temp = getNode(index - 1);
			Node node = new Node(item, temp.next);
			temp.next = node;
			this.size++;
		}
	}

	public T getFirst() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is Empty");
		}

		return this.head.data;
	}

	public T getLast() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is Empty");
		}

		return this.tail.data;
	}

	public T getAt(int index) throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is Empty");
		}
		if (index < 0 || index >= this.size()) {
			throw new Exception("Index out of Range");
		}

		Node temp = this.getNode(index);
		return temp.data;
	}

	public T removeFirst() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is empty");
		}
		Node rv = this.head;
		if (this.size() == 1) {
			this.tail = null;
			this.head = null;
		} else {
			this.head = this.head.next;
		}
		this.size--;
		return rv.data;
	}

	public T removeLast() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is empty");
		}
		Node rv = this.tail;
		if (this.size() == 1) {
			this.tail = null;
			this.head = null;
		} else {
			Node temp = this.getNode(this.size() - 2);
			temp.next = null;
			this.tail = temp;
		}
		this.size--;
		return rv.data;
	}

	public T removeAt(int index) throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is empty");
		}
		if (index < 0 || index >= this.size()) {
			throw new Exception("Index out of Range");

		}
		if (index == 0) {
			return this.removeFirst();
		} else if (index == this.size() - 1) {
			return this.removeLast();
		} else {
			Node rv = this.getNode(index);
			Node temp = this.getNode(index - 1);
			temp.next = temp.next.next;
			this.size--;
			return rv.data;
		}

	}

	public void display() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is empty");
		}
		Node temp = this.head;
		while (temp != null) {
			System.out.print(temp.data + "=>");
			temp = temp.next;
		}
		System.out.println("END");
		System.out.println("*************************************");
	}

	public void reverseDI() throws Exception {
		int si = 0, li = this.size - 1;
		while (si <= li) {
			Node left = this.getNode(si);
			Node right = this.getNode(li);

			T temp = left.data;
			left.data = right.data;
			right.data = temp;

			si++;
			li--;
		}
	}

	public void reversePI() {
		Node prev = this.head;
		Node curr = prev.next;

		while (curr != null) {
			Node tempPrev = prev;
			Node tempCurr = curr;

			prev = curr;
			curr = curr.next;

			tempCurr.next = tempPrev;

		}

		Node temp = this.head;
		this.head = this.tail;
		this.tail = temp;

		this.tail.next = null;
	}

	public void reversePR() {
		reverse(this.head);

		Node temp = this.head;
		this.head = this.tail;
		this.tail = temp;

		this.tail.next = null;
	}

	private void reverse(Node node) {
		if (node == this.tail) {
			return;
		}
		reverse(node.next);
		node.next.next = node;
	}

	public void reverseDR() {
		HeapMover left = new HeapMover(this.head);
		this.reverseDR(left, this.head, 0);
	}

	private void reverseDR(HeapMover left, Node right, int floor) {
		if (right == null) {
			return;
		}

		reverseDR(left, right.next, floor + 1);

		if (floor >= this.size / 2) {
			T temp = left.node.data;
			left.node.data = right.data;
			right.data = temp;

			left.node = left.node.next;
		}
	}

	public T mid() {
		return midNode().data;
	}

	private Node midNode() {
		Node slow = this.head, fast = this.head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}

		return slow;
	}

	public T KthNodeFromEnd(int k) throws Exception {
		if (k < 1 || k > this.size) {
			throw new Exception("Invalid Index");
		}

		Node fast = this.head, slow = this.head;

		for (int i = 0; i < k; i++) {
			fast = fast.next;
		}

		while (fast != null) {
			fast = fast.next;
			slow = slow.next;
		}

		return slow.data;
	}

	private class HeapMover {
		Node node;

		public HeapMover(Node node) {
			// TODO Auto-generated constructor stub
			this.node = node;
		}
	}
	
	public int find(T data) {
		int rv = find(this.head, data);
		return rv;
	}

	private int find(Node node, T data) {
		if (node == null) {
			return -1;
		}
		// "==" checks reference and .equals() checks content
		if (node.data.equals(data)) {
			return 0;
		} else {
			int rv = find(node.next, data);
			if (rv == -1) {
				return rv;
			} else {
				return rv + 1;
			}
		}
	}
	
	
}
