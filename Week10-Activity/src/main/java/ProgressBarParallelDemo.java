import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ProgressBarParallelDemo extends JFrame {

    private JProgressBar progressBar;
    private JButton startButton;

    public ProgressBarParallelDemo() {
        setTitle("Parallel SwingWorker (N-Thread Pool)");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        progressBar = new JProgressBar(0, 100);
        progressBar.setPreferredSize(new Dimension(250, 30));
        progressBar.setStringPainted(true);

        startButton = new JButton("Start Parallel Task");
        startButton.addActionListener(e -> startParallelTask());

        add(progressBar);
        add(startButton);
        setVisible(true);
    }

    private void startParallelTask() {
        startButton.setEnabled(false);
        progressBar.setValue(0);
        progressBar.setString("Starting Pool...");

        SwingWorker<String, Integer> worker = new SwingWorker<>() {
            @Override
            protected String doInBackground() throws Exception {
                String[] files = {"File_A", "File_B", "File_C", "File_D"};
                int total = files.length;

                // Create a pool of N=total threads
                ExecutorService executor = Executors.newFixedThreadPool(total);
                // Synchronization Barrier: Ensures doInBackground() doesn't finish
                // until all parallel threads hit the latch.countDown() signal.
                CountDownLatch latch = new CountDownLatch(total);
                // Thread-Safe Counter: We use AtomicInteger because multiple
                // threads will be updating this number at the exact same time.
                // (A regular 'int' would suffer from Race Conditions here!)
                // Alt: declare 'int' in the Demo class volatile and synchronize
                // the increment operation
                AtomicInteger filesFinished = new AtomicInteger(0);

                for (String file : files) {
                    //thread per file parsing within the SwingWorker
                    executor.submit(() -> {
                        try {
                            // Simulate heavy parsing or processing (Math + Sleep)
                            for(int j=0; j<1000000; j++) { Math.atan(Math.sqrt(j)); }
                            Thread.sleep(2000); // 2 seconds per file

                            // TODO #1: Increment the AtomicInteger safely
                            int finished = filesFinished.incrementAndGet();

                            // TODO #2: Publish the current percentage
                            publish((finished * 100) / total);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            // TODO #3: Tell the latch one worker is done
                            latch.countDown();
                        }
                    });
                }// end - for each file

                // TODO #4: Wait for all threads to finish before returning
                latch.await();

                executor.shutdown();
                return "All "+total+" files processed in parallel!";
            }

            @Override
            protected void process(List<Integer> chunks) {
                int latest = chunks.get(chunks.size() - 1);
                progressBar.setValue(latest);
                progressBar.setString("Parallel Progress: " + latest + "%");
            }

            @Override
            protected void done() {
                try {
                    String msg = get();
                    startButton.setEnabled(true);
                    startButton.setText("Restart Parallel");
                    JOptionPane.showMessageDialog(null, msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProgressBarParallelDemo());
    }
}
