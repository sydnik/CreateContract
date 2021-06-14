package org.sydnik.createContract;

import org.sydnik.createContract.data.DataClient;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateDocumentsAndSave {
    public static void createBaseContract(DataClient dataClient){
        try {
            String docWord = Files.readString(Paths.get(
                    "files/Базовый договор.xml"));
            //ВАжно чтобы первым выполнялась заполенение суммы прописью!!!
            docWord = docWord.replaceAll("allSumInEURWord", NumberInWords.sumEURInWords(dataClient.getAllSumInEUR()));
            docWord = docWord.replaceAll("allSumInBYNWord", NumberInWords.sumBYNInWords(dataClient.getAllSumInBYN()));
            docWord = docWord.replaceAll("prepaymentOr10PercentSumWord", NumberInWords.sumEURInWords(dataClient.getPrepaymentOr10PercentSum()));
            docWord = docWord.replaceAll("payUpTo50PercentSumWord", NumberInWords.sumEURInWords(dataClient.getPayUpTo50PercentSum()));
            docWord = docWord.replaceAll("payUpTo100PercentSumWord", NumberInWords.sumEURInWords(dataClient.getPayUpTo100PercentSum()));
            //Все остально не должно мешать работе идеи
            docWord = docWord.replaceAll("NumberContract", dataClient.getNumberContract());
            docWord = docWord.replaceAll("fullNameClient", dataClient.getFullNameClient());
            docWord = docWord.replaceAll("miniNameClient", dataClient.getMiniNameClient());
            docWord = docWord.replaceAll("numberPassport", dataClient.getNumberPassport());
            docWord = docWord.replaceAll("issuedByPassport", dataClient.getIssuedByPassport());
            docWord = docWord.replaceAll("whenIssued", dataClient.getWhenIssued());
            docWord = docWord.replaceAll("identificationNumber", dataClient.getIdentificationNumber());
            docWord = docWord.replaceAll("addressRegistration", dataClient.getAddressRegistration());
            docWord = docWord.replaceAll("addressDelivery", dataClient.getAddressDelivery());
            docWord = docWord.replaceAll("numberPhoneClient", dataClient.getNumberPhoneClient());
            docWord = docWord.replaceAll("fullNameSalesManager", dataClient.getFullNameSalesManager());
            docWord = docWord.replaceAll("numberPowerOfAttorney", dataClient.getNumberPowerOfAttorney());
            docWord = docWord.replaceAll("datePowerOfAttorney", dataClient.getDatePowerOfAttorney());
            docWord = docWord.replaceAll("miniSalesManager", dataClient.getMiniSalesManager());
            docWord = docWord.replaceAll("dateCreateContract", dataClient.getDateCreateContract());
            docWord = docWord.replaceAll("timeProduction", dataClient.getTimeProduction());
            docWord = docWord.replaceAll("allSumInEUR", String.valueOf(dataClient.getAllSumInEUR()));
            docWord = docWord.replaceAll("allSumInBYN", String.valueOf(dataClient.getAllSumInBYN()));
            docWord = docWord.replaceAll("prepaymentOr10PercentSum", String.valueOf(dataClient.getPrepaymentOr10PercentSum()));
            docWord = docWord.replaceAll("payUpTo50PercentSum", String.valueOf(dataClient.getPayUpTo50PercentSum()));
            docWord = docWord.replaceAll("payUpTo100PercentSum", String.valueOf(dataClient.getPayUpTo100PercentSum()));



            String fileName = "saveContract/" + dataClient.getFullNameClient() + " " + dataClient.getNumberContract()+"/Договор" +dataClient.getNumberContract()+ ".xml";
            Charset charset = StandardCharsets.UTF_8;
            Path path = Paths.get(fileName);
            Files.write(path,docWord.getBytes(charset));
        }
        catch (Exception e){
            System.out.println("то то не то");
        }
    }
}
