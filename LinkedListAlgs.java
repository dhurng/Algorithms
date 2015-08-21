import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class LinkedListAlgs {
    class ListNode {
    	int val;
    	ListNode next;
    	ListNode(int x) { 
    		val = x; 
    	}
    }
    
    class RandomListNode {
         int label;
         RandomListNode next, random;
         RandomListNode(int x) { this.label = x; }
     };
    
    public ListNode merged(ListNode list1, ListNode list2) {
    	//tail to point at new list and act as stack
    	ListNode tail = new ListNode(0);
    	
    	ListNode head = tail;
    	
    	while(list1 != null && list2 != null) {
    		
	    	if(list1.val <= list2.val) {
	    		head.next = list1;
		    	list1 = list1.next;

	    	}
	    	else {
	    		head.next = list2;
	    		list2 = list2.next;
	    	}
	    	//cont new list
	    	head = head.next;
    	}
    	
    	//when list ends earlier
    	if(list2 == null) {
    		head.next = list1;
    	}
    	
    	if(list1 == null) {
    		head.next = list2;
    	}
    	
    	return tail.next;
    }
    
    //store and returned in reverse order
    public ListNode addNums(ListNode l1, ListNode l2) {
    	ListNode tail = new ListNode(0);
    	ListNode curr = tail;
    	int x = 0;
    	int y = 0; 
    	int carry = 0;
    	
    	//if 1 of them is null deal with just 1 list
    	while(l1 != null || l2 != null) {
    		if(l1 != null) {
    			x = l1.val;
    		}
    		else {
    			x = 0;
    		}
    		if(l2 != null) {
    			y = l2.val;
    		}
    		else {
    			y = 0;
    		}
    		
    		//add the least significant of all the sums
    		int newVal = (x + y + carry) % 10;
    		
    		//create new node and push it into stack
    		ListNode newNode = new ListNode(newVal);
    		curr.next = newNode;
    		
    		//next iteration will add the carry of most sig
    		carry = (x + y + carry) / 10;
    		
    		//move forward
    		curr = curr.next;	
    		
    		if(l1 != null) {
    			l1 = l1.next;
    		}
    		
    		if(l2 != null) {
    			l2 = l2.next;
    		}
    		
    	}
    	
		//if there is a carry push it in 
		if(carry > 0) {
			ListNode lastNode = new ListNode(carry);
			curr.next = lastNode;
		}
    	
    	return tail.next;
    }
    
    //must use only constant space
    public ListNode swapNodes(ListNode nums) {
    	
    	if(nums == null || nums.next == null) {
    		return nums;
    	}
    	
    	ListNode tail = new ListNode(0);
    	tail.next = nums;
    	ListNode curr = nums;
    	//keep track when next pair
    	ListNode prev = tail;
    	
    	//at least 2 to swap
    	while(curr != null && curr.next != null) {
        	ListNode n = curr.next;
        	ListNode nn = curr.next.next;

    		//2 point to 1
    		n.next = curr;
    		
    		//point 1 to 3
    		curr.next = nn;
    		
    		//head point to 2
    		prev.next = n;
    		
    		//tail point to curr
    		prev = curr;
    		
    		//curr now to 3
    		curr = nn;
    	}
    	
    	return tail.next;
    }
    
    public ListNode mergeK(List<ListNode> k) {
    	int length = k.size() - 1;
    	
    	if(k.size() == 0) {
    		return null;
    	}
    	
    	//pass in list, left, right
    	return mergeKHelper(k, 0, length);
    }
    	
    	//merge sort technique
    	//recursive
    	//merge 1 and 2 to be 5
    	//and 3 and 4 to be 5
    	//merge 5 and 6
    	//divide list until reaches 1
    	//merge bottom up
    	
    	//priority q technique
//    	PriorityQueue<ListNode> q = new PriorityQueue<>();
    	//add all heads in the queue
    	//poll smallest one
    	//if next node of smallest is not null
    	//add to q
    	//continue
    
    public ListNode mergeKHelper(List<ListNode> list, int beg, int end) {
    	//merge the first half and second half
    	int mid;
    	ListNode first;
    	ListNode second;
    	
    	while(beg < end) {
    		mid = (beg + end)/2;
    		
    		//first halfj
    		first = mergeKHelper(list, beg, mid);
    		//second half
    		second = mergeKHelper(list, mid + 1, end);
    		//merge both halves
    		return merged(first, second);
    	}
    	
    	return null;
    }
    
    public RandomListNode copyList(RandomListNode head) {

    	RandomListNode curr = head;
    	RandomListNode oldNext;
    	RandomListNode curr2 = head;
    	RandomListNode curr3 = head;
    	RandomListNode cloneP;
    	RandomListNode copyListHead;
    	
    	//go thru each node and clone and point to close
    	while(curr != null) {
    		//create clone with same label
    		RandomListNode clone = new RandomListNode(curr.label);
    		
    		//save next, 1
    		oldNext = curr.next;
    		
    		//point curr to the clone 0 -> 0'
    		curr.next = clone;
    		
    		//clone next to old, 0' -> 1
    		clone.next = oldNext;
    		
    		
    		curr = curr.next.next;
    	}
    	
    	//clone's random = prev.random

    	while(curr2 != null) {
    		//get the clone and set random, 0'.random -> 1
    		if(curr2.random != null) {

    			curr2.next.random = curr2.random.next;
    		} else {
    			curr2.next.random = null;
    		}
    		//go to the next next node
    		curr2 = curr2.next.next;
    	}
    	
    	//copy -> cloneP
    	if(curr3 != null) {
    		copyListHead = curr3.next;
    	} else {
    		copyListHead = null;
    	}
    	
    	//break the connections 
    	while(curr3 != null) {
    		//0'
    		cloneP = curr3.next;
    		
    		//0 -> 1
    		curr3.next = cloneP.next;
    		
    		//iter curr to 1
    		curr3 = curr3.next;
    		
    		
    		if(curr3 != null) {
    			cloneP.next = curr3.next;
    		} else {
    			cloneP.next = null;
    		}
    		
    		//0' -> 1'
//    		cloneP.next = curr3.next;
    	}
    	
    	
    	return copyListHead;
    }
}
