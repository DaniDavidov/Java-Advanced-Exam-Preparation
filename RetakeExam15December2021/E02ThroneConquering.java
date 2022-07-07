package RetakeExam15December2021;

import java.util.Scanner;

public class E02ThroneConquering {

    private static boolean helenReached = false;
    private static int helenRow = -1;
    private static int helenCol = -1;
    private static int parisRow = -1;
    private static int parisCol = -1;
    private static int parisEnergy = 0;
    private static char[][] field;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        parisEnergy = Integer.parseInt(scanner.nextLine());
        int n = Integer.parseInt(scanner.nextLine());

        field = new char[n][];
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            char[] row = line.toCharArray();

            if (line.contains("H")) {
                helenRow = i;
                helenCol = line.indexOf("H");
            }

            if (line.contains("P")) {
                parisRow = i;
                parisCol = line.indexOf("P");
            }

            field[i] = row;
        }

        while (parisEnergy > 0 && !helenReached) {
            String[] data = scanner.nextLine().split("\\s+");
            String direction = data[0];
            int enemyRow = Integer.parseInt(data[1]);
            int enemyCol = Integer.parseInt(data[2]);
            field[enemyRow][enemyCol] = 'S';

            int[] nextCoordinates = move(field, direction);
            parisEnergy--;

            if (indexIsValid(nextCoordinates)) {
                field[parisRow][parisCol] = '-';
                parisRow = nextCoordinates[0];
                parisCol = nextCoordinates[1];


                if (field[parisRow][parisCol] == 'S') {
                    parisEnergy -= 2;
                    if (parisEnergy > 0) {
                        field[parisRow][parisCol] = 'P';
                    } else {
                        field[parisRow][parisCol] = 'X';
                    }
                } else if (field[parisRow][parisCol] == 'H') {
                    helenReached = true;
                    field[parisRow][parisCol] = '-';
                } else {
                    if (parisEnergy > 0) {
                        field[parisRow][parisCol] = 'P';
                    } else {
                        field[parisRow][parisCol] = 'X';
                    }

                }
            }
        }

        if (parisEnergy <= 0) {
            System.out.printf("Paris died at %d;%d.%n", parisRow, parisCol);
        } else {
            System.out.printf("Paris has successfully abducted Helen! Energy left: %d%n", parisEnergy);
        }

        printField();
    }

    private static void printField() {
        for (char[] row : field) {
            for (char c : row) {
                System.out.print(c + "");
            }
            System.out.println();
        }
    }

    private static boolean indexIsValid(int[] nextCoordinates) {
        int row = nextCoordinates[0];
        int col = nextCoordinates[1];

        return row < field.length && row >= 0
                && col < field[row].length && col >= 0;
    }

    private static int[] move(char[][] field, String direction) {
        int[] coordinates = new int[2];

        switch (direction) {
            case "up":
                coordinates[0] = parisRow - 1;
                coordinates[1] = parisCol;
                break;
            case "down":
                coordinates[0] = parisRow + 1;
                coordinates[1] = parisCol;
                break;
            case "left":
                coordinates[0] = parisRow;
                coordinates[1] = parisCol - 1;
                break;
            case "right":
                coordinates[0] = parisRow;
                coordinates[1] = parisCol + 1;
                break;
        }
        return coordinates;
    }
}
