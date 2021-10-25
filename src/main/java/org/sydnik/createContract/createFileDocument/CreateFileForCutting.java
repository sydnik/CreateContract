package org.sydnik.createContract.createFileDocument;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.sydnik.createContract.NumberInWords;
import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.data.SalesManager;
import org.sydnik.createContract.exception.CantWriteDoc;
import org.sydnik.createContract.exception.DontHaveFilePattern;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateFileForCutting {

    public static void createFile (DataClient dataClient, SalesManager salesManager, String material, String decor, String edge) throws DontHaveFilePattern, CantWriteDoc {
        String fileName = "saveContract/" + dataClient.getNumberContract() + " " + dataClient.getStrangeName();
        XSSFWorkbook workbook;
        XSSFSheet sheet;
        try {
            FileInputStream fileInputStream = new FileInputStream("files/Распил.xlsx");
            workbook = new XSSFWorkbook(fileInputStream);
            sheet = workbook.getSheet("Бланк");
        } catch (Exception e) {
            throw new DontHaveFilePattern("Не смог получить доступ к шаблону\n" +
                    "files/Базовый договор.docx");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String toDay =  dateFormat.format(new Date());
        try {
            sheet.getRow(2).getCell(3).setCellValue(dataClient.getNumberContract());
            sheet.getRow(2).getCell(7).setCellValue(salesManager.getNumberPhoneManager());
            sheet.getRow(4).getCell(3).setCellValue(toDay);
            sheet.getRow(6).getCell(2).setCellValue(material);
            sheet.getRow(6).getCell(3).setCellValue(decor);
            sheet.getRow(7).getCell(3).setCellValue(edge + " в цвет");
        } catch (Exception e) {
            throw new CantWriteDoc("Не смог записать \n" + fileName);
        }
        if (!salesManager.getPathForSaveContract().equals("")) {
            try {
                new File(salesManager.getPathForSaveContract() + "\\" + dataClient.getNumberContract() + " "
                        + dataClient.getStrangeName()).mkdirs();
                if(checkNameFreeForSave(salesManager.getPathForSaveContract() + "\\"
                        + dataClient.getNumberContract() + " " + dataClient.getStrangeName() + "/"
                        + dataClient.getNumberContract() + " " + decor+ material+ ".xlsx")) {
                    FileOutputStream fos = new FileOutputStream(salesManager.getPathForSaveContract() + "\\"
                            + dataClient.getNumberContract() + " " + dataClient.getStrangeName() + "/"
                            + dataClient.getNumberContract() + " " + decor + material + ".xlsx");
                    workbook.write(fos);
                    workbook.close();
                    fos.close();
                }else {
                    throw new CantWriteDoc("Не смог сохранить файл т.к. файл с таким именем существует");
                }
            } catch (Exception e) {
                throw new CantWriteDoc("Не смог записать в папку которую вы указали в настройках,проверьте доступ \n"
                        + salesManager.getPathForSaveContract());
            }
        }
        else {
            try {
                new File(fileName).mkdirs();
                fileName = fileName+"/" + dataClient.getNumberContract() + " " + decor+ material+ ".xlsx";
                if(checkNameFreeForSave(fileName)) {
                    FileOutputStream fos = new FileOutputStream(fileName);
                    workbook.write(fos);
                    fos.close();
                }else {
                    throw new CantWriteDoc("Не смог сохранить файл т.к. файл с таким именем существует");
                }
            } catch (FileNotFoundException e) {
                throw new CantWriteDoc("Не смог записать в папку которую вы указали в настройках,проверьте доступ \n"
                        + "saveContract");

            } catch (IOException e) {
                throw new CantWriteDoc("Не смог записать в папку которую вы указали в настройках,проверьте доступ \n"
                        + "saveContract");

            }
        }

    }
    public static boolean checkNameFreeForSave(String path){
        File file = new File(path);
        if(file.exists()){
            return false;
        }
        return true;
    }
}
