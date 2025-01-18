package services;

import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentService {

    // Method to add a student
    public void addStudent(String name, int age, String email, String course) {
        String query = "INSERT INTO students (name, age, email, course) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);   // Set the name
            stmt.setInt(2, age);      // Set the age
            stmt.setString(3, email); // Set the email
            stmt.setString(4, course);// Set the course
            stmt.executeUpdate();     // Execute the query
            System.out.println("Student added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to view all students
    public void viewStudents() {
        String query = "SELECT * FROM students";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             var rs = stmt.executeQuery()) {
            System.out.println("Student List:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Age: " + rs.getInt("age") +
                        ", Email: " + rs.getString("email") +
                        ", Course: " + rs.getString("course"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update a student's details
    public void updateStudent(int id, String name, int age, String email, String course) {
        String query = "UPDATE students SET name = ?, age = ?, email = ?, course = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);   // Set the new name
            stmt.setInt(2, age);      // Set the new age
            stmt.setString(3, email); // Set the new email
            stmt.setString(4, course);// Set the new course
            stmt.setInt(5, id);       // Specify the ID
            int rowsAffected = stmt.executeUpdate(); // Execute the query
            if (rowsAffected > 0) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Student not found with ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a student
    public void deleteStudent(int id) {
        String query = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id); // Specify the ID
            int rowsAffected = stmt.executeUpdate(); // Execute the query
            if (rowsAffected > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student not found with ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to search for a student
    public void searchStudent(String keyword) {
        String query = "SELECT * FROM students WHERE id = ? OR name LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, keyword); // Search by ID
            stmt.setString(2, "%" + keyword + "%"); // Search by name (wildcard search)
            var rs = stmt.executeQuery(); // Execute the query
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Age: " + rs.getInt("age") +
                        ", Email: " + rs.getString("email") +
                        ", Course: " + rs.getString("course"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
