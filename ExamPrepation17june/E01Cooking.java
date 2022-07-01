package ExamPrepation17june;

import java.util.*;
import java.util.stream.Collectors;

public class E01Cooking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> foods = new TreeMap<>();
        foods.put("Bread", 0);
        foods.put("Cake", 0);
        foods.put("Fruit Pie", 0);
        foods.put("Pastry", 0);

        ArrayDeque<Integer> liquids = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));


        ArrayDeque<Integer> ingredients = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).forEach(ingredients::push);

        while (!liquids.isEmpty() && !ingredients.isEmpty()) {
            int liquid = liquids.poll();
            int ingredient = ingredients.pop();
            int sum = liquid + ingredient;

            String food;
            switch (sum) {
                case 25:
                    food = "Bread";
                    break;
                case 50:
                    food = "Cake";
                    break;
                case 75:
                    food = "Pastry";
                    break;
                case 100:
                    food = "Fruit Pie";
                    break;
                default:
                    food = null;
            }

            if (food != null) {
                foods.put(food, foods.get(food) + 1);
            } else {
                ingredients.push(ingredient + 3);
            }
        }

        List<Integer> values = foods.values().stream().filter(e -> e > 0).collect(Collectors.toList());
        if (values.size() < 4) {
            System.out.println("Ugh, what a pity! You didn't have enough materials to cook everything.");
        } else {
            System.out.println("Wohoo! You succeeded in cooking all the food!");
        }

        if (liquids.isEmpty()) {
            System.out.println("Liquids left: none");
        } else {
            List<String> strLiquids = liquids.stream().map(String::valueOf).collect(Collectors.toList());
            System.out.printf("Liquids left: %s%n", String.join(", ", strLiquids));
        }

        if (ingredients.isEmpty()) {
            System.out.println("Ingredients left: none");
        } else {
            List<String> strIngredients = ingredients.stream().map(String::valueOf).collect(Collectors.toList());
            System.out.printf("Ingredients left: %s%n", String.join(", ", strIngredients));
        }

        foods.forEach((k, v) -> System.out.printf("%s: %d%n", k, v));
    }
}
