public class Solution {
    static int rows;
    static int cols;
    static int levels;

    static boolean[][][] visited; //grid to hold visited information
    static int[][][] paths; //grid to represent labyrinth as walls and free cells

    static int[] start; // array to hold coordinates of start-cell
    static int[] end; //array to hold coordinates of end-cell

    public static void main(String args[] ) throws Exception {
        BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));


        rows = Integer.valueOf(reader.readLine());  //first is rows
        cols = Integer.valueOf(reader.readLine()); //second is cols
        levels = Integer.valueOf(reader.readLine()); //third is levels

        paths = new int[levels][rows][cols];
        visited = new boolean[levels][rows][cols];

        start = new int[3];
        end = new int[3];

        //construct the grid as a 3-d array
        for(int k = 0; k < levels; k++){ //for each level
            for(int i = 0; i < rows; i++){ //for each row
                for(int j = 0; j< cols; j++){ //for each column

                    //here is where EOF is sometimes found and value is set to null(don't know why)
                    String value = reader.readLine(); //read next line of input

                    if(value.equals("o")){ //if a column, place -1 on the path grid
                        paths[k][i][j] = -1;

                    } else if(value.equals(".")){ //if a dot, place 0 on path grid
                        paths[k][i][j] = 0;
                        visited[k][i][j] = false; //set visited to false

                    } else if(value.equals("1")){ //if starting point, fill start array
                        start[0] = k;
                        start[1] = i;
                        start[2] = j;
                        visited[k][i][j] = true; //set visited to true
                        paths[k][i][j] = 0;

                    } else if(value.equals("2")){ //if end point, fill end array
                        end[0] = k;
                        end[1] = i;
                        end[2] = j;
                        paths[k][i][j] = 0;
                    }
                }
            }
        }
        reader.close(); //close reader from stdin

        //solve shortest path
        int path = findShortestPath(start[1], start[2], start[0], end[1], end[2], end[0], Integer.MAX_VALUE, 0);
        System.out.println(path);
    }

    /*
     * helper to check if the next move will be within the bounds of the grid, not a column, and not visited
     */
    public static boolean validMove(int lvl, int row, int col){
        if(lvl < levels && lvl > -1 && row > -1 && row < rows && col > -1 && col < cols){
            if(paths[lvl][row][col] != -1 && visited[lvl][row][col] != true){
                return true; //if within bounds and not a column or visited
            } else {
                return false; //if within bounds but a column or visited
            }
        } else {
            return false; //if not within bounds
        }
    }


    public static int findShortestPath(int curr_row, int curr_col, int curr_lvl, int dest_row, int dest_col, int dest_lvl, int s_path, int path){

        //base case, reached princess
        if(curr_lvl == dest_lvl && curr_row == dest_row && curr_col == dest_col){
            return Integer.min(path, s_path); //return smallest between shortest path and curr path
        }

        //updated visited array
        visited[curr_lvl][curr_row][curr_col] = true;

        //recurse in each valid direction

        //south direction
        if(validMove(curr_lvl, curr_row+1, curr_col)){
            s_path = findShortestPath(curr_row+1, curr_col, curr_lvl, dest_row, dest_col, dest_lvl, s_path, path+5);
        }

        //north direction
        if(validMove(curr_lvl, curr_row-1, curr_col)){
            s_path = findShortestPath(curr_row-1, curr_col, curr_lvl, dest_row, dest_col, dest_lvl, s_path, path+5);
        }

        //east direction
        if(validMove(curr_lvl, curr_row, curr_col+1)){
            s_path = findShortestPath(curr_row, curr_col+1, curr_lvl, dest_row, dest_col, dest_lvl, s_path, path+5);
        }

        //west direction
        if(validMove(curr_lvl, curr_row, curr_col-1)){
            s_path = findShortestPath(curr_row, curr_col-1, curr_lvl, dest_row, dest_col, dest_lvl, s_path, path+5);
        }

        //down direction (level below)
        if(validMove(curr_lvl+1, curr_row, curr_col)){
            s_path = findShortestPath(curr_row, curr_col, curr_lvl+1, dest_row, dest_col, dest_lvl, s_path, path+5);
        }

        visited[curr_lvl][curr_row][curr_col] = false; //reset cell as not visited for other recursions to traverse through
        return s_path;

    }


}
