package RetakeExam18August2021;

import java.util.*;
import java.util.stream.Collectors;

public class E01PastryShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> foods = new LinkedHashMap<>();
        foods.put("Biscuit", 0);
        foods.put("Cake", 0);
        foods.put("Pie", 0);
        foods.put("Pastry", 0);

        ArrayDeque<Integer> liquids = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).forEach(liquids::offer);

        ArrayDeque<Integer> ingredients = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).forEach(ingredients::push);

        while (!liquids.isEmpty() && !ingredients.isEmpty()) {
            int liquid = liquids.poll();
            int ingredient = ingredients.pop();
            int sum = liquid + ingredient;

            switch (sum) {
                case 25:
                    foods.put("Biscuit", foods.get("Biscuit") + 1);
                    break;
                case 50:
                    foods.put("Cake", foods.get("Cake") + 1);
                    break;
                case 75:
                    foods.put("Pastry", foods.get("Pastry") + 1);
                    break;
                case 100:
                    foods.put("Pie", foods.get("Pie") + 1);
                    break;
                default:
                    ingredient += 3;
                    ingredients.push(ingredient);
                    break;
            }
        }

        long count = foods.entrySet().stream().filter(food -> food.getValue() > 0).count();
        if (count == 4) {
            System.out.println("Great! You succeeded in cooking all the food!");
        } else {
            System.out.println("What a pity! You didn't have enough materials to cook everything.");
        }

        if (liquids.isEmpty()) {
            System.out.println("Liquids left: none");
        } else {
            List<String> liquidsList = liquids.stream().map(String::valueOf).collect(Collectors.toList());
            System.out.printf("Liquids left: %s%n", String.join(", ", liquidsList));
        }

        if (ingredients.isEmpty()) {
            System.out.println("Ingredients left: none");
        } else {
            List<String> ingredientsList = ingredients.stream().map(String::valueOf).collect(Collectors.toList());
            System.out.printf("Ingredients left: %s%n", String.join(", ", ingredientsList));
        }

        foods.entrySet().stream()
                //.sorted((a, b) -> Integer.compare(b.getValue(), a.getValue()))
                .forEach(e -> System.out.printf("%s: %d%n", e.getKey(), e.getValue()));
    }
}
