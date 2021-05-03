package org.sydnik.createContract;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class MyView extends JPanel {
    private MyController controller;



    private static final Color BG_COLOR = new Color(0xbbada0);
    private static final String FONT_NAME = "Arial";
    private static final int TILE_SIZE = 96;
    private static final int TILE_MARGIN = 12;


    public MyView(MyController controller) {
        this.controller = controller;

    }

    public void startPage(SalesManager salesManager){
        removeAll();
        add(new JLabel("Добрый день "+salesManager.getMiniName()));
        JButton createNewClient = new JButton("Добавить нового клиента");
        createNewClient.setName("createNewClient");
        createNewClient.addActionListener(controller);
        add(createNewClient);
        JButton selectClient = new JButton("Выбрать клиента");
        selectClient.addActionListener(controller);
        add(selectClient);
        JButton settingsManager = new JButton("Настройки менеджера");
        settingsManager.setName("sittingsManager");
        settingsManager.addActionListener(controller);
        add(settingsManager);
        revalidate();
        repaint();
    }
    public void settingsManager(SalesManager salesManager){
        removeAll();
        add(new JLabel("ФИО менеджера :"));
        JTextField fullNameSalesManager = new JTextField();
        fullNameSalesManager.setText(salesManager.getFullName());
        fullNameSalesManager.setName("fullName");
        add(fullNameSalesManager);
        add(new JLabel("Номер доверенности :"));
        MaskFormatter numberPowerOfAttorneyFormatter = null;
        MaskFormatter datePowerOfAttorneyFormatter = null;
        JFormattedTextField numberPowerOfAttorney = null;
        JFormattedTextField datePowerOfAttorney = null;
        ;
        try {
            numberPowerOfAttorneyFormatter = new MaskFormatter("##");
            numberPowerOfAttorney = new JFormattedTextField(numberPowerOfAttorneyFormatter);

            datePowerOfAttorneyFormatter = new MaskFormatter("##.##.####");
            datePowerOfAttorney = new JFormattedTextField(datePowerOfAttorneyFormatter);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        numberPowerOfAttorney.setName("numberPowerOfAttorney");
        numberPowerOfAttorney.setText(String.valueOf(salesManager.getNumberPowerOfAttorney()));
        add(numberPowerOfAttorney);
        add(new JLabel("До какой даты действует :"));
        datePowerOfAttorney.setName("datePowerOfAttorney");
        datePowerOfAttorney.setText(salesManager.getDatePowerOfAttorney());
        add(datePowerOfAttorney);

        JButton save = new JButton("Сохранить");
        save.setName("saveSittingsManager");
        save.addActionListener(controller);
        add(save);
        JButton mainPage = new JButton("Главная страница");
        mainPage.setName("mainPage");
        mainPage.addActionListener(controller);
        add(mainPage);
        revalidate();
        repaint();


    }
    public void createNewClient(){
        removeAll();
        MaskFormatter
                identificationNumberFormatter = null,
                whenIssuedFormatter = null,
                numberPassportFormatter = null,
                phoneFormatter = null,
                numberContractFormatter = null,
                dateCreateContractFormatter = null;
        try {
        numberContractFormatter = new MaskFormatter("UU#-######-##*");
        phoneFormatter = new MaskFormatter("+375(##) ### ####");
        numberPassportFormatter = new MaskFormatter("UU#######");
        whenIssuedFormatter = new MaskFormatter("##.##.####");
        identificationNumberFormatter = new MaskFormatter(("#######U###UU#"));
        dateCreateContractFormatter = new MaskFormatter("##.##.####");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        add(new JLabel("Номер договора:"));
        JFormattedTextField numberContract = new JFormattedTextField(numberContractFormatter);
        numberContract.setName("numberContract");
        add(numberContract);


        add(new JLabel("ФИО клиенто полностью:"));
        JTextField fullNameClient = new JTextField();
        fullNameClient.setName("fullNameClient");
        add(fullNameClient);

        add(new JLabel("Номер паспорта:"));
        JFormattedTextField numberPassport = new JFormattedTextField(numberPassportFormatter);
        numberPassport.setName("numberPassport");
        add(numberPassport);

        add(new JLabel("Кем выдан:"));
        JTextField issuedByPassport = new JTextField();
        issuedByPassport.setName("issuedByPassport");
        add(issuedByPassport);

        add(new JLabel("Когда выдан:"));
        JFormattedTextField whenIssued = new JFormattedTextField(whenIssuedFormatter);
        whenIssued.setName("whenIssued");
        add(whenIssued);

        add(new JLabel("Идентификационный номер:"));
        JFormattedTextField identificationNumber = new JFormattedTextField(identificationNumberFormatter);
        identificationNumber.setName("identificationNumber");
        add(identificationNumber);

        add(new JLabel("Адрес регистрации:"));
        JTextField addressRegistration = new JTextField();
        addressRegistration.setName("addressRegistration");
        add(addressRegistration);

        add(new JLabel("Адрес доставки:"));
        JTextField addressDelivery = new JTextField();
        addressDelivery.setName("addressDelivery");
        add(addressDelivery);

        add(new JLabel("Мобильный телефон:"));
        JFormattedTextField numberPhoneClient = new JFormattedTextField(phoneFormatter);
        numberPhoneClient.setName("numberPhoneClient");
        add(numberPhoneClient);

        JButton save = new JButton("Сохранить");
        save.setName("saveNewDataClient");
        save.addActionListener(controller);
        add(save);

        JButton mainPage = new JButton("Главная страница");
        mainPage.setName("mainPage");
        mainPage.addActionListener(controller);
        add(mainPage);

        revalidate();
        repaint();

    }
    public void writeMessage(String message){
        add(new JLabel("Настройки сохранены"));
        revalidate();
        repaint();
    }

}
