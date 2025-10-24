# Notes Manager - Java File I/O Application

A comprehensive text-based notes management system built with Java that demonstrates file input/output operations and data persistence. This application allows users to create, view, search, and manage notes with automatic file storage.

## 📝 Overview

The Notes Manager is a multi-class Java application that provides a command-line interface for managing personal notes. It showcases Java File I/O operations using FileReader, FileWriter, and BufferedReader while implementing proper Object-Oriented Programming principles.

## ✨ Features

### Note Management
- **Create Notes**: Add new notes with automatic timestamping
- **View All Notes**: Display all saved notes with creation dates
- **Search Notes**: Find notes by keywords in content or timestamps
- **Delete Notes**: Remove individual notes by selection
- **Clear All Notes**: Remove all notes with confirmation

### File Operations
- **Automatic Persistence**: Notes automatically saved to `notes.txt`
- **Data Recovery**: Notes loaded from file on application startup
- **File Format**: Custom serialization format for note storage
- **File Demo**: Built-in demonstration of FileReader operations

### User Experience
- **Intuitive Menu System**: Easy-to-navigate command-line interface
- **Input Validation**: Robust error handling and user input validation
- **Timestamp Tracking**: Automatic date/time tracking for all notes
- **Note IDs**: Unique identification for each note

## 🏗️ System Architecture

### Class Structure

```
NotesApp/
├── NotesApp.java          # Main application entry point
├── Menu.java              # User interface and menu management
├── NoteManager.java       # Business logic for note operations
├── Note.java              # Note entity with properties and methods
└── FileHandler.java       # File I/O operations and persistence
```

### Class Responsibilities

- **NotesApp**: Application entry point, initializes the menu system
- **Menu**: Handles user interaction, displays menus, processes choices
- **NoteManager**: Manages note operations (CRUD, search, validation)
- **Note**: Represents a note entity with content, timestamp, and ID
- **FileHandler**: Handles all file operations (save, load, clear)

## 🚀 Installation & Execution

### Prerequisites
- Java JDK 8 or higher
- VS Code, IntelliJ IDEA, or any Java-compatible IDE
- Terminal/Command Prompt access

### Running the Application

#### Using VS Code
1. Create a new Java project in VS Code
2. Copy all five Java files into your `src` directory
3. Run `NotesApp.java`

#### Using IntelliJ IDEA
1. Create a new Java project
2. Add all five Java classes to your project
3. Execute the `main` method in `NotesApp.java`

#### Using Terminal/Command Line
```bash
# Compile all Java files
javac *.java

# Run the application
java NotesApp
```

## 📖 User Guide

### Main Menu Options

1. **Create New Note**
   - Enter note content (press Enter twice to finish)
   - Automatic timestamp and ID assignment
   - Auto-saves to file after creation

2. **View All Notes**
   - Display all notes with IDs and timestamps
   - Shows total note count
   - Formatted output with separators

3. **Search Notes**
   - Search by keyword in note content or timestamps
   - Case-insensitive search
   - Displays matching results with context

4. **Delete Note**
   - View all notes first for reference
   - Select note by number for deletion
   - Confirmation and auto-save after deletion

5. **Clear All Notes**
   - Remove all notes with safety confirmation
   - Clears both memory and file storage
   - Resets note ID counter

6. **File Operations Demo**
   - Demonstrates FileReader functionality
   - Shows raw file content reading
   - Educational purpose for learning File I/O

7. **Exit**
   - Safely close the application
   - All notes are automatically persisted

### File Storage

The application creates and maintains a `notes.txt` file in the same directory with the following format:
```
1|2024-01-15T10:30:45.123|Note content on first line%%NEWLINE%%Second line of note
2|2024-01-15T11:15:20.456|Another note with multiple%%NEWLINE%%lines of text
```

## 🛠️ Technical Implementation

### File I/O Operations

#### Writing to File (FileWriter)
```java
try (FileWriter writer = new FileWriter(NOTES_FILE)) {
    for (Note note : notes) {
        writer.write(note.toFileString() + "\n");
    }
}
```

#### Reading from File (BufferedReader)
```java
try (BufferedReader reader = new BufferedReader(new FileReader(NOTES_FILE))) {
    String line;
    while ((line = reader.readLine()) != null) {
        // Process each line
    }
}
```

#### Direct File Reading (FileReader)
```java
try (FileReader fileReader = new FileReader(NOTES_FILE)) {
    int character;
    while ((character = fileReader.read()) != -1) {
        // Process each character
    }
}
```

### Object-Oriented Programming Principles

- **Encapsulation**: Private fields with public getters/setters
- **Abstraction**: Complex file operations hidden behind simple methods
- **Single Responsibility**: Each class has a specific purpose
- **Composition**: System composed of specialized classes working together

### Error Handling
- Try-with-resources for automatic resource management
- Comprehensive IOException handling
- User-friendly error messages
- Graceful degradation when file operations fail

## 🎯 Learning Outcomes

### File I/O Concepts
- FileWriter for writing text files
- FileReader for character-based file reading
- BufferedReader for efficient line reading
- File input/output exception handling
- Data serialization and deserialization

### Data Persistence
- Understanding file-based data storage
- Application state persistence between sessions
- File format design and implementation
- Data recovery and integrity

### Java Programming
- Object-Oriented Design principles
- Collection management with ArrayList
- DateTime API for timestamping
- Scanner class for user input
- String manipulation and formatting

## 🔧 Extensibility

The system can be enhanced with:

- **Note Categories**: Organize notes by categories or tags
- **Note Editing**: Modify existing note content
- **Export Functionality**: Export notes to different formats
- **Backup System**: Automatic backup creation
- **Encryption**: Secure note storage with encryption
- **GUI Interface**: Graphical user interface version
- **Cloud Sync**: Synchronization with cloud storage
- **Rich Text Support**: Formatting options for notes

## 📋 Sample Usage

```
=== MAIN MENU ===
1. Create New Note
2. View All Notes (3)
3. Search Notes
4. Delete Note
5. Clear All Notes
6. File Operations Demo
7. Exit
Choose an option (1-7): 2

=== ALL NOTES ===
Total Notes: 3
============================================================
Note #1 (ID: 1):
[2024-01-15 10:30:45]
Meeting notes
- Discuss project requirements
- Assign tasks to team members
------------------------------------------------------------
```

## 🤝 Contributing

Contributions are welcome! Feel free to fork this project and submit pull requests for any improvements, bug fixes, or additional features.
