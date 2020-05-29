package com.backtracking;

/**
 * 
 * A Maze is given as N*N binary matrix of blocks where source block is the
 * upper left most block i.e., maze[0][0] and destination block is lower
 * rightmost block i.e., maze[N-1][N-1]. A rat starts from source and has to
 * reach the destination. The rat can move only in two directions: forward and
 * down. In the maze matrix, 0 means the block is a dead end and 1 means the
 * block can be used in the path from source to destination. Note that this is a
 * simple version of the typical Maze problem. For example, a more complex
 * version can be that the rat can move in 4 directions and a more complex
 * version can be with a limited number of moves.
 * 
 * @author Rajesh
 *
 */
public class RatMaze {
	
	private int count = 0;

	/**
	 * 
	 * @param maze
	 */
	public static void solveMaze(int[][] maze) {
		int[][] sol = new int[maze.length][maze.length];
		printMat(maze);
		System.out.println("-----------------------------");
		if (solveMazeUtil(maze, 0, 0, sol)) {
			printMat(sol);
		}
	}

	private static void printMat(int[][] sol) {
		for (int i = 0; i < sol.length; i++) {
			System.out.print("{ ");
			for (int j = 0; j < sol[i].length; j++) {
				System.out.print(sol[i][j] + "  ");
			}
			System.out.println(" }");
		}
	}

	public static void main(String[] args) {
		int[][] maze = { { 1, 0, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 0, 0 },
				{ 0, 1, 1, 1 } };
		solveMaze(maze);
	}

	/**
	 * This will tell us whether the position is valid or not. Validity depends
	 * upon whether we are inside the matrix or not.
	 * 
	 * @param maze
	 * @param row
	 * @param col
	 * @return
	 */
	public static boolean isSafe(int[][] maze, int row, int col) {
		return row >= 0 && row < maze.length && col >= 0 && col < maze.length
				&& maze[row][col] == 1;
	}

	/**
	 * 
	 * @param maze
	 * @param row
	 * @param col
	 * @return
	 */
	public static boolean solveMazeUtil(int maze[][], int row, int col,
			int sol[][]) {
		if (row == maze.length - 1 && col == maze.length - 1
				&& maze[row][col] == 1) {
			sol[row][col] = 1;
			return true;
		}
		if (isSafe(maze, row, col)) {
			sol[row][col] = 1;
			if (solveMazeUtil(maze, row + 1, col, sol)) {
				return true;
			}
			if (solveMazeUtil(maze, row, col + 1, sol)) {
				return true;
			}
			sol[row][col] = 0;
			return false;
		}
		return false;
	}
}
