package RetakeExam15December2021.aquarium;

import java.util.ArrayList;
import java.util.List;

public class Aquarium {
    private String name;
    private int capacity;
    private int size;
    private List<Fish> fishInPool;

    public Aquarium(String name, int capacity, int size) {
        this.name = name;
        this.capacity = capacity;
        this.size = size;
        this.fishInPool = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    public int getFishInPool() {
        return fishInPool.size();
    }

    public void add(Fish fish) {
        long count = fishInPool.stream().filter(e -> e.getName().equals(fish.getName())).count();
        if (fishInPool.size() < capacity && count == 0) {
            fishInPool.add(fish);
        }
    }

    public boolean remove(String name) {
        return fishInPool.removeIf(e -> e.getName().equals(name));
    }

    public Fish findFish(String name) {
        return fishInPool.stream()
                .filter(fish -> fish.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Aquarium: %s ^ Size: %d", name, size)).append(System.lineSeparator());

        for (int i = 0; i < fishInPool.size(); i++) {
            sb.append(fishInPool.get(i));
            if (i < fishInPool.size() - 1) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
