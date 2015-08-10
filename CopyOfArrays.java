import java.util.HashMap;

/**
 * 
 * @author davidhurng
 *
 */
public class CopyOfArrays {
	
	public static String reverse(String str) {
		
		if(str == null || str.length() == 0) {
			return " ";
		}
		
		String[] array = str.split(" ");
		StringBuilder sb = new StringBuilder();
		
		for(int i = array.length - 1; i >= 0; i--) {
			
			sb.append(array[i]);
			
			if(i > 0) {
				sb.append(" ");
			}
			
			//if not space
//			if(!array[i].equals("")) {
//				sb.append(array[i]).append(" ");
//			}
		}
		return sb.toString();
		
	}
	
	public static String removeChars(String str, String remove) {
		
		//convert to array
		char[] s = str.toCharArray();
		char[] r = remove.toCharArray();
		
		int i, j = 0;
		
		//search thru remove and set val in temp too boolean
		//128 assumes ASCII
		boolean[] removeList = new boolean[128];
		
		//initialize list to all false
		
		for(i = 0; i < r.length; i++) {
			removeList[r[i]] = true;
		}
		
		//loop all chars
		//copy if not flagged
		for(i = 0; j < s.length; j++) {
			if(removeList[s[i]] != true) {
				//copy
				s[j++] = s[i];
			}
		}
		
		
		return new String(s, 0, j);
	}

	
	public static Character firstNonRepeated(String str) {
		HashMap<Character, Integer> map = new HashMap<>();
		
		//build hash table from str
		for(int i = 0; i < str.length(); i++) {
			if(map.containsKey(str.charAt(i))) {
				//already seen
				map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
			}
			else {
				//not seen
				map.put(str.charAt(i), 1);
			}
		}
		
		//now search the table for the vals
		for(int j = 0; j < str.length(); j++) {
			if(map.get(str.charAt(j)) == 1) {
				return str.charAt(j);
			}
		}
		
		return null;
	}
}
