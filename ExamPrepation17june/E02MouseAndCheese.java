package ExamPrepation17june;

import java.util.Scanner;

public class E02MouseAndCheese {

    private static int mouseRow = -1;
    private static int mouseCol = -1;
    private static int eatenCheese = 0;
    private static boolean isInTheBounds = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        char[][] territory = new char[n][];
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            char[] row = line.toCharArray();
            territory[i] = row;

            if (line.contains("M")) {
                mouseRow = i;
                mouseCol = line.indexOf("M");
            }
        }

        String line;
        while (!"end".equals(line = scanner.nextLine()) && isInTheBounds) {

            switch (line) {
                case "up":
                    if (mouseIsInField(territory)) {
                        moveUp(territory);
                    }
                    break;
                case "down":
                    moveDown(territory);
                    break;
                case "left":
                    moveLeft(territory);
                    break;
                case "right":
                    moveRight(territory);
                    break;
            }
        }
        printMatrix(territory);
    }

    private static boolean mouseIsInField(char[][] territory) {
        return mouseRow < territory.length - 1 && mouseRow >= 0
                && mouseCol < territory[mouseRow].length && mouseCol >= 0;
    }

    private static void printMatrix(char[][] territory) {
        for (char[] arr : territory) {
            for (char c : arr) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    private static void moveRight(char[][] territory) {
        int nextCol = mouseCol + 1;

        if (territory[mouseRow][mouseCol] == 'B') {
            nextCol += 1;
            territory[mouseRow][mouseCol+1] = '-';
        }

        if (territory[mouseRow][mouseCol] == 'c') {
            eatenCheese++;
        }

        territory[mouseRow][mouseCol] = '-';
        territory[mouseRow][nextCol] = 'M';
        mouseCol = nextCol;
    }

    private static void moveLeft(char[][] territory) {
        int nextCol = mouseCol - 1;
        if (territory[mouseRow][mouseCol] == 'B') {
            nextCol -= 1;
            territory[mouseRow][mouseCol-1] = '-';
        }

        if (territory[mouseRow][mouseCol] == 'c') {
            eatenCheese++;
        }

        territory[mouseRow][mouseCol] = '-';
        territory[mouseRow][nextCol] = 'M';
        mouseCol = nextCol;
    }

    private static void moveDown(char[][] territory) {
        int nextRow = mouseRow + 1;
        if (territory[mouseRow][mouseCol] == 'B') {
            nextRow += 1;
            territory[mouseRow+1][mouseCol] = '-';
        }

        if (territory[mouseRow][mouseCol] == 'c') {
            eatenCheese++;
        }
        territory[mouseRow][mouseCol] = '-';
        territory[nextRow][mouseCol] = 'M';

        mouseRow = nextRow;
    }

    private static void moveUp(char[][] territory) {
        int nextRow = mouseRow - 1;
        if (territory[mouseRow][mouseCol] == 'B') {
            nextRow -= 1;
            territory[mouseRow-1][mouseCol] = '-';
        }

        if (territory[mouseRow][mouseCol] == 'c') {
            eatenCheese++;
        }

        territory[mouseRow][mouseCol] = '-';
        territory[nextRow][mouseCol] = 'M';
        mouseRow = nextRow;
    }
}
