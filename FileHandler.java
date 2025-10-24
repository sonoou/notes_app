import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String NOTES_FILE = "notes.txt";
    
    // Save notes to file using FileWriter
    public static void saveNotes(List<Note> notes) {
        try (FileWriter writer = new FileWriter(NOTES_FILE)) {
            for (Note note : notes) {
                writer.write(note.toFileString() + "\n");
            }
            System.out.println("✓ Notes saved to file successfully!");
        } catch (IOException e) {
            System.out.println("✗ Error saving notes to file: " + e.getMessage());
        }
    }
    
    // Load notes from file using BufferedReader
    public static List<Note> loadNotes() {
        List<Note> notes = new ArrayList<>();
        File file = new File(NOTES_FILE);
        
        if (!file.exists()) {
            System.out.println("ℹ No existing notes file found. Starting fresh.");
            return notes;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(NOTES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Note note = Note.fromFileString(line);
                    if (note != null) {
                        notes.add(note);
                    }
                }
            }
            System.out.println("✓ Loaded " + notes.size() + " note(s) from file.");
        } catch (FileNotFoundException e) {
            System.out.println("ℹ Notes file not found. Starting with empty notes.");
        } catch (IOException e) {
            System.out.println("✗ Error reading notes file: " + e.getMessage());
        }
        
        return notes;
    }
    
    // Clear all notes from file
    public static void clearNotesFile() {
        try (FileWriter writer = new FileWriter(NOTES_FILE)) {
            writer.write("");
            System.out.println("✓ Notes file cleared successfully!");
        } catch (IOException e) {
            System.out.println("✗ Error clearing notes file: " + e.getMessage());
        }
    }
    
    // Demonstrate FileReader usage
    public static void demonstrateFileReader() {
        System.out.println("\n=== FILE READER DEMONSTRATION ===");
        
        try (FileReader fileReader = new FileReader(NOTES_FILE)) {
            StringBuilder content = new StringBuilder();
            int character;
            int charCount = 0;
            
            while ((character = fileReader.read()) != -1 && charCount < 500) {
                content.append((char) character);
                charCount++;
            }
            
            if (content.length() > 0) {
                System.out.println("First 500 characters of file:");
                System.out.println(content.toString());
                if (charCount == 500) {
                    System.out.println("... (file continues)");
                }
            } else {
                System.out.println("File is empty.");
            }
            
        } catch (IOException e) {
            System.out.println("Error demonstrating FileReader: " + e.getMessage());
        }
    }
}