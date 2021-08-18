package org.sydnik.createContract;


import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.data.SalesManager;
import org.sydnik.createContract.view.*;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.xml.crypto.Data;
import java.awt.*;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

public class MyView extends JFrame {
    private MyController controller;
    private JPanel staticJPanel;


    public MyView() {
        super("CreateContract");
        setSize(500, 600);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);//окно по центру
        setVisible(true);
        staticJPanel = new JPanel();
        staticJPanel.setLayout(new GridLayout(20,1));
        UIManager.put("Label.font", new Font("Aria", Font.BOLD, 15));

    }
    public void setController(MyController controller) {
        this.controller = controller;
    }
    //Вся визуализация работает через staticJpanel Поэтому обычный getComponents не работает как надо.
    // Когда нужны компоненты экрана использоваться getComponentsStaticPanel
    public Component[] getComponentsStaticPanel() {
        return staticJPanel.getComponents();
    }

    public void displayInputRate() {
        staticJPanel.removeAll();
        staticJPanel.setLayout(new GridLayout(20,1));
        this.add(staticJPanel);
        staticJPanel.add(new JLabel("Не могу узнать курс евро придется вводить в ручную:("));
        JTextField valueCurrency = new JTextField();
        valueCurrency.setName("valueCurrency");
        staticJPanel.add(valueCurrency);
        JButton saveCurrency = new JButton("Сохранть курс");
        saveCurrency.setName("saveCurrency");
        saveCurrency.addActionListener(controller);
        staticJPanel.add(saveCurrency);


        staticJPanel.revalidate();
        staticJPanel.repaint();
    }
    //Самая первая страница при запуске
    public void mainPage(SalesManager salesManager){
        staticJPanel.removeAll();
        staticJPanel.setLayout(new GridLayout(20,1));
        this.add(staticJPanel);
        staticJPanel.add(new JLabel("Добрый день "+salesManager.getMiniName()));
        JButton createNewClient = new JButton("Добавить нового клиента");
        createNewClient.setName("createNewClient");
        createNewClient.addActionListener(controller);
        staticJPanel.add(createNewClient);
        JButton selectClient = new JButton("Выбрать клиента");
        selectClient.setName("selectClient");
        selectClient.addActionListener(controller);
        staticJPanel.add(selectClient);
        JButton settingsManager = new JButton("Настройки менеджера");
        settingsManager.setName("sittingsManager");
        settingsManager.addActionListener(controller);
        staticJPanel.add(settingsManager);

        staticJPanel.revalidate();
        staticJPanel.repaint();
    }

    public void settingsManager(SalesManager salesManager){
        staticJPanel.removeAll();
        staticJPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        MaskFormatter datePowerOfAttorneyFormatter = null,
                numberPhoneManagerFormatter = null;
        JFormattedTextField datePowerOfAttorney = null;
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
        JTextField fullNameSalesManager = new JTextField();
        fullNameSalesManager.setText(salesManager.getFullName());
        fullNameSalesManager.setName("fullName");
        staticJPanel.add(fullNameSalesManager,gridBagConstraints);

        gridBagConstraints.gridy = 2;
        staticJPanel.add(new JLabel("Номер доверенности :"),gridBagConstraints);

        JTextField numberPowerOfAttorney = new JTextField();
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
        JFormattedTextField numberPhoneManager = new JFormattedTextField(numberPhoneManagerFormatter);
        numberPhoneManager.setName("numberPhoneManager");
        numberPhoneManager.setText(salesManager.getNumberPhoneManager());
        staticJPanel.add(numberPhoneManager,gridBagConstraints);

        gridBagConstraints.gridy = 8;
        staticJPanel.add(new JLabel("Путь куда сохранять доки"),gridBagConstraints);

        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 300;
        JTextField pathForSaveContact = new JTextField();
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
        gridBagConstraints.ipady = 270;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);

        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.ipadx = 0;
        JButton save = new JButton("Сохранить");
        save.setName("saveSittingsManager");
        save.addActionListener(controller);
        staticJPanel.add(save,gridBagConstraints);

        gridBagConstraints.gridy = 12;
        JButton mainPage = new JButton("Главная страница");
        mainPage.setName("mainPage");
        mainPage.addActionListener(controller);
        staticJPanel.add(mainPage,gridBagConstraints);
        staticJPanel.revalidate();
        staticJPanel.repaint();


    }
    public void createNewClient(){
        staticJPanel.removeAll();
        staticJPanel.setLayout(new GridLayout(22,1));
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

        staticJPanel.add(new JLabel("Номер договора:"));
        JFormattedTextField numberContract = new JFormattedTextField(numberContractFormatter);
        numberContract.setName("numberContract");
        staticJPanel.add(numberContract);

        staticJPanel.add(new JLabel("Странное название"));
        JTextField strangeName = new JTextField();
        strangeName.setName("strangeName");
        staticJPanel.add(strangeName);

        staticJPanel.add(new JLabel("ФИО клиенто полностью:"));
        JTextField fullNameClient = new JTextField();
        fullNameClient.setName("fullNameClient");
        staticJPanel.add(fullNameClient);

        staticJPanel.add(new JLabel("Номер паспорта:"));
        JFormattedTextField numberPassport = new JFormattedTextField(numberPassportFormatter);
        numberPassport.setName("numberPassport");
        staticJPanel.add(numberPassport);

        staticJPanel.add(new JLabel("Кем выдан:"));
        JTextField issuedByPassport = new JTextField();
        issuedByPassport.setName("issuedByPassport");
        staticJPanel.add(issuedByPassport);

        staticJPanel.add(new JLabel("Когда выдан:"));
        JFormattedTextField whenIssued = new JFormattedTextField(whenIssuedFormatter);
        whenIssued.setName("whenIssued");
        staticJPanel.add(whenIssued);

        staticJPanel.add(new JLabel("Идентификационный номер:"));
        JFormattedTextField identificationNumber = new JFormattedTextField(identificationNumberFormatter);
        identificationNumber.setName("identificationNumber");
        staticJPanel.add(identificationNumber);

        staticJPanel.add(new JLabel("Адрес регистрации:"));
        JTextField addressRegistration = new JTextField();
        addressRegistration.setName("addressRegistration");
        staticJPanel.add(addressRegistration);

        staticJPanel.add(new JLabel("Адрес доставки:"));
        JTextField addressDelivery = new JTextField();
        addressDelivery.setName("addressDelivery");
        staticJPanel.add(addressDelivery);

        staticJPanel.add(new JLabel("Мобильный телефон:"));
        JFormattedTextField numberPhoneClient = new JFormattedTextField(phoneFormatter);
        numberPhoneClient.setName("numberPhoneClient");
        staticJPanel.add(numberPhoneClient);

        JButton save = new JButton("Добавить нового клиента");
        save.setName("saveNewDataClient");
        save.addActionListener(controller);
        staticJPanel.add(save);

        JButton mainPage = new JButton("Главная страница");
        mainPage.setName("mainPage");
        mainPage.addActionListener(controller);
        staticJPanel.add(mainPage);

        staticJPanel.revalidate();
        staticJPanel.repaint();

    }
    public void editDataAboutClient(DataClient dataClient){
        staticJPanel.removeAll();
        staticJPanel.setLayout(new GridLayout(23,1));
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

        staticJPanel.add(new JLabel("Номер договора:"));
        JFormattedTextField numberContract = new JFormattedTextField(numberContractFormatter);
        numberContract.setText(dataClient.getNumberContract());
        numberContract.setName("numberContract");
        numberContract.setEnabled(false);
        staticJPanel.add(numberContract);

        staticJPanel.add(new JLabel("Странное название"));
        JTextField strangeName = new JTextField();
        strangeName.setText(dataClient.getStrangeName());
        strangeName.setName("strangeName");
        strangeName.setEnabled(false);
        staticJPanel.add(strangeName);

        staticJPanel.add(new JLabel("ФИО клиенто полностью:"));
        JTextField fullNameClient = new JTextField();
        fullNameClient.setText(dataClient.getFullNameClient());
        fullNameClient.setName("fullNameClient");
        staticJPanel.add(fullNameClient);

        staticJPanel.add(new JLabel("Номер паспорта:"));
        JFormattedTextField numberPassport = new JFormattedTextField(numberPassportFormatter);
        numberPassport.setText(dataClient.getNumberPassport());
        numberPassport.setName("numberPassport");
        staticJPanel.add(numberPassport);

        staticJPanel.add(new JLabel("Кем выдан:"));
        JTextField issuedByPassport = new JTextField();
        issuedByPassport.setText(dataClient.getIssuedByPassport());
        issuedByPassport.setName("issuedByPassport");
        staticJPanel.add(issuedByPassport);

        staticJPanel.add(new JLabel("Когда выдан:"));
        JFormattedTextField whenIssued = new JFormattedTextField(whenIssuedFormatter);
        whenIssued.setText(dataClient.getWhenIssued());
        whenIssued.setName("whenIssued");
        staticJPanel.add(whenIssued);

        staticJPanel.add(new JLabel("Идентификационный номер:"));
        JFormattedTextField identificationNumber = new JFormattedTextField(identificationNumberFormatter);
        identificationNumber.setName("identificationNumber");
        identificationNumber.setText(dataClient.getIdentificationNumber());
        staticJPanel.add(identificationNumber);

        staticJPanel.add(new JLabel("Адрес регистрации:"));
        JTextField addressRegistration = new JTextField();
        addressRegistration.setText(dataClient.getAddressRegistration());
        addressRegistration.setName("addressRegistration");
        staticJPanel.add(addressRegistration);

        staticJPanel.add(new JLabel("Адрес доставки:"));
        JTextField addressDelivery = new JTextField();
        addressDelivery.setText(dataClient.getAddressDelivery());
        addressDelivery.setName("addressDelivery");
        staticJPanel.add(addressDelivery);

        staticJPanel.add(new JLabel("Мобильный телефон:"));
        JFormattedTextField numberPhoneClient = new JFormattedTextField(phoneFormatter);
        numberPhoneClient.setText(dataClient.getNumberPhoneClient());
        numberPhoneClient.setName("numberPhoneClient");
        staticJPanel.add(numberPhoneClient);

        JButton save = new JButton("Сохранить");
        save.setName("saveDataAboutClient");
        save.addActionListener(controller);
        staticJPanel.add(save);

        JButton backSelectClient = new JButton("Назад");
        backSelectClient.setName("backSelectClient");
        backSelectClient.addActionListener(controller);
        staticJPanel.add(backSelectClient);

        JButton mainPage = new JButton("Главная страница");
        mainPage.setName("mainPage");
        mainPage.addActionListener(controller);
        staticJPanel.add(mainPage);

        staticJPanel.revalidate();
        staticJPanel.repaint();

    }
    public void listClientsAndSelect(String[] list,String s) {
        staticJPanel.removeAll();
        staticJPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        JLabel jl = new JLabel("Поиск клиента ");
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        staticJPanel.add(jl,gridBagConstraints);

        JTextField searchClient = new JTextField();
        searchClient.setText(s);
        searchClient.setName("searchClient");
        gridBagConstraints.gridx = 1;
        gridBagConstraints.ipadx = 275;
        searchClient.addKeyListener(controller);
        staticJPanel.add(searchClient,gridBagConstraints);

        JButton searchClientButton = new JButton("Найти");
        searchClientButton.setName("searchClientButton");
        searchClientButton.addActionListener(controller);
        gridBagConstraints.gridx     = 2;
        gridBagConstraints.ipadx     = 20;
        staticJPanel.add(searchClientButton, gridBagConstraints);

        if(list==null){
            list = new String[1];
            list[0] = "Нет клиентов";
        }
        JList<String> jList = new JList<>(list);
        jList.setName("jListClients");
        jList.addMouseListener(controller);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gridBagConstraints.ipady     = 450;
        gridBagConstraints.weightx   = 0.0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridx     = 0;
        gridBagConstraints.gridy     = 1;
        JScrollPane ListClients = new JScrollPane(jList);
        ListClients.setName("ListClients");
        staticJPanel.add(ListClients,gridBagConstraints);


        JButton mainPage = new JButton("Главная страница");
        mainPage.setName("mainPage");
        mainPage.addActionListener(controller);
        gridBagConstraints.ipady     = 0;
        gridBagConstraints.gridx     = 0;
        gridBagConstraints.gridy     = 3;
        gridBagConstraints.gridwidth = 3;
        staticJPanel.add(mainPage, gridBagConstraints);

        JButton pushSelectClient = new JButton("Выбрать");
        pushSelectClient.setName("pushSelectClient");
        pushSelectClient.addActionListener(controller);
        gridBagConstraints.gridx     = 0;
        gridBagConstraints.gridy     = 2;
        gridBagConstraints.gridwidth = 3;
        staticJPanel.add(pushSelectClient,gridBagConstraints);

        staticJPanel.revalidate();
        staticJPanel.repaint();
        searchClient.requestFocusInWindow();
    }
    public void selectedClient(DataClient dataClient){
        staticJPanel.removeAll();
        staticJPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 0.1;

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        JLabel j1 = new JLabel("Клиент: ");
        j1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j1,gridBagConstraints);

        gridBagConstraints.ipadx = 40;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        JLabel j2 = new JLabel(dataClient.getFullNameClient());
        j2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j2,gridBagConstraints);

        gridBagConstraints.ipadx = 50;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        JLabel j4 = new JLabel("Номер договора: ");
        j4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j4,gridBagConstraints);

        gridBagConstraints.ipadx = 40;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        JLabel j3 = new JLabel(dataClient.getNumberContract());
        j3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j3,gridBagConstraints);

        gridBagConstraints.gridy = 2;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);
        gridBagConstraints.gridy = 3;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);

        gridBagConstraints.ipadx = 50;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        staticJPanel.add(new JLabel("Базовый договор"),gridBagConstraints);

        gridBagConstraints.ipadx = 40;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 1;
        staticJPanel.add(new JLabel("Сумма: " +dataClient.getBasicContract().getAllSumInEUR()),gridBagConstraints);

        gridBagConstraints.ipadx = 70;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 2;
        JButton editBasicContract = new JButton("Изменить");
        editBasicContract.addActionListener(controller);
        editBasicContract.setName("editBasicContract");
        staticJPanel.add(editBasicContract,gridBagConstraints);

        gridBagConstraints.ipadx = 50;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        staticJPanel.add(new JLabel("Договор UpSale"),gridBagConstraints);

        gridBagConstraints.ipadx = 40;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridx = 1;
        staticJPanel.add(new JLabel("Сумма: " +dataClient.getUpSaleContract().getAllSumUpSaleInBYN()),gridBagConstraints);

        gridBagConstraints.ipadx = 70;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridx = 2;
        JButton editUpSaleContract = new JButton("Изменить");
        editUpSaleContract.setName("editUpSaleContract");
        editUpSaleContract.addActionListener(controller);
        staticJPanel.add(editUpSaleContract,gridBagConstraints);

        gridBagConstraints.ipadx = 50;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        staticJPanel.add(new JLabel("Доп соглашение БД"),gridBagConstraints);

        gridBagConstraints.ipadx = 40;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridx = 1;
        staticJPanel.add(new JLabel("Новая сумма: "+dataClient.getSupplementaryAgreementBasicContract().getAllSumInEURSupplementaryAgreement()),
                gridBagConstraints);

        gridBagConstraints.ipadx = 70;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridx = 2;
        JButton editSupplementaryAgreementBasicContract = new JButton("Изменить");
        editSupplementaryAgreementBasicContract.setName("editSupplementaryAgreementBasicContract");
        editSupplementaryAgreementBasicContract.addActionListener(controller);
        staticJPanel.add(editSupplementaryAgreementBasicContract,gridBagConstraints);

        gridBagConstraints.ipadx = 50;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        staticJPanel.add(new JLabel("Доп соглашение UpSale"),gridBagConstraints);

        gridBagConstraints.ipadx = 40;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridx = 1;
        staticJPanel.add(new JLabel("Новая сумма: "+dataClient.getSupplementaryAgreementUpSaleContract().getAllSumUpSaleInBYNSupplementaryAgreement()),
                gridBagConstraints);

        gridBagConstraints.ipadx = 70;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridx = 2;
        JButton editSupplementaryAgreementUpSale = new JButton("Изменить");
        editSupplementaryAgreementUpSale.addActionListener(controller);
        editSupplementaryAgreementUpSale.setName("editSupplementaryAgreementUpSale");
        staticJPanel.add(editSupplementaryAgreementUpSale,gridBagConstraints);

        gridBagConstraints.ipadx = 50;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        staticJPanel.add(new JLabel("Счет-фактура"),gridBagConstraints);

        gridBagConstraints.ipadx = 40;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridx = 1;
        staticJPanel.add(new JLabel("Сумма: "+dataClient.getInvoiceDocument().getPriceBYNInvoiceDocument()),gridBagConstraints);

        gridBagConstraints.ipadx = 70;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridx = 2;
        JButton editInvoiceDocument = new JButton("Изменить");
        editInvoiceDocument.addActionListener(controller);
        editInvoiceDocument.setName("editInvoiceDocument");
        staticJPanel.add(editInvoiceDocument,gridBagConstraints);

        gridBagConstraints.ipadx = 50;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        staticJPanel.add(new JLabel("Данные о клиенте"),gridBagConstraints);

        gridBagConstraints.ipadx = 40;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridx = 2;
        JButton editDataAboutClient = new JButton("Изменить");
        editDataAboutClient.addActionListener(controller);
        editDataAboutClient.setName("editDataAboutClient");
        staticJPanel.add(editDataAboutClient,gridBagConstraints);

        gridBagConstraints.ipadx = 50;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        staticJPanel.add(new JLabel("Просто строчка"),gridBagConstraints);

        gridBagConstraints.ipadx = 40;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridx = 1;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);

        gridBagConstraints.ipadx = 40;
        gridBagConstraints.ipady = 220;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridx = 1;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);

        JButton selectClient = new JButton("Вернутся к выбору клиента");
        selectClient.setName("selectClient");
        selectClient.addActionListener(controller);
        gridBagConstraints.ipady = 0;
        gridBagConstraints.gridy     = 12;
        gridBagConstraints.gridx    = 0;
        gridBagConstraints.gridwidth = 3;
        staticJPanel.add(selectClient,gridBagConstraints);

        JButton mainPage = new JButton("Главная страница");
        mainPage.setName("mainPage");
        mainPage.addActionListener(controller);
        gridBagConstraints.gridy     = 13;
        gridBagConstraints.gridx     = 0;
        gridBagConstraints.gridwidth = 3;
        staticJPanel.add(mainPage, gridBagConstraints);

        staticJPanel.revalidate();
        staticJPanel.repaint();


    }
    //Форма для заполнения данных о договоре
    public void displayBasicContract(DataClient dataClient, double currencyValue){
        ViewBasicContract vid = new ViewBasicContract(staticJPanel,dataClient,currencyValue,controller);
        vid.display();
        controller.setDisplay(vid);

    }
    public void displaySupplementaryAgreementBasicContract(DataClient dataClient, double currencyValue){
        ViewSupplementaryAgreementBasicContract vid = new ViewSupplementaryAgreementBasicContract(staticJPanel,dataClient,currencyValue,controller);
        vid.display();
        controller.setDisplay(vid);

    }
    public void displayUpSaleContract(DataClient dataClient){
        ViewUpSaleContract vid = new ViewUpSaleContract(staticJPanel,dataClient,controller);
        vid.display();
        controller.setDisplay(vid);

    }
    public void displaySupplementaryAgreementUpSale(DataClient dataClient){
        ViewSupplementaryAgreementUpSale vid = new ViewSupplementaryAgreementUpSale(staticJPanel,dataClient,controller);
        vid.display();
        controller.setDisplay(vid);

    }
    public void displayInvoiceDocument(DataClient dataClient, double currencyValue){
        ViewInvoiceDocument vid = new ViewInvoiceDocument(staticJPanel,dataClient,currencyValue,controller);
        vid.display();
        controller.setDisplay(vid);

    }
    //Общие методы которые работают везде
    public void refreshCheckBox(JCheckBox checkBox){
        String obj = checkBox.getName().replace("checkBox","");
        String objWithSmallLitter = Character.toLowerCase(obj.charAt(0)) + obj.substring(1);
        for(Component component :staticJPanel.getComponents()){
            try {
                if(component.getName().equals(objWithSmallLitter)){
                    component.setEnabled(checkBox.isSelected());
                    break;
                }
            }catch (Exception e){}
        }
        staticJPanel.revalidate();
        staticJPanel.repaint();

    }
    public void onlyNumber(JTextField jTextField){
        jTextField.setText(jTextField.getText().replaceAll("[^0-9]",""));

    }
    public void onlyDoubleNumber(JTextField jTextField,int howManySymbolAfterDot){
        if(!jTextField.getText().matches("[\\d]{0,6}[\\.]?[\\d]{0,"+howManySymbolAfterDot+"}")){
            String result = "";
            String[] forTest = null;
            result = jTextField.getText().replace(",",".");
            forTest = result.split("\\.");
            result = "";
            for (int j = 0; j < forTest.length ; j++) {
                forTest[j] = forTest[j].replaceAll("[^\\d]","");
                result = result + forTest[j];
                if(j==0){
                    result = result+".";
                }
                if (j==1){
                    break;
                }
            }
            try {
                result=result.substring(0,result.indexOf(".")+1+howManySymbolAfterDot);
            } catch (Exception e){

            }
            jTextField.setText(result);
        }
    }
    public void selectPathForSaveContract(){
        JFileChooser chooser = new JFileChooser();
        for(Component component :staticJPanel.getComponents()){
            try {
                if(component.getName().equals("pathForSaveContact")){
                    chooser.setCurrentDirectory(new File(((JTextField) component).getText())); ;
                }
            } catch (Exception e){}
        }
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);// должна открываться папка которая выбрана.
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            for(Component component : staticJPanel.getComponents()){
                try {
                    if(component.getName().equals("pathForSaveContact")){
                        ((JTextField)component).setText(chooser.getSelectedFile().getAbsolutePath());
                    }
                }catch (Exception e){}

            }
        }
    }
    public void writeJustMessage(String message,int messageType) {
        JOptionPane.showMessageDialog(this, message, "Сообщение", messageType);
    }
}
