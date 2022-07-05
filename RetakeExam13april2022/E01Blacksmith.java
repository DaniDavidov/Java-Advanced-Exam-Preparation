package RetakeExam13april2022;

import java.util.*;
import java.util.stream.Collectors;

public class E01Blacksmith {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> weapons = new TreeMap<>();

        ArrayDeque<Integer> steel = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).forEach(steel::offer);

        ArrayDeque<Integer> carbon = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).forEach(carbon::push);


        while (!steel.isEmpty() && !carbon.isEmpty()) {
            int peaceOfSteel = steel.poll();
            int peaceOfCarbon = carbon.pop();
            int sum = peaceOfCarbon + peaceOfSteel;

            String weapon = null;
            switch (sum) {
                case 70:
                    weapon = "Gladius";
                    break;
                case 80:
                    weapon = "Shamshir";
                    break;
                case 90:
                    weapon = "Katana";
                    break;
                case 110:
                    weapon = "Sabre";
                    break;
            }
            if (weapon != null) {
                weapons.putIfAbsent(weapon, 0);
                weapons.put(weapon, weapons.get(weapon) + 1);
            } else {
                peaceOfCarbon += 5;
                carbon.push(peaceOfCarbon);
            }
        }

        if (weapons.isEmpty()) {
            System.out.println("You did not have enough resources to forge a sword.");
        } else {
            int sum = weapons.values().stream().mapToInt(e -> e).sum();
            System.out.printf("You have forged %d swords.%n", sum);
        }

        if (steel.isEmpty()) {
            System.out.println("Steel left: none");
        } else {
            List<String> steelList = steel.stream().map(String::valueOf).collect(Collectors.toList());
            System.out.printf("Steel left: %s%n", String.join(", ", steelList));
        }

        if (carbon.isEmpty()) {
            System.out.println("Carbon left: none");
        } else {
            List<String> carbonList = carbon.stream().map(String::valueOf).collect(Collectors.toList());
            System.out.printf("Carbon left: %s%n", String.join(", ", carbonList));
        }

        weapons.forEach((k, v) -> System.out.printf("%s: %d%n", k, v));
    }
}
