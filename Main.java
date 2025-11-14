/*
 * Main.java
 * Quiz Game with Timer (Java Swing)
 * Reads questions from "questions.txt"
 *
 * File format example:
 * Question: What is the capital of France?
 * A) Berlin
 * B) London
 * C) Paris
 * D) Madrid
 * Answer: C
 *
 * (Blank line between each question)
 */

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {

    private java.util.List<Question> questions = new ArrayList<>();
    private int currentIndex = 0;
    private int score = 0;

    private final int TIME_LIMIT = 15;  // seconds per question
    private int timeLeft = TIME_LIMIT;

    private Timer timer;

    // GUI Components
    private JLabel questionLabel = new JLabel();
    private JRadioButton optionA = new JRadioButton();
    private JRadioButton optionB = new JRadioButton();
    private JRadioButton optionC = new JRadioButton();
    private JRadioButton optionD = new JRadioButton();
    private ButtonGroup group = new ButtonGroup();
    private JLabel timerLabel = new JLabel();
    private JButton nextButton = new JButton("Submit / Next");

    public Main() {
        setTitle("Quiz Game with Timer");
        setSize(750, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        buildGUI();
        loadQuestions();
        showQuestion(0);
    }

    private void buildGUI() {
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        optionA.setActionCommand("A");
        optionB.setActionCommand("B");
        optionC.setActionCommand("C");
        optionD.setActionCommand("D");

        group.add(optionA);
        group.add(optionB);
        group.add(optionC);
        group.add(optionD);

        optionsPanel.add(optionA);
        optionsPanel.add(optionB);
        optionsPanel.add(optionC);
        optionsPanel.add(optionD);

        mainPanel.add(optionsPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        timerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        timerLabel.setText("Time: 15s");
        bottomPanel.add(timerLabel, BorderLayout.WEST);

        nextButton.addActionListener(e -> handleSubmit());
        bottomPanel.add(nextButton, BorderLayout.EAST);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);

        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time: " + timeLeft + "s");

            if (timeLeft <= 0) {
                timer.stop();
                JOptionPane.showMessageDialog(this, "Time's up!");
                goToNext();
            }
        });
    }

    private void loadQuestions() {
        try (BufferedReader br = new BufferedReader(new FileReader("questions.txt"))) {
            String line, q = "", a = "", b = "", c = "", d = "", ans = "";

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Question:")) q = line.substring(9).trim();
                else if (line.startsWith("A)")) a = line.substring(2).trim();
                else if (line.startsWith("B)")) b = line.substring(2).trim();
                else if (line.startsWith("C)")) c = line.substring(2).trim();
                else if (line.startsWith("D)")) d = line.substring(2).trim();
                else if (line.startsWith("Answer:")) {
                    ans = line.substring(7).trim();
                    questions.add(new Question(q, a, b, c, d, ans));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading questions.txt");
            System.exit(0);
        }
    }

    private void showQuestion(int index) {
        if (index >= questions.size()) {
            showResults();
            return;
        }

        Question q = questions.get(index);

        questionLabel.setText((index + 1) + ". " + q.question);
        optionA.setText("A) " + q.a);
        optionB.setText("B) " + q.b);
        optionC.setText("C) " + q.c);
        optionD.setText("D) " + q.d);

        group.clearSelection();
        timeLeft = TIME_LIMIT;
        timerLabel.setText("Time: " + TIME_LIMIT + "s");
        timer.start();
    }

    private void handleSubmit() {
        timer.stop();

        String answer = null;
        if (optionA.isSelected()) answer = "A";
        else if (optionB.isSelected()) answer = "B";
        else if (optionC.isSelected()) answer = "C";
        else if (optionD.isSelected()) answer = "D";

        if (answer != null && answer.equalsIgnoreCase(questions.get(currentIndex).answer)) {
            score++;
        }

        goToNext();
    }

    private void goToNext() {
        currentIndex++;
        showQuestion(currentIndex);
    }

    private void showResults() {
        JOptionPane.showMessageDialog(this,
                "Quiz Completed!
Your Score: " + score + "/" + questions.size());

        int ch = JOptionPane.showConfirmDialog(this, "Play Again?");
        if (ch == JOptionPane.YES_OPTION) {
            currentIndex = 0;
            score = 0;
            showQuestion(0);
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}

class Question {
    String question, a, b, c, d, answer;

    public Question(String q, String a, String b, String c, String d, String ans) {
        this.question = q;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.answer = ans;
    }
}