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
import org.sydnik.createContract.view.ViewSetting;

import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;

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
    public void setCurrency(Display display) throws DontHaveData {
        currency = new Currency(Double.parseDouble(display.getData().get("Result")));
    }
    public void setSalesManager(Display display) throws CantWriteDoc, DontHaveData {
        salesManager = new SalesManager(display.getData());
        salesManager.save();

    }
    public void addNewDecor(Display display) throws CantWriteDoc, DontHaveData {
        ListMaterial listMaterial = new ListMaterial();
        listMaterial.readListCatalog(((ViewSetting)display).getMaterialForAdd());
        listMaterial.addDecor(((ViewSetting)display).getDecorForAdd());
    }
    public void createNewClient(Display display) throws CantWriteDoc, DontHaveData {
        dataClient = new DataClient(display.getData());
        if(dataClient.checkNameFreeForSave(salesManager)){
            dataClient.save();
        }else throw new CantWriteDoc("такой номер догора и имя занято(Поменяйте как минимум Странное название)");


    }
    public void saveDataAboutClient(Display display) throws CantWriteDoc, DontHaveData {
        dataClient.setDateAboutClient(display.getData());
        dataClient.save();
    }
    public void saveDataBaseContractClient (Display display) throws CantWriteDoc, DontHaveFilePattern, DontHaveData {
        dataClient.setBaseContract(display.getData());
        dataClient.save();
        CreateDocumentsAndPrint.createBasicContract(dataClient,salesManager);
    }
    public void saveDataUpSale (Display display) throws CantWriteDoc, DontHaveFilePattern, DontHaveData {
        dataClient.setUpSaleContract(display.getData());
        dataClient.save();
        CreateDocumentsAndPrint.createUpSaleContract(dataClient,salesManager);
    }
    public void saveDataSupplementaryAgreementBasicContract(Display display) throws CantWriteDoc, DontHaveFilePattern, DontHaveData {
        dataClient.setDateSupplementaryAgreementBasicContract(display.getData());
        dataClient.save();
        CreateDocumentsAndPrint.createSupplementaryAgreementBasicContract(dataClient, salesManager);
    }
    public void saveDataSupplementaryAgreementUpSaleContract(Display display) throws CantWriteDoc, DontHaveFilePattern, DontHaveData {
        dataClient.setDateSupplementaryAgreementUpSaleContract(display.getData());
        dataClient.save();
        CreateDocumentsAndPrint.createSupplementaryAgreementUpSaleContract(dataClient, salesManager);
    }
    public void saveDataInvoiceDocument(Display display) throws CantWriteDoc, DontHaveData, DontHaveFilePattern {
        dataClient.setDataInvoiceDocument(display.getData());
        dataClient.save();
        CreateXlsXFile.createInvoiceDocument(dataClient,salesManager);
    }
    public void SaveFileForCutting(Display display) throws DontHaveFilePattern, CantWriteDoc, DontHaveData {
        String material = display.getData().get("material");
        String decor = display.getData().get("decor");
        String edge = display.getData().get("edge");
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
    public void createBaseContract() throws CantWriteDoc, DontHaveFilePattern {
        CreateDocumentsAndPrint.createBasicContract(dataClient,salesManager);
    }
    public void printDoc(String nameDoc) throws DontHaveFilePattern {
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
                    Desktop.getDesktop().open(new File(path+ "\\" + dataClient.getNumberContract() + " " +
                            dataClient.getStrangeName() + "/Договор"+dataClient.getNumberContract() + ".docx"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "openFileUpSaleContract": {
                try {
                    Desktop.getDesktop().open(new File(path + "\\" + dataClient.getNumberContract() + " " +
                            dataClient.getStrangeName() + "/ДоговорUpSale"+dataClient.getNumberContract() + ".docx"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            }
            case "openFileSupplementaryAgreementBasicContract" : {
                try {

                    Desktop.getDesktop().open(new File(path + "\\" + dataClient.getNumberContract() + " " +
                            dataClient.getStrangeName() + "/Дополнительное соглашение №"+
                            dataClient.getSupplementaryAgreementBasicContract().getNumber()+
                            " " +dataClient.getNumberContract() + ".docx"));
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "openFileSupplementaryAgreementUpSaleContract" : {
                try {
                    Desktop.getDesktop().open(new File(path + "\\" + dataClient.getNumberContract() + " " +
                            dataClient.getStrangeName() + "/Дополнительное соглашение UpSale"+
                            dataClient.getSupplementaryAgreementUpSaleContract().getNumber()+
                            " "+dataClient.getNumberContract() + ".docx"));
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "openFileInvoiceDocument" : {
                try {

                    Desktop.getDesktop().open(new File(path + "\\" + dataClient.getNumberContract() + " " +
                            dataClient.getStrangeName() + "/Счет-фактура " + dataClient.getNumberContract()+ ".xlsx"));
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

    public void writeDataClient(HashMap<String,String> map) {
        dataClient = DataClient.load("saveContract/" +map.get("result") +"/baseDataClient.dat");

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
        return currency.getRate();
    }
    public boolean isDateCurrency(String date){
        if(currency.getDate().equals(date)){
            return true;
        }
        return false;
    }
}
