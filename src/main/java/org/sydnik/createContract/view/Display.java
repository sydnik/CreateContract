package org.sydnik.createContract.view;

import org.sydnik.createContract.exception.DontHaveData;

import java.util.HashMap;

public interface Display {
    public void display();
    public HashMap<String,String> getDataForSave() throws DontHaveData;
}
