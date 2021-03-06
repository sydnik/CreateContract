package org.sydnik.createContract.data;

import org.sydnik.createContract.data.contract.*;
import org.sydnik.createContract.exception.CantWriteDoc;
import org.sydnik.createContract.view.document.ViewSupplementaryAgreementUpSale;
import org.sydnik.createContract.view.document.ViewUpSaleContract;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    private BasicContract basicContract;
    private UpSaleContract upSaleContract;
    private SupplementaryAgreementBasicContract supplementaryAgreementBasicContract;
    private SupplementaryAgreementUpSaleContract supplementaryAgreementUpSaleContract;
    private InvoiceDocument invoiceDocument;

    public DataClient(Map<String,String> mapDataClient) {
        numberContract = mapDataClient.get("numberContract").trim();
        strangeName = mapDataClient.get("strangeName").trim();
        fullNameClient = mapDataClient.get("fullNameClient").trim();
        setMiniNameClient(fullNameClient);
        numberPassport = mapDataClient.get("numberPassport");
        issuedByPassport = mapDataClient.get("issuedByPassport");
        whenIssued = mapDataClient.get("whenIssued");
        identificationNumber = mapDataClient.get("identificationNumber");
        addressRegistration = mapDataClient.get("addressRegistration");
        addressDelivery = mapDataClient.get("addressDelivery");
        numberPhoneClient = mapDataClient.get("numberPhoneClient");
        basicContract = new BasicContract();
        upSaleContract = new UpSaleContract();
        supplementaryAgreementBasicContract = new SupplementaryAgreementBasicContract();
        supplementaryAgreementUpSaleContract = new SupplementaryAgreementUpSaleContract();
        invoiceDocument = new InvoiceDocument();
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
        AdditionalProduct[] listAdditionalProducts = new AdditionalProduct[ViewUpSaleContract.ROW_ADDITIONAL_PRODUCT];
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
                map.get("dateCreateSupplementaryAgreementBasicContract"),SupplementaryAgreementBasicContract.TIME_PRODUCT[0],
                Integer.parseInt(map.get("allSumInEURSupplementaryAgreement")), Integer.parseInt(map.get("allSumInBYNSupplementaryAgreement")),
                Integer.parseInt(map.get("prepaymentOr10PercentSumSupplementaryAgreement")), Integer.parseInt(map.get("payUpTo50PercentSumSupplementaryAgreement")),
                Integer.parseInt(map.get("payUpTo100PercentSumSupplementaryAgreement")),Integer.parseInt(map.get("numberSupplementaryAgreementBasicContract")));
    }
    public void setDateSupplementaryAgreementUpSaleContract(Map<String,String> map) {
        AdditionalProduct[] listAdditionalProducts = new AdditionalProduct[ViewSupplementaryAgreementUpSale.ROW_ADDITIONAL_PRODUCT];
        for (int i = 0; i < listAdditionalProducts.length; i++) {
            listAdditionalProducts[i] = new AdditionalProduct(
                    map.get("supplementaryAgreementAdditionalProducts"+i),
                    map.get("supplementaryAgreementAdditionalProductsCount"+i),
                    map.get("supplementaryAgreementAdditionalProductsFullPrice"+i),
                    map.get("supplementaryAgreementAdditionalProductsDiscount" + i),
                    map.get("supplementaryAgreementAdditionalProductsWithDiscount"+i)
            );
        }
        this.supplementaryAgreementUpSaleContract = new SupplementaryAgreementUpSaleContract(map.get("dateCreateSupplementaryAgreementUpSaleContract"),
                listAdditionalProducts,
                Integer.parseInt(map.get("allSumUpSaleInBYNSupplementaryAgreement")),
                Integer.parseInt(map.get("prepaymentUpSaleSupplementaryAgreement")),
                Integer.parseInt(map.get("payUpTo100percentUpSaleSupplementaryAgreement")),
                Integer.parseInt(map.get("numberSupplementaryAgreementUpSale")));
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

    public boolean checkNameFreeForSave(SalesManager salesManager){
        String path ="saveContract/" +numberContract +" "+ strangeName;
        Path s = Paths.get(path);
        if(Files.isDirectory(s)){
            return false;
        }
        s = Paths.get(salesManager.getPathForSaveContract() + "\\" + getNumberContract() + " " + getStrangeName());
        if(Files.isDirectory(s)){
            return false;
        }
        return true;
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
        data.append(basicContract.getDataForSave());
        data.append(upSaleContract.getDataForSave());
        data.append(supplementaryAgreementBasicContract.getDataForSave());
        data.append(supplementaryAgreementUpSaleContract.getDataForSave());
        data.append(invoiceDocument.getDataForSave());
        String path ="saveContract/" +numberContract +" "+ strangeName;
        new File(path).mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(
                path  +"/baseDataClient.dat"))){
                writer.write(String.valueOf(data));
        } catch (Exception e) {
            throw new CantWriteDoc("???? ???????? ?????????????????? ???????????? ?? ????????");
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
