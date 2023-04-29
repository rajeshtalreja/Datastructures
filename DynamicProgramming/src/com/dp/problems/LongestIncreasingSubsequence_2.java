package com.dp.problems;

import java.util.Arrays;

public class LongestIncreasingSubsequence_2 {

	public static void main(String[] args) {
		// One common implementation is just sort the array and find LCS

		String s = "axybnmpc";
		// longest is 3 abc

		String s1 = new String(s);
		char[] arr = s1.toCharArray();
		Arrays.sort(arr);
		s1 = new String(arr);

		System.out.println(LongestCommonSubsequence_1.longestCommonSubsequenceIterative(s, s1));
		LongestCommonSubsequence_1.printLongestCommonSubsequenceIterative(s, s1);

		int array[] = { 10, 9, 2, 5, 3, 7, 101, 18 };
		System.out.println(LongestIncreasingSubsequence(array));
		SegmentTree root = new SegmentTree(array);
		System.out.println(root.count);
	}

	// Leetcode #300 - https://leetcode.com/problems/longest-increasing-subsequence/
	public static int LongestIncreasingSubsequence(int[] nums) {
		// Another way is

		int lis[] = new int[nums.length];
		Arrays.fill(lis, 1);

		int maxLis = 1;
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					lis[i] = Math.max(lis[i], lis[j] + 1);
				}
			}
			maxLis = Math.max(maxLis, lis[i]);
		}
		return maxLis;
	}

	// Leetcode #300 - https://leetcode.com/problems/longest-increasing-subsequence/
	public static int LongestIncreasingSubsequenceWithDifferenceAtmostK(int[] nums, int k) {
		// Another way is

		int lis[] = new int[nums.length];
		Arrays.fill(lis, 1);

		int maxLis = 1;
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[i] - nums[j] <= k) {
					lis[i] = Math.max(lis[i], lis[j] + 1);
				}
			}
			maxLis = Math.max(maxLis, lis[i]);
		}
		return maxLis;
	}

}

/**
 * 
 * @author iswitch
 *
 */
class SegmentTreeNode {
	int start;
	int end;
	int sum;
	SegmentTreeNode left;
	SegmentTreeNode right;
	
	public SegmentTreeNode(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}

	
}
class SegmentTree{
	SegmentTreeNode root;
	int count = 0;
	public SegmentTree(int nums[]) {
		root = build(nums, 0 , nums.length - 1);
	}
	public SegmentTreeNode build(int nums[], int start, int end) {
		if(start > end) {
			return null;
		}
		count++;
		SegmentTreeNode node = new SegmentTreeNode(start, end);
		if(start == end) {
			node.sum = nums[start];
			return node;
		}
		int mid = start + (end - start)/2;
		node.left = build(nums, start, mid);
		node.right = build(nums, mid + 1, end);
		node.sum = node.left.sum + node.right.sum;
		return node;
	}
	
}
