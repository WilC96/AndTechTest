package main.java.com.and.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {

	/**
	 * The following is the method where the solution shall be written
	 */

	public static String solution(String input) {

		String[] numbers = input.replaceAll("[^\\d]", "").split("");

		return numbers[0].trim().length() == 0 ? "No integer present" : helper(numbers);
	}

	// ================================= The helping method
	
	public static String helper(String[] input) {
		
		int[] numbers = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
		
		// Create initial list to store the permutations 
		List<String> christmasPresents = new ArrayList<>();
		
		// Populate the list with permutations
		heapsPermutation(numbers, numbers.length, christmasPresents);
		
		// Store the data into a String array for sorting and maintaining 0s
		String[] perms = christmasPresents.stream().toArray(String[]::new);
		
		// Sort the array
		sort(perms);
		
		// Present the result in the requested format
		StringBuilder result = new StringBuilder();
		
		Arrays.stream(perms).sorted(Collections.reverseOrder()).forEach(num -> {
			result.append(num);
			result.append(",");
		});
		
		return new String(result).substring(0, result.length()-1);
	}
	
	// ================================= Implement Heap's Permutation

	public static void heapsPermutation(int[] numbers, int n, List<String> list) {
		if (n == 1) {
			
			StringBuilder temp = new StringBuilder();

			for (int digit : numbers) {
				temp.append(digit);
			}

			list.add(temp.toString());

		} else {
			for (int i = 0; i < n; i++) {
				heapsPermutation(numbers, n - 1, list);

				int j = (n % 2 == 0) ? i : 0;

				int t = numbers[n - 1];
				numbers[n - 1] = numbers[j];
				numbers[j] = t;
			}
		}
	}

	// ================================= Implement the merge sort
	
	public static void sort(String[] perm) {
		sort(perm, 0, perm.length - 1);
	}

	public static void sort(String[] perm, int start, int end) {
		if (end <= start) {
			return;
		}

		int mid = (start + end) / 2;
		sort(perm, start, mid);
		sort(perm, mid + 1, end);
		merge(perm, start, mid, end);
	}

	public static void merge(String[] perm, int start, int mid, int end) {
		String[] temp = new String[end - start + 1];

		// Index counters for the left and right sides of the array.
		int leftSlot = start;
		int rightSlot = mid + 1;
		int k = 0;

		while (leftSlot <= mid && rightSlot <= end) {
			if (Integer.parseInt(perm[leftSlot]) < Integer.parseInt(perm[rightSlot])) {
				temp[k] = perm[leftSlot];
				leftSlot++;
			} else {
				temp[k] = perm[rightSlot];
				rightSlot++;
			}
			k++;
		}
		if (leftSlot <= mid) {
			while (leftSlot <= mid) {
				temp[k] = perm[leftSlot];
				leftSlot++;
				k++;
			}
		} else if (rightSlot <= end) {
			while (rightSlot <= end) {
				temp[k] = perm[rightSlot];
				rightSlot++;
				k++;
			}
		}
		
		// Populate
		for (int i = 0; i < temp.length; i++) {
			perm[start + i] = temp[i];
		}
	}

	// ================================= Main method
	
	public static void main(String args[]) {
		System.out.println(solution("123Christmas4ever"));	
		System.out.println(solution("Tyson Fury i5 tH3 g0aT"));
		System.out.println(solution("I don't h4v3 5g"));
		System.out.println(solution("123"));
		System.out.println(solution("hello"));
	}
}
