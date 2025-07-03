import java.awt.desktop.SystemEventListener;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        runGame();
    }

    public static void runGame(){
        printWelcomeMsg();

        Random rand = new Random();

        String[][] game_board = {
                {"", "", ""},
                {"", "", "6"},
                {"7", "8", "9"}
        };

        int round = showMenuAndChose();
//        int who_play = rand.nextInt(2);
        int who_play = 0;
        while (round > 0){
            while (true) {
                printBoard(game_board, who_play);
                switch (who_play) {
                    case 0:
                        game_board = player(game_board);
                        break;
                    case 1:
//                    computer();
                        System.out.println();
                }
            }

//            round--;
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



    public static void printBoard(String[][] game_board, int who_is_play){
        if (who_is_play == 0){
            System.out.println("Player1 turn");
        }else {
            System.out.println("computer turn");
        }
        for (int i = 0; i < game_board.length; i++) {
            for (int j = 0; j < game_board[i].length; j++) {
                System.out.print(game_board[i][j]+"\t");
                if (j%5 == 0 || j%5 != 0 && j != game_board[i].length-1) {
                    System.out.print("|\t");
                }
            }
            System.out.println();
            if(i != game_board.length-1) {
                System.out.println("------------------");
            }
        }
    }


    public static String[][] player(String[][] game_board){
        Scanner scn = new Scanner(System.in);
        boolean played = true;
        while (played) {
            System.out.println("Enter where do you want to play: ");
            System.out.println("""
                    1- left up corner\t|\t4- left mid row|\t7- left down row
                    2- center up row\t|\t5- center mid row\t|\t8- center down row
                    3- right up corner\t|\t6- right mid row\t|\t9- right down row
                    """);
            try {
                int player_chose = scn.nextInt();
                switch (player_chose) {
                    case 1:
                        if (checkIsEmptyPlace(game_board, 0, 0)) {
                            game_board[0][0] = "X";
                            played = false;
                        } else {
                            System.out.println("It is not empty place you can not put your X here");
                        }
                        break;
                    case 2:
                        if (checkIsEmptyPlace(game_board, 0, 1)) {
                            game_board[0][1] = "X";
                            played = false;
                        } else {
                            System.out.println("It is not empty place you can not put your X here");
                        }
                        break;
                    case 3:
                        if (checkIsEmptyPlace(game_board, 0, 2)) {
                            game_board[0][2] = "X";
                            played = false;
                        } else {
                            System.out.println("It is not empty place you can not put your X here");
                        }
                        break;
                    case 4:
                        if (checkIsEmptyPlace(game_board, 1, 0)) {
                            game_board[1][0] = "X";
                            played = false;
                        } else {
                            System.out.println("It is not empty place you can not put your X here");
                        }
                        break;
                    case 5:
                        if (checkIsEmptyPlace(game_board, 1, 1)) {
                            game_board[1][1] = "X";
                            played = false;
                        } else {
                            System.out.println("It is not empty place you can not put your X here");
                        }
                        break;
                    case 6:
                        if (checkIsEmptyPlace(game_board, 1, 2)) {
                            game_board[1][2] = "X";
                            played = false;
                        } else {
                            System.out.println("It is not empty place you can not put your X here");
                        }
                        break;
                    case 7:
                        if (checkIsEmptyPlace(game_board, 2, 0)) {
                            game_board[2][0] = "X";
                            played = false;
                        } else {
                            System.out.println("It is not empty place you can not put your X here");
                        }
                        break;
                    case 8:
                        if (checkIsEmptyPlace(game_board, 2, 1)) {
                            game_board[2][1] = "X";
                            played = false;
                        } else {
                            System.out.println("It is not empty place you can not put your X here");
                        }
                        break;
                    case 9:
                        if (checkIsEmptyPlace(game_board, 2, 2)) {
                            game_board[2][2] = "X";
                            played = false;
                        } else {
                            System.out.println("It is not empty place you can not put your X here");
                        }
                        break;
                    default:
                        System.out.println("You must chose from menu numbers only");

                }
            }catch (Exception e){
                scn.nextLine();// empty the scanner buffer
                System.err.println("You cannot enter anything not Integer");
            }

        }

        return game_board;
    }

    public static boolean checkIsEmptyPlace(String[][] game_board, int indexi, int indexj){
        return game_board[indexi][indexj].isEmpty();
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
                        scn.nextLine();
                        return 1;
                    case 2:
                        scn.nextLine();
                        return 3;
                    case 3:
                        scn.nextLine();
                        return -1;
                }
            } catch (Exception e) {
                scn.nextLine();// empty the scanner buffer
                System.err.println("You must enter integer number");
            }
        }
    }
}