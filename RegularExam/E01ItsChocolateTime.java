package RegularExam;

import java.util.*;

public class E01ItsChocolateTime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> chacolates = new TreeMap<>();
        chacolates.put("Milk Chocolate", 0);
        chacolates.put("Dark Chocolate", 0);
        chacolates.put("Baking Chocolate", 0);

        ArrayDeque<Double> milk = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Double::parseDouble).forEach(milk::offer);

        ArrayDeque<Double> cacaoPowder = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Double::parseDouble).forEach(cacaoPowder::push);


        while (!milk.isEmpty() && !cacaoPowder.isEmpty()) {
            double milkValue = milk.poll();
            double cacaoPowderValue = cacaoPowder.pop();
            double sum = milkValue + cacaoPowderValue;
            double cacaoPercentage = cacaoPowderValue / sum * 100;

            switch ((int) cacaoPercentage) {
                case 30:
                    chacolates.put("Milk Chocolate", chacolates.get("Milk Chocolate") + 1);
                    break;
                case 50:
                    chacolates.put("Dark Chocolate", chacolates.get("Dark Chocolate") + 1);
                    break;
                case 100:
                    chacolates.put("Baking Chocolate", chacolates.get("Baking Chocolate") + 1);
                    break;
                default:
                    milkValue += 10;
                    milk.offer(milkValue);
                    break;
            }
        }

        long count = chacolates.entrySet().stream().filter(e -> e.getValue() > 0).count();
        if (count == 3) {
            System.out.println("It’s a Chocolate Time. All chocolate types are prepared.");
        } else {
            System.out.println("Sorry, but you didn't succeed to prepare all types of chocolates.");
        }

        chacolates.entrySet().stream()
                .filter(e -> e.getValue() > 0)
                .forEach(e -> System.out.printf(" # %s --> %d%n", e.getKey(), e.getValue()));
    }
}
