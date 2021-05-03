package org.sydnik.createContract;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class MyController implements ActionListener {
    private Model model;
    private MyView view;


    private ArrayList<TextField> listText = new ArrayList<>();
    private ArrayList<JTextPane> listJTextPane = new ArrayList<>();



    public MyController(Model model) {
        this.model = model;
        view = new MyView(this);
        view.setLayout(new GridLayout(20,1));
        view.startPage(model.getSalesManager());
    }

    public MyView getView() {
        return view;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (((Component)e.getSource()).getName()){
            case "sittingsManager":{
                view.settingsManager(model.getSalesManager());
                break;
            }
            case "saveSittingsManager":{
                model.setSalesManager(view.getComponents());
                view.writeMessage("Настройки сохранены");
                break;
            }
            case "mainPage" :{
                view.startPage(model.getSalesManager());
                break;
            }
            case "createNewClient": {
                view.createNewClient();
                break;
            }
            case "saveNewDataClient" :{
                model.createNewClient(view.getComponents());
                view.writeMessage("Клиент сохранен");
                break;
            }

        }
    }
}
