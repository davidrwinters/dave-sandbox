package org.bigsnow.interview;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class SumSearchTest {

	@Test
	public void testSumExists() {
		Assert.assertFalse("Two ints that sum to 4 does not exist!", sumExists(new int[] {2, 8, 3, 9}, 4));  // Check that int value exists twice.
		Assert.assertTrue("Two ints that sum to 8 does exist!", sumExists(new int[] {1, 2, 3, 4, 5}, 8));  // Check that the middle value is checked against the 2nd half.
	}

	// TODO: Test algo where ints are added to the hash/set in one pass of array.
	// TODO: Does sorting and working both ends of the array actually work?
	public static boolean sumExists(int[] arr, int sum) {

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int num : arr) {
			Integer count = map.get(num);
			if (count == null) {
				map.put(num, 1);
			} else {
				map.put(num, ++count);
			}
		}
		//		System.out.printf("map = %s\n", map.toString());

		for (int i = 0; i < (arr.length / 2) + 1; i++) {
			final int otherVal = sum - arr[i];
			final Integer count = map.get(otherVal);
			if (arr[i] == otherVal) {
				if (count != null && count > 1) {
					return true;
				}
			} else {
				if (count != null && count > 0) {
					return true;
				}
			}
		}
		return false;
	}
}
