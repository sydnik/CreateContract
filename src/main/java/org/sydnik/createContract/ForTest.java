package org.sydnik.createContract;

import org.sydnik.createContract.data.Currency;

import javax.swing.*;
import java.awt.*;

public class ForTest extends JPanel {
    private JList<String> list;
    private String[] imageNames = { "Bird", "Cat", "Dog", "Rabbit", "Pig", "dukeWaveRed",
            "kathyCosmo", "lainesTongue", "left", "middle", "right", "stickerface"};
    public ForTest() {
        setLayout( new BorderLayout(  ) );



        list = new JList<>(imageNames);
        list.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        list.setSelectedIndex( 0 );



        add( new JScrollPane(list) );
    }



    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {



        //Create and set up the window.
        JFrame frame = new JFrame("SplitPaneDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ForTest splitPaneDemo = new ForTest();
        frame.getContentPane().add(splitPaneDemo);



        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }



    public static void main(String[] args) {
        new Currency().ChechKurs();
    }

}
