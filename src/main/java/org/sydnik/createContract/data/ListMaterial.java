package org.sydnik.createContract.data;

import org.sydnik.createContract.exception.CantWriteDoc;
import org.sydnik.createContract.exception.DontHaveData;

import java.io.*;
import java.util.ArrayList;

public class ListMaterial {
    public static final String[] LIST_MATERIAL = {"", "ДСП", "Столешницы", "ДВП"};
    public static final String[] LIST_EDGE = {"", "0.5 мм","0.8 мм", "1 мм", "2 мм"};
    private ArrayList<String> listCatalog;
    private String path;

    public ListMaterial() {
        listCatalog = new ArrayList<>();;
    }

    public void readListCatalog(String s){
        listCatalog = new ArrayList<>();
        path= "material/"+s+".txt";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))){
            while (reader.ready()){
                listCatalog.add(reader.readLine());
            }
        }
        catch (IOException e) {
            listCatalog = new ArrayList<>();
            listCatalog.add("");
        }

    }

    public void addDecor(String decor) throws CantWriteDoc, DontHaveData {
        if(listCatalog.size()==0){
            throw new DontHaveData("Возможно вы забыли выбрать материал куда добавлять");
        }
        int position = findPosition((listCatalog.size()-1)/2,decor.toLowerCase(),(listCatalog.size()));
        if(position==-1){
            throw new CantWriteDoc("Такой декор уже существует");
        }
        listCatalog.add(position,decor);
        writeListDecor();

    }

    public ArrayList<String> getListCatalog(){
        return listCatalog;
    }

    private void writeListDecor() throws CantWriteDoc {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"))){
            for(String s:listCatalog)
            {
                writer.write(s+"\n");
            }
        } catch (IOException e) {
            throw new CantWriteDoc("Что то пошло не так, не смог записать данные");
        }
    }

    private int findPosition(int a,String decor,int maxPosition){
        if(listCatalog.get(a).toLowerCase().compareTo(decor)>0){
            if(maxPosition-a==1){
                return a;
            }
            return findPosition((a/2),decor,a);
        }else if(listCatalog.get(a).toLowerCase().compareTo(decor)<0){
                if(maxPosition-a ==1){
                return a+1;
            }
            return findPosition(((maxPosition-a)/2+a),decor,maxPosition);
        }
        return -1;
    }


}
