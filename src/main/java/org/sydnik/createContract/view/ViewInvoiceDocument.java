package org.sydnik.createContract.view;

import org.sydnik.createContract.MyController;
import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.exception.DontHaveData;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.util.HashMap;
import java.util.Locale;

public class ViewInvoiceDocument implements Display {
    private JPanel staticJPanel;
    private DataClient dataClient;
    private double currencyValue;
    private MyController controller;
    private JTextField currencyInvoiceDocument;
    private JTextField priceInEURInvoiceDocument;
    private JTextField priceBYNInvoiceDocument;
    private JTextField vat20InvoiceDocument;
    private JFormattedTextField createDateInvoiceDocument;
    private JComboBox<String> listBank;

    public ViewInvoiceDocument(JPanel staticJPanel, DataClient dataClient, double currencyValue, MyController controller) {
        this.staticJPanel = staticJPanel;
        this.dataClient = dataClient;
        this.currencyValue = currencyValue;
        this.controller = controller;
    }
    @Override
    public void display(){
        MaskFormatter
                dateMask = null;
        try {
            dateMask = new MaskFormatter("##.##.####");
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
        JLabel j4 = new JLabel("Счет-фактура");
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

        currencyInvoiceDocument = new JTextField();
        currencyInvoiceDocument.setText(String.format(Locale.US,"%.4f",currencyValue));
        currencyInvoiceDocument.setName("currencyInvoiceDocument");
        currencyInvoiceDocument.setEnabled(false);
        currencyInvoiceDocument.addKeyListener(controller);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.ipadx = 290;
        staticJPanel.add(currencyInvoiceDocument,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.ipadx = 20;
        JCheckBox checkBoxCurrencyInvoiceDocument  = new JCheckBox();
        checkBoxCurrencyInvoiceDocument.setSelected(false);
        checkBoxCurrencyInvoiceDocument.setName("checkBoxCurrencyInvoiceDocument");
        checkBoxCurrencyInvoiceDocument.addActionListener(controller);
        staticJPanel.add(checkBoxCurrencyInvoiceDocument,gridBagConstraints);


        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 0;
        staticJPanel.add(new JLabel("Сумма в у.е."),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        priceInEURInvoiceDocument = new JTextField();
        priceInEURInvoiceDocument.setName("priceInEURInvoiceDocument");
        priceInEURInvoiceDocument.addKeyListener(controller);
        priceInEURInvoiceDocument.setEnabled(false);
        priceInEURInvoiceDocument.setText(String.valueOf(dataClient.getInvoiceDocument().getPriceInEURInvoiceDocument()));
        staticJPanel.add(priceInEURInvoiceDocument,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxPriceInEURInvoiceDocument  = new JCheckBox();
        checkBoxPriceInEURInvoiceDocument.setName("checkBoxPriceInEURInvoiceDocument");
        checkBoxPriceInEURInvoiceDocument.addActionListener(controller);
        staticJPanel.add(checkBoxPriceInEURInvoiceDocument,gridBagConstraints);
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 0;
        staticJPanel.add(new JLabel("Сумма в рублях"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        priceBYNInvoiceDocument = new JTextField();
        priceBYNInvoiceDocument.setName("priceBYNInvoiceDocument");
        priceBYNInvoiceDocument.addKeyListener(controller);
        priceBYNInvoiceDocument.setEnabled(false);
        priceBYNInvoiceDocument.setText(String.valueOf(dataClient.getInvoiceDocument().getPriceBYNInvoiceDocument()));
        staticJPanel.add(priceBYNInvoiceDocument,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxPriceBYNInvoiceDocument  = new JCheckBox();
        checkBoxPriceBYNInvoiceDocument.setName("checkBoxPriceBYNInvoiceDocument");
        checkBoxPriceBYNInvoiceDocument.addActionListener(controller);
        staticJPanel.add(checkBoxPriceBYNInvoiceDocument,gridBagConstraints);

        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 0;
        staticJPanel.add(new JLabel("НДС 20%"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        vat20InvoiceDocument = new JTextField();
        vat20InvoiceDocument.setName("vat20InvoiceDocument");
        vat20InvoiceDocument.addKeyListener(controller);
        vat20InvoiceDocument.setEnabled(false);
        vat20InvoiceDocument.setText(String.format(Locale.US,"%.2f",dataClient.getInvoiceDocument().getVat20InvoiceDocument()));
        staticJPanel.add(vat20InvoiceDocument,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxVat20InvoiceDocument  = new JCheckBox();
        checkBoxVat20InvoiceDocument.setName("checkBoxVat20InvoiceDocument");
        checkBoxVat20InvoiceDocument.addActionListener(controller);
        staticJPanel.add(checkBoxVat20InvoiceDocument,gridBagConstraints);

        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridx = 0;
        staticJPanel.add(new JLabel("Дата счет-фактуры"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        createDateInvoiceDocument = new JFormattedTextField(dateMask);
        createDateInvoiceDocument.setName("createDateInvoiceDocument");
        createDateInvoiceDocument.setText(String.valueOf(dataClient.getInvoiceDocument().getCreateDateInvoiceDocument()));
        createDateInvoiceDocument.addKeyListener(controller);
        createDateInvoiceDocument.setEnabled(false);
        staticJPanel.add(createDateInvoiceDocument,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxCreateDateInvoiceDocument  = new JCheckBox();
        checkBoxCreateDateInvoiceDocument.setName("checkBoxCreateDateInvoiceDocument");
        checkBoxCreateDateInvoiceDocument.addActionListener(controller);
        staticJPanel.add(checkBoxCreateDateInvoiceDocument,gridBagConstraints);


        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridx = 0;
        staticJPanel.add(new JLabel("Дата договора"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        JFormattedTextField dateCreateBasicContract = new JFormattedTextField(dateMask);
        dateCreateBasicContract.setName("dateCreateSupplementaryAgreementUpSaleContract");
        dateCreateBasicContract.setText(String.valueOf(dataClient.getSupplementaryAgreementUpSaleContract().getDateCreateSupplementaryAgreementUpSaleContract()));
        dateCreateBasicContract.addKeyListener(controller);
        dateCreateBasicContract.setEnabled(false);
        staticJPanel.add(dateCreateBasicContract,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxDateCreateBasicContract = new JCheckBox();
        checkBoxDateCreateBasicContract.setName("checkBoxDateCreateBasicContract");
        checkBoxDateCreateBasicContract.addActionListener(controller);
        checkBoxDateCreateBasicContract.setEnabled(false);
        staticJPanel.add(checkBoxDateCreateBasicContract,gridBagConstraints);

        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridx = 0;
        staticJPanel.add(new JLabel("Банк"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        listBank = new JComboBox(new String[]{"Беларусбанк","Любой банк"});
        listBank.setName("timeProduction");
        if (dataClient.getInvoiceDocument().getWhichBank() == null){
            listBank.setSelectedIndex(0);
        }
        else if(dataClient.getInvoiceDocument().getWhichBank().equals("Любой банк")){
            listBank.setSelectedIndex(1);
        }
        else {
            listBank.setSelectedIndex(0);
        }
        staticJPanel.add(listBank,gridBagConstraints);

        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridx = 0;
        JButton saveDataInvoiceDocument = new JButton("Сохранить");
        saveDataInvoiceDocument.setName("saveDataInvoiceDocument");
        saveDataInvoiceDocument.addActionListener(controller);
        staticJPanel.add(saveDataInvoiceDocument,gridBagConstraints);

        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        JButton printInvoiceDocument = new JButton("Распечатать");
        printInvoiceDocument.setName("printInvoiceDocument");
        printInvoiceDocument.addActionListener(controller);
        staticJPanel.add(printInvoiceDocument,gridBagConstraints);

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
        JButton openFileInvoiceDocument = new JButton("Открыть файл");
        openFileInvoiceDocument.setName("openFileInvoiceDocument");
        openFileInvoiceDocument.addActionListener(controller);
        staticJPanel.add(openFileInvoiceDocument,gridBagConstraints);

        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipady = 190;
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

        if(priceBYNInvoiceDocument.getText().equals("0")){
            fillData();
        }
        staticJPanel.revalidate();
        staticJPanel.repaint();
    }

    public void editPriceInEURInvoiceDocument(){
        if(!priceInEURInvoiceDocument.getText().equals("")) {
            int priceInEUR = Integer.parseInt(priceInEURInvoiceDocument.getText());
            int priceInBYN = (int) Math.round(((double) priceInEUR) * currencyValue);
            double vat20 = (double) Math.round((double) priceInBYN / 6 * 100) / 100;
            priceBYNInvoiceDocument.setText(String.valueOf(priceInBYN));
            vat20InvoiceDocument.setText(String.format(Locale.US, "%.2f", vat20));
        }

    }
    public void editPriceBYNInvoiceDocument() {
        if(!priceBYNInvoiceDocument.getText().equals("")) {
            double cur = Double.parseDouble(currencyInvoiceDocument.getText());
            int priceInBYN = Integer.parseInt(priceBYNInvoiceDocument.getText());
            double vat20 = (double) Math.round((double) priceInBYN / 6 * 100) / 100;
            int priceInEUR = (int) Math.round((double) priceInBYN / cur);
            vat20InvoiceDocument.setText(String.format(Locale.US, "%.2f", vat20));
            priceInEURInvoiceDocument.setText(String.valueOf(priceInEUR));
        }

    }
    public void editCurrencyInvoiceDocument() {
        if(!currencyInvoiceDocument.getText().equals("")) {
            double cur = Double.parseDouble(currencyInvoiceDocument.getText());
            int priceInEUR = Integer.parseInt(priceInEURInvoiceDocument.getText());
            int priceInBYN = (int) Math.round(((double) priceInEUR)*cur);
            double vat20 = (double) Math.round((double)priceInBYN/6*100)/100;
            priceBYNInvoiceDocument.setText(String.valueOf(priceInBYN));
            vat20InvoiceDocument.setText(String.format(Locale.US,"%.2f",vat20));
        }

    }

    @Override
    public HashMap<String, String> getDataForSave() throws DontHaveData {
        if(priceInEURInvoiceDocument.getText().equals("")||priceBYNInvoiceDocument.getText().equals("")||
                vat20InvoiceDocument.getText().equals("")||createDateInvoiceDocument.getText().equals("  .  .    ")){
            throw new DontHaveData("Заполните все поля");
        }

        HashMap<String,String> map = new HashMap<>();
        map.put("priceBYNInvoiceDocument",priceBYNInvoiceDocument.getText());
        map.put("priceInEURInvoiceDocument",priceInEURInvoiceDocument.getText());
        map.put("vat20InvoiceDocument",vat20InvoiceDocument.getText());
        map.put("createDateInvoiceDocument",createDateInvoiceDocument.getText());
        map.put("whichBank", String.valueOf(listBank.getSelectedItem()));
        System.out.println(map.get("whichBank"));
        return map;
    }

    private void fillData(){
        int priceInEUR = dataClient.getBasicContract().getAllSumInEUR()-dataClient.getBasicContract().getPrepaymentOr10PercentSum();
        int priceInBYN = (int) Math.round(((double) priceInEUR)*currencyValue);
        double vat20 = (double) Math.round((double)priceInBYN/6*100)/100;
        priceInEURInvoiceDocument.setText(String.valueOf(priceInEUR));
        priceBYNInvoiceDocument.setText(String.valueOf(priceInBYN));
        vat20InvoiceDocument.setText(String.format(Locale.US,"%.2f",vat20));
    }
    public void setCurrencyZero(boolean CorrectOrZero){
        if(CorrectOrZero){
            currencyInvoiceDocument.setText(String.valueOf(currencyValue));
        }
        else {
            currencyInvoiceDocument.setText("0");
        }

    }

}
