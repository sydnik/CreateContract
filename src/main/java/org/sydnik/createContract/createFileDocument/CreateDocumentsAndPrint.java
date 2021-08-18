package org.sydnik.createContract.createFileDocument;


import org.sydnik.createContract.NumberInWords;
import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.data.SalesManager;
import org.sydnik.createContract.exception.CantWriteDoc;
import org.sydnik.createContract.exception.DontHaveFilePattern;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

//Требудется разделить на два класса. Данный класс должен быть предназначек только для
public class CreateDocumentsAndPrint {
    public static void createBasicContract(DataClient dataClient, SalesManager salesManager) throws CantWriteDoc, DontHaveFilePattern {
        HashMap<String,String> map = new HashMap<>();
        try(ZipInputStream stream = new ZipInputStream(new FileInputStream("files/Базовый договор.docx"))) {
            ZipEntry entry = stream.getNextEntry();
            StringBuilder stringBuilder = new StringBuilder("");
            while (entry!=null) {
                stringBuilder = new StringBuilder("");
                Scanner sc = new Scanner(stream,"UTF-8");
                while (sc.hasNextLine()) {
                    stringBuilder.append(sc.nextLine());
                }
                map.put(entry.getName(),new String(stringBuilder));
                entry = stream.getNextEntry();
            }
        } catch (Exception e) {
            throw new DontHaveFilePattern("Не смог получить доступ к шаблону\n" +
                    "files/Базовый договор.docx");
        }
        String docWord = map.get("word/document.xml");

        //ВАжно чтобы первым выполнялась заполенение суммы прописью!!!
        docWord = docWord.replace("allSumInEURWord", NumberInWords.sumEURInWords(dataClient.getBasicContract().getAllSumInEUR()));
        docWord = docWord.replace("allSumInBYNWord", NumberInWords.sumBYNInWords(dataClient.getBasicContract().getAllSumInBYN()));
        docWord = docWord.replace("prepaymentOr10PercentSumWord", NumberInWords.sumEURInWords(dataClient.getBasicContract().getPrepaymentOr10PercentSum()));
        docWord = docWord.replace("payUpTo50PercentSumWord", NumberInWords.sumEURInWords(dataClient.getBasicContract().getPayUpTo50PercentSum()));
        docWord = docWord.replace("payUpTo100PercentSumWord", NumberInWords.sumEURInWords(dataClient.getBasicContract().getPayUpTo100PercentSum()));
        //Все остально не должно мешать работе
        docWord = docWord.replaceAll("numberContract", dataClient.getNumberContract());
        docWord = docWord.replaceAll("fullNameClient", dataClient.getFullNameClient());
        docWord = docWord.replaceAll("miniNameClient", dataClient.getMiniNameClient());
        docWord = docWord.replace("numberPassport", dataClient.getNumberPassport());
        docWord = docWord.replace("issuedByPassport", dataClient.getIssuedByPassport());
        docWord = docWord.replace("whenIssued", dataClient.getWhenIssued());
        docWord = docWord.replace("identificationNumber", dataClient.getIdentificationNumber());
        docWord = docWord.replace("addressRegistration", dataClient.getAddressRegistration());
        docWord = docWord.replace("addressDelivery", dataClient.getAddressDelivery());
        docWord = docWord.replace("numberPhoneClient", dataClient.getNumberPhoneClient());
        docWord = docWord.replaceAll("fullNameSalesManager", salesManager.getFullName());
        docWord = docWord.replace("numberPhoneManager", salesManager.getNumberPhoneManager());
        docWord = docWord.replace("numberPowerOfAttorney", salesManager.getNumberPowerOfAttorney());
        docWord = docWord.replace("datePowerOfAttorney", salesManager.getDatePowerOfAttorney());
        docWord = docWord.replaceAll("miniSalesManager", salesManager.getMiniName());
        docWord = docWord.replaceAll("dateCreateContract", dataClient.getBasicContract().getDateCreateContract());
        docWord = docWord.replace("timeProduction", dataClient.getBasicContract().getTimeProduction());
        docWord = docWord.replace("allSumInEUR", dataClient.getBasicContract().getAllSumInEUR()+".00");
        docWord = docWord.replace("allSumInBYN", dataClient.getBasicContract().getAllSumInBYN()+".00");
        docWord = docWord.replace("prepaymentOr10PercentSum", dataClient.getBasicContract().getPrepaymentOr10PercentSum()+".00");
        docWord = docWord.replace("payUpTo50PercentSum", dataClient.getBasicContract().getPayUpTo50PercentSum()+".00");
        docWord = docWord.replace("payUpTo100PercentSum", dataClient.getBasicContract().getPayUpTo100PercentSum()+".00");
        map.put("word/document.xml",docWord);
        String fileName = "saveContract/" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() + "/Договор"+dataClient.getNumberContract() + ".docx";
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            for (Map.Entry<String,String> s : map.entrySet()) {
                byte[] b = s.getValue().getBytes(StandardCharsets.UTF_8);
                ZipEntry zipEntry = new ZipEntry(s.getKey());
                zipOut.putNextEntry(zipEntry);
                zipOut.write(b);
            }
            zipOut.close();
            fos.close();
        }catch (Exception e){
            throw new CantWriteDoc("Не смог записать \n"+fileName);

        }
        if(!salesManager.getPathForSaveContract().equals("")) {
            try {
                new File(salesManager.getPathForSaveContract() + "\\" + dataClient.getNumberContract() + " " + dataClient.getStrangeName()).mkdirs();
                FileOutputStream fos = new FileOutputStream(salesManager.getPathForSaveContract() + "\\" + dataClient.getNumberContract() + " " +
                        dataClient.getStrangeName() + "/Договор" + dataClient.getNumberContract() + ".docx");
                ZipOutputStream zipOut = new ZipOutputStream(fos);
                for (Map.Entry<String, String> s : map.entrySet()) {
                    byte[] b = s.getValue().getBytes(StandardCharsets.UTF_8);
                    ZipEntry zipEntry = new ZipEntry(s.getKey());
                    zipOut.putNextEntry(zipEntry);
                    zipOut.write(b);
                }
                zipOut.close();
                fos.close();
            } catch (Exception e) {
                throw new CantWriteDoc("Не смог записать в папку которую вы указали в настройках,проверьте доступ \n"+salesManager.getPathForSaveContract());
            }
        }


    }
    public static void createUpSaleContract(DataClient dataClient,SalesManager salesManager) throws CantWriteDoc, DontHaveFilePattern {
        String listLine[];
        HashMap<String,String> map = new HashMap<>();
        try(ZipInputStream stream = new ZipInputStream(new FileInputStream("files/Договор UpSale.docx"))) {
            ZipEntry entry = stream.getNextEntry();
            StringBuilder stringBuilder = new StringBuilder("");
            while (entry!=null) {
                stringBuilder = new StringBuilder("");
                Scanner sc = new Scanner(stream,"UTF-8");
                while (sc.hasNextLine()) {
                    stringBuilder.append(sc.nextLine());
                }
                map.put(entry.getName(),new String(stringBuilder));
                entry = stream.getNextEntry();
            }
        } catch (Exception e) {
            throw new DontHaveFilePattern("Не смог получить доступ к шаблону\n" +
                    "files/Договор UpSale.docx");
        }
        String docWord = map.get("word/document.xml");
        //ВАжно чтобы первым выполнялась заполенение суммы прописью!!!
        docWord = docWord.replace("allSumUpSaleInBYNWord", NumberInWords.sumBYNInWords(dataClient.getUpSaleContract().getAllSumUpSaleInBYN()));
        docWord = docWord.replace("prepaymentUpSaleWord", NumberInWords.sumBYNInWords(dataClient.getUpSaleContract().getPrepaymentUpSale()));
        docWord = docWord.replace("payUpTo100percentUpSaleWord", NumberInWords.sumBYNInWords(dataClient.getUpSaleContract().getPayUpTo100percentUpSale()));
        //Все остально не должно мешать работе
        docWord = docWord.replace("numberContract", dataClient.getNumberContract());
        docWord = docWord.replaceAll("fullNameClient", dataClient.getFullNameClient());
        docWord = docWord.replace("miniNameClient", dataClient.getMiniNameClient());
        docWord = docWord.replace("numberPassport", dataClient.getNumberPassport());
        docWord = docWord.replace("issuedByPassport", dataClient.getIssuedByPassport());
        docWord = docWord.replace("whenIssued", dataClient.getWhenIssued());
        docWord = docWord.replace("identificationNumber", dataClient.getIdentificationNumber());
        docWord = docWord.replace("addressRegistration", dataClient.getAddressRegistration());
        docWord = docWord.replace("addressDelivery", dataClient.getAddressDelivery());
        docWord = docWord.replace("numberPhoneClient", dataClient.getNumberPhoneClient());
        docWord = docWord.replace("fullNameSalesManager", salesManager.getFullName());
        docWord = docWord.replace("numberPowerOfAttorney", salesManager.getNumberPowerOfAttorney());
        docWord = docWord.replace("datePowerOfAttorney", salesManager.getDatePowerOfAttorney());
        docWord = docWord.replace("miniSalesManager", salesManager.getMiniName());
        docWord = docWord.replace("numberPhoneManager", salesManager.getNumberPhoneManager());
        docWord = docWord.replace("dateCreateUpSaleContract", dataClient.getUpSaleContract().getDateCreateUpSaleContract());
        docWord = docWord.replaceAll("allSumUpSaleInBYN", dataClient.getUpSaleContract().getAllSumUpSaleInBYN()+".00");
        docWord = docWord.replace("prepaymentUpSale", dataClient.getUpSaleContract().getPrepaymentUpSale()+".00");
        docWord = docWord.replace("payUpTo100percentUpSale", dataClient.getUpSaleContract().getPayUpTo100percentUpSale()+".00");

        for (int i = 0; i < 6; i++) {
            if(!dataClient.getUpSaleContract().getListAdditionalProducts()[i].getName().equals("")) {
                docWord = docWord.replace("additionalProducts" + i, dataClient.getUpSaleContract().getListAdditionalProducts()[i].getName());
                docWord = docWord.replace("additionalProductsCount" + i, String.valueOf(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getCount()));
                docWord = docWord.replace("additionalProductsWithDiscount" + i, dataClient.getUpSaleContract().getListAdditionalProducts()[i].getPriceWithDiscount() + ".00");
            }
            else {
                listLine = docWord.split("additionalProductsWithDiscount"+i);
                listLine[0] = listLine[0].substring(0,listLine[0].lastIndexOf("<w:tr w"));
                for (int j  = i; j < 6 ; j++) {
                    listLine[1] = listLine[1].substring(listLine[1].indexOf("</w:tr>")+7);
                }
                docWord = listLine[0]+listLine[1];
                break;
            }
        }

        map.put("word/document.xml",docWord);
        String fileName = "saveContract/" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() + "/ДоговорUpSale"+dataClient.getNumberContract() + ".docx";
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            for (Map.Entry<String,String> s : map.entrySet()) {
                byte[] b = s.getValue().getBytes(StandardCharsets.UTF_8);
                ZipEntry zipEntry = new ZipEntry(s.getKey());
                zipOut.putNextEntry(zipEntry);
                zipOut.write(b);
            }
            zipOut.close();
            fos.close();
        }catch (Exception e){
            throw new CantWriteDoc("Не смог записать \n"+fileName);

        }
        if(!salesManager.getPathForSaveContract().equals("")) {
            try {
                new File(salesManager.getPathForSaveContract() + "\\" + dataClient.getNumberContract() + " " + dataClient.getStrangeName()).mkdirs();
                FileOutputStream fos = new FileOutputStream(salesManager.getPathForSaveContract() + "\\" + dataClient.getNumberContract() + " " +
                        dataClient.getStrangeName() + "/ДоговорUpSale" + dataClient.getNumberContract() + ".docx");
                ZipOutputStream zipOut = new ZipOutputStream(fos);
                for (Map.Entry<String, String> s : map.entrySet()) {
                    byte[] b = s.getValue().getBytes(StandardCharsets.UTF_8);
                    ZipEntry zipEntry = new ZipEntry(s.getKey());
                    zipOut.putNextEntry(zipEntry);
                    zipOut.write(b);
                }
                zipOut.close();
                fos.close();
            } catch (Exception e) {
                throw new CantWriteDoc("Не смог записать в папку которую вы указали в настройках,проверьте доступ \n"+salesManager.getPathForSaveContract());
            }
        }


    }
    public static void createSupplementaryAgreementBasicContract(DataClient dataClient, SalesManager salesManager) throws CantWriteDoc, DontHaveFilePattern {HashMap<String,String> map = new HashMap<>();
        try(ZipInputStream stream = new ZipInputStream(new FileInputStream("files/Дополнительное соглашение БД.docx"))) {
            ZipEntry entry = stream.getNextEntry();
            StringBuilder stringBuilder = new StringBuilder("");
            while (entry!=null) {
                stringBuilder = new StringBuilder("");
                Scanner sc = new Scanner(stream,"UTF-8");
                while (sc.hasNextLine()) {
                    stringBuilder.append(sc.nextLine());
                }
                map.put(entry.getName(),new String(stringBuilder));
                entry = stream.getNextEntry();
            }
        } catch (Exception e) {
            throw new DontHaveFilePattern("Не смог получить доступ к шаблону\n" +
                    "files/Дополнительное соглашение БД.docx");
        }
        String docWord = map.get("word/document.xml");
        //ВАжно чтобы первым выполнялась заполенение суммы прописью!!!
        docWord = docWord.replace("allSumInEURSupplementaryAgreementWord", NumberInWords.sumEURInWords(dataClient.getSupplementaryAgreementBasicContract().getAllSumInEURSupplementaryAgreement()));
        docWord = docWord.replace("allSumInBYNSupplementaryAgreementWord", NumberInWords.sumBYNInWords(dataClient.getSupplementaryAgreementBasicContract().getAllSumInBYNSupplementaryAgreement()));
        docWord = docWord.replace("prepaymentOr10PercentSumSupplementaryAgreementWord", NumberInWords.sumEURInWords(dataClient.getSupplementaryAgreementBasicContract().getPrepaymentOr10PercentSumSupplementaryAgreement()));
        docWord = docWord.replace("payUpTo50PercentSumSupplementaryAgreementWord", NumberInWords.sumEURInWords(dataClient.getSupplementaryAgreementBasicContract().getPayUpTo50PercentSumSupplementaryAgreement()));
        docWord = docWord.replace("payUpTo100PercentSumSupplementaryAgreementWord", NumberInWords.sumEURInWords(dataClient.getSupplementaryAgreementBasicContract().getPayUpTo100PercentSumSupplementaryAgreement()));
        //Все остально не должно мешать работе
        docWord = docWord.replaceAll("numberContract", dataClient.getNumberContract());
        docWord = docWord.replaceAll("fullNameClient", dataClient.getFullNameClient());
        docWord = docWord.replaceAll("miniNameClient", dataClient.getMiniNameClient());
        docWord = docWord.replace("numberPassport", dataClient.getNumberPassport());
        docWord = docWord.replace("issuedByPassport", dataClient.getIssuedByPassport());
        docWord = docWord.replace("whenIssued", dataClient.getWhenIssued());
        docWord = docWord.replace("identificationNumber", dataClient.getIdentificationNumber());
        docWord = docWord.replace("addressRegistration", dataClient.getAddressRegistration());
        docWord = docWord.replace("addressDelivery", dataClient.getAddressDelivery());
        docWord = docWord.replace("numberPhoneClient", dataClient.getNumberPhoneClient());
        docWord = docWord.replace("fullNameSalesManager", salesManager.getFullName());
        docWord = docWord.replace("numberPowerOfAttorney", salesManager.getNumberPowerOfAttorney());
        docWord = docWord.replace("datePowerOfAttorney", salesManager.getDatePowerOfAttorney());
        docWord = docWord.replaceAll("miniSalesManager", salesManager.getMiniName());
        docWord = docWord.replaceAll("dateCreateContract", dataClient.getBasicContract().getDateCreateContract());
        docWord = docWord.replace("allSumInEURSupplementaryAgreement", dataClient.getSupplementaryAgreementBasicContract().getAllSumInEURSupplementaryAgreement()+".00");
        docWord = docWord.replace("allSumInBYNSupplementaryAgreement", dataClient.getSupplementaryAgreementBasicContract().getAllSumInBYNSupplementaryAgreement()+".00");
        docWord = docWord.replace("prepaymentOr10PercentSumSupplementaryAgreement", dataClient.getSupplementaryAgreementBasicContract().getPrepaymentOr10PercentSumSupplementaryAgreement()+".00");
        docWord = docWord.replace("payUpTo50PercentSumSupplementaryAgreement", dataClient.getSupplementaryAgreementBasicContract().getPayUpTo50PercentSumSupplementaryAgreement()+".00");
        docWord = docWord.replace("payUpTo100PercentSumSupplementaryAgreement", dataClient.getSupplementaryAgreementBasicContract().getPayUpTo100PercentSumSupplementaryAgreement()+".00");
        docWord = docWord.replace("dateCreateSupplementaryAgreementBasicContract", dataClient.getSupplementaryAgreementBasicContract().getDateCreateSupplementaryAgreementBasicContract());
        docWord = docWord.replace("numberSupplementaryAgreementBasicContract", String.valueOf(dataClient.getSupplementaryAgreementBasicContract().getNumberSupplementaryAgreementBasicContract()));
        docWord = docWord.replace("numberPhoneManager", salesManager.getNumberPhoneManager());
        map.put("word/document.xml",docWord);
        String fileName = "saveContract/" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() +
                "/Дополнительное соглашение №"+dataClient.getSupplementaryAgreementBasicContract().getNumberSupplementaryAgreementBasicContract()+" " +
                dataClient.getNumberContract() + ".docx";
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            for (Map.Entry<String,String> s : map.entrySet()) {
                byte[] b = s.getValue().getBytes(StandardCharsets.UTF_8);
                ZipEntry zipEntry = new ZipEntry(s.getKey());
                zipOut.putNextEntry(zipEntry);
                zipOut.write(b);
            }
            zipOut.close();
            fos.close();
        }catch (Exception e){
            throw new CantWriteDoc("Не смог записать \n"+fileName);
        }
        if(!salesManager.getPathForSaveContract().equals("")) {
            try {
                new File(salesManager.getPathForSaveContract() + "\\" + dataClient.getNumberContract() + " " + dataClient.getStrangeName()).mkdirs();
                FileOutputStream fos = new FileOutputStream(salesManager.getPathForSaveContract() + "\\" + dataClient.getNumberContract() + " " + dataClient.getStrangeName() +
                        "/Дополнительное соглашение №" + dataClient.getSupplementaryAgreementBasicContract().getNumberSupplementaryAgreementBasicContract() + " "
                        + dataClient.getNumberContract() + ".docx");
                ZipOutputStream zipOut = new ZipOutputStream(fos);
                for (Map.Entry<String, String> s : map.entrySet()) {
                    byte[] b = s.getValue().getBytes(StandardCharsets.UTF_8);
                    ZipEntry zipEntry = new ZipEntry(s.getKey());
                    zipOut.putNextEntry(zipEntry);
                    zipOut.write(b);
                }
                zipOut.close();
                fos.close();
            } catch (Exception e) {
                throw new CantWriteDoc("Не смог записать в папку которую вы указали в настройках,проверьте доступ \n"+salesManager.getPathForSaveContract());
            }
        }

    }
    public static void createSupplementaryAgreementUpSaleContract(DataClient dataClient, SalesManager salesManager) throws CantWriteDoc, DontHaveFilePattern {
        String listLine[];
        HashMap<String,String> map = new HashMap<>();
        try(ZipInputStream stream = new ZipInputStream(new FileInputStream("files/Дополнительно соглашение UpSale.docx"))) {
            ZipEntry entry = stream.getNextEntry();
            StringBuilder stringBuilder = new StringBuilder("");
            while (entry!=null) {
                stringBuilder = new StringBuilder("");
                Scanner sc = new Scanner(stream,"UTF-8");
                while (sc.hasNextLine()) {
                    stringBuilder.append(sc.nextLine());
                }
                map.put(entry.getName(),new String(stringBuilder));
                entry = stream.getNextEntry();
            }
        } catch (Exception e) {
            throw new DontHaveFilePattern("Не смог получить доступ к шаблону\n" +
                    "files/Дополнительно соглашение UpSale.docx");
        }
        String docWord = map.get("word/document.xml");
        //ВАжно чтобы первым выполнялась заполенение суммы прописью!!!
        docWord = docWord.replace("sumUpSaleInBYNSupplementaryAgreementWord", NumberInWords.sumBYNInWords(dataClient.getSupplementaryAgreementUpSaleContract().getAllSumUpSaleInBYNSupplementaryAgreement()));
        docWord = docWord.replace("prepaymentUpSaleSupplementaryAgreementWord", NumberInWords.sumBYNInWords(dataClient.getSupplementaryAgreementUpSaleContract().getPrepaymentUpSaleSupplementaryAgreement()));
        docWord = docWord.replace("payUpTo100PercentSupplementaryAgreementUpSaleWord", NumberInWords.sumBYNInWords(dataClient.getSupplementaryAgreementUpSaleContract().getPayUpTo100percentUpSaleSupplementaryAgreement()));
        //Все остально не должно мешать работе
        docWord = docWord.replace("numberContract", dataClient.getNumberContract());
        docWord = docWord.replaceAll("fullNameClient", dataClient.getFullNameClient());
        docWord = docWord.replace("miniNameClient", dataClient.getMiniNameClient());
        docWord = docWord.replace("numberPassport", dataClient.getNumberPassport());
        docWord = docWord.replace("issuedByPassport", dataClient.getIssuedByPassport());
        docWord = docWord.replace("whenIssued", dataClient.getWhenIssued());
        docWord = docWord.replace("identificationNumber", dataClient.getIdentificationNumber());
        docWord = docWord.replace("addressRegistration", dataClient.getAddressRegistration());
        docWord = docWord.replace("addressDelivery", dataClient.getAddressDelivery());
        docWord = docWord.replace("numberPhoneClient", dataClient.getNumberPhoneClient());
        docWord = docWord.replace("fullNameSalesManager", salesManager.getFullName());
        docWord = docWord.replace("numberPowerOfAttorney", salesManager.getNumberPowerOfAttorney());
        docWord = docWord.replace("datePowerOfAttorney", salesManager.getDatePowerOfAttorney());
        docWord = docWord.replace("miniSalesManager", salesManager.getMiniName());
        docWord = docWord.replace("numberPhoneManager", salesManager.getNumberPhoneManager());
        docWord = docWord.replace("dateCreateSupplementaryAgreementUpSaleContract", dataClient.getSupplementaryAgreementUpSaleContract().getDateCreateSupplementaryAgreementUpSaleContract());
        docWord = docWord.replace("dateCreateUpSaleContract", dataClient.getUpSaleContract().getDateCreateUpSaleContract());
        docWord = docWord.replace("numberSupplementaryAgreementUpSale", String.valueOf(dataClient.getSupplementaryAgreementUpSaleContract().getNumberSupplementaryAgreementUpSale()));
        docWord = docWord.replaceAll("sumUpSaleInBYNSupplementaryAgreement", dataClient.getSupplementaryAgreementUpSaleContract().getAllSumUpSaleInBYNSupplementaryAgreement()+".00");
        docWord = docWord.replace("prepaymentUpSaleSupplementaryAgreement", dataClient.getSupplementaryAgreementUpSaleContract().getPrepaymentUpSaleSupplementaryAgreement()+".00");
        docWord = docWord.replace("payUpTo100PercentSupplementaryAgreementUpSale", dataClient.getSupplementaryAgreementUpSaleContract().getPayUpTo100percentUpSaleSupplementaryAgreement()+".00");

        for (int i = 0; i < 6; i++) {
            if(!dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProductsSupplementaryAgreementUpSale()[i].getName().equals("")) {
                docWord = docWord.replace("supplementaryAgreementAdditionalProducts" + i, dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProductsSupplementaryAgreementUpSale()[i].getName());
                docWord = docWord.replace("supplementaryAgreementAdditionalProductsCount" + i, String.valueOf(dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProductsSupplementaryAgreementUpSale()[i].getCount()));
                docWord = docWord.replace("supplementaryAgreementAdditionalProductsWithDiscount" + i, dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProductsSupplementaryAgreementUpSale()[i].getPriceWithDiscount() + ".00");
            }
            else {
                listLine = docWord.split("supplementaryAgreementAdditionalProductsWithDiscount"+i);
                listLine[0] = listLine[0].substring(0,listLine[0].lastIndexOf("<w:tr w"));
                for (int j  = i; j < 6 ; j++) {
                    listLine[1] = listLine[1].substring(listLine[1].indexOf("</w:tr>")+7);
                }
                docWord = listLine[0]+listLine[1];
                break;
            }
        }

        map.put("word/document.xml",docWord);
        String fileName = "saveContract/" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() + "/Дополнительное соглашение UpSale"+
                dataClient.getSupplementaryAgreementUpSaleContract().getNumberSupplementaryAgreementUpSale()+" "+dataClient.getNumberContract() + ".docx";
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            for (Map.Entry<String,String> s : map.entrySet()) {
                byte[] b = s.getValue().getBytes(StandardCharsets.UTF_8);
                ZipEntry zipEntry = new ZipEntry(s.getKey());
                zipOut.putNextEntry(zipEntry);
                zipOut.write(b);
            }
            zipOut.close();
            fos.close();
        }catch (Exception e){
            throw new CantWriteDoc("Не смог записать \n"+fileName);
        }
        if(!salesManager.getPathForSaveContract().equals("")) {
            try {
                new File(salesManager.getPathForSaveContract() + "\\" + dataClient.getNumberContract() + " " + dataClient.getStrangeName()).mkdirs();
                FileOutputStream fos = new FileOutputStream(salesManager.getPathForSaveContract() + "\\" + dataClient.getNumberContract() + " " + dataClient.getStrangeName() + "/Дополнительное соглашение UpSale" +
                        dataClient.getSupplementaryAgreementUpSaleContract().getNumberSupplementaryAgreementUpSale() + " " + dataClient.getNumberContract() + ".docx");
                ZipOutputStream zipOut = new ZipOutputStream(fos);
                for (Map.Entry<String, String> s : map.entrySet()) {
                    byte[] b = s.getValue().getBytes(StandardCharsets.UTF_8);
                    ZipEntry zipEntry = new ZipEntry(s.getKey());
                    zipOut.putNextEntry(zipEntry);
                    zipOut.write(b);
                }
                zipOut.close();
                fos.close();
            } catch (Exception e) {
                throw new CantWriteDoc("Не смог записать в папку которую вы указали в настройках,проверьте доступ \n"+salesManager.getPathForSaveContract());
            }
        }


    }

    public static void printDoc(DataClient dataClient, String howDoc,SalesManager salesManager){
        String path = salesManager.getPathForSaveContract();
        if(path.equals("")){
            path="saveContract\\";
        }
        switch (howDoc){
            case "printBaseContract" :{
                try {
                    Desktop.getDesktop().print(new File(path + "\\" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() + "/Договор"+dataClient.getNumberContract() + ".docx"));
                    Thread.sleep(2000);// Возникает ошибка, ворд не успевает закрыться а уже открывается второй, добавил задержку в 1 секунду
                    Desktop.getDesktop().print(new File(path + "\\" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() + "/Договор"+dataClient.getNumberContract() + ".docx"));
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            }
            case "printUpSaleContract" : {
                try {
                    Desktop.getDesktop().print(new File(path + "\\" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() + "/ДоговорUpSale"+dataClient.getNumberContract() + ".docx"));
                    Thread.sleep(2000);// Возникает ошибка, ворд не успевает закрыться а уже открывается второй, добавил задержку в 1 секунду
                    Desktop.getDesktop().print(new File(path + "\\" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() + "/ДоговорUpSale"+dataClient.getNumberContract() + ".docx"));
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            }
            case "printSupplementaryAgreementBasicContract" : {
                try {
                    Desktop.getDesktop().print(new File(path + "\\" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() +
                            "/Дополнительное соглашение №"+ dataClient.getSupplementaryAgreementBasicContract().getNumberSupplementaryAgreementBasicContract()+" " +dataClient.getNumberContract() + ".docx"));
                    Thread.sleep(2000);// Возникает ошибка, ворд не успевает закрыться а уже открывается второй, добавил задержку в 1 секунду Дополнительное соглашение №15 DA2-313123-21
                    Desktop.getDesktop().print(new File(path + "\\" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() +
                            "/Дополнительное соглашение №"+ dataClient.getSupplementaryAgreementBasicContract().getNumberSupplementaryAgreementBasicContract()+" " +dataClient.getNumberContract() + ".docx"));
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "printSupplementaryAgreementUpSaleContract" : {
                try {
                    Desktop.getDesktop().print(new File(path + "\\" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() + "/Дополнительное соглашение UpSale"+
                            dataClient.getSupplementaryAgreementUpSaleContract().getNumberSupplementaryAgreementUpSale()+" "+dataClient.getNumberContract() + ".docx"));
                    Thread.sleep(2000);// Возникает ошибка, ворд не успевает закрыться а уже открывается второй, добавил задержку в 1 секунду Дополнительное соглашение №15 DA2-313123-21
                    Desktop.getDesktop().print(new File(path + "\\" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() + "/Дополнительное соглашение UpSale"+
                            dataClient.getSupplementaryAgreementUpSaleContract().getNumberSupplementaryAgreementUpSale()+" "+dataClient.getNumberContract() + ".docx"));
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "printInvoiceDocument" : {
                try {
                    Desktop.getDesktop().print(new File(path + "/Счет-фактура " + dataClient.getNumberContract()+ ".xlsx"));
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }

        }

    }
}
