package org.sydnik.createContract.view;

import org.sydnik.createContract.MyController;
import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.exception.DontHaveData;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.util.HashMap;
import java.util.Locale;

public class ViewSupplementaryAgreementBasicContract extends MainViewContract implements Display {
    private double currencyValue;
    JTextField currencySumSupplementaryAgreement;
    JTextField allSumInEURSupplementaryAgreement;
    JTextField prepaymentOr10PercentSumSupplementaryAgreement;
    JTextField payUpTo50PercentSumSupplementaryAgreement;
    JTextField payUpTo100PercentSumSupplementaryAgreement;
    JTextField allSumInBYNSupplementaryAgreement;
    JFormattedTextField dateCreateSupplementaryAgreementBasicContract;
    JTextField numberSupplementaryAgreementBasicContract;
    JCheckBox checkBoxAllSumInEURSupplementaryAgreement;

    public ViewSupplementaryAgreementBasicContract(JPanel staticJPanel, DataClient dataClient, double currencyValue, MyController controller) {
        super(staticJPanel,dataClient,"Доп соглашение",controller);
        this.currencyValue = currencyValue;

    }

    @Override
    public void display() {
        startPage();
        MaskFormatter
                dateCreateContractMask = null;
        try {
            dateCreateContractMask = new MaskFormatter("##.##.####");
        }
        catch (Exception e){}

        workingWindow.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        workingWindow.add(new JLabel("Дата Баз Договора"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        JFormattedTextField dateCreateContract = new JFormattedTextField(dateCreateContractMask);
        dateCreateContract.setName("dateCreateContract");
        dateCreateContract.setText(String.valueOf(dataClient.getBasicContract().getDateCreateContract()));
        dateCreateContract.addKeyListener(controller);
        dateCreateContract.setEnabled(false);
        workingWindow.add(dateCreateContract,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxDateCreateContract  = new JCheckBox();
        checkBoxDateCreateContract.setName("checkBoxDateCreateContract");
        checkBoxDateCreateContract.setEnabled(false);
        checkBoxDateCreateContract.addActionListener(controller);
        workingWindow.add(checkBoxDateCreateContract,gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.gridwidth = 1;
        workingWindow.add(new JLabel("Курс евро:"),gridBagConstraints);

        currencySumSupplementaryAgreement = new JTextField();
        currencySumSupplementaryAgreement.setText(String.format(Locale.US,"%.4f",currencyValue));
        currencySumSupplementaryAgreement.setName("currencySumSupplementaryAgreement");
        currencySumSupplementaryAgreement.setEnabled(false);
        currencySumSupplementaryAgreement.addKeyListener(controller);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.ipadx = 300;
        workingWindow.add(currencySumSupplementaryAgreement,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.ipadx = 20;
        JCheckBox checkBoxCurrencySumSupplementaryAgreement  = new JCheckBox();
        checkBoxCurrencySumSupplementaryAgreement.setSelected(false);
        checkBoxCurrencySumSupplementaryAgreement.setName("checkBoxCurrencySumSupplementaryAgreement");
        checkBoxCurrencySumSupplementaryAgreement.addActionListener(controller);
        workingWindow.add(checkBoxCurrencySumSupplementaryAgreement,gridBagConstraints);

        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 0;
        workingWindow.add(new JLabel("Новая сумма договора"),gridBagConstraints);
        allSumInEURSupplementaryAgreement = new JTextField();
        allSumInEURSupplementaryAgreement.setName("allSumInEURSupplementaryAgreement");
        allSumInEURSupplementaryAgreement.setEnabled(false);
        allSumInEURSupplementaryAgreement.setText(String.valueOf(dataClient.getSupplementaryAgreementBasicContract().getAllSumInEURSupplementaryAgreement()));
        allSumInEURSupplementaryAgreement.addKeyListener(controller);
        gridBagConstraints.gridx = 1;
        workingWindow.add(allSumInEURSupplementaryAgreement,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        checkBoxAllSumInEURSupplementaryAgreement  = new JCheckBox();
        checkBoxAllSumInEURSupplementaryAgreement.setName("checkBoxAllSumInEURSupplementaryAgreement");
        checkBoxAllSumInEURSupplementaryAgreement.addActionListener(controller);
        workingWindow.add(checkBoxAllSumInEURSupplementaryAgreement,gridBagConstraints);

        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridx = 0;
        workingWindow.add(new JLabel("Предоплата:"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        prepaymentOr10PercentSumSupplementaryAgreement = new JTextField();
        prepaymentOr10PercentSumSupplementaryAgreement.setName("prepaymentOr10PercentSumSupplementaryAgreement");
        prepaymentOr10PercentSumSupplementaryAgreement.setText(String.valueOf(dataClient.getSupplementaryAgreementBasicContract().getPrepaymentOr10PercentSumSupplementaryAgreement()));
        prepaymentOr10PercentSumSupplementaryAgreement.addKeyListener(controller);
        prepaymentOr10PercentSumSupplementaryAgreement.setEnabled(false);
        workingWindow.add(prepaymentOr10PercentSumSupplementaryAgreement,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxPrepaymentOr10PercentSumSupplementaryAgreement  = new JCheckBox();
        checkBoxPrepaymentOr10PercentSumSupplementaryAgreement.setName("checkBoxPrepaymentOr10PercentSumSupplementaryAgreement");
        checkBoxPrepaymentOr10PercentSumSupplementaryAgreement.addActionListener(controller);
        workingWindow.add(checkBoxPrepaymentOr10PercentSumSupplementaryAgreement,gridBagConstraints);

        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 0;
        workingWindow.add(new JLabel("Оплата до 50%"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        payUpTo50PercentSumSupplementaryAgreement = new JTextField();
        payUpTo50PercentSumSupplementaryAgreement.setName("payUpTo50PercentSumSupplementaryAgreement");
        payUpTo50PercentSumSupplementaryAgreement.setText(String.valueOf(dataClient.getSupplementaryAgreementBasicContract().getPayUpTo50PercentSumSupplementaryAgreement()));
        payUpTo50PercentSumSupplementaryAgreement.addKeyListener(controller);
        payUpTo50PercentSumSupplementaryAgreement.setEnabled(false);
        workingWindow.add(payUpTo50PercentSumSupplementaryAgreement,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxPayUpTo50PercentSumSupplementaryAgreement  = new JCheckBox();
        checkBoxPayUpTo50PercentSumSupplementaryAgreement.setName("checkBoxPayUpTo50PercentSumSupplementaryAgreement");
        checkBoxPayUpTo50PercentSumSupplementaryAgreement.addActionListener(controller);
        workingWindow.add(checkBoxPayUpTo50PercentSumSupplementaryAgreement,gridBagConstraints);

        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridx = 0;
        workingWindow.add(new JLabel("Оплата до 100%"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        payUpTo100PercentSumSupplementaryAgreement = new JTextField();
        payUpTo100PercentSumSupplementaryAgreement.setName("payUpTo100PercentSumSupplementaryAgreement");
        payUpTo100PercentSumSupplementaryAgreement.setText(String.valueOf(dataClient.getSupplementaryAgreementBasicContract().getPayUpTo100PercentSumSupplementaryAgreement()));
        payUpTo100PercentSumSupplementaryAgreement.addKeyListener(controller);
        payUpTo100PercentSumSupplementaryAgreement.setEnabled(false);
        workingWindow.add(payUpTo100PercentSumSupplementaryAgreement,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxPayUpTo100PercentSumSupplementaryAgreement  = new JCheckBox();
        checkBoxPayUpTo100PercentSumSupplementaryAgreement.setName("checkBoxPayUpTo100PercentSumSupplementaryAgreement");
        checkBoxPayUpTo100PercentSumSupplementaryAgreement.addActionListener(controller);
        workingWindow.add(checkBoxPayUpTo100PercentSumSupplementaryAgreement,gridBagConstraints);

        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridx = 0;
        workingWindow.add(new JLabel("Сумма в белках"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        allSumInBYNSupplementaryAgreement = new JTextField();
        allSumInBYNSupplementaryAgreement.setName("allSumInBYNSupplementaryAgreement");
        allSumInBYNSupplementaryAgreement.setText(String.valueOf(dataClient.getSupplementaryAgreementBasicContract().getAllSumInBYNSupplementaryAgreement()));
        allSumInBYNSupplementaryAgreement.addKeyListener(controller);
        allSumInBYNSupplementaryAgreement.setEnabled(false);
        workingWindow.add(allSumInBYNSupplementaryAgreement,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxAllSumInBYNSupplementaryAgreement  = new JCheckBox();
        checkBoxAllSumInBYNSupplementaryAgreement.setName("checkBoxAllSumInBYNSupplementaryAgreement");
        checkBoxAllSumInBYNSupplementaryAgreement.addActionListener(controller);
        workingWindow.add(checkBoxAllSumInBYNSupplementaryAgreement,gridBagConstraints);

        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridx = 0;
        workingWindow.add(new JLabel("Дата подписания"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        dateCreateSupplementaryAgreementBasicContract = new JFormattedTextField(dateCreateContractMask);
        dateCreateSupplementaryAgreementBasicContract.setName("dateCreateSupplementaryAgreementBasicContract");
        dateCreateSupplementaryAgreementBasicContract.setText(String.valueOf(dataClient.getSupplementaryAgreementBasicContract().getDateCreateSupplementaryAgreementBasicContract()));
        dateCreateSupplementaryAgreementBasicContract.addKeyListener(controller);
        dateCreateSupplementaryAgreementBasicContract.setEnabled(false);
        workingWindow.add(dateCreateSupplementaryAgreementBasicContract,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxDateCreateSupplementaryAgreementBasicContract  = new JCheckBox();
        checkBoxDateCreateSupplementaryAgreementBasicContract.setName("checkBoxDateCreateSupplementaryAgreementBasicContract");
        checkBoxDateCreateSupplementaryAgreementBasicContract.addActionListener(controller);
        workingWindow.add(checkBoxDateCreateSupplementaryAgreementBasicContract,gridBagConstraints);

        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridx = 0;
        workingWindow.add(new JLabel("Номер доп соглашения"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        numberSupplementaryAgreementBasicContract = new JTextField();
        numberSupplementaryAgreementBasicContract.setName("numberSupplementaryAgreementBasicContract");
        numberSupplementaryAgreementBasicContract.setText(String.valueOf(dataClient.getSupplementaryAgreementBasicContract().getNumberSupplementaryAgreementBasicContract()));
        numberSupplementaryAgreementBasicContract.addKeyListener(controller);
        numberSupplementaryAgreementBasicContract.setEnabled(false);
        workingWindow.add(numberSupplementaryAgreementBasicContract,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        JCheckBox checkBoxNumberSupplementaryAgreementBasicContract  = new JCheckBox();
        checkBoxNumberSupplementaryAgreementBasicContract.setName("checkBoxNumberSupplementaryAgreementBasicContract");
        checkBoxNumberSupplementaryAgreementBasicContract.addActionListener(controller);
        workingWindow.add(checkBoxNumberSupplementaryAgreementBasicContract,gridBagConstraints);

        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridx = 0;
        JButton saveDataSupplementaryAgreementBasicContract = new JButton("Сохранить");
        saveDataSupplementaryAgreementBasicContract.setName("saveDataSupplementaryAgreementBasicContract");
        saveDataSupplementaryAgreementBasicContract.addActionListener(controller);
        workingWindow.add(saveDataSupplementaryAgreementBasicContract,gridBagConstraints);

        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        JButton printSupplementaryAgreementBasicContract = new JButton("Распечатать 2 раза");
        printSupplementaryAgreementBasicContract.setName("printSupplementaryAgreementBasicContract");
        printSupplementaryAgreementBasicContract.addActionListener(controller);
        workingWindow.add(printSupplementaryAgreementBasicContract,gridBagConstraints);

        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        JButton openDirectoryWithFile = new JButton("Открыть папку с файлом");
        openDirectoryWithFile.setName("openDirectoryWithFile");
        openDirectoryWithFile.addActionListener(controller);
        workingWindow.add(openDirectoryWithFile,gridBagConstraints);

        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        JButton openFileSupplementaryAgreementBasicContract = new JButton("Открыть файл");
        openFileSupplementaryAgreementBasicContract.setName("openFileSupplementaryAgreementBasicContract");
        openFileSupplementaryAgreementBasicContract.addActionListener(controller);
        workingWindow.add(openFileSupplementaryAgreementBasicContract,gridBagConstraints);

        endPage();
        if(dataClient.getSupplementaryAgreementBasicContract().getAllSumInEURSupplementaryAgreement()==0){
            fillData();
            allSumInEURSupplementaryAgreement.requestFocusInWindow();
        }
    }

    public void editAllEURSumAgreementBasicContract (){
        if(!allSumInEURSupplementaryAgreement.getText().equals("")) {
            int allSumEUR = Integer.parseInt(allSumInEURSupplementaryAgreement.getText());
            double currency = Double.parseDouble(currencySumSupplementaryAgreement.getText());
            int sumInBYN = (int) Math.round((double) currency * allSumEUR);
            allSumInBYNSupplementaryAgreement.setText(String.valueOf(sumInBYN));
            editPrepaymentOr10PercentSumSupplementaryAgreement();
        }
    }
    public void editPrepaymentOr10PercentSumSupplementaryAgreement (){
        if(!prepaymentOr10PercentSumSupplementaryAgreement.getText().equals("")) {
            int prepayment = Integer.parseInt(prepaymentOr10PercentSumSupplementaryAgreement.getText()),
                    allSumEUR = Integer.parseInt(allSumInEURSupplementaryAgreement.getText()),
                    percent50 = allSumEUR / 2 - prepayment;
            if (percent50 < 0) {
                percent50 = 0;
            }
            payUpTo50PercentSumSupplementaryAgreement.setText(String.valueOf(percent50));
            editPayUpTo50percentAgreementBasicContract();
        }

    }
    public void editPayUpTo50percentAgreementBasicContract (){
        if(!payUpTo50PercentSumSupplementaryAgreement.getText().equals("")) {
            int prepayment = Integer.parseInt(prepaymentOr10PercentSumSupplementaryAgreement.getText()),
                    allSumEUR = Integer.parseInt(allSumInEURSupplementaryAgreement.getText()),
                    percent50 = Integer.parseInt(payUpTo50PercentSumSupplementaryAgreement.getText()),
                    percent100 = allSumEUR - prepayment - percent50;
            payUpTo100PercentSumSupplementaryAgreement.setText(String.valueOf(percent100));
        }
    }

    public void editCurrencyAgreementBasicContract(){
        if(!currencySumSupplementaryAgreement.getText().equals("")) {
            double allSumEUR = Double.parseDouble(allSumInEURSupplementaryAgreement.getText());
            allSumInBYNSupplementaryAgreement.setText(String.valueOf(Math.round((allSumEUR * Double.parseDouble(currencySumSupplementaryAgreement.getText())))));
            staticJPanel.revalidate();
            staticJPanel.repaint();
        }

    }
    public void setCurrencyZero(boolean CorrectOrZero){
        if(CorrectOrZero){
            currencySumSupplementaryAgreement.setText(String.valueOf(currencyValue));
        }
        else {
            currencySumSupplementaryAgreement.setText("0");
        }

    }




    @Override
    public HashMap<String, String> getDataForSave() throws DontHaveData {
        if(numberSupplementaryAgreementBasicContract.getText().equals("")||allSumInEURSupplementaryAgreement.getText().equals("")||
                allSumInBYNSupplementaryAgreement.getText().equals("")||dateCreateSupplementaryAgreementBasicContract.getText().equals("  .  .    ")||
                prepaymentOr10PercentSumSupplementaryAgreement.getText().equals("")||payUpTo50PercentSumSupplementaryAgreement.getText().equals("")||
                payUpTo100PercentSumSupplementaryAgreement.getText().equals("")|| currencySumSupplementaryAgreement.equals("")){
            throw new DontHaveData("Заполните все поля");
        }
        HashMap<String,String> result = new HashMap<>();
        result.put("dateCreateSupplementaryAgreementBasicContract",dateCreateSupplementaryAgreementBasicContract.getText());
        result.put("numberSupplementaryAgreementBasicContract",numberSupplementaryAgreementBasicContract.getText());
        result.put("allSumInEURSupplementaryAgreement",allSumInEURSupplementaryAgreement.getText());
        result.put("allSumInBYNSupplementaryAgreement",allSumInBYNSupplementaryAgreement.getText());
        result.put("prepaymentOr10PercentSumSupplementaryAgreement",prepaymentOr10PercentSumSupplementaryAgreement.getText());
        result.put("payUpTo50PercentSumSupplementaryAgreement",payUpTo50PercentSumSupplementaryAgreement.getText());
        result.put("payUpTo100PercentSumSupplementaryAgreement",payUpTo100PercentSumSupplementaryAgreement.getText());
        return result;
    }

    private void fillData(){
        checkBoxAllSumInEURSupplementaryAgreement.setSelected(true);
        allSumInEURSupplementaryAgreement.setEnabled(true);
        prepaymentOr10PercentSumSupplementaryAgreement.setText(String.valueOf(dataClient.getBasicContract().getPrepaymentOr10PercentSum()));
        numberSupplementaryAgreementBasicContract.setText("1");
    }
}
