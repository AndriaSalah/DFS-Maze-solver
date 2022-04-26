package Maze_solver;

//20191266 andria salah rousdhy
import java.util.*;

public class Main {
    static Stack<Integer> count = new Stack<>();
    static int counter;
    static boolean multipath =true ;
    static ArrayList<String> path = new ArrayList<>();
    static ArrayList<String> full_path = new ArrayList<>();
    static String[][] maze = {
            {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},
            {"0", "0", "#", "0", "0", "#", "#", "#", "#", "#"},
            {"#", "0", "0", "0", "#", "#", "0", "0", "0", "#"},
            {"#", "#", "0", "#", "#", "#", "0", "#", "#", "#"},
            {"#", "0", "0", "0", "0", "0", "0", "#", "#", "#"},
            {"#", "0", "#", "#", "#", "#", "0", "#", "#", "#"},
            {"#", "0", "#", "#", "#", "#", "0", "#", "#", "#"},
            {"#", "0", "#", "1", "0", "0", "0", "0", "0", "#"},
            {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"}
    };
    static boolean[][] visited = new boolean[maze.length][maze.length];


    static int x = 1, y = 0;

    enum Direction {
        RIGHT,
        DOWN,
        LEFT,
        UP
    }

    static Stack<Integer> stack = new Stack<Integer>();


    public static void main(String[] args) {
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited.length; j++) {
                visited[i][j] = false;
            }

        }
        dir();
        for (int i = 0; i <path.size() ; i++) {
            System.out.print("path : ");
            System.out.println(path.get(i) +"");
        }
        System.out.println();
        for (int i = 0; i <full_path.size() ; i++) {
            System.out.print("full path : ");
            System.out.println(full_path.get(i)+"");
        }
    }

    public static boolean chckgoal() {
        if (maze[x][y].equals("1")) {
            path.add("goal found at :" + "(" + x + "," + y + ")");
            full_path.add("goal found at :" + "(" + x + "," + y + ")");
            return true;
        }
        return false;
    }

    public static boolean checkwall() {
        if (maze[x][y].equals("#")) {
            System.out.println("(" + x + "," + y + ")" + " is a wall");
            return true;
        } else return false;
    }
    public static boolean checkwall(int x , int y) {
        if (maze[x][y].equals("#")) {
            return true;
        } else return false;
    }


    public static boolean move(Direction action) {
        switch (action) {
            case UP:
                if(x-1>0) {
                    if (!visited[x - 1][y]) {
                        if (maze[x - 1][y] != "#") {
                            visited[x][y] = true;
                            chckmultipath();
                            x--;
                            path.add("(" + x + "," + y + ")" + "path up");
                            full_path.add("(" + x + "," + y + ")" + "path up");
                            //if (multipath){
                            counter++;
                            //}
                            //System.out.println("(" + x + "," + y + ")" + "path up");
                            return true;
                        } else return false;

                    }
                }
                return false;
            case DOWN:
                if(x+1< maze.length) {
                    if (!visited[x + 1][y]) {
                        if (maze[x + 1][y] != "#") {
                            visited[x][y] = true;
                            chckmultipath();
                            x++;
                            path.add("(" + x + "," + y + ")" + "path down");
                            full_path.add("(" + x + "," + y + ")" + "path down");
                            counter++;
                            // System.out.println("(" + x + "," + y + ")" + "path down");
                            return true;
                        } else return false;
                    }
                }
                return false;

            case LEFT:
                if(y-1>0) {
                    if (!visited[x][y - 1]) {
                        if (maze[x][y - 1] != "#") {
                            visited[x][y] = true;
                            chckmultipath();
                            y--;
                            path.add("(" + x + "," + y + ")" + "path left");
                            full_path.add("(" + x + "," + y + ")" + "path left");
                            counter++;
                            // System.out.println("(" + x + "," + y + ")" + "path left");
                            return true;

                        } else return false;

                    }
                }
                return false;

            case RIGHT:
                if (y+1 < maze.length){
                    if (!visited[x][y + 1]){
                        if (maze[x][y + 1] != "#") {
                            visited[x][y] = true;
                            chckmultipath();
                            y++;
                            path.add("(" + x + "," + y + ")" + "path right");
                            full_path.add("(" + x + "," + y + ")" + "path right");
                                counter ++;
                           // System.out.println("(" + x + "," + y + ")" + "path right");
                            return true;
                        } else return false;
                    }
                }
                return false;
        }
        return false;
    }

    public static boolean dir() {
        if (chckgoal()) {
            return true;
        }
        if (move(Direction.RIGHT)) {
            dir();
        } else if (move(Direction.DOWN)) {
            dir();
        } else if (move(Direction.LEFT)) {
            dir();
        } else if (move(Direction.UP)) {
            dir();
        } else {
            goback();
            dir();
        }

        return false;
    }

    public static void chckmultipath() {

        if (x - 1 < 0 || x + 1 >= maze.length || y - 1 < 0 || y + 1 >= maze.length) {
            return;
        } else {
            if (!visited[x][y + 1] && !visited[x][y - 1] && !checkwall(x, y + 1) && !checkwall(x, y - 1)) {
                stack.add(x);
                stack.add(y);
                if (multipath) {
                    count.add(counter);
                    counter = 0;
                }


            } else if (!visited[x][y + 1] && !visited[x + 1][y] && !checkwall(x, y + 1) && !checkwall(x + 1, y)) {
                stack.add(x);
                stack.add(y);
                if (counter > 0) {
                    count.add(counter);
                    counter = 0;
                }

            } else if (!visited[x][y + 1] && !visited[x - 1][y] && !checkwall(x, y + 1) && !checkwall(x - 1, y)) {
                stack.add(x);
                stack.add(y);
                if (counter > 0) {
                    count.add(counter);
                    counter = 0;
                }

            } else if (!visited[x + 1][y] && !visited[x][y - 1] && !checkwall(x, y - 1) && !checkwall(x + 1, y)) {
                stack.add(x);
                stack.add(y);
                if (counter > 0) {
                    count.add(counter);
                    counter = 0;
                }

            } else if (!visited[x + 1][y] && !visited[x - 1][y] && !checkwall(x + 1, y) && !checkwall(x - 1, y)) {
                stack.add(x);
                stack.add(y);
                if (counter > 0) {
                    count.add(counter);
                    counter = 0;
                }

            } else if (!visited[x][y + 1] && !visited[x + 1][y] && !checkwall(x, y + 1) && !checkwall(x + 1, y)) {
                stack.add(x);
                stack.add(y);
                if (counter > 0) {
                    count.add(counter);
                    counter = 0;
                }

            }
            else if (!visited[x][y - 1] && !visited[x - 1][y] && !checkwall(x, y - 1) && !checkwall(x - 1, y)) {
                stack.add(x);
                stack.add(y);
                if (counter > 0) {
                    count.add(counter);
                    counter = 0;
                }

            }

        }
    }


        public static void goback() {
        y = stack.pop();
        x = stack.pop();
        int counterr= (path.size()-counter)+1;
        for ( int i = path.size()-1;  i >=counterr; i--) {
            path.remove(i);
            }
            counter = count.pop();

    }

}
