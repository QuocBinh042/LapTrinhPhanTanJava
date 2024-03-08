package ThucHanh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.*;

public class FileCopyGUI extends JFrame {
    private JProgressBar progressBar;
    private JButton copyButton;

    public FileCopyGUI() {
        setTitle("File Copy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 100));

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        copyButton = new JButton("Copy Files");
        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performFileCopy();
            }
        });

        add(progressBar, BorderLayout.CENTER);
        add(copyButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void performFileCopy() {
        Path sourcePath = Paths.get("path/to/source/file.txt"); // Replace with your source file path
        Path destinationPath = Paths.get("path/to/destination/file.txt"); // Replace with your destination file path

        try {
            long fileSize = Files.size(sourcePath);

            new SwingWorker<Void, Long>() {
                @Override
                protected Void doInBackground() throws Exception {
                    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING,
                            StandardCopyOption.COPY_ATTRIBUTES, LinkOption.NOFOLLOW_LINKS);

                    return null;
                }

                @Override
                protected void process(java.util.List<Long> chunks) {
                    long value = chunks.get(chunks.size() - 1);
                    progressBar.setValue((int) ((value * 100) / fileSize));
                }

                @Override
                protected void done() {
                    progressBar.setValue(100);
                    JOptionPane.showMessageDialog(FileCopyGUI.this, "File copied successfully!");
                }
            }.execute();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error copying file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FileCopyGUI().setVisible(true);
            }
        });
    }
}
