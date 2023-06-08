public class N_Queens {

    public static void main(String[] args) {
        int n = 4; //board size nxn
        boolean[][] board = new boolean [n][n];
       System.out.println(queens(board, 0)); // start from 0 row i.e. 0,0
    }

    static int queens(boolean[][] board, int row) {
        if(row == board.length) {
            display(board);
            System.out.println();
            return 1;
            // base condition
        }



    // tells us the no. of solutions
        int count = 0;

        // placing the queen and checking for every row and column
        for(int col = 0; col < board.length; col++) {
            // place the queen if it is safe
            if(isSafe(board, row, col)) {
               // if it is safe the set this line to true
                board[row][col] = true;

                // go to next row and use this method recusively
                count += queens(board, row + 1);

                // When function call is returned, backtracking statement
                board[row][col] = false;
            }
        }

        return count;
    }

    private static boolean isSafe(boolean[][] board, int row, int col) {
        // check previous vertical row 
        for (int i = 0; i < row; i++) {
           // Checks the same columns of the previous row for queens, if they find any queens in range then it returns false
            if(board[i][col]) {
                return false;
            }
        }

        // diagonal left column of the previous row
        int maxLeft = Math.min(row, col);
        for (int i = 1; i <= maxLeft; i++) {
            if(board[row - i][col - i]) {
                return false;
            }
        }

        // diagonal right columns of the previous rows
        int maxRight = Math.min(row, board.length - col - 1);
        for(int i = 1; i <= maxRight; i++) {
            if(board[row - i][col + i]) { 
                return false;
            }
        }
// If all conditions are met then all is safe i.e. true, ∴ place the queen
        return true; 
    } 

    private static void display(boolean[][] board) {
        for(boolean[] row : board) {
            for(boolean element : row) {
                if (element) {
                    System.out.print("Q ");
                }
                else {
                    System.out.print("X ");
                }
            }
           // Go to next row
            System.out.println();
        }
    }
    
}