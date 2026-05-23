package CommonFunction;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class FileChooserHelper {

    /**
     * Opens a file chooser dialog and returns the canonical path of the selected file.
     * 
     * @return The canonical path of the selected file, or null if no file was selected.
     */
    public static String openFileChooser() {
        return openFileChooserDialog(null);
    }

    /**
     * Automated method that uses provided file path or opens file chooser dialog if path is null/empty.
     * If filePath is provided and valid, it will be validated and its canonical path returned.
     * Otherwise, a file chooser dialog will be opened.
     * 
     * @param filePath The file path to use. If null or empty, a dialog will be opened.
     * @return The canonical path of the selected/validated file, or null if no file was found.
     */
    public static String openFileChooser(String filePath) {
        // If file path is provided, validate and use it
        if (filePath != null && !filePath.isEmpty()) {
            return validateAndGetCanonicalPath(filePath);
        }
        
        // Otherwise, open file chooser dialog
        return openFileChooserDialog(null);
    }

    /**
     * Automated method with file filter that uses provided file path or opens file chooser dialog.
     * If filePath is provided and valid, it will be validated and its canonical path returned.
     * Otherwise, a file chooser dialog with filter will be opened.
     * 
     * @param filePath The file path to use. If null or empty, a dialog will be opened.
     * @param fileFilter The file extension filter (e.g., "pdf", "txt", "jpg")
     * @return The canonical path of the selected/validated file, or null if no file was found.
     */
    public static String openFileChooser(String filePath, String fileFilter) {
        // If file path is provided, validate and use it
        if (filePath != null && !filePath.isEmpty()) {
            return validateAndGetCanonicalPath(filePath);
        }
        
        // Otherwise, open file chooser dialog with filter
        return openFileChooserDialog(fileFilter);
    }

    /**
     * Validates a user-provided file path and returns its canonical path.
     * 
     * @param filePath The file path to validate
     * @return The canonical path of the file, or null if the file does not exist or is invalid
     */
    public static String validateAndGetCanonicalPath(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            System.out.println("File path is empty or null.");
            return null;
        }
        
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            try {
                String canonicalPath = file.getCanonicalPath();
                System.out.println("File path validated. Canonical path: " + canonicalPath);
                return canonicalPath;
            } catch (IOException e) {
                System.out.println("Error getting canonical path: " + e.getMessage());
                return null;
            }
        } else {
            System.out.println("Invalid file path provided: " + filePath + " (file does not exist or is not a file)");
            System.out.println("Opening file chooser dialog instead...");
            return openFileChooserDialog(null);
        }
    }

    /**
     * Internal method to open the file chooser dialog.
     * 
     * @param fileFilter The file extension filter (e.g., "pdf", "txt", "jpg"), or null for no filter
     * @return The canonical path of the selected file, or null if no file was selected.
     */
    private static String openFileChooserDialog(String fileFilter) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a File");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        // Add file filter if provided
        if (fileFilter != null && !fileFilter.isEmpty()) {
            javax.swing.filechooser.FileNameExtensionFilter filter = 
                new javax.swing.filechooser.FileNameExtensionFilter(
                    fileFilter.toUpperCase() + " Files", fileFilter);
            fileChooser.setFileFilter(filter);
        }
        
        int result = fileChooser.showOpenDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                String canonicalPath = selectedFile.getCanonicalPath();
                System.out.println("Selected file canonical path: " + canonicalPath);
                return canonicalPath;
            } catch (IOException e) {
                System.out.println("Error getting canonical path: " + e.getMessage());
                return null;
            }
        } else {
            System.out.println("No file selected.");
            return null;
        }
    }
}
