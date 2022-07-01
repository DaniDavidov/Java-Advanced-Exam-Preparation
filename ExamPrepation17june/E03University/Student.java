package ExamPrepation17june.E03University;

public class Student {
    private String firstName;
    private String lastName;
    private String bestSubject;

    public Student(String firstName, String lastName, String bestSubject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bestSubject = bestSubject;
    }

    @Override
    public String toString() {
        return String.format("Student: %s %s, %s", firstName, lastName, bestSubject);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBestSubject() {
        return bestSubject;
    }
}
