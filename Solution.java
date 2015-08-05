import java.lang.reflect.Array;
import java.util.*;

import javax.xml.transform.Templates;


public class Solution{
	
    public void rString(char[] s) {
    	reverse(s, 0, s.length - 1);
    	int j = 0;
    	for(int i = 0; i <= s.length; i++) {
    		if(i == s.length || s[i] == ' ') {
    			reverse(s, j, i - 1);
    			j = i + 1;
    		}
    	}
    	System.out.println(s);
    }

    public static void reverse(char[] s, int beg, int end) {
    	while(beg < end) {
    		char temp = s[beg];
    		s[beg] = s[end];
    		s[end] = temp;
    		beg++;
    		end--;
    	}
    }
    
    public void rInt(int[] input, int k) {
    	int end = input.length - 1;
    	int kth = end - k + 1;
    	reverseInt(input, kth, end);
    	reverseInt(input, 0, kth - 1);
    	reverseInt(input, 0, end);
    	
    	for(int j = 0; j < input.length; j++) {
    		System.out.println(input[j]);
    	}
    }
    
    public void reverseInt(int[] arr, int beg, int end) {
    	while(beg < end) {
    		int temp = arr[beg];
    		arr[beg] = arr[end];
    		arr[end] = temp;
    		beg++;
    		end--;
    	}
    }
    
    public int atoi(String input) {
    	//convert string to int
    	int max = Integer.MAX_VALUE;
    	boolean flag = true;
    	int result = 0;
    	
    	for(int i = 0; i < input.length(); i++) {
        	//read and ignore all the white space
    		if(input.charAt(i) != ' ') {
    			if(input.charAt(i) == '-') {
    				flag = false;
    			}
    			else if(input.charAt(i) =='+') {
    				flag = true;
    			}
    			else if(Character.isDigit(input.charAt(i))) {
    				int test = input.charAt(i) - 48;
    				result = (result * 10) + (input.charAt(i) - 48);
    			}
    		}
    		//anything after numbers is ignored
    		//if input is not there then 0
    		//if max then turn in max or min of int
       	}
    	
    	if(result > max) {
    		System.out.println(max);
    		return max;
    	}
    	
    	if(!flag) {
    		return result *= -1;
    	}
    	else {
    		return result;
    	}
    }
    
    /*reverse int math technique*/
    public int reverseNum(int input) {
    	int res = 0;
    	int right = 0;
    	boolean flag = true;
    	
    	//handle negative
    	if(input < 0) {
    		input *= -1;
    		flag = false;
    	}
    	
    	while(input > 0) {
    		right = input % 10;
    		res = res * 10 + right;
    		input /= 10;
    	}
    	
    	if(!flag) {
    		res *= -1;
    	}
    	
    	return res;
    }
    
    public boolean validate(String s) {
    	boolean flag = false;
    	int i = 0;
    	int length = s.length();
    	
    	//ignore whitespace
    	while(i < length && s.charAt(i) == ' ') {
    		i++;
    	}
    	
    	//if + or - sign skip
    	if(i < length && s.charAt(i) == '+' || s.charAt(i) == '-') {
    		i++;
    	}
    	    	
    	//if it is digit then true
    	while(i < length && Character.isDigit(s.charAt(i))) {
//    		System.out.println("test");
    		i++;
    		flag = true;
    	}
    	
    	//if anything else cept dec then false
    	if(i < length && s.charAt(i) == '.') {
    		i++;
    		//digit after
    		while(i < length && Character.isDigit(s.charAt(i))) {
    			i++;
    			flag = true;
    		}
    	}
    	
    	//if space
    	while(i < length && s.charAt(i) == ' ') {
    		i++;
    	}
    	
    	//if it is true so far and at the end
    	if(flag && i == length) {
    		flag = true;
    	}
    	else {
    		flag = false;
    	}
    	
    	return flag;
    }
    
