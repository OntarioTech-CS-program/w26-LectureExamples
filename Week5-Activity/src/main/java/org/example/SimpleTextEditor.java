package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class SimpleTextEditor extends JFrame {
    private JTextArea textArea;
    private File currentFile; // Stores the currently opened file
    private JMenuBar menuBar;

    public SimpleTextEditor() {
        // Set up the frame
        this.setTitle("Simple Text Editor");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // Create UI components
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Create menu bar
        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem saveAsItem = new JMenuItem("Save As");

        //TODO: Add menu items, then set JMenuBar to this window


        // Add components to frame
        this.add(scrollPane, BorderLayout.CENTER);

        // Add action listeners using lambda notation (Java 8+ supported)
        openItem.addActionListener(this::openFile);
        saveItem.addActionListener(this::saveFile);
        saveAsItem.addActionListener(this::saveFileAs);
    }

    /**
     * Opens a file chooser dialog that allows the user to select a file to open.
     * <p>
     * If the user approves the selection, the contents of the chosen file should
     * be read and displayed in the text area.
     * </p>
     *
     * @param e the action event triggered by the Open menu item or button
     */
    private void openFile(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);

        // TODO: If APPROVE_OPTION, read and display the file content
    }


    /**
     * Saves the current contents of the text area.
     *<p>
     * If no file has been previously selected or created, this method delegates
     * to {@link #saveFileAs(ActionEvent)} to prompt the user for a file name.
     * Otherwise, it overwrites the existing file.
     *</p>
     *
     * @param e the action event triggered by the Save menu item or button
     */
    private void saveFile(ActionEvent e) {
        if (currentFile == null) { //if file is not yet created
            saveFileAs(e);
        } else { // if file already exist
            writeFile(currentFile);
        }
    }


    /**
     * Prompts the user to choose a file location and name, then saves the contents
     * of the text area to the selected file.
     * <p>
     * This method should use a {@link JFileChooser} to obtain the file path and
     * delegate the actual writing operation to {@link #writeFile(File)}.
     * </p>
     *
     * @param e the action event triggered by the Save As menu item or button
     */
    private void saveFileAs(ActionEvent e) {
        // TODO: Get path and name from JFileChooser and use writeFile
    }

    /**
     * Writes the contents of the text area to the specified file, overwriting
     * any existing content.
     * <p>
     * This method uses a {@link PrintWriter} to write the current text area
     * contents to disk. If an error occurs during the write operation, an
     * error dialog is displayed to the user.
     * </p>
     *
     * @param file the file to which the text area contents should be written
     */
    private void writeFile(File file) {
        try (PrintWriter writer = new PrintWriter(file)) {
            textArea.write(writer);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SimpleTextEditor editorView =  new SimpleTextEditor();
        editorView.setVisible(true);
    }
}
