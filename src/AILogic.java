import java.util.Scanner;

public class AILogic {
    public static Scanner scanner = new Scanner(System.in);

    public static void easyAIMove(GridCreator gridCreator, GameLogic gameLogic) {
        System.out.println("\nIt is now the AI's turn. It will make a move once [Enter] is pressed.");
        scanner.nextLine();

        int aiMove;

        do {
            aiMove = (int) (Math.random() * gridCreator.getCols());
        }
        while (!gameLogic.placeMove(aiMove, 2));

        PlayerLogic.playerMove = aiMove;
        GameLogic.addPlayerMoves(main.playersMoves, 2);

        System.out.println("The AI has made a move in column " + aiMove + ".");
        gameLogic.displayGrid();
    }

    public static void hardAIMove(GameLogic gameLogic, int rows, int cols, int[][] playersMoves) {
        System.out.println("\nIt is now the AI's turn. It will make a move once [Enter] is pressed.");
        scanner.nextLine();

        int aiMove = -1;
        int minDistance = Integer.MAX_VALUE;

        for (int col = 0; col < cols; col++) {
            if (gameLogic.getColumnHeight(col) == rows) {
                continue;
            }

            boolean canBlockPlayer = false;
            for (int row = 0; row < rows; row++) {
                if (gameLogic.checkHorizontal(playersMoves, 2)) {
                    canBlockPlayer = true;
                    break;
                }
                if (gameLogic.checkVertical(playersMoves, 2)) {
                    canBlockPlayer = true;
                    break;
                }
                if (gameLogic.checkDiagonal(playersMoves, 2)) {
                    canBlockPlayer = true;
                    break;
                }
            }

            if (canBlockPlayer) {
                int distance = calculateDistanceToPlayerMoves(col, playersMoves);
                if (distance < minDistance) {
                    minDistance = distance;
                    aiMove = col;
                }
            }
        }

        if (aiMove == -1) {
            for (int col = 0; col < cols; col++) {
                if (gameLogic.getColumnHeight(col) == rows) {
                    continue;
                }
                int distance = calculateDistanceToPlayerMoves(col, playersMoves);
                if (distance < minDistance) {
                    minDistance = distance;
                    aiMove = col;
                }
            }
        }

        if (aiMove == -1) {
            return;
        }

        gameLogic.placeMove(aiMove, 2);

        PlayerLogic.playerMove = aiMove;
        GameLogic.addPlayerMoves(main.playersMoves, 2);

        System.out.println("The AI has made a move in column " + aiMove + ".");
        gameLogic.displayGrid();
    }

    private static int calculateDistanceToPlayerMoves(int column, int[][] playersMoves) {
        int distance = 0;
        for (int row = 0; row < playersMoves.length; row++) {
            for (int col = 0; col < playersMoves[row].length; col++) {
                if (playersMoves[row][col] != 0 && playersMoves[row][col] != 2) {
                    distance += Math.abs(column - col);
                }
            }
        }
        return distance;
    }
}