import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Node;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class MiscProblems {

	/*
	 * You have got a range of numbers between 1 to N, 
	 * where one of the number is repeated. 
	 * You need to write a program to find out the duplicate number. 
	 */
	public int duplicate(int[] nums) {
		int res = 0;
		
		for(int i = 0; i < nums.length; i++) {
			res = res ^ nums[i];
		}
		return res;
	}
	
	public void rotate(int[] nums, int k) {
		k = k % nums.length;
		
		if(nums.length == 0 || k < 0) {
			throw new IllegalArgumentException("Illegal Argument!");
		}
		
		//split into 2 
		int split = nums.length - k;
		
		//reverse 1st
		reverse(nums, 0, split - 1);
		//reverse 2nd
		reverse(nums, split, nums.length - 1);
		//reverse whole
		reverse(nums, 0, nums.length - 1);
		
		for(int i = 0; i < nums.length; i++) {
			System.out.print(nums[i]);
		}
	}
	
	public void reverse(int[] nums, int beg, int end) {
		while(beg < end) {
			int temp = nums[beg];
			nums[beg] = nums[end];
			nums[end] = temp;
			
			beg++;
			end--;
		}
	}
	
	interface Operator {
		int eval(int num1, int num2);
	}
	
	public int reversePolish(String[] input) {
		Stack<Integer> stack = new Stack<>();
		
		Map<String, Operator> ops = new HashMap<>();
		ops.put("+", new Operator() { 
				public int eval(int num1, int num2) {
					return num1 + num2; 
				}
			});

		ops.put("-", new Operator() {
			public int eval(int num1, int num2) {
				return num1 - num2;
			}
		});

		ops.put("/", new Operator() {
			public int eval(int num1, int num2) {
				return num1 / num2;
			}
		});
		
		ops.put("*", new Operator() {
			public int eval(int num1, int num2) {
				return num1 * num2;
			}
		});

		
		int num1;
		int num2;
		//go thru the list
		for(int i = 0; i < input.length; i++) {
			//if is operator
			if(ops.containsKey(input[i])) {
				//then pop last 2
				num1 = stack.pop();
				num2 = stack.pop();
				
				num1 = ops.get(input[i]).eval(num2, num1);
				
				stack.push(num1);
			}
			else {
				//not operator push in the int
				stack.push(Integer.parseInt(input[i]));
			}
		}
		
		return stack.pop();
	}
	
	//isomorphic
	public boolean isomorphic(String a, String b) {
		Map<Character, Character> map = new HashMap<>();
		
		if(a.length() != b.length()) {
			return false;
		}
		
		//go thru 1 string
		for(int i = 0; i < a.length(); i++) {
			//if the map contains that letter already g, o
			if(map.containsKey(a.charAt(i))) {
				//get o
				if(map.get(a.charAt(i)) != b.charAt(i)) {
					return false;
				}
			}
			//else add it in
			else {
				//add e, f : g, o
				map.put(a.charAt(i), b.charAt(i));
			}
		}
		
		return true;
	}
	
	//find the kth element in unsorted array
	public int findKth(int[] nums, int k) {
		Arrays.sort(nums);
		return nums.length - k;
	}
	
	//find out middle index where sum of both ends are equal
	public int findMiddleIndex(int[] nums) throws Exception {
		int beg = 0;
		int end = nums.length - 1;
		
		int sumLeft = 0;
		int sumRight = 0;
		
		
		while(true) {
			if(sumLeft > sumRight) {
				sumRight += nums[end--];
			}
			
			else {
				sumLeft += nums[beg++];
			}
			
			if(beg > end) {
				if(sumLeft == sumRight) {
					break;
				}
			} else {
				throw new Exception ("Please pass prper array to match the requirement");
				}
			}
			return end;
		}
	
	//reverse string using recursion
	public String reverseStringRec(String input) {
		String reverse = "";
		
		//base case
		if(input.length() == 1) {
			return input;
		}
		
		//recursion part
		else {
			reverse += input.charAt(input.length() - 1) + reverseStringRec(input.substring(0, input.length() - 1));
			return reverse;
		}
	}
	
	//reverse a number
	public int reverseNumber(int num) {
		int reverse = 0;
		
		while(num != 0) {
			int left = num % 10;
			reverse = reverse * 10 + left;
			num /= 10;
		}
		
		return reverse;
	}
	
	//convert decimal to binary format
	public void convertToBinary(int nums) {
		int binary[] = new int[25];
		int index = 0;
		
		while(nums > 0) {
			binary[index++]  = nums % 2;
			nums = nums/2;
		}
		for(int i = index - 1; i >= 0; i--) {
			System.out.println(binary[i]);
		}
	}
	
	//finding the perfect number ex. 6 = 1+2+3 or (1+2+3+6)/2 = 6
	public boolean isPerfect(int nums) {
		int sum = 0;
		//find all divisors
		for(int i = 1; i <= nums/2; i++) {
			if(nums % i == 0) {
				sum += i;
			}
		}
		
		if(nums == sum) {
			return true;
		}
		return false;
	}
	
	//find duplicate characters
	public void duplicateChar(String input) {
		Map<Character, Integer> map = new HashMap<>();
		
		for(int i = 0; i < input.length(); i++) {
			//if in map then add
			if(map.containsKey(input.charAt(i))) {
				map.put(input.charAt(i), map.get(input.charAt(i))+1);
			} else {
				map.put(input.charAt(i), 1);
			}
		}
		
		Set<Character> keys = map.keySet();
		for(int i = 0; i < keys.size(); i++) {
			if(map.get(input.charAt(i)) > 1) {
				System.out.println(input.charAt(i));
			}
		}
	}
	
	//find top 2 max numbers
	public void twoMax(int[] nums) {
		int l1 = 0 , l2 = 0;
		
		for(int i = 0; i < nums.length; i++) {
			if(nums[i] > l1) {
				l2 = l1;
				l1 = nums[i];
			} else if(nums[i] > l2) {
				l2 = nums[i];
			}
		}
		System.out.println(l1 + " " + l2);
	}
	
	//sort a mpa by value
	public void sortMap() {
		Map<String, Integer> map = new HashMap<>();
		map.put("david", 1);
		map.put("michelle", 2);
		map.put("chris", 3);
		
		Set<Entry<String, Integer>> set = map.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
				{
					public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
						return (o2.getValue().compareTo(o1.getValue()));
					}
				}
			);
	}
	
	//find comman elements between 2 arrays
	public void commonElements(int[] a, int[] b) {
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < b.length; j++) {
				if(a[i] == b[j]) {
					System.out.println(a[i]);
				}
			}
		}
	}
	
	//swap 2 numbers without using temp
	public void swapWithout(int a, int b) {
		a = a + b;
		b = a - b;
		a = a - b;
		
		System.out.println(a + " " + b);
	}
	
	//fibonacci
	public int fib(int nums) {
		if(nums == 0) {
			return 0;
		} else if(nums == 1) {
			return 1;
		} else {
			return fib(nums - 1) + fib(nums - 2);
		}
	}
	
	//iteravely add digits of number
	public int addDigits(int nums) {
		int sum = 0;
		
		while(nums != 0) {
			int left = nums % 10;
			sum += left;
			nums /= 10;
		}
		
		System.out.println(sum);
		return sum;
	}
	
	//recursively add digits of number
	public int addDigitsRec(int nums) {
		int sum = 0;
		
		if(nums == 0) {
			return 0;
		}
		
		else {
			sum += nums % 10;
			addDigitsRec(nums/10);
		}
		
		return sum;
	}
	
	//check if number is prime
	public boolean isPrime(int nums) {
		//any divisor to half
		if(nums == 0) {
			return false;
		}
		
		for(int i = 2; i <= nums/2; i++) {
			if(nums % i == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	//determine if Armstrong: sum of digits to power of # of digits
	public boolean isArmstrong(int nums) {
		int sum = 0;
		int count = String.valueOf(nums).length();
		int temp = nums;
		
		while(temp != 0) {
//			count++;
			sum += temp % 10;
			temp /= 10;
		}
		
//		System.out.println(sum + " " + count);
		if(nums == Math.pow(sum, count)) {
			return true;
		}
		
		return false;
	}
	
	//convert binary to decimal
	public int convertDec(int binary) {
		int decimal = 0;
		int power = 0;
		
		while(true) {
			if(binary == 0) {
				break;
			}
			else {
				int temp = binary % 10;
				decimal += temp * Math.pow(2, power);
				binary /= 10;
				power++;
			}
		}
		
		return decimal;
	}
	
	//check if number is binary
	public boolean isBinary(int nums) {
		boolean flag = true;
		
		while(true) {
			if(nums == 0) {
				break;
			} else {
				int left = nums % 10;
				if(left > 1) {
					flag = false;
					break;
				}
				nums /= 10;
			}
		}
		return flag;
	}
	
	//write a bubble sort
	public void bubbleSort(int[] nums) {
		int k = 0;
		for(int i = nums.length; i >= 0; i--) {
			for(int j = 0; j < nums.length - 1; j++) {
				k = i + 1;
				//compare k and i 
				if(nums[i] > nums[k]) {
					swap(nums, i, k);
				}
			}
		}
	}
	
	public void swap(int[] nums, int a, int b) {
		int temp = nums[a];
		nums[a] = nums[b];
		nums[b] = temp;
	}
	
	//insertion sort
	public void insertionSort(int[] nums) {
		int length = nums.length;
		for(int i = 1; i < length; i++) {
			int key = nums[i];
			int j = i - 1;
			while((j > -1) && (nums[j] > key)) {
				nums[j + 1] = nums[j];
				j--;
			}
			nums[j + 1] = key;
		}
	}
	
	//wildcard matching
	public boolean isMatch(String a, String b) {
		int i = 0;
		int j = 0;
		int starI = -1;
		int iI = -1;
		
		while(i < a.length()) {
			if(j < b.length() && (a.charAt(i) == b.charAt(j) || b.charAt(j) == '?')) {
				++i;
				++j;
			} else if(j < b.length() && b.charAt(j) == '*') {
				starI = j;
				iI = i;
				j++;
			} else if(starI != -1) {
				j = starI + 1;
				i = iI + 1;
				iI++;
			} else {
				return false;
			}
		}
		
		while(j < b.length() && b.charAt(j) == '*') {
			++j;
		}
		
		return j == b.length();
	}
	
	//define the comparator
	class Interval {
		int start;
		int end;
		
		Interval() {
			start = 0;
			end = 0;
		}
		
		Interval(int a, int b) {
			this.start = a;
			this.end = b;
		}
	}
	
	//interval comparator
	class IntervalComparator implements Comparator<Interval> {
		public int compare(Interval a, Interval b) {
			return a.start - b.start;
		}
	}
	
	//merge all overlapping interval
	public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
		
		if(intervals == null || intervals.size() <= 1) {
			return intervals;
		}
		
//		Collections.sort(intervals, IntervalComparator());
		List<Interval> res = new ArrayList<>();
		
		Interval prev = intervals.get(0);
		
		for(int i = 1; i < intervals.size(); i++) {
			//compare the start if less then merge
			if(prev.start >= intervals.get(i).start) {
				Interval merged = new Interval(prev.start, Math.max(prev.end, intervals.get(i).end));
				prev = merged;
			} else {
				res.add(prev);
				prev = intervals.get(i);
			}
		
		}
		
		return null;
	}
	
	//insert interval
	public ArrayList<Interval> insertInterval(ArrayList<Interval> intervals, Interval newInterval) {
		ArrayList<Interval> res = new ArrayList<>();
		
		for(Interval currInterval: intervals) {
			//merge the start
			if(currInterval.end < newInterval.start) {
				res.add(currInterval);
			} else if(currInterval.start > newInterval.end) {
				res.add(newInterval);
				newInterval = currInterval;
			} else if(currInterval.end >= newInterval.start || currInterval.start <= newInterval.end) {
				newInterval = new Interval(Math.min(currInterval.start, newInterval.start), Math.max(currInterval.end, newInterval.end));
			}
			
		}
		res.add(newInterval);
		return res;
	}
	
	class ListNode {
		int val;
		ListNode next;
		
		ListNode(int x) {
			this.val = x;
			this.next = null;
		}
	}
	
	//linkwdlist add 2 numbers
	public ListNode addTwo(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		int carry = 0;
		
		while(l1 != null || l2 != null) {
			if(l1 != null) {
				carry += l1.val;
				l1 = l1.next;
			}
			
			if(l2 != null)
			{
				carry += l2.val;
				l2 = l2.next;
			}
			
			dummy.next = new ListNode(carry % 10);
			dummy = dummy.next;
			carry /= 10;
		}
		
		//if last node adds to 10 or more then add it in front
		if(carry == 1) {
			dummy.next = new ListNode(1);
		}
		
		return dummy.next;
	}
	
	
	//reoarder a list
	public void reorderList(ListNode head) {
		
		if(head != null && head.next != null) {
			ListNode slow = head;
			ListNode fast = head;
			
			while(fast != null && fast.next != null && fast.next.next != null) {
				slow = slow.next;
				fast = fast.next.next;
			}
			
			ListNode second = slow.next;
			slow.next = null;
			
			second = reverseOrder(second);
			
			ListNode p1 = head;
			ListNode p2 = second;
			
			while(p2 != null) {
				ListNode temp1 = p1.next;
				ListNode temp2 = p2.next;
				
				p1.next = p2;
				p2.next = temp1;
				
				p1 = temp1;
				p2 = temp2;
			}
		}
	}
	
	public ListNode reverseOrder(ListNode head) {
		if(head == null || head.next == null) {
			return head;
		}
		
		ListNode pre = head;
		ListNode curr = head.next;
		
		while(curr != null) {
			ListNode temp = curr.next;
			curr.next = pre;
			pre = curr;
			curr = temp;
		}
		
		head.next = null;
		
		return pre;
	}
	
	//LinkedList cycle
	public boolean hasCycle(ListNode head) {
		
		ListNode slow = head;
		ListNode fast = head;
		
		while(fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			
			if(slow == fast) {
				return true;
			}
		}
		return false;
	}
	
	//merge 2 sorted lists
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		ListNode curr = dummy;
		
		while(l1 != null && l2 != null) {
			if(l1.val < l2.val) {
				curr.next = l1;
				l1 = l1.next;
			} else {
				curr.next = l2;
				l2 = l2.next;
			}
			
			curr = curr.next;
		}
		
		if(l1 == null) {
			curr.next = l2;
		} 
		if(l2 == null) {
			curr.next = l1;
		}
	
		return dummy.next;
	}
	
	//merge k sorted
	public ListNode mergeKLists(ArrayList<ListNode> lists) {
		
		if(lists.size() == 0) {
			return null;
		}
		
		PriorityQueue<ListNode> q = new PriorityQueue<>(lists.size(), new Comparator<ListNode>() {
			public int compare(ListNode a, ListNode b) {
				if(a.val > b.val) {
					return 1;
				} else if(a.val == b.val) {
					return 0;
				} else {
					return -1;
				}
		}
		});
		
		for(ListNode list : lists) {
			if(lists != null) {
				q.add(list);
			}
		}
		
		ListNode head = new ListNode(0);
		ListNode curr = head;
		
		while(q.size() > 0) {
			ListNode temp = q.poll();
			curr.next = temp;
			
			if(temp.next != null) {
				q.add(temp.next);
			}
			
			curr = curr.next;
		}
		
		return head.next;
	}
	
	//remove duplicates from sortedlist
	public ListNode removeDuplicates(ListNode head) {
		if(head == null || head.next == null) {
			return head;
		}
		
		ListNode prev = head;
		ListNode curr = head.next;
		
		while(curr != null) {
			if(curr.val == prev.val) {
				prev.next = curr.next;
				curr = curr.next;
			} else {
				prev = curr;
				curr = curr.next;
			}
		}
		
		return head;
	}
	
	//delete all numbers with duplicate 
	public ListNode deleteDuplicates(ListNode head) {
		
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		
		ListNode curr = dummy;
		while(curr.next != null && curr.next.next != null) {
			if(curr.next.val == curr.next.next.val) {
				int duplicate = curr.next.val;
				while(curr.next != null && curr.next.val == duplicate) {
					curr.next = curr.next.next;
				}
			}else {
					curr = curr.next;
				}
			}
		
		return dummy.next;
	}
	
	//move zeroes to end
	public void moveZeroes(int[] nums) {
		int numZeroes = 0;
		
		for(int i = 0; i < nums.length; i++) {
			//if it is zero then continue
			if(nums[i] == 0) {
				numZeroes++;
			} else {
				nums[i - numZeroes] = nums[i];
				if(numZeroes != 0) {
					nums[i] = 0;
				}
			}	
		}
	}
	
	private final String[] lessThan20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	private final String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
	private final String[] thousands = {"", "Thousand", "Million", "Billion"};
	//convert int to english
	public String convertEng(int num) {
		if(num == 0) {
			return "Zero";
		}
		
		String res = "";
		int i = 0;
		while(num > 0) {
			if(num % 1000 != 0) {
				res = convertHelper(num % 1000) + thousands[i] + " " + res;
			}
			num /= 1000;
			i++;
		}
		
		return res.trim();
	}
	
	public String convertHelper(int num) {
		if(num == 0) {
			return "";
		} else if(num < 20) {
			return lessThan20[num] + " ";
		} else if(num < 100) {
			return tens[num / 10] + " " + convertHelper(num % 10);
		} else {
			return lessThan20[num / 100] + " Hundred " + convertHelper(num % 100);
		}
	}
	
	//stack implementation
//	private static class Node<T> {
//		private final T data;
//		private final Node<T> next;
//		
//		public Node(T data) {
//			this.data = data;
//		}
//		
//		@Override
//		public String toString() {
//			return data.toString();
//		}
//	}
	
//	private Node<T> head = null;
	
	//find mth to the last in linkedlist
	public ListNode findMthToLast(ListNode head, int m) {
		
		if(head == null) {
			return null;
		}
		
		ListNode curr = head;
		
		//iter until mBehind
		for(int i = 0; i < m; i++) {
			if(curr.next != null) {
				curr = curr.next;
			} else {
				return null;
			}
		}
		
		ListNode mBehind = head;
		
		//keep iterating
		while(curr.next != null) {
			curr = curr.next;
			mBehind = mBehind.next;
		}
		
		//at the end return mBehind
		return mBehind;
	}
	
	//determine null or cycle
	public boolean cycle(ListNode head) {
		
		if(head == null || head.next == null) {
			return false;
		}
		
		//have a fast and slow
		ListNode slow = head;
		ListNode fast = head.next;
		
		while(true) {
			if(fast == null || fast.next == null) {
				return false;
			}
			
			else if(fast == slow || fast.next == slow) {
				return true;
			} 
			else {
				slow = slow.next;
				fast = fast.next.next;
			}
		}
	}
	
	public abstract class TreeNode {
		private TreeNode[] children;
		
		public TreeNode(TreeNode[] children) {
			this.children = children;
		}
		
		public int getNumChildren() {
			return children.length;
		}
		
		public TreeNode getChild(int index) {
			return children[index];
		}
	}
	
	public class IntNode extends TreeNode {
		private int val;
		
		public IntNode(TreeNode[] children, int val) {
			super(children);
			this.val = val;
		}
		
		public int getValue() {
			return val;
		}
	}
	
	public class TNode {
		private TNode left;
		private TNode right;
		private int val;
		
		public TNode(TNode left, TNode right, int val) {
			this.left = left;
			this.right = right;
			this.val = val;
		}
		
		public TNode getLeft() {return left;}
		public TNode getRight() {return right;}
		public int getVal() {return val;}
	}
	
	//bst find node
	public TNode findNode(TNode root, int value) {
		while(root != null) {
			if(root.val == value) {break;}
			else if(root.val < value) {root = root.right;}
			else {root = root.left;}
		}
		return root;
	}
	
	public void preOrderIter(TNode root) {
		Stack<TNode> stack = new Stack<>();
		
		stack.push(root);
		while(!stack.isEmpty()) {
			//pop
			TNode curr = stack.pop();
			//print
			System.out.println(curr.val);
			//check right push
			if(curr.right != null) { stack.push(curr.right); }
			//check left push
			if(curr.left != null) { stack.push(curr.left); }
		}
		
	}
	
	public TNode findLowestAncWrapper(TNode root, TNode n1, TNode n2) {
		if(root == null || n1 == null || n2 == null) {
			return null;
		}
		
		//pass in value next time
		return findLowestAnc(root, n1, n2);
	}
	
	//find common lowest common ancestor
	//iteratively
	public TNode findLowestAnc(TNode root, TNode n1, TNode n2) {
		TNode curr = root;
		
		while(curr != null) {
			//check if it is both greater than the nodes
			if(curr.val > n1.val && curr.val > n2.val) {
				curr = curr.getLeft();
			}
			else if(curr.val < n1.val && curr.val < n2.val) {
				curr = curr.getRight();
			}
			else {
				return root;
			}
		}
		//for empty tree
		return null;
	}
	
	public static int traverse(TNode node, int count, TNode[] arr) {
		if(node == null) {
			return count;
		}
		
		if(arr != null) {
			arr[count] = node;
		}
		
		count++;
		count = traverse(node.left, count, arr);
		count = traverse(node.right, count, arr);
		
		return count;
	}
	
	
	
	//array sort a unbalanced tree and then heapify
	public void treeToArray(TNode root) {
		//go thru the tree once to get the size
		int size = traverse(root, 0, null);
		TNode[] array = new TNode[size];
		
		Arrays.sort(array, new Comparator<TNode>() {
			@Override
			public int compare(TNode n1, TNode n2) {
				return (n1.val < n2.val ? -1 : (n1.val == n2.val ? 0 : 1)); 
			}
		});
		
		//reassign
		for(int i = 0; i < size; i++) {
			int left = 2*i + 1;
			int right = left + 1;
			//set left
			
			//set right
		}
		
		//go thru again to store it into fixed list
	}
	
	//right rotation runtime is constant
	public TNode rotateRight(TNode root) {
		TNode newRoot = root.getLeft();
		root.left = newRoot.getRight();
		newRoot.left = root;
		return newRoot;
	}
	
    public void moveZeroes1(int[] nums) {
        //move all zeroes to end
        int numZeroes = 0;
		
		for(int i = 0; i < nums.length; i++) {
			//if it is zero then continue
			if(nums[i] == 0) {
				numZeroes++;
			} else {
				nums[i - numZeroes] = nums[i];
				if(numZeroes != 0) {
					nums[i] = 0;
				}
			}	
		}
    }
    
    //6 degrees of kevin bacon
    public class ActorGraphNode {
    	private String name;
    	private Set<ActorGraphNode> linkedActors;
    	private int baconNumber;
    	
    	public ActorGraphNode(String name) {
    		this.name = name;
    		baconNumber = -1;
    		linkedActors = new HashSet<ActorGraphNode>();
    	}
    	
    	public void linkCostar(ActorGraphNode costar) {
    		linkedActors.add(costar);
    		costar.linkedActors.add(this);
    	}
    }
    
//    public int degreesOfKB(ActorGraphNode actor, ActorGraphNode kb) {
//    	Queue<ActorGraphNode> q = new Queue<>();
//    	
//    	while(!q.isEmpty()) {
//    		ActorGraphNode first = q.poll();
//    		
//    		if(first.equals(kb)) {
//    			return kb.baconNumber;
//    		}
//    		
//    		for(int i = 0; i < first.linkedActors.size(); i++) {
//    			//if node is unvisited then bc = -1
//    			if(first.linkedActors.)
//    		}
//     	}
//    	
//    }
    
    //find first norepeated
    public Character firstNonRepeated(String str) {
    	 Map<Character, Integer> map = new HashMap<>();
    	 int count = 0;
    	 
    	 for(int i = 0; i < str.length(); i++) {
    		 //already in map then
    		 if(map.containsKey(i)) {
    			 map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
    		 }
    		 //add into map
    		 else {
    			 map.put(str.charAt(i), 1);
    		 }
    	 }
    	
    	 for(int j = 0; j < str.length(); j++) {
    		 if(map.get(str.charAt(j)) == 1) {
    			 return str.charAt(j);
    		 }
    	 }
    	 return null;
    }
	
    //remove specified characters
    public String removeChars(String str, String remove) {
    	StringBuilder sb = new StringBuilder();
    	 
    	Map<Character, Integer> map = new HashMap<>();
    	
    	//load remove characters into map
    	for(int i = 0; i < remove.length(); i++) {
    		map.put(remove.charAt(i), 1);
    	}
    	
    	//go thru string
    	for(int j = 0; j < str.length(); j++) {
    		if(!map.containsKey(str.charAt(j))) {
    			sb.append(str.charAt(j));
    		}
    	}
    	return sb.toString();
    }
    
    //reverse words
    public void reverseWords(String str) {
    	
    	if(str == null || str.length() == 0) {
    		return;
    	}
    	
    	//reverse the entire string
    	char[] array = str.toCharArray();
    	
    	array = reverseWordsHelper(array, 0, array.length - 1);
    	
//    	for(int k = 0; k < array.length; k++) {
//    		System.out.print(array[k]);
//    	}
    	
    	int beg = 0;
    	int i = 0;
    	//go thru and reverse the words
    	while(i < array.length) {
    		//doesnt reverse because not white space
    		if(!Character.isWhitespace(array[i])) {
    			i++;
    		}
    		else {
       			reverseWordsHelper(array, beg, i - 1);
    			beg = i + 1;
    			i++;
    		}
    	}
    	
    	if(i == array.length) {
    		reverseWordsHelper(array, beg, i - 1);
    	}
    	
    	for(int k = 0; k < array.length; k++) {
    		System.out.print(array[k]);
    	}
    }
    
    public char[] reverseWordsHelper(char[] str, int beg, int end) {
    	char temp;
    	while(beg < end) {
    		temp = str[beg];
    		str[beg] = str[end];
    		str[end] = temp;
    		
    		beg++;
    		end--;
    	}
    	
    	return str;
    }
    
    public String reverseWords2(String str) {
    	
    	String[] array = str.split(" ");
    	StringBuilder sb = new StringBuilder();
    	
    	for(int i = array.length - 1; i >= 0; i--) {
    		sb.append(array[i] + " ");
    	}
    	
    	return sb.toString().trim();
    }
    
    public int stringToInt(String str) {
    	boolean negative = false;
    	int i = 0;
    	int num = 0;
    	
    	if(str.charAt(i) == '-') {
    		negative = true;
    		i++;
    	}
    	
    	while(i < str.length()) {
    		num = num * 10;
    		num = num + (str.charAt(i) - '0');
    		i++;
    	}
    	
    	if(negative) {
    		num *= -1;
    	}
    	
    	return num;
    }
    
    //int to string
    public String intToString(int num) {
    	Stack<Character> stack = new Stack<>();
    	StringBuilder sb = new StringBuilder();
    	boolean negative = false;
    	
    	if(num < 0) {
    		negative = true;
    		num *= -1;
    		sb.append('-');
    	}
    	
    	while(num != 0) {
    		char left = (char)((num % 10) + '0');  
    		stack.push(left);
    		num = num / 10;
    	}
    	
    	if(num == 0){
    		sb.append(0);
    	}
    	
    	while(!stack.isEmpty()) {
    		sb.append(stack.pop());
    	}
    	
    	    	
    	return sb.toString();
    }
    
    //factorials
    public int[] allFactorials(int n) {
    	int[] res = new int[n == 0 ? 1 : n];
    	doAllFactorials(n, res, 0);
    
    	return res;
    }
    
    public int doAllFactorials(int n, int[] res, int level) {
    	if(n > 1) {
    		res[level] = n * doAllFactorials(n - 1, res, level + 1);
    		return res[level];
    	}
    	
    	else {
    		res[level] = 1;
    		return 1;
    	}
    }
    
    //permutations of string
    public void permutations(String prefix, String str) {
    	int length = str.length();
    	
    	if(length == 0) {
    		System.out.println(prefix);
    	}
    	
    	else {
    		for(int i = 0; i < str.length(); i++) {
    			permutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1));
    		}
    	}
    }
    
    //combinations of string
    public void combination(String str, StringBuilder sb, int index) {
    	for(int i = index; i < str.length(); i++) {
    		sb.append(str.charAt(i));
    		System.out.println(sb.toString());
    		combination(str, sb, i + 1);
    		sb.deleteCharAt(sb.length() - 1);
    	}
    }
    
    public void quicksort(int[] array, int low, int high) {
    	if(array == null || array.length == 0) {
    		return;
    	}
    	
    	int mid = low + (high - low)/2;
    	int pivot = array[mid];
    	int i = low;
    	int j = high;
    	
    	while(low < high) {
    		while(array[low] < pivot) {
    			i++;
    		}
    		
    		while(array[high] > pivot) {
    			j--;
    		}
    		
    		if(i <= j) {
    			int temp = array[i];
    			array[i] = array[j];
    			array[j] = temp;
    			i++;
    			j--;
    		}
    	}
    
    if(low < j) {
    	quicksort(array, low, j);
    }
    
    if(high > i) {
    	quicksort(array, i, high);
    }
 }

    private int[] array;
    private int[] temparray;
    
    //mergesort
    public void mergeSort(int low, int high) {
    	if(low < high) {
    		int middle = low + (high - low)/2;
    		mergeSort(low, middle);
    		mergeSort(middle + 1, high);
    		merge(low, middle, high);
    	}
    }
    
    public void merge(int low, int mid, int high) {
    	for(int i = low; i < high; i++) {
    		temparray[i] = array[i];
    	}
    	
    	int i = low;
    	int j = mid;
    	int k = high;
    	
    	while(i <= mid && j <= high) {
    		if(temparray[i] <= temparray[j]) {
    			array[k] = temparray[i];
    			i++;
    		}
    		
    		else {
    			array[k] = temparray[j];
    			j++;
    		}
    		k++;
    	}
    	
    	while(i <= mid) {
    		array[k] = temparray[i];
    		k++;
    		i++;
    	}
    	
    }
      
    //given
    public Character getCharKey(int telephoneKey, int place) {
    	return null;
    }
    
    //selection sort
    public void selectionSort(int[] input) {
    	selectionSortRec(input, 0);
    }
    
    public void selectionSortRec(int[] input, int start) {
    	if(start < input.length - 1) {
    		swapSelect(input, start, findMinPos(input, start));
    		selectionSortRec(input, start + 1);
    	}
    }
    
    public int findMinPos(int[] input, int start) {
    	int minPos = start;
    	
    	//++i
    	for(int i = start + 1; i < input.length; i++) {
    		if(input[i] < input[minPos]) {
    			minPos = i;
    		}
    	}
    	return minPos;
    }
    
    public void swapSelect(int[] input, int start, int minPos) {
    	if(start != minPos) {
    		int temp = input[start];
    		input[start] = input[minPos];
    		input[minPos] = temp;
    	}
    }
    
   //needle in the haystack
    public int strStr(String needle, String haystack) {
    	
    	if(needle == null || haystack == null) {
    		return 0;
    	}
    	
    	if(needle.length() == 0) {
    		return 0;
    	}
    	
    	
    	//go thru haystack
    	for(int i = 0; i < haystack.length(); i++){
    		//know its not in haystack already
    		if(i + needle.length() > haystack.length()) {
    			return -1;
    		}
    		
    		int k = i;
    		for(int j = 0; j < needle.length(); j++) {
    			if(needle.charAt(j) == haystack.charAt(k)) {
    				//j reaches the end
    				if(j == needle.length() - 1) {
    					return i;
    				}
    				k++;
    			} else {
    				break;
    			}
    		}
    	}
    	return -1;
    }
    
    
    //insertion sort best case O(n) when sorted avg and worst is n^2
    public void insertionSort2(int[] input) {
    	for(int i = 1; i < input.length; ++i) {
    		int val = input[i];
    		
    		for(int j = 0; j < i; ++j) {
    			if(input[j] > val) {
    				System.arraycopy(input, j, input, j+1, i - j);
    				input[j] = val;
    				break;
    			}
    		}
    	}
    }
    
    //check if word is pattern of other
    public boolean wordPattern(String pattern, String str) {
        //map the pattern letter to the str word 
        Map<Character, String> map = new HashMap<>();
        
        String[] array = str.split(" ");
            
        if(pattern.length() != array.length) {
        	return false;
        }
        
        for(int i = 0; i < pattern.length(); i++){
            if(!map.containsKey(pattern.charAt(i))) {
                if(map.containsValue(array[i])){
                    return false;
                }
                map.put(pattern.charAt(i), array[i]);
            }else{
                if(!map.get(pattern.charAt(i)).equals(array[i])){
                    return false;
                }
            }
        }
        return true;
    }
    
