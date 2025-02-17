package notessecured.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import notessecured.database.Database;
import notessecured.models.Note;

/**
 * Controller for the Add/Edit Note window.
 * Handles saving new notes and updating existing ones.
 */
public class AddNoteController {

    @FXML
    private TextField titleField; // Input field for note title

    @FXML
    private TextArea contentField; // Input field for note content

    private Note currentNote; // Stores the note if editing an existing one

    /**
     * Sets the note data when editing an existing note.
     * @param note The note to be edited.
     */
    public void setNote(Note note) {
        this.currentNote = note;
        titleField.setText(note.getTitle()); // Show current title
        contentField.setText(note.getContent()); // Show current content
    }

    /**
     * Saves the note (either a new note or an update).
     */
    @FXML
    private void saveNote() {
        String title = titleField.getText().trim();
        String content = contentField.getText().trim();

        // If title is empty, show an error message
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty!");
            return;
        }

        if (currentNote == null) {
            // If adding a new note, create a new Note object with a temporary ID (-1)
            currentNote = new Note(-1, title, content);
        } else {
            // If editing an existing note, update the title and content
            currentNote.setTitle(title);
            currentNote.setContent(content);
        }

        // Save the note to the database
        Database.saveNote(currentNote);

        // Close the window after saving
        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }
}
