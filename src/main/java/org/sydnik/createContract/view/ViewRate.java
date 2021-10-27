package org.sydnik.createContract.view;

import org.sydnik.createContract.MyController;
import org.sydnik.createContract.exception.DontHaveData;
import org.sydnik.createContract.myComponent.MyButton;
import org.sydnik.createContract.myComponent.MyTextField;
import org.sydnik.createContract.myComponent.ValueButton;
import org.sydnik.createContract.myComponent.ValueTextField;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ViewRate implements Display {
    private JPanel staticJPanel;
    private MyController controller;
    private JTextField valueCurrency;

    public ViewRate(JPanel staticJPanel, MyController controller) {
        this.staticJPanel = staticJPanel;
        this.controller = controller;
    }

    @Override
    public void display() {
        staticJPanel.removeAll();
        staticJPanel.setLayout(new GridLayout(20,1));
        staticJPanel.add(new JLabel("Не могу узнать курс евро, придется вводить в ручную:("));
        valueCurrency = new MyTextField(ValueTextField.VALUE_RATE,"",true,controller);
        staticJPanel.add(valueCurrency);
        staticJPanel.add(new MyButton(ValueButton.SAVE_CURRENCY,controller));

        staticJPanel.revalidate();
        staticJPanel.repaint();
    }

    @Override
    public HashMap<String, String> getData() throws DontHaveData {
        HashMap<String,String> map = new HashMap<>();
        map.put("Result",valueCurrency.getText());
        return map;
    }
}
