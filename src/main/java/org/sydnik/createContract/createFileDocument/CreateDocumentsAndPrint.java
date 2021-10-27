package org.sydnik.createContract.createFileDocument;


import org.sydnik.createContract.NumberInWords;
import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.data.SalesManager;
import org.sydnik.createContract.exception.CantWriteDoc;
import org.sydnik.createContract.exception.DontHaveFilePattern;
import org.sydnik.createContract.view.document.ViewSupplementaryAgreementUpSale;
import org.sydnik.createContract.view.document.ViewUpSaleContract;

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
        docWord = docWord.replace("prepaymentOr10PercentSumWord", NumberInWords.sumEURInWords(dataClient.getBasicContract().getPrepayment()));
        docWord = docWord.replace("payUpTo50PercentSumWord", NumberInWords.sumEURInWords(dataClient.getBasicContract().getPayUpTo50Percent()));
        docWord = docWord.replace("payUpTo100PercentSumWord", NumberInWords.sumEURInWords(dataClient.getBasicContract().getPayUpTo100Percent()));
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
        docWord = docWord.replaceAll("dateCreateContract", dataClient.getBasicContract().getDateCreate());
        docWord = docWord.replace("timeProduction", dataClient.getBasicContract().getTimeProduction());
        docWord = docWord.replace("allSumInEUR", dataClient.getBasicContract().getAllSumInEUR()+".00");
        docWord = docWord.replace("allSumInBYN", dataClient.getBasicContract().getAllSumInBYN()+".00");
        docWord = docWord.replace("prepaymentOr10PercentSum", dataClient.getBasicContract().getPrepayment()+".00");
        docWord = docWord.replace("payUpTo50PercentSum", dataClient.getBasicContract().getPayUpTo50Percent()+".00");
        docWord = docWord.replace("payUpTo100PercentSum", dataClient.getBasicContract().getPayUpTo100Percent()+".00");
        map.put("word/document.xml",docWord);
        String fileName = "saveContract/" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() + "/Договор"+dataClient.getNumberContract() + ".docx";
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
        else {
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
        docWord = docWord.replace("allSumUpSaleInBYNWord", NumberInWords.sumBYNInWords(dataClient.getUpSaleContract().getAllSumBYN()));
        docWord = docWord.replace("prepaymentUpSaleWord", NumberInWords.sumBYNInWords(dataClient.getUpSaleContract().getPrepayment()));
        docWord = docWord.replace("payUpTo100percentUpSaleWord", NumberInWords.sumBYNInWords(dataClient.getUpSaleContract().getPayUpTo100percent()));
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
        docWord = docWord.replace("dateCreateUpSaleContract", dataClient.getUpSaleContract().getDateCreate());
        docWord = docWord.replaceAll("allSumUpSaleInBYN", dataClient.getUpSaleContract().getAllSumBYN()+".00");
        docWord = docWord.replace("prepaymentUpSale", dataClient.getUpSaleContract().getPrepayment()+".00");
        docWord = docWord.replace("payUpTo100percentUpSale", dataClient.getUpSaleContract().getPayUpTo100percent()+".00");

        for (int i = 0; i < ViewUpSaleContract.ROW_ADDITIONAL_PRODUCT; i++) {
            if(!dataClient.getUpSaleContract().getListAdditionalProducts()[i].getName().equals("")) {
                docWord = docWord.replace("additionalProducts" + i, dataClient.getUpSaleContract().getListAdditionalProducts()[i].getName());
                docWord = docWord.replace("additionalProductsCount" + i, String.valueOf(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getCount()));
                docWord = docWord.replace("additionalProductsWithDiscount" + i, dataClient.getUpSaleContract().getListAdditionalProducts()[i].getPriceWithDiscount() + ".00");
            }
            else {
                listLine = docWord.split("additionalProductsWithDiscount"+i);
                listLine[0] = listLine[0].substring(0,listLine[0].lastIndexOf("<w:tr w"));
                for (int j = i; j < ViewUpSaleContract.ROW_ADDITIONAL_PRODUCT; j++) {
                    listLine[1] = listLine[1].substring(listLine[1].indexOf("</w:tr>")+7);
                }
                docWord = listLine[0]+listLine[1];
                break;
            }
        }

        map.put("word/document.xml",docWord);
        String fileName = "saveContract/" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() + "/ДоговорUpSale"+dataClient.getNumberContract() + ".docx";
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
        else {
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
        docWord = docWord.replace("allSumInEURSupplementaryAgreementWord", NumberInWords.sumEURInWords(dataClient.getSupplementaryAgreementBasicContract().getAllSumInEUR()));
        docWord = docWord.replace("allSumInBYNSupplementaryAgreementWord", NumberInWords.sumBYNInWords(dataClient.getSupplementaryAgreementBasicContract().getAllSumInBYN()));
        docWord = docWord.replace("prepaymentOr10PercentSumSupplementaryAgreementWord", NumberInWords.sumEURInWords(dataClient.getSupplementaryAgreementBasicContract().getPrepayment()));
        docWord = docWord.replace("payUpTo50PercentSumSupplementaryAgreementWord", NumberInWords.sumEURInWords(dataClient.getSupplementaryAgreementBasicContract().getPayUpTo50Percent()));
        docWord = docWord.replace("payUpTo100PercentSumSupplementaryAgreementWord", NumberInWords.sumEURInWords(dataClient.getSupplementaryAgreementBasicContract().getPayUpTo100Percent()));
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
        docWord = docWord.replaceAll("dateCreateContract", dataClient.getBasicContract().getDateCreate());
        docWord = docWord.replace("allSumInEURSupplementaryAgreement", dataClient.getSupplementaryAgreementBasicContract().getAllSumInEUR()+".00");
        docWord = docWord.replace("allSumInBYNSupplementaryAgreement", dataClient.getSupplementaryAgreementBasicContract().getAllSumInBYN()+".00");
        docWord = docWord.replace("prepaymentOr10PercentSumSupplementaryAgreement", dataClient.getSupplementaryAgreementBasicContract().getPrepayment()+".00");
        docWord = docWord.replace("payUpTo50PercentSumSupplementaryAgreement", dataClient.getSupplementaryAgreementBasicContract().getPayUpTo50Percent()+".00");
        docWord = docWord.replace("payUpTo100PercentSumSupplementaryAgreement", dataClient.getSupplementaryAgreementBasicContract().getPayUpTo100Percent()+".00");
        docWord = docWord.replace("dateCreateSupplementaryAgreementBasicContract", dataClient.getSupplementaryAgreementBasicContract().getDateCreate());
        docWord = docWord.replace("numberSupplementaryAgreementBasicContract", String.valueOf(dataClient.getSupplementaryAgreementBasicContract().getNumber()));
        docWord = docWord.replace("numberPhoneManager", salesManager.getNumberPhoneManager());
        map.put("word/document.xml",docWord);
        String fileName = "saveContract/" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() +
                "/Дополнительное соглашение №"+dataClient.getSupplementaryAgreementBasicContract().getNumber()+" " +
                dataClient.getNumberContract() + ".docx";
        if(!salesManager.getPathForSaveContract().equals("")) {
            try {
                new File(salesManager.getPathForSaveContract() + "\\" + dataClient.getNumberContract() + " " + dataClient.getStrangeName()).mkdirs();
                FileOutputStream fos = new FileOutputStream(salesManager.getPathForSaveContract() + "\\" + dataClient.getNumberContract() + " " + dataClient.getStrangeName() +
                        "/Дополнительное соглашение №" + dataClient.getSupplementaryAgreementBasicContract().getNumber() + " "
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
        }else {
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
        docWord = docWord.replace("sumUpSaleInBYNSupplementaryAgreementWord", NumberInWords.sumBYNInWords(dataClient.getSupplementaryAgreementUpSaleContract().getAllSumBYN()));
        docWord = docWord.replace("prepaymentUpSaleSupplementaryAgreementWord", NumberInWords.sumBYNInWords(dataClient.getSupplementaryAgreementUpSaleContract().getPrepayment()));
        docWord = docWord.replace("payUpTo100PercentSupplementaryAgreementUpSaleWord", NumberInWords.sumBYNInWords(dataClient.getSupplementaryAgreementUpSaleContract().getPayUpTo100percent()));
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
        docWord = docWord.replace("dateCreateSupplementaryAgreementUpSaleContract", dataClient.getSupplementaryAgreementUpSaleContract().getDateCreate());
        docWord = docWord.replace("dateCreateUpSaleContract", dataClient.getUpSaleContract().getDateCreate());
        docWord = docWord.replace("numberSupplementaryAgreementUpSale", String.valueOf(dataClient.getSupplementaryAgreementUpSaleContract().getNumber()));
        docWord = docWord.replaceAll("sumUpSaleInBYNSupplementaryAgreement", dataClient.getSupplementaryAgreementUpSaleContract().getAllSumBYN()+".00");
        docWord = docWord.replace("prepaymentUpSaleSupplementaryAgreement", dataClient.getSupplementaryAgreementUpSaleContract().getPrepayment()+".00");
        docWord = docWord.replace("payUpTo100PercentSupplementaryAgreementUpSale", dataClient.getSupplementaryAgreementUpSaleContract().getPayUpTo100percent()+".00");

        for (int i = 0; i < ViewSupplementaryAgreementUpSale.ROW_ADDITIONAL_PRODUCT; i++) {
            if(!dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProducts()[i].getName().equals("")) {
                docWord = docWord.replace("supplementaryAgreementAdditionalProducts" + i, dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProducts()[i].getName());
                docWord = docWord.replace("supplementaryAgreementAdditionalProductsCount" + i, String.valueOf(dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProducts()[i].getCount()));
                docWord = docWord.replace("supplementaryAgreementAdditionalProductsWithDiscount" + i, dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProducts()[i].getPriceWithDiscount() + ".00");
            }
            else {
                listLine = docWord.split("supplementaryAgreementAdditionalProductsWithDiscount"+i);
                listLine[0] = listLine[0].substring(0,listLine[0].lastIndexOf("<w:tr w"));
                for (int j = i; j < ViewSupplementaryAgreementUpSale.ROW_ADDITIONAL_PRODUCT; j++) {
                    listLine[1] = listLine[1].substring(listLine[1].indexOf("</w:tr>")+7);
                }
                docWord = listLine[0]+listLine[1];
                break;
            }
        }

        map.put("word/document.xml",docWord);
        String fileName = "saveContract/" + dataClient.getNumberContract() + " " +dataClient.getStrangeName() + "/Дополнительное соглашение UpSale"+
                dataClient.getSupplementaryAgreementUpSaleContract().getNumber()+" "+dataClient.getNumberContract() + ".docx";

        if(!salesManager.getPathForSaveContract().equals("")) {
            try {
                new File(salesManager.getPathForSaveContract() + "\\" + dataClient.getNumberContract() + " " + dataClient.getStrangeName()).mkdirs();
                FileOutputStream fos = new FileOutputStream(salesManager.getPathForSaveContract() + "\\" + dataClient.getNumberContract() + " " + dataClient.getStrangeName() + "/Дополнительное соглашение UpSale" +
                        dataClient.getSupplementaryAgreementUpSaleContract().getNumber() + " " + dataClient.getNumberContract() + ".docx");
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
        else {
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
        }


    }

    public static void printDoc(DataClient dataClient, String howDoc,SalesManager salesManager) throws DontHaveFilePattern {
        String path = salesManager.getPathForSaveContract();
        if(path.equals("")){
            path="saveContract\\";
        }
        try {
            switch (howDoc) {
                case "printBaseContract": {
                        Desktop.getDesktop().print(new File(path + "\\" + dataClient.getNumberContract() + " " + dataClient.getStrangeName() + "/Договор" + dataClient.getNumberContract() + ".docx"));
                        Thread.sleep(2000);// Возникает ошибка, ворд не успевает закрыться а уже открывается второй, добавил задержку в 1 секунду
                        Desktop.getDesktop().print(new File(path + "\\" + dataClient.getNumberContract() + " " + dataClient.getStrangeName() + "/Договор" + dataClient.getNumberContract() + ".docx"));
                    break;

                }
                case "printUpSaleContract": {
                        Desktop.getDesktop().print(new File(path + "\\" + dataClient.getNumberContract() + " " + dataClient.getStrangeName() + "/ДоговорUpSale" + dataClient.getNumberContract() + ".docx"));
                        Thread.sleep(2000);// Возникает ошибка, ворд не успевает закрыться а уже открывается второй, добавил задержку в 1 секунду
                        Desktop.getDesktop().print(new File(path + "\\" + dataClient.getNumberContract() + " " + dataClient.getStrangeName() + "/ДоговорUpSale" + dataClient.getNumberContract() + ".docx"));
                    break;

                }
                case "printSupplementaryAgreementBasicContract": {
                        Desktop.getDesktop().print(new File(path + "\\" + dataClient.getNumberContract() + " " + dataClient.getStrangeName() +
                                "/Дополнительное соглашение №" + dataClient.getSupplementaryAgreementBasicContract().getNumber() + " " + dataClient.getNumberContract() + ".docx"));
                        Thread.sleep(2000);// Возникает ошибка, ворд не успевает закрыться а уже открывается второй, добавил задержку в 1 секунду Дополнительное соглашение №15 DA2-313123-21
                        Desktop.getDesktop().print(new File(path + "\\" + dataClient.getNumberContract() + " " + dataClient.getStrangeName() +
                                "/Дополнительное соглашение №" + dataClient.getSupplementaryAgreementBasicContract().getNumber() + " " + dataClient.getNumberContract() + ".docx"));
                    break;
                }
                case "printSupplementaryAgreementUpSaleContract": {
                        Desktop.getDesktop().print(new File(path + "\\" + dataClient.getNumberContract() + " " + dataClient.getStrangeName() + "/Дополнительное соглашение UpSale" +
                                dataClient.getSupplementaryAgreementUpSaleContract().getNumber() + " " + dataClient.getNumberContract() + ".docx"));
                        Thread.sleep(2000);// Возникает ошибка, ворд не успевает закрыться а уже открывается второй, добавил задержку в 1 секунду Дополнительное соглашение №15 DA2-313123-21
                        Desktop.getDesktop().print(new File(path + "\\" + dataClient.getNumberContract() + " " + dataClient.getStrangeName() + "/Дополнительное соглашение UpSale" +
                                dataClient.getSupplementaryAgreementUpSaleContract().getNumber() + " " + dataClient.getNumberContract() + ".docx"));
                    break;
                }
                case "printInvoiceDocument": {
                        Desktop.getDesktop().print(new File(path + "/Счет-фактура " + dataClient.getNumberContract() + ".xlsx"));
                    break;
                }

            }
        }catch (IllegalArgumentException | IOException d){
            throw new DontHaveFilePattern("Не смог распечатать. \n" +
                    "Скорее всего что то с принтером.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
