package org.sydnik.createContract;

import org.sydnik.createContract.data.Currency;
import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.data.ListMaterial;
import org.sydnik.createContract.data.SalesManager;
import org.sydnik.createContract.exception.CantWriteDoc;
import org.sydnik.createContract.exception.DontHaveData;
import org.sydnik.createContract.exception.DontHaveFilePattern;
import org.sydnik.createContract.view.Display;
import org.sydnik.createContract.createFileDocument.*;
import org.sydnik.createContract.view.ViewCreateFileForCutting;
import org.sydnik.createContract.view.ViewSetting;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Model {
    private SalesManager salesManager;
    private DataClient dataClient;
    private Currency currency;

    public Model() {

            this.salesManager = SalesManager.load();
        if(salesManager==null) {
            salesManager = SalesManager.getBasicSalesManager();
        }
    }
    public void setCurrency(){
        currency = Currency.createCurrency();
    }
    public void setCurrency(Component[] components){
        double value = 0;
        for(Component component : components){
            try {
                if(component.getName().equals("valueCurrency")){
                    value=Double.parseDouble(((JTextField)component).getText());
                }
            }catch (Exception e){}

        }
        currency = new Currency(value);
    }
    public void setSalesManager(Display display) throws CantWriteDoc, DontHaveData {
        salesManager = new SalesManager(display.getDataForSave());
        salesManager.save();

    }
    public void addNewDecor(Display display) throws CantWriteDoc, DontHaveData {
        ListMaterial listMaterial = new ListMaterial();
        listMaterial.readListCatalog(((ViewSetting)display).getMaterialForAdd());
        listMaterial.addDecor(((ViewSetting)display).getDecorForAdd());
    }
    public void createNewClient(Display display) throws CantWriteDoc, DontHaveData {
        dataClient = new DataClient(display.getDataForSave());
        if(dataClient.checkNameFreeForSave(salesManager)){
            dataClient.save();
        }else throw new CantWriteDoc("такой номер догора и имя занято(Поменяйте как минимум Странное название)");


    }
    public void saveDataAboutClient(Display display) throws CantWriteDoc, DontHaveData {
        dataClient.setDateAboutClient(display.getDataForSave());
        dataClient.save();
    }
    public void saveDataBaseContractClient (Display display) throws CantWriteDoc, DontHaveFilePattern, DontHaveData {
        dataClient.setBaseContract(display.getDataForSave());
        dataClient.save();
        CreateDocumentsAndPrint.createBasicContract(dataClient,salesManager);
    }
    public void saveDataUpSale (Display display) throws CantWriteDoc, DontHaveFilePattern, DontHaveData {
        dataClient.setUpSaleContract(display.getDataForSave());
        dataClient.save();
        CreateDocumentsAndPrint.createUpSaleContract(dataClient,salesManager);
    }
    public void saveDataSupplementaryAgreementBasicContract(Display display) throws CantWriteDoc, DontHaveFilePattern, DontHaveData {
        dataClient.setDateSupplementaryAgreementBasicContract(display.getDataForSave());
        dataClient.save();
        CreateDocumentsAndPrint.createSupplementaryAgreementBasicContract(dataClient, salesManager);
    }
    public void saveDataSupplementaryAgreementUpSaleContract(Display display) throws CantWriteDoc, DontHaveFilePattern, DontHaveData {
        dataClient.setDateSupplementaryAgreementUpSaleContract(display.getDataForSave());
        dataClient.save();
        CreateDocumentsAndPrint.createSupplementaryAgreementUpSaleContract(dataClient, salesManager);
    }
    public void saveDataInvoiceDocument(Display display) throws CantWriteDoc, DontHaveData, DontHaveFilePattern {
        dataClient.setDataInvoiceDocument(display.getDataForSave());
        dataClient.save();
        CreateXlsXFile.createInvoiceDocument(dataClient,salesManager);
    }

    public void createFileForCutting(Display display) throws DontHaveFilePattern, CantWriteDoc {
        String material = ((ViewCreateFileForCutting)display).getValueMaterial();
        String decor = ((ViewCreateFileForCutting)display).getValueDecor();
        String edge = ((ViewCreateFileForCutting)display).getValueEdge();
        CreateFileForCutting.createFile(dataClient,salesManager,material,decor,edge);
    }
    //Возвращает лист всех папок(Клиентов)
    public String[] listSelectClient(){
        File path = new File("saveContract"); //path указывает на директорию
        String[] list = path.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.matches("([a-zA-Zа-яёА-ЯЁ]{2})\\d-\\d{6}-\\d{2}(.*)");
            }
        });
        return list;
    }
    public String[] listSelectClientSearch(Component[] components){
        String line= "";
        for(Component component:components) {
            try {
                switch (component.getName()) {
                    case "searchClient": {
                        line = ((JTextField) component).getText();
                        break;
                    }

                }
            }catch (Exception e){}
        }
        File path = new File("saveContract"); //path указывает на директорию
        String finalLine = line;
        String[] list = path.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if(name.matches("([a-zA-Zа-яёА-ЯЁ]{2})\\d-\\d{6}-\\d{2}(.*)"))
                {
                    return name.toLowerCase().contains(finalLine.toLowerCase());
                }
                return false;
            }
        });
        return list;
    }
    public void createBaseContract() throws CantWriteDoc, DontHaveFilePattern {
        CreateDocumentsAndPrint.createBasicContract(dataClient,salesManager);
    }
    public void printDoc(String nameDoc){
        // 150 мс недает встретится двум потокам
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CreateDocumentsAndPrint.printDoc(dataClient,nameDoc,salesManager);
    }
    public void openDoc(String s){
        String path =salesManager.getPathForSaveContract();
        if(path.equals("")){
            path = "saveContract\\";
        }
        switch (s){
            case "openFileBasicContract" : {
                try {
                    Desktop.getDesktop().open(new File(path+ "\\" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() + "/Договор"+dataClient.getNumberContract() + ".docx"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "openFileUpSaleContract": {
                try {
                    Desktop.getDesktop().open(new File(path + "\\" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() + "/ДоговорUpSale"+dataClient.getNumberContract() + ".docx"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            }
            case "openFileSupplementaryAgreementBasicContract" : {
                try {

                    Desktop.getDesktop().open(new File(path + "\\" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() +
                            "/Дополнительное соглашение №"+ dataClient.getSupplementaryAgreementBasicContract().getNumberSupplementaryAgreementBasicContract()+" " +dataClient.getNumberContract() + ".docx"));
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "openFileSupplementaryAgreementUpSaleContract" : {
                try {

                    Desktop.getDesktop().open(new File(path + "\\" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() + "/Дополнительное соглашение UpSale"+
                            dataClient.getSupplementaryAgreementUpSaleContract().getNumberSupplementaryAgreementUpSale()+" "+dataClient.getNumberContract() + ".docx"));
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "openFileInvoiceDocument" : {
                try {

                    Desktop.getDesktop().open(new File(path + "\\" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() +
                            "/Счет-фактура " + dataClient.getNumberContract()+ ".xlsx"));
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

    }
    public void openDirectory (){
        String path = salesManager.getPathForSaveContract();
        if(path.equals("")){
            path = "saveContract\\";
        }
        try {
            Desktop.getDesktop().open(new File(path + "\\"+dataClient.getNumberContract() + " " + dataClient.getStrangeName()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeDataClient(Component[] listComponent) {
        for(Component component:listComponent){
            try {
                if (component.getName().equals("ListClients")) {
                    dataClient = DataClient.load("saveContract/" +
                            (((JList)((JViewport)((JScrollPane)component).getComponent(0)).getComponent(0)).getSelectedValue())
                            +"/baseDataClient.dat");
                }
            }catch (Exception e){

            }
        }
    }
    public void clearDataClient(){
        dataClient = null;
    }

    public SalesManager getSalesManager() {
        return salesManager;
    }
    public DataClient getDataClient(){
        return dataClient;
    }
    public double getCurrencyValue(){
        return currency.getValue();
    }
    public boolean isDateCurrency(String date){
        if(currency.getDate().equals(date)){
            return true;
        }
        return false;
    }
}
