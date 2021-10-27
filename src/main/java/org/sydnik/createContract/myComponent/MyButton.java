package org.sydnik.createContract.myComponent;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MyButton extends JButton {
    private ValueButton valueButton;
    public MyButton(ValueButton valueButton, ActionListener actionListener) {
        this.valueButton = valueButton;
        setText(valueButton.getName());
        addActionListener(actionListener);
    }
    public ValueButton getEnumValue(){
        return valueButton;
    }
}
