

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProgressBarSequentialDemo extends JFrame {

    private JProgressBar progressBar;
    private JButton startButton;

    public ProgressBarSequentialDemo() {
        // --- UI Setup & Configuration ---
        setTitle("SwingWorker (Seq) Example");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Initialize progress bar: range 0-100, custom size, show % text
        progressBar = new JProgressBar(0, 100);
        progressBar.setPreferredSize(new Dimension(250, 30));
        progressBar.setStringPainted(true);

        // Define button and trigger the background task on click
        startButton = new JButton("Start");
        startButton.addActionListener(e -> startBackgroundTask());

        // Add components to the frame and display
        add(progressBar);
        add(startButton);
        setVisible(true);
    }

    private void startBackgroundTask() {
        // 1. UI Preparation (runs on Event Dispatch Thread)
        startButton.setEnabled(false);
        startButton.setText("Processing...");
        progressBar.setValue(0);

        // SwingWorker<ReturnType, IntermediateType>
        SwingWorker<Void, Integer> worker = new SwingWorker<>() {

            @Override
            protected Void doInBackground() throws Exception {
                // 2. Heavy Lifting (runs on Background Thread)
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(50); // Simulating work

                    // Send progress to the process() method
                    publish(i);
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                // 3. UI Updates (runs on Event Dispatch Thread)
                // We take the latest chunk to update the bar smoothly
                int latestValue = chunks.get(chunks.size() - 1);
                progressBar.setValue(latestValue);

                // Optional: Update text dynamically
                progressBar.setString("Loading... " + latestValue + "%");
            }

            @Override
            protected void done() {
                // 4. Cleanup (runs on Event Dispatch Thread)
                // After background tasks are done
                startButton.setEnabled(true);
                startButton.setText("Restart?");
                progressBar.setString("Task Complete!");

                // Interaction: a simple non-blocking notification
                System.out.println("Background thread finished successfully.");
            }
        };

        worker.execute(); // Essential: kicks off the background thread
    }




        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new ProgressBarSequentialDemo());
        }

}