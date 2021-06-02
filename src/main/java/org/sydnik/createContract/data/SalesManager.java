package org.sydnik.createContract.data;

import java.io.*;

public class SalesManager implements Serializable {
    private String fullName;
    private int numberPowerOfAttorney;
    private String datePowerOfAttorney;
    private String miniName;

    public SalesManager(String fullName, int numberPowerOfAttorney, String datePowerOfAttorney) {
        this.fullName = fullName;
        this.numberPowerOfAttorney = numberPowerOfAttorney;
        this.datePowerOfAttorney = datePowerOfAttorney;
        setMiniName(fullName);
    }

    private void setMiniName(String fullName){
        String[] createMiniName = fullName.split(" ");
        miniName = createMiniName[0]+" ";
        for(int i= 0;i<createMiniName.length;i++){
            if(i>0){
                createMiniName[i] = createMiniName[i].substring(0,1)+".";
                miniName = miniName + createMiniName[i];
            }

        }
    }

    public String getFullName() {
        return fullName;
    }

    public int getNumberPowerOfAttorney() {
        return numberPowerOfAttorney;
    }

    public String getDatePowerOfAttorney() {
        return datePowerOfAttorney;
    }

    public String getMiniName() {
        return miniName;
    }

    public static void save(SalesManager salesManager){
        try {
            FileOutputStream fileOutput = new FileOutputStream("settingManager/dataSalesManager.dat");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);
            outputStream.writeObject(salesManager);
            fileOutput.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static SalesManager load(){
        try {
            FileInputStream fiStream = new FileInputStream("settingManager/dataSalesManager.dat");
            ObjectInputStream objectStream = new ObjectInputStream(fiStream);
            Object object = objectStream.readObject();
            fiStream.close();
            objectStream.close();

            return  (SalesManager) object;
        }  catch (Exception e) {
        }
        return null;


    }
}
