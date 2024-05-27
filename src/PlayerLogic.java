import java.util.ArrayList;
import java.util.Scanner;
public class PlayerLogic {
    public static Scanner scanner = new Scanner(System.in);
    public static void player1MultiMove(String username, GridCreator gridCreator, GameLogic gameLogic, ArrayList<Integer> playerMoves) {
        boolean player1ValidMove = false;

        while (!player1ValidMove) {
            System.out.println("\n" + username + ", it's your turn! Make a move on an available column (0-" + (gridCreator.getCols() - 1) + "): ");
            int player1Move = scanner.nextInt();
            scanner.nextLine();

            if (player1Move >= 0 && player1Move < gridCreator.getCols()) {
                if (gameLogic.placeMove(player1Move, 1)) {
                    playerMoves.add(player1Move);

                    gameLogic.displayGrid();
                    player1ValidMove = true;
                }
                else {
                    errorMessage();
                    scanner.nextLine();
                    gameLogic.displayGrid();
                }
            }
            else {
                errorMessage();
                scanner.nextLine();
                gameLogic.displayGrid();
            }
        }
    }

    public static void player2MultiMove(String friendUsername, GridCreator gridCreator, GameLogic gameLogic, ArrayList<Integer> playerMoves) {
        boolean player2ValidMove = false;

        while (!player2ValidMove) {
            System.out.println("\n" + friendUsername + ", it's your turn! Make a move on an available column (0-" + (gridCreator.getCols() - 1) + "): ");
            int player2Move = scanner.nextInt();
            scanner.nextLine();

            if (player2Move >= 0 && player2Move < gridCreator.getCols()) {
                if (gameLogic.placeMove(player2Move, 2)) {
                    playerMoves.add(player2Move);

                    gameLogic.displayGrid();
                    player2ValidMove = true;
                }
                else {
                    errorMessage();
                    scanner.nextLine();
                    gameLogic.displayGrid();
                }
            }
            else {
                errorMessage();
                scanner.nextLine();
                gameLogic.displayGrid();
            }
        }
    }

    public static void errorMessage() {
        clearScreen();

        System.out.println("Invalid input. Please try again.");
        System.out.println("Press [Enter] to continue.");
        for (int i = 0; i < 4; i++) {
            System.out.println();
        }

        scanner.nextLine();
    }

    public static void clearScreen() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }
}