import java.util.ArrayList;

public class GameLogic {
    private char[][] grid;
    private int rows;
    private int cols;

    public GameLogic(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new char[rows][cols];
    }

    public boolean checkWin(ArrayList <Integer> playerMoves, int cols) {
        return checkHorizontal(playerMoves) || checkVertical(playerMoves, cols) || checkDiagonal(playerMoves);
    }

    public boolean checkHorizontal(ArrayList<Integer> playerMoves) {
        return false;
    }

    public boolean checkVertical(ArrayList <Integer> playerMoves, int cols) {
        int[] p1Counts = new int[cols]; // Keep track of the count for each column for Player 1
        int[] p2Counts = new int[cols]; // Keep track of the count for each column for Player 2

        for (int i = 0; i < playerMoves.size(); i++) {
            int column = playerMoves.get(i);

            if (i % 2 == 0) { // Player 1's turn
                p1Counts[column]++;

                if (p1Counts[column] >= 4) {
                    return true; // Player 1 wins
                }
            } else { // Player 2's turn
                p2Counts[column]++;

                if (p2Counts[column] >= 4) {
                    return true; // Player 2 wins
                }
            }
        }

        return false; // No winner
    }

    public boolean checkDiagonal(ArrayList <Integer> playerMoves){
        return false;
    }

    public boolean checkForDraw(char[][] grid, int totalMoves) {
        int rows = grid.length;
        int cols = grid[0].length;
        int totalCells = rows * cols;

        return totalMoves == totalCells;
    }

    public int getColumnHeight(int column) {
        int height = 0;
        for (int row = 0; row < rows; row++) {
            if (grid[row][column] != 0) {
                height++;
            }
        }
        return height;
    }

    public boolean placeMove(int column, int player) {
        for (int i = rows - 1; i >= 0; i--) {
            if (grid[i][column] == 0) {
                grid[i][column] = (char) player;
                return true;
            }
        }
        return false;
    }

    public void displayGrid() {
        System.out.print("+");
        System.out.print("---+".repeat(cols));
        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print("|");

            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    System.out.print("   |");
                } else if (grid[i][j] == 1) {
                    System.out.print(" O |");
                } else {
                    System.out.print(" X |");
                }
            }

            System.out.println();
            System.out.print("+");
            System.out.print("---+".repeat(cols));
            System.out.println();
        }
    }
}