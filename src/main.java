import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class main {
    public static Scanner scanner = new Scanner(System.in);
    private static int rows;
    private static int cols;
    private static int totalMoves;
    private static ArrayList <Integer> playerMoves = new ArrayList<>();

    public static void main(String[] args) {
        String fileName = "invalidInput.txt";
        String username = welcomeUsername(fileName);
        loop(fileName, username);
    }

    public static String welcomeUsername(String fileName) {
        while (true) {
            //welcome banner
            System.out.println("             .--------------------------------.");
            System.out.println("             |    Welcome to Yoshi's Grid!    |");
            System.out.println("             '--------------------------------'");
            System.out.println("     * It is recommended to play the game in full screen! *\n");

            //username
            System.out.print("Player, please enter your username to proceed: ");
            String username = scanner.nextLine();

            //validate username
            if (validateInput(fileName, username)) {
                errorMessage();
            }
            else {
                clearScreen();
                return username;
            }
        }
    }

    public static void mainMenu(String fileName, String username) {
        boolean validMainMenuInput = false;

        while (!validMainMenuInput) {
            //main menu
            System.out.println("          ~ Main Menu ~");
            System.out.println(".--------------------------------.");
            System.out.println("|      1: Play with an AI        |");
            System.out.println("|      2: Play with a friend     | ");
            System.out.println("|      3: Exit the program       |");
            System.out.println("'--------------------------------'");

            //selection
            System.out.println("* You may select [1] to play with AI or [2] to play with a friend, and [3] to exit the program *\n");
            System.out.print(username + ", please enter your choice: ");
            String mainMenuChoice = scanner.nextLine();

            if(mainMenuChoice.equals("1")){
                validMainMenuInput = true;
                clearScreen();
                AIMenu(username);

            }
            else if(mainMenuChoice.equals("2")){
                validMainMenuInput = true;
                clearScreen();
                playerMenu(fileName, username);

            }
            else if(mainMenuChoice.equals("3")){
                return;
            }
            else{
                errorMessage();
            }
        }
    }

    public static void endProgram(String fileName, String username){
        boolean endProgramInput = false;

        while(!endProgramInput){
            clearScreen();

            System.out.println(username + ", do you want to quit the program?");
            System.out.print("Please enter [Y] for yes or [N] for no: ");
            String endOfProgramChoice = scanner.nextLine();

            if(endOfProgramChoice.equals("y")||endOfProgramChoice.equals("Y")){
                endProgramInput = true;
                clearScreen();
                System.out.println("End of program. " + username + ", we hope you had fun playing Yoshi's Grid! :)");
            }
            else if(endOfProgramChoice.equals("n")||endOfProgramChoice.equals("N")){
                clearScreen();
                mainMenu(fileName, username);
            }
            else{
                errorMessage();
            }
        }
    }

    public static void AIMenu(String username){
        boolean validAIMenuInput = false;

        while(!validAIMenuInput){
            System.out.println("         ~ AI Menu ~");
            System.out.println(".--------------------------.");
            System.out.println("|      1: Easy mode        |");
            System.out.println("|      2: Hard mode        | ");
            System.out.println("'--------------------------'");

            System.out.println("* You may select [1] to play in Easy mode or [2] to play in Hard mode *\n");
            System.out.print(username + ", please enter your choice: ");
            String AIMenuChoice = scanner.nextLine();

            if(AIMenuChoice.equals("1")){
                validAIMenuInput = true;
                clearScreen();
                easyAIMenu(username);
            }
            else if(AIMenuChoice.equals("2")){
                validAIMenuInput = true;
                clearScreen();
                hardAIMenu(username);
            }
            else{
                errorMessage();
            }
        }
    }

    public static void easyAIMenu(String username){
        System.out.println("    * " + username + ", welcome to [Easy] mode! *");
        System.out.println("   ⠀⠀      ⢀⡤⠦⢤⣠⠖⠶⣄⠀⠀⠀⠀⠀⠀   ⠀⠀⠀  \n" +
                           "⠀ ⠀⠀⠀⠀⠀⠀⠀⢠⠏⢀⠤⠤⣽⣤⠚⠺⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   \n" +
                           "⠀ ⠀⠀⠀⠀⠀⠀⠀⢸⢠⠋⠀⠀⠈⢇⢀⠀⢸⣄⣠⣤⣤⣀⡀⠀⠀⠀⠀   \n" +
                           "⠀ ⠀⠀⠀⠀⠀⢀⣤⠟⡞⠀⣴⣆⠀⢸⠿⠓⠉⠁⠀⠀⣀⡈⢉⡳⣄⠀⠀   \n" +
                           "⠀ ⠀⠀⠀⠀⢸⠁⣸⠀⠰⠄⣹⣏⠜⠁⠀⠀⠀⠀⠀⠀⠁⠈⠀⠂⠈⢷⠀   \n" +
                           "⠀ ⠀⠀⠀⠀⠘⡞⣁⡠⠄⣀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⡇     ");
        System.out.println("Please press [Enter] to proceed to customising your grid size.");
        scanner.nextLine();

        createGrid(username);
        scanner.nextLine();

        GridCreator gridCreator = new GridCreator(rows, cols);
        GameLogic gameLogic = new GameLogic(rows, cols);
        easyAIAlgorithm(username, gridCreator, gameLogic);
    }

    public static void hardAIMenu(String username){
        System.out.println("    * " + username + ", welcome to [Hard] mode! *");
        System.out.println("  ⠀⠀         ⣀⣀⡰⣋⠉⠲⡄                \n" +
                           "⠀⠀⠀⠀⠀⠀⠀⠀   ⡼⠶⢬⡏⠉⠙⢦⡘⡄               \n" +
                           "⠀⠀⠀⠀⠀⠀⠀⠀   ⣇⠀⣤⡇⠀⠀⣶⣷⠈⢲⠒⠲            \n" +
                           "⠀⠀⠀⠀⠀⠀⢀⡠⠴⠒⠚⠒⠚⠻⢄⡀⢙⠿⠂⠀⠑⠂⢇⡀          \n" +
                           "⠀⠀⠀⠀ ⡴⠋⠀⡠⠄⠀⠀⠀⠀⠀⠈⠃⠀⠀⠀⠀⠀⠀⢯⠑⡆⠀⠀⢠⠒⡦⠀⡀\n" +
                           "⠀⠀⠀ ⡼⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡜⢿⠙⢦⡼⠠⡷⠋⡹");
        System.out.println("Please press [Enter] to proceed to customising your grid size.");
        scanner.nextLine();

        createGrid(username);
        scanner.nextLine();

        GridCreator gridCreator = new GridCreator(rows, cols);
        GameLogic gameLogic = new GameLogic(rows, cols);
        hardAIAlgorithm(username, gridCreator, gameLogic);
    }

    public static void playerMenu(String fileName, String username){
        System.out.println("        * " + username + " and Player 2, welcome to the [Multiplayer Menu]! *");
        System.out.println("  ⠀⠀      ⢀⡤⠦⢤⣠⠖⠶⣄⠀⠀⠀⠀⠀⠀   ⠀⠀⠀       ⠀⠀⠀⠀⠀⠀⠀  ⣀⣀⡰⣋⠉⠲⡄\n" +
                           "⠀⠀⠀⠀⠀⠀⠀⠀⢠⠏⢀⠤⠤⣽⣤⠚⠺⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀        ⠀⠀⠀⠀⠀⠀  ⡼⠶⢬⡏⠉⠙⢦⡘⡄\n" +
                           "⠀⠀⠀⠀⠀⠀⠀⠀⢸⢠⠋⠀⠀⠈⢇⢀⠀⢸⣄⣠⣤⣤⣀⡀⠀⠀⠀⠀                ⣇⠀⣤⡇⠀⠀⣶⣷⠈⢲⠒⠲\n" +
                           "⠀⠀⠀⠀⠀⠀⢀⣤⠟⡞⠀⣴⣆⠀⢸⠿⠓⠉⠁⠀⠀⣀⡈⢉⡳⣄⠀⠀        ⠀⠀⢀⡠⠴⠒⠚⠒⠚⠻⢄⡀⢙⠿⠂⠀⠑⠂⢇⡀\n" +
                           "⠀⠀⠀⠀⠀⢸⠁⣸⠀⠰⠄⣹⣏⠜⠁⠀⠀⠀⠀⠀⠀⠁⠈⠀⠂⠈⢷⠀        ⠀⡴⠋⠀⡠⠄⠀⠀⠀⠀⠀⠈⠃⠀⠀⠀⠀⠀⠀⢯⠑⡆⠀⠀⢠⠒⡦⠀⡀\n" +
                           "⠀⠀⠀⠀⠀⠘⡞⣁⡠⠄⣀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⡇        ⡼⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡜⢿⠙⢦⡼⠠⡷⠋⡹\n" +
                           "⠀⠀⠀⠀⣠⢺⠋⠀⠀⠀⠀⠁⢢⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇        ⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⢸⢆⠀⢷⠈⠑⠀⠈⠿⠒⢲");

        String friendUsername = friendUsername(fileName);

        System.out.println("Please press [Enter] to proceed to customising your grid size.");
        scanner.nextLine();

        createGrid(username);
        scanner.nextLine();

        GridCreator gridCreator = new GridCreator(rows, cols);
        GameLogic gameLogic = new GameLogic(rows, cols);
        playerAlgorithm(username, friendUsername, gridCreator, gameLogic);
    }

    public static String friendUsername(String fileName){
        while(true){
            System.out.print("Player 2, please enter your username to proceed: ");
            String friendUsername = scanner.nextLine();

            //validate username
            if (validateInput(fileName, friendUsername)) {
                errorMessage();
            }
            else {
                clearScreen();
                return friendUsername;
            }
        }
    }

    public static void createGrid(String username){
        System.out.println("~ Grid Creator ~");
        System.out.println("* Note that all dimensions must be between 4 and 16, inclusive *\n");

        while(true) {
            System.out.println(username + ", please enter the desired amount of rows for the grid: ");
            rows = scanner.nextInt();

            if (rows >= 4 && rows <= 16) {
                break;
            }
            else {
                errorMessage();
                scanner.nextLine();
            }
        }

        while (true) {
            System.out.println("\n" + username + ", please enter the desired amount of columns for the grid: ");
            cols = scanner.nextInt();

            if (cols >= 4 && cols <= 16) {
                break;
            }
            else {
                errorMessage();
                scanner.nextLine();
            }
        }

        scanner.nextLine();
        System.out.println("\nThe " + rows + "x" + cols + " grid is now printing, please press [Enter] to continue to the game.");
        scanner.nextLine();

        GridCreator gridCreator = new GridCreator(rows, cols);
        gridCreator.printGrid();
    }

    public static void easyAIAlgorithm(String username, GridCreator gridCreator, GameLogic gameLogic){
        boolean gameEnded = false;

        while (!gameEnded) {
            PlayerLogic.player1MultiMove(username, gridCreator, gameLogic, playerMoves);
            totalMoves++;

            if (gameLogic.checkWin(playerMoves, cols)) {
                GameBanner.winAIBanner(username, playerMoves);
                gameEnded = true;
            }
            else if (gameLogic.checkForDraw(gridCreator.getGrid(), totalMoves)) {
                GameBanner.drawAIBanner(username, playerMoves);
                gameEnded = true;
            }
            else {
                AILogic.easyAIMove(gridCreator, gameLogic, playerMoves);
                totalMoves++;

                if (gameLogic.checkWin(playerMoves, cols)) {
                    GameBanner.loseAIBanner(username, playerMoves);
                    gameEnded = true;
                }
                else if (gameLogic.checkForDraw(gridCreator.getGrid(), totalMoves)) {
                    GameBanner.drawAIBanner(username, playerMoves);
                    gameEnded = true;
                }
            }
        }
    }

    public static void hardAIAlgorithm(String username, GridCreator gridCreator, GameLogic gameLogic){
        boolean gameEnded = false;

        while (!gameEnded) {
            PlayerLogic.player1MultiMove(username, gridCreator, gameLogic, playerMoves);
            totalMoves++;

            if (gameLogic.checkWin(playerMoves, cols)) {
                GameBanner.winAIBanner(username, playerMoves);
                gameEnded = true;
            }
            else if (gameLogic.checkForDraw(gridCreator.getGrid(), totalMoves)) {
                GameBanner.drawAIBanner(username, playerMoves);
                gameEnded = true;
            }
            else {
                AILogic.hardAIMove(gridCreator, gameLogic, rows, cols, playerMoves);
                totalMoves++;

                if (gameLogic.checkWin(playerMoves, cols)) {
                    GameBanner.loseAIBanner(username, playerMoves);
                    gameEnded = true;
                }
                else if (gameLogic.checkForDraw(gridCreator.getGrid(), totalMoves)) {
                    GameBanner.drawAIBanner(username, playerMoves);
                    gameEnded = true;
                }
            }
        }
    }

    public static void playerAlgorithm(String username, String friendUsername, GridCreator gridCreator, GameLogic gameLogic) {
        boolean gameEnded = false;

        while (!gameEnded) {
            PlayerLogic.player1MultiMove(username, gridCreator, gameLogic, playerMoves);
            totalMoves++;

            if (gameLogic.checkWin(playerMoves, cols)) {
                GameBanner.userWinBanner(username, friendUsername, playerMoves);
                gameEnded = true;
            }
            else if (gameLogic.checkForDraw(gridCreator.getGrid(), totalMoves)) {
                GameBanner.drawBanner(username, friendUsername, playerMoves);
                gameEnded = true;
            }
            else {
                PlayerLogic.player2MultiMove(friendUsername, gridCreator, gameLogic, playerMoves);
                totalMoves++;

                if (gameLogic.checkWin(playerMoves, cols)) {
                    GameBanner.friendWinBanner(username, friendUsername, playerMoves);
                    gameEnded = true;
                }
                else if (gameLogic.checkForDraw(gridCreator.getGrid(), totalMoves)) {
                    GameBanner.drawBanner(username, friendUsername, playerMoves);
                    gameEnded = true;
                }
            }
        }
    }

    public static boolean validateInput (String fileName, String username){
        File file = new File(fileName);
        Scanner fileScanner;

        try {
            fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

                if (line.equalsIgnoreCase(username)) {
                    fileScanner.close();
                    return true;
                }
            }

            fileScanner.close();
            return false;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
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

    public static void loop(String fileName, String username){
        mainMenu(fileName, username);
        endProgram(fileName, username);
    }
}