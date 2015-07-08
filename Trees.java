import java.util.Arrays;
import java.util.LinkedList;

/**
 * 
 * @author davidhurng
 *
 */
public class Trees {
	
	private void preorderTrav(Node root) {
		
//		//print itself first
//		System.out.print(root);
//		//take root check if it has a left child
//		preorderTrav(root.leftChild);
//		preorderTrav(root.rightChild);
//		//if so do it again
//		//else print that go up and get right if exist
		
		//without recursion
		//use a stack		
		Stack stack = new Stack();
		
		
		//define own stack
		
		//push root
		stack.push(root);
		//while not empty 
		while(stack.size != 0) {
			//pop node
			Node curr = stack.pop();
			System.out.println(curr.data);
			if(curr.rightChild != null) {
				stack.push(curr.rightChild);
			}
			
			if(curr.leftChild != null) {
				stack.push(curr.leftChild);
			}
			//print val
			//if right then push
			//if left then push
		}
		
	}
	
	
	
	private void inorderTrav(Node root) {
		if(root == null) {
			return;
		}

		inorderTrav(root.leftChild);
		System.out.println(root);
		inorderTrav(root.rightChild);
	}
	
	private void postorderTrav(Node root) {
		if(root == null) {
			return;
		}
		
		postorderTrav(root.leftChild);
		postorderTrav(root.rightChild);
		System.out.println(root);
	}
	
	
	private Node lowCommonAnc(Node root, Node a, Node b) {
		Node curr = root;
		
		while(curr != null) {
			if(curr.data < a.data && curr.data < b.data) {
				curr = curr.leftChild;
			}
			else if(curr.data > a.data && curr.data > b.data) {
				curr = curr.rightChild;
			}
			else { 
				return curr;
			}
		}
		//only if empty tree
		return null;
		//check if node is smaller
		//check if node is bigger
		//if between return
	}
	
	//static way you can use oop instead
	public static Node balance(Node root) {
		Node newRoot = root.leftChild;
		root.leftChild = newRoot.rightChild;
		root.rightChild = root;
		return newRoot;
	}
	
	public static Node heapify(Node root) {
		//count nodes
		int size = traverse(root, 0, null);
		//load nodes into array
		Node[] nodeArray = new Node[size];
		
		Arrays.sort(nodeArray, new Comparator<Node>() {
			@Override public int compare(Node m, Node n) {
				return (m.data < n.data)
			}
		});
		//2i + 1 2i + 2
		return null;
	}
	
	public static int traverse(Node node, int count, Node[] array) {
		if(node == null) {
			return count;
		}
		if(array != null) {
			array[count] = node;
		}
		count++;
		count = traverse(node.leftChild, count, array);
		count = traverse(node.rightChild, count, array);
		return count;
	}
	
	
	private int height(Node i) {
		if(i == null) {
			return 0;
		}
		return 1 + Math.max(height(i.leftChild), height(i.rightChild));
	}

	private static class Node {
		int data;
		Node[] children;
		Node leftChild;
		Node rightChild;
		
		public Node(Node[] children) {
			this.children = children;
		}
		
		public int numChildren() {
			return children.length;
		}
		
		public Node getChild(int i) {
			return children[i];
		}
	}
}
