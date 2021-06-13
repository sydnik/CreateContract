package org.sydnik.createContract;

import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.data.SalesManager;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class MyView extends JFrame {
    private MyController controller;
    private static JPanel staticJPanel;

    private JTextField allSumInEUR;
    private JTextField payUpTo100PercentSum;
    private JTextField payUpTo50PercentSum;
    private JTextField prepaymentOr10PercentSum;
    private JTextField allSumInBYN;


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
    //Вся визуализация работает через статикJpanel Поэтому обычный getComponents не работает как надо.
    // Когда нужны компоненты экрана использоваться getComponentsStaticPanel
    public Component[] getComponentsStaticPanel() {
        return staticJPanel.getComponents();
    }

    //Самая первая страница при запуске
    public void startPage(SalesManager salesManager){
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
    //Страница настройки для менеджера(Доверенность, до какого действует, И ФИО)
    public void settingsManager(SalesManager salesManager){
        staticJPanel.removeAll();
        staticJPanel.setLayout(new GridLayout(20,1));
        staticJPanel.add(new JLabel("ФИО менеджера :"));
        JTextField fullNameSalesManager = new JTextField();
        fullNameSalesManager.setText(salesManager.getFullName());
        fullNameSalesManager.setName("fullName");
        staticJPanel.add(fullNameSalesManager);
        staticJPanel.add(new JLabel("Номер доверенности :"));
        MaskFormatter numberPowerOfAttorneyFormatter = null,
                datePowerOfAttorneyFormatter = null,
                numberPhoneManagerFormatter = null;
        JFormattedTextField numberPowerOfAttorney = null;
        JFormattedTextField datePowerOfAttorney = null;
        try {
            numberPowerOfAttorneyFormatter = new MaskFormatter("##");
            numberPowerOfAttorney = new JFormattedTextField(numberPowerOfAttorneyFormatter);
            numberPhoneManagerFormatter = new MaskFormatter("+375(##) ### ####");

            datePowerOfAttorneyFormatter = new MaskFormatter("##.##.####");
            datePowerOfAttorney = new JFormattedTextField(datePowerOfAttorneyFormatter);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        numberPowerOfAttorney.setName("numberPowerOfAttorney");
        numberPowerOfAttorney.setText(String.valueOf(salesManager.getNumberPowerOfAttorney()));
        staticJPanel.add(numberPowerOfAttorney);
        staticJPanel.add(new JLabel("До какой даты действует :"));
        datePowerOfAttorney.setName("datePowerOfAttorney");
        datePowerOfAttorney.setText(salesManager.getDatePowerOfAttorney());
        staticJPanel.add(datePowerOfAttorney);

        staticJPanel.add(new JLabel("Номер менеджера:"));
        JFormattedTextField numberPhoneManager = new JFormattedTextField(numberPhoneManagerFormatter);
        numberPhoneManager.setName("numberPhoneManager");
        numberPhoneManager.setText(salesManager.getNumberPhoneManager());
        staticJPanel.add(numberPhoneManager);

        JButton save = new JButton("Сохранить");
        save.setName("saveSittingsManager");
        save.addActionListener(controller);
        staticJPanel.add(save);
        JButton mainPage = new JButton("Главная страница");
        mainPage.setName("mainPage");
        mainPage.addActionListener(controller);
        staticJPanel.add(mainPage);
        staticJPanel.revalidate();
        staticJPanel.repaint();


    }
    //Создаю нового клиента
    public void createNewClient(){
        staticJPanel.removeAll();
        staticJPanel.setLayout(new GridLayout(20,1));
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
    //Выбираю из списка уже существуещего клиента
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

        JList<String> jList = new JList<>(list);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gridBagConstraints.ipady     = 460;
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
    //Отображение выбранного клиента
    public void selectedClient(DataClient data){
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
        JLabel j2 = new JLabel(data.getFullNameClient());
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
        JLabel j3 = new JLabel(data.getNumberContract());
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
        staticJPanel.add(new JLabel("Сумма: " +data.getAllSumInEUR()),gridBagConstraints);

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
        staticJPanel.add(new JLabel("Сумма: "),gridBagConstraints);

        gridBagConstraints.ipadx = 70;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridx = 2;
        JButton editUpSaleContract = new JButton("Изменить");
        editUpSaleContract.setName("editUpSaleContract");
        staticJPanel.add(editUpSaleContract,gridBagConstraints);

        gridBagConstraints.ipadx = 50;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        staticJPanel.add(new JLabel("Доп соглашение БД"),gridBagConstraints);

        gridBagConstraints.ipadx = 40;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridx = 1;
        staticJPanel.add(new JLabel("Новая сумма: "),gridBagConstraints);

        gridBagConstraints.ipadx = 70;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridx = 2;
        JButton editSupplementaryAgreementBasicContract = new JButton("Изменить");
        editSupplementaryAgreementBasicContract.setName("editSupplementaryAgreementBasicContract");
        staticJPanel.add(editSupplementaryAgreementBasicContract,gridBagConstraints);

        gridBagConstraints.ipadx = 50;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        staticJPanel.add(new JLabel("Доп соглашение UpSale"),gridBagConstraints);

        gridBagConstraints.ipadx = 40;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridx = 1;
        staticJPanel.add(new JLabel("Новая сумма: "),gridBagConstraints);

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
        staticJPanel.add(new JLabel("Просто строчка"),gridBagConstraints);

        gridBagConstraints.ipadx = 40;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridx = 1;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);

//        gridBagConstraints.ipadx = 70;
//        gridBagConstraints.gridy = 8;
//        gridBagConstraints.gridx = 2;
//        JButton editSupplementaryAgreementUpSale = new JButton("Изменить");
//        editSupplementaryAgreementUpSale.setName("editSupplementaryAgreementUpSale");
//        staticJPanel.add(editSupplementaryAgreementUpSale,gridBagConstraints);

        gridBagConstraints.ipadx = 50;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        staticJPanel.add(new JLabel("Просто строчка"),gridBagConstraints);

        gridBagConstraints.ipadx = 40;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridx = 1;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);

        gridBagConstraints.ipadx = 40;
        gridBagConstraints.ipady = 260;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridx = 1;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);

//        gridBagConstraints.ipadx = 70;
//        gridBagConstraints.gridy = 9;
//        gridBagConstraints.gridx = 2;
//        JButton editSupplementaryAgreementUpSale = new JButton("Изменить");
//        editSupplementaryAgreementUpSale.setName("editSupplementaryAgreementUpSale");
//        staticJPanel.add(editSupplementaryAgreementUpSale,gridBagConstraints);

        JButton selectClient = new JButton("Вернутся к выбору клиента");
        selectClient.setName("selectClient");
        selectClient.addActionListener(controller);
        gridBagConstraints.ipady = 0;
        gridBagConstraints.gridy     = 11;
        gridBagConstraints.gridx    = 0;
        gridBagConstraints.gridwidth = 3;
        staticJPanel.add(selectClient,gridBagConstraints);

        JButton mainPage = new JButton("Главная страница");
        mainPage.setName("mainPage");
        mainPage.addActionListener(controller);
        gridBagConstraints.gridy     = 12;
        gridBagConstraints.gridx     = 0;
        gridBagConstraints.gridwidth = 3;
        staticJPanel.add(mainPage, gridBagConstraints);

        staticJPanel.revalidate();
        staticJPanel.repaint();


    }
    //Данные базового договора
    public void editBasicContract(DataClient dataClient,double currencyValue){
        MaskFormatter
                dateCreateContractMask = null;
        try {
            dateCreateContractMask = new MaskFormatter("##.##.####");
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
        JLabel j4 = new JLabel("Номер договора: ");
        j4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j4,gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        JLabel j3 = new JLabel(dataClient.getNumberContract());
        j3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j3,gridBagConstraints);

        gridBagConstraints.gridy = 2;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);
        gridBagConstraints.gridy = 3;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);

        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.gridwidth = 1;
        staticJPanel.add(new JLabel("Курс евро:"),gridBagConstraints);

        JFormattedTextField currency = new JFormattedTextField();
        currency.setText(String.format("%.4f",currencyValue));
        currency.setName("currency");
        currency.setEnabled(false);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.ipadx = 300;
        staticJPanel.add(currency,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.ipadx = 20;
        JCheckBox checkBoxCurrency  = new JCheckBox();
        checkBoxCurrency.setName("checkBoxCurrency");
        checkBoxCurrency.setEnabled(false);
        checkBoxCurrency.addActionListener(controller);
        staticJPanel.add(checkBoxCurrency,gridBagConstraints);

        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 0;
        staticJPanel.add(new JLabel("Сумма договора"),gridBagConstraints);
        allSumInEUR = new JTextField();
        allSumInEUR.setName("allSumInEUR");
        if(dataClient.getAllSumInEUR()!=0){
            allSumInEUR.setText(String.valueOf(dataClient.getAllSumInEUR()));
        }
        allSumInEUR.addKeyListener(controller);
        gridBagConstraints.gridx = 1;
        staticJPanel.add(allSumInEUR,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxAllSumInEUR  = new JCheckBox();
        checkBoxAllSumInEUR.setName("checkBoxAllSumInEUR");
        checkBoxAllSumInEUR.addActionListener(controller);
        staticJPanel.add(checkBoxAllSumInEUR,gridBagConstraints);
        if(dataClient.getAllSumInEUR()!=0){allSumInEUR.setEnabled(false); }
        else { checkBoxAllSumInEUR.setSelected(true); }

        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridx = 0;
        staticJPanel.add(new JLabel("Срок исполнения"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        JComboBox<String> periodOfExecution = new JComboBox(new String[]{"14 до 25","21 до 35"});
        periodOfExecution.setName("timeProduction");
        staticJPanel.add(periodOfExecution,gridBagConstraints);

        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridx = 0;
        staticJPanel.add(new JLabel("Предоплата:"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        prepaymentOr10PercentSum = new JTextField();
        prepaymentOr10PercentSum.setName("prepaymentOr10PercentSum");
        prepaymentOr10PercentSum.setText(String.valueOf(dataClient.getPrepaymentOr10PercentSum()));
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
        payUpTo50PercentSum.setText(String.valueOf(dataClient.getPayUpTo50PercentSum()));
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
        payUpTo100PercentSum.setText(String.valueOf(dataClient.getPayUpTo100PercentSum()));
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
        allSumInBYN.setText(String.valueOf(dataClient.getAllSumInBYN()));
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
        dateCreateContract.setText(String.valueOf(dataClient.getDateCreateContract()));
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
        JButton printContract = new JButton("Распечатать");
        printContract.setName("printContract");
        printContract.addActionListener(controller);
        staticJPanel.add(printContract,gridBagConstraints);

        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipady = 200;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);

        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.gridwidth =3;
        JButton backSelectClient = new JButton("Назад");
        backSelectClient.setName("backSelectClient");
        backSelectClient.addActionListener(controller);
        staticJPanel.add(backSelectClient,gridBagConstraints);

        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        JButton mainPage = new JButton("Главная страница");
        mainPage.setName("mainPage");
        mainPage.addActionListener(controller);
        staticJPanel.add(mainPage,gridBagConstraints);

        staticJPanel.revalidate();
        staticJPanel.repaint();
        if(dataClient.getAllSumInEUR()==0){
            allSumInEUR.requestFocusInWindow();
        }
    }
    //Делаю перерасчет сумму во всех ячейках ниже при вводе суммы договора
    public void editBasicContractEditAllSumInEUR(int allSum,double currency){
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
        int percent50 = allSum/2-prepayment10,
                percent100 = allSum-prepayment10-percent50;
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
    //Если изменился чекбокс на editBasicContact то вызываю этот метод и меняю доступность JTextField
    public void editBasicContractEditCheckBox(JCheckBox checkBox){
        switch (checkBox.getName()) {
            case "checkBoxCurrency" :{
                for(Component component :staticJPanel.getComponents()){
                    try {
                        if(component.getName().equals("currency")){
                            component.setEnabled(checkBox.isSelected());
                            break;
                        }
                    }catch (Exception e){}
                }
                break;
            }
            case "checkBoxAllSumInEUR" :{
                for(Component component :staticJPanel.getComponents()){
                    try {
                        if(component.getName().equals("allSumInEUR")){
                            component.setEnabled(checkBox.isSelected());
                            break;
                        }
                    }catch (Exception e){}
                }
                break;
            }
            case "checkBoxPrepaymentOr10PercentSum" :{
                for(Component component :staticJPanel.getComponents()){
                    try {
                        if(component.getName().equals("prepaymentOr10PercentSum")){
                            component.setEnabled(checkBox.isSelected());
                            break;
                        }
                    }catch (Exception e){}
                }
                break;
            }
            case "checkBoxPayUpTo50PercentSum" :{
                for(Component component :staticJPanel.getComponents()){
                    try {
                        if(component.getName().equals("payUpTo50PercentSum")){
                            component.setEnabled(checkBox.isSelected());
                            break;
                        }
                    }catch (Exception e){}
                }
                break;
            }
            case "checkBoxPayUpTo100PercentSum" :{
                for(Component component :staticJPanel.getComponents()){
                    try {
                        if(component.getName().equals("payUpTo100PercentSum")){
                            component.setEnabled(checkBox.isSelected());
                            break;
                        }
                    }catch (Exception e){}
                }
                break;
            }
            case "checkBoxAllSumInBYN" :{
                for(Component component :staticJPanel.getComponents()){
                    try {
                        if(component.getName().equals("allSumInBYN")){
                            component.setEnabled(checkBox.isSelected());
                            break;
                        }
                    }catch (Exception e){}
                }
                break;
            }
            case "checkBoxDateCreateContract" :{
                for(Component component :staticJPanel.getComponents()){
                    try {
                        if(component.getName().equals("dateCreateContract")){
                            component.setEnabled(checkBox.isSelected());
                            break;
                        }
                    }catch (Exception e){}
                }
                break;
            }

        }

        staticJPanel.revalidate();
        staticJPanel.repaint();
    }
    //Отоброжает сообщение при этом не обновляет полностью страницу
    public void writeMessage(String message){
        staticJPanel.add(new JLabel(message));
        staticJPanel.revalidate();
        staticJPanel.repaint();
    }


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
