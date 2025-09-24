# CCRM - Campus Course & Records Manager  
A simple console-based system to manage **students, courses, enrollments, marks, and transcripts**.  

---

## System Requirements & Setup

- **Java**: JDK 8 or later  
- **Operating System**: Windows, Linux, or macOS  
- **Files**: Ensure `ccrm.jar` is available in your working directory.  
- **Data Directory**: By default, data files will be stored in `/data` (relative path).  

### Setup
1. Install [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html).  
2. Place `ccrm.jar` in your preferred directory.  
3. Run the program using:  
   ```bash
   java -jar ccrm.jar
   ```

---

## Menu Options

### 1. Create Sample Data
Generates some preloaded students, courses, and enrollments.  
```
Choose: 1
Sample data created
```

---

### 2. Add Student
Add a new student to the system.  
**Input format:**
```
RegNo: <StudentID>
Name: <FullName>
Email: <EmailAddress>
```

**Example:**
```
Choose: 2
RegNo: S003
Name: Alice Johnson
Email: alice@university.edu
Added student: S003
```

---

### 3. Add Course
Add a new course.  
**Input format:**
```
Code: <CourseCode>
Title: <CourseTitle>
Credits: <Credits>
Instructor: <InstructorName>
```

**Example:**
```
Choose: 3
Code: PH101
Title: Physics I
Credits: 4
Instructor: Dr. Smith
Added course: PH101
```

---

### 4. Enroll Student
Enroll a student in a course.  
**Input format:**
```
RegNo: <StudentID>
CourseCode: <CourseCode>
```

**Example:**
```
Choose: 4
RegNo: S003
CourseCode: CS101
Enrolled.
```

---

### 5. Record Marks
Record marks (0–100) for a student in a course.  
**Input format:**
```
RegNo: <StudentID>
CourseCode: <CourseCode>
Marks (0-100): <Marks>
```

**Example:**
```
Choose: 5
RegNo: S001
CourseCode: CS101
Marks (0-100): 85
Recorded.
```

---

### 6. Print Transcript
View GPA for a student.  
**Input format:**
```
RegNo: <StudentID>
```

**Example:**
```
Choose: 6
RegNo: S001
GPA: 3.25
```

---

### 7. Export Data
Export data files (CSV) to the `/data` directory.  
```
Choose: 7
Exported to /data/students.csv
```

---

### 0. Exit
Exit the application.  
```
Choose: 0
Exiting...
```

---

## Sample Data Files

### `students.csv`
```csv
regNo,fullName,email
S001,Babloo,bab@example.com
S002,Dabloo,dab@example.com
S003,Alice Johnson,alice@university.edu
```

---

## Sample Session
```
java -jar ccrm.jar

CCRM - Campus Course & Records Manager
Data dir: /path/to/data

Choose: 1
Sample data created.

Choose: 5
RegNo: S001
CourseCode: CS101
Marks (0-100): 92
Recorded.

Choose: 6
RegNo: S001
GPA: 3.67

Choose: 0
Exiting...
```
