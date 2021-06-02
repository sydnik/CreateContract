package org.sydnik.createContract;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class MyController implements ActionListener, ListSelectionListener {
    private Model model;
    private MyView view;


    private ArrayList<TextField> listText = new ArrayList<>();
    private ArrayList<JTextPane> listJTextPane = new ArrayList<>();



    public MyController(Model model,MyView view) {
        this.model = model;
        this.view =view;
        view.setController(this);
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
                model.setSalesManager(view.getComponentsStaticPanel());
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
                model.createNewClient(view.getComponentsStaticPanel());
                view.writeMessage("Клиент сохранен");
                break;
            }
            case "selectClient" : {
                view.listClientsAndSelect(model.listSelectClient());
                break;
            }
            case "pushSelectClient" : {
                for (Component component : view.getComponentsStaticPanel()) {
                    try {
                        if ((((JList) ((JViewport) ((JScrollPane) component).getComponent(0)).getComponent(0)).getSelectedIndex()) != -1) {
                            model.writeDataClient(view.getComponentsStaticPanel());
                            view.selectedClient(model.getDataClient());
                        }
                    } catch (Exception a) {}
                }
                break;
            }
            case "searchClientButton" :{
                view.listClientsAndSelect(model.listSelectClientSearch(view.getComponentsStaticPanel()));
                break;
            }

        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

        int select = e.getFirstIndex();
        System.out.println("we hehe");

    }
}
