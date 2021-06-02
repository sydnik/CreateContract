package org.sydnik.createContract.data;

import java.io.*;
import java.util.Date;
import java.util.Map;

public class DataClient implements Serializable {
    private  Map<String,String> dataMap ;
    //data client
    private String numberContract;
    private String fullNameClient;
    private String miniNameClient;
    private String numberPassport;
    private String issuedByPassport;
    private String whenIssued;
    private String identificationNumber;
    private String addressRegistration;
    private String addressDelivery;
    private String numberPhoneClient;
    // base contract
    private Date dateCreateContract;
    private String fullNameSalesManager;
    private String miniSalesManager;
    private String timeProduction;
    private int allSumInEUR;
    private int allSumInBYN;
    private int prepaymentOr10PercentSum;
    private int payUpTo50PercentSum;
    private int payUpTo100PercentSum;
    // UpSale contract


    public DataClient(String numberContract, String fullNameClient,Map<String,String> mapDataClient) {
        this.numberContract = numberContract.replace(" ","");
        this.fullNameClient = fullNameClient;
        setMiniNameClient(fullNameClient);
        numberPassport = mapDataClient.get("numberPassport");
        issuedByPassport = mapDataClient.get("issuedBy");
        whenIssued = mapDataClient.get("whenIssued");
        identificationNumber = mapDataClient.get("identificationNumber");
        addressRegistration = mapDataClient.get("addressRegistration");
        addressDelivery = mapDataClient.get("addressDelivery");
        numberPhoneClient = mapDataClient.get("numberPhoneClient");

    }


    private void setMiniNameClient(String fullName){
        String[] createMiniName = fullName.split(" ");
        miniNameClient = createMiniName[0]+" ";
        for(int i= 0;i<createMiniName.length;i++){
            if(i>0){
                createMiniName[i] = createMiniName[i].substring(0,1)+".";
                miniNameClient = miniNameClient + createMiniName[i];
            }

        }
    }


    public void setDateClient(){

    }


    public Map<String, String> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, String> dataMap) {
        this.dataMap = dataMap;
    }


    public String numberInWord(){
        System.out.println();

        return null;
    }


    public String getNumberContract() {
        return numberContract;
    }
    public String getFullNameClient() {
        return fullNameClient;
    }
    public String getMiniNameClient() {
        return miniNameClient;
    }
    public String getNumberPassport() {
        return numberPassport;
    }
    public String getIssuedByPassport() {
        return issuedByPassport;
    }
    public String getWhenIssued() {
        return whenIssued;
    }
    public String getIdentificationNumber() {
        return identificationNumber;
    }
    public String getAddressRegistration() {
        return addressRegistration;
    }
    public String getAddressDelivery() {
        return addressDelivery;
    }
    public String getNumberPhoneClient() {
        return numberPhoneClient;
    }
    public Date getDateCreateContract() {
        return dateCreateContract;
    }
    public String getFullNameSalesManager() {
        return fullNameSalesManager;
    }
    public String getMiniSalesManager() {
        return miniSalesManager;
    }
    public String getTimeProduction() {
        return timeProduction;
    }
    public int getAllSumInEUR() {
        return allSumInEUR;
    }
    public int getAllSumInBYN() {
        return allSumInBYN;
    }
    public int getPrepaymentOr10PercentSum() {
        return prepaymentOr10PercentSum;
    }
    public int getPayUpTo50PercentSum() {
        return payUpTo50PercentSum;
    }
    public int getPayUpTo100PercentSum() {
        return payUpTo100PercentSum;
    }

    public void save(DataClient dataClient){
        String path ="saveContract/" + fullNameClient+" "+ numberContract  +"/baseDataClient.dat";
        String[] pathArray = path.split("/");
        path = "";
        if(pathArray.length>1){
            for (int i = 0; i < pathArray.length-1; i++) {
                path = path  + pathArray[i] + "/";
            }
        }
        new File(path).mkdirs();
        path = path + pathArray[pathArray.length-1];
        try {
            FileOutputStream fileOutput = new FileOutputStream(path);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);
            outputStream.writeObject(dataClient);
            fileOutput.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static DataClient load(String path){
        try {
            FileInputStream fiStream = new FileInputStream(path);
            ObjectInputStream objectStream = new ObjectInputStream(fiStream);
            Object object = objectStream.readObject();
            fiStream.close();
            objectStream.close();

            return  (DataClient) object;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
