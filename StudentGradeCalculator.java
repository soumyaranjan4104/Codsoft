import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculator {
    private JFrame frame;
    private JTextField[] subjectFields;
    private JLabel totalMarksLabel;
    private JLabel averagePercentageLabel;
    private JLabel gradeLabel;

    public StudentGradeCalculator() {
        frame = new JFrame("Student Grade Calculator");
        frame.setLayout(new GridLayout(0, 2));

        subjectFields = new JTextField[5]; // Assuming a maximum of 5 subjects
        for (int i = 0; i < subjectFields.length; i++) {
            frame.add(new JLabel("Subject " + (i + 1) + " Marks: "));
            subjectFields[i] = new JTextField(5);
            frame.add(subjectFields[i]);
        }

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAndDisplayResults();
            }
        });
        frame.add(calculateButton);

        totalMarksLabel = new JLabel("Total Marks: ");
        averagePercentageLabel = new JLabel("Average Percentage: ");
        gradeLabel = new JLabel("Grade: ");
        frame.add(totalMarksLabel);
        frame.add(averagePercentageLabel);
        frame.add(gradeLabel);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void calculateAndDisplayResults() {
        int totalMarks = 0;
        int numberOfSubjects = 0;

        for (JTextField subjectField : subjectFields) {
            try {
                int marks = Integer.parseInt(subjectField.getText());
                totalMarks += marks;
                numberOfSubjects++;
            } catch (NumberFormatException e) {
                // Handle invalid input here if needed
            }
        }

        if (numberOfSubjects > 0) {
            double averagePercentage = (double) totalMarks / (numberOfSubjects * 100);
            String grade = calculateGrade(averagePercentage);

            totalMarksLabel.setText("Total Marks: " + totalMarks);
            averagePercentageLabel.setText("Average Percentage: " + (averagePercentage * 100) + "%");
            gradeLabel.setText("Grade: " + grade);
        }
    }

    private String calculateGrade(double averagePercentage) {
        // You can implement your grading logic here based on the averagePercentage
        // For example, you can use if-else statements to assign grades.

        // Sample grading logic:
        if (averagePercentage >= 0.9) {
            return "A+";
        } else if (averagePercentage >= 0.8) {
            return "A";
        } else if (averagePercentage >= 0.7) {
            return "B";
        } else if (averagePercentage >= 0.6) {
            return "C";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentGradeCalculator();
            }
        });
    }
}

