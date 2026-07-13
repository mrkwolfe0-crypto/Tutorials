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
    
    //Create the window
    JFrame frame = new JFrame("Calculator");

    //Constructor
    Calculator() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false); //restricts the resizing of the window, most likely to avoid graphical glitches as the buttons may not scale like in the Python variant when the window is resized.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Ensures that the window closes when the user clicks on the x binded at top of window like in Pygame "for event in pygame.event.get():if event.type == pygame.QUIT:running = False"
        frame.setLayout(new BorderLayout()); //Allows for components to be laid out within the winow north, south, east, and west.
        
    }
}
