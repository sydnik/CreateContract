package org.sydnik.createContract;

import org.sydnik.createContract.exception.CantWriteDoc;
import org.sydnik.createContract.exception.DontHaveData;
import org.sydnik.createContract.exception.DontHaveFilePattern;
import org.sydnik.createContract.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class MyController implements ActionListener, KeyListener, MouseListener {
    private Model model;
    private MyView view;
    private Display display;


    private ArrayList<TextField> listText = new ArrayList<>();
    private ArrayList<JTextPane> listJTextPane = new ArrayList<>();



    public MyController(Model model,MyView view) {
        this.model = model;
        this.view =view;
        view.setController(this);

    }

    public void setDisplay(Display display){
        this.display = display;
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
        if(((Component)e.getSource()).getName().contains("checkBox")){
            view.refreshCheckBox((JCheckBox) e.getSource());
        }
        try {
            switch (((Component) e.getSource()).getName()) {
                case "sittingsManager": {
                    view.settingsManager(model.getSalesManager());
                    break;
                }
                case "saveSittingsManager": {
                    model.setSalesManager(view.getComponentsStaticPanel());
                    view.writeJustMessage("Настройки сохранены", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "mainPage": {
                    view.mainPage(model.getSalesManager());
                    model.clearDataClient();
                    display= null;
                    break;
                }
                case "createNewClient": {
                    view.createNewClient();
                    break;
                }
                case "saveNewDataClient": {
                    model.createNewClient(view.getComponentsStaticPanel());
                    view.selectedClient(model.getDataClient());
                    break;
                }
                case "saveDataAboutClient": {
                    model.saveDataAboutClient(view.getComponentsStaticPanel());
                    view.selectedClient(model.getDataClient());
                    break;
                }
                case "selectClient": {
                    view.listClientsAndSelect(model.listSelectClient(), "");
                    model.clearDataClient();
                    break;
                }
                case "pushSelectClient": {
                    for (Component component : view.getComponentsStaticPanel()) {
                        try {
                            if ((((JList) ((JViewport) ((JScrollPane) component).getComponent(0)).getComponent(0)).getSelectedIndex()) != -1) {
                                model.writeDataClient(view.getComponentsStaticPanel());
                                if (model.getDataClient() != null) {
                                    view.selectedClient(model.getDataClient());
                                }
                            }
                        } catch (Exception a) {
                        }
                    }
                    break;
                }
                case "searchClientButton": {
                    String line = "";
                    for (Component component : view.getComponentsStaticPanel()) {
                        try {
                            switch (component.getName()) {
                                case "searchClient": {
                                    line = ((JTextField) component).getText();
                                    break;
                                }

                            }
                        } catch (Exception a) {
                        }
                    }
                    view.listClientsAndSelect(model.listSelectClientSearch(view.getComponentsStaticPanel()), line);
                    break;
                }
                case "editBasicContract": {
                    view.displayBasicContract(model.getDataClient(), model.getCurrencyValue());
                    break;
                }
                case "backSelectClient": {
                    view.selectedClient(model.getDataClient());
                    display = null;
                    break;
                }
                case "saveDataBaseContractClient": {
                    model.saveDataBaseContractClient(display);
                    model.createBaseContract();
                        view.writeJustMessage("Настройки сохранены", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "printBaseContract": {
                    model.saveDataBaseContractClient(display);
                    model.printDoc("printBaseContract");
                    break;
                }
                case "editDataAboutClient": {
                    view.editDataAboutClient(model.getDataClient());
                    break;
                }
                case "openFileBasicContract": {
                    model.saveDataBaseContractClient(display);
                    model.openDoc("openFileBasicContract");
                    break;
                }
                case "openFileSupplementaryAgreementBasicContract": {
                    model.saveDataSupplementaryAgreementBasicContract(display);
                    model.openDoc("openFileSupplementaryAgreementBasicContract");
                    break;
                }
                case "openDirectoryWithFile": {
                    model.openDirectory();
                    break;
                }
                case "editUpSaleContract": {
                    view.displayUpSaleContract(model.getDataClient());
                    break;
                }
                case "saveDataUpSaleContact": {
                    model.saveDataUpSale(display);
                    view.writeJustMessage("Настройки сохранены", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "openFileUpSaleContract": {
                    model.saveDataUpSale(display);
                    model.openDoc("openFileUpSaleContract");
                    break;
                }
                case "printUpSaleContract": {
                        model.saveDataUpSale(display);
                        model.printDoc("printUpSaleContract");
                    break;
                }
                case "printSupplementaryAgreementBasicContract": {

                    model.saveDataSupplementaryAgreementBasicContract(display);
                    model.printDoc("printSupplementaryAgreementBasicContract");
                    break;
                }
                case "editSupplementaryAgreementBasicContract": {
                    view.displaySupplementaryAgreementBasicContract(model.getDataClient(), model.getCurrencyValue());
                    break;
                }
                case "saveDataSupplementaryAgreementBasicContract": {
                    try {
                        model.saveDataSupplementaryAgreementBasicContract(display);
                        view.writeJustMessage("Настройки сохранены", JOptionPane.INFORMATION_MESSAGE);
                    } catch (CantWriteDoc | DontHaveFilePattern ex) {
                        view.writeJustMessage(ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
                case "saveCurrency": {
                    model.setCurrency(view.getComponentsStaticPanel());
                    view.mainPage(model.getSalesManager());
                    break;
                }
                case "editSupplementaryAgreementUpSale": {
                    view.displaySupplementaryAgreementUpSale(model.getDataClient());
                    break;
                }
                case "saveDataSupplementaryAgreementUpSaleContact": {
                    model.saveDataSupplementaryAgreementUpSaleContract(display);
                    view.writeJustMessage("Настройки сохранены", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "printSupplementaryAgreementUpSaleContract": {
                    model.saveDataSupplementaryAgreementUpSaleContract(display);
                    model.printDoc("printSupplementaryAgreementUpSaleContract");
                    break;
                }
                case "openFileSupplementaryAgreementUpSaleContract": {
                    model.saveDataSupplementaryAgreementUpSaleContract(display);
                    model.openDoc("openFileSupplementaryAgreementUpSaleContract");
                    break;
                }
                case "selectPath": {
                    view.selectPathForSaveContract();
                    break;
                }
                case "editInvoiceDocument": {
                    view.displayInvoiceDocument(model.getDataClient(), model.getCurrencyValue());
                    break;
                }
                case "saveDataInvoiceDocument": {
                    model.saveDataInvoiceDocument(display);
                    view.writeJustMessage("Настройки сохранены", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                case "printInvoiceDocument": {
                    model.saveDataInvoiceDocument(display);
                    model.printDoc("printInvoiceDocument");
                    break;
                }
                case "openFileInvoiceDocument": {
                    model.saveDataInvoiceDocument(display);
                    model.openDoc("openFileInvoiceDocument");
                    break;
                }

            }
        } catch (CantWriteDoc | DontHaveFilePattern cantWriteDoc) {
        view.writeJustMessage(cantWriteDoc.getMessage(), JOptionPane.ERROR_MESSAGE);
        } catch (DontHaveData dontHaveData) {
            view.writeJustMessage(dontHaveData.getMessage(), JOptionPane.WARNING_MESSAGE);
        }
        System.out.println(System.currentTimeMillis()-s);
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
                view.onlyNumber((JTextField) e.getSource());
                ((ViewBasicContract)display).editBasicContractEditAllSumInEUR();
                break;
            }
            case "searchClient" :{
                view.listClientsAndSelect(model.listSelectClientSearch(view.getComponentsStaticPanel()),((JTextField)e.getComponent()).getText());
                break;
            }
            case "prepaymentOr10PercentSum" :{
                view.onlyNumber((JTextField) e.getSource());
                ((ViewBasicContract)display).editBasicContractEditPrepaymentOr10PercentSum();
                break;
            }
            case "payUpTo50PercentSum":{
                view.onlyNumber((JTextField) e.getSource());
                ((ViewBasicContract)display).editBasicContractEditPayUpTo50PercentSum();
                break;
            }
            case "allSumUpSaleInBYN" :{
                view.onlyNumber((JTextField) e.getComponent());
                ((ViewUpSaleContract)display).editAllSumUpSaleInBYN();
                break;
            }
            case "prepaymentUpSale" : {
                view.onlyNumber((JTextField) e.getComponent());
                ((ViewUpSaleContract)display).editPrepaymentUpSaleUpSale();
                break;
            }
            case "currency":{
                view.onlyDoubleNumber((JTextField) e.getComponent(),4);
                ((ViewBasicContract)display).editCurrencyBasicContract();
                break;
            }
            case "allSumInEURSupplementaryAgreement" : {
                view.onlyNumber((JTextField) e.getComponent());
                ((ViewSupplementaryAgreementBasicContract)display).editAllEURSumAgreementBasicContract();
                break;
            }
            case "prepaymentOr10PercentSumSupplementaryAgreement" : {
                view.onlyNumber((JTextField) e.getComponent());
                ((ViewSupplementaryAgreementBasicContract)display).editPrepaymentOr10PercentSumSupplementaryAgreement();
                break;
            }
            case "payUpTo50PercentSumSupplementaryAgreement" : {
                view.onlyNumber((JTextField) e.getComponent());
                ((ViewSupplementaryAgreementBasicContract)display).editPayUpTo50percentAgreementBasicContract();
                break;
            }
            case "prepaymentUpSaleSupplementaryAgreement" :
            case "sumUpSaleInBYNSupplementaryAgreement" : {
                view.onlyNumber((JTextField) e.getComponent());
                ((ViewSupplementaryAgreementUpSale)display).editPrepaymentOrAllSumSupplementaryAgreementUpSale();
                break;
            }
            case "currencySumSupplementaryAgreement" :{
                view.onlyDoubleNumber((JTextField) e.getComponent(),4);
                ((ViewSupplementaryAgreementBasicContract)display).editCurrencyAgreementBasicContract();
                break;
            }
            case "priceInEURInvoiceDocument" :{
                view.onlyNumber((JTextField) e.getComponent());
                ((ViewInvoiceDocument) display).editPriceInEURInvoiceDocument();
                break;
            }
            case "priceBYNInvoiceDocument" :{
                view.onlyNumber((JTextField) e.getComponent());
                ((ViewInvoiceDocument) display).editPriceBYNInvoiceDocument();
                break;
            }
            case "currencyInvoiceDocument" :{
                view.onlyDoubleNumber((JTextField)e.getComponent(),4 );
                ((ViewInvoiceDocument) display).editCurrencyInvoiceDocument();
                break;
            }
            case "numberSupplementaryAgreementBasicContract" :
            case "numberPowerOfAttorney" : {
                view.onlyNumber((JTextField) e.getComponent());
                break;
            }
            case "vat20InvoiceDocument" : {
                view.onlyDoubleNumber(((JTextField)e.getComponent()),2);
                break;
            }
            case "dateCreateSupplementaryAgreementBasicContract" : {
                if(!model.isDateCurrency(((JTextField)e.getComponent()).getText())){
                    ((ViewSupplementaryAgreementBasicContract)display).setCurrencyZero(false);
                }
                else {
                    ((ViewSupplementaryAgreementBasicContract)display).setCurrencyZero(true);
                }
                ((ViewSupplementaryAgreementBasicContract) display).editCurrencyAgreementBasicContract();
                break;
            }
            case "dateCreateContract" : {
                if(!model.isDateCurrency(((JTextField)e.getComponent()).getText())){
                    ((ViewBasicContract)display).setCurrencyZero(false);
                }
                else {
                    ((ViewBasicContract)display).setCurrencyZero(true);
                }
                ((ViewBasicContract) display).editCurrencyBasicContract();
                break;
            }
            case "createDateInvoiceDocument" :{
                if(!model.isDateCurrency(((JTextField)e.getComponent()).getText())){
                    ((ViewInvoiceDocument)display).setCurrencyZero(false);
                }
                else {
                    ((ViewInvoiceDocument)display).setCurrencyZero(true);
                }
                ((ViewInvoiceDocument)display).editCurrencyInvoiceDocument();
                break;
            }
            case "valueCurrency" : {
                view.onlyDoubleNumber((JTextField) e.getComponent(),4);
                break;
            }
        }
        if(e.getComponent().getName().contains("dditionalProduct")){
            if(e.getComponent().getName().contains("supplementaryAgreement")){
                //"Этот блок отвественный за Доп соглашение Апсейл
                if (e.getComponent().getName().contains("Count")||e.getComponent().getName().contains("sDiscount")||
                        e.getComponent().getName().contains("FullPrice")){
                    view.onlyNumber((JTextField) e.getComponent());
                    ((ViewSupplementaryAgreementUpSale)display).editSupplementaryAgreementUpSaleSumProductPriceAndDiscountAndCount((JTextField) e.getComponent());

                }  if  (e.getComponent().getName().contains("WithDiscount")){
                    view.onlyNumber((JTextField) e.getComponent());
                    ((ViewSupplementaryAgreementUpSale)display).editWithDiscount();
                }
            }
            else {

                if (e.getComponent().getName().contains("additionalProductsCount")||e.getComponent().getName().contains("additionalProductsDiscount")|| e.getComponent().getName().contains("additionalProductsFullPrice")){
                    view.onlyNumber((JTextField) e.getComponent());
                    ((ViewUpSaleContract)display).editUpSaleEditSumProductPriceAndDiscountAndCount((JTextField) e.getComponent());


                }  if  (e.getComponent().getName().contains("additionalProductsWithDiscount")){
                    view.onlyNumber((JTextField) e.getComponent());
                    ((ViewUpSaleContract)display).editWithDiscount();
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
