import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NoteManager {
    private List<Note> notes;
    private int nextId;
    
    public NoteManager() {
        this.notes = new ArrayList<>();
        this.nextId = 1;
        loadNotesFromFile();
    }
    
    // Load notes from file on startup
    private void loadNotesFromFile() {
        notes = FileHandler.loadNotes();
        // Set nextId based on loaded notes
        if (!notes.isEmpty()) {
            nextId = notes.stream().mapToInt(Note::getId).max().getAsInt() + 1;
        }
    }
    
    // CREATE - Add new note
    public void createNote(Scanner scanner) {
        System.out.println("\n=== CREATE NEW NOTE ===");
        System.out.println("Enter your note (press Enter twice to finish):");
        
        StringBuilder content = new StringBuilder();
        String line;
        int emptyLineCount = 0;
        
        while (emptyLineCount < 2) {
            line = scanner.nextLine();
            if (line.isEmpty()) {
                emptyLineCount++;
            } else {
                if (emptyLineCount > 0) {
                    // Add the empty lines that were counted
                    for (int i = 0; i < emptyLineCount; i++) {
                        content.append("\n");
                    }
                    emptyLineCount = 0;
                }
                content.append(line).append("\n");
            }
        }
        
        if (content.length() > 0) {
            // Remove the last newline character
            content.setLength(content.length() - 1);
            
            Note note = new Note(content.toString());
            note.setId(nextId++);
            notes.add(note);
            
            System.out.println("✓ Note saved successfully! (ID: " + note.getId() + ")");
            FileHandler.saveNotes(notes);
        } else {
            System.out.println("✗ Note is empty! Nothing saved.");
        }
    }
    
    // READ - View all notes
    public void viewAllNotes() {
        System.out.println("\n=== ALL NOTES ===");
        
        if (notes.isEmpty()) {
            System.out.println("No notes found!");
            return;
        }
        
        System.out.println("Total Notes: " + notes.size());
        System.out.println("============================================================================================");
        
        for (int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);
            System.out.println("Note #" + (i + 1) + " (ID: " + note.getId() + "):");
            System.out.println(note);
            System.out.println("---------------------------------------------------------------------------------------------");
        }
    }
    
    // SEARCH - Search notes by keyword
    public void searchNotes(Scanner scanner) {
        System.out.println("\n=== SEARCH NOTES ===");
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine().toLowerCase();
        
        List<Note> searchResults = new ArrayList<>();
        
        for (Note note : notes) {
            if (note.getContent().toLowerCase().contains(keyword) || 
                note.getFormattedTimestamp().contains(keyword)) {
                searchResults.add(note);
            }
        }
        
        if (searchResults.isEmpty()) {
            System.out.println("No notes found containing: '" + keyword + "'");
        } else {
            System.out.println("✓ Found " + searchResults.size() + " note(s) containing: '" + keyword + "'");
            System.out.println("===========================================================================================");
            
            for (int i = 0; i < searchResults.size(); i++) {
                Note note = searchResults.get(i);
                System.out.println("Result #" + (i + 1) + " (ID: " + note.getId() + "):");
                System.out.println(note);
                System.out.println("--------------------------------------------------------------------------------------------");
            }
        }
    }
    
    // DELETE - Remove specific note
    public void deleteNote(Scanner scanner) {
        System.out.println("\n=== DELETE NOTE ===");
        
        if (notes.isEmpty()) {
            System.out.println("No notes available to delete!");
            return;
        }
        
        viewAllNotes();
        System.out.print("Enter note number to delete (1-" + notes.size() + "): ");
        
        try {
            int noteNumber = Integer.parseInt(scanner.nextLine());
            
            if (noteNumber >= 1 && noteNumber <= notes.size()) {
                Note deletedNote = notes.remove(noteNumber - 1);
                System.out.println("✓ Note deleted successfully!");
                System.out.println("Deleted content:");
                System.out.println(deletedNote);
                
                FileHandler.saveNotes(notes);
            } else {
                System.out.println("✗ Invalid note number!");
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ Please enter a valid number!");
        }
    }
    
    // CLEAR - Remove all notes
    public void clearAllNotes(Scanner scanner) {
        System.out.println("\n=== CLEAR ALL NOTES ===");
        
        if (notes.isEmpty()) {
            System.out.println("No notes to clear!");
            return;
        }
        
        System.out.print("Are you sure you want to delete ALL " + notes.size() + " notes? (y/n): ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("y")) {
            notes.clear();
            nextId = 1;
            System.out.println("✓ All notes cleared!");
            FileHandler.clearNotesFile();
        } else {
            System.out.println("Operation cancelled.");
        }
    }
    
    // Get notes count
    public int getNotesCount() {
        return notes.size();
    }
}