package org.sydnik.createContract.data;

import java.io.*;
import java.util.HashMap;

public class SalesManager implements Serializable {
    private String fullName;
    private String numberPowerOfAttorney;
    private String datePowerOfAttorney;
    private String miniName;
    private String numberPhoneManager;
    private String pathForSaveContact;

    public SalesManager(String fullName, String numberPowerOfAttorney, String datePowerOfAttorney,
                        String numberPhoneManager, String pathForSaveContact) {
        this.fullName = fullName;
        this.numberPowerOfAttorney = numberPowerOfAttorney;
        this.datePowerOfAttorney = datePowerOfAttorney;
        this.numberPhoneManager = numberPhoneManager;
        this.pathForSaveContact = pathForSaveContact;
        setMiniName(fullName);
    }
    private SalesManager(String fullName, String numberPowerOfAttorney, String datePowerOfAttorney,
                         String miniName, String numberPhoneManager, String pathForSaveContact) {
        this.fullName = fullName;
        this.numberPowerOfAttorney = numberPowerOfAttorney;
        this.datePowerOfAttorney = datePowerOfAttorney;
        this.miniName = miniName;
        this.numberPhoneManager = numberPhoneManager;
        this.pathForSaveContact = pathForSaveContact;
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

    public String getNumberPowerOfAttorney() {
        return numberPowerOfAttorney;
    }

    public String getDatePowerOfAttorney() {
        return datePowerOfAttorney;
    }

    public String getMiniName() {
        return miniName;
    }

    public String getNumberPhoneManager() {
        return numberPhoneManager;
    }

    public String getPathForSaveContact() {
        return pathForSaveContact;
    }

    public void save(){
        String path ="settingManager/";
        new File(path).mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path+"basicSetting.dat"))){
            String data = "fullName/=/"+fullName+"\n"+
                    "numberPowerOfAttorney/=/"+numberPowerOfAttorney+"\n"+
                    "datePowerOfAttorney/=/"+datePowerOfAttorney+"\n"+
                    "miniName/=/"+miniName+"\n"+
                    "numberPhoneManager/=/"+numberPhoneManager+"\n"+
                    "pathForSaveContact/=/"+pathForSaveContact;
            writer.write(data);
        } catch (Exception e) {
        }
    }
    public static SalesManager load(){
        try (BufferedReader reader = new BufferedReader(new FileReader("settingManager/basicSetting.dat"))){
            HashMap<String,String> map = new HashMap<>();
            String line = "";
            String[] data = new String[0];
            while (reader.ready()){
                try {
                    line = reader.readLine();
                    data = line.split("/=/");
                    map.put(data[0], data[1]);
                }catch (ArrayIndexOutOfBoundsException e){
                    if (data[0].equals("pathForSaveContact")){
                        map.put(data[0],"");
                    }

                }
            }
            if(map.size()<4){
                return null;
            }
            return new SalesManager(map.get("fullName"),map.get("numberPowerOfAttorney"),
                    map.get("datePowerOfAttorney"),map.get("miniName"),map.get("numberPhoneManager"),map.get("pathForSaveContact"));
        }  catch (Exception e) {
        }
        return null;
    }
}
