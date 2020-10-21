/* Sean Michael
 * CS 211
 * 20-Oct-2020
 * tests new functionality in the arrayintlist class
 * exercises tested with separate functions
 */
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Client {

	public static void main(String[] args) {
		ArrayIntList list = new ArrayIntList();
		
		
		int[] nums1 = {1, 18, 2, 7, 18, 39, 18, 40};
		arrayToList(nums1,list);
		testLastIndex(list, 18);
		testLastIndex(list, 3);
		System.out.println();
		
		
		int[] nums2 = {11, -7, 3, 42, 0, 14};
		arrayToList(nums2,list);
		List<Integer> subList = new LinkedList<Integer>(Arrays.asList(3, 42, 0));
		testIndexOfSubList(list,subList);
		System.out.println();
		
		int[] nums3 = {2, 3, 5, 4, 7, 15, 20, 7};
		arrayToList(nums3,list);
		testRunningTotal(list);
		System.out.println();
		
		
		int[] nums4 = {2, -3, 2, 0, 5, 2, 2, 6};
		arrayToList(nums4,list);
		testCount(list,2);
		System.out.println();
		
		
		int[] nums5 = {8, 17, 42, 3, 8};
		arrayToList(nums5,list);
		testRemoveLast(list);
		testRemoveLast(list);
		testRemoveLast(list);
		testRemoveLast(list);
		testRemoveLast(list);
		try {
			testRemoveLast(list);
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("Exception successfully thrown, list is empty");
		}
		
	}
	public static void arrayToList(int[] nums, ArrayIntList list) {
		list.clear();
		for (int i = 0; i < nums.length; i++) {
			list.add(nums[i]);
		}
		System.out.println("Testing list: " + list);
	}
	public static void testLastIndex(ArrayIntList list, int searchFor) {
		System.out.println("Searching for last index of: " + searchFor);
		int index = list.lastIndexOf(searchFor);
		if (index != -1) System.out.println("Found last index at: " + index);
		else System.out.println("Last index not found");
	}
	public static void testIndexOfSubList(ArrayIntList list, List<Integer> subList) {
		System.out.println("Searching for index of sublist: " + subList);
		int index = list.indexOfSubList(subList);
		if (index != -1) System.out.println("Sublist found at: " + index);
		else System.out.println("Sublist not found");
	}
	public static void testRunningTotal(ArrayIntList list) {
		ArrayIntList runTot = list.runningTotal();
		System.out.println("Running total: " + runTot);
		System.out.println("Original list: " + list);
	}
	public static void testCount(ArrayIntList list, int input) {
		System.out.println("Counting: " + input);
		System.out.println("Count of " + input + ": " + list.count(input));
	}
	public static void testRemoveLast(ArrayIntList list) {
		System.out.println("Removing last element: " + list.get(list.size()-1));
		int last = list.removeLast();
		System.out.println("List after removal: " + list);
		System.out.println("Last element: " + last);
		
	}

}
