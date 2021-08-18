package org.sydnik.createContract.data;

import org.sydnik.createContract.data.contract.*;
import org.sydnik.createContract.exception.CantWriteDoc;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataClient implements Serializable {
    private String numberContract;
    private String strangeName;
    private String fullNameClient;
    private String miniNameClient;
    private String numberPassport;
    private String issuedByPassport;
    private String whenIssued;
    private String identificationNumber;
    private String addressRegistration;
    private String addressDelivery;
    private String numberPhoneClient;

    BasicContract basicContract;
    UpSaleContract upSaleContract;
    SupplementaryAgreementBasicContract supplementaryAgreementBasicContract;
    SupplementaryAgreementUpSaleContract supplementaryAgreementUpSaleContract;
    InvoiceDocument invoiceDocument;

    public DataClient(Map<String,String> mapDataClient) {
        this.numberContract = mapDataClient.get("numberContract").trim();
        this.strangeName = mapDataClient.get("strangeName").trim();
        fullNameClient = mapDataClient.get("fullNameClient").trim();
        setMiniNameClient(fullNameClient);
        numberPassport = mapDataClient.get("numberPassport");
        issuedByPassport = mapDataClient.get("issuedByPassport");
        whenIssued = mapDataClient.get("whenIssued");
        identificationNumber = mapDataClient.get("identificationNumber");
        addressRegistration = mapDataClient.get("addressRegistration");
        addressDelivery = mapDataClient.get("addressDelivery");
        numberPhoneClient = mapDataClient.get("numberPhoneClient");
        this.basicContract = new BasicContract();
        this.upSaleContract = new UpSaleContract();
        this.supplementaryAgreementBasicContract = new SupplementaryAgreementBasicContract();
        this.supplementaryAgreementUpSaleContract = new SupplementaryAgreementUpSaleContract();
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


    public void setDateAboutClient(Map<String,String> mapDataClient){
        this.numberContract = mapDataClient.get("numberContract").trim();
        this.strangeName = mapDataClient.get("strangeName").trim();
        fullNameClient = mapDataClient.get("fullNameClient").trim();
        setMiniNameClient(fullNameClient);
        numberPassport = mapDataClient.get("numberPassport");
        issuedByPassport = mapDataClient.get("issuedByPassport");
        whenIssued = mapDataClient.get("whenIssued");
        identificationNumber = mapDataClient.get("identificationNumber");
        addressRegistration = mapDataClient.get("addressRegistration");
        addressDelivery = mapDataClient.get("addressDelivery");
        numberPhoneClient = mapDataClient.get("numberPhoneClient");
    }

    public void setBaseContract(Map<String,String> map){
        this.basicContract = new BasicContract(map.get("dateCreateContract"),map.get("timeProduction"),
                Integer.parseInt(map.get("allSumInEUR")), Integer.parseInt(map.get("allSumInBYN")),
                Integer.parseInt(map.get("prepaymentOr10PercentSum")),Integer.parseInt(map.get("payUpTo50PercentSum")),
                Integer.parseInt(map.get("payUpTo100PercentSum")));
    }
    public void setUpSaleContract(Map<String,String> map){
        AdditionalProduct[] listAdditionalProducts = new AdditionalProduct[6];
        for (int i = 0; i < listAdditionalProducts.length; i++) {
            listAdditionalProducts[i] = new AdditionalProduct(
                    map.get("additionalProducts"+i),
                    map.get("additionalProductsCount"+i),
                    map.get("additionalProductsFullPrice"+i),
                    map.get("additionalProductsDiscount" + i),
                    map.get("additionalProductsWithDiscount"+i)
            );
        }
        this.upSaleContract = new UpSaleContract(map.get("dateCreateUpSaleContract"),listAdditionalProducts,
                Integer.parseInt(map.get("allSumUpSaleInBYN")),Integer.parseInt(map.get("prepaymentUpSale")),
                Integer.parseInt(map.get("payUpTo100percentUpSale")));
    }
    public void setDateSupplementaryAgreementBasicContract(Map<String,String> map) {
        this.supplementaryAgreementBasicContract = new SupplementaryAgreementBasicContract(
                map.get("dateCreateSupplementaryAgreementBasicContract"),Integer.parseInt(map.get("numberSupplementaryAgreementBasicContract")),
                Integer.parseInt(map.get("allSumInEURSupplementaryAgreement")),Integer.parseInt(map.get("allSumInBYNSupplementaryAgreement")),
                Integer.parseInt(map.get("prepaymentOr10PercentSumSupplementaryAgreement")),Integer.parseInt(map.get("payUpTo50PercentSumSupplementaryAgreement")),
                Integer.parseInt(map.get("payUpTo100PercentSumSupplementaryAgreement")));
    }
    public void setDateSupplementaryAgreementUpSaleContract(Map<String,String> map) {
        AdditionalProduct[] listAdditionalProductsSupplementaryAgreementUpSale = new AdditionalProduct[6];
        for (int i = 0; i < listAdditionalProductsSupplementaryAgreementUpSale.length; i++) {
            listAdditionalProductsSupplementaryAgreementUpSale[i] = new AdditionalProduct(
                    map.get("supplementaryAgreementAdditionalProducts"+i),
                    map.get("supplementaryAgreementAdditionalProductsCount"+i),
                    map.get("supplementaryAgreementAdditionalProductsFullPrice"+i),
                    map.get("supplementaryAgreementAdditionalProductsDiscount" + i),
                    map.get("supplementaryAgreementAdditionalProductsWithDiscount"+i)
            );
        }
        this.supplementaryAgreementUpSaleContract = new SupplementaryAgreementUpSaleContract(
                map.get("dateCreateSupplementaryAgreementUpSaleContract"),Integer.parseInt(map.get("numberSupplementaryAgreementUpSale")),
                listAdditionalProductsSupplementaryAgreementUpSale,Integer.parseInt(map.get("sumUpSaleInBYNSupplementaryAgreement")),
                Integer.parseInt(map.get("prepaymentUpSaleSupplementaryAgreement")),Integer.parseInt(map.get("payUpTo100percentUpSaleSupplementaryAgreement")));
    }
    public void setDataInvoiceDocument(HashMap<String,String> map){
        invoiceDocument = new InvoiceDocument(
                Integer.parseInt(map.get("priceBYNInvoiceDocument")),
                Integer.parseInt(map.get("priceInEURInvoiceDocument")),
                Double.parseDouble(map.get("vat20InvoiceDocument")),
                map.get("createDateInvoiceDocument"),
                map.get("whichBank"));

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
    public String getStrangeName() {
        return strangeName;
    }
    public BasicContract getBasicContract() {
        return basicContract;
    }
    public UpSaleContract getUpSaleContract() {
        return upSaleContract;
    }
    public SupplementaryAgreementBasicContract getSupplementaryAgreementBasicContract() {
        return supplementaryAgreementBasicContract;
    }
    public SupplementaryAgreementUpSaleContract getSupplementaryAgreementUpSaleContract() {
        return supplementaryAgreementUpSaleContract;
    }
    public InvoiceDocument getInvoiceDocument() {
        return invoiceDocument;
    }

    public void save() throws CantWriteDoc {
        StringBuilder data = new StringBuilder();
        data.append("numberContract/=/").append(numberContract).append("\n");
        data.append("strangeName/=/").append(strangeName).append("\n");
        data.append("fullNameClient/=/").append(fullNameClient).append("\n");
        data.append("miniNameClient/=/").append(miniNameClient).append("\n");
        data.append("numberPassport/=/").append(numberPassport).append("\n");
        data.append("issuedByPassport/=/").append(issuedByPassport).append("\n");
        data.append("whenIssued/=/").append(whenIssued).append("\n");
        data.append("identificationNumber/=/").append(identificationNumber).append("\n");
        data.append("addressRegistration/=/").append(addressRegistration).append("\n");
        data.append("addressDelivery/=/").append(addressDelivery).append("\n");
        data.append("numberPhoneClient/=/").append(numberPhoneClient).append("\n");
        data.append(basicContract.dataForSave());
        data.append(upSaleContract.dataForSave());
        data.append(supplementaryAgreementBasicContract.dataForSave());
        data.append(supplementaryAgreementUpSaleContract.dataForSave());
        data.append(invoiceDocument.dataForSave());
        String path ="saveContract/" +numberContract +" "+ strangeName;
        new File(path).mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(
                path  +"/baseDataClient.dat"))){
                writer.write(String.valueOf(data));
        } catch (Exception e) {
            throw new CantWriteDoc("Не смог сохранить данные в файл");
        }
    }
    public static DataClient load(String path){
        HashMap<String,String> map = new HashMap<>();
        String[] data = new String[0];
        try (BufferedReader reader = new BufferedReader(new FileReader(path))){
            while (reader.ready()){
                try {
                    data = reader.readLine().split("/=/");
                    map.put(data[0],data[1]);
                }catch (Exception e){
                    map.put(data[0],"");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataClient dataClient = new DataClient(map);
        dataClient.upSaleContract = UpSaleContract.load(map);
        dataClient.supplementaryAgreementUpSaleContract = SupplementaryAgreementUpSaleContract.load(map);
        dataClient.basicContract = BasicContract.load(map);
        dataClient.supplementaryAgreementBasicContract = SupplementaryAgreementBasicContract.load(map);
        dataClient.invoiceDocument = InvoiceDocument.load(map);
        return dataClient;
    }
}
