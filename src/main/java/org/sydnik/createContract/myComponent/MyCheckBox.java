package org.sydnik.createContract.myComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MyCheckBox extends JCheckBox {
    private Component linkToComponent;

    public MyCheckBox(Component linkToComponent, ActionListener actionListener) {
        this.linkToComponent = linkToComponent;
        addActionListener(actionListener);
    }

    public void setSelectedLinkToComponent() {
        linkToComponent.setEnabled(this.isSelected());
    }
}
