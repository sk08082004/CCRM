package edu.ccrm.domain;

import java.time.LocalDateTime;

// Abstract base class for person (student,  staff)
public abstract class Person {
    // unique identifier
    protected final String id;

    // name
    protected String fullName;

    // email id
    protected String email;

    // time at which obj was created
    protected final LocalDateTime createdAt;

    // Constructor: initializes id, fullName, email, and sets creation time
    protected Person(String id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.createdAt = LocalDateTime.now(); // capture creation time
    }

    // getters & setters
    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public void setFullName(String name) { this.fullName = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // String representation of a Person (e.g., "Namiesh (S001) <namiesh@example.com>")
    @Override
    public String toString() {
        return String.format("%s (%s) <%s>", fullName, id, email);
    }
}
