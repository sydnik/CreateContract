package org.sydnik.createContract;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class MyController implements ActionListener, ListSelectionListener, FocusListener, TextListener, KeyListener, MouseListener {
    private Model model;
    private MyView view;


    private ArrayList<TextField> listText = new ArrayList<>();
    private ArrayList<JTextPane> listJTextPane = new ArrayList<>();



    public MyController(Model model,MyView view) {
        this.model = model;
        this.view =view;
        view.setController(this);

    }

    public void displayMainPage(){
        view.mainPage(model.getSalesManager());
    }
    public void displayRateEUR() {
        view.displayInputRate();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
        long s = System.currentTimeMillis();
        switch (((Component)e.getSource()).getName()){
            case "sittingsManager":{
                view.settingsManager(model.getSalesManager());
                break;
            }
            case "saveSittingsManager":{
                model.setSalesManager(view.getComponentsStaticPanel());
//                view.writeMessage("Настройки сохранены");
                break;
            }
            case "mainPage" :{
                view.mainPage(model.getSalesManager());
                model.clearDataClient();
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
            case "saveDataAboutClient" :{
                model.saveDataAboutClient(view.getComponentsStaticPanel());
                view.selectedClient(model.getDataClient());
                break;
            }
            case "selectClient" : {
                view.listClientsAndSelect(model.listSelectClient(),"");
                model.clearDataClient();
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
            case "saveDataBaseContractClient" :{
                model.saveDataBaseContractClient(view.getComponentsStaticPanel());
                model.createBaseContract();
                break;
            }
            case "printBaseContract" : {
                model.saveDataBaseContractClient(view.getComponentsStaticPanel());
                model.printDoc("printBaseContract");
                break;
            }
            case "editDataAboutClient" :{
                view.editDataAboutClient(model.getDataClient());
                break;
            }
            case "openFileBasicContract" :{
                model.saveDataBaseContractClient(view.getComponentsStaticPanel());
                model.openDoc("openFileBasicContract");
                break;
            }
            case "openFileSupplementaryAgreementBasicContract" :{
                model.saveDateSupplementaryAgreementBasicContract(view.getComponentsStaticPanel());
                model.openDoc("openFileSupplementaryAgreementBasicContract");
                break;
            }
            case "openDirectoryWithFile" :{
                model.openDirectory();
                break;
            }
            case "editUpSaleContract" : {
                view.editUpSaleContract(model.getDataClient());
                break;
            }
            case "saveDataUpSaleContact" :{
                model.saveDataUpSale(view.getComponentsStaticPanel());
                break;
            }
            case "openFileUpSaleContract" : {
                model.saveDataUpSale(view.getComponentsStaticPanel());
                model.openDoc("openFileUpSaleContract");
                break;
            }
            case "printUpSaleContract" : {
                model.saveDataUpSale(view.getComponentsStaticPanel());
                model.printDoc("printUpSaleContract");
                break;
            }
            case "printSupplementaryAgreementBasicContract" : {
                model.saveDateSupplementaryAgreementBasicContract(view.getComponentsStaticPanel());
                model.printDoc("printSupplementaryAgreementBasicContract");
                break;
            }
            case "editSupplementaryAgreementBasicContract" : {
                view.editSupplementaryAgreementBasicContract(model.getDataClient(),model.getCurrencyValue());
                break;
            }
            case "saveDataSupplementaryAgreementBasicContract": {
                model.saveDateSupplementaryAgreementBasicContract(view.getComponentsStaticPanel());
                break;
            }
            case "saveCurrency" : {
                model.setCurrency(view.getComponentsStaticPanel());
                view.mainPage(model.getSalesManager());
                break;
            }
            case "editSupplementaryAgreementUpSale" :{
                view.editSupplementaryAgreementUpSale(model.getDataClient());
                break;
            }
            case "saveDataSupplementaryAgreementUpSaleContact" :{
                model.saveDateSupplementaryAgreementUpSaleContract(view.getComponentsStaticPanel());
                break;
            }
            case "printSupplementaryAgreementUpSaleContract" : {
                model.saveDateSupplementaryAgreementUpSaleContract(view.getComponentsStaticPanel());
                model.printDoc("printSupplementaryAgreementUpSaleContract");
                break;
            }
            case "openFileSupplementaryAgreementUpSaleContract" :{
                model.saveDateSupplementaryAgreementUpSaleContract(view.getComponentsStaticPanel());
                model.openDoc("openFileSupplementaryAgreementUpSaleContract");
                break;
            }
            case "selectPath" : {
                view.selectPathForSaveContract();
                break;
            }
            case "checkBoxPayUpTo100percentUpSaleSupplementaryAgreement" :
            case "checkBoxSumUpSaleInBYNSupplementaryAgreement" :
            case "checkBoxPrepaymentUpSaleSupplementaryAgreement" :
            case "checkBoxPayUpTo100percentSupplementaryAgreementUpSale" :
            case "checkBoxDateCreateSupplementaryAgreementUpSale" :
            case "checkBoxNumberSupplementaryAgreementUpSale" :
            case "checkBoxDateCreateContract" :
            case "checkBoxCurrencySumSupplementaryAgreement" :
            case "checkBoxAllSumInEURSupplementaryAgreement" :
            case "checkBoxPayUpTo50PercentSumSupplementaryAgreement" :
            case "checkBoxPayUpTo100PercentSumSupplementaryAgreement" :
            case "checkBoxAllSumInBYNSupplementaryAgreement":
            case "checkBoxDateCreateSupplementaryAgreementBasicContract" :
            case "checkBoxCurrency" :
            case "checkBoxAllSumInEUR" :
            case "checkBoxPrepaymentOr10PercentSum" :
            case "checkBoxPayUpTo50PercentSum" :
            case "checkBoxPayUpTo100PercentSum" :
            case "checkBoxAllSumInBYN" :
            case "checkAllSumUpSaleInBYN" :
            case "checkBoxPrepaymentUpSale" :
            case "checkBoxPayUpTo100percentUpSale" :
            case "checkBoxDateCreateUpSaleContract" :
            case "checkBoxNumberSupplementaryAgreementBasicContract" :
            case "checkBoxPrepaymentOr10PercentSumSupplementaryAgreement" :{
                view.refreshCheckBox((JCheckBox) e.getSource());
                break;
            }


        }
        System.out.println(System.currentTimeMillis()-s);
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
                        view.editBasicContractEditAllSumInEUR(Integer.parseInt(view.getAllSumInEUR().getText().replaceAll("[^0-9]","")));
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
            case "allSumUpSaleInBYN" :{
                try {
                    view.onlyNumber((JTextField) e.getComponent());
                    view.editUpSaleEditPrepaymentUpSale(Integer.parseInt(((JTextField)e.getComponent()).getText()));
                }
                catch (Exception a) {

                }
                break;

            }
            case "prepaymentUpSale" : {
                try {
                    view.onlyNumber((JTextField) e.getComponent());
                    view.editUpSaleEditPayUpTo100percentUpSale(Integer.parseInt(((JTextField)e.getComponent()).getText()));
                }
                catch (Exception a) { }
                break;
            }
            case "currency":{
                try {
                    Double.parseDouble(((JTextField)e.getComponent()).getText());
                    view.editCurrencyBasicContract((JTextField) e.getComponent());
                } catch (Exception f){

                }
            }
            case "allSumInEURSupplementaryAgreement" : {
                try {
                    view.onlyNumber((JTextField) e.getComponent());
                    view.editEditAllSumAgreementBasicContract(Integer.parseInt(((JTextField)e.getComponent()).getText()));
                }
                catch (Exception a) {

                }
                break;
            }
            case "prepaymentOr10PercentSumSupplementaryAgreement" : {
                try {
                    view.onlyNumber((JTextField) e.getComponent());
                    view.editEditPayUpTo50percentAgreementBasicContract();
                }
                catch (Exception a) {

                }
                break;
            }
            case "payUpTo50PercentSumSupplementaryAgreement" : {
                try {
                    view.onlyNumber((JTextField) e.getComponent());
                    view.editEditPayUpTo100percentAgreementBasicContract();
                }
                catch (Exception a) {

                }
                break;
            }
            case "numberSupplementaryAgreementBasicContract" : {
                view.onlyNumber((JTextField) e.getComponent());
                break;
            }
            case "prepaymentUpSaleSupplementaryAgreement" :
            case "sumUpSaleInBYNSupplementaryAgreement" : {
                view.onlyNumber((JTextField) e.getComponent());
                view.editSupplementaryAgreementUpSaleEditPayUpTo100percentUpSale();
                break;
            }
            case "currencySumSupplementaryAgreement" :{
                Double.parseDouble(((JTextField)e.getComponent()).getText());
                view.editCurrencyAgreementBasicContract((JTextField) e.getComponent());
                break;
            }

        }
        if(e.getComponent().getName().contains("dditionalProduct")){
            if(e.getComponent().getName().contains("supplementaryAgreement")){
                if (e.getComponent().getName().contains("Count")||e.getComponent().getName().contains("sDiscount")||
                        e.getComponent().getName().contains("FullPrice")){
                    try {
                        Integer.parseInt(((JTextField)e.getComponent()).getText());
                        view.editSupplementaryAgreementUpSaleEditSumProductWithDiscount((JTextField) e.getComponent());
                    }
                    catch (Exception a) {
                        view.onlyNumber((JTextField) e.getComponent());
                        view.editSupplementaryAgreementUpSaleEditSumProductWithDiscount((JTextField) e.getComponent());
                    }

                }  if  (e.getComponent().getName().contains("WithDiscount")){
                    try {
                        Integer.parseInt(((JTextField)e.getComponent()).getText());
                        view.editSupplementaryAgreementUpSaleEditAllSum();
                    }
                    catch (Exception a) {
                        view.onlyNumber((JTextField) e.getComponent());
                        view.editSupplementaryAgreementUpSaleEditAllSum();
                    }
                }
            }
            else {

                if (e.getComponent().getName().contains("additionalProductsCount")||e.getComponent().getName().contains("additionalProductsDiscount")||
                        e.getComponent().getName().contains("additionalProductsFullPrice")){
                    try {
                        Integer.parseInt(((JTextField)e.getComponent()).getText());
                        view.editUpSaleEditSumProductWithDiscount((JTextField) e.getComponent());
                    }
                    catch (Exception a) {
                        view.onlyNumber((JTextField) e.getComponent());
                        view.editUpSaleEditSumProductWithDiscount((JTextField) e.getComponent());
                    }

                }  if  (e.getComponent().getName().contains("additionalProductsWithDiscount")){
                    try {
                        Integer.parseInt(((JTextField)e.getComponent()).getText());
                        view.editUpSaleEditAllSum();
                    }
                    catch (Exception a) {
                        view.onlyNumber((JTextField) e.getComponent());
                        view.editUpSaleEditAllSum();
                    }
                }

            }



        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (((Component) e.getSource()).getName()) {
            case "jListClients": {
                if (e.getClickCount() == 2) {
                    int index = ((JList) e.getSource()).locationToIndex(e.getPoint());
                    if(index!=-1) {
                        model.writeDataClient(view.getComponentsStaticPanel());
                        if (model.getDataClient() != null) {
                            view.selectedClient(model.getDataClient());
                        }
                    }
                }
                break;
            }
        }


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
