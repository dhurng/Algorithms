/**
 * 
 * @author davidhurng
 *	
 *	Flatten linked list of linked list into 1 
 */
public class ListFlattening {
	//doubly linked list of double linked list
	Node head = null;
	Node tail;
	Node curr;
	
	//start at beginning of first level
	public void flatten(Node head, Node tail) {
		curr = head;
		
		while(curr != null) {
			if(curr.child != null) {
				//append child to the tail
				tail.next = curr.child;
				curr.child.prev = tail;
				//search for new tail
				for(curr = curr.child; curr.next != null; curr = curr.next);
				
			}
			curr = curr.next;
		}
	}
	
	public void unflatten() {
		//work backwards 
		
		//worry about child
		curr = head;
		while(curr.next != null) {
			if(curr.child != null) {
				//separate child from prev node
				//cont path with child
				
			}
			curr = curr.next;
		}
	
	}
	
	private boolean checkCycle() {
		
		Node slow = head;
		Node fast = slow.next;
		boolean flag = true;
		
		while(flag) {
			if(fast == null) {
				return false;
			}
			else if(fast == slow || fast.next == slow) {
				return true;
			}
			else {
				slow = slow.next;
				//1 node faster
				fast = fast.next.next;
			}
		}
		
		//start slow pointer at head;
		
		//loop
		
		
		return false;
	}
	
	private static class Node {
		Node next;
		Node prev;
		Node child;
		int data;
	}
}
