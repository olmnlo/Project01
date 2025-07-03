import java.sql.Time;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {
        runGame();
    }

    public static void runGame() throws Exception {
        printMsg("welcome");

        Random rand = new Random();

        String[][] game_board = generateGameBoard();

        int round = showMenuAndChose();
        int who_play = rand.nextInt(2);
        int score_p1 = 0;
        int score_computer = 0;
        while (round > 0){
            System.out.println("round will start");
            TimeUnit.SECONDS.sleep(2);

            int result = rounds(game_board, who_play);
            if (result == 0){
                score_p1++;
            }else {
                score_computer++;
            }
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


    }
    public static int rounds(String[][] game_board, int who_play){
        while (true) {
            switch (who_play) {
                case 0:
                    game_board = player(game_board);
                    break;
                case 1:
                    game_board = computer(game_board);
                    break;
            }
            if (checkIsWinner(game_board) && who_play == 0){
                printMsg("winner_p1");
                break;
            }else if (checkIsWinner(game_board) && who_play == 1){
                printMsg("winner_computer");
                break;
            }
            if (who_play == 0){
                who_play = 1;
            }else {
                who_play = 0;
            }
        }
        return who_play;

    }

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
                    
                     ____   _       ____  __ __    ___  ____       ____   _____     __    __  ____  ____   ____     ___  ____ \s
                    |    \\ | |     /    ||  |  |  /  _]|    \\     |    | / ___/    |  |__|  ||    ||    \\ |    \\   /  _]|    \\\s
                    |  o  )| |    |  o  ||  |  | /  [_ |  D  )     |  | (   \\_     |  |  |  | |  | |  _  ||  _  | /  [_ |  D  )
                    |   _/ | |___ |     ||  ~  ||    _]|    /      |  |  \\__  |    |  |  |  | |  | |  |  ||  |  ||    _]|    /\s
                    |  |   |     ||  _  ||___, ||   [_ |    \\      |  |  /  \\ |    |  `  '  | |  | |  |  ||  |  ||   [_ |    \\\s
                    |  |   |     ||  |  ||     ||     ||  .  \\     |  |  \\    |     \\      /  |  | |  |  ||  |  ||     ||  .  \\
                    |__|   |_____||__|__||____/ |_____||__|\\_|    |____|  \\___|      \\_/\\_/  |____||__|__||__|__||_____||__|\\_|
                    
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
        }

    }

    public static String[][] generateGameBoard(){
        return new String[][]{
                {".", ".", "."},
                {".", ".", "."},
                {".", ".", "."}
        };
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
            printBoard(game_board, 0);
            System.out.println("Enter where do you want to play: ");
            System.out.println("""                    
                    1|2|3
                    -----
                    4|5|6
                    -----
                    7|8|9
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

    public static boolean checkIsWinner(String[][] game_board){

        return checkRow(game_board) || checkColumn(game_board) || checkDiagonally(game_board);
    }

    public static boolean checkRow(String[][] game_board){
        int total_X = 0;
        int total_O = 0;
        for (int i = 0; i < game_board.length; i++) {
            for (int j = 0; j < game_board[i].length; j++) {
               if(game_board[i][j].equals("X")){
                   total_X++;
               }else if (game_board[i][j].equals("O")) {
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

    public static boolean checkColumn(String[][] game_board){
        int total_X = 0;
        int total_O = 0;
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

    public static boolean checkDiagonally(String[][] board_game){
        int total_X = 0;
        int total_O = 0;
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
            if (board_game[0][2].equals("X")){
                total_X++;
            }else if(board_game[0][2].equals("O")) {
                total_O++;
            }
            if(board_game[1][1].equals("X")){
                total_X++;
            }else if (board_game[1][1].equals("O")){
                total_O++;
            }
            if(board_game[2][0].equals("X")){
                total_X++;
            }else if (board_game[2][0].equals("O")){
                total_O++;
            }
        }
        return total_X == 3 || total_O == 3;
    }


    public static boolean checkIsEmptyPlace(String[][] game_board, int indexi, int indexj){
        return game_board[indexi][indexj].equals(".");
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



    public static String[][] computer(String[][] game_board){
        Random rand = new Random();
        printBoard(game_board, 1);
        boolean played = true;
        while (played) {
            int indix_i = rand.nextInt(3);
            int indix_j = rand.nextInt(3);
            if (checkIsEmptyPlace(game_board, indix_i, indix_j)) {
                game_board[indix_i][indix_j] = "O";
                played = false;
            }
        }
        return game_board;
    }
}