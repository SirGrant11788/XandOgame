
package XandOgame;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author Kyle/Grant
 */
public class XandOgame {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        game();
    }

    public static void game() {

        String choice = "", cont = "";
        Scanner inChoice = new Scanner(System.in);
        System.out.println("WELCOME TO THE X AND O GAME!!!!!\n*************************************************\n"
                + "ENTER A (1) TO START THE GAME OR (2) TO VIEW THE GAME HISTORY");
        choice = input.nextLine();
        if (choice.equals("1")) {
            while (!cont.equals("x")) {
                playGame();
                cont = "";
                System.out.println("Would you like to play again? Press (x) to exit or any other key to play again.");
                cont = inChoice.nextLine();
            }
        }
        if (choice.equals("2")) {
            History();
        }
    }

    public static void playGame() {
        String playerX = "", playerO = "", msg = "", save = "", winner = "";
        Scanner insave = new Scanner(System.in);
        Scanner inname = new Scanner(System.in);
        int ROW, COL, round;
        boolean isDone = false, playerXturn = true, playerOturn = true;
        String[][] grid = {{"-", "-", "-"},
        {"-", "-", "-"},
        {"-", "-", "-"}
        };
        playerX = "";
        playerO = "";
        System.out.println("PLEASE ENTER PLAYER (X) NAME:");
        playerX = inname.nextLine();
        System.out.println("PLEASE ENTER PLAYER (O) NAME:");
        playerO = inname.nextLine();
        round = 0;

        System.out.println("*************************************************");
        while (isDone != true) {
            playerXturn = true;
            while (playerXturn) {
                round++;
                System.out.println("ROUND " + round
                        + "\n*************************************************");
                playerXturn(grid, playerX);
                if (!space(grid)) {
                    isDone = false;
                }
                playerXturn = false;
            }
            isDone = checkWinner(grid);
            playerOturn = true;
            if (isDone) {
                winner = playerX;
                msg = "CONGRATULATIONS " + playerX.toUpperCase() + " YOU ARE THE WINNER!!!";
                playerOturn = false;
            }
            while (playerOturn) {
                round++;
                System.out.println("ROUND " + round
                        + "\n*************************************************");
                playerOturn(grid, playerO);
                isDone = checkWinner(grid);
                if (isDone) {
                    winner = playerO;
                    msg = "CONGRATULATIONS " + playerO.toUpperCase() + " YOU ARE THE WINNER!!!";
                }
                if (!space(grid)) {
                    isDone = false;
                }
                playerOturn = false;
            }
        }
        for (int x = 0; x < 3; x++) {
            System.out.print(x);
            for (int y = 0; y < 3; y++) {
                System.out.print("|" + grid[x][y]);
            }
            System.out.println("|");
        }
        System.out.println(msg);
        save = "";
        System.out.println("Would you like to save the game? Enter (1) to save or any other key to continue");
        save = insave.nextLine();
        if (save.equals("1")) {
            saveGame(winner);
        }
    }

    public static void playerXturn(String[][] grid, String playerX) {
        int ROW, COL;
        System.out.println("  0 1 2");
        for (int x = 0; x < 3; x++) {
            System.out.print(x);
            for (int y = 0; y < 3; y++) {
                System.out.print("|" + grid[x][y]);
            }
            System.out.println("|");
        }
        System.out.println("*************************************************");
        System.out.print(playerX.toUpperCase() + " PLEASE ENTER A ROW NUMBER:");
        ROW = input.nextInt();
        System.out.print(playerX.toUpperCase() + " PLEASE ENTER A COLOUMN NUMBER:");
        COL = input.nextInt();
        System.out.println("*************************************************");
        if (!grid[ROW][COL].equals("-")) {
            System.out.println("that position is already taken");
            System.out.print(playerX.toUpperCase() + " PLEASE ENTER A ROW NUMBER:");
            ROW = input.nextInt();
            System.out.print(playerX.toUpperCase() + " PLEASE ENTER A COLOUMN NUMBER:");
            COL = input.nextInt();
        }
        grid[ROW][COL] = "X";

    }

    public static void playerOturn(String[][] grid, String playerO) {
        int ROW, COL;
        System.out.println("  0 1 2");
        for (int x = 0; x < 3; x++) {
            System.out.print(x);
            for (int y = 0; y < 3; y++) {
                System.out.print("|" + grid[x][y]);
            }
            System.out.println("|");
        }
        System.out.println("*************************************************");
        System.out.print(playerO.toUpperCase() + " PLEASE ENTER A ROW NUMBER:");
        ROW = input.nextInt();
        System.out.print(playerO.toUpperCase() + " PLEASE ENTER A COLOUMN NUMBER:");
        COL = input.nextInt();
        System.out.println("*************************************************");
        if (!grid[ROW][COL].equals("-")) {
            System.out.println("that position is already taken");
            System.out.print(playerO.toUpperCase() + " PLEASE ENTER A ROW NUMBER:");
            ROW = input.nextInt();
            System.out.print(playerO.toUpperCase() + " PLEASE ENTER A COLOUMN NUMBER:");
            COL = input.nextInt();
        }
        grid[ROW][COL] = "O";
    }

    public static boolean space(String[][] grid) {
        boolean space = false;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (grid[x][y].equals("-")) {
                    space = true;
                }
            }
        }
        return space;
    }

    public static boolean checkWinner(String[][] grid) {
        boolean isDone = false;
        int x;
        for (x = 0; x < 3; ++x) {
            if (grid[x][0].equals(grid[x][1]) && grid[x][0].equals(grid[x][2])) {
                if (!grid[x][0].equals("-") && !grid[x][1].equals("-") && !grid[x][2].equals("-")) {
                    isDone = true;
                }
            }
        }
        for (x = 0; x < 3; ++x) {
            if (grid[0][x].equals(grid[1][x]) && grid[1][x].equals(grid[2][x])) {
                if (!grid[0][x].equals("-") && !grid[1][x].equals("-") && !grid[2][x].equals("-")) {
                    isDone = true;
                }
            }
        }
        if (grid[0][0].equals(grid[1][1]) && grid[0][0].equals(grid[2][2])) {
            if (!grid[0][0].equals("-") && !grid[1][1].equals("-") && !grid[2][2].equals("-")) {
                isDone = true;
            }
        }
        if (grid[0][2].equals(grid[1][1]) && grid[0][2].equals(grid[2][0])) {
            if (!grid[0][2].equals("-") && !grid[1][1].equals("-") && !grid[2][0].equals("-")) {
                isDone = true;
            }
        }
        return isDone;
    }

    public static void saveGame(String winner) {
        try (FileWriter FW = new FileWriter("SaveHistory.txt", true);
                PrintWriter PW = new PrintWriter(FW);) {
            String dateTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
            PW.print("Player Name: ");
            PW.println(winner);
            PW.print("Game Date: ");
            PW.println(dateTime);
            PW.println("********************************");
            PW.close();

        } catch (Exception e) {
            System.out.println("Message " + e);
        }
        System.out.println("The game has been saved successfully\n*************************************************\n\n\n");
    }

    public static void History() {
        String check;

        try (FileReader FR = new FileReader("SaveHistory.txt");
                BufferedReader BR = new BufferedReader(FR);) {
            while ((check = BR.readLine()) != null) {
                System.out.println(check);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

