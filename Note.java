package notessecured.models;

/**
 * Represents a Note with an ID, title, and content.
 */
public class Note {
    private int id;         // Unique ID for the note (from the database)
    private String title;   // Title of the note
    private String content; // Content of the note

    /**
     * Constructor to create a Note object.
     * @param id The note's ID (from database, -1 if new).
     * @param title The title of the note.
     * @param content The content of the note.
     */
    public Note(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // Getter and setter methods to access and modify note properties

    public int getId() {
        return id;
    }

    public void setId(int id) { 
        this.id = id; 
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) { 
        this.title = title; 
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) { 
        this.content = content; 
    }

    /**
     * This method is used when displaying notes in a ListView.
     * It returns the title of the note instead of the full object.
     */
    @Override
    public String toString() {
        return title; // Display only the title in the ListView
    }
}
