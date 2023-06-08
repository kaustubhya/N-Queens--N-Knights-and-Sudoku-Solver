public class Sudoku_Solver {

    public static void main(String[] args) {
        int [][] board = new int [][] { 
            {3, 0, 6, 5, 0, 8, 4, 0, 0},
            {5, 2, 0, 0, 0, 0, 0, 0, 0},
            {0, 8, 7, 0, 0, 0, 0, 3, 1},
            {0, 0, 3, 0, 1, 0, 0, 8, 0},
            {9, 0, 0, 8, 6, 3, 0, 0, 5},
            {0, 5, 0, 0, 9, 0, 6, 0, 0}, 
            {1, 3, 0, 0, 0, 0, 2, 5, 0},
            {0, 0, 0, 0, 0, 0, 0, 7, 4},
            {0, 0, 5, 2, 0, 6, 3, 0, 0} 
        };

        if(solve(board)) {
            // print the solution
            display(board);
        }

        else {
            System.out.println("Cannot solve this sudoku");
        }
    }


    static boolean solve(int[][] board) {
        int n = board.length;

        // initial row and col
        int row = -1;
        int col = -1;

        boolean emptyLeft = true; // has zeros
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n ; j++) {
                if(board[i][j] == 0) {
                    // replacing the initial row and cols with curr row and curr cols
                    row = i;
                    col = j;
                    emptyLeft = false; // that particular zero gone
                    break;
                }
            }

            // if we found some empty element in the row then we should break out of that
            //  recursion loop, this may be possible when we filled in all nos and there is
            //  some discrepancies in our sudoku solution. Hence we should break out and try again
             if(!emptyLeft) {
                break;
            }
        }

        if(emptyLeft) {
            // i.e. no empty elements found
            return true;
            // i.e. sudoku is solved
        }

        // backtrack
        for(int number = 1; number <= 9; number++) {
            if(isSafe(board, row, col, number)) {
                board[row][col] = number;
                if(solve(board)) {
                    // declare that sudoku is solved (temporarily/ permanently)
                    return true;
                }
                else {
                   // backtrack by replacing that number with zero
                   board[row][col] = 0;
                }
            }
        }
// after backtracking, the sudoku which was declared temporarily solved, becomes unsolved
        return false;
    }

    private static void display(int[][] board) {
       for(int[] row : board) {
          for(int num : row) {
             System.out.print(num + " ");
          }
          System.out.println();
       }
    }



    static boolean isSafe(int [][] board, int row, int col, int num) {
        // check the row
        for(int i = 0; i < board.length; i++) {
            // check if the number is in the row
            if(board[row][i] == num) {
                return false;
                 
            }
        }

        // check the col
        for (int i = 0; i < board.length; i++) {
            // check if the number is in the col
            if (board[i][col] == num) {
                return false;
            }
        }

        int sqrt = (int) (Math.sqrt(board.length));
        int rowStart = row - row % sqrt;
        int colStart = col - col % sqrt;


        for(int r = rowStart; r < rowStart + sqrt; r++) { // 3X3 matrix traversal condition
           for(int c = colStart; c < colStart + sqrt; c++) {
               if(board[r][c] == num) {
                return false; 
               }
            } 
        }
// if all is well
        return true;
    }
    
}
