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
    public static final int HEIGHT_WINDOW_APPS = 600;
    public static final int WIDTH_WINDOW_APPS = 500;

    private MyController controller;
    private JPanel staticJPanel;


    public MyView() {
        super("CreateContract");
        setSize(WIDTH_WINDOW_APPS, HEIGHT_WINDOW_APPS);
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
        ViewSetting viewSetting = new ViewSetting(controller,staticJPanel,salesManager);
        viewSetting.display();
        controller.setDisplay(viewSetting);
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
        gridBagConstraints.gridwidth = 2;
        staticJPanel.add(new JLabel("Добавить файлы для распила"),gridBagConstraints);

        gridBagConstraints.ipadx = 70;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridwidth = 1;
        JButton viewCreateFileForCutting = new JButton("Создать");
        viewCreateFileForCutting.addActionListener(controller);
        viewCreateFileForCutting.setName("viewCreateFileForCutting");
        staticJPanel.add(viewCreateFileForCutting,gridBagConstraints);

        gridBagConstraints.ipadx = 40;
        gridBagConstraints.ipady = 210;
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
    //jnj,hfpbnm
    public void displayNewClient(){
        ViewMainDataClient view = new ViewMainDataClient(staticJPanel,null,controller);
        view.display();
        controller.setDisplay(view);
    }
    public void displayEditDataAboutClient(DataClient dataClient){
        ViewMainDataClient view = new ViewMainDataClient(staticJPanel,dataClient,controller);
        view.display();
        controller.setDisplay(view);
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
    public void displayCreateFileForCutting(DataClient dataClient){
        ViewCreateFileForCutting viewCreateFileForCutting = new ViewCreateFileForCutting(staticJPanel,dataClient,controller);
        viewCreateFileForCutting.display();
        controller.setDisplay(viewCreateFileForCutting);
    }
    //Общие методы которые работают везде
    public void refreshCheckBox(JCheckBox checkBox){
        String obj = checkBox.getName().replace("checkBox","");
        String objWithSmallLitter = Character.toLowerCase(obj.charAt(0)) + obj.substring(1);
        for(Component component :((JPanel)(staticJPanel.getComponent(MainViewContract.INDEX_WORK_WINDOW))).getComponents()){
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
