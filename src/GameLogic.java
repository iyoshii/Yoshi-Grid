public class GameLogic {
    private char[][] grid;
    private int rows;
    private int cols;

    public GameLogic(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new char[rows][cols];
    }

    public boolean checkWin(int[][] playersMoves, int player) {
        return checkVertical(playersMoves, player) || checkHorizontal(playersMoves, player) || checkDiagonal(playersMoves, player);
    }

    public boolean checkHorizontal(int[][] playersMoves, int player) {
        for (int row = 0; row < playersMoves.length; row++) {
            for (int col = 0; col < playersMoves[row].length - 3; col++) {
                if(playersMoves[row][col] == player){
                    if(playersMoves[row][col + 1] == player){
                        if(playersMoves[row][col + 2] == player){
                            if(playersMoves[row][col + 3] == player){
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean checkVertical(int[][] playersMoves, int player) {
        for (int row = 0; row < playersMoves.length - 3; row++) {
            for (int col = 0; col < playersMoves[row].length; col++) {
                    if(playersMoves[row][col] == player){
                        if(playersMoves[row + 1][col] == player){
                            if(playersMoves[row + 2][col] == player){
                                if(playersMoves[row + 3][col] == player){
                                    return true;
                                }
                            }
                        }
                    }
                }
            }

        return false;
    }

    public boolean checkDiagonal(int[][] playersMoves, int player) {
        for (int row = 0; row < playersMoves.length - 3; row++) {
            for (int col = 0; col < playersMoves[row].length - 3; col++) {
                if(playersMoves[row][col] == player){
                    if(playersMoves[row + 1][col + 1] == player){
                        if(playersMoves[row + 2][col + 2] == player){
                            if(playersMoves[row + 3][col + 3] == player){
                                return true;
                            }
                        }
                    }
                }
                if(row >= 3 && playersMoves[row][col] == player){
                    if(playersMoves[row - 1][col + 1] == player){
                        if(playersMoves[row - 2][col + 2] == player){
                            if(playersMoves[row - 3][col + 3] == player){
                                return true;
                            }
                        }
                    }
                }
            }
        }

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

    public static void addPlayerMoves(int[][] playersMoves, int player) {
        boolean added = false;

        for (int row = 0; row < playersMoves.length; row++) {
            for (int col = 0; col < playersMoves[row].length; col++) {
                if (playersMoves[row][PlayerLogic.playerMove] == 0) {
                    if (player == 1) {
                        playersMoves[row][PlayerLogic.playerMove] = 1;
                    } else {
                        playersMoves[row][PlayerLogic.playerMove] = 2;
                    }

                    added = true;
                    break;
                }
            }

            if (added) {
                break;
            }
        }
    }
}