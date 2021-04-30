package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);
    private final static String delimField = "---------";
    private final static String[][] gameProgress = new String[3][3];
    private static int moveCnt = 0;
    private final static int[][] winSeq = new int[][]{
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };

    public static void main(String[] args) {
        fillGameProgressArray();
        playGame();
    }

    private static void fillGameProgressArray() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameProgress[i][j] = " ";
            }
        }
    }

    private static void printGameField() {
        System.out.println(delimField);
        for (String[] array : gameProgress) {
            System.out.println(Arrays.toString(array)
                    .replace("[", "| ")
                    .replace("]", " |")
                    .replace(",", "")
            );
        }
        System.out.println(delimField);
    }

    private static String gameResult(String playerMove) {
        if (isGameWinner(playerMove)) {
            return String.format("%s wins", playerMove);
        } else if (moveCnt == 9) {
            return "Draw";
        }
        return null;
    }

    private static boolean isGameWinner(String name) {
        for (int[] seq : winSeq) {
            if (gameProgress[seq[0] / 3][seq[0] % 3].equals(gameProgress[seq[1] / 3][seq[1] % 3])
                    && gameProgress[seq[0] / 3][seq[0] % 3].equals(gameProgress[seq[2] / 3][seq[2] % 3])
                    && gameProgress[seq[0] / 3][seq[0] % 3].equals(name)
            ) {
                return true;
            }
        }
        return false;
    }

    private static void playGame() {
        boolean needRepeatReq = true;
        String player = "X";
        printGameField();

        while (needRepeatReq) {
            System.out.print("Enter the coordinates: ");

            String x = scanner.next();
            String y = scanner.next();
            String gameResult;

            if (x.matches("\\d+") && y.matches("\\d+")) {
                int xNum = Integer.parseInt(x);
                int yNum = Integer.parseInt(y);

                if (xNum >= 1 && xNum <= 3 && yNum >= 1 && yNum <= 3) {
                    if (gameProgress[xNum - 1][yNum - 1].equals(" ")) {
                        gameProgress[xNum - 1][yNum - 1] = player;
                        moveCnt++;
                        printGameField();
                        gameResult = gameResult(player);

                        if (gameResult == null) {
                            player = player.equals("X") ? "O" : "X";
                        } else {
                            System.out.println(gameResult);
                            needRepeatReq = false;
                        }
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            } else {
                System.out.println("You should enter numbers!");
            }
        }
    }

}
