package org.sydnik.createContract;


import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.data.SalesManager;
import org.sydnik.createContract.view.ViewInvoiceDocument;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

public class MyView extends JFrame {
    private MyController controller;
    private JPanel staticJPanel;

    private JTextField allSumInEUR;
    private JTextField payUpTo100PercentSum;
    private JTextField payUpTo50PercentSum;
    private JTextField prepaymentOr10PercentSum;
    private JTextField allSumInBYN;
    private JTextField additionalProductsWithDiscount[];

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
        MaskFormatter currencyMark =null;
        try {
            currencyMark = new MaskFormatter("#.####");
        }
        catch (Exception e){}

        this.add(staticJPanel);
        staticJPanel.add(new JLabel("Не могу узнать курс евро придется вводить в ручную:("));
        JFormattedTextField valueCurrency = new JFormattedTextField(currencyMark);
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
    //Страница настройки для менеджера(Доверенность, от какого действует, И ФИО)
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
    //BasicContract
    public void editBasicContract(DataClient dataClient,double currencyValue){
        MaskFormatter
                dateCreateContractMask = null,
                currencyMark =null;
        try {
            dateCreateContractMask = new MaskFormatter("##.##.####");
            currencyMark = new MaskFormatter("#.####");
        }
        catch (Exception e){}

        staticJPanel.removeAll();
        staticJPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 0.1;

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        JLabel j1 = new JLabel("Клиент: ");
        j1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j1,gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        JLabel j2 = new JLabel(dataClient.getFullNameClient());
        j2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j2,gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        JLabel j4 = new JLabel("Базовый договор");
        j4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j4,gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        JLabel j3 = new JLabel(dataClient.getNumberContract()+" " + dataClient.getStrangeName());
        j3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j3,gridBagConstraints);

