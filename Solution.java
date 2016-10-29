import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static int getBiggestRegion(int[][] matrix, int n, int m) {
        int biggestRegion = 0;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] != 0 && !(visited[i][j])) {
                    int region = dfsFromVertex(matrix, i, j, visited, n, m);
                    if (region > biggestRegion) {
                        biggestRegion = region;
                    }
                }
            }
        }
        return biggestRegion;
    }
    
    public static void pushCoordinates(int[][] matrix, boolean[][] visited, Stack<int[]> st, int coordinate1, int coordinate2, int n, int m) {
        if (coordinate1 >= 0 && coordinate1 < n && coordinate2 >= 0 && coordinate2 < m && 
            !(visited[coordinate1][coordinate2])) {
            visited[coordinate1][coordinate2] = true;
            if (matrix[coordinate1][coordinate2] != 0) {
                st.push(new int[]{coordinate1, coordinate2});
            }
        }
    }
    
    public static int dfsFromVertex(int[][] matrix, int i, int j, boolean[][] visited, int n, int m) {
        Stack<int[]> st = new Stack<int[]>();
        visited[i][j] = true;
        st.push(new int[]{i, j});
        int region = 0;
        while (!(st.isEmpty())) {
            int[] coordinates = st.pop();
            region++;
            int[][] neighbors = new int[][]{new int[]{coordinates[0] - 1, coordinates[1] - 1},
                                            new int[]{coordinates[0] - 1, coordinates[1]},
                                            new int[]{coordinates[0] - 1, coordinates[1] + 1},
                                            new int[]{coordinates[0], coordinates[1] - 1},
                                            new int[]{coordinates[0], coordinates[1] + 1},
                                            new int[]{coordinates[0] + 1, coordinates[1] - 1},
                                            new int[]{coordinates[0] + 1, coordinates[1]},
                                            new int[]{coordinates[0] + 1, coordinates[1] + 1},
                                           };
            for (int[] adjacentCoordinate : neighbors) {
                pushCoordinates(matrix, visited, st, adjacentCoordinate[0], adjacentCoordinate[1], n, m);
            }
        }
        return region;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int grid[][] = new int[n][m];
        for(int grid_i=0; grid_i < n; grid_i++){
            for(int grid_j=0; grid_j < m; grid_j++){
                grid[grid_i][grid_j] = in.nextInt();
            }
        }
        System.out.println(getBiggestRegion(grid, n, m));
    }
}
