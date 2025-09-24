package edu.ccrm.cli;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.*;
import edu.ccrm.service.*;

import java.nio.file.*;
import java.util.Scanner;

public class Main {
    // services handling students, courses & enrollments
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();

    public static void main(String[] args) throws Exception {
        // app header
        System.out.println("CCRM - Campus Course & Records Manager");
        System.out.println("Data dir: " + AppConfig.getInstance().getDataDir());

        Scanner sc = new Scanner(System.in);
        boolean sus = true;

        // main prog loop
        while (sus) {
            showMenu(); // display menu
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1" -> createSampleData();   // creating sample data
                case "2" -> addStud(sc);       
                case "3" -> addCor(sc);        
                case "4" -> enrolStud(sc);    // enroll student
                case "5" -> markRec(sc);      // record marks
                case "6" -> printTranscript(sc);  // gpa and transcript
                case "7" -> exportData();         
                case "0" -> {                     
                    sus = false;
                    System.out.println("Exiting...");
                }
                default -> System.out.println("Unknown choice"); 
            }
        }
        sc.close(); 
    }

    
    private static void showMenu(){
        System.out.println(
            "\nMenu:\n" +
            "1) Create sample data\n" +
            "2) Add Student\n" +
            "3) Add Course\n" +
            "4) Enroll Student\n" +
            "5) Record Marks\n" +
            "6) Print Transcript (GPA)\n" +
            "7) Export students.csv\n" +
            "0) Exit\nChoose:"
        );
    }

    // create some predefined students, courses, & enrollments for test
    private static void createSampleData(){
        studentService.addStud("S001","Babloo","bab@example.com");
        studentService.addStud("S002","Dabloo","dab@example.com");

        Course c1 = new Course.Builder()
                .code("CS101").title("Intro to CS").credits(4).prof("Dr. X")
                .sem(Semester.FALL).build();
        Course c2 = new Course.Builder()
                .code("MA101").title("Calculus").credits(3).prof("Dr. Y")
                .sem(Semester.FALL).build();

        courseService.addCor(c1);
        courseService.addCor(c2);

        enrollmentService.enrolStud("S001","CS101");
        enrollmentService.enrolStud("S001","MA101");
        enrollmentService.enrolStud("S002","CS101");

        System.out.println("Sample data created.");
    }

    // adding a new student interactively
    private static void addStud(Scanner sc){
        System.out.print("RegNo: "); String r = sc.nextLine().trim();
        System.out.print("Name: "); String n = sc.nextLine().trim();
        System.out.print("Email: "); String e = sc.nextLine().trim();
        studentService.addStud(r,n,e);
        System.out.println("Added student: " + r);
    }

    // adding a new course interactively
    private static void addCor(Scanner sc){
        System.out.print("Code: "); String c = sc.nextLine().trim();
        System.out.print("Title: "); String t = sc.nextLine().trim();
        System.out.print("Credits: "); int cr = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Instructor: "); String prof = sc.nextLine().trim();
        Course course = new Course.Builder().code(c).title(t).credits(cr).prof(prof).build();
        courseService.addCor(course);
        System.out.println("Added course: " + c);
    }

    // enroll student
    private static void enrolStud(Scanner sc){
        System.out.print("RegNo: "); String r = sc.nextLine().trim();
        System.out.print("CourseCode: "); String c = sc.nextLine().trim();
        enrollmentService.enrolStud(r,c);
        System.out.println("Enrolled.");
    }

    // record marks
    private static void markRec(Scanner sc){
        System.out.print("RegNo: "); String r = sc.nextLine().trim();
        System.out.print("CourseCode: "); String c = sc.nextLine().trim();
        System.out.print("Marks (0-100): "); int m = Integer.parseInt(sc.nextLine().trim());
        enrollmentService.markRec(r,c,m);
        System.out.println("Recorded.");
    }

    // transcript of a student with gpa
    private static void printTranscript(Scanner sc){
        System.out.print("RegNo: "); 
        String r = sc.nextLine().trim();
        studentService.getStudent(r).ifPresentOrElse(s -> {
            System.out.println(s); //  student info
            enrollmentService.listEnrollments(r).forEach(System.out::println); //showing enrollments
            double gpa = enrollmentService.computeGPA(r, courseService); // gpa calculation
            System.out.printf("GPA: %.2f\n", gpa);
        }, () -> System.out.println("Student not found."));
    }

    // exporting student data in csv file
    private static void exportData(){
        try {
            Path out = AppConfig.getInstance().getDataDir().resolve("students.csv");
            Files.createDirectories(out.getParent());

            StringBuilder sb = new StringBuilder();
            sb.append("regNo,fullName,email\n"); 

            for (var s : studentService.listStudents()){
                sb.append(s.getRegNo()).append(",")
                  .append(s.getFullName()).append(",")
                  .append(s.getEmail()).append("\n");
            }

            Files.writeString(out, sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Exported to " + out);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}