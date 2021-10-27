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

public class ViewSupplementaryAgreementBasicContract extends MainViewContract implements Display, DataForViber, InfoForPayment {
    private double rateValue;
    private MyTextField rate;
    private MyTextField allSumInEUR;
    private MyTextField prepayment;
    private MyTextField payUpTo50Percent;
    private MyTextField payUpTo100Percent;
    private MyTextField allSumInBYN;
    private MyFormattedTextField dateCreateSupplementaryAgreement;
    private MyTextField numberSupplementaryAgreement;
    MyCheckBox checkBoxAllSumInEUR;
    MyButton[] buttonsDataForViber;

    public ViewSupplementaryAgreementBasicContract(JPanel staticJPanel, DataClient dataClient, double rate, MyController controller) {
        super(staticJPanel,dataClient,"Доп соглашение",controller);
        this.rateValue = rate;

    }

    @Override
    public void display() {
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
        grid.gridwidth = 1;
        workingWindow.add(new JLabel("Дата Баз Договора"),grid);
        grid.gridx = 1;
        JTextField dateCreateBasicContract = new JTextField(String.valueOf(dataClient.getBasicContract().getDateCreate()));
        dateCreateBasicContract.setEnabled(false);
        workingWindow.add(dateCreateBasicContract,grid);
        grid.gridx = 2;
        JCheckBox forBeauty  = new JCheckBox();
        forBeauty.setEnabled(false);
        workingWindow.add(forBeauty,grid);

        grid.gridy = row++;
        grid.gridx = 0;
        grid.ipadx = 100;
        grid.gridwidth = 1;
        workingWindow.add(new JLabel("Курс евро:"),grid);
        grid.gridx = 1;
        grid.ipadx = 300;
        rate = new MyTextField(ValueTextField.RATE_SUM_SUPPLEMENTARY_AGREEMENT,
                String.valueOf(rateValue),false,controller);
        workingWindow.add(rate,grid);
        grid.gridx = 2;
        grid.ipadx = 20;
        workingWindow.add(new MyCheckBox(rate,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        grid.ipadx = 0;
        workingWindow.add(new JLabel("Сумма договора"),grid);
        grid.gridx = 1;
        allSumInEUR = new MyTextField(ValueTextField.ALL_SUM_IN_EUR_SUPPLEMENTARY_AGREEMENT,
                String.valueOf(dataClient.getSupplementaryAgreementBasicContract().getAllSumInEUR()), false,controller);
        workingWindow.add(allSumInEUR,grid);
        grid.gridx = 2;
        checkBoxAllSumInEUR = new MyCheckBox(allSumInEUR,controller);
        workingWindow.add(checkBoxAllSumInEUR,grid);

        grid.gridy = row++;
        grid.gridx = 0;
        workingWindow.add(new JLabel("Предоплата:"),grid);
        grid.gridx = 1;
        prepayment = new MyTextField(ValueTextField.PREPAYMENT_OR_10_PERCENT_SUM_SUPPLEMENTARY_AGREEMENT,
                String.valueOf(dataClient.getSupplementaryAgreementBasicContract().getPrepayment()), false,controller);
        workingWindow.add(prepayment,grid);
        grid.gridx = 2;
        workingWindow.add(new MyCheckBox(prepayment,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        workingWindow.add(new JLabel("Оплата до 50%"),grid);
        grid.gridx = 1;
        payUpTo50Percent = new MyTextField(ValueTextField.PAY_UP_TO_50_PERCENT_SUM_SUPPLEMENTARY_AGREEMENT,
                String.valueOf(dataClient.getSupplementaryAgreementBasicContract().getPayUpTo50Percent()), false,controller);
        workingWindow.add(payUpTo50Percent,grid);
        grid.gridx = 2;
        workingWindow.add(new MyCheckBox(payUpTo50Percent,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        workingWindow.add(new JLabel("Оплата до 100%"),grid);
        grid.gridx = 1;
        payUpTo100Percent = new MyTextField(ValueTextField.PAY_UP_TO_50_PERCENT_SUM_SUPPLEMENTARY_AGREEMENT,
                String.valueOf(dataClient.getSupplementaryAgreementBasicContract().getPayUpTo100Percent()),
                false,controller);
        workingWindow.add(payUpTo100Percent,grid);
        grid.gridx = 2;
        workingWindow.add(new MyCheckBox(payUpTo100Percent,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        workingWindow.add(new JLabel("Сумма в белках"),grid);
        grid.gridx = 1;
        allSumInBYN = new MyTextField(ValueTextField.ALL_SUM_IN_BYN_SUPPLEMENTARY_AGREEMENT,
                String.valueOf(dataClient.getSupplementaryAgreementBasicContract().getAllSumInBYN()), false,controller);
        workingWindow.add(allSumInBYN,grid);
        grid.gridx = 2;
        workingWindow.add(new MyCheckBox(allSumInBYN,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        workingWindow.add(new JLabel("Дата доп. соглашения"),grid);
        grid.gridx = 1;
        dateCreateSupplementaryAgreement = new MyFormattedTextField(ValueTextField.DATE_CREATE_SUPPLEMENTARY_AGREEMENT_BASIC_CONTRACT,
                dateMask,String.valueOf(dataClient.getSupplementaryAgreementBasicContract().getDateCreate()), false,controller);
        workingWindow.add(dateCreateSupplementaryAgreement,grid);
        grid.gridx = 2;
        workingWindow.add(new MyCheckBox(dateCreateSupplementaryAgreement,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        workingWindow.add(new JLabel("Номер доп. соглашения"),grid);
        grid.gridx = 1;
        numberSupplementaryAgreement = new MyTextField(ValueTextField.NUMBER_SUPPLEMENTARY_AGREEMENT_BASIC_CONTRACT,
                String.valueOf(dataClient.getSupplementaryAgreementBasicContract().getNumber()),false,controller);
        workingWindow.add(numberSupplementaryAgreement,grid);
        grid.gridx = 2;
        workingWindow.add(new MyCheckBox(numberSupplementaryAgreement,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        workingWindow.add(new MyButton(ValueButton.SAVE_DATA_SUPPLEMENTARY_AGREEMENT_BASIC_CONTRACT,controller),grid);

        grid.gridx = 1;
        grid.gridwidth = 2;
        workingWindow.add(new MyButton(ValueButton.PRINT_SUPPLEMENTARY_AGREEMENT_BASIC_CONTRACT,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        grid.gridwidth = 1;
        workingWindow.add(new MyButton(ValueButton.OPEN_DIRECTORY_WITH_FILE,controller),grid);

        grid.gridx = 1;
        grid.gridwidth = 2;
        workingWindow.add(new MyButton(ValueButton.OPEN_FILE_SUPPLEMENTARY_AGREEMENT_BASIC_CONTRACT,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        grid.gridwidth = 3;
        workingWindow.add(new MyButton(ValueButton.VIEW_DATA_FOR_VIBER,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        grid.gridwidth = 3;
        workingWindow.add(new MyButton(ValueButton.VIEW_SUM_IN_BYN_TODAY,controller),grid);

        endPage();
        if(dataClient.getSupplementaryAgreementBasicContract().getAllSumInEUR()==0){
            fillData();
            allSumInEUR.requestFocusInWindow();
        }
    }

    @Override
    public HashMap<String, String> getData() throws DontHaveData {
        if(numberSupplementaryAgreement.getText().equals("")||
                allSumInEUR.getText().equals("")||
                allSumInBYN.getText().equals("")||
                dateCreateSupplementaryAgreement.getText().equals("  .  .    ")||
                prepayment.getText().equals("")||
                payUpTo50Percent.getText().equals("")||
                payUpTo100Percent.getText().equals("")||
                rate.equals("")){
            throw new DontHaveData("Заполните все поля");
        }
        HashMap<String,String> result = new HashMap<>();
        result.put("dateCreateSupplementaryAgreementBasicContract", dateCreateSupplementaryAgreement.getText());
        result.put("numberSupplementaryAgreementBasicContract", numberSupplementaryAgreement.getText());
        result.put("allSumInEURSupplementaryAgreement", allSumInEUR.getText());
        result.put("allSumInBYNSupplementaryAgreement", allSumInBYN.getText());
        result.put("prepaymentOr10PercentSumSupplementaryAgreement", prepayment.getText());
        result.put("payUpTo50PercentSumSupplementaryAgreement", payUpTo50Percent.getText());
        result.put("payUpTo100PercentSumSupplementaryAgreement", payUpTo100Percent.getText());
        return result;
    }

    public void editAllEURSum(){
        if(!allSumInEUR.getText().equals("")) {
            int allSumEUR = Integer.parseInt(allSumInEUR.getText());
            double currency = Double.parseDouble(this.rate.getText());
            int sumInBYN = (int) Math.round((double) currency * allSumEUR);
            allSumInBYN.setText(String.valueOf(sumInBYN));
            editPrepayment();
        }
    }
    public void editPrepayment(){
        if(!prepayment.getText().equals("")) {
            int prepayment = Integer.parseInt(this.prepayment.getText()),
                    allSumEUR = Integer.parseInt(allSumInEUR.getText()),
                    percent50 = allSumEUR / 2 - prepayment;
            if (percent50 < 0) {
                percent50 = 0;
            }
            payUpTo50Percent.setText(String.valueOf(percent50));
            editPayUpTo50percent();
        }

    }
    public void editPayUpTo50percent(){
        if(!payUpTo50Percent.getText().equals("")) {
            int prepayment = Integer.parseInt(this.prepayment.getText()),
                    allSumEUR = Integer.parseInt(allSumInEUR.getText()),
                    percent50 = Integer.parseInt(payUpTo50Percent.getText()),
                    percent100 = allSumEUR - prepayment - percent50;
            payUpTo100Percent.setText(String.valueOf(percent100));
        }
    }
    public void editCurrency(){
        if(!rate.getText().equals("")) {
            double allSumEUR = Double.parseDouble(allSumInEUR.getText());
            allSumInBYN.setText(String.valueOf(
                    Math.round((allSumEUR * Double.parseDouble(rate.getText())))));
            staticJPanel.revalidate();
            staticJPanel.repaint();
        }

    }
    public void setCurrencyZero(boolean CorrectOrZero){
        if(CorrectOrZero){
            rate.setText(String.valueOf(rateValue));
        }
        else {
            rate.setText("0");
        }

    }

    @Override
    public void displayDataForViber() {
        buttonsDataForViber = new MyButton[6];
        displayDataForViber(staticJPanel,controller, buttonsDataForViber);
    }
    @Override
    public void setSelectMethodPayment(String nameButton) {
        setSelectMethodPayment(buttonsDataForViber,nameButton);
    }

    @Override
    public String getDataFirstPayment() {
        String result = "Если вы воспользовались данной кнопкой \n" +
                "напишете мне и объясните что она должна выдавать ";
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(result),null);
        return result;
    }

    @Override
    public String getDataSecondPayment() {
        int payTo50Percent = (int) Math.round(Double.parseDouble(payUpTo50Percent.getText())*
                Double.parseDouble(rate.getText()));
        String methodPayment = "";
        for (int i = 0; i < 3; i++) {
            if(!buttonsDataForViber[i].isEnabled()){
                methodPayment = buttonsDataForViber[i].getText();
                break;
            }
        }
        String result = "Доп соглашение " +dataClient.getNumberContract()+" стало "+ allSumInEUR.getText()+
                "у.е., "+" доплата " +
                payUpTo50Percent.getText()+ "у.е. " + payTo50Percent +"руб., " +
                methodPayment;
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(result),null);
        return result;
    }

    @Override
    public String getDataThirdPayment() {
        int payTo100Percent = (int) Math.round(Double.parseDouble(payUpTo100Percent.getText()) *
                Double.parseDouble(rate.getText()));
        String methodPayment = "";
        for (int i = 0; i < 3; i++) {
            if(!buttonsDataForViber[i].isEnabled()){
                methodPayment = buttonsDataForViber[i].getText();
                break;
            }
        }
        String result = "Доплата по договору " +dataClient.getNumberContract()+" на сумму " +
                payUpTo100Percent.getText()+ "у.е. " + payTo100Percent +"руб., " +
                methodPayment;
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(result),null);
        return result;
    }

    @Override
    public String getInfoForPayment() {
        int prepaymentBYN = (int) Math.round(Double.parseDouble(prepayment.getText())*
                Double.parseDouble(rate.getText()));
        int payTo50Percent = (int) Math.round(Double.parseDouble(payUpTo50Percent.getText())*
                Double.parseDouble(rate.getText()));
        int payTo100Percent = (int) Math.round(Double.parseDouble(payUpTo100Percent.getText())*
                Double.parseDouble(rate.getText()));

        return "Предолпата: "+prepaymentBYN+"руб.\n"+
                "Доплата до 50%: " + payTo50Percent + "руб.\n"+
                "Доплата до 100% " + payTo100Percent + "руб.";
    }

    private void fillData(){
        checkBoxAllSumInEUR.setSelected(true);
        allSumInEUR.setEnabled(true);
        prepayment.setText(String.valueOf(dataClient.getBasicContract().getPrepayment()));
        numberSupplementaryAgreement.setText("1");
    }
}
