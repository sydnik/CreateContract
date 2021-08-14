package org.sydnik.createContract;

import org.sydnik.createContract.exception.CantWriteDoc;
import org.sydnik.createContract.exception.DontHaveData;
import org.sydnik.createContract.view.Display;
import org.sydnik.createContract.view.ViewInvoiceDocument;

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
        switch (((Component)e.getSource()).getName()){
            case "sittingsManager":{
                view.settingsManager(model.getSalesManager());
                break;
            }
            case "saveSittingsManager":{
                try {
                    model.setSalesManager(view.getComponentsStaticPanel());
                    view.writeJustMessage("Настройки сохранены",JOptionPane.INFORMATION_MESSAGE);
                } catch (CantWriteDoc cantWriteDoc) {
                    view.writeJustMessage(cantWriteDoc.getMessage(),JOptionPane.ERROR_MESSAGE);
                }
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
                try {
                    model.createNewClient(view.getComponentsStaticPanel());
                    view.selectedClient(model.getDataClient());
                } catch (CantWriteDoc cantWriteDoc){
                    view.writeJustMessage(cantWriteDoc.getMessage(),JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
            case "saveDataAboutClient" :{
                try {
                    model.saveDataAboutClient(view.getComponentsStaticPanel());
                    view.selectedClient(model.getDataClient());
                }catch (CantWriteDoc cantWriteDoc){
                    view.writeJustMessage(cantWriteDoc.getMessage(),JOptionPane.ERROR_MESSAGE);
                }
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
                try {
                    model.saveDataBaseContractClient(view.getComponentsStaticPanel());
                    model.createBaseContract();
                    view.writeJustMessage("Настройки сохранены",JOptionPane.INFORMATION_MESSAGE);
                } catch (CantWriteDoc ex){
                    view.writeJustMessage(ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                }

                break;
            }
            case "printBaseContract" : {
                try {
                    model.saveDataBaseContractClient(view.getComponentsStaticPanel());
                    model.printDoc("printBaseContract");
                } catch (CantWriteDoc cantWriteDoc){
                    view.writeJustMessage(cantWriteDoc.getMessage(),JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
            case "editDataAboutClient" :{
                view.editDataAboutClient(model.getDataClient());
                break;
            }
            case "openFileBasicContract" :{
                try {
                    model.saveDataBaseContractClient(view.getComponentsStaticPanel());
                    model.openDoc("openFileBasicContract");
                } catch (CantWriteDoc cantWriteDoc){
                  view.writeJustMessage(cantWriteDoc.getMessage(),JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
            case "openFileSupplementaryAgreementBasicContract" :{
                try {
                    model.saveDataSupplementaryAgreementBasicContract(view.getComponentsStaticPanel());
                    model.openDoc("openFileSupplementaryAgreementBasicContract");
                } catch (CantWriteDoc cantWriteDoc){
                    view.writeJustMessage(cantWriteDoc.getMessage(),JOptionPane.ERROR_MESSAGE);
                }
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
                try {
                    model.saveDataUpSale(view.getComponentsStaticPanel());
                    view.writeJustMessage("Настройки сохранены",JOptionPane.INFORMATION_MESSAGE);
                } catch (CantWriteDoc cantWriteDoc){
                    view.writeJustMessage(cantWriteDoc.getMessage(),JOptionPane.ERROR_MESSAGE);
                }

                break;
            }
            case "openFileUpSaleContract" : {
                try {
                    model.saveDataUpSale(view.getComponentsStaticPanel());
                    model.openDoc("openFileUpSaleContract");
                } catch (CantWriteDoc cantWriteDoc){
                    view.writeJustMessage(cantWriteDoc.getMessage(),JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
            case "printUpSaleContract" : {
                try {
                    model.saveDataUpSale(view.getComponentsStaticPanel());
                    model.printDoc("printUpSaleContract");
                } catch (CantWriteDoc cantWriteDoc){
                    view.writeJustMessage(cantWriteDoc.getMessage(),JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
            case "printSupplementaryAgreementBasicContract" : {
                try {
                    model.saveDataSupplementaryAgreementBasicContract(view.getComponentsStaticPanel());
                    model.printDoc("printSupplementaryAgreementBasicContract");
                } catch (CantWriteDoc cantWriteDoc){
                    view.writeJustMessage(cantWriteDoc.getMessage(),JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
            case "editSupplementaryAgreementBasicContract" : {
                view.editSupplementaryAgreementBasicContract(model.getDataClient(),model.getCurrencyValue());
                break;
            }
            case "saveDataSupplementaryAgreementBasicContract": {
                try {
                    model.saveDataSupplementaryAgreementBasicContract(view.getComponentsStaticPanel());
                    view.writeJustMessage("Настройки сохранены",JOptionPane.INFORMATION_MESSAGE);
                } catch (CantWriteDoc cantWriteDoc){
                    view.writeJustMessage(cantWriteDoc.getMessage(),JOptionPane.ERROR_MESSAGE);
                }
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
                try {
                    model.saveDataSupplementaryAgreementUpSaleContract(view.getComponentsStaticPanel());
                    view.writeJustMessage("Настройки сохранены",JOptionPane.INFORMATION_MESSAGE);
                } catch (CantWriteDoc cantWriteDoc){
                    view.writeJustMessage(cantWriteDoc.getMessage(),JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
            case "printSupplementaryAgreementUpSaleContract" : {
                try {
                    model.saveDataSupplementaryAgreementUpSaleContract(view.getComponentsStaticPanel());
                    model.printDoc("printSupplementaryAgreementUpSaleContract");
                } catch (CantWriteDoc cantWriteDoc){
                    view.writeJustMessage(cantWriteDoc.getMessage(),JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
            case "openFileSupplementaryAgreementUpSaleContract" :{
                try {
                    model.saveDataSupplementaryAgreementUpSaleContract(view.getComponentsStaticPanel());
                    model.openDoc("openFileSupplementaryAgreementUpSaleContract");
                } catch (CantWriteDoc cantWriteDoc){
                    view.writeJustMessage(cantWriteDoc.getMessage(),JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
            case "selectPath" : {
                view.selectPathForSaveContract();
                break;
            }
            case "editInvoiceDocument" : {
                view.displayInvoiceDocument(model.getDataClient(),model.getCurrencyValue());
                break;
            }
            case "saveDataInvoiceDocument" : {
                try {
                    model.saveDataInvoiceDocument(display);
                    view.writeJustMessage("Настройки сохранены",JOptionPane.INFORMATION_MESSAGE);
                } catch (CantWriteDoc cantWriteDoc){
                    view.writeJustMessage(cantWriteDoc.getMessage(),JOptionPane.ERROR_MESSAGE);
                } catch (DontHaveData dontHaveData) {
                    view.writeJustMessage(dontHaveData.getMessage(),JOptionPane.WARNING_MESSAGE);
                }
                break;
            }
            case "printInvoiceDocument" : {
                try {
                    model.saveDataInvoiceDocument(display);
                    model.printDoc("printInvoiceDocument");
                } catch (CantWriteDoc cantWriteDoc){
                    view.writeJustMessage(cantWriteDoc.getMessage(),JOptionPane.ERROR_MESSAGE);
                } catch (DontHaveData dontHaveData) {
                    view.writeJustMessage(dontHaveData.getMessage(),JOptionPane.WARNING_MESSAGE);
                }
                break;
            }
            case "openFileInvoiceDocument" : {
                try {
                    model.saveDataInvoiceDocument(display);
                    model.openDoc("openFileInvoiceDocument");
                } catch (CantWriteDoc cantWriteDoc){
                    view.writeJustMessage(cantWriteDoc.getMessage(),JOptionPane.ERROR_MESSAGE);
                }catch (DontHaveData dontHaveData) {
                    view.writeJustMessage(dontHaveData.getMessage(),JOptionPane.WARNING_MESSAGE);
                }
                break;
            }

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
