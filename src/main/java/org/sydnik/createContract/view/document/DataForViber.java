package org.sydnik.createContract.view.document;

import org.sydnik.createContract.MyController;
import org.sydnik.createContract.myComponent.MyButton;
import org.sydnik.createContract.myComponent.ValueButton;

import javax.swing.*;
import java.awt.*;

public interface DataForViber {

    default void displayDataForViber(JPanel staticJPanel, MyController controller, JButton[] buttons){
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(staticJPanel);
        JDialog dialog = new JDialog(frame, "Данные для вайбер", true);
        dialog.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(null);

        JPanel jPanel = new JPanel();
        dialog.add(jPanel);
        jPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 48;
        buttons[0] = new MyButton(ValueButton.SELECT_PAYMENT_CASH,controller);
        jPanel.add(buttons[0],gridBagConstraints);
        gridBagConstraints.gridx = 1;
        buttons[1] = new MyButton(ValueButton.SELECT_PAYMENT_CARD,controller);
        jPanel.add(buttons[1],gridBagConstraints);
        gridBagConstraints.gridx = 2;
        buttons[2] = new MyButton(ValueButton.SELECT_PAYMENT_NON_CASH,controller);
        jPanel.add(buttons[2],gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 155;
        buttons[3] = new MyButton(ValueButton.COPY_PREPAYMENT,controller);
        buttons[3].setEnabled(false);
        jPanel.add(buttons[3],gridBagConstraints);

        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        buttons[4] = new MyButton(ValueButton.COPY_PAY_TO_50_PERCENT,controller);
        buttons[4].setEnabled(false);
        jPanel.add(buttons[4],gridBagConstraints);

        gridBagConstraints.gridy = 3;
        buttons[5] = new MyButton(ValueButton.COPY_PAY_TO_100_PERCENT,controller);
        buttons[5].setEnabled(false);
        jPanel.add(buttons[5],gridBagConstraints);

        dialog.setVisible(true);
    }
    default void setSelectMethodPayment(MyButton[] buttons,String nameButton){
        for (int i = 0; i < 3; i++) {
            if(buttons[i].getText().equals(nameButton)){
                buttons[i].setEnabled(false);
            }
            else {
                buttons[i].setEnabled(true);
            }
        }
        for (int i = 3; i < 6 ; i++) {
            buttons[i].setEnabled(true);
        }

    }
    void displayDataForViber();
    void setSelectMethodPayment(String nameButton);
    String getDataFirstPayment();
    String getDataSecondPayment();
    String getDataThirdPayment();
}
