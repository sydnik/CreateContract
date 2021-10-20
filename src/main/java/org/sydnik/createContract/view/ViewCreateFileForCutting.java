package org.sydnik.createContract.view;

import org.sydnik.createContract.MyController;
import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.exception.DontHaveData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewCreateFileForCutting extends MainViewContract implements Display {

    private final String[] LIST_MATERIAL = {"", "ДСП", "Столешница", "ДВП"};

    private JComboBox<String> boxListMaterial;
    private JTextField test;
    private ArrayList<String> editListMaterial;

    public ViewCreateFileForCutting(JPanel staticJPanel, DataClient dataClient, MyController controller,ArrayList<String> listChipboard) {
        super(staticJPanel, dataClient, "Файлы для распила", controller);
    }
    public void display(){
        startPage();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        workingWindow.add(new JLabel("Материал:"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        boxListMaterial = new JComboBox<>(LIST_MATERIAL);
        boxListMaterial.setName("boxListMaterial");
        boxListMaterial.addActionListener(controller);
        boxListMaterial.setEditable(true);
        test = (JTextField) boxListMaterial.getEditor().getEditorComponent();
        test.addKeyListener(controller);
        test.addMouseListener(controller);
        test.setName("boxListMaterial");
//        boxListMaterial.add(new JList<String(LIST_MATERIAL));



        workingWindow.add(boxListMaterial,gridBagConstraints);

        endPage();
    }


    public void filterBoxListMaterial(String text){
        editListMaterial = new ArrayList();
        if(!boxListMaterial.isPopupVisible()){
            boxListMaterial.setPopupVisible(true);
        }
        for (int i = 0; i < LIST_MATERIAL.length; i++) {
            if(LIST_MATERIAL[i].toLowerCase().contains(test.getText().toLowerCase())){
                editListMaterial.add(LIST_MATERIAL[i]);
            }
        }
        boxListMaterial.removeAllItems();
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


//        boxListMaterial.revalidate();
        boxListMaterial.repaint();

    }
    public void popupVisible(){
        boxListMaterial.setPopupVisible(true);
    }
    @Override
    public HashMap<String, String> getDataForSave() throws DontHaveData {
        return null;
    }
}
