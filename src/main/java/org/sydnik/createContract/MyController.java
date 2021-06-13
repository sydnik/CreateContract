package org.sydnik.createContract;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.InvalidClassException;
import java.util.ArrayList;


public class MyController implements ActionListener, ListSelectionListener, FocusListener, TextListener, KeyListener {
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
        System.out.println(e);
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
                view.selectedClient(model.getDataClient());
                break;
            }
            case "selectClient" : {
                view.listClientsAndSelect(model.listSelectClient(),"");
                break;
            }
            case "pushSelectClient" : {
                for (Component component : view.getComponentsStaticPanel()) {
                    try {
                        if ((((JList) ((JViewport) ((JScrollPane) component).getComponent(0)).getComponent(0)).getSelectedIndex()) != -1) {
                            model.writeDataClient(view.getComponentsStaticPanel());
                            if(model.getDataClient()!=null) {
                                view.selectedClient(model.getDataClient());
                            }
                        }
                    } catch (Exception a) {}
                }
                break;
            }
            case "searchClientButton" :{
                String line= "";
                for(Component component:view.getComponentsStaticPanel()) {
                    try {
                        switch (component.getName()) {
                            case "searchClient": {
                                line = ((JTextField) component).getText();
                                break;
                            }

                        }
                    }catch (Exception a){}
                }
                view.listClientsAndSelect(model.listSelectClientSearch(view.getComponentsStaticPanel()),line);
                break;
            }
            case "editBasicContract" :{
                view.editBasicContract(model.getDataClient(),model.getCurrencyValue());
                break;
            }
            case "backSelectClient" : {
                view.selectedClient(model.getDataClient());
                break;
            }
            case "checkBoxCurrency" :
            case "checkBoxAllSumInEUR" :
            case "checkBoxPrepaymentOr10PercentSum" :
            case "checkBoxPayUpTo50PercentSum" :
            case "checkBoxPayUpTo100PercentSum" :
            case "checkBoxAllSumInBYN" :
            case "checkBoxDateCreateContract" :{
                view.editBasicContractEditCheckBox((JCheckBox) e.getSource());
                break;
            }
            case "saveDataBaseContractClient" :{
                model.saveDataBaseContractClient(view.getComponentsStaticPanel());
                break;
            }

        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
        System.out.println(e);
    }

    @Override
    public void textValueChanged(TextEvent e) {
        System.out.println(e);
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {
        //свич реализует логику если кнопку нажал и находится  в определеном поел то делаем это
        switch (e.getComponent().getName()){
            case "allSumInEUR":{
                    try {
                        view.editBasicContractEditAllSumInEUR(Integer.parseInt(view.getAllSumInEUR().getText().replaceAll("[^0-9]","")),model.getCurrencyValue());
                    }
                    catch (NumberFormatException s){
                    }
                break;
            }
            case "searchClient" :{
                String line= "";
                for(Component component:view.getComponentsStaticPanel()) {
                    try {
                        if((component.getName().equals("searchClient"))) {
                            line = ((JTextField) component).getText();
                        }
                    }catch (Exception a){}
                }
                view.listClientsAndSelect(model.listSelectClientSearch(view.getComponentsStaticPanel()),line);
                break;
            }
            case "prepaymentOr10PercentSum" :{
                try {
                    view.editBasicContractEditPrepaymentOr10PercentSum(Integer.parseInt(view.getAllSumInEUR().getText()),
                            Integer.parseInt(view.getPrepaymentOr10PercentSum().getText().replaceAll("[^0-9]","")));
                }catch (Exception t){
                }
                break;
            }
            case "payUpTo50PercentSum":{
                try {
                    view.editBasicContractEditPayUpTo50PercentSum(Integer.parseInt(view.getAllSumInEUR().getText()),
                            Integer.parseInt(view.getPrepaymentOr10PercentSum().getText()),
                            Integer.parseInt(view.getPayUpTo50PercentSum().getText().replaceAll("[^0-9]","")));
                }catch (Exception t){
                }
                break;
            }
            case "payUpTo100PercentSum":{ }
            case "allSumInBYN":{ }
        }
    }
}
