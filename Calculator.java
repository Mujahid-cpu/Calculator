import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculator implements ActionListener {
    private JTextField textField;       //Created instance variables that can be accessed anywhere, and did not declare it in the constructor so it can be used anywhere
    private JButton[] numButtons;
    private JButton[] funcButtons;
    private JButton decB, addB, subB, mulB, divB, equalB, delB, clrB, darkMode;
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;
    private boolean darkModeEnabled = false ;
    private JFrame frame;
    private JPanel panel;
    


    public Calculator(){        //Constructor
    frame = new JFrame("Calculator");
    textField = new JTextField();
    textField.setBounds(50,25,300,50); 
    textField.setEditable(false);
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(420,550);
    frame.setLayout(null);
    frame.add(textField);
    
    numButtons = new JButton[10];   
    funcButtons = new JButton[8];
    
    addB= new JButton("+");
    subB= new JButton("-");
    mulB= new JButton("x");
    divB= new JButton("/");
    decB= new JButton(".");
    equalB= new JButton("=");
    delB= new JButton("DELETE");
    clrB= new JButton("CLEAR");
    darkMode = new JButton("MODE");

    funcButtons[0] = addB;
    funcButtons[1] = subB;
    funcButtons[2] = mulB;
    funcButtons[3] = divB;
    funcButtons[4] = decB;
    funcButtons[5] = equalB;
    funcButtons[6] = delB;
    funcButtons[7] = clrB;

    darkMode.addActionListener(this);

    for(JButton button : funcButtons){      //Sets the fonts for the buttons
       button.addActionListener(this);
       button.setFont(new Font("Courier New", Font.BOLD, 30));
       button.setFocusable(false);
    }

    for(int i=0;i<10;i++){                 //Sets the fonts for the buttons
        numButtons[i] = new JButton(String.valueOf(i));
        numButtons[i].setFont(new Font("Courier New", Font.BOLD, 30));
        numButtons[i].addActionListener(this);
        numButtons[i].setFocusable(false);
    }
    delB.setBounds(50,430,100,50);
    delB.setFont(new Font("Courier New", Font.BOLD, 18));
    clrB.setBounds(248,430,100,50);
    clrB.setFont(new Font("Courier New", Font.BOLD, 18));
    darkMode.setBounds(150,430,100,50); 
    darkMode.setFont(new Font("Courier New", Font.BOLD, 18));
    frame.add(delB);
    frame.add(clrB);
    frame.add(darkMode);

    panel = new JPanel();
    panel.setBounds(50,100,300,300);
    panel.setLayout(new GridLayout(4,4,10,10));
    panel.add(numButtons[1]);   
    panel.add(numButtons[2]);
    panel.add(numButtons[3]);
    panel.add(addB);
    panel.add(numButtons[4]);
    panel.add(numButtons[5]);
    panel.add(numButtons[6]);
    panel.add(subB);
    panel.add(numButtons[7]);
    panel.add(numButtons[8]);
    panel.add(numButtons[9]);
    panel.add(mulB);
    panel.add(decB);
    panel.add(numButtons[0]);
    panel.add(equalB);
    panel.add(divB);

    frame.add(panel);
    frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i= 0; i<10; i++){
            if(e.getSource() == numButtons[i]){
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if(e.getSource() == decB ){
            textField.setText(textField.getText().concat("."));
        } 
        if(e.getSource() == addB){
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }
        if(e.getSource() == subB){
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }
        if(e.getSource() == mulB){
            num1 = Double.parseDouble(textField.getText());
            operator = 'x';
            textField.setText("");
        }
        if(e.getSource() == divB){
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }
        if(e.getSource()==equalB){
            num2=Double.parseDouble(textField.getText());
            switch(operator){
                case'+':
                    result=num1+num2;
                    break;
                case'-':
                    result=num1-num2;
                    break;
                case'x':
                    result=num1*num2;
                    break;
                case'/':
                    if (num2 == 0) { // Division by zero check
                        textField.setText("Error");
                        return;
                    } else {
                        result = num1 / num2;
                    }
                    break;
            }

            textField.setText(String.valueOf(result));
            num1=result;
        
        }
        if(e.getSource() == clrB){
            textField.setText("");
        }
        if(e.getSource() == delB) {
            String currentText = textField.getText();
            if (currentText.length() > 0) {
                textField.setText(currentText.substring(0, currentText.length() - 1));
            }
        }
        if(e.getSource() == darkMode){
            toggleDarkMode();
        }
    }
    private void toggleDarkMode() {
        if (!darkModeEnabled) { // Switch to Dark Mode
            frame.getContentPane().setBackground(Color.BLACK);
            textField.setBackground(Color.DARK_GRAY);
            textField.setForeground(Color.LIGHT_GRAY);
            panel.setBackground(Color.DARK_GRAY);

            for (JButton numButtons_colour : numButtons) {
                numButtons_colour.setBackground(Color.BLACK);
                numButtons_colour.setForeground(Color.LIGHT_GRAY);
            }

            for (JButton funcButtons_colour : funcButtons) { // Fixed loop variable
                funcButtons_colour.setBackground(Color.BLACK);
                funcButtons_colour.setForeground(Color.LIGHT_GRAY);
            }

            darkMode.setText("MODE");
            darkMode.setBackground(Color.BLACK);
            darkMode.setForeground(Color.LIGHT_GRAY);
        } else { // Switch back to Light Mode
            frame.getContentPane().setBackground(null); // Resets to default
            textField.setBackground(null);
            textField.setForeground(Color.BLACK);
            panel.setBackground(null);

            for (JButton numButtons_colour : numButtons) {
                numButtons_colour.setBackground(null);
                numButtons_colour.setForeground(Color.BLACK);
            }

            for (JButton funcButtons_colour : funcButtons) {
                funcButtons_colour.setBackground(null);
                funcButtons_colour.setForeground(Color.BLACK);
            }

            darkMode.setText("MODE");
            darkMode.setBackground(null);
            darkMode.setForeground(null);
        }

        darkModeEnabled = !darkModeEnabled; // Toggle state
    }
    
    
    public static void main(String[] args) {
        new Calculator();
    }
}
