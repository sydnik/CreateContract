package org.sydnik.createContract.view;

import org.sydnik.createContract.MyController;

import javax.swing.*;
import java.awt.*;

public interface DataForViber {
    String[] PAYMENT_METHOD_VALUE_BUTTON = {"Наличные", "Карта", "Безнал"};
    String PAYMENT_METHOD_NAME_BUTTON = "PushMethodPayment";
    String BUTTON_NAME_FIRST_PAYMENT = "dataForViberFirstPayment";
    String BUTTON_NAME_SECOND_PAYMENT = "dataForViberSecondPayment";
    String BUTTON_NAME_THIRD_PAYMENT = "dataForViberThirdPayment";
    String BUTTON_VALUE_FIRST_PAYMENT = "Скопировать \"Предоплата\"";
    String BUTTON_VALUE_SECOND_PAYMENT = "Скопировать \"Оплата до 50%\"";
    String BUTTON_VALUE_THIRD_PAYMENT = "Скопировать \"Оплата до 100%\"";

    default void displayDataForViber(JPanel staticJPanel, MyController controller, JButton[] jButtons){
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
        jButtons[0] = new JButton(PAYMENT_METHOD_VALUE_BUTTON[0]);
        jButtons[0].setName(PAYMENT_METHOD_NAME_BUTTON);
        jButtons[0].addActionListener(controller);
        jPanel.add(jButtons[0],gridBagConstraints);
        gridBagConstraints.gridx = 1;
        jButtons[1] = new JButton(PAYMENT_METHOD_VALUE_BUTTON[1]);
        jButtons[1].setName(PAYMENT_METHOD_NAME_BUTTON);
        jButtons[1].addActionListener(controller);
        jPanel.add(jButtons[1],gridBagConstraints);
        gridBagConstraints.gridx = 2;
        jButtons[2] = new JButton(PAYMENT_METHOD_VALUE_BUTTON[2]);
        jButtons[2].setName(PAYMENT_METHOD_NAME_BUTTON);
        jButtons[2].addActionListener(controller);
        jPanel.add(jButtons[2],gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 155;
        jButtons[3] = new JButton(BUTTON_VALUE_FIRST_PAYMENT);
        jButtons[3].setName(BUTTON_NAME_FIRST_PAYMENT);
        jButtons[3].addActionListener(controller);
        jButtons[3].setEnabled(false);
        jPanel.add(jButtons[3],gridBagConstraints);

        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        jButtons[4] = new JButton(BUTTON_VALUE_SECOND_PAYMENT);
        jButtons[4].setName(BUTTON_NAME_SECOND_PAYMENT);
        jButtons[4].addActionListener(controller);
        jButtons[4].setEnabled(false);
        jPanel.add(jButtons[4],gridBagConstraints);

        gridBagConstraints.gridy = 3;
        jButtons[5] = new JButton(BUTTON_VALUE_THIRD_PAYMENT);
        jButtons[5].setName(BUTTON_NAME_THIRD_PAYMENT);
        jButtons[5].addActionListener(controller);
        jButtons[5].setEnabled(false);
        jPanel.add(jButtons[5],gridBagConstraints);

        dialog.setVisible(true);
    }
    default void setSelectMethodPayment(JButton[] jButtons,String nameButton){
        for (int i = 0; i < 3; i++) {
            if(jButtons[i].getText().equals(nameButton)){
                jButtons[i].setEnabled(false);
            }
            else {
                jButtons[i].setEnabled(true);
            }
        }
        for (int i = 3; i < 6 ; i++) {
            jButtons[i].setEnabled(true);
        }

    }
    void displayDataForViber();
    void setSelectMethodPayment(String nameButton);
    String getDataFirstPayment();
    String getDataSecondPayment();
    String getDataThirdPayment();
}
