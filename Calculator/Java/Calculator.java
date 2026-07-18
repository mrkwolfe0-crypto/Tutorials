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
    String[] rightSymbols = {"/", "x", "-", "="};
    String[] topSymbols = {"AC", "+/-", "%"};
    

    //Creates the window
    JFrame frame = new JFrame("Calculator");

    //This section are the variables to track and enable the calculation state.
    //Refer to the python code that set the callculation area plus four to the left and right than colored it black and set 0 to be on the right not default center.
    String a = "0";
    String operator = null;
    String b = null;
    /*#This is to set the values of A, B and the operator to be used in the calculation. The operator is set to None as it will be set when the user clicks on one of the operator buttons. A and B are set to 0 and None respectively as they will be set when the user clicks on a number button.
    #A+B, A-B, A*B, A/B
    A=0
    operator= None
    B=None */


    //Constructor, the layout and panel configurations.
    Calculator() {
        frame.setVisible(true);
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
            

        }
    }
}
