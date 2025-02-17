package notessecured.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import notessecured.database.Database;
import notessecured.models.Note;

import java.io.IOException;
import java.util.List;

/**
 * Controller for the main application window.
 * Handles displaying, adding, editing, and deleting notes.
 */
public class MainController {

    @FXML
    private ListView<Note> notesListView; // List to display notes

    @FXML
    private Button editButton, deleteButton; // Buttons for editing and deleting notes

    private ObservableList<Note> notesList = FXCollections.observableArrayList(); // List to store notes

    /**
     * Called automatically when the window loads.
     * Loads notes from the database and sets up the UI.
     */
    @FXML
    public void initialize() {
        loadNotes(); // Load notes from the database
        notesListView.setItems(notesList); // Display notes in ListView

        // Disable edit and delete buttons when no note is selected
        editButton.setDisable(true);
        deleteButton.setDisable(true);

        // Enable buttons only when a note is selected
        notesListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            boolean selected = newSelection != null;
            editButton.setDisable(!selected);
            deleteButton.setDisable(!selected);
        });
    }

    /**
     * Loads notes from the database and updates the list.
     */
    private void loadNotes() {
        notesList.setAll(Database.getAllNotes()); // Get all notes and update the list
    }

    /**
     * Opens the Add Note window.
     */
    public void openAddNoteWindow() {
        openNoteWindow(null); // Pass null to indicate adding a new note
    }

    /**
     * Opens the Edit Note window for the selected note.
     */
    @FXML
    private void openEditNoteWindow() {
        Note selectedNote = notesListView.getSelectionModel().getSelectedItem();
        if (selectedNote != null) {
            openNoteWindow(selectedNote); // Open window with selected note data
        }
    }

    /**
     * Opens the Add/Edit Note window.
     * @param note The note to be edited, or null if adding a new note.
     */
    private void openNoteWindow(Note note) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/notessecured/views/add-note-view.fxml"));
            Parent root = loader.load();

            AddNoteController controller = loader.getController();
            if (note != null) {
                controller.setNote(note); // Set note data if editing
            }

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(note == null ? "Add Note" : "Edit Note"); // Set window title
            stage.showAndWait(); // Wait until window is closed

            loadNotes(); // Refresh notes after closing
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes the selected note from the database.
     */
    @FXML
    private void deleteSelectedNote() {
        Note selectedNote = notesListView.getSelectionModel().getSelectedItem();
        if (selectedNote != null) {
            Database.deleteNote(selectedNote.getId()); // Delete from database
            loadNotes(); // Refresh notes after deletion
        }
    }
}
