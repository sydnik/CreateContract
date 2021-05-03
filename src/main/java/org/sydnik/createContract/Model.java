package org.sydnik.createContract;

import javax.swing.*;
import java.awt.*;
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
    public void createNewClient(Component[] listComponent){

        Map<String,String> mapDataClient = new HashMap<>();
        String numberContract = "";
        String fullNameClient= "";
        for(Component component:listComponent){
            try {
                System.out.println(((JTextField) component).getName());
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
        DataClient.save(dataClient,"saveContract/" + fullNameClient+ numberContract.replace(" ","")  +"/baseDataClient.dat");
    }

    public SalesManager getSalesManager() {
        return salesManager;
    }

    public DataClient getDataClient() {
        return dataClient;
    }
}
