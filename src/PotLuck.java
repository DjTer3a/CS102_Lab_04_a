/**
 * Lab04a Instructions found on moodle
 * 
 * Style guidlines URL:-
 * http://www.cs.bilkent.edu.tr/~adayanik/cs101/practicalwork/styleguidelines.htm
 * 
 * 
 * @author Mostafa Higazy
 * @version 09/07/2021
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PotLuck extends JFrame implements ActionListener {

    
    private final int WIDTH = 800;
    private final int HEIGHT = 800;
    private final String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12","13", "14","15", "16"};
    

    
    //Variables
    private GridLayout numPad;
    private Bomb bombButton_1;
    private JLabel Label;
    private boolean endGame = false;
    private int win ;
    private int bomb_1 ;
    private int bomb_2 ;
    private Bomb bombbutton_2;
    private Prize Star;
    private JPanel panel;
    private int counter ;
    private JButton[] buttons = new JButton[numbers.length];


    /**
     * Constructor for PotLuck Class
     * 
     */
    public PotLuck() {


        //While loop used to assign random values to the special buttons
        while ( win == bomb_1  || win == bomb_2 || bomb_1 == bomb_2){
        win = (int) (Math.random() * 16);
        bomb_1 = (int) (Math.random() * 16);
        bomb_2 = (int) (Math.random() * 16);
        }
        counter = 0;


        //frame settings 
        setSize(WIDTH, HEIGHT);
        setTitle("Welcome to PotLuck/Are ya winning son");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Intializing JPanel
        panel = new JPanel();
        numPad = new GridLayout(5, 5);
        numPad.setHgap(15); 
        numPad.setVgap(15);
        panel.setLayout(numPad);


        //for loop to create the buttons
        for (int x = 0; x < buttons.length; x++) {


            //if button is a bomb
            if (x == bomb_1 ) {
                bombButton_1 = new Bomb(numbers[x]);
                bombButton_1.setBackground(Color.BLUE);
                bombButton_1.addActionListener(this);
                panel.add(bombButton_1);
            }


            //if button is a bomb
            else if (x == bomb_2) {
                bombbutton_2 = new Bomb(numbers[x]);
                bombbutton_2.setBackground(Color.BLUE);
                bombbutton_2.addActionListener(this);
                panel.add(bombbutton_2);
            }


            //if button is a star
            else if (x == win ) {
                Star = new Prize(numbers[x]);
                Star.setBackground(Color.BLUE);
                Star.addActionListener(this);
                panel.add(Star);
            }


            //if button is a number
            else{
                buttons[x] = new JButton(numbers[x]);
                buttons[x].setBackground(Color.BLUE);
                buttons[x].addActionListener(this);
                panel.add(buttons[x]);
            }

        }


        //setting up the frame
        add(panel, BorderLayout.CENTER);
        add(Label = new JLabel("You have clicked the buttons " + counter +  " times, Please stop spamming."), BorderLayout.NORTH);

        Icon imgIcon = new ImageIcon(this.getClass().getResource("tenor.gif"));
        JLabel label2 = new JLabel(imgIcon);
        label2.setBounds(420, 420, 420, 69);
        getContentPane().add(label2,BorderLayout.SOUTH);


    }

    
    /** 
     * ActionPerformed when a ActionListener is activated(button pressed)
     * @return void
     */
    @Override
    public void actionPerformed(ActionEvent e) {


        //If button is a Bomb
        if (e.getSource() == bombButton_1 ) {
            endGame = true;
            repaint();
            Label.setText("HaHa noob you only lasted " + (counter + 1) + " rounds xD");
            ( (JButton) e.getSource()).setFont(new Font(getFont().getFontName(), Font.BOLD , 40));
            ((JButton) panel.getComponent(win)).setText("🌟") ;
            ((JButton) e.getSource()).setText("💣") ;
            ( (JButton) e.getSource()).setForeground(Color.RED);
            ((JButton) panel.getComponent(bomb_2)).setText("💣") ;


            


            for(int x = 0; x < 16 ; x++){
                if(panel.getComponent(x) != bombButton_1 && panel.getComponent(x) != bombbutton_2 && panel.getComponent(x) != Star ){
                  ((JButton) panel.getComponent(x)).setEnabled(false);
                  ((JButton) panel.getComponent(win)).setEnabled(false);
                  ((JButton) panel.getComponent(bomb_2)).setEnabled(false);  
                }}
        }


        //If button is a Bomb
        else if (e.getSource() == bombbutton_2){
            endGame = true;
            repaint();
            Label.setText("HaHa noob you only lasted " + (counter + 1) + " rounds xD");
            ( (JButton) e.getSource()).setFont(new Font(getFont().getFontName(), Font.BOLD , 40));
            ((JButton) panel.getComponent(win)).setText("🌟") ;
            ((JButton) panel.getComponent(bomb_1)).setText("💣") ;
            ((JButton) e.getSource()).setText("💣") ;
            ( (JButton) e.getSource()).setForeground(Color.RED);
            
            for(int x = 0; x < 16 ; x++){
                if(panel.getComponent(x) != bombButton_1 && panel.getComponent(x) != bombbutton_2 && panel.getComponent(x) != Star ){
                  ((JButton) panel.getComponent(x)).setEnabled(false);
                  ((JButton) panel.getComponent(win)).setEnabled(false);
                  ((JButton) panel.getComponent(bomb_1)).setEnabled(false); 
                }}
        }


        //If button is a Star
        else if (e.getSource() == Star ) {
            endGame = true;
            ( (JButton) e.getSource()).setFont(new Font(getFont().getFontName(), Font.BOLD , 40));
            ( (JButton) e.getSource()).setText("🌟") ;
            ((JButton) panel.getComponent(bomb_1)).setText("💣") ;
            ((JButton) panel.getComponent(bomb_2)).setText("💣") ;
            ( (JButton) e.getSource()).setForeground(Color.YELLOW);
            for(int x = 0; x < 16 ; x++){
                if(panel.getComponent(x) != Star){
                  ((JButton) panel.getComponent(x)).setEnabled(false);   
                }}
            
            Label.setText("You won the battle in " + (counter + 1) +  " attempts, but did you win the war?");

        }


        //If button is a Number
        else {
        counter++;
        ( (JButton) e.getSource()).setEnabled(false); 
        Label.setText("You have clicked the buttons " + counter +  " times, Please stop spamming.");
        }
    }

    
    
}
