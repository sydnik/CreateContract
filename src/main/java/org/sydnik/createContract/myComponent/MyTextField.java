package org.sydnik.createContract.myComponent;

import javax.swing.*;
import java.awt.event.KeyListener;

public class MyTextField extends JTextField implements EnumValue {
    private ValueTextField valueTextField;

    public MyTextField(ValueTextField valueTextField,String text,boolean enabled, KeyListener keyListener) {
        this.valueTextField = valueTextField;
        setText(text);
        addKeyListener(keyListener);
        setEnabled(enabled);
    }
    @Override
    public ValueTextField getEnumValue(){
        return valueTextField;
    }
}
