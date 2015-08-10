import java.util.HashMap;
import java.util.Map;


public class TwoSum {
	/* ex add(1), add(3), add(5); find(4) -> true because 1 + 3 find(7) -> false*/
	HashMap<Integer, Integer> map = new HashMap<>();
	
	public void add(int value) {
		int count = 0;
		if(map.containsKey(value)) {
			map.get(value);
		}
		map.put(value, count + 1);
	}
	
	public boolean find(int value) {
		for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int num = entry.getKey();
			int y = value - num;
			if(y == num) {
				if(entry.getValue() >= 2) {
					return true;
				}
			}
			else if(map.containsKey(y)) {
				return true;
			}
		}
		
		return false;
	}
	
	
}
