package edu.ccrm.cli;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.*;
import edu.ccrm.service.*;

import java.nio.file.*;
import java.util.Scanner;

public class Main {
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();

    public static void main(String[] args) throws Exception {
        System.out.println("CCRM - Campus Course & Records Manager");
        System.out.println("Data dir: " + AppConfig.getInstance().getDataDir());
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {L
            showMenu();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1" -> createSampleData();
                case "2" -> addStudent(sc);
                case "3" -> addCourse(sc);
                case "4" -> enrollStudent(sc);
                case "5" -> recordMarks(sc);
                case "6" -> printTranscript(sc);
                case "7" -> exportData();
                case "0" -> { running = false; System.out.println("Exiting..."); }
                default -> System.out.println("Unknown choice");
            }
        }
        sc.close();
    }

    private static void showMenu(){
        System.out.println("\nMenu:\n1) Create sample data\n2) Add Student\n3) Add Course\n4) Enroll Student\n5) Record Marks\n6) Print Transcript (GPA)\n7) Export students.csv\n0) Exit\nChoose:");
    }

    private static void createSampleData(){
        studentService.addStudent("S001","Alice","alice@example.com");
        studentService.addStudent("S002","Bob","bob@example.com");
        Course c1 = new Course.Builder().code("CS101").title("Intro to CS").credits(4).instructor("Dr. X").semester(Semester.FALL).build();
        Course c2 = new Course.Builder().code("MA101").title("Calculus").credits(3).instructor("Dr. Y").semester(Semester.FALL).build();
        courseService.addCourse(c1);
        courseService.addCourse(c2);
        enrollmentService.enroll("S001","CS101");
        enrollmentService.enroll("S001","MA101");
        enrollmentService.enroll("S002","CS101");
        System.out.println("Sample data created.");
    }

    private static void addStudent(Scanner sc){
        System.out.print("RegNo: "); String r = sc.nextLine().trim();
        System.out.print("Name: "); String n = sc.nextLine().trim();
        System.out.print("Email: "); String e = sc.nextLine().trim();
        studentService.addStudent(r,n,e);
        System.out.println("Added student: " + r);
    }

    private static void addCourse(Scanner sc){
        System.out.print("Code: "); String c = sc.nextLine().trim();
        System.out.print("Title: "); String t = sc.nextLine().trim();
        System.out.print("Credits: "); int cr = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Instructor: "); String ins = sc.nextLine().trim();
        Course course = new Course.Builder().code(c).title(t).credits(cr).instructor(ins).build();
        courseService.addCourse(course);
        System.out.println("Added course: " + c);
    }

    private static void enrollStudent(Scanner sc){
        System.out.print("RegNo: "); String r = sc.nextLine().trim();
        System.out.print("CourseCode: "); String c = sc.nextLine().trim();
        enrollmentService.enroll(r,c);
        System.out.println("Enrolled.");
    }

    private static void recordMarks(Scanner sc){
        System.out.print("RegNo: "); String r = sc.nextLine().trim();
        System.out.print("CourseCode: "); String c = sc.nextLine().trim();
        System.out.print("Marks (0-100): "); int m = Integer.parseInt(sc.nextLine().trim());
        enrollmentService.recordMarks(r,c,m);
        System.out.println("Recorded.");
    }

    private static void printTranscript(Scanner sc){
        System.out.print("RegNo: "); String r = sc.nextLine().trim();
        studentService.getStudent(r).ifPresentOrElse(s -> {
            System.out.println(s);
            enrollmentService.listEnrollments(r).forEach(System.out::println);
            double gpa = enrollmentService.computeGPA(r, courseService);
            System.out.printf("GPA: %.2f\n", gpa);
        }, () -> System.out.println("Student not found."));
    }

    private static void exportData(){
        try {
            Path out = AppConfig.getInstance().getDataDir().resolve("students.csv");
            Files.createDirectories(out.getParent());
            StringBuilder sb = new StringBuilder();
            sb.append("regNo,fullName,email\n");
            for (var s : studentService.listStudents()){
                sb.append(s.getRegNo()).append(",").append(s.getFullName()).append(",").append(s.getEmail()).append("\n");
            }
            Files.writeString(out, sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Exported to " + out);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
