import java.util.HashMap;
import java.util.Hashtable;

/**
 * 
 * @author davidhurng
 *	Problems from Leet Code
 */
public class Leet {

	/* Find the total area covered by two rectilinear rectangles in a 2D plane. */
	public int rectArea(int A, int B, int C, int D, int E, int F, int G, int H) {
		
		//find areas for rectangles first
		int rect1 = (C - A) * (D - B);
		int rect2 = (G - E) * (H - F);
		
		//if there is no intersection
		if(A >= G || B >= H || C <= E || D <= F) {
			return rect1 + rect2;
		}
		
		//there is intersection
		//calc intersect area
		int length = Math.min(C, G) - Math.max(A, E);
		int height = Math.min(D, H) - Math.max(B, F);
		
		return rect1 + rect2 - (length * height);
	}
	
	public int reverseInt(int n) {
		int reversed = 0;
		while(n != 0) {
			reversed = reversed * 10 + n % 10;
			n = n/10;
		}
		return reversed;
	}
	
	/* rotate an array */
	public static void rotate(int[] arr, int order) {
		order = order % arr.length;
	 
		if (arr == null || order < 0) {
			throw new IllegalArgumentException("Illegal argument!");
		}
	 
		//length of first part
		int a = arr.length - order; 
	 
		reverse(arr, 0, a-1);
		reverse(arr, a, arr.length-1);
		reverse(arr, 0, arr.length-1);
	 
	}
	 
	public static void reverse(int[] arr, int left, int right){
		if(arr == null || arr.length == 1) 
			return;
	 
		while(left < right){
			int temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;
			left++;
			right--;
		}	
	}
	
	/* excel sheet get the number from letter */
	public int titleToNumber(String s) {
        int val = 0;
        //go thru string
        for (int i = 0; i < s.length(); i++){
        	//26 letts of alph add the int val of that character 
            val = val * 26 + (int) s.charAt(i) - (int) 'A' + 1;
        }
        return val;
    }
	
	/* Given an array of integers, find two numbers such that they add up to a specific target
number. */
	public int[] twoSum(int[] numbers, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();
		
		//go thru the array
		for(int i = 0; i < numbers.length; i++) {
			//if the map contains the other number
			if(map.containsKey(target - numbers[i])) {
				return new int[] { map.get(target-numbers[i]) + 1, i + 1};
			}
			//if not then put
			map.put(numbers[i], i);
		}
		
		throw new IllegalArgumentException("No two sum solution");
	}
	
	public int[] twoSumSorted(int[] numbers, int target) {
		int i = 0;
		int j = numbers.length - 1;
		int sum = 0;
		while(i < j) {
			sum = numbers[i] + numbers[j];
			//if less than add
			if(sum < target) {
				i++;
			}
			
			//if more then less
			else if(sum > target) {
				j--;
			}
			//if exact then win!
			else  {
				return new int[] { i + 1, j + 1};
			}
		}
		throw new IllegalArgumentException("No two sum solution");
	}
	
