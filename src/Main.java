import calculator.Calculator;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    static boolean player2;
    public static void main(String[] args) throws Exception {
        //this will run the XO game
        runTicTacToe();

        // if you run both and exit from x/o you will start calculator

        //Calculator functionality in another folder /calculator:
        //this will run calculator:

//        runCalculator();

    }


    public static void runTicTacToe() throws Exception {
        printMsg("welcome");

        Random rand = new Random();

        String[][] game_board = generateGameBoard();
        // default variables
        int round = showMenuAndChose();
        int who_play;
        int score_p1 = 0;
        int score_computer = 0;
        //while loop to make how many times you want to play
        while (round > 0){
            who_play = rand.nextInt(2);
            System.out.println("round will start");
            //sleep to make you ready for game :)
            TimeUnit.SECONDS.sleep(2);

            //the round will start here
            int result = rounds(game_board, who_play);

            //checks the result for the round
            if (result == 0){
                score_p1++;
            }else {
                score_computer++;
            }

            //check the result out of three rounds
            if(score_p1 >= 2){
                printMsg("winner_p1");
                break;
            }else if(score_computer >= 2){
                printMsg("winner_computer");
                break;
            }else {
                game_board = generateGameBoard();
                round--;
            }
        }
    //infinity game-run until you enter exit from menu
        if (round != -1) {
            runTicTacToe();
        }
    }


    public static int rounds(String[][] game_board, int who_play){
        while (true) {
            //who will play player or computer (Random from runGame())
            switch (who_play) {
                case 0:
                    game_board = player(game_board, true, "X");
                    break;
                case 1:
                    if (player2) {
                        game_board = computer(game_board, true);
                    }else {
                        game_board = player(game_board, true, "O");
                    }
                    break;
            }
            //check is there a winner?
            if (checkIsWinner(game_board) && who_play == 0){
                printBoard(game_board, who_play, "X");
                printMsg("winner_p1");
                player2 = false;
                break;
            }else if (checkIsWinner(game_board) && who_play == 1){
                printBoard(game_board, who_play, "O");
                if (player2) {
                    printMsg("winner_computer");
                }else {
                    printMsg("winner_p2");
                }
                player2 = false;
                break;
            }
            //swap between player every turn
            if (who_play == 0){
                who_play = 1;
            }else {
                who_play = 0;
            }
            if (checkIsDraw(game_board)){
                printMsg("draw");
                game_board = generateGameBoard();
            }
        }
        return who_play;

    }

    public static boolean checkIsDraw(String[][] game_board){
        for (int i = 0; i < game_board.length; i++) {
            for (int j = 0; j < game_board[i].length; j++) {
                if (game_board[i][j].equals(".")){
                return false;
                }
            }
        }
        return true;
    }

    // joyful messages
    public static void printMsg(String msg){
        switch (msg) {
            case "welcome":
                System.out.println("""
                    
                     __      __   _                    _         _   _      _             _           \s
                     \\ \\    / /__| |__ ___ _ __  ___  | |_ ___  | |_(_)__  | |_ __ _ __  | |_ ___  ___\s
                      \\ \\/\\/ / -_) / _/ _ \\ '  \\/ -_) |  _/ _ \\ |  _| / _| |  _/ _` / _| |  _/ _ \\/ -_)
                       \\_/\\_/\\___|_\\__\\___/_|_|_\\___|  \\__\\___/  \\__|_\\__|  \\__\\__,_\\__|  \\__\\___/\\___|
                    
                    """);
                break;
            case "winner_p1":
                System.out.println("""
                         __   __  _                _                      \s
                         \\ \\ / / (_)              (_)                     \s
                          \\ V /   _ ___  __      ___ _ __  _ __   ___ _ __\s
                           > <   | / __| \\ \\ /\\ / / | '_ \\| '_ \\ / _ \\ '__|
                          / . \\  | \\__ \\  \\ V  V /| | | | | | | |  __/ |  \s
                         /_/ \\_\\ |_|___/   \\_/\\_/ |_|_| |_|_| |_|\\___|_|  \s
                    """);
                break;
            case "winner_computer":
                System.out.println("""
                    
                        __   ___   ___ ___  ____   __ __  ______    ___  ____       ____   _____     __    __  ____  ____   ____     ___  ____ \s
                       /  ] /   \\ |   |   ||    \\ |  |  ||      |  /  _]|    \\     |    | / ___/    |  |__|  ||    ||    \\ |    \\   /  _]|    \\\s
                      /  / |     || _   _ ||  o  )|  |  ||      | /  [_ |  D  )     |  | (   \\_     |  |  |  | |  | |  _  ||  _  | /  [_ |  D  )
                     /  /  |  O  ||  \\_/  ||   _/ |  |  ||_|  |_||    _]|    /      |  |  \\__  |    |  |  |  | |  | |  |  ||  |  ||    _]|    /\s
                    /   \\_ |     ||   |   ||  |   |  :  |  |  |  |   [_ |    \\      |  |  /  \\ |    |  `  '  | |  | |  |  ||  |  ||   [_ |    \\\s
                    \\     ||     ||   |   ||  |   |     |  |  |  |     ||  .  \\     |  |  \\    |     \\      /  |  | |  |  ||  |  ||     ||  .  \\
                     \\____| \\___/ |___|___||__|    \\__,_|  |__|  |_____||__|\\_|    |____|  \\___|      \\_/\\_/  |____||__|__||__|__||_____||__|\\_|
                    
                    
                    """);
                break;
            case "winner_p2":
                System.out.println(""" 
                           ____    _                _                      \s
                          / __ \\  (_)              (_)                     \s
                         | |  | |  _ ___  __      ___ _ __  _ __   ___ _ __\s
                         | |  | | | / __| \\ \\ /\\ / / | '_ \\| '_ \\ / _ \\ '__|
                         | |__| | | \\__ \\  \\ V  V /| | | | | | | |  __/ |  \s
                          \\____/  |_|___/   \\_/\\_/ |_|_| |_|_| |_|\\___|_|  \s
                        """);
                break;
            case "draw":
                System.out.println(""" 
                             _                   \s
                            | |                  \s
                          __| |_ __ __ ___      __
                         / _` | '__/ _` \\ \\ /\\ / /
                        | (_| | | | (_| |\\ V  V /\s
                         \\__,_|_|  \\__,_| \\_/\\_/ \s
                        """);
                break;
        }

    }

    // generate game board from scratch
    public static String[][] generateGameBoard(){
        return new String[][]{
                {".", ".", "."},
                {".", ".", "."},
                {".", ".", "."}
        };
    }

    // print the board to show the updated one
    public static void printBoard(String[][] game_board, int who_is_play, String symbol){
        if (who_is_play == 0){
            System.out.println(symbol+ " turn");
        }else {
            System.out.println("computer turn");
        }
        for (int i = 0; i < game_board.length; i++) {
            for (int j = 0; j < game_board[i].length; j++) {
                System.out.print(game_board[i][j]+"\t");
                if (j % 5 == 0 || j != game_board[i].length - 1) {
                    System.out.print("|\t");
                }
            }
            System.out.println();
            if(i != game_board.length-1) {
                System.out.println("------------------");
            }
        }
    }

    // player1 turn to play
    public static String[][] player(String[][] game_board, boolean turn, String symbol){
        Scanner scn = new Scanner(System.in);
        while (turn) {
            printBoard(game_board, 0, symbol);
            System.out.println("""                    
                    1|2|3
                    -----
                    4|5|6
                    -----
                    7|8|9
                    """);
            System.out.print("Enter where do you want to play: ");
            try {
                int player_chose = scn.nextInt();
                switch (player_chose) {
                    case 1:
                        if (checkIsEmptyPlace(game_board, 0, 0)) {
                            game_board[0][0] = symbol;
                            turn = false;
                        } else {
                            System.err.println("It is not empty place you can not put your X here");
                        }
                        break;
                    case 2:
                        if (checkIsEmptyPlace(game_board, 0, 1)) {
                            game_board[0][1] = symbol;
                            turn = false;
                        } else {
                            System.err.println("It is not empty place you can not put your X here");
                        }
                        break;
                    case 3:
                        if (checkIsEmptyPlace(game_board, 0, 2)) {
                            game_board[0][2] = symbol;
                            turn = false;
                        } else {
                            System.err.println("It is not empty place you can not put your X here");
                        }
                        break;
                    case 4:
                        if (checkIsEmptyPlace(game_board, 1, 0)) {
                            game_board[1][0] = symbol;
                            turn = false;
                        } else {
                            System.err.println("It is not empty place you can not put your X here");
                        }
                        break;
                    case 5:
                        if (checkIsEmptyPlace(game_board, 1, 1)) {
                            game_board[1][1] = symbol;
                            turn = false;
                        } else {
                            System.err.println("It is not empty place you can not put your X here");
                        }
                        break;
                    case 6:
                        if (checkIsEmptyPlace(game_board, 1, 2)) {
                            game_board[1][2] = symbol;
                            turn = false;
                        } else {
                            System.err.println("It is not empty place you can not put your X here");
                        }
                        break;
                    case 7:
                        if (checkIsEmptyPlace(game_board, 2, 0)) {
                            game_board[2][0] = symbol;
                            turn = false;
                        } else {
                            System.err.println("It is not empty place you can not put your X here");
                        }
                        break;
                    case 8:
                        if (checkIsEmptyPlace(game_board, 2, 1)) {
                            game_board[2][1] = symbol;
                            turn = false;
                        } else {
                            System.err.println("It is not empty place you can not put your X here");
                        }
                        break;
                    case 9:
                        if (checkIsEmptyPlace(game_board, 2, 2)) {
                            game_board[2][2] = symbol;
                            turn = false;
                        } else {
                            System.err.println("It is not empty place you can not put your X here");
                        }
                        break;
                    default:
                        System.err.println("You must chose from menu numbers only");

                }
            }catch (Exception e){
                scn.nextLine();// empty the scanner buffer it cause infinity loop if not use it
                System.err.println("You cannot enter anything not Integer");
            }

        }

        return game_board;
    }


    //check is winner is the biggest method, but I make it on three steps :)
    public static boolean checkIsWinner(String[][] game_board){

        return checkRow(game_board, 0, 0) || checkColumn(game_board, 0, 0) || checkDiagonally(game_board, 0, 0);
    }


    public static boolean checkRow(String[][] game_board, int total_X, int total_O){
        for (String[] strings : game_board) {
            for (String string : strings) {
                if (string.equals("X")) {
                    total_X++;
                } else if (string.equals("O")) {
                    total_O++;
                }
            }
            if (total_X == 3 || total_O == 3) {
                return true;
            }
            total_X = 0;
            total_O = 0;
        }
        return false;
    }


    public static boolean checkColumn(String[][] game_board, int total_X, int total_O){
        for (int i = 0; i < game_board.length; i++) {
            for (int j = 0; j < game_board[i].length; j++) {
                if(game_board[j][i].equals("X")){
                    total_X++;
                }else if(game_board[j][i].equals("O")){
                    total_O++;
                }
            }
            if(total_X == 3 || total_O == 3){
                return true;
            }
            total_X = 0;
            total_O = 0;
        }
        return false;
    }


    public static boolean checkDiagonally(String[][] board_game, int total_X, int total_O){
        for (int i = 0; i < board_game.length; i++) {
            if(board_game[i][i].equals("X")){
                total_X++;
            } else if (board_game[i][i].equals("O")) {
                total_O++;
            }
        }
        if (total_X == 3 || total_O == 3){
            return true;
        }else {
            total_O = 0;
            total_X = 0;
            for (int i = 0; i < board_game.length; i++) {
                if (board_game[i][board_game.length - 1  - i].equals("X")) {
                    total_X++;
                } else if (board_game[i][board_game.length - 1 - i].equals("O")) {
                    total_O++;
                }
            }
        }
        return total_X == 3 || total_O == 3;
    }


    public static boolean checkIsEmptyPlace(String[][] game_board, int indexi, int indexj){
        return game_board[indexi][indexj].equals(".");
    }

    //Bonus idea:
    public static int showMenuAndChose() throws Exception {
        Scanner scn = new Scanner(System.in);
        while (true) {
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("""
                    1- one round win (computer)
                    2- three rounds win (computer)
                    3- player1_X vs player2_O
                    4- exit
                    """);
            System.out.print("chose number from menu: ");
            try {
                int user_chose = scn.nextInt();
                switch (user_chose) {
                    case 1:
                        return 1;
                    case 2:
                        return 3;
                    case 3:
                        System.out.println("""
                            1- one round win
                            2- three rounds win"""
                        );
                        System.out.print("chose number from menu: ");
                        user_chose = scn.nextInt();
                        player2 = true;
                        if (user_chose == 2){
                            return 3;
                        }else {
                            return 1;
                        }
                    case 4:
                        return -1;
                    default:
                        System.err.println("you must chose from menu only");
                }
            } catch (Exception e) {
                scn.nextLine();// empty the scanner buffer it cause infinity loop if not use it
                System.err.println("You must enter integer number");
            }
        }
    }

    //agent play turn
    public static String[][] computer(String[][] game_board, boolean turn){
        Random rand = new Random();
        printBoard(game_board, 1, "O");
        while (turn) {
            int indix_i = rand.nextInt(3);
            int indix_j = rand.nextInt(3);
            if (checkIsEmptyPlace(game_board, indix_i, indix_j)) {
                game_board[indix_i][indix_j] = "O";
                turn = false;
            }
        }
        return game_board;
    }

