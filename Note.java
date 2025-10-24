import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Note {
    private String content;
    private LocalDateTime createdAt;
    private int id;
    
    private static final DateTimeFormatter formatter = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public Note(String content) {
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }
    
    public Note(int id, String content, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
    }
    
    // Getters
    public String getContent() { return content; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public int getId() { return id; }
    public String getFormattedTimestamp() { 
        return createdAt.format(formatter); 
    }
    
    // Setters
    public void setContent(String content) { this.content = content; }
    public void setId(int id) { this.id = id; }
    
    @Override
    public String toString() {
        return "[" + getFormattedTimestamp() + "]\n" + content;
    }
    
    public String toFileString() {
        return id + "|" + createdAt + "|" + content.replace("\n", "%%NEWLINE%%");
    }
    
    public static Note fromFileString(String fileString) {
        String[] parts = fileString.split("\\|", 3);
        if (parts.length == 3) {
            int id = Integer.parseInt(parts[0]);
            LocalDateTime createdAt = LocalDateTime.parse(parts[1]);
            String content = parts[2].replace("%%NEWLINE%%", "\n");
            return new Note(id, content, createdAt);
        }
        return null;
    }
}