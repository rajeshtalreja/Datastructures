package com.dp;

/**
 * 1. Longest Common Subsequence(LCS) - Length and print the LCS - Recursive and
 * DP solution
 * 
 * 2. Longest Increasing Subsequence (LIS)
 * 
 * 3. Longest Decreasing Subsequence (LDS)
 * 
 * 4. Longest Bitonic Subsequence
 * 
 * 5. Longest Palindromic Subsequence(LPS)
 * 
 * 6. Longest Repeated Subsequence
 * 
 * 7. Longest Common Substring
 * 
 * 8. Shortest Common Supersequence
 * 
 * 7. LCS of three Strings
 * 
 * 8. Minimum jumps needed to reach end of the array( O(n2) and O(n) approach)
 * 
 * 9. Minimum insertions/deletions required to make string palindrome.
 * 
 * 10.Maximum length chain of pairs.
 * 
 * 11.Maximum length chain of pairs in any order.
 * https://leetcode.com/problems/maximum-length-of-pair-chain/
 * 
 * 12.Building Bridge problem.
 * 
 * 13.Maximum Sum Increasing Subsequence.
 * 
 * 14.Cutting the rod to obtain maximum value.
 * 
 * 15.Maximum Sum Sub array problem(DP and Kadane's algorithm)
 * 
 * 16.Maximum sum subsequence so that no two elements are adjacent
 * https://www.geeksforgeeks
 * .org/maximum-sum-such-that-no-two-elements-are-adjacent/
 * 
 * 17.Maximum sum subsequence where no three are consecutive
 * https://www.geeksforgeeks
 * .org/maximum-subsequence-sum-such-that-no-three-are-consecutive/
 * 
 * 18.Longest subsequence such that difference between adjacents is one
 * https://www
 * .geeksforgeeks.org/longest-subsequence-such-that-difference-between
 * -adjacents-is-one/
 * 
 * 19. Maximum length subsequence with difference between adjacent elements as
 * either 0 or 1
 * https://www.geeksforgeeks.org/maximum-length-subsequence-difference
 * -adjacent-elements-either-0-1/
 * 
 * 20. Coin change problem.
 * 
 * 21. Minimum number of coins needed for making a sum.
 * 
 * 22. Edit Distance between two strings.
 * 
 * 23. Subset Sum Problem
 * 
 * 24.* Longest valid parenthesis( Length of longest valid parenthesis)
 * https://leetcode.com/problems/longest-valid-parentheses/
 * 
 * 25. Given a 2D binary matrix filled with 0's and 1's, find the largest square
 * containing only 1's and return its area.
 * https://leetcode.com/problems/maximal-square/
 * 
 * 26. Maximum product subarray
 * https://leetcode.com/problems/maximum-product-subarray/
 * 
 * 27. Longest Valid Parenthesis(Leetcode)
 * 
 * 28. Longest palindromic substring
 * 
 * 29. 
 * 
 * 
 * 
 * @author Rajesh Talreja
 */
public class DPProblems {
	
	public static void main(String[] args) {
		
		System.out.println(maxProductSubArray_26(new int[]{2,-1,6,-5,-1}));
		System.out.println(subsetSum_23(new int[]{1,2,5,7}, 20));
		System.out.println(longestCommonSubsequence_1("abcade", "axcbdy"));
	}
	