// Calculator
    //----------------------------------------------------------------------------------------
    public static void runCalculator(){
        Scanner scn = new Scanner(System.in);
        Calculator c1 = new Calculator();
        double num1, num2;
        outerLoop:
        while (true) {
            try{
            c1.showMenu();
            System.out.print("chose: ");
            int user_operation = scn.nextInt();
            switch (user_operation) {
                case 1:
                    System.out.print("Enter first number: ");
                    num1 = scn.nextInt();
                    System.out.print("Enter second number: ");
                    num2 = scn.nextInt();
                    System.out.println(c1.addition(num1, num2));
                    break;
                case 2:
                    System.out.print("Enter first number: ");
                    num1 = scn.nextInt();
                    System.out.print("Enter second number: ");
                    num2 = scn.nextInt();
                    System.out.println(c1.subtraction(num1, num2));
                    break;
                case 3:
                    System.out.print("Enter first number: ");
                    num1 = scn.nextInt();
                    System.out.print("Enter second number: ");
                    num2 = scn.nextInt();
                    System.out.println(c1.multiplication(num1, num2));
                    break;
                case 4:
                    System.out.print("Enter first number: ");
                    num1 = scn.nextInt();
                    System.out.print("Enter second number: ");
                    num2 = scn.nextInt();
                    System.out.println(c1.division(num1, num2));
                    break;
                case 5:
                    System.out.print("Enter first number: ");
                    num1 = scn.nextInt();
                    System.out.print("Enter second number: ");
                    num2 = scn.nextInt();
                    System.out.println(c1.modulus(num1, num2));
                    break;
                case 6:
                    System.out.print("Enter first number: ");
                    num1 = scn.nextInt();
                    System.out.print("Enter second number: ");
                    num2 = scn.nextInt();
                    System.out.println(c1.minimum(num1, num2));
                    break;
                case 7:
                    System.out.print("Enter first number: ");
                    num1 = scn.nextInt();
                    System.out.print("Enter second number: ");
                    num2 = scn.nextInt();
                    System.out.println(c1.maximum(num1, num2));
                    break;
                case 8:
                    boolean user_input_true = true;
                    ArrayList<Double> numbers_list = new ArrayList<>();
                    while (user_input_true) {
                        System.out.print("Enter number: ");
                        num1 = scn.nextDouble();
                        numbers_list.add(num1);
                        System.out.print("do you want enter another number? enter anything to continue N to stop: ");
                        String user_want_stop = scn.next();
                        if (user_want_stop.equalsIgnoreCase("N")) {
                            user_input_true = false;
                        }
                    }
                    System.out.println(c1.average(numbers_list));
                    break;
                case 9:
                    System.out.println(c1.getLastResult());
                    break;
                case 10:
                    System.out.println(c1.listAllResultsHistory());
                    break;
                default:
                    break outerLoop;

            }
        }catch(Exception e){
                scn.nextLine();
                System.err.println("you must enter numbers only");
            }
        }
    }
}