package javaquizapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.*;
import java.io.*;

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String path) {
        backgroundImage = new ImageIcon(path).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

public class BattleQuiz extends JFrame implements ActionListener {

    private JLabel questionLabel, timerLabel, battleStatusLabel, resultLabel, hurryLabel;
    private JRadioButton optionA, optionB, optionC, optionD;
    private JButton nextButton;
    private ButtonGroup optionsGroup;
    private int currentQuestion = 0;
    private int score = 0;
    private int health = 100;
    private int timeLeft = 60;
    private Timer timer;
    private boolean timerRunning = false;
    private BackgroundPanel backgroundPanel;

    // 30 Questions
    private String[] questions = {
        "What does JVM stand for?",
        "Which component provides the runtime environment?",
        "What is JRE full form?",
        "What does a Java compiler generate?",
        "What is inheritance in Java?",
        "What is polymorphism in Java?",
        "Which keyword is used to inherit a class?",
        "Which method starts a thread?",
        "What is size of int in Java?",
        "Which operator is used to allocate memory in Java?",
        "What is default value of boolean?",
        "Which is not a Java feature?",
        "What is the superclass of every class in Java?",
        "Which keyword makes a method unchangeable?",
        "What is encapsulation?",
        "Which access specifier is most restrictive?",
        "What is output of: 3+2*2?",
        "Which loop checks condition first?",
        "When finally block is executed?",
        "Which is valid array declaration in Java?",
        "Which symbol is used to inherit an interface?",
        "Which exception is thrown when dividing by zero?",
        "What is correct way to create an object in Java?",
        "Which keyword is used for exception handling?",
        "Which operator is used for comparing two values?",
        "Which is correct syntax of for-each loop?",
        "Which method converts string to int?",
        "What does System.out.println() do?",
        "What is the output of: \"5\" + 2 + 3?",
        "Which statement is true about Java?"
    };

    private String[][] options = {
        {"Java Virtual Machine", "Java Valuable Method", "Just Virtual Machine", "Java Verified Machine"},
        {"JDK", "JVM", "JRE", "Compiler"},
        {"Java Runtime Environment", "Java Real Environment", "Java Runtime Exception", "Java Reactive Environment"},
        {"Source code", "Bytecode", "Machine code", "Script"},
        {"Deriving new class from existing one", "Hiding data", "Overloading methods", "None"},
        {"Ability to take many forms", "Hiding data", "Extending classes", "None"},
        {"extends", "implements", "inherits", "this"},
        {"start()", "run()", "execute()", "launch()"},
        {"32 bits", "16 bits", "64 bits", "8 bits"},
        {"new", "malloc", "alloc", "create"},
        {"true", "false", "null", "undefined"},
        {"Object-oriented", "Dynamic", "Slow", "Robust"},
        {"Object", "Class", "Superclass", "Throwable"},
        {"final", "static", "const", "immutable"},
        {"Wrapping data and methods together", "Hiding data only", "Overloading methods", "None"},
        {"private", "protected", "public", "default"},
        {"7", "8", "10", "6"},
        {"while", "do-while", "for", "switch"},
        {"Always", "Never", "Only on exception", "At program start"},
        {"int arr[] = new int[5];", "int arr[5];", "array int[] = new int();", "int[] array;"},
        {"extends", "implements", "inherits", "interface"},
        {"ArithmeticException", "NullPointerException", "ArrayIndexOutOfBoundsException", "IOException"},
        {"Class obj = new Class();", "new Class obj();", "Class obj();", "Class = new object();"},
        {"try", "catch", "throw", "finally"},
        {"==", "=", "equals", "!="},
        {"for(type var : array)", "foreach(type var in array)", "for(type var from array)", "for each(type var : array)"},
        {"Integer.parseInt()", "String.toInteger()", "parseInt.String()", "Integer.toString()"},
        {"Prints text to the console", "Scans user input", "Compiles the code", "Terminates program"},
        {"523", "10", "7", "Error"},
        {"Java supports multiple inheritance through classes", "Java does not support inheritance", "Java supports interfaces", "Java is not object-oriented"}
    };

    private char[] correctAnswers = {
        'A', 'B', 'A', 'B', 'A', 'A', 'A', 'A', 'A', 'A',
        'B', 'C', 'A', 'A', 'A', 'A', 'A', 'B', 'A', 'A',
        'B', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'C'
    };

    public BattleQuiz() {
        setTitle("⚔️ Java Battle Quiz ⚔️");
        setSize(1280, 850);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        backgroundPanel = new BackgroundPanel("JavaQuizApp/src/javaquizapp/resources/background1.jpg");
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(null);

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
        questionLabel.setForeground(new Color(255, 215, 0));
        questionLabel.setBounds(100, 30, 1000, 70);
        backgroundPanel.add(questionLabel);

        optionA = createOptionButton(100, 150, new Color(135, 206, 250));
        optionB = createOptionButton(100, 230, new Color(255, 182, 193));
        optionC = createOptionButton(100, 310, new Color(144, 238, 144));
        optionD = createOptionButton(100, 390, new Color(255, 160, 122));

        optionsGroup = new ButtonGroup();
        optionsGroup.add(optionA);
        optionsGroup.add(optionB);
        optionsGroup.add(optionC);
        optionsGroup.add(optionD);

        nextButton = new JButton("Next");
        nextButton.setBounds(500, 500, 250, 50);
        nextButton.setBackground(Color.ORANGE);
        nextButton.setFont(new Font("Tahoma", Font.BOLD, 24));
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(this);
        backgroundPanel.add(nextButton);

        timerLabel = new JLabel("Time left: 60s");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timerLabel.setForeground(Color.GREEN);
        timerLabel.setBounds(1000, 20, 250, 50);
        backgroundPanel.add(timerLabel);

        battleStatusLabel = new JLabel(" Health: 100");
        battleStatusLabel.setFont(new Font("Arial", Font.BOLD, 24));
        battleStatusLabel.setForeground(Color.PINK);
        battleStatusLabel.setBounds(1000, 80, 250, 50);
        backgroundPanel.add(battleStatusLabel);

        resultLabel = new JLabel("");
        resultLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        resultLabel.setForeground(Color.YELLOW);
        resultLabel.setBounds(400, 600, 500, 50);
        backgroundPanel.add(resultLabel);

        hurryLabel = new JLabel("");
        hurryLabel.setFont(new Font("Arial", Font.BOLD, 28));
        hurryLabel.setForeground(Color.RED);
        hurryLabel.setBounds(500, 100, 400, 40);
        backgroundPanel.add(hurryLabel);

        loadQuestion();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JRadioButton createOptionButton(int x, int y, Color color) {
        JRadioButton button = new JRadioButton();
        button.setBounds(x, y, 1000, 50);
        button.setBackground(color);
        button.setFont(new Font("Tahoma", Font.PLAIN, 22));
        backgroundPanel.add(button);
        return button;
    }

    private void startTimer() {
        if (timerRunning) return;
        timerRunning = true;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                timeLeft--;
                timerLabel.setText("Time left: " + timeLeft + "s");

                if (timeLeft <= 10) {
                    timerLabel.setForeground(Color.RED);
                    hurryLabel.setText(" Hurry Up!");
                }

                if (timeLeft <= 0) {
                    timer.cancel();
                    nextQuestion();
                }
            }
        }, 1000, 1000);
    }

    private void loadQuestion() {
        if (currentQuestion < questions.length) {
            questionLabel.setText((currentQuestion + 1) + ". " + questions[currentQuestion]);
            optionA.setText("A. " + options[currentQuestion][0]);
            optionB.setText("B. " + options[currentQuestion][1]);
            optionC.setText("C. " + options[currentQuestion][2]);
            optionD.setText("D. " + options[currentQuestion][3]);
            optionsGroup.clearSelection();
            resultLabel.setText("");
            hurryLabel.setText("");
            timeLeft = 60;
            timerRunning = false;
            startTimer();
        } else {
            finishQuiz();
        }
    }

    private void nextQuestion() {
        timer.cancel();
        boolean answeredCorrectly = false;

        if (optionA.isSelected() && correctAnswers[currentQuestion] == 'A' ||
            optionB.isSelected() && correctAnswers[currentQuestion] == 'B' ||
            optionC.isSelected() && correctAnswers[currentQuestion] == 'C' ||
            optionD.isSelected() && correctAnswers[currentQuestion] == 'D') {
            score++;
            health += 20;
            if (health > 100) health = 100;
            battleStatusLabel.setText(" Health: " + health);
            resultLabel.setText(" Victory!");
            playSound("JavaQuizApp/src/javaquizapp/resources/victory.wav");
            answeredCorrectly = true;
        } else {
            health -= 10;
            if (health < 0) health = 0;
            battleStatusLabel.setText(" Health: " + health);
            resultLabel.setText(" Damage!");
            playSound("JavaQuizApp/src/javaquizapp/resources/damage.wav");
        }

        if (health == 0) {
            finishQuiz();
            return;
        }

        currentQuestion++;
        loadQuestion();
    }

    private void finishQuiz() {
        double calculatedScore = (score / (double) questions.length) * 300;
        Result resultScreen = new Result();
        resultScreen.getPoints(calculatedScore);
        resultScreen.setVisible(true);
        this.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        nextQuestion();
    }

    private void playSound(String soundFile) {
        try {
            File file = new File(soundFile);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error playing sound: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new BattleQuiz();
    }
}
