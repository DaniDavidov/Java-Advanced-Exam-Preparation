package ExamPrepation17june.E03University;

import java.util.ArrayList;
import java.util.List;

public class University {
    private int capacity;
    private List<Student> students;

    public University(int capacity) {
        this.capacity = capacity;
        students = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Student> getStudents() {
        return students;
    }

    public int getStudentCount() {
        return this.students.size();
    }

    public String registerStudent(Student student) {
        String result = null;
        if (!this.students.contains(student) && getStudentCount() + 1 <= this.capacity) {
            this.students.add(student);
            result = String.format("Added student %s %s", student.getFirstName(), student.getLastName());
        } else if (this.students.contains(student)) {
            result = "Student is already in the university";
        } else if (getStudentCount() + 1 > capacity) {
            result = "No seats in the university";
        }
        return result;
    }

    public String dismissStudent(Student student) {
        if (this.students.contains(student)) {
            this.students.remove(student);
            return "Removed student " + student.getFirstName() + " " + student.getLastName();
        }

        return "Student not found";
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();

        this.students.forEach(student -> sb.append(
                String.format("==Student: First Name = %s, Last Name = %s, Best Subject = %s%n",
                        student.getFirstName(), student.getLastName(), student.getBestSubject())));
        return sb.toString();
    }

    public String getStudent(String firstName, String lastName) {
        Student student = findStudent(students, firstName, lastName);
        String result = String.format("Student: %s %s, %s", firstName, lastName, student.getBestSubject());
        return result;
    }

    private Student findStudent(List<Student> students, String firstName, String lastName) {
        for (Student student : students) {
            if (firstName.equals(student.getFirstName()) && lastName.equals(student.getLastName())) {
                return student;
            }
        }
        return null;
    }
}
