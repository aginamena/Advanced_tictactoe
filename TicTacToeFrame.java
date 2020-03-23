import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * A class modelling a tic-tac-toe (noughts and crosses, Xs and Os) game in a very
 * simple GUI window.
 * 
 * @author Lynn Marshall
 * @version November 8, 2012
 * 
 * @author Agina Oghenemena Benaiah
 * @version March 23, 2019
 */

public class TicTacToeFrame extends TicTacToe implements ActionListener
{ 
    private JTextArea status; // text area to print game status

    private JFrame frame; // our frame

    private JButton button1; //our list of buttons
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;

    private JLabel label; //update gui
    private JPanel panel; // container for the update gui text label

    private JMenuBar menubar; //menubar and menuitems
    private JMenu menu;
    private JMenuItem New;
    private JMenuItem Quit;

    int count1,count2,count3; //update gui

    private JPanel panel3; //container for the update gui text label
    private JLabel lab1; //update gui text label
    private JLabel lab2;
    private JLabel lab3;

    private JButton reset; // reset the count

    private JButton yes;

    private ArrayList<JButton> rnd;
    private boolean test;
    /** 
     * Constructs a new Tic-Tac-Toe board and sets up the basic
     * JFrame containing a JTextArea in a JScrollPane GUI.
     */
    public TicTacToeFrame()
    { 
        // add the necessary code here
        super();
        rnd = new ArrayList<JButton>();
        count1 = count2 =count3=0;

        //new panel to hold the statistics
        panel3 = new JPanel();

        frame = new JFrame("TICTACTOE");
        menubar= new JMenuBar();
        menu = new JMenu("Game");
        New = new JMenuItem("New");
        Quit = new JMenuItem("Quit");

        //add xwon, ywon and tie into to a panel and add the panel into
        //the contentPane
        lab1 = new JLabel("X won: " + count1);
        lab2 = new JLabel("O won: " + count2);
        lab3 = new JLabel("Tie: " + count3);
        panel3.add(lab1);
        panel3.add(lab2);
        panel3.add(lab3);

        //add the items into the menu
        menu.add(New);
        menu.add(Quit);

        //add the menu into the menubar
        menubar.add(menu);

        frame.setJMenuBar(menubar); //added the menubar into the frame

        label = new JLabel();
        panel = new JPanel();
        panel.setLayout(new GridLayout(3,3));

        createButtons(); //create the buttons

        registerButton(); //register the buttons

        //adding the buttons in the content pane
        Container contentPane = frame.getContentPane();

        panel.add(button1);
        rnd.add(button1);
        panel.add(button2);
        rnd.add(button2);
        panel.add(button3);
        rnd.add(button3);
        panel.add(button4);
        rnd.add(button4);
        panel.add(button5);
        rnd.add(button5);
        panel.add(button6);
        rnd.add(button6);
        panel.add(button7);
        rnd.add(button7);
        panel.add(button8);
        rnd.add(button8);
        panel.add(button9);
        rnd.add(button9);

        //adding the panel into the content pane
        contentPane.add(panel,BorderLayout.CENTER);

        //adding the panel3(statistics) into the content pane
        panel3.add(reset);
        contentPane.add(panel3,BorderLayout.NORTH);

        //adding the label to the end
        label = new JLabel("Game in progress and "+getPlayer()+ "'s Turn");
        contentPane.add(label,BorderLayout.SOUTH);

        // this allows us to use shortcuts (e.g. Ctrl-R and Ctrl-Q)
        final int SHORTCUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(); // to save typing
        Quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
        New.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, SHORTCUT_MASK));

        //registering listeners
        Quit.addActionListener(this);
        New.addActionListener(this);

        //add the yes and no buttons into the contentpane
        JLabel lb= new JLabel("Play with computer?");
        JPanel p = new JPanel();
        p.add(lb); //added the text into the jpanel
        JPanel panel4 = new JPanel(); //default to flow layout
        panel4.add(yes);
        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2,BoxLayout.Y_AXIS));
        p2.add(p); //add the text into the panel
        p2.add(panel4);
        //add everthing to the east of the contentpane
        contentPane.add(p2, BorderLayout.EAST);

        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(400,300));
        frame.pack();
    }

    /**
     * Prints the board to the GUI using toString().
     */
    public void print() 
    {  
        // add code here
        status.append(super.toString());
    }

    /**
     * create the buttons for the Gui
     */
    private void createButtons(){
        button1 = new JButton("");
        button2 = new JButton("");
        button3 = new JButton("");
        button4 = new JButton("");
        button5 = new JButton("");
        button6 = new JButton("");
        button7 = new JButton("");
        button8 = new JButton("");
        button9 = new JButton("");
        yes = new JButton("Yes");
        reset = new JButton("Reset scores");
        label = new JLabel(getPlayer()+"'s Turn in prograss");

    }

    /**
     * Register the listeners 
     */
    private void registerButton(){
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);
        button9.addActionListener(this);
        reset.addActionListener(this);
        yes.addActionListener(this);
    }

    /**
     * The action we want to perform when a button or a menuitem is clicked
     * 
     * @param messagePassed stores the event
     */
    public void actionPerformed(ActionEvent messagePassed){
        Object ob = messagePassed.getSource();

        //when the reset only the scores should change and nothing else
        if(ob instanceof JButton && (JButton)ob == reset  ){
            //set all scores back to zero
            count1 = count2 = count3 = 0;
            lab1.setText("X won "+ count1);
            lab2.setText("O won "+count2);
            lab3.setText("Tie "+count3);
            return;
        }

        if(ob instanceof JButton && (JButton)ob == yes){
            //we've to crear the board
            clearScreen();
            enableKeys();

            yes.setEnabled(false);
            test = true;
            play();
            see(); //see if there's a winner or not

        }

        else if(ob instanceof JButton){
            test = false;
            cTest((JButton)ob);
            see(); //see if there's a winner or not
        }
        
        else{
            //it's a jmenuitem
            String b = "";
            JMenuItem item = (JMenuItem)ob;
            if(item == New){

                label.setText("Game in progress and X's Turn");
                //we've to crear the board
                clearScreen();
                enableKeys();
                yes.setEnabled(true);
            }else{
                //it's obviously the quit menuitem at this point
                System.exit(0);
            }

        }
    }

    /**
     * update the view of the Gui
     */
    private void updateGui(){
        String b = "";
        if(!check() && (getWinner().equals("X")|| getWinner().equals("O"))){  
            label.setText("Game over. "+getWinner()+ " wins"); 
        }else if(checkTie()){
            label.setText("Game over. It's a tie");
        }
        else{
            if(getPlayer().equals("X")){
                b = "O";
            }else{
                b = "X";
            }
            label.setText("Game in progress and "+b+ "'s Turn");
        }

    }

    /**
     * Restarting a new game
     */
    private void enableKeys(){
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        button5.setEnabled(true);
        button6.setEnabled(true);
        button7.setEnabled(true);
        button8.setEnabled(true);
        button9.setEnabled(true);
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");
    }

    /**
     * check to see if there's a winner and update the gui
     * 
     * @param c this is the test string to see the input value
     */
    private void checkTest(String c){
        if(c.equals("X")){
            count1++;

            lab1.setText("X won: "+count1);
        } else  if(c.equals("O")){
            //it's o
            count2++;
            lab2.setText("O won: "+count2);
        }
        else if(c.equals("T") ){
            count3++;
            lab3.setText("Tie: "+count3);
        }
    }

    /**
     * The play and the computer can play a game
     */
    public void play(){
        Random r = new Random();
        int x ;
        x = r.nextInt(9);
        if(!checkTie()){
            while(!rnd.get(x).isEnabled() && !checkTie() && check()){
                x = r.nextInt(9);
            }
            cTest(rnd.get(x));
        }

    }
    /**
     * cTest performs the operation on a specific button
     * 
     * @param b stores the button we want to operate on
     */
    public void cTest(JButton b){

        //when ever a button is clicked we've to update the change
        updateGui();

        String newString = "";

        if(b == button1){
            newString = getPlayer();
            playGame2(0,0);

            button1.setText(newString);
            button1.setEnabled(false);

        }
        else if(b == button2){
            newString = getPlayer();
            playGame2(0,1);

            button2.setText(newString);
            button2.setEnabled(false);

        }
        else if(b == button3){
            newString = getPlayer();
            playGame2(0,2);

            button3.setText(newString);
            button3.setEnabled(false);

        }
        else if(b == button4){
            newString = getPlayer();
            playGame2(1,0);

            button4.setText(newString);
            button4.setEnabled(false);  

        }
        else if(b == button5){
            newString = getPlayer();
            playGame2(1,1);

            button5.setText(newString);
            button5.setEnabled(false); 

        }
        else if(b == button6){
            newString = getPlayer();
            playGame2(1,2);

            button6.setText(newString);
            button6.setEnabled(false);

        }
        else if(b == button7){
            newString = getPlayer();
            playGame2(2,0);

            button7.setText(newString);
            button7.setEnabled(false);

        }
        else if(b == button8){
            newString = getPlayer();
            playGame2(2,1);

            button8.setText(newString);
            button8.setEnabled(false);

        }
        else if(b == button9){
            newString = getPlayer();
            playGame2(2,2);

            button9.setText(newString);
            button9.setEnabled(false);

        }

        //when ever a button is clicked we've to update the change
        //update1();

        if(!check()){ //if there's a winner
            //if there's a winner, call update1
            updateGui();
            button1.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
            button5.setEnabled(false);
            button6.setEnabled(false);
            button7.setEnabled(false);
            button8.setEnabled(false);
            button9.setEnabled(false);

            checkTest(whoWon()); 
            return;
        }

        //we've to check if there's no winner(tie)
        if(checkTie()){ //if there's a tie then we want to update it
            updateGui();
            return;
        }

        //if the butten is disabled, we want to play randomly
        if(!yes.isEnabled() && getPlayer().equals("X") && !test && !checkTie()){
            play();
            return;
        }

    }

    /**
     * check if the game has ended
     */
    public void see(){
        //if after game has ended set the yes back to true
        //there's a winner at this stage
        if(!check()){
            yes.setEnabled(true);
        }
    }
}