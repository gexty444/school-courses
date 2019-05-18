package com.example.pset2a.C7Robot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class GetPath {

    private static int counter = 0;

    //
    public static boolean getPath(int r, int c, ArrayList<Point> path, final int[][] grid) {
        if (r == 0 && c == 0) {
            Point p = new Point(0, 0);
            path.add(p);
            return true;
        }

        if (grid[r][c] == 1) return false;

        // move up
        if (r - 1 >= 0 && getPath(r - 1, c, path, grid)) {
            Point p = new Point(r, c);
            path.add(p);
            return true;
        }

        // move left
        if (c - 1 >= 0 && getPath(r, c - 1, path, grid)) {
            Point p = new Point(r, c);
            path.add(p);
            return true;
        }


        return false;
    }


//    public static boolean searchpath (int x, int y, int r, int c, ArrayList<Point> path, int[][] maze){
//        if (counter == 0){
//            // mark the final destination
//            maze[r][c] = 9;}
//
//        if (maze[y][x] == 9){
//            Point p = new Point(r,c);
//            path.add(p);
//            return true;
//        }
//
//        if (maze[y][x] == 1) return false;
//
//        if (x+1 < maze[0].length && searchpath(x+1,y,r,c,path,maze)) {
//            Point p = new Point(y,x);
//            path.add(p);
//            return true;
//        }
//
//        if (y+1 < maze.length && searchpath(x,y+1,r,c,path,maze)) {
//            Point p = new Point(y, x);
//            path.add(p);
//            return true;
//        }

//        if (maze[y][x] == 0){
//            // mark as visited
//            maze[y][x] = 2;
//
//            // move right
//            int dx = 1;
//            int dy = 0;
//            if (x+dx < maze[0].length){
//                if (searchpath(x + dx, y + dy, r,c, path,maze)) {
//                    Point p = new Point(y,x);
//                    path.add(p);
//                    return true;
//                }
//            }
//
//            // move down
//            dx = 0;
//            dy = 1;
//            if (y+dy < maze.length){
//                if (searchpath(x + dx, y + dy, r,c, path,maze)) {
//                    Point p = new Point(y,x);
//                    path.add(p);
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//
//
//    public static boolean getPath (int r, int c, ArrayList<Point> path, final int [][] grid) {
//            int[][] map = new int[grid.length][];
//            for (int i=0; i<grid.length;i++) {
//                map[i] = Arrays.copyOf(grid[i], grid[i].length);
//            }
//
//            boolean result = searchpath(0,0, r,c, path, map);
//            Collections.reverse(path);
//
//            return result;
//            }
//        }
}

    class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }



