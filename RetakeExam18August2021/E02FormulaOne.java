package RetakeExam18August2021;

import java.util.Scanner;

public class E02FormulaOne {

    private static char[][] field;
    private static int playerRow = -1;
    private static int playerCol = -1;
    private static boolean finishReached = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        int n = Integer.parseInt(scanner.nextLine());

        field = new char[size][];
        for (int i = 0; i < size; i++) {
            String line = scanner.nextLine();
            char[] row = line.toCharArray();

            if (line.contains("P")) {
                playerRow = i;
                playerCol = line.indexOf("P");
            }

            field[i] = row;
        }

        for (int i = 0; i < n; i++) {
            String direction = scanner.nextLine();

            int[] nextCoordinates = estimateCoordinates(direction);
            int nextRow = nextCoordinates[0];
            int nextCol = nextCoordinates[1];

            field[playerRow][playerCol] = '.';
            playerRow = nextRow;
            playerCol = nextCol;

            if (field[nextRow][nextCol] == 'B') {
                nextCoordinates = estimateCoordinates(direction);
                playerRow = nextCoordinates[0];
                playerCol = nextCoordinates[1];
            } else if (field[nextRow][nextCol] == 'T') {
                String newDirection = findOppositeDirection(direction);
                nextCoordinates = estimateCoordinates(newDirection);
                playerRow = nextCoordinates[0];
                playerCol = nextCoordinates[1];
            } else if (field[nextRow][nextCol] == 'F') {
                finishReached = true;
            }

            field[playerRow][playerCol] = 'P';

            if (finishReached) {
                break;
            }
        }

        if (finishReached) {
            System.out.println("Well done, the player won first place!");
        } else {
            System.out.println("Oh no, the player got lost on the track!");
        }

        printMatrix();
    }

    private static void printMatrix() {
        for (char[] row : field) {
            for (char c : row) {
                System.out.print(c + "");
            }
            System.out.println();
        }
    }

    private static String findOppositeDirection(String direction) {
        String newDirection = "";
        switch (direction) {
            case "up":
                newDirection = "down";
                break;
            case "down":
                newDirection = "up";
                break;
            case "left":
                newDirection = "right";
                break;
            case "right":
                newDirection = "left";
                break;
        }
        return newDirection;
    }

    private static int[] estimateCoordinates(String direction) {
        int[] coordinates = new int[2];

        switch (direction) {
            case "up":
                if (playerRow == 0) {
                    coordinates[0] = field.length - 1;
                } else {
                    coordinates[0] = playerRow - 1;
                }
                coordinates[1] = playerCol;
                break;
            case "down":
                if (playerRow == field.length - 1) {
                    coordinates[0] = 0;
                } else {
                    coordinates[0] = playerRow + 1;
                }
                coordinates[1] = playerCol;

                break;
            case "left":
                if (playerCol == 0) {
                    coordinates[0] = playerRow;
                    coordinates[1] = field[playerRow].length - 1;
                } else {
                    coordinates[0] = playerRow;
                    coordinates[1] = playerCol - 1;
                }
                break;
            case "right":
                if (playerCol == field[playerRow].length - 1) {
                    coordinates[0] = playerRow;
                    coordinates[1] = 0;
                } else {
                    coordinates[0] = playerRow;
                    coordinates[1] = playerCol + 1;
                }
                break;
        }
        return coordinates;

    }
}