        gridBagConstraints.gridy = 2;
        JLabel jName = new JLabel(" ");
        staticJPanel.add(jName,gridBagConstraints);
        gridBagConstraints.gridy = 3;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);

        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.gridwidth = 1;
        staticJPanel.add(new JLabel("Курс евро:"),gridBagConstraints);

        JFormattedTextField currency = new JFormattedTextField(currencyMark);
        currency.setText(String.format(Locale.US,"%.4f",currencyValue));
        currency.setName("currency");
        currency.setEnabled(false);
        currency.addKeyListener(controller);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.ipadx = 290;
        staticJPanel.add(currency,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.ipadx = 20;
        JCheckBox checkBoxCurrency  = new JCheckBox();
        checkBoxCurrency.setSelected(false);
        checkBoxCurrency.setName("checkBoxCurrency");
        checkBoxCurrency.addActionListener(controller);
        staticJPanel.add(checkBoxCurrency,gridBagConstraints);

        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 0;
        staticJPanel.add(new JLabel("Сумма договора"),gridBagConstraints);
        allSumInEUR = new JTextField();
        allSumInEUR.setName("allSumInEUR");
        if(dataClient.getBasicContract().getAllSumInEUR()!=0){
            allSumInEUR.setText(String.valueOf(dataClient.getBasicContract().getAllSumInEUR()));
        }
        allSumInEUR.addKeyListener(controller);
        gridBagConstraints.gridx = 1;
        staticJPanel.add(allSumInEUR,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxAllSumInEUR  = new JCheckBox();
        checkBoxAllSumInEUR.setName("checkBoxAllSumInEUR");
        checkBoxAllSumInEUR.addActionListener(controller);
        staticJPanel.add(checkBoxAllSumInEUR,gridBagConstraints);
        if(dataClient.getBasicContract().getAllSumInEUR()!=0){allSumInEUR.setEnabled(false); }
        else { checkBoxAllSumInEUR.setSelected(true); }

        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridx = 0;
        staticJPanel.add(new JLabel("Срок исполнения"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        JComboBox<String> periodOfExecution = new JComboBox(new String[]{"14 до 25","21 до 29"});
        periodOfExecution.setName("timeProduction");
        if (dataClient.getBasicContract().getTimeProduction() == null){
            periodOfExecution.setSelectedIndex(0);
        }
        else if(dataClient.getBasicContract().getTimeProduction().equals("21 до 29")){
            periodOfExecution.setSelectedIndex(1);
        }
        else {
            periodOfExecution.setSelectedIndex(0);
        }
        staticJPanel.add(periodOfExecution,gridBagConstraints);

        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridx = 0;
        staticJPanel.add(new JLabel("Предоплата:"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        prepaymentOr10PercentSum = new JTextField();
        prepaymentOr10PercentSum.setName("prepaymentOr10PercentSum");
        prepaymentOr10PercentSum.setText(String.valueOf(dataClient.getBasicContract().getPrepaymentOr10PercentSum()));
        prepaymentOr10PercentSum.addKeyListener(controller);
        prepaymentOr10PercentSum.setEnabled(false);
        staticJPanel.add(prepaymentOr10PercentSum,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxPrepaymentOr10PercentSum  = new JCheckBox();
        checkBoxPrepaymentOr10PercentSum.setName("checkBoxPrepaymentOr10PercentSum");
        checkBoxPrepaymentOr10PercentSum.addActionListener(controller);
        staticJPanel.add(checkBoxPrepaymentOr10PercentSum,gridBagConstraints);

        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridx = 0;
        staticJPanel.add(new JLabel("Оплата до 50%"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        payUpTo50PercentSum = new JTextField();
        payUpTo50PercentSum.setName("payUpTo50PercentSum");
        payUpTo50PercentSum.setText(String.valueOf(dataClient.getBasicContract().getPayUpTo50PercentSum()));
        payUpTo50PercentSum.addKeyListener(controller);
        payUpTo50PercentSum.setEnabled(false);
        staticJPanel.add(payUpTo50PercentSum,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxPayUpTo50PercentSum  = new JCheckBox();
        checkBoxPayUpTo50PercentSum.setName("checkBoxPayUpTo50PercentSum");
        checkBoxPayUpTo50PercentSum.addActionListener(controller);
        staticJPanel.add(checkBoxPayUpTo50PercentSum,gridBagConstraints);

        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridx = 0;
        staticJPanel.add(new JLabel("Оплата до 100%"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        payUpTo100PercentSum = new JTextField();
        payUpTo100PercentSum.setName("payUpTo100PercentSum");
        payUpTo100PercentSum.setText(String.valueOf(dataClient.getBasicContract().getPayUpTo100PercentSum()));
        payUpTo100PercentSum.addKeyListener(controller);
        payUpTo100PercentSum.setEnabled(false);
        staticJPanel.add(payUpTo100PercentSum,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxPayUpTo100PercentSum  = new JCheckBox();
        checkBoxPayUpTo100PercentSum.setName("checkBoxPayUpTo100PercentSum");
        checkBoxPayUpTo100PercentSum.addActionListener(controller);
        staticJPanel.add(checkBoxPayUpTo100PercentSum,gridBagConstraints);

        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridx = 0;
        staticJPanel.add(new JLabel("Сумма в белках"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        allSumInBYN = new JTextField();
        allSumInBYN.setName("allSumInBYN");
        allSumInBYN.setText(String.valueOf(dataClient.getBasicContract().getAllSumInBYN()));
        allSumInBYN.addKeyListener(controller);
        allSumInBYN.setEnabled(false);
        staticJPanel.add(allSumInBYN,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxAllSumInBYN  = new JCheckBox();
        checkBoxAllSumInBYN.setName("checkBoxAllSumInBYN");
        checkBoxAllSumInBYN.addActionListener(controller);
        staticJPanel.add(checkBoxAllSumInBYN,gridBagConstraints);

        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridx = 0;
        staticJPanel.add(new JLabel("Дата подписания"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        JFormattedTextField dateCreateContract = new JFormattedTextField(dateCreateContractMask);
        dateCreateContract.setName("dateCreateContract");
        dateCreateContract.setText(String.valueOf(dataClient.getBasicContract().getDateCreateContract()));
        dateCreateContract.addKeyListener(controller);
        dateCreateContract.setEnabled(false);
        staticJPanel.add(dateCreateContract,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxDateCreateContract  = new JCheckBox();
        checkBoxDateCreateContract.setName("checkBoxDateCreateContract");
        checkBoxDateCreateContract.addActionListener(controller);
        staticJPanel.add(checkBoxDateCreateContract,gridBagConstraints);

        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridx = 0;
        JButton saveDataBaseContractClient = new JButton("Сохранить");
        saveDataBaseContractClient.setName("saveDataBaseContractClient");
        saveDataBaseContractClient.addActionListener(controller);
        staticJPanel.add(saveDataBaseContractClient,gridBagConstraints);

        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        JButton printBaseContract = new JButton("Распечатать 2 раза");
        printBaseContract.setName("printBaseContract");
        printBaseContract.addActionListener(controller);
        staticJPanel.add(printBaseContract,gridBagConstraints);

        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        JButton openDirectoryWithFile = new JButton("Открыть папку с файлом");
        openDirectoryWithFile.setName("openDirectoryWithFile");
        openDirectoryWithFile.addActionListener(controller);
        staticJPanel.add(openDirectoryWithFile,gridBagConstraints);

        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        JButton openFileBasicContract = new JButton("Открыть файл");
        openFileBasicContract.setName("openFileBasicContract");
        openFileBasicContract.addActionListener(controller);
        staticJPanel.add(openFileBasicContract,gridBagConstraints);

        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipady = 170;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);

        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.gridwidth =3;
        JButton backSelectClient = new JButton("Назад");
        backSelectClient.setName("backSelectClient");
        backSelectClient.addActionListener(controller);
        staticJPanel.add(backSelectClient,gridBagConstraints);

        gridBagConstraints.gridy = 19;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        JButton mainPage = new JButton("Главная страница");
        mainPage.setName("mainPage");
        mainPage.addActionListener(controller);
        staticJPanel.add(mainPage,gridBagConstraints);

        staticJPanel.revalidate();
        staticJPanel.repaint();
        if(dataClient.getBasicContract().getAllSumInEUR()==0){
            allSumInEUR.requestFocusInWindow();
        }
    }
    public void editBasicContractEditAllSumInEUR(int allSum){
        double currency = 0;
        for(Component component : staticJPanel.getComponents()){
            try {
                if(component.getName().equals("currency")){
                    currency = Double.parseDouble(((JTextField)component).getText());
                    break;
                }
            } catch (Exception e){}

        }
        int prepayment10 = allSum/10,
                percent50 = allSum/2-prepayment10,
                percent100 = allSum-prepayment10-percent50;
        allSumInEUR.setText(String.valueOf(allSum));
        prepaymentOr10PercentSum.setText(String.valueOf(prepayment10));
        payUpTo50PercentSum.setText(String.valueOf(percent50));
        payUpTo100PercentSum.setText(String.valueOf(percent100));
        allSumInBYN.setText(String.valueOf(Math.round(((double) allSum)*currency)));
        staticJPanel.revalidate();
        staticJPanel.repaint();
    }
    public void editBasicContractEditPrepaymentOr10PercentSum(int allSum,int prepayment10){
        int percent50 = allSum/2-prepayment10;
        if (percent50<0){
            percent50 =0;
        }
        int percent100 = allSum-prepayment10-percent50;
        allSumInEUR.setText(String.valueOf(allSum));
        prepaymentOr10PercentSum.setText(String.valueOf(prepayment10));
        payUpTo50PercentSum.setText(String.valueOf(percent50));
        payUpTo100PercentSum.setText(String.valueOf(percent100));
        staticJPanel.revalidate();
        staticJPanel.repaint();
    }
    public void editBasicContractEditPayUpTo50PercentSum(int allSum,int prepayment10, int percent50){
        int percent100 = allSum-prepayment10-percent50;
        allSumInEUR.setText(String.valueOf(allSum));
        prepaymentOr10PercentSum.setText(String.valueOf(prepayment10));
        payUpTo50PercentSum.setText(String.valueOf(percent50));
        payUpTo100PercentSum.setText(String.valueOf(percent100));
        staticJPanel.revalidate();
        staticJPanel.repaint();
    }
    public void editCurrencyBasicContract(JTextField jTextField){
        allSumInBYN.setText(String.valueOf(Math.round(( Double.parseDouble(allSumInEUR.getText()))*Double.parseDouble(jTextField.getText()))));
        staticJPanel.revalidate();
        staticJPanel.repaint();

    }

    //UpSale
    public void editUpSaleContract (DataClient dataClient){
        MaskFormatter
                dateCreateContractMask = null;
        try {
            dateCreateContractMask = new MaskFormatter("##.##.####");
        }
        catch (Exception e){}

        staticJPanel.removeAll();
        staticJPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        JLabel j1 = new JLabel("Клиент: ");
        j1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j1,gridBagConstraints);

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridwidth = 5;
        JLabel j2 = new JLabel(dataClient.getFullNameClient());
        j2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j2,gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        JLabel j4 = new JLabel("UpSale договор ");
        j4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j4,gridBagConstraints);

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridwidth = 5;
        JLabel j3 = new JLabel(dataClient.getNumberContract()+" " + dataClient.getStrangeName());
        j3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j3,gridBagConstraints);

        gridBagConstraints.gridy = 2;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);
        gridBagConstraints.gridy = 3;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 0;
        staticJPanel.add(new JLabel("№"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 3;
        staticJPanel.add(new JLabel(" Наименование товара"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        staticJPanel.add(new JLabel("К"),gridBagConstraints);
        gridBagConstraints.gridx = 5;
        staticJPanel.add(new JLabel("Цена"),gridBagConstraints);
        gridBagConstraints.gridx = 6;
        staticJPanel.add(new JLabel("%"),gridBagConstraints);
        gridBagConstraints.gridx = 7;
        staticJPanel.add(new JLabel("Всего"),gridBagConstraints);
        JTextField additionalProducts[] = new JTextField[6];
        JTextField additionalProductsCount[] = new JTextField[6];
        JTextField additionalProductsDiscount[] = new JTextField[6];
        JTextField additionalProductsFullPrice[] = new JTextField[6];
        additionalProductsWithDiscount = new JTextField[6];
        for (int i = 0; i < 6; i++) {
                additionalProducts[i] = new JTextField();
                additionalProductsCount[i] = new JTextField();
                additionalProductsDiscount[i] = new JTextField();
                additionalProductsFullPrice[i] = new JTextField();
                additionalProductsWithDiscount[i] = new JTextField();
                gridBagConstraints.gridy = 5+i;
                gridBagConstraints.gridx = 0;
                gridBagConstraints.ipadx = 5;
                gridBagConstraints.gridwidth = 1;
                staticJPanel.add(new JLabel(String.valueOf(i+1)),gridBagConstraints);
                gridBagConstraints.gridx = 1;
                gridBagConstraints.ipadx = 310;
                gridBagConstraints.gridwidth = 3;
                additionalProducts[i].setName("additionalProducts"+i);
                staticJPanel.add(additionalProducts[i],gridBagConstraints);
                gridBagConstraints.gridx = 4;
                gridBagConstraints.ipadx = 10;
                gridBagConstraints.gridwidth = 1;
                additionalProductsCount[i].setText(String.valueOf(1));
                additionalProductsCount[i].setName("additionalProductsCount"+i);
                additionalProductsCount[i].addKeyListener(controller);
                staticJPanel.add(additionalProductsCount[i],gridBagConstraints);
                gridBagConstraints.gridx = 5;
                gridBagConstraints.ipadx = 50;
                additionalProductsDiscount[i].setText(String.valueOf(0));
                additionalProductsFullPrice[i].setName("additionalProductsFullPrice"+i);
                additionalProductsFullPrice[i].addKeyListener(controller);
                staticJPanel.add(additionalProductsFullPrice[i],gridBagConstraints);
                gridBagConstraints.gridx = 6;
                gridBagConstraints.ipadx = 15;
                additionalProductsDiscount[i].setName("additionalProductsDiscount"+i);
                additionalProductsDiscount[i].addKeyListener(controller);
                staticJPanel.add(additionalProductsDiscount[i],gridBagConstraints);
                gridBagConstraints.gridx = 7;
                gridBagConstraints.ipadx = 50;
                additionalProductsWithDiscount[i].setName("additionalProductsWithDiscount"+i);
                additionalProductsWithDiscount[i].addKeyListener(controller);
                staticJPanel.add(additionalProductsWithDiscount[i],gridBagConstraints);
            try {
                if(dataClient.getUpSaleContract().getListAdditionalProducts()[i]!=null){
                    additionalProducts[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getName());
                    additionalProductsCount[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getCount());
                    additionalProductsDiscount[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getDiscount());
                    additionalProductsFullPrice[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getFullPrice());
                    additionalProductsWithDiscount[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getPriceWithDiscount());
                }
            } catch (NullPointerException e){

            }




        }
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 0;
        staticJPanel.add(new JLabel("Сумма договора"),gridBagConstraints);


        JTextField allSumUpSaleInBYN;
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        allSumUpSaleInBYN = new JTextField();
        allSumUpSaleInBYN.setName("allSumUpSaleInBYN");
        if(dataClient.getUpSaleContract().getAllSumUpSaleInBYN()!=0){
            allSumUpSaleInBYN.setText(String.valueOf(dataClient.getUpSaleContract().getAllSumUpSaleInBYN()));
        }
        allSumUpSaleInBYN.addKeyListener(controller);
        allSumUpSaleInBYN.setEnabled(false);
        staticJPanel.add(allSumUpSaleInBYN,gridBagConstraints);

        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxAllSumUpSaleInBYN = new JCheckBox();
        checkBoxAllSumUpSaleInBYN.setName("checkBoxAllSumUpSaleInBYN");
        checkBoxAllSumUpSaleInBYN.addActionListener(controller);
        staticJPanel.add(checkBoxAllSumUpSaleInBYN,gridBagConstraints);


        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 0;
        staticJPanel.add(new JLabel("Предоплата:"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth =1;
        JCheckBox checkBoxPrepaymentUpSale  = new JCheckBox();
        checkBoxPrepaymentUpSale.setName("checkBoxPrepaymentUpSale");
        checkBoxPrepaymentUpSale.addActionListener(controller);
        staticJPanel.add(checkBoxPrepaymentUpSale,gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        JTextField prepaymentUpSale = new JTextField();
        prepaymentUpSale.setName("prepaymentUpSale");
        prepaymentUpSale.setText(String.valueOf(dataClient.getUpSaleContract().getPrepaymentUpSale()));
        prepaymentUpSale.addKeyListener(controller);
        prepaymentUpSale.setEnabled(false);
        staticJPanel.add(prepaymentUpSale,gridBagConstraints);

        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        staticJPanel.add(new JLabel("Оплата до 100%"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxPayUpTo100percentUpSale  = new JCheckBox();
        checkBoxPayUpTo100percentUpSale.setName("checkBoxPayUpTo100percentUpSale");
        checkBoxPayUpTo100percentUpSale.addActionListener(controller);
        staticJPanel.add(checkBoxPayUpTo100percentUpSale,gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        JTextField payUpTo100percentUpSale = new JTextField();
        payUpTo100percentUpSale.setName("payUpTo100percentUpSale");
        payUpTo100percentUpSale.setText(String.valueOf(dataClient.getUpSaleContract().getPayUpTo100percentUpSale()));
        payUpTo100percentUpSale.addKeyListener(controller);
        payUpTo100percentUpSale.setEnabled(false);
        staticJPanel.add(payUpTo100percentUpSale,gridBagConstraints);

        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        staticJPanel.add(new JLabel("Дата подписания"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxDateCreateUpSaleContract  = new JCheckBox();
        checkBoxDateCreateUpSaleContract.setName("checkBoxDateCreateUpSaleContract");
        checkBoxDateCreateUpSaleContract.addActionListener(controller);
        staticJPanel.add(checkBoxDateCreateUpSaleContract,gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        JFormattedTextField dateCreateUpSaleContract = new JFormattedTextField(dateCreateContractMask);
        dateCreateUpSaleContract.setName("dateCreateUpSaleContract");
        dateCreateUpSaleContract.setText(String.valueOf(dataClient.getUpSaleContract().getDateCreateUpSaleContract()));
        dateCreateUpSaleContract.addKeyListener(controller);
        dateCreateUpSaleContract.setEnabled(false);
        staticJPanel.add(dateCreateUpSaleContract,gridBagConstraints);

        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        JButton saveDataUpSaleContact = new JButton("Сохранить");
        saveDataUpSaleContact.setName("saveDataUpSaleContact");
        saveDataUpSaleContact.addActionListener(controller);
        staticJPanel.add(saveDataUpSaleContact,gridBagConstraints);

        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 4;
        JButton printUpSaleContract = new JButton("Распечатать 2 раза");
        printUpSaleContract.setName("printUpSaleContract");
        printUpSaleContract.addActionListener(controller);
        staticJPanel.add(printUpSaleContract,gridBagConstraints);

        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        JButton openDirectoryWithFile = new JButton("Открыть папку с файлом");
        openDirectoryWithFile.setName("openDirectoryWithFile");
        openDirectoryWithFile.addActionListener(controller);
        staticJPanel.add(openDirectoryWithFile,gridBagConstraints);

        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 4;
        JButton openFileUpSaleContract = new JButton("Открыть файл");
        openFileUpSaleContract.setName("openFileUpSaleContract");
        openFileUpSaleContract.addActionListener(controller);
        staticJPanel.add(openFileUpSaleContract,gridBagConstraints);

        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipady = 120;
        gridBagConstraints.gridwidth =8;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);

        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipady = 0;
        JButton backSelectClient = new JButton("Назад");
        backSelectClient.setName("backSelectClient");
        backSelectClient.addActionListener(controller);
        staticJPanel.add(backSelectClient,gridBagConstraints);

        gridBagConstraints.gridy = 19;
        gridBagConstraints.gridx = 0;
        JButton mainPage = new JButton("Главная страница");
        mainPage.setName("mainPage");
        mainPage.addActionListener(controller);
        staticJPanel.add(mainPage,gridBagConstraints);

        staticJPanel.revalidate();
        staticJPanel.repaint();

    }
    public void editUpSaleEditSumProductWithDiscount(JTextField jTextField){
        String count = jTextField.getName().substring(jTextField.getName().length()-1);
        int jCount = 0;
        int jDiscount = 0;
        int jPrice = 0;
        JTextField jPriceWitchDiscount = null;

        for(Component component :staticJPanel.getComponents()){
            try {
                if(component.getName().equals("additionalProductsCount"+count)){
                    jCount = Integer.parseInt(((JTextField) component).getText());
                }
                else if (component.getName().equals("additionalProductsDiscount"+count)){
                    jDiscount = Integer.parseInt(((JTextField) component).getText());
                }
                else if (component.getName().equals("additionalProductsFullPrice"+count)){
                    jPrice = Integer.parseInt(((JTextField) component).getText());
                }
                else if (component.getName().equals("additionalProductsWithDiscount"+count)){
                    jPriceWitchDiscount = (JTextField) component;
                }
            }catch (Exception e){}
        }

        jPriceWitchDiscount.setText(String.valueOf(((int) Math.round((double) jPrice*((double) (100-jDiscount)/100)))*jCount));
        staticJPanel.revalidate();
        staticJPanel.repaint();
        editUpSaleEditAllSum();


    }
    public void editUpSaleEditAllSum (){
        int allSum = 0;
        for(int i =0;i<additionalProductsWithDiscount.length;i++){
            try {
                allSum = allSum + Integer.parseInt(additionalProductsWithDiscount[i].getText());

            }catch (NumberFormatException e){

            }
        }
        for(Component component :getComponentsStaticPanel()){
            try {
                if(component.getName().equals("allSumUpSaleInBYN")){
                    ((JTextField)component).setText(String.valueOf(allSum));
                    editUpSaleEditPrepaymentUpSale(allSum);
                    break;
                }
            }catch (Exception e){}
        }
        staticJPanel.repaint();

    }
    public void editUpSaleEditPrepaymentUpSale (int allSum){
        int intPrepaymentUpSale = (int) Math.round(((double)allSum)/10);
        for(Component component :getComponentsStaticPanel()){
            try {
                if(component.getName().equals("prepaymentUpSale")){
                    ((JTextField)component).setText(String.valueOf(intPrepaymentUpSale));
                    break;
                }
            }catch (Exception e){}
        }
        staticJPanel.revalidate();
        staticJPanel.repaint();
        editUpSaleEditPayUpTo100percentUpSale(intPrepaymentUpSale);
    }
    public void editUpSaleEditPayUpTo100percentUpSale (int prepayment){
        int allSum = 0;
        for(Component component :getComponentsStaticPanel()){
            try {
                if(component.getName().equals("allSumUpSaleInBYN")){
                  allSum = Integer.parseInt (((JTextField)component).getText());
                    break;
                }
            }catch (Exception e){}
        }
        for(Component component :getComponentsStaticPanel()){
            try {
                if(component.getName().equals("payUpTo100percentUpSale")){
                    ((JTextField)component).setText(String.valueOf(allSum-prepayment));
                    break;
                }
            }catch (Exception e){}
        }
        staticJPanel.revalidate();
        staticJPanel.repaint();

    }

    //SupplementaryAgreementBasicContract
    public void editSupplementaryAgreementBasicContract(DataClient dataClient,double currencyValue){
        MaskFormatter
                dateCreateContractMask = null,
                currencyMark =null;
        try {
            dateCreateContractMask = new MaskFormatter("##.##.####");
            currencyMark = new MaskFormatter("#.####");
        }
        catch (Exception e){}

        staticJPanel.removeAll();
        staticJPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 0.1;

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        JLabel j1 = new JLabel("Клиент: ");
        j1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j1,gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        JLabel j2 = new JLabel(dataClient.getFullNameClient());
        j2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j2,gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        JLabel j4 = new JLabel("Доп соглашение");
        j4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j4,gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        JLabel j3 = new JLabel(dataClient.getNumberContract()+" " + dataClient.getStrangeName());
        j3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j3,gridBagConstraints);

        gridBagConstraints.gridy = 2;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);
        gridBagConstraints.gridy = 3;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);

        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        staticJPanel.add(new JLabel("Дата Баз Договора"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        JFormattedTextField dateCreateContract = new JFormattedTextField(dateCreateContractMask);
        dateCreateContract.setName("dateCreateContract");
        dateCreateContract.setText(String.valueOf(dataClient.getBasicContract().getDateCreateContract()));
        dateCreateContract.addKeyListener(controller);
        dateCreateContract.setEnabled(false);
        staticJPanel.add(dateCreateContract,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxDateCreateContract  = new JCheckBox();
        checkBoxDateCreateContract.setName("checkBoxDateCreateContract");
        checkBoxDateCreateContract.setEnabled(false);
        checkBoxDateCreateContract.addActionListener(controller);
        staticJPanel.add(checkBoxDateCreateContract,gridBagConstraints);

        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.gridwidth = 1;
        staticJPanel.add(new JLabel("Курс евро:"),gridBagConstraints);

        JFormattedTextField currencySumSupplementaryAgreement = new JFormattedTextField(currencyMark);
        currencySumSupplementaryAgreement.setText(String.format(Locale.US,"%.4f",currencyValue));
        currencySumSupplementaryAgreement.setName("currencySumSupplementaryAgreement");
        currencySumSupplementaryAgreement.setEnabled(false);
        currencySumSupplementaryAgreement.addKeyListener(controller);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.ipadx = 300;
        staticJPanel.add(currencySumSupplementaryAgreement,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.ipadx = 20;
        JCheckBox checkBoxCurrencySumSupplementaryAgreement  = new JCheckBox();
        checkBoxCurrencySumSupplementaryAgreement.setSelected(false);
        checkBoxCurrencySumSupplementaryAgreement.setName("checkBoxCurrencySumSupplementaryAgreement");
        checkBoxCurrencySumSupplementaryAgreement.addActionListener(controller);
        staticJPanel.add(checkBoxCurrencySumSupplementaryAgreement,gridBagConstraints);

        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 0;
        staticJPanel.add(new JLabel("Новая сумма договора"),gridBagConstraints);
        JTextField allSumInEURSupplementaryAgreement = new JTextField();
        allSumInEURSupplementaryAgreement.setName("allSumInEURSupplementaryAgreement");
        if(dataClient.getBasicContract().getAllSumInEUR()!=0){
            allSumInEURSupplementaryAgreement.setText(String.valueOf(dataClient.getSupplementaryAgreementBasicContract().getAllSumInEURSupplementaryAgreement()));
        }
        allSumInEURSupplementaryAgreement.addKeyListener(controller);
        gridBagConstraints.gridx = 1;
        staticJPanel.add(allSumInEURSupplementaryAgreement,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxAllSumInEURSupplementaryAgreement  = new JCheckBox();
        checkBoxAllSumInEURSupplementaryAgreement.setName("checkBoxAllSumInEURSupplementaryAgreement");
        checkBoxAllSumInEURSupplementaryAgreement.addActionListener(controller);
        staticJPanel.add(checkBoxAllSumInEURSupplementaryAgreement,gridBagConstraints);
        if(dataClient.getSupplementaryAgreementBasicContract().getAllSumInEURSupplementaryAgreement()!=0){
            allSumInEURSupplementaryAgreement.setEnabled(false);
        }
        else {
            checkBoxAllSumInEURSupplementaryAgreement.setSelected(true);
        }


        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridx = 0;
        staticJPanel.add(new JLabel("Предоплата:"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        JTextField prepaymentOr10PercentSumSupplementaryAgreement = new JTextField();
        prepaymentOr10PercentSumSupplementaryAgreement.setName("prepaymentOr10PercentSumSupplementaryAgreement");
        if(dataClient.getSupplementaryAgreementBasicContract().getPrepaymentOr10PercentSumSupplementaryAgreement()!=0) {
            prepaymentOr10PercentSumSupplementaryAgreement.setText(String.valueOf(dataClient.getSupplementaryAgreementBasicContract().getPrepaymentOr10PercentSumSupplementaryAgreement()));
        } else {
            prepaymentOr10PercentSumSupplementaryAgreement.setText(String.valueOf(dataClient.getBasicContract().getPrepaymentOr10PercentSum()));

        }
        prepaymentOr10PercentSumSupplementaryAgreement.addKeyListener(controller);
        prepaymentOr10PercentSumSupplementaryAgreement.setEnabled(false);

        staticJPanel.add(prepaymentOr10PercentSumSupplementaryAgreement,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxPrepaymentOr10PercentSumSupplementaryAgreement  = new JCheckBox();
        checkBoxPrepaymentOr10PercentSumSupplementaryAgreement.setName("checkBoxPrepaymentOr10PercentSumSupplementaryAgreement");
        checkBoxPrepaymentOr10PercentSumSupplementaryAgreement.addActionListener(controller);
        staticJPanel.add(checkBoxPrepaymentOr10PercentSumSupplementaryAgreement,gridBagConstraints);

        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridx = 0;
        staticJPanel.add(new JLabel("Оплата до 50%"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        JTextField payUpTo50PercentSumSupplementaryAgreement = new JTextField();
        payUpTo50PercentSumSupplementaryAgreement.setName("payUpTo50PercentSumSupplementaryAgreement");
        payUpTo50PercentSumSupplementaryAgreement.setText(String.valueOf(dataClient.getSupplementaryAgreementBasicContract().getPayUpTo50PercentSumSupplementaryAgreement()));
        payUpTo50PercentSumSupplementaryAgreement.addKeyListener(controller);
        payUpTo50PercentSumSupplementaryAgreement.setEnabled(false);
        staticJPanel.add(payUpTo50PercentSumSupplementaryAgreement,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxPayUpTo50PercentSumSupplementaryAgreement  = new JCheckBox();
        checkBoxPayUpTo50PercentSumSupplementaryAgreement.setName("checkBoxPayUpTo50PercentSumSupplementaryAgreement");
        checkBoxPayUpTo50PercentSumSupplementaryAgreement.addActionListener(controller);
        staticJPanel.add(checkBoxPayUpTo50PercentSumSupplementaryAgreement,gridBagConstraints);

        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridx = 0;
        staticJPanel.add(new JLabel("Оплата до 100%"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        JTextField payUpTo100PercentSumSupplementaryAgreement = new JTextField();
        payUpTo100PercentSumSupplementaryAgreement.setName("payUpTo100PercentSumSupplementaryAgreement");
        payUpTo100PercentSumSupplementaryAgreement.setText(String.valueOf(dataClient.getSupplementaryAgreementBasicContract().getPayUpTo100PercentSumSupplementaryAgreement()));
        payUpTo100PercentSumSupplementaryAgreement.addKeyListener(controller);
        payUpTo100PercentSumSupplementaryAgreement.setEnabled(false);
        staticJPanel.add(payUpTo100PercentSumSupplementaryAgreement,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxPayUpTo100PercentSumSupplementaryAgreement  = new JCheckBox();
        checkBoxPayUpTo100PercentSumSupplementaryAgreement.setName("checkBoxPayUpTo100PercentSumSupplementaryAgreement");
        checkBoxPayUpTo100PercentSumSupplementaryAgreement.addActionListener(controller);
        staticJPanel.add(checkBoxPayUpTo100PercentSumSupplementaryAgreement,gridBagConstraints);

        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridx = 0;
        staticJPanel.add(new JLabel("Сумма в белках"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        JTextField allSumInBYNSupplementaryAgreement = new JTextField();
        allSumInBYNSupplementaryAgreement.setName("allSumInBYNSupplementaryAgreement");
        allSumInBYNSupplementaryAgreement.setText(String.valueOf(dataClient.getSupplementaryAgreementBasicContract().getAllSumInBYNSupplementaryAgreement()));
        allSumInBYNSupplementaryAgreement.addKeyListener(controller);
        allSumInBYNSupplementaryAgreement.setEnabled(false);
        staticJPanel.add(allSumInBYNSupplementaryAgreement,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxAllSumInBYNSupplementaryAgreement  = new JCheckBox();
        checkBoxAllSumInBYNSupplementaryAgreement.setName("checkBoxAllSumInBYNSupplementaryAgreement");
        checkBoxAllSumInBYNSupplementaryAgreement.addActionListener(controller);
        staticJPanel.add(checkBoxAllSumInBYNSupplementaryAgreement,gridBagConstraints);

        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridx = 0;
        staticJPanel.add(new JLabel("Дата подписания"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        JFormattedTextField dateCreateSupplementaryAgreementBasicContract = new JFormattedTextField(dateCreateContractMask);
        dateCreateSupplementaryAgreementBasicContract.setName("dateCreateSupplementaryAgreementBasicContract");
        dateCreateSupplementaryAgreementBasicContract.setText(String.valueOf(dataClient.getSupplementaryAgreementBasicContract().getDateCreateSupplementaryAgreementBasicContract()));
        System.out.println(String.valueOf(dataClient.getSupplementaryAgreementBasicContract().getDateCreateSupplementaryAgreementBasicContract()));
        dateCreateSupplementaryAgreementBasicContract.addKeyListener(controller);
        dateCreateSupplementaryAgreementBasicContract.setEnabled(false);
        staticJPanel.add(dateCreateSupplementaryAgreementBasicContract,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxDateCreateSupplementaryAgreementBasicContract  = new JCheckBox();
        checkBoxDateCreateSupplementaryAgreementBasicContract.setName("checkBoxDateCreateSupplementaryAgreementBasicContract");
        checkBoxDateCreateSupplementaryAgreementBasicContract.addActionListener(controller);
        staticJPanel.add(checkBoxDateCreateSupplementaryAgreementBasicContract,gridBagConstraints);

        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridx = 0;
        staticJPanel.add(new JLabel("Номер доп соглашения"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        JTextField numberSupplementaryAgreementBasicContract = new JTextField();
        numberSupplementaryAgreementBasicContract.setName("numberSupplementaryAgreementBasicContract");
        if(dataClient.getSupplementaryAgreementBasicContract().getNumberSupplementaryAgreementBasicContract()==0){
            numberSupplementaryAgreementBasicContract.setText("1");
        }else {
            numberSupplementaryAgreementBasicContract.setText(String.valueOf(dataClient.getSupplementaryAgreementBasicContract().getNumberSupplementaryAgreementBasicContract()));
        }
        numberSupplementaryAgreementBasicContract.addKeyListener(controller);
        numberSupplementaryAgreementBasicContract.setEnabled(false);
        staticJPanel.add(numberSupplementaryAgreementBasicContract,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxNumberSupplementaryAgreementBasicContract  = new JCheckBox();
        checkBoxNumberSupplementaryAgreementBasicContract.setName("checkBoxNumberSupplementaryAgreementBasicContract");
        checkBoxNumberSupplementaryAgreementBasicContract.addActionListener(controller);
        staticJPanel.add(checkBoxNumberSupplementaryAgreementBasicContract,gridBagConstraints);

        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridx = 0;
        JButton saveDataSupplementaryAgreementBasicContract = new JButton("Сохранить");
        saveDataSupplementaryAgreementBasicContract.setName("saveDataSupplementaryAgreementBasicContract");
        saveDataSupplementaryAgreementBasicContract.addActionListener(controller);
        staticJPanel.add(saveDataSupplementaryAgreementBasicContract,gridBagConstraints);

        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        JButton printSupplementaryAgreementBasicContract = new JButton("Распечатать 2 раза");
        printSupplementaryAgreementBasicContract.setName("printSupplementaryAgreementBasicContract");
        printSupplementaryAgreementBasicContract.addActionListener(controller);
        staticJPanel.add(printSupplementaryAgreementBasicContract,gridBagConstraints);

        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        JButton openDirectoryWithFile = new JButton("Открыть папку с файлом");
        openDirectoryWithFile.setName("openDirectoryWithFile");
        openDirectoryWithFile.addActionListener(controller);
        staticJPanel.add(openDirectoryWithFile,gridBagConstraints);

        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        JButton openFileSupplementaryAgreementBasicContract = new JButton("Открыть файл");
        openFileSupplementaryAgreementBasicContract.setName("openFileSupplementaryAgreementBasicContract");
        openFileSupplementaryAgreementBasicContract.addActionListener(controller);
        staticJPanel.add(openFileSupplementaryAgreementBasicContract,gridBagConstraints);

        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipady = 150;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);

        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.gridwidth =3;
        JButton backSelectClient = new JButton("Назад");
        backSelectClient.setName("backSelectClient");
        backSelectClient.addActionListener(controller);
        staticJPanel.add(backSelectClient,gridBagConstraints);

        gridBagConstraints.gridy = 19;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        JButton mainPage = new JButton("Главная страница");
        mainPage.setName("mainPage");
        mainPage.addActionListener(controller);
        staticJPanel.add(mainPage,gridBagConstraints);

        staticJPanel.revalidate();
        staticJPanel.repaint();
        if(dataClient.getSupplementaryAgreementBasicContract().getAllSumInEURSupplementaryAgreement()==0){
            allSumInEURSupplementaryAgreement.requestFocusInWindow();
        }
    }
    public void editEditAllSumAgreementBasicContract (int allSum){
        double currency =0;
        for(Component component :  getComponentsStaticPanel()){
            try {
                if(component.getName().equals("currencySumSupplementaryAgreement")){
                    currency = Double.parseDouble(((JTextField)component).getText());
                }
            }catch (Exception e){ }

        }
        int sumInBYN = (int) Math.round((double) currency*allSum);
        for(Component component :  getComponentsStaticPanel()) {
            try {
                switch (component.getName()){
                    case "allSumInBYNSupplementaryAgreement" :{
                        ((JTextField)component).setText(String.valueOf(sumInBYN));
                        break;
                    }
                }
            } catch (Exception e){

            }
        }
        editEditPayUpTo50percentAgreementBasicContract();
        staticJPanel.revalidate();
        staticJPanel.repaint();

    }
    public void editEditPayUpTo50percentAgreementBasicContract (){
        int prepayment  = 0,
                allSum = 0,
                percent50 = 0;

        for(Component component :getComponentsStaticPanel()){
            try {
                if(component.getName().equals("prepaymentOr10PercentSumSupplementaryAgreement")){
                    prepayment = Integer.parseInt (((JTextField)component).getText());
                }
                else  if(component.getName().equals("allSumInEURSupplementaryAgreement")){
                    allSum = Integer.parseInt (((JTextField)component).getText());
                }
            }catch (Exception e){}
        }
        percent50 = allSum/2-prepayment;
        if(percent50 <0)percent50=0;
        for(Component component :getComponentsStaticPanel()){
            try {
                if(component.getName().equals("payUpTo50PercentSumSupplementaryAgreement")){
                    ((JTextField)component).setText(String.valueOf(percent50));
                    break;
                }
            }catch (Exception e){}
        }
        editEditPayUpTo100percentAgreementBasicContract();
        staticJPanel.revalidate();
        staticJPanel.repaint();

    }
    public void editEditPayUpTo100percentAgreementBasicContract (){
        int prepayment  = 0,
                allSum = 0,
                percent50 = 0,
                percent100 = 0;
        for(Component component :getComponentsStaticPanel()){
            try {
                if(component.getName().equals("prepaymentOr10PercentSumSupplementaryAgreement")){
                    prepayment = Integer.parseInt (((JTextField)component).getText());
                }
                else  if(component.getName().equals("allSumInEURSupplementaryAgreement")){
                    allSum = Integer.parseInt (((JTextField)component).getText());
                }
                else  if(component.getName().equals("payUpTo50PercentSumSupplementaryAgreement")){
                    percent50 = Integer.parseInt (((JTextField)component).getText());
                }
            }catch (Exception e){}
        }
        percent100 = allSum-prepayment-percent50;
        for(Component component :getComponentsStaticPanel()){
            try {
                if(component.getName().equals("payUpTo100PercentSumSupplementaryAgreement")){
                    ((JTextField)component).setText(String.valueOf(percent100));
                    break;
                }
            }catch (Exception e){}
        }
        staticJPanel.revalidate();
        staticJPanel.repaint();

    }
    public void editCurrencyAgreementBasicContract(JTextField jTextField){
        double allSum = 0;
        JTextField allSumField = null;
        for(Component component : staticJPanel.getComponents()){
            try {
                if (component.getName().equals("allSumInBYNSupplementaryAgreement")){
                    allSumField = (JTextField) component;

                } else if(component.getName().equals("allSumInEURSupplementaryAgreement")){
                    allSum = Double.parseDouble(((JTextField)component).getText());
                }
            }catch (Exception e){

            }
        }
        allSumField.setText(String.valueOf(Math.round(((double) allSum*Double.parseDouble(jTextField.getText())))));
        staticJPanel.revalidate();
        staticJPanel.repaint();

    }

    //SupplementaryAgreementUpSale
    public void editSupplementaryAgreementUpSale (DataClient dataClient){
        //Если данные еще не вводились в доп соглашение то мы все данные подтягиваем из UpSale Contract
        try {
            dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProductsSupplementaryAgreementUpSale()[0].hashCode();
        }catch (NullPointerException e){
            editSupplementaryAgreementUpSaleIfDontHaveListAdditionalProducts(dataClient);
            return;
        }
        MaskFormatter
                dateCreateContractMask = null;
        try {
            dateCreateContractMask = new MaskFormatter("##.##.####");
        }
        catch (Exception e){}

        staticJPanel.removeAll();
        staticJPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        JLabel j1 = new JLabel("Клиент: ");
        j1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j1,gridBagConstraints);

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridwidth = 5;
        JLabel j2 = new JLabel(dataClient.getFullNameClient());
        j2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j2,gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        JLabel j4 = new JLabel("Доп UpSale");
        j4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j4,gridBagConstraints);

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridwidth = 5;
        JLabel j3 = new JLabel(dataClient.getNumberContract()+" " + dataClient.getStrangeName());
        j3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j3,gridBagConstraints);

        gridBagConstraints.gridy = 2;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);
        gridBagConstraints.gridy = 3;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 0;
        staticJPanel.add(new JLabel("№"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 3;
        staticJPanel.add(new JLabel(" Наименование товара"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        staticJPanel.add(new JLabel("К"),gridBagConstraints);
        gridBagConstraints.gridx = 5;
        staticJPanel.add(new JLabel("Цена"),gridBagConstraints);
        gridBagConstraints.gridx = 6;
        staticJPanel.add(new JLabel("%"),gridBagConstraints);
        gridBagConstraints.gridx = 7;
        staticJPanel.add(new JLabel("Всего"),gridBagConstraints);
        JTextField supplementaryAgreementAdditionalProducts[] = new JTextField[6];
        JTextField supplementaryAgreementAdditionalProductsCount[] = new JTextField[6];
        JTextField supplementaryAgreementAdditionalProductsDiscount[] = new JTextField[6];
        JTextField supplementaryAgreementAdditionalProductsFullPrice[] = new JTextField[6];
        JTextField supplementaryAgreementAdditionalProductsWithDiscount[] = new JTextField[6];
        for (int i = 0; i < 6; i++) {
            supplementaryAgreementAdditionalProducts[i] = new JTextField();
            supplementaryAgreementAdditionalProductsCount[i] = new JTextField();
            supplementaryAgreementAdditionalProductsDiscount[i] = new JTextField();
            supplementaryAgreementAdditionalProductsFullPrice[i] = new JTextField();
            supplementaryAgreementAdditionalProductsWithDiscount[i] = new JTextField();

            gridBagConstraints.gridy = 5+i;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.ipadx = 5;
            gridBagConstraints.gridwidth = 1;
            staticJPanel.add(new JLabel(String.valueOf(i+1)),gridBagConstraints);
            gridBagConstraints.gridx = 1;
            gridBagConstraints.ipadx = 310;
            gridBagConstraints.gridwidth = 3;
            supplementaryAgreementAdditionalProducts[i].setName("supplementaryAgreementAdditionalProducts"+i);
            staticJPanel.add(supplementaryAgreementAdditionalProducts[i],gridBagConstraints);
            gridBagConstraints.gridx = 4;
            gridBagConstraints.ipadx = 10;
            gridBagConstraints.gridwidth = 1;
            supplementaryAgreementAdditionalProductsCount[i].setText(String.valueOf(1));
            supplementaryAgreementAdditionalProductsCount[i].setName("supplementaryAgreementAdditionalProductsCount"+i);
            supplementaryAgreementAdditionalProductsCount[i].addKeyListener(controller);
            staticJPanel.add(supplementaryAgreementAdditionalProductsCount[i],gridBagConstraints);
            gridBagConstraints.gridx = 5;
            gridBagConstraints.ipadx = 50;
            supplementaryAgreementAdditionalProductsDiscount[i].setText(String.valueOf(0));
            supplementaryAgreementAdditionalProductsFullPrice[i].setName("supplementaryAgreementAdditionalProductsFullPrice"+i);
            supplementaryAgreementAdditionalProductsFullPrice[i].addKeyListener(controller);
            staticJPanel.add(supplementaryAgreementAdditionalProductsFullPrice[i],gridBagConstraints);
            gridBagConstraints.gridx = 6;
            gridBagConstraints.ipadx = 15;
            supplementaryAgreementAdditionalProductsDiscount[i].setName("supplementaryAgreementAdditionalProductsDiscount"+i);
            supplementaryAgreementAdditionalProductsDiscount[i].addKeyListener(controller);
            staticJPanel.add(supplementaryAgreementAdditionalProductsDiscount[i],gridBagConstraints);
            gridBagConstraints.gridx = 7;
            gridBagConstraints.ipadx = 50;
            supplementaryAgreementAdditionalProductsWithDiscount[i].setName("supplementaryAgreementAdditionalProductsWithDiscount"+i);
            supplementaryAgreementAdditionalProductsWithDiscount[i].addKeyListener(controller);
            staticJPanel.add(supplementaryAgreementAdditionalProductsWithDiscount[i],gridBagConstraints);
            try {
                supplementaryAgreementAdditionalProducts[i].setText(dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProductsSupplementaryAgreementUpSale()[i].getName());
                supplementaryAgreementAdditionalProductsCount[i].setText(dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProductsSupplementaryAgreementUpSale()[i].getCount());
                supplementaryAgreementAdditionalProductsDiscount[i].setText(dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProductsSupplementaryAgreementUpSale()[i].getDiscount());
                supplementaryAgreementAdditionalProductsFullPrice[i].setText(dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProductsSupplementaryAgreementUpSale()[i].getFullPrice());
                supplementaryAgreementAdditionalProductsWithDiscount[i].setText(dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProductsSupplementaryAgreementUpSale()[i].getPriceWithDiscount());

            } catch (NullPointerException e){

            }
        }

        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 0;
        staticJPanel.add(new JLabel("Сумма договора"),gridBagConstraints);

        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxSumUpSaleInBYNSupplementaryAgreement = new JCheckBox();
        checkBoxSumUpSaleInBYNSupplementaryAgreement.setName("checkBoxSumUpSaleInBYNSupplementaryAgreement");
        checkBoxSumUpSaleInBYNSupplementaryAgreement.addActionListener(controller);
        staticJPanel.add(checkBoxSumUpSaleInBYNSupplementaryAgreement,gridBagConstraints);

        JTextField sumUpSaleInBYNSupplementaryAgreement;
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        sumUpSaleInBYNSupplementaryAgreement = new JTextField();
        sumUpSaleInBYNSupplementaryAgreement.setName("sumUpSaleInBYNSupplementaryAgreement");
        if(dataClient.getUpSaleContract().getAllSumUpSaleInBYN()!=0){
            sumUpSaleInBYNSupplementaryAgreement.setText(String.valueOf(dataClient.getSupplementaryAgreementUpSaleContract().getAllSumUpSaleInBYNSupplementaryAgreement()));
        }
        sumUpSaleInBYNSupplementaryAgreement.addKeyListener(controller);
        sumUpSaleInBYNSupplementaryAgreement.setEnabled(false);
        staticJPanel.add(sumUpSaleInBYNSupplementaryAgreement,gridBagConstraints);




        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        staticJPanel.add(new JLabel("Предоплата:"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth =1;
        JCheckBox checkBoxPrepaymentUpSaleSupplementaryAgreement  = new JCheckBox();
        checkBoxPrepaymentUpSaleSupplementaryAgreement.setName("checkBoxPrepaymentUpSaleSupplementaryAgreement");
        checkBoxPrepaymentUpSaleSupplementaryAgreement.addActionListener(controller);
        staticJPanel.add(checkBoxPrepaymentUpSaleSupplementaryAgreement,gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        JTextField prepaymentUpSaleSupplementaryAgreement = new JTextField();
        prepaymentUpSaleSupplementaryAgreement.setName("prepaymentUpSaleSupplementaryAgreement");
        prepaymentUpSaleSupplementaryAgreement.setText(String.valueOf(dataClient.getSupplementaryAgreementUpSaleContract().getPrepaymentUpSaleSupplementaryAgreement()));
        prepaymentUpSaleSupplementaryAgreement.addKeyListener(controller);
        prepaymentUpSaleSupplementaryAgreement.setEnabled(false);
        staticJPanel.add(prepaymentUpSaleSupplementaryAgreement,gridBagConstraints);

        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        staticJPanel.add(new JLabel("Оплата до 100%"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxPayUpTo100percentUpSaleSupplementaryAgreement  = new JCheckBox();
        checkBoxPayUpTo100percentUpSaleSupplementaryAgreement.setName("checkBoxPayUpTo100percentUpSaleSupplementaryAgreement");
        checkBoxPayUpTo100percentUpSaleSupplementaryAgreement.addActionListener(controller);
        staticJPanel.add(checkBoxPayUpTo100percentUpSaleSupplementaryAgreement,gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        JTextField payUpTo100percentUpSaleSupplementaryAgreement = new JTextField();
        payUpTo100percentUpSaleSupplementaryAgreement.setName("payUpTo100percentUpSaleSupplementaryAgreement");
        payUpTo100percentUpSaleSupplementaryAgreement.setText(String.valueOf(dataClient.getSupplementaryAgreementUpSaleContract().getPayUpTo100percentUpSaleSupplementaryAgreement()));
        payUpTo100percentUpSaleSupplementaryAgreement.addKeyListener(controller);
        payUpTo100percentUpSaleSupplementaryAgreement.setEnabled(false);
        staticJPanel.add(payUpTo100percentUpSaleSupplementaryAgreement,gridBagConstraints);

        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        staticJPanel.add(new JLabel("Дата UpSale"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxJust  = new JCheckBox();
        checkBoxJust.setName("checkBoxJust");
        checkBoxJust.addActionListener(controller);
        checkBoxJust.setEnabled(false);
        staticJPanel.add(checkBoxJust,gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        JFormattedTextField dateCreateUpSaleContract = new JFormattedTextField(dateCreateContractMask);
        dateCreateUpSaleContract.setName("dateCreateUpSaleContract");
        dateCreateUpSaleContract.setText(String.valueOf(dataClient.getUpSaleContract().getDateCreateUpSaleContract()));
        dateCreateUpSaleContract.addKeyListener(controller);
        dateCreateUpSaleContract.setEnabled(false);
        staticJPanel.add(dateCreateUpSaleContract,gridBagConstraints);

        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        staticJPanel.add(new JLabel("Дата подписания"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxDateCreateSupplementaryAgreementUpSaleContract  = new JCheckBox();
        checkBoxDateCreateSupplementaryAgreementUpSaleContract.setName("checkBoxDateCreateSupplementaryAgreementUpSaleContract");
        checkBoxDateCreateSupplementaryAgreementUpSaleContract.addActionListener(controller);
        staticJPanel.add(checkBoxDateCreateSupplementaryAgreementUpSaleContract,gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        JFormattedTextField dateCreateSupplementaryAgreementUpSaleContract = new JFormattedTextField(dateCreateContractMask);
        dateCreateSupplementaryAgreementUpSaleContract.setName("dateCreateSupplementaryAgreementUpSaleContract");
        dateCreateSupplementaryAgreementUpSaleContract.setText(String.valueOf(dataClient.getSupplementaryAgreementUpSaleContract().getDateCreateSupplementaryAgreementUpSaleContract()));
        dateCreateSupplementaryAgreementUpSaleContract.addKeyListener(controller);
        dateCreateSupplementaryAgreementUpSaleContract.setEnabled(false);
        staticJPanel.add(dateCreateSupplementaryAgreementUpSaleContract,gridBagConstraints);

        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        staticJPanel.add(new JLabel("Номер"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxNumberSupplementaryAgreementUpSale  = new JCheckBox();
        checkBoxNumberSupplementaryAgreementUpSale.setName("checkBoxNumberSupplementaryAgreementUpSale");
        checkBoxNumberSupplementaryAgreementUpSale.addActionListener(controller);
        staticJPanel.add(checkBoxNumberSupplementaryAgreementUpSale,gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        JTextField numberSupplementaryAgreementUpSale = new JTextField();
        numberSupplementaryAgreementUpSale.setName("numberSupplementaryAgreementUpSale");
        numberSupplementaryAgreementUpSale.setText(String.valueOf(dataClient.getSupplementaryAgreementBasicContract().getNumberSupplementaryAgreementBasicContract()));
        numberSupplementaryAgreementUpSale.addKeyListener(controller);
        numberSupplementaryAgreementUpSale.setEnabled(false);
        staticJPanel.add(numberSupplementaryAgreementUpSale,gridBagConstraints);


        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 0;
        JButton saveDataSupplementaryAgreementUpSaleContact = new JButton("Сохранить");
        saveDataSupplementaryAgreementUpSaleContact.setName("saveDataSupplementaryAgreementUpSaleContact");
        saveDataSupplementaryAgreementUpSaleContact.addActionListener(controller);
        staticJPanel.add(saveDataSupplementaryAgreementUpSaleContact,gridBagConstraints);

        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 4;
        JButton printSupplementaryAgreementUpSaleContract = new JButton("Распечатать 2 раза");
        printSupplementaryAgreementUpSaleContract.setName("printSupplementaryAgreementUpSaleContract");
        printSupplementaryAgreementUpSaleContract.addActionListener(controller);
        staticJPanel.add(printSupplementaryAgreementUpSaleContract,gridBagConstraints);

        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        JButton openDirectoryWithFile = new JButton("Открыть папку с файлом");
        openDirectoryWithFile.setName("openDirectoryWithFile");
        openDirectoryWithFile.addActionListener(controller);
        staticJPanel.add(openDirectoryWithFile,gridBagConstraints);

        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 4;
        JButton openFileUpSaleContract = new JButton("Открыть файл");
        openFileUpSaleContract.setName("openFileSupplementaryAgreementUpSaleContract");
        openFileUpSaleContract.addActionListener(controller);
        staticJPanel.add(openFileUpSaleContract,gridBagConstraints);

        gridBagConstraints.gridy = 19;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipady = 75;
        gridBagConstraints.gridwidth =8;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);

        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipady = 0;
        JButton backSelectClient = new JButton("Назад");
        backSelectClient.setName("backSelectClient");
        backSelectClient.addActionListener(controller);
        staticJPanel.add(backSelectClient,gridBagConstraints);

        gridBagConstraints.gridy = 21;
        gridBagConstraints.gridx = 0;
        JButton mainPage = new JButton("Главная страница");
        mainPage.setName("mainPage");
        mainPage.addActionListener(controller);
        staticJPanel.add(mainPage,gridBagConstraints);

        staticJPanel.revalidate();
        staticJPanel.repaint();

    }
    private void editSupplementaryAgreementUpSaleIfDontHaveListAdditionalProducts(DataClient dataClient){
        MaskFormatter
                dateCreateContractMask = null;
        try {
            dateCreateContractMask = new MaskFormatter("##.##.####");
        }
        catch (Exception e){}

        staticJPanel.removeAll();
        staticJPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        JLabel j1 = new JLabel("Клиент: ");
        j1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j1,gridBagConstraints);

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridwidth = 5;
        JLabel j2 = new JLabel(dataClient.getFullNameClient());
        j2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j2,gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        JLabel j4 = new JLabel("Доп UpSale");
        j4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j4,gridBagConstraints);

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridwidth = 5;
        JLabel j3 = new JLabel(dataClient.getNumberContract()+" " + dataClient.getStrangeName());
        j3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j3,gridBagConstraints);

        gridBagConstraints.gridy = 2;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);
        gridBagConstraints.gridy = 3;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 0;
        staticJPanel.add(new JLabel("№"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 3;
        staticJPanel.add(new JLabel(" Наименование товара"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        staticJPanel.add(new JLabel("К"),gridBagConstraints);
        gridBagConstraints.gridx = 5;
        staticJPanel.add(new JLabel("Цена"),gridBagConstraints);
        gridBagConstraints.gridx = 6;
        staticJPanel.add(new JLabel("%"),gridBagConstraints);
        gridBagConstraints.gridx = 7;
        staticJPanel.add(new JLabel("Всего"),gridBagConstraints);
        JTextField supplementaryAgreementAdditionalProducts[] = new JTextField[6];
        JTextField supplementaryAgreementAdditionalProductsCount[] = new JTextField[6];
        JTextField supplementaryAgreementAdditionalProductsDiscount[] = new JTextField[6];
        JTextField supplementaryAgreementAdditionalProductsFullPrice[] = new JTextField[6];
        JTextField supplementaryAgreementAdditionalProductsWithDiscount[] = new JTextField[6];
        for (int i = 0; i < 6; i++) {
            supplementaryAgreementAdditionalProducts[i] = new JTextField();
            supplementaryAgreementAdditionalProductsCount[i] = new JTextField();
            supplementaryAgreementAdditionalProductsDiscount[i] = new JTextField();
            supplementaryAgreementAdditionalProductsFullPrice[i] = new JTextField();
            supplementaryAgreementAdditionalProductsWithDiscount[i] = new JTextField();
            gridBagConstraints.gridy = 5+i;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.ipadx = 5;
            gridBagConstraints.gridwidth = 1;
            staticJPanel.add(new JLabel(String.valueOf(i+1)),gridBagConstraints);
            gridBagConstraints.gridx = 1;
            gridBagConstraints.ipadx = 310;
            gridBagConstraints.gridwidth = 3;
            supplementaryAgreementAdditionalProducts[i].setName("supplementaryAgreementAdditionalProducts"+i);
            staticJPanel.add(supplementaryAgreementAdditionalProducts[i],gridBagConstraints);
            gridBagConstraints.gridx = 4;
            gridBagConstraints.ipadx = 10;
            gridBagConstraints.gridwidth = 1;
            supplementaryAgreementAdditionalProductsCount[i].setText(String.valueOf(1));
            supplementaryAgreementAdditionalProductsCount[i].setName("supplementaryAgreementAdditionalProductsCount"+i);
            supplementaryAgreementAdditionalProductsCount[i].addKeyListener(controller);
            staticJPanel.add(supplementaryAgreementAdditionalProductsCount[i],gridBagConstraints);
            gridBagConstraints.gridx = 5;
            gridBagConstraints.ipadx = 50;

            supplementaryAgreementAdditionalProductsFullPrice[i].setName("supplementaryAgreementAdditionalProductsFullPrice"+i);
            supplementaryAgreementAdditionalProductsFullPrice[i].addKeyListener(controller);
            staticJPanel.add(supplementaryAgreementAdditionalProductsFullPrice[i],gridBagConstraints);
            gridBagConstraints.gridx = 6;
            gridBagConstraints.ipadx = 15;
            supplementaryAgreementAdditionalProductsDiscount[i].setText(String.valueOf(0));
            supplementaryAgreementAdditionalProductsDiscount[i].setName("supplementaryAgreementAdditionalProductsDiscount"+i);
            supplementaryAgreementAdditionalProductsDiscount[i].addKeyListener(controller);
            staticJPanel.add(supplementaryAgreementAdditionalProductsDiscount[i],gridBagConstraints);
            gridBagConstraints.gridx = 7;
            gridBagConstraints.ipadx = 50;
            supplementaryAgreementAdditionalProductsWithDiscount[i].setName("supplementaryAgreementAdditionalProductsWithDiscount"+i);
            supplementaryAgreementAdditionalProductsWithDiscount[i].addKeyListener(controller);
            staticJPanel.add(supplementaryAgreementAdditionalProductsWithDiscount[i],gridBagConstraints);
            try {
                supplementaryAgreementAdditionalProducts[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getName());
                supplementaryAgreementAdditionalProductsCount[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getCount());
                supplementaryAgreementAdditionalProductsDiscount[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getDiscount());
                supplementaryAgreementAdditionalProductsFullPrice[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getFullPrice());
                supplementaryAgreementAdditionalProductsWithDiscount[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getPriceWithDiscount());
            } catch (NullPointerException e){

            }
        }
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 0;
        staticJPanel.add(new JLabel("Сумма договора"),gridBagConstraints);


        JTextField sumUpSaleInBYNSupplementaryAgreement;
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        sumUpSaleInBYNSupplementaryAgreement = new JTextField();
        sumUpSaleInBYNSupplementaryAgreement.setName("sumUpSaleInBYNSupplementaryAgreement");
        if(dataClient.getUpSaleContract().getAllSumUpSaleInBYN()!=0){
            sumUpSaleInBYNSupplementaryAgreement.setText(String.valueOf(dataClient.getUpSaleContract().getAllSumUpSaleInBYN()));
        }
        sumUpSaleInBYNSupplementaryAgreement.addKeyListener(controller);
        sumUpSaleInBYNSupplementaryAgreement.setEnabled(false);
        staticJPanel.add(sumUpSaleInBYNSupplementaryAgreement,gridBagConstraints);

        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxSumUpSaleInBYNSupplementaryAgreement = new JCheckBox();
        checkBoxSumUpSaleInBYNSupplementaryAgreement.setName("checkBoxSumUpSaleInBYNSupplementaryAgreement");
        checkBoxSumUpSaleInBYNSupplementaryAgreement.addActionListener(controller);
        staticJPanel.add(checkBoxSumUpSaleInBYNSupplementaryAgreement,gridBagConstraints);


        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 0;
        staticJPanel.add(new JLabel("Предоплата:"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth =1;
        JCheckBox checkBoxPrepaymentUpSaleSupplementaryAgreement  = new JCheckBox();
        checkBoxPrepaymentUpSaleSupplementaryAgreement.setName("checkBoxPrepaymentUpSaleSupplementaryAgreement");
        checkBoxPrepaymentUpSaleSupplementaryAgreement.addActionListener(controller);
        staticJPanel.add(checkBoxPrepaymentUpSaleSupplementaryAgreement,gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        JTextField prepaymentUpSaleSupplementaryAgreement = new JTextField();
        prepaymentUpSaleSupplementaryAgreement.setName("prepaymentUpSaleSupplementaryAgreement");
        prepaymentUpSaleSupplementaryAgreement.setText(String.valueOf(dataClient.getUpSaleContract().getPrepaymentUpSale()));
        prepaymentUpSaleSupplementaryAgreement.addKeyListener(controller);
        prepaymentUpSaleSupplementaryAgreement.setEnabled(false);
        staticJPanel.add(prepaymentUpSaleSupplementaryAgreement,gridBagConstraints);

        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        staticJPanel.add(new JLabel("Оплата до 100%"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxPayUpTo100percentSupplementaryAgreementUpSale  = new JCheckBox();
        checkBoxPayUpTo100percentSupplementaryAgreementUpSale.setName("checkBoxPayUpTo100percentSupplementaryAgreementUpSale");
        checkBoxPayUpTo100percentSupplementaryAgreementUpSale.addActionListener(controller);
        staticJPanel.add(checkBoxPayUpTo100percentSupplementaryAgreementUpSale,gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        JTextField payUpTo100percentSupplementaryAgreementUpSale = new JTextField();
        payUpTo100percentSupplementaryAgreementUpSale.setName("payUpTo100percentUpSaleSupplementaryAgreement");
        payUpTo100percentSupplementaryAgreementUpSale.setText(String.valueOf(dataClient.getUpSaleContract().getPayUpTo100percentUpSale()));
        payUpTo100percentSupplementaryAgreementUpSale.addKeyListener(controller);
        payUpTo100percentSupplementaryAgreementUpSale.setEnabled(false);
        staticJPanel.add(payUpTo100percentSupplementaryAgreementUpSale,gridBagConstraints);

        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        staticJPanel.add(new JLabel("Дата UpSale"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxJust  = new JCheckBox();
        checkBoxJust.setName("checkBoxJust");
        checkBoxJust.addActionListener(controller);
        checkBoxJust.setEnabled(false);
        staticJPanel.add(checkBoxJust,gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        JFormattedTextField dateCreateUpSaleContract = new JFormattedTextField(dateCreateContractMask);
        dateCreateUpSaleContract.setName("dateCreateUpSaleContract");
        dateCreateUpSaleContract.setText(String.valueOf(dataClient.getUpSaleContract().getDateCreateUpSaleContract()));
        dateCreateUpSaleContract.addKeyListener(controller);
        dateCreateUpSaleContract.setEnabled(false);
        staticJPanel.add(dateCreateUpSaleContract,gridBagConstraints);

        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        staticJPanel.add(new JLabel("Дата подписания"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxDateCreateSupplementaryAgreementUpSaleContract  = new JCheckBox();
        checkBoxDateCreateSupplementaryAgreementUpSaleContract.setName("checkBoxDateCreateSupplementaryAgreementUpSaleContract");
        checkBoxDateCreateSupplementaryAgreementUpSaleContract.addActionListener(controller);
        staticJPanel.add(checkBoxDateCreateSupplementaryAgreementUpSaleContract,gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        JFormattedTextField dateCreateSupplementaryAgreementUpSaleContract = new JFormattedTextField(dateCreateContractMask);
        dateCreateSupplementaryAgreementUpSaleContract.setName("dateCreateSupplementaryAgreementUpSaleContract");
        dateCreateSupplementaryAgreementUpSaleContract.setText(String.valueOf(dataClient.getSupplementaryAgreementUpSaleContract().getDateCreateSupplementaryAgreementUpSaleContract()));
        dateCreateSupplementaryAgreementUpSaleContract.addKeyListener(controller);
        dateCreateSupplementaryAgreementUpSaleContract.setEnabled(false);
        staticJPanel.add(dateCreateSupplementaryAgreementUpSaleContract,gridBagConstraints);

        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        staticJPanel.add(new JLabel("Номер"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxNumberSupplementaryAgreementUpSale  = new JCheckBox();
        checkBoxNumberSupplementaryAgreementUpSale.setName("checkBoxNumberSupplementaryAgreementUpSale");
        checkBoxNumberSupplementaryAgreementUpSale.addActionListener(controller);
        staticJPanel.add(checkBoxNumberSupplementaryAgreementUpSale,gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        JTextField numberSupplementaryAgreementUpSale = new JTextField();
        numberSupplementaryAgreementUpSale.setName("numberSupplementaryAgreementUpSale");
        numberSupplementaryAgreementUpSale.setText("1");
        numberSupplementaryAgreementUpSale.addKeyListener(controller);
        numberSupplementaryAgreementUpSale.setEnabled(false);
        staticJPanel.add(numberSupplementaryAgreementUpSale,gridBagConstraints);


        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        JButton saveDataSupplementaryAgreementUpSaleContact = new JButton("Сохранить");
        saveDataSupplementaryAgreementUpSaleContact.setName("saveDataSupplementaryAgreementUpSaleContact");
        saveDataSupplementaryAgreementUpSaleContact.addActionListener(controller);
        staticJPanel.add(saveDataSupplementaryAgreementUpSaleContact,gridBagConstraints);

        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 4;
        JButton printSupplementaryAgreementUpSaleContract = new JButton("Распечатать 2 раза");
        printSupplementaryAgreementUpSaleContract.setName("printSupplementaryAgreementUpSaleContract");
        printSupplementaryAgreementUpSaleContract.addActionListener(controller);
        staticJPanel.add(printSupplementaryAgreementUpSaleContract,gridBagConstraints);

        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        JButton openDirectoryWithFile = new JButton("Открыть папку с файлом");
        openDirectoryWithFile.setName("openDirectoryWithFile");
        openDirectoryWithFile.addActionListener(controller);
        staticJPanel.add(openDirectoryWithFile,gridBagConstraints);

        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 4;
        JButton openFileUpSaleContract = new JButton("Открыть файл");
        openFileUpSaleContract.setName("openFileSupplementaryAgreementUpSaleContract");
        openFileUpSaleContract.addActionListener(controller);
        staticJPanel.add(openFileUpSaleContract,gridBagConstraints);

        gridBagConstraints.gridy = 19;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipady = 75;
        gridBagConstraints.gridwidth =8;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);

        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipady = 0;
        JButton backSelectClient = new JButton("Назад");
        backSelectClient.setName("backSelectClient");
        backSelectClient.addActionListener(controller);
        staticJPanel.add(backSelectClient,gridBagConstraints);

        gridBagConstraints.gridy = 21;
        gridBagConstraints.gridx = 0;
        JButton mainPage = new JButton("Главная страница");
        mainPage.setName("mainPage");
        mainPage.addActionListener(controller);
        staticJPanel.add(mainPage,gridBagConstraints);

        staticJPanel.revalidate();
        staticJPanel.repaint();

    }
    public void editSupplementaryAgreementUpSaleEditSumProductWithDiscount(JTextField jTextField){
        String count = jTextField.getName().substring(jTextField.getName().length()-1);
        int jCount = 0;
        int jDiscount = 0;
        int jPrice = 0;
        JTextField jPriceWitchDiscount = null;

        for(Component component :staticJPanel.getComponents()){
            try {
                if(component.getName().equals("supplementaryAgreementAdditionalProductsCount"+count)){
                    jCount = Integer.parseInt(((JTextField) component).getText());
                }
                else if (component.getName().equals("supplementaryAgreementAdditionalProductsDiscount"+count)){
                    jDiscount = Integer.parseInt(((JTextField) component).getText());
                }
                else if (component.getName().equals("supplementaryAgreementAdditionalProductsFullPrice"+count)){
                    jPrice = Integer.parseInt(((JTextField) component).getText());
                }
                else if (component.getName().equals("supplementaryAgreementAdditionalProductsWithDiscount"+count)){
                    jPriceWitchDiscount = (JTextField) component;
                }
            }catch (Exception e){}
        }

        jPriceWitchDiscount.setText(String.valueOf(((int) Math.round((double) jPrice*((double) (100-jDiscount)/100)))*jCount));
        staticJPanel.revalidate();
        staticJPanel.repaint();
        editSupplementaryAgreementUpSaleEditAllSum();


    }
    public void editSupplementaryAgreementUpSaleEditAllSum (){
        int allSum = 0;
        ArrayList<JTextField> list = new ArrayList<>();
        for(Component component : staticJPanel.getComponents()){
           try {
               if (component.getName().contains("supplementaryAgreementAdditionalProductsWithDiscount")) {
                   list.add((JTextField) component);
               }
           }catch (NullPointerException e){}
        }
        for(int i =0;i<list.size();i++){
            try {
                allSum = allSum + Integer.parseInt(list.get(i).getText());

            }catch (NumberFormatException e){

            }
        }
        for(Component component :getComponentsStaticPanel()){
            try {
                if(component.getName().equals("sumUpSaleInBYNSupplementaryAgreement")){
                    ((JTextField)component).setText(String.valueOf(allSum));
                    editSupplementaryAgreementUpSaleEditPayUpTo100percentUpSale();
                    break;
                }
            }catch (Exception e){}
        }
        staticJPanel.repaint();

    }
    public void editSupplementaryAgreementUpSaleEditPayUpTo100percentUpSale (){
        int allSum = 0;
        int prepayment = 0;
        for(Component component :getComponentsStaticPanel()){
            try {
                if(component.getName().equals("sumUpSaleInBYNSupplementaryAgreement")){
                    allSum = Integer.parseInt (((JTextField)component).getText());
                }else if(component.getName().equals("prepaymentUpSaleSupplementaryAgreement")){
                    prepayment = Integer.parseInt(((JTextField)component).getText());
                }
            }catch (Exception e){}
        }
        for(Component component :getComponentsStaticPanel()){
            try {
                if(component.getName().equals("payUpTo100percentUpSaleSupplementaryAgreement")){
                    ((JTextField)component).setText(String.valueOf(allSum-prepayment));
                    break;
                }
            }catch (Exception e){}
        }
        staticJPanel.revalidate();
        staticJPanel.repaint();

    }

    public void displayInvoiceDocument(DataClient dataClient, double currencyValue){
        ViewInvoiceDocument vid = new ViewInvoiceDocument(staticJPanel,dataClient,currencyValue,controller);
        vid.display();
        controller.setDisplay(vid);

    }
    //Метод обновляет чек бокс + подвязаную к нему строку делая ее доступной или наоборот
    public void refreshCheckBox(JCheckBox checkBox){
        String obj = checkBox.getName().replace("checkBox","");
        String objWithSmallLitter = Character.toLowerCase(obj.charAt(0)) + obj.substring(1);
        System.out.println(objWithSmallLitter);
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
    //Использую чтобы нельзя было вводить буквы
    public void onlyNumber(JTextField jTextField){
        jTextField.setText(jTextField.getText().replaceAll("[^0-9]",""));
        staticJPanel.revalidate();
        staticJPanel.repaint();
    }
    public void onlyDoubleNumber(JTextField jTextField,int howSymbolAfterDot){
        if(!jTextField.getText().matches("[\\d]{0,6}[\\.]?[\\d]{0,"+howSymbolAfterDot+"}")){
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
                result=result.substring(0,result.indexOf(".")+1+howSymbolAfterDot);
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
    public void writeJustMessage(String message,int messageType){
        JOptionPane.showMessageDialog(this, message,"Сообщение",messageType);
    }
    //
    public JTextField getAllSumInEUR() {
        return allSumInEUR;
    }
    public JTextField getPayUpTo50PercentSum() {
        return payUpTo50PercentSum;
    }
    public JTextField getPrepaymentOr10PercentSum() {
        return prepaymentOr10PercentSum;
    }
}