	/**
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int longestCommonSubsequence_1(String s1, String s2) {
		
		return longestCommonSubsequenceUtil(s1.toCharArray(), 0,s2.toCharArray(), 0);
	}

	public static int longestCommonSubsequenceUtil(char x[], int i, char y[], int j) {
		if(i == x.length || j == y.length) {
			return 0;
		}
		if(x[i] == y[j]) {
			return 1 + longestCommonSubsequenceUtil(x, i+1,y, j+1);
		}
		return Math.max(longestCommonSubsequenceUtil(x, i,y, j+1), longestCommonSubsequenceUtil(x, i+1,y, j+1));
	}
	
	
	public static int longestValidParenthesis_24(String str){
		if(str == null || str.length() == 0){
			return 0;
		}
		str = str.trim();
		//There are two possible solutions to it.
		
		// 1. DP solution
		// Create a DP array which will store the length of valid parenthesis at index each index.
		// We will find the max out of that.
		// While filling that array we need to keep below things in mind
		// 1. if the character at i is ( then dp[i] = 0, which means if the string ends at ( which can not be valid parenthesis
		// 2. If the character at i is ) then there are two cases
		//      a. if char at (i-1) == ( then dp[i] = dp[i-2] + 2 take an example to understand this - )() - so dp[0] = 0, dp[1] = 0 and dp[2] = 2
		//		    the reason for checking for dp[i-2] is we will the longest till i-2 and add 2 for () so we derived the above formula.
		//      b. if char at i-1 == ) then we need to check i-dp[i-1]-1 character. The reason for checking this is suppose the string is 
		//         )()) - if we are at the last character and we found ) and the previous character is ) so may be the previous was valid then we must
		//         check for first character which will be i - longest till previous and -1(j = i-dp[i-1] -1) if the this character is (
		//			In this case dp[i] = dp[i-1] + 2 because longest[i-1] is the valid till i-1 + 2 because of () matching
		//			Also we will add dp[j-1] which longest till j-1
		
		int dp[] = new int[str.length()];
		
		//Single character cannot be a valid 
		dp[0] = 0;
		//To store tha max length
		int max = 0;
		for (int i=0; i < str.length(); i++) {
			//If the character at index i is ) then there might be valid parenthesis in this string.
			// We will not write the conditions for ( because this cannot be the valid parenthesis
			if(str.charAt(i) == ')'){
				//This is the case of when we found the matching. We need to check the prior matching at i-2
				if(str.charAt(i-1) == '('){
					dp[i] = dp[i-2] + 2;
				}else{
					//Consider the case of (()) or )()) in both these cases we need to check for character at 0 index
					// i = 3 - 2 - 1 = 0
					// Logically j = current index - longest valid till last and -1 to get the index before that
					int j = i - dp[i-1] - 1;
					if( j >=0 && str.charAt(j-1) == '('){
						dp[i] = dp[i-1] + 2 + ( j-1>=0 ? dp[j-1] : 0);
					}
				}
				max = Math.max(max, dp[i]);
			}
		}
		
		return max;
		
		
		//2. Stack Solution 
		//
		
		
		
		
		
		
	}
	
	
	/**
	 * Subset Sum Problem
	 * @param arr
	 * @param sum
	 * @return
	 */
	public static boolean subsetSum_23(int arr[], int sum){
		
		//Approach is discussed in 
		//https://www.youtube.com/watch?v=K20Tx8cdwYY
		if(arr == null || arr.length == 0){
			return false;
		}
		boolean dp[][] = new boolean[arr.length +1][sum+1];
		for(int i=0;i<=arr.length;i++){
			for(int j=0;j<=sum;j++){
				if(i ==0 && j ==0){
					dp[i][j] = true;
				}else if(i ==0){
					dp[i][j] = false;			
				}else if(j == 0){
					dp[i][j] = true;
				}else if(arr[i-1] > j){
					dp[i][j] = dp[i-1][j];
				}else{
					dp[i][j] = dp[i-1][j] || dp[i-1][j-arr[i-1]];
				}
			}
		}
		
		return dp[arr.length][sum];
	}

	public static int maxProductSubArray_26(int arr[]) {

		// Approach is duscussed in
		// https://www.youtube.com/watch?v=vtJvbRlHqTA
		// Problem is solved here
		// https://leetcode.com/problems/maximum-product-subarray/

		/**
		 * 1. Find all subarrays and keep track of maximum product. (complexity
		 * - o(n2))
		 * 
		 * 2. O(n) DP approach mentioned in above video
		 */
        if(arr == null || arr.length == 0){
        	return 0;
        }
		int currMaxProd = arr[0];
		int currMinProd = arr[0];
		int prevMaxProd = arr[0];
		int prevMinProd = arr[0];
		int result = arr[0];
		for (int i = 1; i < arr.length; i++) {
			currMaxProd = Math.max(prevMaxProd*arr[i] , Math.max(arr[i], prevMinProd*arr[i]));
			currMinProd = Math.min(prevMaxProd*arr[i] , Math.min(arr[i], prevMinProd*arr[i]));
			result = Math.max(result, currMaxProd);
			prevMaxProd = currMaxProd;
			prevMinProd = currMinProd;
		}
		return result;
	}

}
