
public class BinarySearchProblems {

	public int searchInsert(int[] nums, int x) {
		int beg = 0;
		int end = nums.length - 1;

		while(beg < end) {
			int mid = (beg + end) / 2;
			
			//found it in mid
			if(nums[mid] == x) {
//				System.out.println("found");
				return mid;
			}
			
			//go to lower half
			else if(x < nums[mid]) {
//				System.out.println("lower");
				//update lower end bound
				end = mid - 1;
			}
			
			//else go to upper half
			else if(x > nums[mid]) {
//				System.out.println("upper");
				beg = mid + 1;
			}
		}
		
		//if not found yet then return where it should be
		//if is is greater return curr + 1
		//else return curr - 1
		if(nums[beg] < x) {
			return beg + 1;
		} else {
			return beg;
		}
	}
	
	//find the minimum
	public int searchRotated(int[] nums) {
		int beg = 0;
		int end = nums.length - 1;
				
		//check val is rotated
		while(beg < end && nums[beg] > nums[end]) {
			int mid = (beg + end) / 2;
			
			//7 > 2 then 4-7 is bigger
			if(nums[mid] > nums[end]) {
				beg = mid + 1;
			}
			
			//if mid < 2
			else {
				end = mid;
			}
		}
		return nums[beg];
	}
	
	//rotated contains duplicates
	public int searchRotatedDuplicates(int[] nums) {
		int beg = 0;
		int end = nums.length - 1;
		
		while(beg < end && nums[beg] > nums[end]) {
			int mid = (beg + end) / 2;
			
			//check mid bigger than end 
			if(nums[mid] > nums[end]) {
				beg = mid + 1;
			}
			
			//check less than end
			else if(nums[mid] < nums[end]) {
				end = mid;
			}
			
			else {
				beg += 1;
			}
		}
		
		return nums[beg];
	}
	
	public static void main(String args[]) {
		BinarySearchProblems test = new BinarySearchProblems();
		
		int[] nums = {1, 2, 4, 5, 6, 7, 0, 0};
		System.out.println(test.searchRotatedDuplicates(nums));
	}
}
