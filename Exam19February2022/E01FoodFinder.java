package Exam19February2022;

import java.util.*;
import java.util.stream.Collectors;

public class E01FoodFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> foods = new LinkedHashMap<>();
        foods.put("pear", 0);
        foods.put("flour", 0);
        foods.put("pork", 0);
        foods.put("olive", 0);

        ArrayDeque<String> vowels = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .forEach(vowels::offer);


        ArrayDeque<String> consonants = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .forEach(consonants::push);

        while (!consonants.isEmpty()) {
            String vowel = vowels.remove();
            String consonant = consonants.pop();

            List<Map.Entry<String, Integer>> foodsFound = foods.entrySet().stream()
                    .filter(food -> food.getKey().contains(vowel) && food.getKey().contains(consonant))
                    .collect(Collectors.toList());
            for (Map.Entry<String, Integer> entry : foodsFound) {
                if (foods.get(entry.getKey()) == 0) {
                    foods.put(entry.getKey(), foods.get(entry.getKey()) + 1);
                }
            }
            vowels.offer(vowel);

        }
        long count = foods.values().stream().filter(e -> e != 0).mapToInt(e -> e).count();
        System.out.printf("Words found: %d%n", count);
        foods.entrySet().stream()
                .filter(food -> food.getValue() != 0)
                .forEach(food -> System.out.println(food.getKey()));
    }
}
