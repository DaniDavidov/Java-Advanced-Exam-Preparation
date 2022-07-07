package RetakeExam15December2021;

import java.util.*;
import java.util.stream.Collectors;

public class E01Meeting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> males = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).forEach(males::push);

        ArrayDeque<Integer> females = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).forEach(females::offer);

        int matchesCount = 0;
        
        while (!males.isEmpty() && !females.isEmpty()) {
            int male = males.peek();
            int female = females.peek();

            if (male <= 0) {
                males.pop();
                continue;
            }

            if (female <= 0) {
                females.poll();
                continue;
            }

            if (male % 25 == 0) {
                males.pop();
                males.pop();
                continue;
            }

            if (female % 25 == 0) {
                females.remove();
                females.remove();
                continue;
            }

            if (male == female) {
                matchesCount++;
                males.pop();
                females.poll();
            } else {
                male -= 2;
                females.poll();
                males.pop();
                males.push(male);
            }
        }

        System.out.println("Matches: " + matchesCount);
        if (males.isEmpty()) {
            System.out.println("Males left: none");
        } else {
            List<String> malesList = males.stream().map(String::valueOf).collect(Collectors.toList());
            System.out.printf("Males left: %s%n", String.join(", ", malesList));
        }

        if (females.isEmpty()) {
            System.out.println("Females left: none");
        } else {
            List<String> femalesList = females.stream().map(String::valueOf).collect(Collectors.toList());
            System.out.printf("Females left: %s%n", String.join(", ", femalesList));
        }
    }
}
