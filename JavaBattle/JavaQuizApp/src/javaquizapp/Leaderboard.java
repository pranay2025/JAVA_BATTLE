package javaquizapp;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Leaderboard extends JFrame {
    private JTextArea leaderboardArea;

    public Leaderboard() {
        setTitle("🏆 Leaderboard");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 245, 245));
        setLocationRelativeTo(null);

        // Title Label
        JLabel titleLabel = new JLabel("Top Scores", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Leaderboard Text Area
        leaderboardArea = new JTextArea();
        leaderboardArea.setEditable(false);
        leaderboardArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        leaderboardArea.setMargin(new Insets(10, 10, 10, 10));
        leaderboardArea.setBackground(Color.WHITE);
        leaderboardArea.setForeground(Color.DARK_GRAY);

        JScrollPane scrollPane = new JScrollPane(leaderboardArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        add(scrollPane, BorderLayout.CENTER);

        // Close Button
        JButton closeButton = new JButton("Close");
        closeButton.setFocusPainted(false);
        closeButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        closeButton.addActionListener(e -> dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load leaderboard data
        loadLeaderboard();
    }

    private void loadLeaderboard() {
        File file = new File("leaderboard.txt");
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                StringBuilder sb = new StringBuilder();
                while (scanner.hasNextLine()) {
                    sb.append(scanner.nextLine()).append("\n");
                }
                leaderboardArea.setText(sb.toString());
            } catch (IOException e) {
                leaderboardArea.setText("⚠ Failed to load leaderboard.");
            }
        } else {
            leaderboardArea.setText("No leaderboard data yet.");
        }
    }
}
