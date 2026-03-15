package javaquizapp;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class StartPage extends JFrame implements ActionListener {
    private JButton startButton, loginButton, registerButton, submitLoginButton;
    private JLabel titleLabel, usernameLabel, passwordLabel, registerUsernameLabel, registerPasswordLabel, registerConfirmPasswordLabel, registerEmailLabel, registerNameLabel;
    private JTextField usernameField, registerUsernameField, registerEmailField, registerNameField;
    private JPasswordField passwordField, registerPasswordField, registerConfirmPasswordField;
    private JPanel loginPanel, registerPanel, quizPanel;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private String loggedInUsername = "";

    public StartPage() {
        setTitle("⚔️ Welcome to BattleQuiz ⚔️");
        setSize(1280, 850);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Background panel
        BackgroundPanel backgroundPanel = new BackgroundPanel("JavaQuizApp/src/javaquizapp/resources/StartPage.jpg");
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(null);

        titleLabel = new JLabel("⚔️ BattleQuiz ⚔️");
        titleLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 56));
        titleLabel.setForeground(new Color(255, 215, 0));
        titleLabel.setBounds(380, 50, 600, 100);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setOpaque(false);
        backgroundPanel.add(titleLabel);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBounds(0, 200, 1280, 600);

        loginPanel = createLoginPanel();
        registerPanel = createRegisterPanel();
        quizPanel = createQuizPanel();

        cardPanel.add(loginPanel, "Login");
        cardPanel.add(registerPanel, "Register");
        cardPanel.add(quizPanel, "Quiz");

        backgroundPanel.add(cardPanel);

        cardLayout.show(cardPanel, "Login");

        startButton = new JButton("Start Quiz");
        startButton.setBounds(500, 500, 350, 50);
        startButton.setFont(new Font("Tahoma", Font.BOLD, 24));
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(Color.ORANGE);
        startButton.setVisible(false);
        startButton.addActionListener(this);
        backgroundPanel.add(startButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(1280, 600));
        panel.setOpaque(false);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        usernameLabel.setBounds(450, 100, 100, 30);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(550, 100, 200, 30);
        panel.add(usernameField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        passwordLabel.setBounds(450, 150, 100, 30);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(550, 150, 200, 30);
        panel.add(passwordField);

        submitLoginButton = new JButton("Login");
        submitLoginButton.setBounds(550, 200, 200, 40);
        submitLoginButton.setBackground(Color.GREEN);
        submitLoginButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        submitLoginButton.setForeground(Color.WHITE);
        submitLoginButton.addActionListener(this);
        panel.add(submitLoginButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(550, 250, 200, 40);
        registerButton.setBackground(Color.ORANGE);
        registerButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        registerButton.setForeground(Color.WHITE);
        registerButton.addActionListener(this);
        panel.add(registerButton);

        return panel;
    }

    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(1280, 600));
        panel.setOpaque(false);

        registerNameLabel = new JLabel("Full Name:");
        registerNameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        registerNameLabel.setBounds(450, 50, 100, 30);
        panel.add(registerNameLabel);

        registerNameField = new JTextField();
        registerNameField.setBounds(550, 50, 200, 30);
        panel.add(registerNameField);

        registerEmailLabel = new JLabel("Email:");
        registerEmailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        registerEmailLabel.setBounds(450, 100, 100, 30);
        panel.add(registerEmailLabel);

        registerEmailField = new JTextField();
        registerEmailField.setBounds(550, 100, 200, 30);
        panel.add(registerEmailField);

        registerUsernameLabel = new JLabel("Username:");
        registerUsernameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        registerUsernameLabel.setBounds(450, 150, 100, 30);
        panel.add(registerUsernameLabel);

        registerUsernameField = new JTextField();
        registerUsernameField.setBounds(550, 150, 200, 30);
        panel.add(registerUsernameField);

        registerPasswordLabel = new JLabel("Password:");
        registerPasswordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        registerPasswordLabel.setBounds(450, 200, 100, 30);
        panel.add(registerPasswordLabel);

        registerPasswordField = new JPasswordField();
        registerPasswordField.setBounds(550, 200, 200, 30);
        panel.add(registerPasswordField);

        registerConfirmPasswordLabel = new JLabel("Confirm Password:");
        registerConfirmPasswordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        registerConfirmPasswordLabel.setBounds(450, 250, 200, 30);
        panel.add(registerConfirmPasswordLabel);

        registerConfirmPasswordField = new JPasswordField();
        registerConfirmPasswordField.setBounds(650, 250, 200, 30);
        panel.add(registerConfirmPasswordField);

        JButton submitRegisterButton = new JButton("Register");
        submitRegisterButton.setBounds(550, 300, 200, 40);
        submitRegisterButton.setBackground(Color.BLUE);
        submitRegisterButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        submitRegisterButton.setForeground(Color.WHITE);
        submitRegisterButton.addActionListener(this);
        panel.add(submitRegisterButton);

        return panel;
    }

    private JPanel createQuizPanel() {
        JPanel panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(1280, 600));
        panel.setOpaque(false);

        JLabel quizLabel = new JLabel("You are now logged in! Ready to start the quiz?");
        quizLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        quizLabel.setForeground(Color.BLACK);
        quizLabel.setBounds(450, 100, 500, 50);
        panel.add(quizLabel);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitLoginButton) {
            handleLogin();
        } else if (e.getSource() == registerButton) {
            cardLayout.show(cardPanel, "Register");
        } else if (e.getSource() == startButton) {
            dispose(); // Close the Start Page
            new BattleQuiz(); // Open the Main Quiz App
        } else if (e.getActionCommand().equals("Register")) {
            handleRegister();
        }
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        boolean loginSuccess = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials.length == 2 && credentials[0].equals(username) && credentials[1].equals(password)) {
                    loginSuccess = true;
                    loggedInUsername = username;
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (loginSuccess) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            startButton.setText("Start Quiz - " + loggedInUsername);
            startButton.setVisible(true);
            cardLayout.show(cardPanel, "Quiz");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials. Please try again.");
        }
    }

    private void handleRegister() {
        String name = registerNameField.getText().trim();
        String email = registerEmailField.getText().trim();
        String username = registerUsernameField.getText().trim();
        String password = new String(registerPasswordField.getPassword());
        String confirmPassword = new String(registerConfirmPasswordField.getPassword());

        if (name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Invalid Email Address!");
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!");
            return;
        }

        if (isUsernameTaken(username)) {
            JOptionPane.showMessageDialog(this, "Username already exists! Please login or choose another username.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            writer.write(username + "," + password);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Registration Successful! Please Login Now.");
            cardLayout.show(cardPanel, "Login");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error registering user. Please try again.");
        }
    }

    private boolean isUsernameTaken(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials.length >= 1 && credentials[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    public static void main(String[] args) {
        new StartPage();
    }
}