    public int longestSub(String s) {
    	
    	Map<Character, Integer> map = new HashMap<>();
    	int beg = 0;
    	int end = 0;
    	int size = 0;
    	
    	String longest = "";
    	
    	for(int i = 0; i < s.length(); i++) {
    		//if duplicate is found
    		if(map.containsKey(s.charAt(i))) {
    			//get duplicate pos
    			beg = map.get(s.charAt(i));
    			//end is the curr duplicate
    			end = i;
    			
    			//if this substring is longer
    			if(s.substring(beg, end).length() > longest.length()) {
    				longest = s.substring(beg, end);
//    				System.out.println(longest);
    			}
    			map.clear();
    		}
    		
    		//check a the end
    		else if(i == s.length() - 1) {
//    			System.out.println("at end " + end + " " + longest + " " + longest.length());
    			
    			if(s.substring(end).length() > longest.length()) {
//    				System.out.println(s.substring(end).length());
    				longest = s.substring(end);
    			}
    		}
    		
    		map.put(s.charAt(i), i);
    	}
 
		System.out.println(longest);
    	return longest.length();
    }
    
    public int longestSub2(String s) {
    	String longest = "";
    	int i = 0;
    	int length = s.length();
    	boolean[] map = new boolean[256];
    	int counter = 0;
    	int j = 0;
    	int l = j;
    	
    	while(i < length) {
    		if(map[s.charAt(i)]) {    			
    			if(i == length - 1) {    				
    				String sub2 = s.substring(j);
    				if(sub2.length() > longest.length()) {
    					longest = sub2;
    				}
    				break;
      			}
    			i++;
    		}
   
    		else {
    			if(counter == 2) {
	    			String sub = s.substring(j,i);
	    			if(sub.length() > longest.length()) {
	    				longest = sub;
	    			}

	    			/*****************************/
	
	    			for(int k = i - 1; k >= j; k--) {
	    				if(s.charAt(k) != s.charAt(j)) {
	    					l = k;
	    				}
	    			}
	    			
	    			j = l;
	    			i = j;
	    			
	    			/****************************/
	    			
	
	    			Arrays.fill(map, false);
	    			counter = 0;
    			}
    			
    			else {
        			map[s.charAt(i)] = true;
        			counter++;
        			i++;
    			}
    		}
    	}
    	
    	System.out.println(longest + " " + longest.length());
    	return longest.length();
    }
    
    public ArrayList<String> missing(int[] input) {
    	int i = 0;
    	int length = input.length;
    	ArrayList<String> res = new ArrayList<>();
    	
    	//empty
    	if(length == 0) {
    		res.add("0 -> 99");
    	}
    	
    	for(i = 0; i < length; i++) {
    		if(i == 0) {
    			if(input[i] != 0) {
    				res.add("0 -> " + (input[i] - 1));
    			}
    		}
    		
    		if(i != length - 1) {
//    			System.out.println("mid");
    			//check if diff is 1
    			if(input[i + 1] - input[i] == 1) {
    				//ignore
    			}
    			else if(input[i + 1] - input[i] == 2) {
    				res.add(input[i] + 1 + " ");
    			}
    			else {
    				res.add((input[i] + 1) + " -> " + (input[i+1] - 1));
    			}
    		}
    		
    		else {
//    			System.out.println("end");
    			if(input[i] != 99) {
    				res.add((input[i] + 1) + " -> 99");
    			}
    		}
    	}
    	
    	System.out.println(res.toString());
    	return res;
    }
    
    
    public int longestPal(String s) {
    	int length = s.length();
    	int length1 = 0;
    	int length2 = 0;
    	int longest = 0;
    	
    	for(int i = 0; i < length; i++) {
	    	if(0 <= i - 1 && i + 1 < length) {
	    		length1 = expand(s, i-1, i+1) + 1;
	    		length2 = expand(s, i, i+1);
	    		System.out.println(length1 + " first");
	    		System.out.println(length2 + " second");
	    		
	    		if(length1 > length2 && length1 > longest) {
	    			longest = length1;
	    		}
	    		else if(length2 > length1 && length2 > longest) {
	    			longest = length2;
	    		}
	    		
	    	}
    	}
    	
    	System.out.println(longest);
    	return longest;
    }
    
