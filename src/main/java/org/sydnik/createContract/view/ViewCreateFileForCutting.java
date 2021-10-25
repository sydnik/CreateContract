package org.sydnik.createContract.view;

import org.sydnik.createContract.MyController;
import org.sydnik.createContract.MyView;
import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.data.ListMaterial;
import org.sydnik.createContract.exception.DontHaveData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewCreateFileForCutting extends MainViewContract implements Display {
    

    private JComboBox boxListMaterial;
    private JComboBox boxListDecor;
    private JComboBox boxListEdge;
    private JTextField jTextFieldBoxMaterial;
    private JTextField jTextFieldBoxListDecor;
    private JTextField jTextFieldBoxListEdge;
    private ListMaterial listMaterial;

    public ViewCreateFileForCutting(JPanel staticJPanel, DataClient dataClient, MyController controller) {
        super(staticJPanel, dataClient, "Файлы для распила", controller);
        listMaterial = new ListMaterial();
        listMaterial.readListCatalog("");
    }
    public void display(){
        startPage();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 50;
        workingWindow.add(new JLabel("Материал:"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.ipadx = 170;
        boxListMaterial = new JComboBox<>(ListMaterial.LIST_MATERIAL);
        boxListMaterial.setName("boxListMaterial");
        boxListMaterial.addActionListener(controller);
        boxListMaterial.setEditable(true);
        jTextFieldBoxMaterial = (JTextField) boxListMaterial.getEditor().getEditorComponent();
        jTextFieldBoxMaterial.addKeyListener(controller);
        jTextFieldBoxMaterial.addMouseListener(controller);
        jTextFieldBoxMaterial.setName("boxListMaterial");
        workingWindow.add(boxListMaterial,gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 50;
        JLabel label1 = new JLabel("Декор:");
        workingWindow.add(label1,gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.ipadx = 170;
        boxListDecor = new JComboBox(listMaterial.getListCatalog().toArray());
        boxListDecor.setPrototypeDisplayValue("XXXXXXX");
        boxListDecor.setName("boxListDecor");
        boxListDecor.addActionListener(controller);
        boxListDecor.setEditable(true);
        boxListDecor.setMaximumRowCount(20);
        workingWindow.add(boxListDecor,gridBagConstraints);
        jTextFieldBoxListDecor = (JTextField) boxListDecor.getEditor().getEditorComponent();
        jTextFieldBoxListDecor.addKeyListener(controller);
        jTextFieldBoxListDecor.addMouseListener(controller);
        jTextFieldBoxListDecor.setName("boxListDecor");

        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 50;
        workingWindow.add(new JLabel("Толщина кромки:"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.ipadx = 170;
        boxListEdge = new JComboBox(ListMaterial.LIST_EDGE);
        boxListEdge.setName("boxListEdge");
        boxListEdge.setEditable(true);
        jTextFieldBoxListEdge = (JTextField) boxListEdge.getEditor().getEditorComponent();
        jTextFieldBoxListEdge.setName("boxListEdge");
        jTextFieldBoxListEdge.addMouseListener(controller);
        workingWindow.add(boxListEdge,gridBagConstraints);

        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        JButton createFileForCutting = new JButton("Создать файл для распила");
        createFileForCutting.setName("createFileForCutting");
        createFileForCutting.addActionListener(controller);
        workingWindow.add(createFileForCutting,gridBagConstraints);


        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 0;
        JButton openDirectoryWithFile = new JButton("Открыть папку с файлом");
        openDirectoryWithFile.setName("openDirectoryWithFile");
        openDirectoryWithFile.addActionListener(controller);
        workingWindow.add(openDirectoryWithFile,gridBagConstraints);

        endPage();
        boxListMaterial.requestFocusInWindow();
    }



    public void filterBoxListMaterial(String text){
        boxListMaterial.setPopupVisible(false);
        ArrayList<String> editListMaterial;
        editListMaterial = new ArrayList();
        if(!boxListMaterial.isPopupVisible()){
            boxListMaterial.setPopupVisible(true);
        }
        for (int i = 0; i < ListMaterial.LIST_MATERIAL.length; i++) {
            if(ListMaterial.LIST_MATERIAL[i].toLowerCase().contains(jTextFieldBoxMaterial.getText().toLowerCase())){
                editListMaterial.add(ListMaterial.LIST_MATERIAL[i]);
            }
        }
        try {
            boxListMaterial.removeActionListener(controller);
            boxListMaterial.removeAllItems();
            }
        catch (Exception e){}
        ((JTextField)(boxListMaterial.getEditor().getEditorComponent())).setText(text);
        boolean check = false;
        for (int i = 0; i < editListMaterial.size(); i++) {
            if(editListMaterial.get(i).equals(text)){
                check= true;
            }
        }
        if(!check) {
            boxListMaterial.addItem(text);
        }
        for (int i = 0; i < editListMaterial.size(); i++) {
            boxListMaterial.addItem(editListMaterial.get(i));
        }
        boxListMaterial.addActionListener(controller);
        boxListMaterial.setPopupVisible(true);
    }
    public void filterBoxListDecor(String text){
        ArrayList<String> editListDecor = new ArrayList();
        if(!boxListDecor.isPopupVisible()){
            boxListDecor.setPopupVisible(true);
        }
        for (int i = 0; i < listMaterial.getListCatalog().size(); i++) {
            if(listMaterial.getListCatalog().get(i).toLowerCase().contains(jTextFieldBoxListDecor.getText().toLowerCase())){
                editListDecor.add(listMaterial.getListCatalog().get(i));
            }
        }
        boxListDecor.removeAllItems();
        ((JTextField)(boxListDecor.getEditor().getEditorComponent())).setText(text);
        boolean check = false;
        for (int i = 0; i < editListDecor.size(); i++) {
            if(editListDecor.get(i).equals(text)){
                check= true;
            }
        }
        if(!check) {
            boxListDecor.addItem(text);
        }
        for (int i = 0; i < editListDecor.size(); i++) {
            boxListDecor.addItem(editListDecor.get(i));
        }
    }
    public void updateBoxListDecor(){
        listMaterial.readListCatalog(boxListMaterial.getSelectedItem().toString());
        try {
            boxListDecor.removeActionListener(controller);
            boxListDecor.removeAllItems();
        }catch (Exception e){}
        boxListDecor.addActionListener(controller);
        String[] list = listMaterial.getListCatalog().toArray(new String[0]);
        for (int i = 0; i < list.length; i++) {
            boxListDecor.addItem(list[i]);
        }
        if(boxListMaterial.getSelectedItem().toString().equals(ListMaterial.LIST_MATERIAL[1])){
            boxListEdge.setSelectedItem(ListMaterial.LIST_EDGE[3]);
        }
        else if(boxListMaterial.getSelectedItem().toString().equals(ListMaterial.LIST_MATERIAL[2])){
            boxListEdge.setSelectedItem(ListMaterial.LIST_EDGE[4]);
        }
        boxListDecor.requestFocusInWindow();
    }
    public void setVisibleBoxMaterial(){
        if(!boxListMaterial.isPopupVisible()){
            boxListMaterial.setPopupVisible(true);
        }
    }
    public void setVisibleBoxDecor(){
        if(!boxListDecor.isPopupVisible()){
            boxListDecor.setPopupVisible(true);
        }
    }
    public void setVisibleBoxEdge(){
        if(!boxListEdge.isPopupVisible()){
            boxListEdge.setPopupVisible(true);
        }
    }

    public String getValueMaterial(){
        return (String) boxListMaterial.getSelectedItem();
    }
    public String getValueDecor(){
        return (String) boxListDecor.getSelectedItem();
    }
    public String getValueEdge(){
        return (String) boxListEdge.getSelectedItem();
    }
    @Override
    public HashMap<String, String> getDataForSave() throws DontHaveData {
        return null;
    }
}
