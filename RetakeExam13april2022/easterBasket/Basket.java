package RetakeExam13april2022.easterBasket;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Basket {

    private String material;
    private int capacity;
    List<Egg> data;

    public Basket(String material, int capacity) {
        this.material = material;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void addEgg(Egg egg) {
        if (data.size() < capacity) {
            data.add(egg);
        }
    }

    public boolean removeEgg(String color) {
        return data.removeIf(e -> e.getColor().equals(color));
    }

    public Egg getStrongestEgg() {
        return data.stream()
                .max(Comparator.comparingInt(Egg::getStrength))
                .orElse(null);
    }

    public Egg getEgg(String color) {
        return data.stream()
                .filter(egg -> egg.getColor().equals(color))
                .findFirst()
                .orElse(null);
    }

    public int getCount() {
        return data.size();
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(material).append(" basket contains:").append(System.lineSeparator());
        for (int i = 0; i < data.size(); i++) {
            sb.append(data.get(i));
            if (i < data.size() - 1) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
