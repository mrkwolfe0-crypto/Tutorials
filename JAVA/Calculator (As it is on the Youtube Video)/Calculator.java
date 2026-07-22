//Source:https://youtu.be/jQo6n-i6wpo?is=lGaybv5gFnrdi2wj
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder; //for modifying the border of the buttons of the calculator

public class Calculator {
     //This is for the window, the creator often names his window board
    int boardWidth = 360;
    int boardHeight = 540;

    //These are the default colors of how a calculator is presented in Mac Operating system.
    Color customLightGray = new Color(212, 212, 210);
    Color customDarkGray = new Color(82,80,80);
    Color customBlack = new Color(28,28,28);
    Color customOrange = new Color(255,149,0);

    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

    //This section creates the layout and buttons akin to how the Pyhton calculator displayed its buttons.
    //Type a square root symbol (√) on a Mac by pressing Option + V. On Windows, hold Alt and press 2, 5, 1 on your numeric keypad, or use the Emoji menu
    String[] buttonValues = {
        "AC", "+/-", "%", "/",
        "7", "8", "9", "*",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "="
    };

    //Plays out just like the python variant but instead of using [] to define the associated symbols it is assigned a string where from my understanding string in Python is assumed, and the symbols are contained in the {} in Java vs Python with []. Is {} doing for Java what [] does for Python and define lists?
    //From a functional standpoint,both ⁠{}⁠ in this specific Java context and ⁠[]⁠ in Python serve the exact same primary purpose for a programmer: they let you create a collection of items all at once in a single line of code.
    //FIXED: Added "+" to rightSymbols so addition works properly
    String[] rightSymbols = {"/", "*", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"}; //AC, +/-, %

    //Creates the window
    //This section are the variables to track and enable the calculation state.
        //Refer to the python code that set the callculation area plus four to the left and right than colored it black and set 0 to be on the right not default center.
        /*frame = tkinter.Frame(window)
        label = tkinter.Label(frame, text = "0", 
                            font = ("Arial", 45), 
                            background = black, 
                            foreground = white,
                            anchor = "e",
                            width = column_count)
        #width = column_count stops the calculator window from expanding as more numbers populate the claculation area that the label enables to be displayed. Since the buttons do not scale the expansion could lead to graphical issues.
        #label.pack() */
    /*#This is to set the values of A, B and the operator to be used in the calculation. The operator is set to None as it will be set when the user clicks on one of the operator buttons. A and B are set to 0 and None respectively as they will be set when the user clicks on a number button.
    #A+B, A-B, A*B, A/B
    A=0
    operator= None
    B=None */   
    String a = "0";
    String operator = null;
    String b = null;
    JFrame frame = new JFrame("Calculator");
    public Calculator() {
        // Layout and panels configuration
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
                                } else if (operator.equals("*")) {
                                    displayLabel.setText(removeZeroDecimal(numA * numB));
                                } else if (operator.equals("/")) {
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
                    } 
                    else if (Arrays.asList(topSymbols).contains(buttonValue)) {
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
                    } 
                    else {// Digits and Decimal Symbol
                        if (buttonValue.equals(".")) {
                            if (!displayLabel.getText().contains(buttonValue)) {
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            } //If the current display label doesn't have a decimal place, one can be added. 
                        else if ("0123456789".contains(buttonValue)) {
                            if (displayLabel.getText().equals("0")) {
                                displayLabel.setText(buttonValue);
                            } //The makes it so pressing a number at default label of "0", you get the number desired no "0" then the number pressed.
                            else {
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        }
                    }
                });
            }
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

        } 

        else {

            return Double.toString(numDisplay);

        }

    }

} // closes class Calculator 
            //Source:https://youtu.be/jQo6n-i6wpo?is=lGaybv5gFnrdi2wj

