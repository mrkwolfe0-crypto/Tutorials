import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Calculator {
    int boardWidth = 360;
    int boardHeight = 540;

    JFrame frame = new JFrame("Calculator");

    // Colors mimicking the iPhone calculator palette
    Color customLightGray = new Color(212, 212, 210);
    Color customDarkGray = new Color(80, 80, 80);
    Color customBlack = new Color(28, 28, 28);
    Color customOrange = new Color(255, 149, 0);

    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

    // UI Buttons Array, 
    String[] buttonValues = {
        "AC", "+/-", "%", "÷",
        "7", "8", "9", "×",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "="
    }; //He left us to do the square root operator.

    String[] rightSymbols = {"÷", "×", "-", "+", "="}; //Operators
    String[] topSymbols = {"AC", "+/-", "%"}; //AC, +/-, %

    // Variables to track calculation state
    String a = "0";
    String operator = null;
    String b = null;

    public Calculator() {
        // Layout and panels configuration
        frame.setVisibility(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null); // Center window
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Display configuration
        displayLabel.setBackground(customBlack);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel); //Put the text label in the panel
        frame.add(displayPanel, BorderLayout.NORTH);//Puts the panel in window

        // Buttons Grid Layout
        buttonsPanel.setLayout(new GridLayout(5, 4));
        buttonsPanel.setBackground(customBlack);
        frame.add(buttonsPanel);

        //For loop.
        for (int i = 0; i < buttonValues.length; i++) {
            JButton button = new JButton();
            String buttonValue = buttonValues[i];

            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setText(buttonValue);
            button.setFocusable(false); //Removes the square around the values in the button.
            button.setBorder(new LineBorder(customBlack)); //Border around the buttons.
            buttonsPanel.add(button);
            

            // Apply specific button styling based on functionality
            if (Arrays.asList(topSymbols).contains(buttonValue)) {
                button.setBackground(customLightGray);
                button.setForeground(customBlack);
            } else if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                button.setBackground(customOrange);
                button.setForeground(Color.white);
            } else {
                button.setBackground(customDarkGray);
                button.setForeground(Color.white);
            }

            // Click handling event listener
            button.addActionListener(new ActionListener() {
                //Listening for the mouse click and e refers to the action.
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource(); //e is the action event, source is where that event comes from.
                    String buttonValue = button.getText(); //Checks the button clicked on.

                    if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                        // Right Side Symbols: Operators & Equal
                        if (buttonValue.equals("=")) {
                            if (a != null) {
                                b = displayLabel.getText();
                                double numA = Double.parseDouble(a);
                                double numB = Double.parseDouble(b);

                                if (operator.equals("+")) {
                                    displayLabel.setText(removeZeroDecimal(numA + numB));
                                } else if (operator.equals("-")) {
                                    displayLabel.setText(removeZeroDecimal(numA - numB));
                                } else if (operator.equals("×")) {
                                    displayLabel.setText(removeZeroDecimal(numA * numB));
                                } else if (operator.equals("÷")) {
                                    displayLabel.setText(removeZeroDecimal(numA / numB));
                                }
                                clearAll();
                            }
                        } else {
                            if (operator == null) {
                                a = displayLabel.getText();
                                displayLabel.setText("0");
                                b = "0";
                            }
                            operator = buttonValue;
                        }
                    } else if (Arrays.asList(topSymbols).contains(buttonValue)) {
                        // Top Symbols: AC, +/-, %
                        if (buttonValue.equals("AC")) {
                            clearAll();
                        } else if (buttonValue.equals("+/-")) {
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay *= -1;
                            displayLabel.setText(removeZeroDecimal(numDisplay));
                        } else if (buttonValue.equals("%")) {
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay /= 100;
                            displayLabel.setText(removeZeroDecimal(numDisplay));
                        }
                    } else {
                        // Digits and Decimal Symbol
                        if (buttonValue.equals(".")) {
                            if (!displayLabel.getText().contains(buttonValue)) {
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        } else if ("0123456789".contains(buttonValue)) {
                            if (displayLabel.getText().equals("0")) {
                                displayLabel.setText(buttonValue);
                            } else {
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        }
                    }
                }
            });
        }

        frame.add(buttonsPanel);
        frame.setVisible(true); // Made visible after adding all components to fix render artifacts
    }

    void clearAll() {
        a = "0";
        operator = null;
        b = null;
        displayLabel.setText("0");
    }

    String removeZeroDecimal(double numDisplay) {
        if (numDisplay % 1 == 0) {
            return Integer.toString((int) numDisplay);
        } else {
            return Double.toString(numDisplay);
        }
    }
}
