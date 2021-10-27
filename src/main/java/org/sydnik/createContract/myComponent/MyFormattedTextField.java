package org.sydnik.createContract.myComponent;

import javax.swing.*;
import java.awt.event.KeyListener;

public class MyFormattedTextField extends JFormattedTextField implements EnumValue {
    private ValueTextField valueTextField;

    public MyFormattedTextField(ValueTextField valueTextField,AbstractFormatter formatter,String text,boolean enabled, KeyListener keyListener) {
        this.valueTextField = valueTextField;
        addKeyListener(keyListener);
        setEnabled(enabled);
        setFormatter(formatter);
        setText(text);
    }
    @Override
    public ValueTextField getEnumValue(){
        return valueTextField;
    }
}