//    //find first bad version using boolean isBadVersion
//    public int firstBadVersion(int n) {
//    	int beg = 1;
//    	int end = n;
//    	
//    	while(beg < end) {
//    		int mid = beg + (end - beg)/2;
//    		
//    		//is bad then go down
//    		if(isBadVersion(mid)) {
//    			end = mid;
//    		} else {
//    			beg = mid + 1;
//    		}
//    	}
//    	return beg;
//    }
    
    //find missing number
    public int missingNumber(int[] nums) {
    	int length = nums.length;
    	int original = 0;
    	int current = 0;
    	
    	for(int i = 0; i < nums.length; i++) {
    		original += i;
    		current += nums[i];
    	}
    	
    	return original + length - current;
    }
    
    //add 2 digits represented in linkedlist
    //if regular order reverse the list
    public ListNode addNumbers(ListNode l1, ListNode l2) {
    	int carry = 0;
    	
    	ListNode dummy = new ListNode(0);
    	ListNode curr = dummy;
    	
    	while(l1 != null || l2 != null) {
    		if(l1 != null) {
    			carry += l1.val;
    			l1 = l1.next;
    		}
    		
    		if(l2 != null) {
    			carry += l2.val;
    			l2 = l2.next;
    		}
    		
    		//get right
    		curr.next = new ListNode(carry % 10);
    		curr = curr.next;
    		carry /= 10;
    	}

    	//handle carr
    	if(carry == 1) {
    		curr.next = new ListNode(1);
    	}
    	
    	return dummy.next;
    }
    
    //reverse linked list iteratively
    public ListNode reverseLinkedList(ListNode head) {
    	
    	if(head == null && head.next == null) {
    		return head;
    	}
    	
    	ListNode prev = head;
    	ListNode curr = head.next;
    	
    	//turn this around
    	head.next = null;
    	
    	while(prev != null && curr != null) {
    		ListNode temp = curr.next;
    		curr.next = prev;
    		prev = curr;
    		
    		//not at the end
    		if(temp != null) {
    			curr = temp;
    		}
    		else {
    			break;
    		}
    	}
    	return curr;
    }
    
    //reverse is recursively
    public ListNode reverseLinkedListRec(ListNode head) {
    	if(head == null || head.next == null) {
    		return head;
    	}
    	
    	ListNode second = head.next;
    	//set first to be null
    	head.next = null;
    	
    	ListNode rest = reverseLinkedListRec(second);
    	second.next = head;
    	
    	return rest;
    }
    
    //2 string palindrome
    public boolean twoStringPalindrome(String str1, String str2) {
    	//concatinate and then determine if palindrome
    	StringBuilder sb = new StringBuilder();
    	sb.append(str1);
    	sb.append(str2);
    	
//    	System.out.println(sb.toString());
    	
    	int i = 0;
    	int j = sb.length() - 1;
    	
    	while(i < j) {
    		if(sb.charAt(i) == sb.charAt(j)) {
    			i++;
    			j--;
    		} else {
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    //calculate square root of number
    public double squareRoot(int n) {
    	double temp;
    	
    	//firsgt square root number
    	double squareRoot = n/2;
    	
    	do {
    		temp = squareRoot;
    		squareRoot = (temp + (n/temp)) / 2; 
    	}
    	
    	while((temp - squareRoot) != 0);
    	
    	return squareRoot;
    }
    
    //permutation another way
    public Set<String> permutation2(String str) {
    	Set<String> res = new HashSet<>();
    	if(str == null) {
    		return res;
    	} else if(str.length() == 0) {
    		res.add("");
    		return res;
    	}
    	
    	char first = str.charAt(0);
    	String rest = str.substring(1);
    	
    	Set<String> words = permutation2(rest);
    	for(String newString : words) {
    		for(int i = 0; i < str.length(); i++) {
    			res.add(combine(newString, first, i));
    			
    		}
    	}
    	return res;
    }
    
    public String combine(String str, char character, int i) {
    	String first = str.substring(0, i);
    	String last = str.substring(i);
    	return first + character + last;
    }
   
    
    //largest number with same digits before n
    public int largestBeforeN(int n) {
    	String num = Integer.toString(n);
    	
    	//brute force is to find all the permutations and then search to find the smallest one
    	System.out.println(permutation2(num));
    	
    	Set<String> set = new HashSet<>();
    	set = permutation2(num);
    	for(int i = 0; i < set.size(); i++) {
    		
    	}
    	//for find the first left digit is less than the right 
    	//find the smallest digit larget than 4 to the right
    	
    	//place it to the lft of the 4
    	
    	//sort the digits to the right of 5
    	
    	
    	return -1;
    }
    
    //given 2 strings check if anagrams
    //anagrams if they contain same number of characters and same size
    //n log n runtime n length of longer string
    public boolean isAnagram(String str1, String str2) {
    	
    	//best way to sort the strings and compare if equal
    	if(str1.length() != str2.length()) {
    		return false;
    	}
    	
    	//consider converting all the lower case!
    	//str1.toLowerCase()
    	
    	//sort them to see if they are the same word!
    	char[] charArray = str1.toCharArray();
    	char[] charArray2 = str2.toCharArray();
    	    	
    	Arrays.sort(charArray);
    	Arrays.sort(charArray2);
    	
    	String s1 = new String(charArray);
    	String s2 = new String(charArray2);
    	
    	return s1.equals(s2);
    }
    
    //linear time and using constant space
    /*
     * Create hashmap to keep track of how many times each char is being used
     *  check lengths 
     *  
     *  add all characters from s1 to map
     *  increment value to keep track of number of occurances
     *  
     *  iterate thru all char in s2 and decrement count of each
     *  
     *  if they are anagrams each character should be at 0 at point
     *  if hit char that is not then false
     *  
     *  overall O(n) and space is constant because # of elements in map is constant
     *  because of alphabet
     *  
     *  all in all use the same array to save space
     */
    
    
    
    //given list of strings reutnr a list of strings repping grouping by anagrams
    //string without anagrams still put into the array
    public List<String> isAnagrams(String[] strs) {
    	ArrayList<String> result = new ArrayList<String>();
    	if(strs == null || strs.length == 0) {
    		return result;
    	}
    	
    	HashMap<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
        for(int i=0; i<strs.length; i++){
            char[] arr = strs[i].toCharArray();
            Arrays.sort(arr);
            String t = String.valueOf(arr);
            if(map.get(t) == null){
                ArrayList<Integer> l = new ArrayList<Integer>();
                l.add(i);
                map.put(t, l);
            }else{
                map.get(t).add(i);
            }
        }
     
        for(ArrayList<Integer> l: map.values()){
            if(l.size() > 1){
                for(Integer i: l){
                    result.add(strs[i]);
                }
            }
        }
     
        return result;
    }
    
    //check if any 3 numbers sum to given number
    //n2 worst case, assumes array is sorted
    public boolean sum3Nums(int[] nums, int x) {
    	for(int i = 0; i < nums.length; i++) {
    		boolean res = sum2Nums(nums, x - nums[i], i);
    		if(res) {
    			return res;
    		}
    	}
    	return false;
    }
    
    public boolean sum2Nums(int[] nums, int x, int low) {
    	int high = nums.length - 1;
    	
    	while(low < high) {
    		if(nums[low] + nums[high] == x) {
    			return true;
    		}
    		if(array[low] + array[high] > x) {
    			high--;
    		} else {
    			low++;
    		}
    	}
    	return false;
    }
    
    //print tree layer by layer
//    public void printTree(TreeNode root) {
//    	Queue<TreeNode> level = new LinkedList<>();
//    	level.add(root);
//    	
//    	while(!level.isEmpty()) {
//    		TreeNode node = level.poll();
//    		System.out.println(node.data + " ");
//    		if(node.leftChild != null) {
//    			level.add(node.leftChild);
//    		}
//    		if(node.rightChild != null) {
//    			level.add(node.rightChild);
//    		}
//    	}
//    }
    
    //find length of longest sequence of increasing numbers in unsorted array
    
    //test 1,2,4,5,3 time is m 
    
    //if 1,3,5,7,9 then run n^2 cuz m == n and no element is removed
    public int longestSequence(int[] nums) {
    	//itself
    	int max = 1;
    	
    	if(nums.length == 0) {
    		return 0;
    	}
    	
    	Set<Integer> set = new HashSet<>();
    	
    	for(int i : nums)
    		set.add(i);
    	
    	for(int i : nums) {
    		int left = i - 1;
    		int right = i + 1;
    		int count = 1;
    		
    		//after elemtn is checked remove from set or runtime is O(mn) m is avg length
    		while(set.contains(left)) {
    			count++;
    			set.remove(left);
    			left--;
    		}
    		
    		while(set.contains(right)) {
    			count++;
    			set.remove(right);
    			right++;
    		}
    		
    		max = Math.max(count, max);
    	}
    	
    	return max;
    }
    
    //given 2 strings add them o(n)
    public String add2Strings(String str1, String str2) {
    	
    	if(str1 == null) {
    		return str2;
    	}
    	
    	if(str2 == null) {
    		return str1;
    	}
    	
    	//if not able to use parse int 
    	
    	StringBuilder sb = new StringBuilder();
    	
    	int carry = 0;
    	int i = str1.length() - 1;
    	int j = str2.length() - 1;
    	
    	while(i >= 0 || j >= 0) {
    		int d1 = 0;
    		int d2 = 0;
    		
    		if(i >= 0) {
    			d1 = str1.charAt(i--) - '0';
    		}
    		if(j >= 0) {
    			d2 = str2.charAt(j--) - '0';
    		}
    		
    		int sum = d1 + d2 + carry;
    		
    		if(sum >= 10) {
    			carry = sum / 10;
    			sum = sum % 10;
    		} else {
    			carry = 0;
    			sb.insert(0, sum);
    		}
    	}
    	
    	return sb.toString();
    }
    
//    public BinaryTreeNode convert(int[] nums, int n) {
//    	return convertToBST(nums, 0, n - 1);
//    }
//    
//    //convert a sorted array into a binary search tree
//    public void convertToBST(int[] nums, int start, int end) {
//    	if(start > end) {
//    		return null;
//    	}
//    	
//    	int mid = start + (end - start) / 2;
//    	
//    	BinaryTreeNode node = new BinaryTree(nums[mid]);
//    	
//    	node.left = convertToBST(nums, start, mid - 1);
//    	node.right = convertToBST(nums, mid + 1, end);
//    	
//    	return node;
//    }
    
    //without using java Integer.bitCount(int);
    public int countOnes(int num){
    	int count = 0;
    	    	
    	//use bitwise "and" to turn off
    	while(num != 0) {
    		num &= num - 1;
    		count++;
    	}
    	
    	return count;
    }
    
    //given string inorder and pre order can u do postorder string?
    //root is always in first item in preorder and last item in postorder
    
    //recursively print left subtree then print right subtree
    //then print root
    
    //visit every node so O(n) but overall o(n^2)

    
    //rounded kind of division
    public double divide(double num1, double num2) {
    	if(num2 == 0) {
    		System.out.println("NO!");
    	}
    	
    	int sign = 1;
    	if(num1 < 0) {
    		num1 *= -1;
    		sign *= -1;
    	}
    	
    	if(num2 < 0) {
    		num2 *= -1;
    		//make back to pos
    		sign *= -1;
    	}
    	
    	double result = 0;
    	
    	while(num1 >= 0) {
    		num1 -= num2;
    		result++;
    	}
    	return (result - 1) * sign;
    }
    
    public boolean wordPattern2(String pattern, String str) {
        //create map and store pattern with string
        Map<Character, String> map = new HashMap<>();
        
        String[] str2 = str.split(" ");
        
        if(pattern.length() != str2.length) {
        	return false;
        }
        
        for(int i = 0; i < pattern.length(); i++) {
        	//check if map contains the value 
        	if(map.containsKey(pattern.charAt(i))) {
        		//check if the keys val is the same as array
        		if(!map.get(pattern.charAt(i)).equals(str2[i])) {
        			return false;
        		}
        	}
        	//before you add make sure the key isnt there
        	else {
        		//check value isnt in map already
        		if(!map.containsValue(str2[i])) {
        			map.put(pattern.charAt(i), str2[i]);
        		} else {
        			return false;
        		}
        	}
        }
        return true;
    }
    
    //game of nim given number of stuff in heap
    public boolean nim(int n) {
    	
    	return n % 4 != 0;
    }
    
    //add digits until remaining is only 1
    public int addDigitsSingle(int num) {
    	if(num == 0) {
    		return 0;
    	}
    	else if(num % 9 == 0) {
    		return 9;
    	}
    	else {
    		return num % 9;
    	}
    }
    
    private static int ROW;
    private static int COL;
    
    //game of life compute the nexrt state 
    public void gameOfLife(int[][] board) {
    	ROW = board.length;
    	COL = board[0].length;
    	
    	
    	//any living cell with fewer than 2 dies
    	
    	
    	//any with 2 or 3 lives
    	
    	//any with more than 3 dies
    	
    	//any with 3 live becomes live
    }
    
    public boolean isAnagram2(String s, String t) {
    	if(s.length() != t.length()) return false;
    	
    	int[] count = new int[26];
    	for(int i = 0; i < s.length(); i++) {
    		count[s.charAt(i) - 'a']++;
    		count[t.charAt(i) - 'a']--;
    	}
    	
    	for(int j : count) {
    		if(j != 0) return false;
    	}
    	
    	return true;
    }
    
     
	public static void main(String args[]) {
		MiscProblems test = new MiscProblems();
		
		StringBuilder sb = new StringBuilder();
		
		
		System.out.println(test.addDigitsSingle(38));
		
//		int[][] island = new int[][] {
//			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
//		};
//		
//		test.gameOfLife(island);
		
	}
}
