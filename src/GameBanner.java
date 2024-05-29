import java.util.Scanner;

public class GameBanner {
    public static Scanner scanner = new Scanner(System.in);

    public static void drawBanner(String username, String friendUsername){
        clearScreen();
        System.out.println("      It was a draw! " + username + " has tied with " + friendUsername + "!");
        System.out.println("                        ᕕ( ᐛ )ᕗ");
        System.out.println("             * Press [Enter] to continue *");
        scanner.nextLine();
    }

    public static void userWinBanner(String username, String friendUsername){
        clearScreen();
        System.out.println("      Congrats on winning! " + username + ", you emerged victorious against " + friendUsername + "!");
        System.out.println("                           ദ്ദി(˵ •̀ ᴗ - ˵ ) ✧");
        System.out.println("                    * Press [Enter] to continue *");
        scanner.nextLine();
    }

    public static void friendWinBanner(String username, String friendUsername){
        clearScreen();
        System.out.println("      Congrats on winning! " + friendUsername + ", you emerged victorious against " + username + "!");
        System.out.println("                           ദ്ദി(˵ •̀ ᴗ - ˵ ) ✧");
        System.out.println("                    * Press [Enter] to continue *");
        scanner.nextLine();
    }

    public static void drawAIBanner(String username){
        clearScreen();
        System.out.println("  It was a draw! " + username + ", you have tied with AI :)");
        System.out.println("                      ᕕ( ᐛ )ᕗ");
        System.out.println("           * Press [Enter] to continue *");
        scanner.nextLine();
    }

    public static void winAIBanner(String username){
        clearScreen();
        System.out.println("Congrats on winning! " + username + ", you emerged victorious against AI :)");
        System.out.println("                      ദ്ദി(˵ •̀ ᴗ - ˵ ) ✧");
        System.out.println("              * Press [Enter] to continue *");
        scanner.nextLine();
    }

    public static void loseAIBanner(String username){
        clearScreen();
        System.out.println("Oh no! " + username + ", you have lost against AI. Better luck next time :(");
        System.out.println("                       (ง ◉ _ ◉)ง");
        System.out.println("             * Press [Enter] to continue *");
        scanner.nextLine();
    }

    public static void clearScreen() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }
}