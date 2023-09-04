import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGameGUI extends JFrame {
    private int lowerBound = 1;
    private int upperBound = 100;
    private int targetNumber;
    private int attempts = 0;
    private int rounds = 0;
    private int score = 0;

    private JLabel instructionLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel resultLabel;
    private JLabel scoreLabel;
    private JButton newGameButton;

    public NumberGameGUI() {
        setTitle("Number Guessing Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        instructionLabel = new JLabel("Guess the number between " + lowerBound + " and " + upperBound + ":");
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        resultLabel = new JLabel("");
        scoreLabel = new JLabel("Score: " + score);
        newGameButton = new JButton("New Game");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(instructionLabel);
        panel.add(guessField);
        panel.add(guessButton);
        panel.add(resultLabel);
        panel.add(scoreLabel);
        panel.add(newGameButton);

        add(panel);

        generateNewNumber();
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });
    }

    private void generateNewNumber() {
        Random rand = new Random();
        targetNumber = rand.nextInt(upperBound - lowerBound + 1) + lowerBound;
        attempts = 0;
        instructionLabel.setText("Guess the number between " + lowerBound + " and " + upperBound + ":");
        guessField.setText("");
        resultLabel.setText("");
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            attempts++;

            if (guess < targetNumber) {
                resultLabel.setText("Too low! Try again.");
            } else if (guess > targetNumber) {
                resultLabel.setText("Too high! Try again.");
            } else {
                resultLabel.setText("Congratulations! You guessed the number in " + attempts + " attempts.");
                score++;
                scoreLabel.setText("Score: " + score);
                guessButton.setEnabled(false);
            }

            guessField.setText("");
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter a valid number.");
        }
    }

    private void startNewGame() {
        generateNewNumber();
        guessButton.setEnabled(true);
        rounds++;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGameGUI().setVisible(true);
            }
        });
    }
}
