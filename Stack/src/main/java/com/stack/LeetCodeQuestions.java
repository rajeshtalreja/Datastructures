package com.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCodeQuestions {
	
	public static void main(String[] args) {
		int[] result = getNextGreaterElementArray(new int[] {1,2,1});
														//	2,-1,2
														//  12,-1,6,5,2,5,6,4,2,4,6,-1,
														//	12,-1,6,5,2,5,6,4,2,4,6,-1,
		Arrays.stream(result).forEach(c -> {System.out.print(c + ",");});
		//String s = "([})";
		//System.out.println(validParenthesis(s));
		int n = 4;
		 
        int[][] maze = { { 1, 0, 0, 0 },
                         { 1, 1, 0, 1 },
                         { 1, 1, 0, 0 },
                         { 0, 1, 1, 1 } };
        
        System.out.println(findSum(maze,4));
 
	}
	
	public static boolean validParenthesis(String s) {
		
		if(s == null || s.length() == 0) {
			return true;
		}
		
		if(s.length() % 2 == 1) {
			return false;
		}
		char arr[] = s.toCharArray();
		Map<Character,Character> matchingMap = new HashMap<Character,Character>();
		matchingMap.put('}', '{');
		matchingMap.put(')', '(');
		matchingMap.put(']', '[');
		java.util.Stack<Character> stack = new java.util.Stack<Character>();
		for(int i = 0;i<arr.length;i++) {
			char c = arr[i];
			if(c == '(' || c == '[' || c == '{') {
				stack.push(c);
			}else {
				char matchingOpen = matchingMap.get(c);
				if(stack.peek() != matchingOpen) {
					return false;
				}else {
					stack.pop();
				}
			}
		}
		return stack.isEmpty();
	}
	public boolean isCountOfOpenCloseMatching(String s) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for(char c : s.toCharArray()) {
			Integer v = map.getOrDefault(c, 0);
			v = v + 1;
			map.put(c, v);
		}
		char arr[] = new char[] {'{', '(', '['};
		//
		
		return true;
		
	}
		
	public static int[] getNextGreaterElementArray(int []arr) {
		
//		int[] nge = new int[arr.length];
//		for(int i = 0;i<arr.length;i++) {
//			int currElement = arr[i];
//			boolean found = false;
//			for(int j = i + 1;j<arr.length;j++) {
//				if(arr[j] > currElement) {
//					nge[i] = arr[j];
//					found = true;
//					break;
//				}
//			}
//			if(!found) {
//				nge[i] = -1;
//			}
//		}
		
		// 1 2 1
		// 2 -1 2
		
		
		//
		
		int tempArr[] = new int[arr.length * 2];
		int j = 0;
		for(int i = 0;i<tempArr.length;i++) {
			tempArr[i] = arr[i % arr.length];
		}
		
		// 1 2 1
		// 1 2 1 1 2 1
		java.util.Stack<Integer> stack = new java.util.Stack<Integer>();
		int[] nge = new int[tempArr.length];
		for(int i = tempArr.length - 1; i>= 0;i--) {
			while(!stack.isEmpty() && stack.peek() <= tempArr[i]) {
				stack.pop();
			}
			if(stack.isEmpty()) {
				nge[i] = -1;
			}else {
				nge[i] = stack.peek();
			}
			stack.push(tempArr[i]);	
		}
		int[] res = Arrays.copyOf(nge, arr.length);
		return res;
	}
	
	public static ArrayList < String > findSum(int[][] arr, int n) {
        // Write your code here.
        ArrayList<String> result = new ArrayList<>();
        boolean [][]visited = new boolean[n][n];
        solve(0,0,arr,n, new StringBuilder(""), result, visited);
        return result;
    }

    public static void solve(int r, int c, int[][] arr, int n, StringBuilder s, List<String> list, boolean[][] visited){

        if(r == n - 1 && c == n-1){
            list.add(s.toString());
            return;
        }
        char dir[] = new char[]{'D', 'L', 'R', 'U'};
        int row[] = new int[]{1, 0, 0, -1};
        int col[] = new int[]{0, -1, 1, 0};
        visited[r][c] = true;
        for(int i = 0;i<4;i++){
            int nRow = r + row[i];
            int nCol = c + col[i];
            if(isSafe(arr, nRow, nCol, visited)){
                s.append(dir[i]);
                solve(nRow, nCol, arr, n, s, list, visited);
                s.deleteCharAt(s.length() - 1);
            }
        }
        visited[r][c] = false;
    }
    public static boolean isSafe(int [][]arr, int r, int c, boolean[][] visited){
        if(r >= 0 && r < arr.length && c >= 0 && c < arr[r].length && !visited[r][c] && arr[r][c] == 1){
            return true;
        }
        return false;
    }

}
