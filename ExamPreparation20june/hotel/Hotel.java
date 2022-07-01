package ExamPreparation20june.hotel;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    List<Person> people;
    private String name;
    private int capacity;

    public Hotel(String name, int capacity) {
        this.people = new ArrayList<>();
        this.name = name;
        this.capacity = capacity;
    }

    public void add(Person person) {
        if (this.people.size() < this.capacity) {
            people.add(person);
        }
    }

    public boolean remove(String name) {
        return people.removeIf(person -> person.getName().equals(name));
    }

    public Person getPerson(String name, String hometown) {
        return people.stream().
                filter(person -> person.getName().equals(name) && person.getHometown().equals(hometown))
                .findFirst()
                .orElse(null);
    }

    public int getCount() {
        return this.people.size();
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("The people in the ExamPreparation20june.hotel %s are:%n", this.name));
        this.people.forEach(person -> sb.append(person.toString()));
        return sb.toString();
    }
}
