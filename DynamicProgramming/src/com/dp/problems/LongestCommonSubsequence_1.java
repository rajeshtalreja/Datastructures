/**
 * 
 */
package com.dp.problems;

/**
 * @author iswitch Video Link to refer -
 *         https://www.youtube.com/watch?v=NPZn9jBrX8U&t=1143s Practice Link -
 *         https://leetcode.com/problems/longest-common-subsequence/
 *
 */
public class LongestCommonSubsequence_1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(longestCommonSubsequenceRecursive("abcade", "axcbdy"));

		// corner cases
		System.out.println(longestCommonSubsequenceRecursive("", "axcbdy"));
		System.out.println(longestCommonSubsequenceRecursive("", ""));

		// Complexity in Recursion - 2^(m+n)
		System.out.println(longestCommonSubsequenceIterative("abcade", "axcbdy"));
		
		printLongestCommonSubsequenceIterative("abcade", "axcbdy");
	}

	/**
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int longestCommonSubsequenceRecursive(String s1, String s2) {

		return longestCommonSubsequenceUtil(s1.toCharArray(), 0, s2.toCharArray(), 0);
	}

	public static int longestCommonSubsequenceUtil(char x[], int i, char y[], int j) {
		if (i == x.length || j == y.length) {
			return 0;
		}
		if (x[i] == y[j]) {
			return 1 + longestCommonSubsequenceUtil(x, i + 1, y, j + 1);
		}
		return Math.max(longestCommonSubsequenceUtil(x, i, y, j + 1), longestCommonSubsequenceUtil(x, i + 1, y, j + 1));
	}

	public static int longestCommonSubsequenceIterative(String s1, String s2) {

		int maxLcs = Integer.MIN_VALUE;
		int[][] lcs = new int[s1.length() + 1][s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) {
			for (int j = 0; j <= s2.length(); j++) {
				if (i == 0 || j == 0) {
					lcs[i][j] = 0;
				} else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					lcs[i][j] = 1 + lcs[i - 1][j - 1];
				} else {
					lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
				}
				maxLcs = Math.max(maxLcs, lcs[i][j]);
			}
		}
		return maxLcs;
	}

	public static void printLongestCommonSubsequenceIterative(String s1, String s2) {

		int maxLcs = Integer.MIN_VALUE;
		int[][] lcs = new int[s1.length() + 1][s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) {
			for (int j = 0; j <= s2.length(); j++) {
				if (i == 0 || j == 0) {
					lcs[i][j] = 0;
				} else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					lcs[i][j] = 1 + lcs[i - 1][j - 1];
				} else {
					lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
				}
				maxLcs = Math.max(maxLcs, lcs[i][j]);
			}
		}
		char lcsStr[] = new char[maxLcs];
		int i = lcs.length - 1;
		int j = lcs[0].length - 1;
		int cnt = 0;
		while(i > 0 && j > 0) {
			if(s1.charAt(i-1) == s2.charAt(j-1)) {
				lcsStr[cnt++] = s1.charAt(i - 1);
				i--;
				j--;
			}else if(lcs[i-1][j] > lcs[i][j-1]) {
				i--;
			}else {
				j--;
			}
		}
		StringBuilder s = new StringBuilder(new String(lcsStr));
		System.out.println(s.reverse().toString());
	}
}