	/*check if it is palindrome ignore cases*/
	public boolean isPalindrome(String input) {
		
		if(input == "") {
			return true;
		}
		
		//strip out all spaces
		input.replaceAll("\\s", "");
		int i = 0; 
		int j = input.length() - 1;
		
		while(i < j) {
			if(Character.toLowerCase(input.charAt(i)) != Character.toLowerCase(input.charAt(j))) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}
	
	public int strstr(String needle, String haystack) {
		
		if(haystack == null || needle == null) {
			return 0;
		}
		
		//if length is 0 then always return 0
		if(needle.length() == 0) {
			return 0;
		}
		
		//iter thru haystack
		for(int i = 0; i < haystack.length(); i++) {
			if(i + needle.length() > haystack.length()) {
				return -1;
			}
			//iter thru the needle
			int m = i;
			for(int j = 0; j < needle.length(); j++) {
				//if they match at letter
				if(needle.charAt(j) == haystack.charAt(m)) {
					if(j == needle.length() - 1) {
						return i;
						//continue
					}
					m++;
				}
				//doesnt match at that letter
				else {
					break;
				}
			}
		}
		return -1;
	}
	
	/*Reverse a string*/
	public String reverseString(String input) {
		
		StringBuilder builder = new StringBuilder();
		int len = input.length();
		
		if(input == null) {
			return null;
		}
		
		//start at the end and read until u hit space
		for(int i = input.length() - 1; i >= 0; i--) {
			//if the character is a space
			if(input.charAt(i) == ' ') {
				len = i;
			}
			//reaches the end or before it will be space
			else if(i == 0 || input.charAt(i - 1) == ' ') {
				if(builder.length() != 0) {
					builder.append(' ');
				}
				builder.append(input.substring(i, len));
			}
		}
		return builder.toString();
	}
	
	/*convert string to integer*/
	public int atoi(String input) {
	
		final int maxDiv10 = Integer.MAX_VALUE / 10;
		
		if(input == null) {
			return 0;
		}
		
		int len = input.length();
		int index = 0;
		int sign = 1;
		int res = 0;
		
		//if white space just continue
		while(index < len && Character.isWhitespace(input.charAt(index))) {
			index++;
		}
		
		//check if still in bounds
		if(index == len) {
			return 0;
		}
		
		//signal 
		if(input.charAt(index) == '-') {
			sign = -1;
			//continue
			index++;
		}
		else if(input.charAt(index) == '+') {
			sign = 1;
			index++;
		}

		while(index < len && Character.isDigit(input.charAt(index))) {
			int digit = Character.getNumericValue(input.charAt(index));
			
			//if result greater than max or equal to it 
			if(res > maxDiv10 || res == maxDiv10 && digit >= 8) {
				return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			}
			res = res * 10 + digit;
			index++;
		}
		return sign * res;
	}
	
	public boolean validateNum(String str) {
		
		int index = 0;
		int len = str.length();
		
		while(index < len && Character.isWhitespace(str.charAt(index))) {
			index++;
		}
		
		if(index < len && (str.charAt(index)) == '+' || (str.charAt(index) == '-')) {
			index++;
		}
		
		boolean isNum = true;
		
		while(index < len && Character.isDigit(str.charAt(index))) {
			index++;
			isNum = true;
		}
		
		//handle decimals
		if(index < len && str.charAt(index) == '.') {
			index++;
			while(index < len && Character.isDigit(str.charAt(index))) {
				index++;
				isNum = true;
			}
		}
		
		//handle exponents
		if(index < len && str.charAt(index) == 'e') {
			index++;
			isNum = false;
			if(index < len && (str.charAt(index)) == '-' || (str.charAt(index)) == '+') {
				index++;
			}
			while(index < len && Character.isDigit(str.charAt(index))) {
				index++;
				isNum = true;
			}
		}
		
		while(index < len && Character.isWhitespace(str.charAt(index))) {
			index++;
		}
		
		return isNum && index == len;
	}
	
	public int longestSub(String str) {
		//create a map
		HashMap<Character, Integer> map = new HashMap<>();
		//search map for duplicates 
		for(int i = 0; i < str.length(); i++) {
			//if there is stop reading
			if(map.containsKey(str.charAt(i))) {
				//return the value
				return map.get(str.charAt(i));
			}
			//if not put it in and give it a value
			map.put(str.charAt(i), i + 1);
		}
		//add the keys of the map that have value
		return 1;
	}
	
	    public int[] twoSum2(int[] nums, int target) {
	        int length = nums.length;
	        for(int i = 0; i < length; i++) {
	            //search arr, target, start
	            int val = bsearch(nums, target - nums[i], i + 1);
	            if(val != -1) {
	                //found it 
	                return new int[] {i, val};
	            }
	            //else not found
	        }
	            throw new IllegalArgumentException("No 2 sum!");   
	    }
	    
	    public int bsearch(int[] nums, int x, int start) {
	        int L = start, R = nums.length - 1, M = (L+R)/2;
	        while(L < R) {
	            if(x < nums[M]) {
	                //left side
	                R = M - 1;
	            }
	            else if(x > nums[M]) {
	                //right side
	                L = M;
	            }
	            else {
	                //equal to 
	                return M;
	            }
	        }
	        return -1;
	    }
	    
	    public char[] rString(char[] s) {
	    	char[] res = reverse(s, 0, s.length);
	    	int j = 0;
	    	for(int i = 0; i < res.length; i++) {
	    		if(s[i] == ' ' || i == s.length) {
	    			reverse(s, j, i-1);
	    		}
	    	}
	    	return res;
	    }
	    
	    public char[] reverse(char[] s, int beg, int end) {
	    	while(beg < end) {
	    		char temp = s[beg];
	    		s[beg] = s[end];
	    		s[end] = temp;
	    		beg++;
	    		end--;
	    	}
	    	return s;
	    }

}
