import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        runGame();
    }

    public static void runGame(){
        printWelcomeMsg();
        String[][] game_board = {
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"}
        };
        int round = showMenuAndChose();

        while (round > 0){
            printBoard(game_board);

            round--;
        }


    }

    public static void printWelcomeMsg(){

        System.out.println("""
                
                
                 __      __   _                    _         _   _      _             _           \s
                 \\ \\    / /__| |__ ___ _ __  ___  | |_ ___  | |_(_)__  | |_ __ _ __  | |_ ___  ___\s
                  \\ \\/\\/ / -_) / _/ _ \\ '  \\/ -_) |  _/ _ \\ |  _| / _| |  _/ _` / _| |  _/ _ \\/ -_)
                   \\_/\\_/\\___|_\\__\\___/_|_|_\\___|  \\__\\___/  \\__|_\\__|  \\__\\__,_\\__|  \\__\\___/\\___|
                
                
                """);

    }



    public static void printBoard(String[][] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
                if (j%5 == 0 || j%5 != 0) {
                    System.out.print("|");
                }
            }
            System.out.println();
            System.out.println("----------");
        }
    }



    //Bonus idea:
    public static int showMenuAndChose(){
        Scanner scn = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    1- one round win
                    2- three rounds win
                    3- exit
                    """);
            try {
                int user_chose = scn.nextInt();
                switch (user_chose) {
                    case 1:
                        return 1;
                    case 2:
                        return 3;
                    case 3:
                        return -1;
                }
            } catch (Exception e) {
                scn.nextLine();// empty the scanner buffer
                System.err.println("You must enter integer number");
            }
        }
    }
}