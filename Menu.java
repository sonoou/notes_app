import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private NoteManager noteManager;
    private boolean running;
    
    public Menu() {
        this.scanner = new Scanner(System.in);
        this.noteManager = new NoteManager();
        this.running = true;
    }
    
    public void start() {
        displayWelcomeMessage();
        
        while (running) {
            displayMainMenu();
            int choice = getMenuChoice();
            processChoice(choice);
        }
        
        scanner.close();
        displayExitMessage();
    }
    
    private void displayWelcomeMessage() {
        System.out.println("====================================");
        System.out.println("          NOTES MANAGER");
        System.out.println("====================================");
        System.out.println("Your notes are automatically saved to 'notes.txt'");
    }
    
    private void displayExitMessage() {
        System.out.println("Thank you for using Notes Manager!");
        System.out.println("Your notes are saved in 'notes.txt'");
    }
    
    private void displayMainMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. Create New Note");
        System.out.println("2. View All Notes (" + noteManager.getNotesCount() + ")");
        System.out.println("3. Search Notes");
        System.out.println("4. Delete Note");
        System.out.println("5. Clear All Notes");
        System.out.println("6. File Operations Demo");
        System.out.println("7. Exit");
        System.out.print("Choose an option (1-7): ");
    }
    
    private int getMenuChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private void processChoice(int choice) {
        switch (choice) {
            case 1:
                noteManager.createNote(scanner);
                break;
            case 2:
                noteManager.viewAllNotes();
                break;
            case 3:
                noteManager.searchNotes(scanner);
                break;
            case 4:
                noteManager.deleteNote(scanner);
                break;
            case 5:
                noteManager.clearAllNotes(scanner);
                break;
            case 6:
                FileHandler.demonstrateFileReader();
                break;
            case 7:
                running = false;
                break;
            default:
                System.out.println("✗ Invalid option! Please choose 1-7.");
        }
    }
}