import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BasicCalculator extends JFrame implements ActionListener {

    private JTextField num1Field, num2Field, resultField;
    private JButton addButton, subButton, mulButton, divButton, calculateButton, clearButton;
    private String operator = "";

    public BasicCalculator() {
        setTitle("EASY CALCULATOR FOR LOCAL CALCULATION");
        setSize(400, 320);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));
        getContentPane().setBackground(new Color(500, 247, 250)); // Light Cyan Background

        Font font = new Font("Segoe UI", Font.PLAIN, 16);

        JLabel num1Label = new JLabel("Number 1:");
        JLabel num2Label = new JLabel("Number 2:");
        JLabel resultLabel = new JLabel("Result:");

        // Set label color
        num1Label.setForeground(new Color(40, 40, 40));
        num2Label.setForeground(new Color(40, 40, 40));
        resultLabel.setForeground(new Color(40, 40, 40));

        num1Field = new JTextField();
        num2Field = new JTextField();
        resultField = new JTextField();
        resultField.setEditable(false);

        num1Field.setFont(font);
        num2Field.setFont(font);
        resultField.setFont(font);

        addButton = createButton("+");
        subButton = createButton("-");
        mulButton = createButton("*");
        divButton = createButton("/");
        calculateButton = createButton("Calculate");
        clearButton = createButton("Clear");

        add(num1Label); add(num1Field);
        add(num2Label); add(num2Field);
        add(resultLabel); add(resultField);
        add(addButton); add(subButton);
        add(mulButton); add(divButton);
        add(calculateButton); add(clearButton);

        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(new Color(0, 121, 107)); // Teal
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == addButton) operator = "+";
        else if (src == subButton) operator = "-";
        else if (src == mulButton) operator = "*";
        else if (src == divButton) operator = "/";
        else if (src == calculateButton) calculateResult();
        else if (src == clearButton) clearFields();
    }

    private void calculateResult() {
        try {
            double num1 = Double.parseDouble(num1Field.getText());
            double num2 = Double.parseDouble(num2Field.getText());
            double result = 0;

            switch (operator) {
                case "+" -> result = num1 + num2;
                case "-" -> result = num1 - num2;
                case "*" -> result = num1 * num2;
                case "/" -> {
                    if (num2 == 0) {
                        resultField.setText("Cannot divide by 0");
                        return;
                    }
                    result = num1 / num2;
                }
                default -> {
                    resultField.setText("Choose operation");
                    return;
                }
            }

            resultField.setText(String.format("%.2f", result));
        } catch (NumberFormatException ex) {
            resultField.setText("Invalid input");
        }
    }

    private void clearFields() {
        num1Field.setText("");
        num2Field.setText("");
        resultField.setText("");
        operator = "";
    }

    public static void main(String[] args) {
        new BasicCalculator();
    }
}
