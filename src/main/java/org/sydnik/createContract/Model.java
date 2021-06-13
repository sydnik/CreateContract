package org.sydnik.createContract;

import org.sydnik.createContract.data.Currency;
import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.data.SalesManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Model {
    private SalesManager salesManager;
    private DataClient dataClient;
    private Currency currency;

    public Model() {

            this.salesManager = SalesManager.load();
        if(salesManager==null) {
            salesManager = new SalesManager("Нужно имя тебе", 99,
                    "99.99.9999","+375(00) 000 0000");
        }
        currency = Currency.createCurrency();


    }
    //Изменяю настройки главное менеджера
    public void setSalesManager(Component[] listComponent){
        String fullName = null;
        String numberPhoneManager = null;
        int numberPowerOfAttorney = 0;
        String datePowerOfAttorney = null;
        for (Component component:listComponent) {
            try {
                switch ((String) component.getName()) {
                    case "fullName": {
                        fullName = ((JTextField) component).getText();
                        break;
                    }
                    case "numberPowerOfAttorney": {
                        numberPowerOfAttorney = Integer.parseInt(((JTextField) component).getText());
                        break;
                    }
                    case "datePowerOfAttorney": {
                        datePowerOfAttorney = ((JTextField) component).getText();
                        break;
                    } case "numberPhoneManager": {
                        numberPhoneManager = ((JTextField) component).getText();
                    }
                }
            } catch (Exception e) {
            }
        }
        salesManager = new SalesManager(fullName,numberPowerOfAttorney,datePowerOfAttorney,numberPhoneManager);
        salesManager.save();

    }
    //Создаю нового клиента
    public void createNewClient(Component[] listComponent){

        Map<String,String> mapDataClient = new HashMap<>();
        String numberContract = "";
        String fullNameClient= "";
        for(Component component:listComponent){
            try {
            switch ( component.getName()) {

                    case "numberContract": {
                        numberContract = ((JTextField) component).getText();
                        break;
                    }
                    case "fullNameClient": {
                        fullNameClient = ((JTextField) component).getText();
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

        dataClient = new DataClient(numberContract,fullNameClient,mapDataClient);
        System.out.println(fullNameClient);
        System.out.println(numberContract);
        dataClient.save();
    }
    public void saveDataBaseContractClient (Component[] components){
        Map<String,String> mapDataBaseContract = new HashMap<>();
        for (Component component : components){
            try {
                System.out.println(component.getName());
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
        mapDataBaseContract.put("fullNameSalesManager",salesManager.getFullName());
        mapDataBaseContract.put("miniSalesManager",salesManager.getMiniName());
        dataClient.setBaseContract(mapDataBaseContract);
        dataClient.save();
    }
    //Возвращает лист всех папок(Клиентов)
    public String[] listSelectClient(){
        File path = new File("saveContract"); //path указывает на директорию
        String[] list = path.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.matches("(.*)([a-zA-Zа-яёА-ЯЁ]{2})\\d-\\d{6}-\\d{2}");
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
                if(name.matches("(.*)([a-zA-Zа-яёА-ЯЁ]{2})\\d-\\d{6}-\\d{2}"))
                {
                    return name.toLowerCase().contains(finalLine.toLowerCase());
                }
                return false;
            }
        });
        return list;
    }
    public void createBaseContract(){
        try {
            String docWord = Files.readString(Paths.get(
                    "saveContract/Судникович Виталий Олегович МН5-210402-76/testXML.xml"));
            docWord = docWord.replace("NumberContract", "МН5-210402-76");

            Files.write(Paths.get("saveContract/Судникович Виталий Олегович МН5-210402-76/testXML2.xml"), docWord.getBytes(StandardCharsets.UTF_8));

            String fileName = "saveContract/Судникович Виталий Олегович МН5-210402-76/testXML.xml";
            String search = "NumberContract";
            String replace = "МН5-210402-76";
            Charset charset = StandardCharsets.UTF_8;
            Path path = Paths.get(fileName);
            Files.write(path,
                    new String(Files.readAllBytes(path), charset).replace(search, replace)
                            .getBytes(charset));
        }
        catch (Exception e){}

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
