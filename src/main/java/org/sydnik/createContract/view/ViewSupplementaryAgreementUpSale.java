package org.sydnik.createContract.view;

import org.sydnik.createContract.MyController;
import org.sydnik.createContract.data.AdditionalProduct;
import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.data.contract.SupplementaryAgreementUpSaleContract;
import org.sydnik.createContract.exception.DontHaveData;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class ViewSupplementaryAgreementUpSale extends MainViewContract implements Display,DataForViber{
    public static final int COUNT_ADDITIONAL_PRODUCT = 9;

    private JTextField supplementaryAgreementAdditionalProducts[];
    private JTextField supplementaryAgreementAdditionalProductsCount[];
    private JTextField supplementaryAgreementAdditionalProductsDiscount[];
    private JTextField supplementaryAgreementAdditionalProductsFullPrice[];
    private JTextField supplementaryAgreementAdditionalProductsWithDiscount[];
    private JTextField sumUpSaleInBYNSupplementaryAgreement;
    private JTextField numberSupplementaryAgreementUpSale;
    private JTextField prepaymentUpSaleSupplementaryAgreement;
    private JTextField payUpTo100percentUpSaleSupplementaryAgreement;
    private JFormattedTextField dateCreateSupplementaryAgreementUpSaleContract;
    private JButton[] buttonsForDataForViber;

    public ViewSupplementaryAgreementUpSale(JPanel staticJPanel, DataClient dataClient, MyController controller) {
        super(staticJPanel,dataClient,"Доп Upsale",controller);
    }
    @Override
    public void display() {
        int line = -1;
        startPage();
        MaskFormatter
                dateCreateContractMask = null;
        try {
            dateCreateContractMask = new MaskFormatter("##.##.####");
        }
        catch (Exception e){}

        workingWindow.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;

        gridBagConstraints.gridy = ++line;
        gridBagConstraints.gridx = 0;
        workingWindow.add(new JLabel("№"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 3;
        workingWindow.add(new JLabel(" Наименование товара"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        workingWindow.add(new JLabel("К"),gridBagConstraints);
        gridBagConstraints.gridx = 5;
        workingWindow.add(new JLabel("Цена"),gridBagConstraints);
        gridBagConstraints.gridx = 6;
        workingWindow.add(new JLabel("%"),gridBagConstraints);
        gridBagConstraints.gridx = 7;
        workingWindow.add(new JLabel("Всего"),gridBagConstraints);
        supplementaryAgreementAdditionalProducts = new JTextField[COUNT_ADDITIONAL_PRODUCT];
        supplementaryAgreementAdditionalProductsCount = new JTextField[COUNT_ADDITIONAL_PRODUCT];
        supplementaryAgreementAdditionalProductsDiscount = new JTextField[COUNT_ADDITIONAL_PRODUCT];
        supplementaryAgreementAdditionalProductsFullPrice = new JTextField[COUNT_ADDITIONAL_PRODUCT];
        supplementaryAgreementAdditionalProductsWithDiscount = new JTextField[COUNT_ADDITIONAL_PRODUCT];
        int i =0;
        line++;
        for (; i < COUNT_ADDITIONAL_PRODUCT; i++) {
            supplementaryAgreementAdditionalProducts[i] = new JTextField();
            supplementaryAgreementAdditionalProductsCount[i] = new JTextField();
            supplementaryAgreementAdditionalProductsDiscount[i] = new JTextField();
            supplementaryAgreementAdditionalProductsFullPrice[i] = new JTextField();
            supplementaryAgreementAdditionalProductsWithDiscount[i] = new JTextField();

            gridBagConstraints.gridy = line+i;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.ipadx = 5;
            gridBagConstraints.gridwidth = 1;
            workingWindow.add(new JLabel(String.valueOf(i+1)),gridBagConstraints);
            gridBagConstraints.gridx = 1;
            gridBagConstraints.ipadx = 310;
            gridBagConstraints.gridwidth = 3;
            supplementaryAgreementAdditionalProducts[i].setName("supplementaryAgreementAdditionalProducts"+i);
            workingWindow.add(supplementaryAgreementAdditionalProducts[i],gridBagConstraints);
            gridBagConstraints.gridx = 4;
            gridBagConstraints.ipadx = 10;
            gridBagConstraints.gridwidth = 1;
            supplementaryAgreementAdditionalProductsCount[i].setText(String.valueOf(1));
            supplementaryAgreementAdditionalProductsCount[i].setName("supplementaryAgreementAdditionalProductsCount"+i);
            supplementaryAgreementAdditionalProductsCount[i].addKeyListener(controller);
            workingWindow.add(supplementaryAgreementAdditionalProductsCount[i],gridBagConstraints);
            gridBagConstraints.gridx = 5;
            gridBagConstraints.ipadx = 50;
            supplementaryAgreementAdditionalProductsDiscount[i].setText(String.valueOf(0));
            supplementaryAgreementAdditionalProductsFullPrice[i].setName("supplementaryAgreementAdditionalProductsFullPrice"+i);
            supplementaryAgreementAdditionalProductsFullPrice[i].addKeyListener(controller);
            workingWindow.add(supplementaryAgreementAdditionalProductsFullPrice[i],gridBagConstraints);
            gridBagConstraints.gridx = 6;
            gridBagConstraints.ipadx = 15;
            supplementaryAgreementAdditionalProductsDiscount[i].setName("supplementaryAgreementAdditionalProductsDiscount"+i);
            supplementaryAgreementAdditionalProductsDiscount[i].addKeyListener(controller);
            workingWindow.add(supplementaryAgreementAdditionalProductsDiscount[i],gridBagConstraints);
            gridBagConstraints.gridx = 7;
            gridBagConstraints.ipadx = 50;
            supplementaryAgreementAdditionalProductsWithDiscount[i].setName("supplementaryAgreementAdditionalProductsWithDiscount"+i);
            supplementaryAgreementAdditionalProductsWithDiscount[i].addKeyListener(controller);
            workingWindow.add(supplementaryAgreementAdditionalProductsWithDiscount[i],gridBagConstraints);
            try {
                supplementaryAgreementAdditionalProducts[i].setText(dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProductsSupplementaryAgreementUpSale()[i].getName());
                supplementaryAgreementAdditionalProductsCount[i].setText(dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProductsSupplementaryAgreementUpSale()[i].getCount());
                supplementaryAgreementAdditionalProductsDiscount[i].setText(dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProductsSupplementaryAgreementUpSale()[i].getDiscount());
                supplementaryAgreementAdditionalProductsFullPrice[i].setText(dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProductsSupplementaryAgreementUpSale()[i].getFullPrice());
                supplementaryAgreementAdditionalProductsWithDiscount[i].setText(dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProductsSupplementaryAgreementUpSale()[i].getPriceWithDiscount());

            } catch (Exception e){

            }
        }
        line = line +i;
        gridBagConstraints.gridy = ++line;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 0;
        workingWindow.add(new JLabel("Сумма договора"),gridBagConstraints);

        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxSumUpSaleInBYNSupplementaryAgreement = new JCheckBox();
        checkBoxSumUpSaleInBYNSupplementaryAgreement.setName("checkBoxSumUpSaleInBYNSupplementaryAgreement");
        checkBoxSumUpSaleInBYNSupplementaryAgreement.addActionListener(controller);
        workingWindow.add(checkBoxSumUpSaleInBYNSupplementaryAgreement,gridBagConstraints);


        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        sumUpSaleInBYNSupplementaryAgreement = new JTextField();
        sumUpSaleInBYNSupplementaryAgreement.setName("sumUpSaleInBYNSupplementaryAgreement");
        if(dataClient.getUpSaleContract().getAllSumUpSaleInBYN()!=0){
            sumUpSaleInBYNSupplementaryAgreement.setText(String.valueOf(dataClient.getSupplementaryAgreementUpSaleContract().getAllSumUpSaleInBYNSupplementaryAgreement()));
        }
        sumUpSaleInBYNSupplementaryAgreement.addKeyListener(controller);
        sumUpSaleInBYNSupplementaryAgreement.setEnabled(false);
        workingWindow.add(sumUpSaleInBYNSupplementaryAgreement,gridBagConstraints);




        gridBagConstraints.gridy = ++line;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        workingWindow.add(new JLabel("Предоплата:"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth =1;
        JCheckBox checkBoxPrepaymentUpSaleSupplementaryAgreement  = new JCheckBox();
        checkBoxPrepaymentUpSaleSupplementaryAgreement.setName("checkBoxPrepaymentUpSaleSupplementaryAgreement");
        checkBoxPrepaymentUpSaleSupplementaryAgreement.addActionListener(controller);
        workingWindow.add(checkBoxPrepaymentUpSaleSupplementaryAgreement,gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        prepaymentUpSaleSupplementaryAgreement = new JTextField();
        prepaymentUpSaleSupplementaryAgreement.setName("prepaymentUpSaleSupplementaryAgreement");
        prepaymentUpSaleSupplementaryAgreement.setText(String.valueOf(dataClient.getSupplementaryAgreementUpSaleContract().getPrepaymentUpSaleSupplementaryAgreement()));
        prepaymentUpSaleSupplementaryAgreement.addKeyListener(controller);
        prepaymentUpSaleSupplementaryAgreement.setEnabled(false);
        workingWindow.add(prepaymentUpSaleSupplementaryAgreement,gridBagConstraints);

        gridBagConstraints.gridy = ++line;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        workingWindow.add(new JLabel("Оплата до 100%"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxPayUpTo100percentUpSaleSupplementaryAgreement  = new JCheckBox();
        checkBoxPayUpTo100percentUpSaleSupplementaryAgreement.setName("checkBoxPayUpTo100percentUpSaleSupplementaryAgreement");
        checkBoxPayUpTo100percentUpSaleSupplementaryAgreement.addActionListener(controller);
        workingWindow.add(checkBoxPayUpTo100percentUpSaleSupplementaryAgreement,gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        payUpTo100percentUpSaleSupplementaryAgreement = new JTextField();
        payUpTo100percentUpSaleSupplementaryAgreement.setName("payUpTo100percentUpSaleSupplementaryAgreement");
        payUpTo100percentUpSaleSupplementaryAgreement.setText(String.valueOf(dataClient.getSupplementaryAgreementUpSaleContract().getPayUpTo100percentUpSaleSupplementaryAgreement()));
        payUpTo100percentUpSaleSupplementaryAgreement.addKeyListener(controller);
        payUpTo100percentUpSaleSupplementaryAgreement.setEnabled(false);
        workingWindow.add(payUpTo100percentUpSaleSupplementaryAgreement,gridBagConstraints);

        gridBagConstraints.gridy = ++line;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        workingWindow.add(new JLabel("Дата UpSale"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxJust  = new JCheckBox();
        checkBoxJust.setName("checkBoxJust");
        checkBoxJust.addActionListener(controller);
        checkBoxJust.setEnabled(false);
        workingWindow.add(checkBoxJust,gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        JFormattedTextField dateCreateUpSaleContract = new JFormattedTextField(dateCreateContractMask);
        dateCreateUpSaleContract.setName("dateCreateUpSaleContract");
        dateCreateUpSaleContract.setText(String.valueOf(dataClient.getUpSaleContract().getDateCreateUpSaleContract()));
        dateCreateUpSaleContract.addKeyListener(controller);
        dateCreateUpSaleContract.setEnabled(false);
        workingWindow.add(dateCreateUpSaleContract,gridBagConstraints);

        gridBagConstraints.gridy = ++line;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        workingWindow.add(new JLabel("Дата подписания"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxDateCreateSupplementaryAgreementUpSaleContract  = new JCheckBox();
        checkBoxDateCreateSupplementaryAgreementUpSaleContract.setName("checkBoxDateCreateSupplementaryAgreementUpSaleContract");
        checkBoxDateCreateSupplementaryAgreementUpSaleContract.addActionListener(controller);
        workingWindow.add(checkBoxDateCreateSupplementaryAgreementUpSaleContract,gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        dateCreateSupplementaryAgreementUpSaleContract = new JFormattedTextField(dateCreateContractMask);
        dateCreateSupplementaryAgreementUpSaleContract.setName("dateCreateSupplementaryAgreementUpSaleContract");
        dateCreateSupplementaryAgreementUpSaleContract.setText(String.valueOf(dataClient.getSupplementaryAgreementUpSaleContract().getDateCreateSupplementaryAgreementUpSaleContract()));
        dateCreateSupplementaryAgreementUpSaleContract.addKeyListener(controller);
        dateCreateSupplementaryAgreementUpSaleContract.setEnabled(false);
        workingWindow.add(dateCreateSupplementaryAgreementUpSaleContract,gridBagConstraints);

        gridBagConstraints.gridy = ++line;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        workingWindow.add(new JLabel("Номер"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxNumberSupplementaryAgreementUpSale  = new JCheckBox();
        checkBoxNumberSupplementaryAgreementUpSale.setName("checkBoxNumberSupplementaryAgreementUpSale");
        checkBoxNumberSupplementaryAgreementUpSale.addActionListener(controller);
        workingWindow.add(checkBoxNumberSupplementaryAgreementUpSale,gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        numberSupplementaryAgreementUpSale = new JTextField();
        numberSupplementaryAgreementUpSale.setName("numberSupplementaryAgreementUpSale");
        numberSupplementaryAgreementUpSale.setText(String.valueOf(dataClient.getSupplementaryAgreementUpSaleContract().getNumberSupplementaryAgreementUpSale()));
        numberSupplementaryAgreementUpSale.addKeyListener(controller);
        numberSupplementaryAgreementUpSale.setEnabled(false);
        workingWindow.add(numberSupplementaryAgreementUpSale,gridBagConstraints);


        gridBagConstraints.gridy = ++line;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 0;
        JButton saveDataSupplementaryAgreementUpSaleContact = new JButton("Сохранить");
        saveDataSupplementaryAgreementUpSaleContact.setName("saveDataSupplementaryAgreementUpSaleContact");
        saveDataSupplementaryAgreementUpSaleContact.addActionListener(controller);
        workingWindow.add(saveDataSupplementaryAgreementUpSaleContact,gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 4;
        JButton printSupplementaryAgreementUpSaleContract = new JButton("Распечатать 2 раза");
        printSupplementaryAgreementUpSaleContract.setName("printSupplementaryAgreementUpSaleContract");
        printSupplementaryAgreementUpSaleContract.addActionListener(controller);
        workingWindow.add(printSupplementaryAgreementUpSaleContract,gridBagConstraints);

        gridBagConstraints.gridy = ++line;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        JButton openDirectoryWithFile = new JButton("Открыть папку с файлом");
        openDirectoryWithFile.setName("openDirectoryWithFile");
        openDirectoryWithFile.addActionListener(controller);
        workingWindow.add(openDirectoryWithFile,gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 4;
        JButton openFileUpSaleContract = new JButton("Открыть файл");
        openFileUpSaleContract.setName("openFileSupplementaryAgreementUpSaleContract");
        openFileUpSaleContract.addActionListener(controller);
        workingWindow.add(openFileUpSaleContract,gridBagConstraints);

        gridBagConstraints.gridy = ++line;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 8;
        JButton dataForViberBasicContract = new JButton("Данные для вайбер");
        dataForViberBasicContract.setName("dataForViber");
        dataForViberBasicContract.addActionListener(controller);
        workingWindow.add(dataForViberBasicContract,gridBagConstraints);

//        gridBagConstraints.gridy = ++line;
//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridwidth = 4;
//        JButton orderSupplementaryAgreementUpSaleMaunfeld = new JButton("Скопировать Maunfeld");
//        orderSupplementaryAgreementUpSaleMaunfeld.setName("orderSupplementaryAgreementUpSaleMaunfeld");
//        orderSupplementaryAgreementUpSaleMaunfeld.addActionListener(controller);
//        workingWindow.add(orderSupplementaryAgreementUpSaleMaunfeld, gridBagConstraints);
//        gridBagConstraints.gridx = 4;
//        gridBagConstraints.gridwidth = 4;
//        JButton orderSupplementaryAgreementUpSaleSink = new JButton("Скопировать Emar");
//        orderSupplementaryAgreementUpSaleSink.setName("orderSupplementaryAgreementUpSaleSink");
//        orderSupplementaryAgreementUpSaleSink.addActionListener(controller);
//        workingWindow.add(orderSupplementaryAgreementUpSaleSink, gridBagConstraints);

        if(sumUpSaleInBYNSupplementaryAgreement.getText().equals("")||sumUpSaleInBYNSupplementaryAgreement.getText().equals("0")){
            fillData();
        }
        endPage();
    }

    public void editSupplementaryAgreementUpSaleSumProductPriceAndDiscountAndCount(JTextField jTextField){
        int i = Integer.parseInt(jTextField.getName().substring(jTextField.getName().length()-1));
        int jCount = Integer.parseInt(supplementaryAgreementAdditionalProductsCount[i].getText());
        int jDiscount = Integer.parseInt(supplementaryAgreementAdditionalProductsDiscount[i].getText());
        int jPrice = 0;
        try {
            jPrice = Integer.parseInt(supplementaryAgreementAdditionalProductsFullPrice[i].getText());
        }catch (Exception e){

        }
        supplementaryAgreementAdditionalProductsWithDiscount[i].setText(String.valueOf(((int) Math.round((double) jPrice*((double) (100-jDiscount)/100)))*jCount));
        staticJPanel.revalidate();
        staticJPanel.repaint();
        editWithDiscount();


    }
    public void editWithDiscount() {
        int allSum = 0;
        for (int i = 0; i < supplementaryAgreementAdditionalProductsWithDiscount.length; i++) {
            try {
                allSum = allSum + Integer.parseInt(supplementaryAgreementAdditionalProductsWithDiscount[i].getText());

            } catch (NumberFormatException e) {

            }
        }
        sumUpSaleInBYNSupplementaryAgreement.setText(String.valueOf(allSum));
        editPrepaymentOrAllSumSupplementaryAgreementUpSale();
    }
    public void editPrepaymentOrAllSumSupplementaryAgreementUpSale (){
        int allSum = Integer.parseInt(sumUpSaleInBYNSupplementaryAgreement.getText());
        int prepayment = Integer.parseInt(prepaymentUpSaleSupplementaryAgreement.getText());
        payUpTo100percentUpSaleSupplementaryAgreement.setText(String.valueOf(allSum-prepayment));
    }
    @Override
    public HashMap<String, String> getDataForSave() throws DontHaveData {
        if(sumUpSaleInBYNSupplementaryAgreement.equals("")||sumUpSaleInBYNSupplementaryAgreement.equals("0")||
                prepaymentUpSaleSupplementaryAgreement.equals("")||payUpTo100percentUpSaleSupplementaryAgreement.equals("")||
                dateCreateSupplementaryAgreementUpSaleContract.equals("  .  .    ")||numberSupplementaryAgreementUpSale.equals("")){
            throw  new DontHaveData("Заполните все поля техники должно быть минимум 1");
        }
        HashMap<String,String> result = new HashMap<>();
        for (int i = 0; i < supplementaryAgreementAdditionalProducts.length; i++) {
            result.put("supplementaryAgreementAdditionalProducts"+i,supplementaryAgreementAdditionalProducts[i].getText());
            result.put("supplementaryAgreementAdditionalProductsCount"+i,supplementaryAgreementAdditionalProductsCount[i].getText());
            result.put("supplementaryAgreementAdditionalProductsFullPrice"+i,supplementaryAgreementAdditionalProductsFullPrice[i].getText());
            result.put("supplementaryAgreementAdditionalProductsDiscount" + i,supplementaryAgreementAdditionalProductsDiscount[i].getText());
            result.put("supplementaryAgreementAdditionalProductsWithDiscount"+i,supplementaryAgreementAdditionalProductsWithDiscount[i].getText());
        }
        result.put("dateCreateSupplementaryAgreementUpSaleContract",dateCreateSupplementaryAgreementUpSaleContract.getText());
        result.put("numberSupplementaryAgreementUpSale",numberSupplementaryAgreementUpSale.getText());
        result.put("sumUpSaleInBYNSupplementaryAgreement",sumUpSaleInBYNSupplementaryAgreement.getText());
        result.put("prepaymentUpSaleSupplementaryAgreement",prepaymentUpSaleSupplementaryAgreement.getText());
        result.put("payUpTo100percentUpSaleSupplementaryAgreement",payUpTo100percentUpSaleSupplementaryAgreement.getText());
        return result;
    }
    private void fillData(){//Если данных нет берем все из UpSale
        for (int i = 0; i < supplementaryAgreementAdditionalProducts.length; i++) {
            try {
                supplementaryAgreementAdditionalProducts[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getName());
                supplementaryAgreementAdditionalProductsCount[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getCount());
                supplementaryAgreementAdditionalProductsDiscount[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getDiscount());
                supplementaryAgreementAdditionalProductsFullPrice[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getFullPrice());
                supplementaryAgreementAdditionalProductsWithDiscount[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getPriceWithDiscount());
            } catch (Exception e) {

            }
        }
        sumUpSaleInBYNSupplementaryAgreement.setText(String.valueOf(dataClient.getUpSaleContract().getAllSumUpSaleInBYN()));
        prepaymentUpSaleSupplementaryAgreement.setText(String.valueOf(dataClient.getUpSaleContract().getPrepaymentUpSale()));
        payUpTo100percentUpSaleSupplementaryAgreement.setText(String.valueOf(dataClient.getUpSaleContract().getPayUpTo100percentUpSale()));
        dateCreateSupplementaryAgreementUpSaleContract.setText(String.valueOf(dataClient.getSupplementaryAgreementUpSaleContract().getDateCreateSupplementaryAgreementUpSaleContract()));

    }

    public void displayDataForViber(JPanel staticJPanel, MyController controller, JButton[] jButtons){
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(staticJPanel);
        JDialog dialog = new JDialog(frame, "Данные для вайбер", true);
        dialog.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
        dialog.setSize(420, 200);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);

        JPanel jPanel = new JPanel();
        dialog.add(jPanel);
        jPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 48;
        jButtons[0] = new JButton(PAYMENT_METHOD_VALUE_BUTTON[0]);
        jButtons[0].setName(PAYMENT_METHOD_NAME_BUTTON);
        jButtons[0].addActionListener(controller);
        jPanel.add(jButtons[0],gridBagConstraints);
        gridBagConstraints.gridx = 1;
        jButtons[1] = new JButton(PAYMENT_METHOD_VALUE_BUTTON[1]);
        jButtons[1].setName(PAYMENT_METHOD_NAME_BUTTON);
        jButtons[1].addActionListener(controller);
        jPanel.add(jButtons[1],gridBagConstraints);
        gridBagConstraints.gridx = 2;
        jButtons[2] = new JButton(PAYMENT_METHOD_VALUE_BUTTON[2]);
        jButtons[2].setName(PAYMENT_METHOD_NAME_BUTTON);
        jButtons[2].addActionListener(controller);
        jPanel.add(jButtons[2],gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 155;
        jButtons[3] = new JButton(BUTTON_VALUE_FIRST_PAYMENT);
        jButtons[3].setName(BUTTON_NAME_FIRST_PAYMENT);
        jButtons[3].addActionListener(controller);
        jButtons[3].setEnabled(false);
        jButtons[3].setVisible(false);
        jPanel.add(jButtons[3],gridBagConstraints);

        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        jButtons[4] = new JButton(BUTTON_VALUE_SECOND_PAYMENT);
        jButtons[4].setName(BUTTON_NAME_SECOND_PAYMENT);
        jButtons[4].addActionListener(controller);
        jButtons[4].setEnabled(false);
        jButtons[4].setVisible(false);
        jPanel.add(jButtons[4],gridBagConstraints);

        gridBagConstraints.gridy = 3;
        jButtons[5] = new JButton(BUTTON_VALUE_THIRD_PAYMENT);
        jButtons[5].setName(BUTTON_NAME_THIRD_PAYMENT);
        jButtons[5].addActionListener(controller);
        jButtons[5].setEnabled(false);
        jPanel.add(jButtons[5],gridBagConstraints);

        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 0;
        jButtons[6] = new JButton("Скопировать Maunfeld");
        jButtons[6].setName("orderSupplementaryAgreementUpSaleMaunfeld");
        jButtons[6].addActionListener(controller);
        jPanel.add(jButtons[6], gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridwidth = 1;
        jButtons[7] = new JButton("Скопировать Emar");
        jButtons[7].setName("orderSupplementaryAgreementUpSaleSink");
        jButtons[7].addActionListener(controller);
        jPanel.add(jButtons[7], gridBagConstraints);

        dialog.setVisible(true);
    }

    @Override
    public void displayDataForViber() {
        buttonsForDataForViber = new JButton[8];
        displayDataForViber(staticJPanel,controller, buttonsForDataForViber);
    }

    @Override
    public void setSelectMethodPayment(String nameButton) {
        setSelectMethodPayment(buttonsForDataForViber,nameButton);
    }

    @Override
    public String getDataFirstPayment() {
        return "Ничего не делает";
    }

    @Override
    public String getDataSecondPayment() {
        return "Ничего не делает";
    }

    @Override
    public String getDataThirdPayment() {
        String methodPayment = "";
        for (int i = 0; i < 3; i++) {
            if(!buttonsForDataForViber[i].isEnabled()){
                methodPayment = buttonsForDataForViber[i].getText();
                break;
            }
        }
        String result = "Доплата по договору " +dataClient.getNumberContract()+"/1 на сумму " +
                payUpTo100percentUpSaleSupplementaryAgreement.getText() +"руб., " + methodPayment;
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(result),null);
        return result;
    }

}
