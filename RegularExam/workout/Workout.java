package workout;

import java.util.ArrayList;
import java.util.List;

public class Workout {
    private String type;
    private int exerciseCount;
    private List<Exercise> exercises;

    public Workout(String type, int exerciseCount) {
        this.type = type;
        this.exerciseCount = exerciseCount;
        this.exercises = new ArrayList<>();
    }

    public void addExercise(Exercise exercise) {
        if (exercises.size() < exerciseCount) {
            exercises.add(exercise);
        }
    }

    public boolean removeExercise(String name, String muscle) {
        return exercises.removeIf(exercise -> exercise.getName().equals(name) && exercise.getMuscle().equals(muscle));
    }

    public Exercise getExercise(String name, String muscle) {
        return exercises.stream()
                .filter(exercise -> exercise.getName().equals(name) && exercise.getMuscle().equals(muscle))
                .findFirst().orElse(null);
    }

    public Exercise getMostBurnedCaloriesExercise() {
        return exercises.stream()
                .sorted((e1, e2) -> Integer.compare(e2.getBurnedCalories(), e1.getBurnedCalories()))
                .findFirst()
                .orElse(null);
    }

    public int getExerciseCount() {
        return exercises.size();
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Workout type: %s", type)).append(System.lineSeparator());

        for (int i = 0; i < exercises.size(); i++) {
            sb.append(exercises.get(i));
            if (i < exercises.size() - 1) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
