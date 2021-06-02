package org.sydnik.createContract;

import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.data.SalesManager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;

public class MyView extends JFrame {
    private MyController controller;
    private static JPanel staticJPanel;


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
        MaskFormatter numberPowerOfAttorneyFormatter = null;
        MaskFormatter datePowerOfAttorneyFormatter = null;
        JFormattedTextField numberPowerOfAttorney = null;
        JFormattedTextField datePowerOfAttorney = null;
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
        staticJPanel.add(numberPowerOfAttorney);
        staticJPanel.add(new JLabel("До какой даты действует :"));
        datePowerOfAttorney.setName("datePowerOfAttorney");
        datePowerOfAttorney.setText(salesManager.getDatePowerOfAttorney());
        staticJPanel.add(datePowerOfAttorney);

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

        JButton save = new JButton("Сохранить");
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
    public void listClientsAndSelect(String[] list) {
        staticJPanel.removeAll();
        staticJPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        JLabel jl = new JLabel("Поиск клиента ");
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        staticJPanel.add(jl,gridBagConstraints);

        JTextField searchClient = new JTextField();
        searchClient.setName("searchClient");
        gridBagConstraints.gridx = 1;
        gridBagConstraints.ipadx = 275;
        staticJPanel.add(searchClient,gridBagConstraints);

        JButton searchClientButton = new JButton("Найти");
        searchClientButton.setName("searchClientButton");
        searchClientButton.addActionListener(controller);
        gridBagConstraints.gridx     = 2;
        gridBagConstraints.ipadx     = 20;
        staticJPanel.add(searchClientButton, gridBagConstraints);




        JButton mainPage = new JButton("Главная страница");
        mainPage.setName("mainPage");
        mainPage.addActionListener(controller);
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

        staticJPanel.revalidate();
        staticJPanel.repaint();
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
    //Данные

    //Отоброжает сообщение при этом не обновляет полностью страницу
    public void writeMessage(String message){
        staticJPanel.add(new JLabel(message));
        staticJPanel.revalidate();
        staticJPanel.repaint();
    }




}
