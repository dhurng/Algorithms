import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.tree.TreeNode;

/**
 * 
 * @author davidhurng
 *
 */
public class Trees {
	
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) {
			val = x;
		}
	}
	
	public boolean validateBST(TreeNode tree) {
//		int low = Integer.MIN_VALUE;
//		int high = Integer.MAX_VALUE;
		return validateBSThelper(tree, null, null);
	}
	
	public boolean validateBSThelper(TreeNode tree, Integer low, Integer high) {
		if(tree == null) {
			return true;
		}
		
		if((low == null || low < tree.val) && (high == null || tree.val < high) && (validateBSThelper(tree.left, low, tree.val)) && (validateBSThelper(tree.right, tree.val, high))) {
			return true;
		}

		return false;
	}
	
	public int maxDepth(TreeNode tree) {
		int leftSub = 0;
		int rightSub = 0;
		
		if(tree == null) {
			return 0;
		}

		leftSub = maxDepth(tree.left);
		rightSub = maxDepth(tree.right);
		

		if(leftSub > rightSub) {
			return leftSub + 1;
		}
		else {
			return rightSub + 1;
		}
	}
	
	public int minDepth(TreeNode tree) {
		int leftSub = 0, rightSub = 0;
		if(tree == null) {
			return 0;
		}
		
		if(tree.left == null) {
			return minDepth(tree.right) + 1;
		}
		if(tree.right == null) {
			return minDepth(tree.left) + 1;
		}
		
		return Math.min(leftSub, rightSub) + 1;	
	}
	
	public boolean isBalanced(TreeNode tree) {
		if(isBalancedHelper(tree) == -1) {
			return false;
		}
		return true;
	}
	
	
	public int isBalancedHelper(TreeNode tree) {
		if(tree == null) {
			return 0;
		}
		
		int leftSubH = 0;
		int rightSubH = 0;
		
		leftSubH = isBalancedHelper(tree.left);
		if(leftSubH == -1) {
			return -1;
		}
		
		rightSubH = isBalancedHelper(tree.right);
		if(rightSubH == -1) {
			return -1;
		}
		
		
		if(Math.abs(leftSubH - rightSubH) > 1) {
			return -1;
		}
		
		return Math.max(leftSubH, rightSubH) + 1;
	}
	
	
	public TreeNode convert(int[] nums) {
		int beg = 0;
		int end = nums.length - 1;

		return convertHelper(nums, beg, end);

	}
	
	public TreeNode convertHelper(int[] nums, int beg, int end){
		int mid = (beg + end)/2;
		TreeNode curr = new TreeNode(nums[mid]);
		
		if(beg > end) {
			return null;
		}
		
		curr.left = convertHelper(nums, beg, mid - 1);
		curr.right = convertHelper(nums, mid + 1, end);
		
		return curr;
	}
}
