package RetakeExam13april2022;

import java.util.Arrays;
import java.util.Scanner;

public class E02Armory {

    private static int officerRow = -1;
    private static int officerCol = -1;
    private static int[] firstMirrorCoordinates = {-1, -1};
    private static int[] secondMirrorCoordinates = {-1, -1};
    private static int totalSum = 0;
    private static boolean hasToLeave = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        char[][] field = new char[n][];
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            char[] row = line.toCharArray();

            if (line.contains("A")) {
                officerRow = i;
                officerCol = line.indexOf("A");
            }

            field[i] = row;
        }

        estimateMirrorsCoordinates(field);

        while (!hasToLeave && totalSum < 65) {
            String direction = scanner.nextLine();

            int[] nextCoordinates = estimateOfficerCoordinates(field, direction);
            int nextRow = nextCoordinates[0];
            int nextCol = nextCoordinates[1];

            field[officerRow][officerCol] = '-';

            if (nextCoordinates[0] >= field.length || nextCoordinates[0] < 0
                    || nextCoordinates[1] >= field[0].length || nextCoordinates[1] < 0) {
                hasToLeave = true;
            } else {

                if (Character.isDigit(field[nextCoordinates[0]][nextCoordinates[1]])) {
                    totalSum += Integer.parseInt(String.valueOf(field[nextCoordinates[0]][nextCoordinates[1]]));
                } else if (field[nextCoordinates[0]][nextCoordinates[1]] == 'M') {
                    nextCoordinates = teleport(nextCoordinates[0], nextCoordinates[1]);
                    field[firstMirrorCoordinates[0]][firstMirrorCoordinates[1]] = '-';
                    field[secondMirrorCoordinates[0]][secondMirrorCoordinates[1]] = '-';
                }

                field[nextCoordinates[0]][nextCoordinates[1]] = 'A';
                officerRow = nextCoordinates[0];
                officerCol = nextCoordinates[1];
            }

        }

        if (hasToLeave) {
            System.out.println("I do not need more swords!");
        } else {
            System.out.println("Very nice swords, I will come back for more!");
        }

        System.out.printf("The king paid %d gold coins.%n", totalSum);
        printMatrix(field);
    }

    private static void printMatrix(char[][] field) {
        for (char[] arr : field) {
            for (char c : arr) {
                System.out.print(c + "");
            }
            System.out.println();
        }
    }

    private static int[] teleport(int nextRow, int nextCol) {
        int[] coordinates = new int[2];
        if (nextRow == firstMirrorCoordinates[0] && nextCol == firstMirrorCoordinates[1]) {
            coordinates[0] = secondMirrorCoordinates[0];
            coordinates[1] = secondMirrorCoordinates[1];
        } else {
            coordinates[0] = firstMirrorCoordinates[0];
            coordinates[1] =firstMirrorCoordinates[1];
        }
        return coordinates;
    }

    private static int[] estimateOfficerCoordinates(char[][] field, String direction) {
        int[] coordinates = new int[2];
        switch (direction) {
            case "up":
                coordinates[0] = officerRow - 1;
                coordinates[1] = officerCol;
                break;
            case "down":
                coordinates[0] = officerRow + 1;
                coordinates[1] = officerCol;
                break;
            case "left":
                coordinates[0] = officerRow;
                coordinates[1] = officerCol - 1;
                break;
            case "right":
                coordinates[0] = officerRow;
                coordinates[1] = officerCol + 1;
                break;
        }
        return coordinates;
    }

    private static void estimateMirrorsCoordinates(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 'M') {
                    if (firstMirrorCoordinates[0] == -1) {
                        firstMirrorCoordinates[0] = i;
                        firstMirrorCoordinates[1] = j;
                    } else {
                        secondMirrorCoordinates[0] = i;
                        secondMirrorCoordinates[1] = j;
                    }
                }
            }
        }
    }
}
