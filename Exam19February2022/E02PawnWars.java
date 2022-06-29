package Exam19February2022;

import javax.security.sasl.SaslClient;
import java.util.Scanner;

public class E02PawnWars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] whiteCoordinates = {-1, -1};
        int[] blackCoordinates = {-1, -1};

        String[][] chessboard = new String[8][8];
        for (int i = 0; i < chessboard.length; i++) {
            String line = scanner.nextLine();
            String[] row = line.split("");
            chessboard[i] = row;

            if (line.contains("w")) {
                whiteCoordinates[0] = i;
                whiteCoordinates[1] = line.indexOf("w");
            }

            if (line.contains("b")) {
                blackCoordinates[0] = i;
                blackCoordinates[1] = line.indexOf("b");
            }
        }

        boolean canBeCaptured = Math.abs(whiteCoordinates[1] - blackCoordinates[1]) == 1
                && whiteCoordinates[0] > blackCoordinates[0];

        String winner = "";
        String winnerCoordinates = "";

        if (canBeCaptured) {
//            while (blackCoordinates[0] - whiteCoordinates[0] == 0) {
//                chessboard[blackCoordinates[0]][blackCoordinates[1]] = "-";
//                blackCoordinates[0] = 1;
//                chessboard[blackCoordinates[0]][blackCoordinates[1]] = "b";
//
//                chessboard[whiteCoordinates[0]][whiteCoordinates[1]] = "-";
//                whiteCoordinates[0] -= 1;
//                chessboard[whiteCoordinates[0]][whiteCoordinates[1]] = "w";
//
//
//            }
            int diff = whiteCoordinates[0] - blackCoordinates[0];
            if (diff % 2 == 0) {
                winner = "Black";
                int digit = blackCoordinates[0] - diff / 2;
                winnerCoordinates = String.format("%c%d", 97 + whiteCoordinates[1], digit);
            } else {
                winner = "White";
                int digit = blackCoordinates[0] - diff / 2;
                winnerCoordinates = String.format("%c%d", 97 + blackCoordinates[1], digit);
            }
            System.out.printf("Game over! %s capture on %s.", winner, winnerCoordinates);

        } else {

            int whiteSquaresLeft = chessboard.length - whiteCoordinates[0];
            int blackSquaresLeft = blackCoordinates[0] - 1;

            if (whiteSquaresLeft == blackSquaresLeft) {
                winner = "White";
                winnerCoordinates = String.format("%c%d", 97 + whiteCoordinates[1], 8);
            } else if (whiteSquaresLeft > blackSquaresLeft) {
                winner = "White";
                winnerCoordinates = String.format("%c%d", 97 + whiteCoordinates[1], 8);
            } else {
                winner = "Black";
                winnerCoordinates = String.format("%c%d", 97 + blackCoordinates[1], 1);
            }
            System.out.printf("Game over! %s pawn is promoted to a queen at %s.", winner, winnerCoordinates);
        }
    }
}
