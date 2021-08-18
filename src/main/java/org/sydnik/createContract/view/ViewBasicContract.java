package org.sydnik.createContract.view;

import org.sydnik.createContract.MyController;
import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.exception.DontHaveData;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.util.HashMap;

public class ViewBasicContract implements Display{
    private JPanel staticJPanel;
    private DataClient dataClient;
    private double currencyValue;
    private MyController controller;
    private JTextField allSumInEUR;
    private JTextField payUpTo100PercentSum;
    private JTextField payUpTo50PercentSum;
    private JTextField prepaymentOr10PercentSum;
    private JTextField allSumInBYN;
    private JFormattedTextField dateCreateContract;
    private JComboBox<String> periodOfExecution;
    private JTextField currency;

    public ViewBasicContract(JPanel staticJPanel, DataClient dataClient, double currencyValue, MyController controller) {
        this.staticJPanel = staticJPanel;
        this.dataClient = dataClient;
        this.currencyValue = currencyValue;
        this.controller = controller;
    }

    @Override
    public void display() {
        MaskFormatter dateCreateContractMask = null;
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

        currency = new JTextField();
        currency.setText(String.valueOf(currencyValue));
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
        periodOfExecution = new JComboBox(new String[]{"14 до 25","21 до 29"});
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
        dateCreateContract = new JFormattedTextField(dateCreateContractMask);
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

    public void editBasicContractEditAllSumInEUR(){
        if(!allSumInEUR.getText().equals("")) {
            double cur = Double.parseDouble(currency.getText());
            int allSum = Integer.parseInt(allSumInEUR.getText());
            int prepayment10 = allSum / 10,
                    percent50 = allSum / 2 - prepayment10,
                    percent100 = allSum - prepayment10 - percent50;
            prepaymentOr10PercentSum.setText(String.valueOf(prepayment10));
            payUpTo50PercentSum.setText(String.valueOf(percent50));
            payUpTo100PercentSum.setText(String.valueOf(percent100));
            allSumInBYN.setText(String.valueOf(Math.round(((double) allSum) * cur)));
        }
    }

    public void editBasicContractEditPrepaymentOr10PercentSum(){
        if(!prepaymentOr10PercentSum.getText().equals("")) {
            int allSum = Integer.parseInt(allSumInEUR.getText());
            int prepayment10 = Integer.parseInt(prepaymentOr10PercentSum.getText());
            int percent50 = allSum / 2 - prepayment10;
            if (percent50 < 0) {
                percent50 = 0;
            }
            int percent100 = allSum - prepayment10 - percent50;
            allSumInEUR.setText(String.valueOf(allSum));
            payUpTo50PercentSum.setText(String.valueOf(percent50));
            payUpTo100PercentSum.setText(String.valueOf(percent100));
        }
    }

    public void editBasicContractEditPayUpTo50PercentSum(){
        if(!payUpTo50PercentSum.getText().equals("")) {
            int allSum = Integer.parseInt(allSumInEUR.getText());
            int prepayment10 = Integer.parseInt(prepaymentOr10PercentSum.getText());
            int percent50 = Integer.parseInt(payUpTo50PercentSum.getText());
            int percent100 = allSum - prepayment10 - percent50;
            allSumInEUR.setText(String.valueOf(allSum));
            prepaymentOr10PercentSum.setText(String.valueOf(prepayment10));
            payUpTo100PercentSum.setText(String.valueOf(percent100));
        }
    }

    public void editCurrencyBasicContract(){
        if(!currency.getText().equals("")) {
            allSumInBYN.setText(String.valueOf((double) Math.round((Double.parseDouble(allSumInEUR.getText())) * Double.parseDouble(currency.getText()))));
        }
    }

    @Override
    public HashMap<String, String> getDataForSave() throws DontHaveData {
        if(allSumInEUR.getText().equals("")||allSumInBYN.getText().equals("")||
                prepaymentOr10PercentSum.getText().equals("")||dateCreateContract.getText().equals("  .  .    ")||
                payUpTo50PercentSum.getText().equals("")||payUpTo100PercentSum.getText().equals("")||currency.getText().equals("")){
            throw new DontHaveData("Заполните все поля");
        }
        HashMap<String,String> map = new HashMap<>();
        map.put("dateCreateContract", dateCreateContract.getText());
        map.put("timeProduction", (String) periodOfExecution.getSelectedItem());
        map.put("allSumInEUR", allSumInEUR.getText());
        map.put("allSumInBYN", allSumInBYN.getText());
        map.put("prepaymentOr10PercentSum", prepaymentOr10PercentSum.getText());
        map.put("payUpTo50PercentSum", payUpTo50PercentSum.getText());
        map.put("payUpTo100PercentSum", payUpTo100PercentSum.getText());
        return map;
    }
    public void setCurrencyZero(boolean CorrectOrZero){
        if(CorrectOrZero){
            currency.setText(String.valueOf(currencyValue));
        }
        else {
            currency.setText("0");
        }

    }
}
