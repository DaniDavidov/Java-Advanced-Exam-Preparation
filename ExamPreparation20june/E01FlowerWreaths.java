package ExamPreparation20june;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class E01FlowerWreaths {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> lilies = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt).forEach(lilies::push);

        ArrayDeque<Integer> roses = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt).forEach(roses::offer);

        int wreathsCount = 0;
        int flowersLeft = 0;
        while (!lilies.isEmpty() && !roses.isEmpty()) {
            int lilie = lilies.poll();
            int rose = roses.remove();
            int sum = lilie + rose;

            if (sum > 15) {
                while (sum > 15) {
                    sum -= 2;
                    lilie -= 2;
                }
            }
            if (sum == 15) {
                wreathsCount++;
            }
            if (sum < 15) {
                flowersLeft += sum;
            }
        }

        if (flowersLeft > 15) {
            int wreathsLeft = flowersLeft / 15;
            wreathsCount += wreathsLeft;
        }

        if (wreathsCount >= 5) {
            System.out.printf("You made it, you are going to the competition with %d wreaths!%n", wreathsCount);
        } else {
            System.out.printf("You didn't make it, you need %d wreaths more!", 5 - wreathsCount);
        }
    }
}