    public int expand(String s, int prev, int next) {
    	int size = 0;
    	
    	while(0 <= prev && next < s.length()) {
//    		System.out.println(s.charAt(prev) + " " + s.charAt(next));
    		
	    	//check prev and next
	    	if(s.charAt(prev) == s.charAt(next)) {
	    		size += 2;
	    		
	    		//check the ones after
	    		prev--;
	    		next++;
	    	}
	    	
	    	else {
	    		break;
	    	}
	    	
	    	//if equal then continue 
    	
    	}
    	
    	return size;	
    }
    
    public boolean oneEdit(String s, String t) {
    	int i = 0;
    	int j = 0;
    	boolean edit = false;
    	int count = 0;
    	
    	//more than 1 change
    	if(t.length() - s.length() > 1) {
    		System.out.println("More than 1 difference");
    		return false;
    	}
    	
    	//m == n 
    	//find exactly 1 
    	if(s.length() == t.length()) {
    		for(;i < t.length(); i++) {
    			if(s.charAt(i) != t.charAt(i)) {
    				edit = true;
    				count++;
    			}
    			if(count > 1) {
    				edit = false;
    			}
    		}
    	}
    	
    	//m != n
    	//just consider the addition
    	else {    		
    		//if there is one change in letter
    		for(; i < s.length() && j < t.length(); i++, j++) {
				System.out.println(s.charAt(i) + " " + t.charAt(j));
    			if(s.charAt(i) != t.charAt(j)) {
    				edit = true;
    				count++;
    				i--;    				
    			}
    			if(count > 1) {
    				edit = false;
    			}
    		}
    		//if everything in s is same in t then its inserted at the end
    		if(!edit && count == 0) {
    			edit = true;
    		}
    	}
    	
    	System.out.println(edit);
    	return edit;
    }
    
    public List<Integer> plusOne(List<Integer> nums) {
    	int length = nums.size() - 1;

    	for(int i = length; i >= 0; i--) {
    		if(nums.get(i) < 9) {
    			nums.set(i, nums.get(i) + 1);
       			return nums;
    		}
    		else {
    			nums.set(i, 0);
    		}
    	}
    	    	
    	nums.add(0);
    	nums.set(0, 1);

    	return nums;
    }
    
    public boolean palNum(int nums) {
//    	//if negative or just 1 digit
//    	if(0 > nums && nums < 9) {
//    		return false;
//    	}
    	
    	int right = 0;
    	int reverse = 0;
    	int temp = nums;
    	boolean res = false;
    	
    	//reverse
    	while(temp > 0) {
    		right = temp % 10;
    		reverse = reverse * 10 + right;
    		temp /= 10;
    	}
    	
//    	System.out.println(reverse + " " + temp + " " + nums);
    	
    	//compare
    	if(reverse - nums == 0) {
    		res = true;
    	}
    	
    	System.out.println(res);
    	return res;
  }
   
    public int largestInt(int[] nums) {
    	int largest = 0;
    	int secondlargest = 0;
    	int count = 0;
    	
    	for(int i = 0; i < nums.length; i++) {
    		if(nums[i] > largest) {
    			secondlargest = largest;
    			largest = nums[i];
    		}
    		else if(nums[i] > secondlargest && nums[i] != largest) {
    			secondlargest = nums[i];
    		}
    	}
    	
    	System.out.println(largest + " " + secondlargest);
    	
    	return secondlargest;
    }
    
 
    public static void main(String[] args) {
		Solution test = new Solution();

	}
}
