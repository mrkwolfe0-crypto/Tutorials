import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Calculator implements ActionListener {
    // Window dimensions
    int boardWidth = 360;
    int boardHeight = 540;

    JFrame frame = new JFrame("Calculator");
    JTextField textField = new JTextField();
    JPanel panel = new JPanel();

    // Calculation variables
    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculator() {
        // Frame Setup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(boardWidth, boardHeight);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Display Field Setup
        textField.setEditable(false);
        textField.setFont(new Font("Arial", Font.BOLD, 24));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setBorder(new LineBorder(Color.GRAY, 2));
        frame.add(textField, BorderLayout.NORTH);

        // Button Panel Setup
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        // Button labels
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.PLAIN, 20));
            btn.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
            btn.addActionListener(this);
            panel.add(btn);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Clear button
        if (command.equals("C")) {
            textField.setText("");
            num1 = num2 = result = 0;
        } 
        // Equals button
        else if (command.equals("=")) {
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/': result = num1 / num2; break;
            }
            textField.setText(String.valueOf(result));
            num1 = result; // Allow continued calculations
        } 
        // Operators
        else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
            num1 = Double.parseDouble(textField.getText());
            operator = command.charAt(0);
            textField.setText("");
        } 
        // Numbers
        else {
            textField.setText(textField.getText() + command);
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
