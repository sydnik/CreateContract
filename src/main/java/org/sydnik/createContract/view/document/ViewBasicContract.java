package org.sydnik.createContract.view.document;

import org.sydnik.createContract.MyController;
import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.exception.DontHaveData;
import org.sydnik.createContract.myComponent.*;
import org.sydnik.createContract.view.Display;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.HashMap;

public class ViewBasicContract extends MainViewContract implements Display, DataForViber, InfoForPayment {
    private double currencyValue;
    private MyTextField currency;
    private MyTextField allSumInEUR;
    private MyTextField payUpTo100PercentSum;
    private MyTextField payUpTo50PercentSum;
    private MyTextField prepaymentOr10PercentSum;
    private MyTextField allSumInBYN;
    private MyFormattedTextField dateCreateContract;
    private JComboBox<String> periodOfExecution;
    private MyButton[] buttonsForDataForViber;
    private MyCheckBox checkBoxAllSumInEUR;

    public ViewBasicContract(JPanel staticJPanel, DataClient dataClient, double currencyValue, MyController controller) {
        super(staticJPanel,dataClient,"Базовый договор",controller);
        this.currencyValue = currencyValue;
    }

    @Override
    public void display() {
        startPage();
        int row = 0;
        MaskFormatter dateCreateContractMask = null;
        try {
            dateCreateContractMask = new MaskFormatter("##.##.####");
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
        grid.gridx = 1;
        grid.ipadx = 290;
        currency = new MyTextField(ValueTextField.RATE,String.valueOf(currencyValue),false,controller);
        workingWindow.add(currency,grid);
        grid.gridx = 2;
        grid.ipadx = 20;
        workingWindow.add(new MyCheckBox(currency,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        grid.ipadx = 0;
        workingWindow.add(new JLabel("Сумма договора"),grid);
        allSumInEUR = new MyTextField(ValueTextField.ALL_SUM_IN_EUR,"",true,controller);
        grid.gridx = 1;
        workingWindow.add(allSumInEUR,grid);
        grid.gridx = 2;
        checkBoxAllSumInEUR  = new MyCheckBox(allSumInEUR,controller);
        workingWindow.add(checkBoxAllSumInEUR,grid);

        grid.gridy = row++;
        grid.gridx = 0;
        workingWindow.add(new JLabel("Срок исполнения"),grid);
        grid.gridx = 1;
        periodOfExecution = new JComboBox(new String[]{"14 до 25","21 до 29"});
        workingWindow.add(periodOfExecution,grid);

        grid.gridy = row++;
        grid.gridx = 0;
        workingWindow.add(new JLabel("Предоплата:"),grid);
        grid.gridx = 1;
        prepaymentOr10PercentSum = new MyTextField(ValueTextField.PREPAYMENT_OR_10_PERCENT_SUM,
                String.valueOf(dataClient.getBasicContract().getPrepayment()),false,controller);
        workingWindow.add(prepaymentOr10PercentSum,grid);
        grid.gridx = 2;
        workingWindow.add(new MyCheckBox(prepaymentOr10PercentSum,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        workingWindow.add(new JLabel("Оплата до 50%"),grid);
        grid.gridx = 1;
        payUpTo50PercentSum = new MyTextField(ValueTextField.PAY_UP_TO_50_PERCENT_SUM,
                String.valueOf(dataClient.getBasicContract().getPayUpTo50Percent()),false,controller);
        workingWindow.add(payUpTo50PercentSum,grid);
        grid.gridx = 2;
        workingWindow.add(new MyCheckBox(payUpTo50PercentSum,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        workingWindow.add(new JLabel("Оплата до 100%"),grid);
        grid.gridx = 1;
        payUpTo100PercentSum = new MyTextField(ValueTextField.PAY_UP_TO_100_PERCENT_SUM,
                String.valueOf(dataClient.getBasicContract().getPayUpTo100Percent()),false,controller);
        workingWindow.add(payUpTo100PercentSum,grid);
        grid.gridx = 2;
        workingWindow.add(new MyCheckBox(payUpTo100PercentSum,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        workingWindow.add(new JLabel("Сумма в белках"),grid);
        grid.gridx = 1;
        allSumInBYN = new MyTextField(ValueTextField.ALL_SUM_IN_BYN,
                String.valueOf(dataClient.getBasicContract().getAllSumInBYN()),false,controller);
        workingWindow.add(allSumInBYN,grid);
        grid.gridx = 2;
        workingWindow.add(new MyCheckBox(allSumInBYN,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        workingWindow.add(new JLabel("Дата подписания"),grid);
        grid.gridx = 1;
        dateCreateContract = new MyFormattedTextField(ValueTextField.DATE_CREATE_CONTRACT, dateCreateContractMask,
                String.valueOf(dataClient.getBasicContract().getDateCreate()),false,controller);
        workingWindow.add(dateCreateContract,grid);
        grid.gridx = 2;
        workingWindow.add(new MyCheckBox(dateCreateContract,controller),grid);

        grid.gridy = row;
        grid.gridx = 0;
        workingWindow.add(new MyButton(ValueButton.SAVE_DATA_BASE_CONTRACT_CLIENT,controller),grid);

        grid.gridy = row++;
        grid.gridx = 1;
        grid.gridwidth = 2;
        workingWindow.add(new MyButton(ValueButton.PRINT_BASE_CONTRACT,controller),grid);

        grid.gridy = row;
        grid.gridx = 0;
        grid.gridwidth = 1;
        workingWindow.add(new MyButton(ValueButton.OPEN_DIRECTORY_WITH_FILE,controller),grid);

        grid.gridy = row++;
        grid.gridx = 1;
        grid.gridwidth = 2;
        workingWindow.add(new MyButton(ValueButton.OPEN_FILE_BASIC_CONTRACT,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        grid.gridwidth = 3;
        workingWindow.add(new MyButton(ValueButton.VIEW_DATA_FOR_VIBER,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        grid.gridwidth = 3;
        workingWindow.add(new MyButton(ValueButton.VIEW_SUM_IN_BYN_TODAY,controller),grid);

        endPage();
        fillData();

    }
    @Override
    public HashMap<String, String> getData() throws DontHaveData {
        if(allSumInEUR.getText().equals("")||
                allSumInBYN.getText().equals("")||
                prepaymentOr10PercentSum.getText().equals("")||
                dateCreateContract.getText().equals("  .  .    ")||
                payUpTo50PercentSum.getText().equals("")||
                payUpTo100PercentSum.getText().equals("")||
                currency.getText().equals("")){
            throw new DontHaveData("Заполните все поля");
        }
        HashMap<String,String> result = new HashMap<>();
        result.put("dateCreateContract", dateCreateContract.getText());
        result.put("timeProduction", (String) periodOfExecution.getSelectedItem());
        result.put("allSumInEUR", allSumInEUR.getText());
        result.put("allSumInBYN", allSumInBYN.getText());
        result.put("prepaymentOr10PercentSum", prepaymentOr10PercentSum.getText());
        result.put("payUpTo50PercentSum", payUpTo50PercentSum.getText());
        result.put("payUpTo100PercentSum", payUpTo100PercentSum.getText());
        return result;
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
            allSumInBYN.setText(String.valueOf((int) Math.round((Double.parseDouble(allSumInEUR.getText())) * Double.parseDouble(currency.getText()))));
        }
    }
    public void setCurrencyZero(boolean CorrectOrZero){
        if(CorrectOrZero){
            currency.setText(String.valueOf(currencyValue));
        }
        else {
            currency.setText("0");
        }

    }
    @Override
    public void displayDataForViber(){
        buttonsForDataForViber = new MyButton[6];
        displayDataForViber(staticJPanel,controller, buttonsForDataForViber);
    }
    @Override
    public void setSelectMethodPayment(String nameButton) {
        setSelectMethodPayment(buttonsForDataForViber,nameButton);
    }

    @Override
    public String getDataFirstPayment() {
        int prepaymentBYN = (int) Math.round(Double.parseDouble(prepaymentOr10PercentSum.getText())*Double.parseDouble(currency.getText()));
        String methodPayment = "";
        for (int i = 0; i < 3; i++) {
            if(!buttonsForDataForViber[i].isEnabled()){
                methodPayment = buttonsForDataForViber[i].getText();
                break;
            }
        }
        String result = "Договор " +dataClient.getNumberContract()+" на сумму " + allSumInEUR.getText()+ "у.е., " +
                "предоплата " + prepaymentOr10PercentSum.getText()+ "у.е. " + prepaymentBYN +"руб., " +
                methodPayment;
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(result),null);

        return result;
    }
    @Override
    public String getDataSecondPayment() {
        int payTo50Percent = (int) Math.round(Double.parseDouble(payUpTo50PercentSum.getText())*Double.parseDouble(currency.getText()));
        String methodPayment = "";
        for (int i = 0; i < 3; i++) {
            if(!buttonsForDataForViber[i].isEnabled()){
                methodPayment = buttonsForDataForViber[i].getText();
                break;
            }
        }
        String result = "Доплата по договору " +dataClient.getNumberContract()+" на сумму " +
                payUpTo50PercentSum.getText()+ "у.е. " + payTo50Percent +"руб., " +
                methodPayment;
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(result),null);
        return result;
    }
    @Override
    public String getDataThirdPayment() {
        int payTo100Percent = (int) Math.round(Double.parseDouble(payUpTo100PercentSum.getText())*Double.parseDouble(currency.getText()));
        String methodPayment = "";
        for (int i = 0; i < 3; i++) {
            if(!buttonsForDataForViber[i].isEnabled()){
                methodPayment = buttonsForDataForViber[i].getText();
                break;
            }
        }
        String result = "Доплата по договору " +dataClient.getNumberContract()+" на сумму " +
                payUpTo100PercentSum.getText()+ "у.е. " + payTo100Percent +"руб., " +
                methodPayment;
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(result),null);
        return result;
    }
    @Override
    public String getInfoForPayment() {
        int prepaymentBYN = (int) Math.round(Double.parseDouble(prepaymentOr10PercentSum.getText())*Double.parseDouble(currency.getText()));
        int payTo50Percent = (int) Math.round(Double.parseDouble(payUpTo50PercentSum.getText())*Double.parseDouble(currency.getText()));
        int payTo100Percent = (int) Math.round(Double.parseDouble(payUpTo100PercentSum.getText())*Double.parseDouble(currency.getText()));

        return "Предолпата: "+prepaymentBYN+"руб.\n"+
                "Доплата до 50%: " + payTo50Percent + "руб.\n"+
                "Доплата до 100% " + payTo100Percent + "руб.";
    }

    private void fillData (){
        if(dataClient.getBasicContract().getTimeProduction().equals("21 до 29")){
            periodOfExecution.setSelectedIndex(1);
        }
        else {
            periodOfExecution.setSelectedIndex(0);
        }

        if(dataClient.getBasicContract().getAllSumInEUR()!=0){
            allSumInEUR.setText(String.valueOf(dataClient.getBasicContract().getAllSumInEUR()));
            allSumInEUR.setEnabled(false);
        }
        else {
            checkBoxAllSumInEUR.setSelected(true);
            allSumInEUR.requestFocusInWindow();
        }
    }
}
