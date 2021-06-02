package org.sydnik.createContract;

import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.data.SalesManager;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Model {
    private SalesManager salesManager;
    private DataClient dataClient;

    public Model() {

            this.salesManager = SalesManager.load();
        if(salesManager==null) {
            salesManager = new SalesManager("Нужно имя тебе", 99, "99.99.9999");
        }


    }
    //Изменяю настройки главное менеджера
    public void setSalesManager(Component[] listComponent){
        String fullName = null;
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
                    }
                }
            } catch (Exception e) {
            }
        }
        salesManager = new SalesManager(fullName,numberPowerOfAttorney,datePowerOfAttorney);
        SalesManager.save(salesManager);

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
        dataClient.save(dataClient);
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
                    return name.contains(finalLine);
                }
                return false;
            }
        });
        return list;
    }

    public SalesManager getSalesManager() {
        return salesManager;
    }

    public void writeDataClient(Component[] listComponent) {
        for(Component component:listComponent){
            try {
                if (component.getName().equals("ListClients")) {
                    dataClient = DataClient.load("saveContract/" +
                            (((JList)((JViewport)((JScrollPane)component).getComponent(0)).getComponent(0)).getSelectedValue())
                            +"/baseDataClient.dat");
                }
            }catch (Exception e){}
        }
    }

    public DataClient getDataClient(){
        return dataClient;
    }
}
