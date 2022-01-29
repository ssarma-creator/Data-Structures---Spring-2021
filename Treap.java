
/** Homework 5 
 * Topic- Binary search Trees and heaps
 * Student Name - Sudarshana Sarma
 */
import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {
	private static class Node<E> {
		public E data;
		public int priority;
		public Node<E> left;
		public Node<E> right;

		/**
		 * Constructor to create a node
		 * 
		 * @param data information to be stored in the node
		 * @param priority priority in treap
		 * @throws IllegalArgumentException if the given data is null
		 */
		public Node(E data, int priority) throws IllegalArgumentException {
			if (data == null)
				throw new IllegalArgumentException();
			this.data = data;
			this.priority = priority;
			this.left = null;
			this.right = null;
		}

		/**
		 * This method performs right rotation; tree is rotated right with respect to
		 * the reference node
		 *
		 * @return the new node after the rotation
		 */
		public Node<E> rotateRight() {
			Node<E> rot = this.left;
			Node<E> rot1 = rot.right;
			rot.right = this;
			this.left = rot1;
			return rot;
		}

		/**
		 * This method performs left rotation; tree is rotated left with respect to the
		 * reference node
		 *
		 * @return the new node after the rotation
		 */
		public Node<E> rotateLeft() {
			Node<E> rot = this.right;
			Node<E> rot1 = rot.left;
			rot.left = this;
			this.right = rot1;
			return rot;
		}

	}
	

	private Random priorityGenerator;
	private Node<E> root;

	/**
	 * Constructor to create null treap.
	 *  It initializes random generator
	 */
	public Treap() {
		root = null;
		priorityGenerator = new Random();
	}

	/**
	 * Constructor to create null treap
	 *
	 * @param seed to initialize random Generator with given seed value
	 */
	public Treap(long seed) {
		root = null;
		priorityGenerator = new Random(seed);
	}

	/**
	 * Add new node in treap.
	 *
	 * @param key data to be added in treap.
	 * @return true if data added successfully otherwise false
	 */
	boolean add(E key) {
		return add(key, priorityGenerator.nextInt());
	}

	/**
	 * Method to add an element reheap() method is used to maintain heap property in
	 * treap
	 * 
	 * @param key      The data to be added
	 * @param priority priority of node
	 * @return true after the successful addition else false
	 */
	boolean add(E key, int priority) {
		if (root == null) {
			root = new Node<E>(key, priority);
			return true;
		} else {
			Node<E> temproot = root;
			Stack<Node<E>> stack = new Stack<>();
			while (temproot != null) {
				if (key.compareTo(temproot.data) == 0) {
					return false;
				} else if (key.compareTo(temproot.data) < 0) {
					stack.push(temproot);
					if (temproot.left == null) {
						temproot.left = new Node<E>(key, priority);
						reheap(stack, temproot.left);
						return true;
					} else {
						temproot = temproot.left;
					}
				} else {
					stack.push(temproot);
					if (temproot.right == null) {
						temproot.right = new Node<E>(key, priority);
						reheap(stack, temproot.right);
						return true;
					} else {
						temproot = temproot.right;
					}
				}
			}
			return false;
		}
	}

	/**
	 * this method restores the heap properties
	 * 
	 * @param node  is used to indicate current node
	 * @param stack stack to maintain node path from root to given node
	 */
	private void reheap(Stack<Node<E>> stack, Node<E> node) {
		while (!stack.empty()) {
			Node<E> scan = stack.pop();
			if (node.priority > scan.priority) {
				if (scan.left == node) {
					node = scan.rotateRight();
				} else {
					node = scan.rotateLeft();
				}
				if (!stack.empty()) {
					Node<E> stackPeek = stack.peek();
					if (stackPeek.left == scan) {
						stackPeek.left = node;
					} else {
						stackPeek.right = node;
					}
				} else {
					this.root = node;
				}
			} else {
				return;
			}
		}
	}

	/**
	 * This method deletes given node in treap.
	 *
	 * @param key the key to be removed
	 * @return true if the item is found and removed successfully otherwise returns
	 *         false
	 */
	boolean delete(E key) {
		if (!find(key)) {
			return false;
		} else {
			root = delete(root, key);
			return true;
		}
	}

	/**
	 * Method that removes the key
	 * 
	 * @param key the key to be removed
	 * @return true if the item is found and removed successfully otherwise returns
	 *         false
	 */
	private Node<E> delete(Node<E> temproot, E key) {
		if (temproot == null) {
			return temproot;
		}
		int compareTo = key.compareTo(temproot.data);
		if (compareTo < 0) {
			temproot.left = delete(temproot.left, key);
			return temproot;
		} else if (compareTo > 0) {
			temproot.right = delete(temproot.right, key);
			return temproot;
		} else {
			if (temproot.right == null) {
				temproot = temproot.left;
			} else if (temproot.left == null) {
				temproot = temproot.right;
			} else {
				if (temproot.left.priority > temproot.right.priority) {
					temproot = temproot.rotateRight();
					temproot.right = delete(temproot.right, key);
				} else {
					temproot = temproot.rotateLeft();
					temproot.left = delete(temproot.left, key);
				}
			}
		}
		return temproot;
	}

	/**
	 * This is a method to find a data from treap. It works recursively.
	 * 
	 * @param root root node of treap.
	 * @param key  data to be find.
	 * @return node of the data to be find.
	 */
	private Node<E> findNode(Node<E> root, E key) {
		if (root == null)
			return null;

		if (root.data.compareTo(key) == 0)
			return root;

		if (root.data.compareTo(key) > 0) {
			root = root.left;
		} else {
			root = root.right;
		}
		return findNode(root, key);
	}

	/**
	 * 
	 * Wrapper method to find a data in the treap
	 * 
	 * @param root root node of the treap.
	 * @param key  data to be found in treap.
	 * @return true if data found otherwise false.
	 */
	private boolean find(Node<E> root, E key) {
		return !(findNode(root, key) == null);
	}

	/***
	 * This method will find a given data from treap
	 * 
	 * @param key data to be found in treap.
	 * @return true if data found otherwise false.
	 */
	public boolean find(E key) {
		return find(root, key);
	}

	/**
	 * This method provides a String representation of the Treap
	 *
	 * @return String representation
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		traverse(root, 1, s);
		return s.toString();
	}

	/**
	 * This method helps to traverse through the tree
	 * It is preorder traversal
	 * @param node  node to start the method
	 * @param depth height of the tree
	 * @param sb    the string that stores the String representation for each node
	 */
	private void traverse(Node node, int depth, StringBuilder s) {
		for (int i = 1; i < depth; i++) {
			s.append(" ");
		}

		if (node == null) {
			s.append("null\n");
		} else {
			String string = "(key= " + node.data + ", Priority= " + node.priority + ")\n";
			s.append(string);
			traverse(node.left, depth + 1, s);
			traverse(node.right, depth + 1, s);
		}
	}

	/*
	 * @test
	 */
	public static void main(String[] args) {
		Treap<Integer> test = new Treap<Integer>();
		test.add(4, 19);
		test.add(2, 31);
		test.add(6, 70);
		test.add(1, 84);
		test.add(3, 12);
		test.add(5, 83);
		test.add(7, 26);
		System.out.println(test.toString());
		System.out.println(test.delete(7));
		System.out.println();
		System.out.println(test.delete(10));
		System.out.println();
		System.out.println(test.find(4));
		System.out.println();
		System.out.println(test.find(40));
		System.out.println();
		System.out.println(test.toString());

	}
}