package org.sydnik.createContract;

import org.sydnik.createContract.data.Currency;
import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.data.SalesManager;

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
            System.out.println("i tutu");
            salesManager = new SalesManager("Нужно имя тебе", "99",
                    "99.99.9999","+375(00) 000 0000","");
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
    public void setSalesManager(Component[] listComponent){
        String fullName = null;
        String numberPhoneManager = null;
        String numberPowerOfAttorney = null;
        String datePowerOfAttorney = null;
        String pathForSaveContact = null;
        for (Component component:listComponent) {
            try {
                switch ((String) component.getName()) {
                    case "fullName": {
                        fullName = ((JTextField) component).getText();
                        break;
                    }
                    case "numberPowerOfAttorney": {
                        numberPowerOfAttorney = ((JTextField) component).getText();
                        break;
                    }
                    case "datePowerOfAttorney": {
                        datePowerOfAttorney = ((JTextField) component).getText();
                        break;
                    }
                    case "numberPhoneManager": {
                        numberPhoneManager = ((JTextField) component).getText();
                        break;
                    }
                    case "pathForSaveContact": {
                        pathForSaveContact = ((JTextField) component).getText();
                        break;
                    }
                }
            } catch (Exception e) {
            }
        }
        salesManager = new SalesManager(fullName,numberPowerOfAttorney,datePowerOfAttorney,numberPhoneManager,pathForSaveContact);
        salesManager.save();

    }
    public void createNewClient(Component[] listComponent){

        Map<String,String> mapDataClient = new HashMap<>();
        for(Component component:listComponent){
            try {
            switch ( component.getName()) {
                case "numberContract": {
                    mapDataClient.put("numberContract",((JTextField) component).getText());
                    break;
                }
                case "strangeName" : {
                    mapDataClient.put("strangeName", ((JTextField) component).getText());
                    break;
                }
                case "fullNameClient": {
                    mapDataClient.put("fullNameClient",((JTextField) component).getText());
                    break;
                }
                case "numberPassport": {
                    mapDataClient.put("numberPassport", ((JTextField) component).getText());
                    break;
                }
                case "issuedByPassport": {
                    mapDataClient.put("issuedByPassport", ((JTextField) component).getText());
                    break;
                }
                case "whenIssued": {
                    mapDataClient.put("whenIssued", ((JTextField) component).getText());
                    break;
                }
                case "identificationNumber": {
                    mapDataClient.put("identificationNumber", ((JTextField) component).getText());
                    break;
                }
                case "addressRegistration": {
                    mapDataClient.put("addressRegistration", ((JTextField) component).getText());
                    break;
                }
                case "addressDelivery": {
                    mapDataClient.put("addressDelivery", ((JTextField) component).getText());
                    break;
                }
                case "numberPhoneClient": {
                    mapDataClient.put("numberPhoneClient", ((JTextField) component).getText());
                    break;
                }
            }
            }catch (Exception e){}
        }

        dataClient = new DataClient(mapDataClient);
        dataClient.save();
    }
    public void saveDataAboutClient(Component[] listComponent){
        Map<String,String> mapDataClient = new HashMap<>();
        for(Component component:listComponent){
            try {
                switch ( component.getName()) {

                    case "numberContract": {
                        mapDataClient.put("numberContract",((JTextField) component).getText());
                        break;
                    }
                    case "strangeName" : {
                        mapDataClient.put("strangeName", ((JTextField) component).getText());
                        break;
                    }
                    case "fullNameClient": {
                        mapDataClient.put("fullNameClient",((JTextField) component).getText());
                        break;
                    }
                    case "numberPassport": {
                        mapDataClient.put("numberPassport", ((JTextField) component).getText());
                        break;
                    }
                    case "issuedByPassport": {
                        mapDataClient.put("issuedByPassport", ((JTextField) component).getText());
                        break;
                    }
                    case "whenIssued": {
                        mapDataClient.put("whenIssued", ((JTextField) component).getText());
                        break;
                    }
                    case "identificationNumber": {
                        mapDataClient.put("identificationNumber", ((JTextField) component).getText());
                        break;
                    }
                    case "addressRegistration": {
                        mapDataClient.put("addressRegistration", ((JTextField) component).getText());
                        break;
                    }
                    case "addressDelivery": {
                        mapDataClient.put("addressDelivery", ((JTextField) component).getText());
                        break;
                    }
                    case "numberPhoneClient": {
                        mapDataClient.put("numberPhoneClient", ((JTextField) component).getText());
                        break;
                    }

                }
            }catch (Exception e){}
        }

        dataClient.setDateAboutClient(mapDataClient);
        dataClient.save();
    }
    public void saveDataBaseContractClient (Component[] components){
        Map<String,String> mapDataBaseContract = new HashMap<>();
        for (Component component : components){
            try {
                switch (component.getName()) {
                    case "dateCreateContract": {
                        mapDataBaseContract.put("dateCreateContract", ((JTextField) component).getText());
                        break;
                    }
                    case "timeProduction": {
                        mapDataBaseContract.put("timeProduction", (String)((JComboBox)component).getSelectedItem());

                        break;
                    }
                    case "allSumInEUR": {
                        mapDataBaseContract.put("allSumInEUR", ((JTextField) component).getText());
                        break;
                    }
                    case "allSumInBYN": {
                        mapDataBaseContract.put("allSumInBYN", ((JTextField) component).getText());
                        break;
                    }
                    case "prepaymentOr10PercentSum": {
                        mapDataBaseContract.put("prepaymentOr10PercentSum", ((JTextField) component).getText());
                        break;
                    }
                    case "payUpTo50PercentSum": {
                        mapDataBaseContract.put("payUpTo50PercentSum", ((JTextField) component).getText());
                        break;
                    }
                    case "payUpTo100PercentSum": {
                        mapDataBaseContract.put("payUpTo100PercentSum", ((JTextField) component).getText());
                        break;
                    }
                }
            }
            catch (Exception a){}

        }
        dataClient.setBaseContract(mapDataBaseContract);
        dataClient.save();
    }
    public void saveDataUpSale (Component[] components){
        Map<String,String> mapDataUpSale = new HashMap<>();
        for (Component component : components){
            if(component instanceof JTextField){
                try {
                    mapDataUpSale.put(component.getName(),((JTextField)component).getText());
                } catch (Exception e){
                    mapDataUpSale.put(component.getName(),"");
                }

            }

        }
        dataClient.setUpSaleContract(mapDataUpSale);
        dataClient.save();
        CreateDocumentsAndPrint.createUpSaleContract(dataClient,salesManager);
    }
    public void saveDateSupplementaryAgreementBasicContract (Component[] components){
        Map<String,String> mapDataUpSale = new HashMap<>();
        for (Component component : components){
            if(component instanceof JTextField){
                try {
                    mapDataUpSale.put(component.getName(),((JTextField)component).getText());
                } catch (Exception e){
                    mapDataUpSale.put(component.getName(),"");
                }
            }
        }
        dataClient.setDateSupplementaryAgreementBasicContract(mapDataUpSale);
        dataClient.save();
        CreateDocumentsAndPrint.createSupplementaryAgreementBasicContract(dataClient, salesManager);
    }
    public void saveDateSupplementaryAgreementUpSaleContract (Component[] components){
        Map<String,String> mapDataUpSale = new HashMap<>();
        for (Component component : components){
            if(component instanceof JTextField){
                try {
                    mapDataUpSale.put(component.getName(),((JTextField)component).getText());
                } catch (Exception e){
                    mapDataUpSale.put(component.getName(),"");
                }
            }
        }
        dataClient.setDateSupplementaryAgreementUpSaleContract(mapDataUpSale);
        dataClient.save();
        CreateDocumentsAndPrint.createSupplementaryAgreementUpSaleContract(dataClient, salesManager);
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
    public void createBaseContract(){
        CreateDocumentsAndPrint.createBaseContract(dataClient,salesManager);
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
            }
            case "openFileSupplementaryAgreementUpSaleContract" : {
                try {

                    Desktop.getDesktop().open(new File(path + "\\" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() + "/Дополнительное соглашение UpSale"+
                            dataClient.getSupplementaryAgreementUpSaleContract().getNumberSupplementaryAgreementUpSale()+" "+dataClient.getNumberContract() + ".docx"));
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
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
}
