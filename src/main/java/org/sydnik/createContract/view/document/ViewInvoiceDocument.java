package org.sydnik.createContract.view.document;

import org.sydnik.createContract.MyController;
import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.exception.DontHaveData;
import org.sydnik.createContract.myComponent.*;
import org.sydnik.createContract.view.Display;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.util.HashMap;
import java.util.Locale;

public class ViewInvoiceDocument extends MainViewContract implements Display {
    private double currencyValue;
    private MyTextField currencyInvoiceDocument;
    private MyTextField priceInEURInvoiceDocument;
    private MyTextField priceBYNInvoiceDocument;
    private MyTextField vat20InvoiceDocument;
    private MyFormattedTextField createDateInvoiceDocument;
    private JComboBox<String> listBank;

    public ViewInvoiceDocument(JPanel staticJPanel, DataClient dataClient, double currencyValue, MyController controller) {
        super(staticJPanel,dataClient,"Счёт-фактура",controller);
        this.currencyValue = currencyValue;
    }
    @Override
    public void display(){
        int row = 0;
        startPage();
        MaskFormatter
                dateMask = null;
        try {
            dateMask = new MaskFormatter("##.##.####");
        }
        catch (Exception e){}

        workingWindow.setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        grid.weightx = 0.1;

        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.anchor = GridBagConstraints.WEST;

        grid.gridy = row++;
        grid.gridx = 0;
        grid.ipadx = 100;
        grid.gridwidth = 1;
        workingWindow.add(new JLabel("Курс евро:"),grid);
        currencyInvoiceDocument = new MyTextField(ValueTextField.RATE_INVOICE_DOCUMENT,
                String.valueOf(currencyValue),false,controller);
        grid.gridx = 1;
        grid.ipadx = 290;
        workingWindow.add(currencyInvoiceDocument,grid);
        grid.gridx = 2;
        grid.ipadx = 20;
        workingWindow.add(new MyCheckBox(currencyInvoiceDocument,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        grid.ipadx = 0;
        workingWindow.add(new JLabel("Сумма в у.е."),grid);
        grid.gridx = 1;
        priceInEURInvoiceDocument = new MyTextField(ValueTextField.PRICE_IN_EUR_INVOICE_DOCUMENT,
                String.valueOf(dataClient.getInvoiceDocument().getPriceInEUR()),false,controller);
        workingWindow.add(priceInEURInvoiceDocument,grid);
        grid.gridx = 2;
        workingWindow.add(new MyCheckBox(priceInEURInvoiceDocument,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        grid.ipadx = 0;
        workingWindow.add(new JLabel("Сумма в рублях"),grid);
        grid.gridx = 1;
        priceBYNInvoiceDocument = new MyTextField(ValueTextField.PRICE_BYN_INVOICE_DOCUMENT,
                String.valueOf(dataClient.getInvoiceDocument().getPriceBYN()),false,controller);
        workingWindow.add(priceBYNInvoiceDocument,grid);
        grid.gridx = 2;
        workingWindow.add(new MyCheckBox(priceBYNInvoiceDocument,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        grid.ipadx = 0;
        workingWindow.add(new JLabel("НДС 20%"),grid);
        grid.gridx = 1;
        vat20InvoiceDocument = new MyTextField(ValueTextField.VAT_20_INVOICE_DOCUMENT,
                String.format(Locale.US,"%.2f",dataClient.getInvoiceDocument().getVat20()),false,controller);
        workingWindow.add(vat20InvoiceDocument,grid);
        grid.gridx = 2;
        workingWindow.add(new MyCheckBox(vat20InvoiceDocument,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        workingWindow.add(new JLabel("Дата счет-фактуры"),grid);
        grid.gridx = 1;
        createDateInvoiceDocument = new MyFormattedTextField(ValueTextField.CREATE_DATE_INVOICE_DOCUMENT,dateMask,
                String.valueOf(dataClient.getInvoiceDocument().getCreateDate()),false,controller);
        workingWindow.add(createDateInvoiceDocument,grid);
        grid.gridx = 2;
        workingWindow.add(new MyCheckBox(createDateInvoiceDocument,controller),grid);


        grid.gridy = row++;
        grid.gridx = 0;
        workingWindow.add(new JLabel("Дата договора"),grid);
        grid.gridx = 1;
        JTextField dateCreateBasicContract = new JTextField(String.valueOf(dataClient.getBasicContract().getDateCreate()));
        dateCreateBasicContract.setEnabled(false);
        workingWindow.add(dateCreateBasicContract,grid);
        grid.gridx = 2;
        JCheckBox forBeauty = new JCheckBox();
        forBeauty.setEnabled(false);
        workingWindow.add(forBeauty,grid);

        grid.gridy = row++;
        grid.gridx = 0;
        workingWindow.add(new JLabel("Банк"),grid);
        grid.gridx = 1;
        listBank = new JComboBox(new String[]{"Беларусбанк","Любой банк"});
        listBank.setName("timeProduction");
        if(dataClient.getInvoiceDocument().getWhichBank().equals("Любой банк")){
            listBank.setSelectedIndex(1);
        }
        else {
            listBank.setSelectedIndex(0);
        }
        workingWindow.add(listBank,grid);

        grid.gridy = row++;
        grid.gridx = 0;
        workingWindow.add(new MyButton(ValueButton.SAVE_DATA_INVOICE_DOCUMENT,controller),grid);
        grid.gridx = 1;
        grid.gridwidth = 2;
        workingWindow.add(new MyButton(ValueButton.PRINT_INVOICE_DOCUMENT,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        grid.gridwidth = 1;
        workingWindow.add(new MyButton(ValueButton.OPEN_DIRECTORY_WITH_FILE,controller),grid);
        grid.gridx = 1;
        grid.gridwidth = 2;
        workingWindow.add(new MyButton(ValueButton.OPEN_FILE_INVOICE_DOCUMENT,controller),grid);

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
    public void setCurrencyZero(boolean CorrectOrZero){
        if(CorrectOrZero){
            currencyInvoiceDocument.setText(String.valueOf(currencyValue));
        }
        else {
            currencyInvoiceDocument.setText("0");
        }

    }

    @Override
    public HashMap<String, String> getData() throws DontHaveData {
        if(priceInEURInvoiceDocument.getText().equals("")||
                priceBYNInvoiceDocument.getText().equals("")||
                vat20InvoiceDocument.getText().equals("")||
                createDateInvoiceDocument.getText().equals("  .  .    "))
        {
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
        int priceInEUR = dataClient.getBasicContract().getAllSumInEUR()-dataClient.getBasicContract().getPrepayment();
        int priceInBYN = (int) Math.round(((double) priceInEUR)*currencyValue);
        double vat20 = (double) Math.round((double)priceInBYN/6*100)/100;
        priceInEURInvoiceDocument.setText(String.valueOf(priceInEUR));
        priceBYNInvoiceDocument.setText(String.valueOf(priceInBYN));
        vat20InvoiceDocument.setText(String.format(Locale.US,"%.2f",vat20));
    }


}
