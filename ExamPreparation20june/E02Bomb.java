package ExamPreparation20june;

import java.util.Scanner;

public class E02Bomb {
    private static int sapperRow = -1;
    private static int sapperCol = -1;
    private static int numberOfBombs = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String[] commands = scanner.nextLine().split(",");

        char[][] field = new char[n][];
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine().replaceAll("\\s+", "");
            char[] row = line.toCharArray();

            long count;
            if (line.contains("B")) {
                count = line.chars().filter(c -> c == 'B').count();
                numberOfBombs += count;
            }
            if (line.contains("s")) {
                sapperRow = i;
                sapperCol = line.indexOf("s");
            }

            field[i] = row;
        }


        boolean endApproached = false;
        int i = 0;
        while (numberOfBombs > 0 && i < commands.length){
            String command = commands[i];

            String result = move(field, command);

            if (result == null) {
                i++;
                continue;
            }

            if (result.equals("You found a bomb!")) {
                System.out.println(result);
            } else if (result.equals("end")) {
                endApproached = true;
            }

            if (endApproached) {
                break;
            }

            i++;
        }

        if (endApproached) {
            System.out.printf("END! %d bombs left on the field", numberOfBombs);
        } else if (numberOfBombs == 0) {
            System.out.println("Congratulations! You found all bombs!");
        } else {
            System.out.printf("%d bombs left on the field. Sapper position: (%d,%d)",
                    numberOfBombs, sapperRow, sapperCol);
        }
    }



    private static String move(char[][] field, String direction) {
        int[] coordinates = estimateCoordinates(direction);
        String result = null;

        int nextRow = coordinates[0];
        int nextCol = coordinates[1];

        if (indexIsValid(field, nextRow, nextCol)) {
            field[sapperRow][sapperCol] = '+';

            switch (field[nextRow][nextCol]) {
                case 'B':
                    numberOfBombs--;
                    field[nextRow][nextCol] = '+';
                    result = "You found a bomb!";
                    break;
                case 'e':
                    result = "end";
                    break;
                default:
                    field[nextRow][nextCol] = 's';
                    break;
            }

            sapperRow = nextRow;
            sapperCol = nextCol;
        }
        return result;
    }

    private static int[] estimateCoordinates(String direction) {
        int[] coordinates = new int[2];
        switch (direction) {
            case "up":
                coordinates[0] = sapperRow - 1;
                coordinates[1] = sapperCol;
                break;
            case "down":
                coordinates[0] = sapperRow + 1;
                coordinates[1] = sapperCol;
                break;
            case "left":
                coordinates[0] = sapperRow;
                coordinates[1] = sapperCol - 1;
                break;
            case "right":
                coordinates[0] = sapperRow;
                coordinates[1] = sapperCol + 1;
                break;
        }
        return coordinates;
    }

    private static boolean indexIsValid(char[][] field, int sapperRow, int sapperCol) {
        return sapperRow < field.length && sapperRow >= 0
                && sapperCol < field[sapperRow].length && sapperCol >= 0;
    }
}
