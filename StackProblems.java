import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StackProblems {
	//min stack problem
	
	Stack<Integer> stack = new Stack<>();
	Stack<Integer> minStack = new Stack<>();
	
	public void push(int x) {
		stack.push(x);
		
		if(minStack.isEmpty() || x <= minStack.peek()) {
			minStack.push(x);
		}

	}
	
	public void pop() {
		int popped = stack.pop();
		if(popped == minStack.peek()) {
			minStack.pop();
		}
	}
	
	public int top() {
		return stack.peek();
	}
	
	public int getMin() {
		return minStack.peek();
	}
	
	interface Operator {
		int eval(int num1, int num2);
	}
	
	//reverse polisj
	public int reversePolish(String[] tokens) {
		
		Map<String, Operator> map = new HashMap<>();
		
		map.put("+", new Operator() {
			 public int eval(int x, int y) { return x + y; }
			});
		
		map.put("-", new Operator() {
			 public int eval(int x, int y) { return x - y; }
			 });
		
		map.put("*", new Operator() {
			 public int eval(int x, int y) { return x * y; }
			 });
		
		map.put("/", new Operator() {
			 public int eval(int x, int y) { return x / y; }
			 });
		
		Stack<Integer> stack = new Stack<>();
		int num1 = 0;
		int num2 = 0;
		
		for(int i = 0; i < tokens.length; i++) {
			
			if(map.containsKey(tokens[i])) {
				num1 = stack.pop();
				num2 = stack.pop();
				
				num1 = map.get(tokens[i]).eval(num2, num1);
				
				stack.push(num1);
			}
			
			else {
				stack.push(Integer.parseInt(tokens[i]));
			}
			
		}
		return stack.pop();
	}
			
	
	public boolean validParenth(String input) {
		
		Map<Character, Character> map = new HashMap<>();
		Stack<Character> stack = new Stack<>();
		
		map.put('(', ')');
		map.put('{', '}');
		map.put('[', ']');
		
		if(input.length() == 0) {
			return true;
		}
		
		for(int i = 0; i < input.length(); i++) {
			//map contains key of opening
			if(map.containsKey(input.charAt(i))) {
				stack.push(input.charAt(i));
				
				System.out.println(stack.peek());
			}
			
			//must not be empty and must be closing
			else if (stack.isEmpty() || input.charAt(i) != map.get(stack.pop())){
				return false;
			}
		}
	
		return stack.isEmpty();
	}
	
	public static void main(String args[]) {
		StackProblems test = new StackProblems();
		
		String tester = "([])";
		System.out.println(test.validParenth(tester));
	}
}
