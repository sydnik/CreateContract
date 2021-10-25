package org.sydnik.createContract.view;

import org.sydnik.createContract.MyController;
import org.sydnik.createContract.data.ListMaterial;
import org.sydnik.createContract.data.SalesManager;
import org.sydnik.createContract.exception.DontHaveData;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.HashMap;

public class ViewSetting implements Display {
    private MyController controller;
    private JPanel staticJPanel;
    private SalesManager salesManager;
    private JTextField fullNameSalesManager;
    private JTextField numberPowerOfAttorney;
    private JFormattedTextField numberPhoneManager;
    private JTextField pathForSaveContact;
    private JFormattedTextField datePowerOfAttorney;
    private JPanel panelForAddDecor;
    private JComboBox boxListMaterial;
    private JTextField decorForAdd;
    private JButton saveNewDecor;

    public ViewSetting(MyController controller, JPanel staticJPanel, SalesManager salesManager) {
        this.controller = controller;
        this.staticJPanel = staticJPanel;
        this.salesManager = salesManager;
    }
    @Override
    public void display(){
        staticJPanel.removeAll();
        staticJPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        MaskFormatter datePowerOfAttorneyFormatter = null,
                numberPhoneManagerFormatter = null;
        try {
            numberPhoneManagerFormatter = new MaskFormatter("+375(##) ### ####");

            datePowerOfAttorneyFormatter = new MaskFormatter("##.##.####");
            datePowerOfAttorney = new JFormattedTextField(datePowerOfAttorneyFormatter);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        staticJPanel.add(new JLabel("ФИО менеджера :"),gridBagConstraints);

        gridBagConstraints.gridy = 1;
        fullNameSalesManager = new JTextField();
        fullNameSalesManager.setText(salesManager.getFullName());
        fullNameSalesManager.setName("fullName");
        staticJPanel.add(fullNameSalesManager,gridBagConstraints);

        gridBagConstraints.gridy = 2;
        staticJPanel.add(new JLabel("Номер доверенности :"),gridBagConstraints);

        numberPowerOfAttorney = new JTextField();
        gridBagConstraints.gridy = 3;
        numberPowerOfAttorney.setName("numberPowerOfAttorney");
        numberPowerOfAttorney.setText(String.valueOf(salesManager.getNumberPowerOfAttorney()));
        numberPowerOfAttorney.addKeyListener(controller);
        staticJPanel.add(numberPowerOfAttorney,gridBagConstraints);

        gridBagConstraints.gridy = 4;
        staticJPanel.add(new JLabel("От какой даты действует :"),gridBagConstraints);

        gridBagConstraints.gridy = 5;
        datePowerOfAttorney.setName("datePowerOfAttorney");
        datePowerOfAttorney.setText(salesManager.getDatePowerOfAttorney());
        staticJPanel.add(datePowerOfAttorney,gridBagConstraints);

        gridBagConstraints.gridy = 6;
        staticJPanel.add(new JLabel("Номер менеджера:"),gridBagConstraints);

        gridBagConstraints.gridy = 7;
        numberPhoneManager = new JFormattedTextField(numberPhoneManagerFormatter);
        numberPhoneManager.setName("numberPhoneManager");
        numberPhoneManager.setText(salesManager.getNumberPhoneManager());
        staticJPanel.add(numberPhoneManager,gridBagConstraints);

        gridBagConstraints.gridy = 8;
        staticJPanel.add(new JLabel("Путь куда сохранять доки"),gridBagConstraints);

        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 300;
        pathForSaveContact = new JTextField();
        pathForSaveContact.setName("pathForSaveContact");
        pathForSaveContact.setText(salesManager.getPathForSaveContract());
        staticJPanel.add(pathForSaveContact,gridBagConstraints);

        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.ipadx = 70;
        JButton selectPath = new JButton("Указать путь");
        pathForSaveContact.addActionListener(controller);
        selectPath.addActionListener(controller);
        selectPath.setName("selectPath");
        staticJPanel.add(selectPath,gridBagConstraints);

        gridBagConstraints.gridy = 10;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);

        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        JButton addNewDecor = new JButton("Добавить декор");
        addNewDecor.setName("addNewDecor");
        addNewDecor.addActionListener(controller);
        staticJPanel.add(addNewDecor,gridBagConstraints);

        gridBagConstraints.gridy = 15;
        gridBagConstraints.ipady = 235;
        panelForAddDecor = new JPanel();
        staticJPanel.add(panelForAddDecor,gridBagConstraints);

        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.ipadx = 0;
        JButton save = new JButton("Сохранить");
        save.setName("saveSittingsManager");
        save.addActionListener(controller);
        staticJPanel.add(save,gridBagConstraints);

        gridBagConstraints.gridy = 17;
        JButton mainPage = new JButton("Главная страница");
        mainPage.setName("mainPage");
        mainPage.addActionListener(controller);
        staticJPanel.add(mainPage,gridBagConstraints);
        staticJPanel.revalidate();
        staticJPanel.repaint();
    }

    public void addNewDecor(){
        JFrame jFrame = (JFrame) SwingUtilities.getWindowAncestor(staticJPanel);
        JDialog dialog = new JDialog(jFrame, "Добавление декора", true);
        dialog.setDefaultCloseOperation(jFrame.DISPOSE_ON_CLOSE);
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(null);

        JPanel jPanel = new JPanel();
        dialog.setContentPane(jPanel);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        jPanel.setLayout(new GridBagLayout());
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 230;
        gridBagConstraints.ipady = 10;
        boxListMaterial = new JComboBox<>(ListMaterial.LIST_MATERIAL);
        boxListMaterial.setName("boxListMaterial");
        jPanel.add(boxListMaterial,gridBagConstraints);

        gridBagConstraints.gridy = 1;
        decorForAdd = new JTextField("Введите название декора");
        jPanel.add(decorForAdd,gridBagConstraints);

        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipady = 50;
        panelForAddDecor = new JPanel();
        jPanel.add(panelForAddDecor,gridBagConstraints);

        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipady = 20;
        saveNewDecor= new JButton("Добавить декор");
        saveNewDecor.setName("saveNewDecor");
        saveNewDecor.addActionListener(controller);
        jPanel.add(saveNewDecor,gridBagConstraints);

        jPanel.revalidate();
        jPanel.repaint();
        dialog.setVisible(true);
    }

    @Override
    public HashMap<String, String> getDataForSave() throws DontHaveData {
        HashMap<String,String> result = new HashMap<>();
        result.put("fullName",fullNameSalesManager.getText());
        result.put("numberPhoneManager",numberPhoneManager.getText());
        result.put("numberPowerOfAttorney",numberPowerOfAttorney.getText());
        result.put("datePowerOfAttorney",datePowerOfAttorney.getText());
        result.put("pathForSaveContact",pathForSaveContact.getText());
        return result;
    }
    public String getDecorForAdd(){
        return decorForAdd.getText();
    }
    public String getMaterialForAdd(){
        return (String) boxListMaterial.getSelectedItem();
    }
}
