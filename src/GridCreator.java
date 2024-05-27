public class GridCreator {
    private int rows;
    private int cols;
    private char[][] grid;

    public GridCreator(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new char[rows][cols];
        initializeGrid();
    }

    public char[][] getGrid() {
        return grid;
    }

    public int getCols() {
        return cols;
    }

    private void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    public void printGrid() {
        System.out.print("+");

        for (int j = 0; j < cols; j++) {
            System.out.print("---+");
        }

        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print("|");

            for (int j = 0; j < cols; j++) {
                System.out.print(" " + grid[i][j] + " |");
            }

            System.out.println();
            System.out.print("+");

            for (int j = 0; j < cols; j++) {
                System.out.print("---+");
            }

            System.out.println();
        }
    }
}