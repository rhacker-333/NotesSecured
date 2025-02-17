package notessecured.database;

import notessecured.models.Note;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles all database operations (CRUD - Create, Read, Update, Delete).
 */
public class Database {
    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/simplenotesdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    /**
     * Establishes a connection to the database.
     * @return Connection object.
     * @throws SQLException if connection fails.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Saves a new note or updates an existing one.
     * @param note The note to save or update.
     */
    public static void saveNote(Note note) {
        // If note ID is -1, insert a new note; otherwise, update an existing one
        String sql = (note.getId() == -1) ? 
            "INSERT INTO notes (title, content) VALUES (?, ?)" : 
            "UPDATE notes SET title = ?, content = ? WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             
            stmt.setString(1, note.getTitle());
            stmt.setString(2, note.getContent());
            
            if (note.getId() != -1) {
                stmt.setInt(3, note.getId()); // Set ID only for updates
            }
            
            stmt.executeUpdate();

            // If it's a new note, get the generated ID from the database
            if (note.getId() == -1) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        note.setId(generatedKeys.getInt(1)); // Set the auto-generated ID
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all notes from the database.
     * @return List of all notes.
     */
    public static List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        String sql = "SELECT * FROM notes";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            // Loop through result set and add notes to the list
            while (rs.next()) {
                notes.add(new Note(rs.getInt("id"), rs.getString("title"), rs.getString("content")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }

    /**
     * Deletes a note from the database.
     * @param noteId ID of the note to delete.
     */
    public static void deleteNote(int noteId) {
        String sql = "DELETE FROM notes WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, noteId); // Set ID of the note to delete
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
