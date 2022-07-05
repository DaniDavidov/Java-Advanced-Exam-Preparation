package RegularExam;

import java.util.Scanner;

public class E02StickyFingers {

    private static boolean gotCaught = false;
    private static int dillingerRow = -1;
    private static int dillingerCol = -1;
    private static int pocket = 0;
    private static char[][] field;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        String[] commands = scanner.nextLine().split(",");

        field = new char[n][];
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine().replaceAll("\\s+", "");
            char[] row = line.toCharArray();

            if (line.contains("D")) {
                dillingerRow = i;
                dillingerCol = line.indexOf("D");
            }

            field[i] = row;
        }

        int i = 0;
        while (i < commands.length && !gotCaught) {
            String direction = commands[i];

            int[] newCoordinates = estimateCoordinates(direction);
            int nextRow = newCoordinates[0];
            int nextCol = newCoordinates[1];

            if (!indexIsValid(nextRow, nextCol)) {
                System.out.println("You cannot leave the town, there is police outside!");
                i++;
                continue;
            }

            field[dillingerRow][dillingerCol] = '+';
            if (field[nextRow][nextCol] == '$') {
                int money = nextRow * nextCol;
                pocket += money;
                System.out.printf("You successfully stole %d$.%n", money);
            } else if (field[nextRow][nextCol] == 'P') {
                gotCaught = true;
                field[nextRow][nextCol] = '#';
                break;
            }
            dillingerRow = nextRow;
            dillingerCol = nextCol;
            field[dillingerRow][dillingerCol] = 'D';

            i++;
        }

        if (!gotCaught) {
            System.out.printf("Your last theft has finished successfully with %d$ in your pocket.%n", pocket);
        } else {
            System.out.printf("You got caught with %d$, and you are going to jail.%n", pocket);
        }

        printMatrix();
    }

    private static void printMatrix() {
        for (char[] arr : field) {
            for (char c : arr) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    private static boolean indexIsValid(int nextRow, int nextCol) {
        return nextRow < field.length && nextRow >= 0
                && nextCol < field[nextRow].length && nextCol >= 0;
    }

    private static int[] estimateCoordinates(String direction) {
        int[] coordinates = new int[2];
        switch (direction) {

            case "up":
                coordinates[0] = dillingerRow - 1;
                coordinates[1] = dillingerCol;
                break;
            case "down":
                coordinates[0] = dillingerRow + 1;
                coordinates[1] = dillingerCol;
                break;
            case "left":
                coordinates[0] = dillingerRow;
                coordinates[1] = dillingerCol - 1;
                break;
            case "right":
                coordinates[0] = dillingerRow;
                coordinates[1] = dillingerCol + 1;
                break;
        }
        return coordinates;
    }
}
