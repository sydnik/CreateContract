package org.sydnik.createContract.view;


import org.sydnik.createContract.MyController;
import org.sydnik.createContract.exception.DontHaveData;
import org.sydnik.createContract.myComponent.MyButton;
import org.sydnik.createContract.myComponent.MyTextField;
import org.sydnik.createContract.myComponent.ValueButton;
import org.sydnik.createContract.myComponent.ValueTextField;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewListClients implements Display {
    private JPanel staticJPanel;
    private MyController controller;
    private String[] allClient;
    private ArrayList<String> filterResult;
    private MyTextField searchClient;
    private JList<String> jList;
    private JScrollPane listClients;

    public ViewListClients(JPanel staticJPanel, MyController controller, String[] allClient) {
        this.staticJPanel = staticJPanel;
        this.controller = controller;
        this.allClient = allClient;
    }
    @Override
    public void display() {
        int row = 0;
        staticJPanel.removeAll();
        staticJPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridy     = row++;
        gridBagConstraints.gridx     = 0;
        JLabel jl = new JLabel("Поиск клиента ");
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        staticJPanel.add(jl,gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.ipadx = 275;
        searchClient = new MyTextField(ValueTextField.SEARCH_CLIENT,"",true,controller);
        staticJPanel.add(searchClient,gridBagConstraints);
        gridBagConstraints.gridx     = 2;
        gridBagConstraints.ipadx     = 20;
        staticJPanel.add(new MyButton(ValueButton.SEARCH_CLIENT_BUTTON,controller), gridBagConstraints);

        gridBagConstraints.gridy     = row++;
        gridBagConstraints.ipady     = 450;
        gridBagConstraints.weightx   = 0.0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridx     = 0;
        if(allClient==null){
            allClient = new String[1];
            allClient[0] = "Нет клиентов";
        }
        jList = new JList<>(allClient);
        jList.setName("jListClients");
        jList.addMouseListener(controller);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listClients = new JScrollPane(jList);
        staticJPanel.add(listClients,gridBagConstraints);

        gridBagConstraints.gridy     = row++;
        gridBagConstraints.ipady     = 0;
        gridBagConstraints.gridx     = 0;
        gridBagConstraints.gridwidth = 3;
        staticJPanel.add(new MyButton(ValueButton.VIEW_CLIENT,controller),gridBagConstraints);

        gridBagConstraints.gridy     = row++;
        gridBagConstraints.ipady     = 0;
        gridBagConstraints.gridx     = 0;
        gridBagConstraints.gridwidth = 3;
        staticJPanel.add(new MyButton(ValueButton.VIEW_MAIN_PAGE,controller), gridBagConstraints);

        staticJPanel.revalidate();
        staticJPanel.repaint();
        searchClient.requestFocusInWindow();
    }

    @Override
    public HashMap<String, String> getData() throws DontHaveData {
        HashMap<String,String> map = new HashMap<>();
        map.put("result",jList.getSelectedValue());
        return map;
    }

    public void filter(){
        filterResult = new ArrayList<>();
        String finalLine = searchClient.getText();
        for (int i = 0; i < allClient.length; i++) {
            if(allClient[i].toLowerCase().contains(finalLine.toLowerCase())){
                filterResult.add(allClient[i]);
            }
        }
        jList.removeAll();
        jList.setListData(filterResult.toArray(new String[0]));

    }

}
