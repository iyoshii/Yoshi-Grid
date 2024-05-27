import java.util.ArrayList;
import java.util.Scanner;

public class AILogic {
    public static Scanner scanner = new Scanner(System.in);

    public static void easyAIMove(GridCreator gridCreator, GameLogic gameLogic, ArrayList<Integer> playerMoves) {
        System.out.println("\nIt is now the AI's turn. It will make a move once [Enter] is pressed.");
        scanner.nextLine();

        int aiMove;

        do {
            aiMove = (int) (Math.random() * gridCreator.getCols());
        }
        while (!gameLogic.placeMove(aiMove, 2));

        playerMoves.add(aiMove);

        System.out.println("The AI has made a move in column " + aiMove + ".");
        gameLogic.displayGrid();
    }

    public static void hardAIMove(GridCreator gridCreator, GameLogic gameLogic, int rows, int cols, ArrayList<Integer> playerMoves) {
        System.out.println("\nIt is now the AI's turn. It will make a move once [Enter] is pressed.");
        scanner.nextLine();

        int aiMove = -1;
        int minDistance = Integer.MAX_VALUE;

        for (int col = 0; col < gridCreator.getCols(); col++) {
            if (gameLogic.getColumnHeight(col) == rows) {
                continue;
            }

            boolean canBlockPlayer = false;
            for (int row = 0; row < rows; row++) {
                if (gameLogic.checkHorizontal(playerMoves)){
                    canBlockPlayer = true;
                    break;
                }
                if (gameLogic.checkVertical(playerMoves, cols)) {
                    canBlockPlayer = true;
                    break;
                }
                if (gameLogic.checkDiagonal(playerMoves)) {
                    canBlockPlayer = true;
                    break;
                }
            }

            if (canBlockPlayer) {
                int distance = calculateDistanceToPlayerMoves(col, playerMoves);
                if (distance < minDistance) {
                    minDistance = distance;
                    aiMove = col;
                }
            }
        }

        if (aiMove == -1) {
            for (int col = 0; col < gridCreator.getCols(); col++) {
                if (gameLogic.getColumnHeight(col) == rows) {
                    continue;
                }
                int distance = calculateDistanceToPlayerMoves(col, playerMoves);
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
        playerMoves.add(aiMove);

        System.out.println("The AI has made a move in column " + aiMove + ".");
        gameLogic.displayGrid();
    }

    private static int calculateDistanceToPlayerMoves(int column, ArrayList<Integer> playerMoves) {
        int distance = 0;
        for (int playerMove : playerMoves) {
            distance += Math.abs(column - playerMove);
        }
        return distance;
    }
}