package Exam19February2022.parrots;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cage {
    private String name;
    private int capacity;
    private List<Parrot> data;

    public Cage(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        data = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void add(Parrot parrot) {
        if (data.size() < capacity) {
            data.add(parrot);
        }
    }

    public boolean remove(String name) {
        return data.removeIf(e -> e.getName().equals(name));
    }

    public Parrot sellParrot(String name) {
        Parrot parrot = data.stream()
                .filter(e -> e.getName().equals(name))
                .findFirst()
                .orElse(null);
        if (parrot != null) {
            parrot.setAvailable(false);
        }
        return parrot;
    }

    public List<Parrot> sellParrotBySpecies(String species) {
        List<Parrot> forSell = data.stream()
                .filter(parrot -> parrot.getSpecies().equals(species))
                .collect(Collectors.toList());

        forSell.forEach(parrot -> parrot.setAvailable(false));
        return forSell;
    }

    public int count() {
        return data.size();
    }

    public String report() {
        List<Parrot> available = data.stream().filter(Parrot::isAvailable).collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Parrots available at %s:", name)).append(System.lineSeparator());

        for (int i = 0; i < available.size(); i++) {
            sb.append(available.get(i));

            if (i < available.size() - 1) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
