package org.sydnik.createContract.view;

import org.sydnik.createContract.MyController;
import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.exception.DontHaveData;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.util.HashMap;
import java.util.Locale;

public class ViewInvoiceDocument extends MainViewContract implements Display {
    private double currencyValue;
    private JTextField currencyInvoiceDocument;
    private JTextField priceInEURInvoiceDocument;
    private JTextField priceBYNInvoiceDocument;
    private JTextField vat20InvoiceDocument;
    private JFormattedTextField createDateInvoiceDocument;
    private JComboBox<String> listBank;

    public ViewInvoiceDocument(JPanel staticJPanel, DataClient dataClient, double currencyValue, MyController controller) {
        super(staticJPanel,dataClient,"Счёт-фактура",controller);
        this.currencyValue = currencyValue;
    }
    @Override
    public void display(){
        startPage();
        MaskFormatter
                dateMask = null;
        try {
            dateMask = new MaskFormatter("##.##.####");
        }
        catch (Exception e){}

        workingWindow.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 0.1;

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.gridwidth = 1;
        workingWindow.add(new JLabel("Курс евро:"),gridBagConstraints);

        currencyInvoiceDocument = new JTextField();
        currencyInvoiceDocument.setText(String.format(Locale.US,"%.4f",currencyValue));
        currencyInvoiceDocument.setName("currencyInvoiceDocument");
        currencyInvoiceDocument.setEnabled(false);
        currencyInvoiceDocument.addKeyListener(controller);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.ipadx = 290;
        workingWindow.add(currencyInvoiceDocument,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.ipadx = 20;
        JCheckBox checkBoxCurrencyInvoiceDocument  = new JCheckBox();
        checkBoxCurrencyInvoiceDocument.setSelected(false);
        checkBoxCurrencyInvoiceDocument.setName("checkBoxCurrencyInvoiceDocument");
        checkBoxCurrencyInvoiceDocument.addActionListener(controller);
        workingWindow.add(checkBoxCurrencyInvoiceDocument,gridBagConstraints);


        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 0;
        workingWindow.add(new JLabel("Сумма в у.е."),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        priceInEURInvoiceDocument = new JTextField();
        priceInEURInvoiceDocument.setName("priceInEURInvoiceDocument");
        priceInEURInvoiceDocument.addKeyListener(controller);
        priceInEURInvoiceDocument.setEnabled(false);
        priceInEURInvoiceDocument.setText(String.valueOf(dataClient.getInvoiceDocument().getPriceInEURInvoiceDocument()));
        workingWindow.add(priceInEURInvoiceDocument,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxPriceInEURInvoiceDocument  = new JCheckBox();
        checkBoxPriceInEURInvoiceDocument.setName("checkBoxPriceInEURInvoiceDocument");
        checkBoxPriceInEURInvoiceDocument.addActionListener(controller);
        workingWindow.add(checkBoxPriceInEURInvoiceDocument,gridBagConstraints);
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 0;
        workingWindow.add(new JLabel("Сумма в рублях"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        priceBYNInvoiceDocument = new JTextField();
        priceBYNInvoiceDocument.setName("priceBYNInvoiceDocument");
        priceBYNInvoiceDocument.addKeyListener(controller);
        priceBYNInvoiceDocument.setEnabled(false);
        priceBYNInvoiceDocument.setText(String.valueOf(dataClient.getInvoiceDocument().getPriceBYNInvoiceDocument()));
        workingWindow.add(priceBYNInvoiceDocument,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxPriceBYNInvoiceDocument  = new JCheckBox();
        checkBoxPriceBYNInvoiceDocument.setName("checkBoxPriceBYNInvoiceDocument");
        checkBoxPriceBYNInvoiceDocument.addActionListener(controller);
        workingWindow.add(checkBoxPriceBYNInvoiceDocument,gridBagConstraints);

        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 0;
        workingWindow.add(new JLabel("НДС 20%"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        vat20InvoiceDocument = new JTextField();
        vat20InvoiceDocument.setName("vat20InvoiceDocument");
        vat20InvoiceDocument.addKeyListener(controller);
        vat20InvoiceDocument.setEnabled(false);
        vat20InvoiceDocument.setText(String.format(Locale.US,"%.2f",dataClient.getInvoiceDocument().getVat20InvoiceDocument()));
        workingWindow.add(vat20InvoiceDocument,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxVat20InvoiceDocument  = new JCheckBox();
        checkBoxVat20InvoiceDocument.setName("checkBoxVat20InvoiceDocument");
        checkBoxVat20InvoiceDocument.addActionListener(controller);
        workingWindow.add(checkBoxVat20InvoiceDocument,gridBagConstraints);

        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 0;
        workingWindow.add(new JLabel("Дата счет-фактуры"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        createDateInvoiceDocument = new JFormattedTextField(dateMask);
        createDateInvoiceDocument.setName("createDateInvoiceDocument");
        createDateInvoiceDocument.setText(String.valueOf(dataClient.getInvoiceDocument().getCreateDateInvoiceDocument()));
        createDateInvoiceDocument.addKeyListener(controller);
        createDateInvoiceDocument.setEnabled(false);
        workingWindow.add(createDateInvoiceDocument,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxCreateDateInvoiceDocument  = new JCheckBox();
        checkBoxCreateDateInvoiceDocument.setName("checkBoxCreateDateInvoiceDocument");
        checkBoxCreateDateInvoiceDocument.addActionListener(controller);
        workingWindow.add(checkBoxCreateDateInvoiceDocument,gridBagConstraints);


        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridx = 0;
        workingWindow.add(new JLabel("Дата договора"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        JFormattedTextField dateCreateBasicContract = new JFormattedTextField(dateMask);
        dateCreateBasicContract.setName("dateCreateBasicContract");
        dateCreateBasicContract.setText(String.valueOf(dataClient.getBasicContract().getDateCreateContract()));
        dateCreateBasicContract.addKeyListener(controller);
        dateCreateBasicContract.setEnabled(false);
        workingWindow.add(dateCreateBasicContract,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxDateCreateBasicContract = new JCheckBox();
        checkBoxDateCreateBasicContract.setName("checkBoxDateCreateBasicContract");
        checkBoxDateCreateBasicContract.addActionListener(controller);
        checkBoxDateCreateBasicContract.setEnabled(false);
        workingWindow.add(checkBoxDateCreateBasicContract,gridBagConstraints);

        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridx = 0;
        workingWindow.add(new JLabel("Банк"),gridBagConstraints);
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
        workingWindow.add(listBank,gridBagConstraints);

        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridx = 0;
        JButton saveDataInvoiceDocument = new JButton("Сохранить");
        saveDataInvoiceDocument.setName("saveDataInvoiceDocument");
        saveDataInvoiceDocument.addActionListener(controller);
        workingWindow.add(saveDataInvoiceDocument,gridBagConstraints);

        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        JButton printInvoiceDocument = new JButton("Распечатать");
        printInvoiceDocument.setName("printInvoiceDocument");
        printInvoiceDocument.addActionListener(controller);
        workingWindow.add(printInvoiceDocument,gridBagConstraints);

        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        JButton openDirectoryWithFile = new JButton("Открыть папку с файлом");
        openDirectoryWithFile.setName("openDirectoryWithFile");
        openDirectoryWithFile.addActionListener(controller);
        workingWindow.add(openDirectoryWithFile,gridBagConstraints);

        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        JButton openFileInvoiceDocument = new JButton("Открыть файл");
        openFileInvoiceDocument.setName("openFileInvoiceDocument");
        openFileInvoiceDocument.addActionListener(controller);
        workingWindow.add(openFileInvoiceDocument,gridBagConstraints);

        if(priceBYNInvoiceDocument.getText().equals("0")){
            fillData();
        }
        endPage();
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

        HashMap<String,String> result = new HashMap<>();
        result.put("priceBYNInvoiceDocument",priceBYNInvoiceDocument.getText());
        result.put("priceInEURInvoiceDocument",priceInEURInvoiceDocument.getText());
        result.put("vat20InvoiceDocument",vat20InvoiceDocument.getText());
        result.put("createDateInvoiceDocument",createDateInvoiceDocument.getText());
        result.put("whichBank", String.valueOf(listBank.getSelectedItem()));
        System.out.println(result.get("whichBank"));
        return result;
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
