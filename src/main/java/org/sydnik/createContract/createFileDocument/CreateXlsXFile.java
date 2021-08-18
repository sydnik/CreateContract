package org.sydnik.createContract.createFileDocument;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.sydnik.createContract.NumberInWords;
import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.data.SalesManager;
import org.sydnik.createContract.exception.CantWriteDoc;
import org.sydnik.createContract.exception.DontHaveFilePattern;

import java.io.*;

//Данный класс предназначен для статик методов которые будут делать xlsx файлы из шаблонов
public class CreateXlsXFile {

    public static void createInvoiceDocument(DataClient dataClient, SalesManager salesManager) throws CantWriteDoc, DontHaveFilePattern {
        String fileName = "saveContract/" + dataClient.getNumberContract() + " " + dataClient.getStrangeName();
        XSSFWorkbook workbook;
        XSSFSheet sheet;
        try {
            FileInputStream fileInputStream = new FileInputStream("files/Счет-Фактура.xlsx");
            workbook = new XSSFWorkbook(fileInputStream);
            sheet = workbook.getSheet("TDSheet");
        } catch (Exception e) {
            throw new DontHaveFilePattern("Не смог получить доступ к шаблону\n" +
                    "files/Базовый договор.docx");
        }
        try {
            sheet.getRow(4).getCell(1).setCellValue("Счет-Фактура " + dataClient.getNumberContract() + " " + dataClient.getInvoiceDocument().getCreateDateInvoiceDocument());
            sheet.getRow(5).getCell(3).setCellValue(dataClient.getFullNameClient());
            sheet.getRow(6).getCell(3).setCellValue("Документ, удостоверяющий личность:\n" +
                    "Паспорт: " + dataClient.getNumberPassport() + " \n" +
                    "Когда и кем выдан: " + dataClient.getIssuedByPassport() + "\n" +
                    dataClient.getWhenIssued() + " \n" +
                    "Идентификационный номер: " + dataClient.getIdentificationNumber() + "\n" +
                    "Адрес регистрации: " + dataClient.getAddressRegistration() + "\n" +
                    "Адрес доставки: " + dataClient.getAddressDelivery() + "\n" +
                    "тел.: " + dataClient.getNumberPhoneClient());
            sheet.getRow(7).getCell(3).setCellValue("№" + dataClient.getNumberContract() + " " + dataClient.getBasicContract().getDateCreateContract());
            sheet.getRow(14).getCell(2).setCellValue("Набор мебельных деталей для кухни (№" + dataClient.getNumberContract() + ")q \n" +
                    "Страна производства Республика Беларусь");
            sheet.getRow(14).getCell(7).setCellValue(dataClient.getInvoiceDocument().getPriceBYNInvoiceDocument() + ".00");
            sheet.getRow(14).getCell(8).setCellValue(dataClient.getInvoiceDocument().getPriceBYNInvoiceDocument() + ".00");
            sheet.getRow(14).getCell(10).setCellValue(dataClient.getInvoiceDocument().getVat20InvoiceDocument());
            sheet.getRow(14).getCell(11).setCellValue(dataClient.getInvoiceDocument().getPriceBYNInvoiceDocument());
            sheet.getRow(15).getCell(10).setCellValue(dataClient.getInvoiceDocument().getVat20InvoiceDocument());
            sheet.getRow(15).getCell(11).setCellValue(dataClient.getInvoiceDocument().getPriceBYNInvoiceDocument());

            sheet.getRow(18).getCell(3).setCellValue(NumberInWords.sumBYNInWords(dataClient.getInvoiceDocument().getVat20InvoiceDocument()));
            sheet.getRow(19).getCell(3).setCellValue(NumberInWords.sumBYNInWords(dataClient.getInvoiceDocument().getPriceInEURInvoiceDocument()));
            sheet.getRow(21).getCell(1).setCellValue(dataClient.getInvoiceDocument().getLineForBank());
            new File(fileName).mkdirs();
            fileName = fileName+"/Счет-фактура " + dataClient.getNumberContract() + ".xlsx";
            FileOutputStream fos = new FileOutputStream(fileName);
                workbook.write(fos);
                fos.close();
            } catch (Exception e) {
                throw new CantWriteDoc("Не смог записать \n" + fileName);
            }
            if (!salesManager.getPathForSaveContract().equals("")) {
                try {
                    new File(salesManager.getPathForSaveContract() + "\\" + dataClient.getNumberContract() + " " + dataClient.getStrangeName()).mkdirs();
                    FileOutputStream fos = new FileOutputStream(salesManager.getPathForSaveContract() + "\\" + dataClient.getNumberContract() +
                            " " + dataClient.getStrangeName() + "/Счет-фактура " + dataClient.getNumberContract() + ".xlsx");
                    workbook.write(fos);
                    workbook.close();
                    fos.close();
                } catch (Exception e) {
                    throw new CantWriteDoc("Не смог записать в папку которую вы указали в настройках,проверьте доступ \n" + salesManager.getPathForSaveContract());
                }
            }
    }
}
