package javaquizapp;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class Result extends javax.swing.JFrame {

    private double gp = 0.0;
    private javax.swing.JButton leaderboardButton;

    public Result() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        lbl = new javax.swing.JLabel();
        ProBar = new javax.swing.JProgressBar();
        jButton1 = new javax.swing.JButton();
        feedbackButton = new javax.swing.JButton();
        leaderboardButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbl.setFont(new java.awt.Font("Tahoma", 1, 18));
        lbl.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        ProBar.setMaximum(300);
        ProBar.setStringPainted(true);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jButton1.setText("Show Result");
        jButton1.addActionListener(evt -> jButton1ActionPerformed(evt));

        feedbackButton.setFont(new java.awt.Font("Tahoma", 1, 18));
        feedbackButton.setText("Give Feedback");
        feedbackButton.setVisible(false);
        feedbackButton.addActionListener(evt -> feedbackButtonActionPerformed(evt));

        leaderboardButton.setFont(new java.awt.Font("Tahoma", 1, 18));
        leaderboardButton.setText("View Leaderboard");
        leaderboardButton.setVisible(false);
        leaderboardButton.addActionListener(evt -> new Leaderboard().setVisible(true));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(158, 158, 158)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ProBar, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE))
                    .addContainerGap(129, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(62, 62, 62)
                    .addComponent(lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(56, 56, 56)
                    .addComponent(ProBar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(141, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(106, 106, 106)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(122, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(feedbackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(leaderboardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(375, 375, 375))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(40, 40, 40)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(15, 15, 15)
                    .addComponent(feedbackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(15, 15, 15)
                    .addComponent(leaderboardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String str = "";
        if (gp < 100) {
            str = "Hard Luck ";
        } else if (gp < 200) {
            str = "Good Job !";
        } else {
            str = "Excellent work !";
        }

        lbl.setText(" Your Marks : " + gp + " " + str);
        ProBar.setValue((int) gp);
        feedbackButton.setVisible(true);
        leaderboardButton.setVisible(true);
    }

    private void feedbackButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String username = JOptionPane.showInputDialog(this, "Enter your username:");
        String feedback = JOptionPane.showInputDialog(this, "Enter your feedback:");
        if (username != null && feedback != null && !username.trim().isEmpty() && !feedback.trim().isEmpty()) {
            saveToLeaderboard(username, gp);
            saveFeedback(username, feedback);
            JOptionPane.showMessageDialog(this, "Thank you for your feedback!");
        } else {
            JOptionPane.showMessageDialog(this, "Feedback not saved. Username or feedback is empty.");
        }
    }

    private void saveToLeaderboard(String username, double score) {
        File file = new File("leaderboard.txt");
        List<String[]> entries = new ArrayList<>();

        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split("\t");
                    if (parts.length == 2) {
                        entries.add(parts);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading leaderboard: " + e.getMessage());
            }
        }

        entries.add(new String[]{username, String.valueOf(score)});
        entries.sort((a, b) -> Double.compare(Double.parseDouble(b[1]), Double.parseDouble(a[1])));

        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            for (String[] entry : entries) {
                out.println(entry[0] + "\t" + entry[1]);
            }
        } catch (IOException e) {
            System.out.println("Error saving to leaderboard: " + e.getMessage());
        }
    }

    private void saveFeedback(String username, String feedback) {
        File feedbackFile = new File("feedback.txt");
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(feedbackFile, true)))) {
            out.println(username + "\t" + feedback);
        } catch (IOException e) {
            System.out.println("Error saving feedback: " + e.getMessage());
        }
    }

    public void getPoints(double pts) {
        gp = pts;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Result().setVisible(true));
    }

    private javax.swing.JProgressBar ProBar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton feedbackButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl;
}
