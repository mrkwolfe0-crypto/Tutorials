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
        "7", "8", "9", "x",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "="
    };

    //Plays out just like the python variant but instead of using [] to define the associated symbols it is assigned a string where from my understanding string in Python is assumed, and the symbols are contained in the {} in Java vs Python with []. Is {} doing for Java what [] does for Python and define lists?
    //From a functional standpoint,both ⁠{}⁠ in this specific Java context and ⁠[]⁠ in Python serve the exact same primary purpose for a programmer: they let you create a collection of items all at once in a single line of code.
    //FIXED: Added "+" to rightSymbols so addition works properly
    String[] rightSymbols = {"/", "x", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};
    

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
    JFrame frame = new JFrame("Calculator");   
    String a = "0";
    String operator = null;
    String b = null;
    


    //Constructor, the layout and panel configurations.
    Calculator() {
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false); //restricts the resizing of the window, most likely to avoid graphical glitches as the buttons may not scale like in the Python variant when the window is resized.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Ensures that the window closes when the user clicks on the x binded at top of window like in Pygame "for event in pygame.event.get():if event.type == pygame.QUIT:running = False"
        frame.setLayout(new BorderLayout()); //Allows for components to be laid out within the winow north, south, east, and west.
        displayLabel.setBackground(customBlack);
        displayLabel.setForeground(Color.WHITE);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));

        //Setting behavior for the right aligned buttons
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);
        
        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel, BorderLayout.NORTH);

        //The grid layout and configuration for the buttons
        buttonsPanel.setLayout(new GridLayout(5,4));
        buttonsPanel.setBackground(customBlack);
        //for loop in Java coming up, remember this and refer back to it for later forloops as a reference.
        for (int i = 0; i < buttonValues.length; i++) {
            JButton button = new JButton();
            String buttonValue = buttonValues[i];

            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setText(buttonValue);
            button.setFocusable(false);
            button.setBorder(new LineBorder(customBlack));

            //This next section applies the specific styling based on functionality.
            //Like Python if/elif/else
            if (Arrays.asList(topSymbols).contains(buttonValue)) {
                button.setBackground(customLightGray);
                button.setForeground(customBlack);
            } else if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                button.setBackground(customOrange);
                button.setForeground(Color.white);
            } else {
                //FIXED: Added missing default dark gray styling for regular number buttons
                button.setBackground(customDarkGray);
                button.setForeground(Color.white);
            } //When I ran it to test the buttons did not populate, I must need to continue to make them appear.
            
            buttonsPanel.add(button);
            //Next section works on the listener for the buttons.
            //Talk about nested loops and arrays wth
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    String buttonValue = button.getText();
                    if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                        //For my sanity this is to determine the right side symbols operators and equal.
                        //DOn't forget the () around buttonValues, operator.equals.
                        //If done right the obj: will appear before the designated button being programmed.
                        
                        //FIXED: Changed buttonValues.equals("=") to buttonValue.equals("=")
                        if (buttonValue.equals("=")){
                            if (a != null && operator != null) {
                                b = displayLabel.getText();
                                double numA = Double.parseDouble(a);
                                double numB = Double.parseDouble(b);
                                if (operator.equals("+")) {
                                    //done right anObject auto fills in.
                                    displayLabel.setText(removeZeroDecimal(numA + numB));
                                } else if (operator.equals("-")) {
                                    displayLabel.setText(removeZeroDecimal(numA - numB));
                                } else if (operator.equals("x")) {
                                    displayLabel.setText(removeZeroDecimal(numA * numB));
                                } else if (operator.equals("/")) {
                                    displayLabel.setText(removeZeroDecimal(numA / numB));
                                }
                                clearAll();
                            } //This who section was to determine the behavior of the operators and remove the floating decimal point assuming the following number is a 0 and not a number greater than 0.
                            //Is this equal to the function I had to make in Python def check_zero_decimal(num): that removed the decimal after the conversion from number to string to number for the +/- function of the calculator.
                        } else {
                            if (operator == null) {
                                a = displayLabel.getText();
                                displayLabel.setText("0");
                                b = "0";
                            }
                            operator = buttonValue;
                        }
                    } else if (Arrays.asList(topSymbols).contains(buttonValue)) {
                        //AC, +/-, %
                        if (buttonValue.equals("AC")) {
                            clearAll();
                        } else if (buttonValue.equals("+/-")) { //FIXED: Typo "+/=" -> "+/-"
                            //FIXED: Properly multiply display number by -1
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay *= -1;
                            displayLabel.setText(removeZeroDecimal(numDisplay));
                        } else if (buttonValue.equals("%")) {
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay /= 100;
                            displayLabel.setText(removeZeroDecimal(numDisplay));
                        }
                    } else {
                        //Coming up next is the settings for the digits and decimal symbol.
                        //When launching the calc at this point I noticed just like the python version, the numbers overflowed the calculation screen area. It is all gray not the Apple calc color scheme yet for some reason.
                        if (buttonValue.equals(".")) {
                            //FIXED: Typo display.getText() -> displayLabel.getText()
                            if (!displayLabel.getText().contains(buttonValue)) {
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        } else if (buttonValue.equals("√")) {
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            displayLabel.setText(removeZeroDecimal(Math.sqrt(numDisplay)));
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
        frame.setVisible(true); //Visible aafer adding all components fixing the rendering artifacts.
    }

    void clearAll() {
        a = "0";
        operator = null;
        b = null;
        displayLabel.setText("0");
    } //Rhyming with the def clear_all(): in the python variant.

    //def check_zero_decimal(num):
    String removeZeroDecimal(double numDisplay) {
        if (numDisplay % 1 == 0) {
            return Integer.toString((int) numDisplay);
        } else {
            return Double.toString(numDisplay);
        }
    }
}