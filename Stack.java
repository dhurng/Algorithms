/**
 * 
 * @author davidhurng
 * Stack Implementation
 */
public class Stack {
	
	Node head = null;
	Node curr;
	Node prev;
	
	//create stack
	public Stack() {
		head = new Node(null);
	}
	
	public void push(Node newElement) {
		//check if list is empty
		if(head == null) {
			//new element will be at head and its next is null
			head = newElement;
		}
		//traverse to end and then add
		else {
			curr = head;
			//there is an element next keep traversing until end
			while(curr.next != null) {
				curr = curr.next;
			}
			curr.next = newElement;
		}
	}
	
	public Node pop() {
		//check if list is empty
		if(head != null) {
			curr = head;
			prev = head;
			//traverse to end
			while(curr.next != null) {
				prev = curr;
				curr = curr.next;
			}
			//once at the end remove
			prev.next = null;
			return curr;
		}
		return null;
	}
	
	public boolean delete(Node delNode) {
		//traverse through stack to find element
		curr = head;
		prev = head;
		while(curr.next != null) {
			//check if it must be deleted
			if(curr.data == delNode.data) {
				//remove
				prev.next = curr.next;
			}
			else {
				//continue to iterate
				prev = curr;
				curr = curr.next;
			}
		}
		//keep track of next one
		//have previous point to next and skip deleted one
		return false;
	}
	
	public boolean insertAfter(Node addNode, Object data) {
		//traverse
		curr = head;
		prev = head;
		
		while(curr.next != null) {
			if(curr.data == addNode.data) {
				//if found then add node after
				Node newNode = new Node(data);
				curr.next = newNode;
			}
			else {
				prev = curr;
				curr = curr.next;
			}
		}
		
		return false;
	}
	
	public void removeHead() {
		//make head null and everything after will be affected
		head = head.next;
	}
	
	public Node findMth(int m) {
		//create 2 iters
		curr = head;
		//Node miter = curr - m;
		if(head == null) {
			return null;
		}
		
		for(int i = 0; i < m; i++) {
			if(curr.next != null) {
				curr = curr.next;
			}
			
			else {
				return null;
			}
		}
		
		Node mbehind = head;
		while(curr.next != null) {
			curr = curr.next;
			mbehind = mbehind.next;
		}
		
		return mbehind;
	}
	
	
	private static class Node {
		private Object data;
		private Node next;
		
		public Node(Object data) {
			this.data = data;
			next = null;
		}
	}
	
	
}
