package org.sydnik.createContract.view;

import org.sydnik.createContract.MyController;
import org.sydnik.createContract.data.AdditionalProduct;
import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.data.contract.SupplementaryAgreementUpSaleContract;
import org.sydnik.createContract.exception.DontHaveData;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class ViewSupplementaryAgreementUpSale implements Display{
    private JPanel staticJPanel;
    private DataClient dataClient;
    private MyController controller;
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

    public ViewSupplementaryAgreementUpSale(JPanel staticJPanel, DataClient dataClient, MyController controller) {
        this.staticJPanel = staticJPanel;
        this.dataClient = dataClient;
        this.controller = controller;
    }

    @Override
    public void display() {
        MaskFormatter
                dateCreateContractMask = null;
        try {
            dateCreateContractMask = new MaskFormatter("##.##.####");
        }
        catch (Exception e){}

        staticJPanel.removeAll();
        staticJPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        JLabel j1 = new JLabel("Клиент: ");
        j1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j1,gridBagConstraints);

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridwidth = 5;
        JLabel j2 = new JLabel(dataClient.getFullNameClient());
        j2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j2,gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        JLabel j4 = new JLabel("Доп UpSale");
        j4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j4,gridBagConstraints);

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridwidth = 5;
        JLabel j3 = new JLabel(dataClient.getNumberContract()+" " + dataClient.getStrangeName());
        j3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j3,gridBagConstraints);

        gridBagConstraints.gridy = 2;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);
        gridBagConstraints.gridy = 3;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 0;
        staticJPanel.add(new JLabel("№"),gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 3;
        staticJPanel.add(new JLabel(" Наименование товара"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        staticJPanel.add(new JLabel("К"),gridBagConstraints);
        gridBagConstraints.gridx = 5;
        staticJPanel.add(new JLabel("Цена"),gridBagConstraints);
        gridBagConstraints.gridx = 6;
        staticJPanel.add(new JLabel("%"),gridBagConstraints);
        gridBagConstraints.gridx = 7;
        staticJPanel.add(new JLabel("Всего"),gridBagConstraints);
        supplementaryAgreementAdditionalProducts = new JTextField[6];
        supplementaryAgreementAdditionalProductsCount = new JTextField[6];
        supplementaryAgreementAdditionalProductsDiscount = new JTextField[6];
        supplementaryAgreementAdditionalProductsFullPrice = new JTextField[6];
        supplementaryAgreementAdditionalProductsWithDiscount = new JTextField[6];
        for (int i = 0; i < 6; i++) {
            supplementaryAgreementAdditionalProducts[i] = new JTextField();
            supplementaryAgreementAdditionalProductsCount[i] = new JTextField();
            supplementaryAgreementAdditionalProductsDiscount[i] = new JTextField();
            supplementaryAgreementAdditionalProductsFullPrice[i] = new JTextField();
            supplementaryAgreementAdditionalProductsWithDiscount[i] = new JTextField();

            gridBagConstraints.gridy = 5+i;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.ipadx = 5;
            gridBagConstraints.gridwidth = 1;
            staticJPanel.add(new JLabel(String.valueOf(i+1)),gridBagConstraints);
            gridBagConstraints.gridx = 1;
            gridBagConstraints.ipadx = 310;
            gridBagConstraints.gridwidth = 3;
            supplementaryAgreementAdditionalProducts[i].setName("supplementaryAgreementAdditionalProducts"+i);
            staticJPanel.add(supplementaryAgreementAdditionalProducts[i],gridBagConstraints);
            gridBagConstraints.gridx = 4;
            gridBagConstraints.ipadx = 10;
            gridBagConstraints.gridwidth = 1;
            supplementaryAgreementAdditionalProductsCount[i].setText(String.valueOf(1));
            supplementaryAgreementAdditionalProductsCount[i].setName("supplementaryAgreementAdditionalProductsCount"+i);
            supplementaryAgreementAdditionalProductsCount[i].addKeyListener(controller);
            staticJPanel.add(supplementaryAgreementAdditionalProductsCount[i],gridBagConstraints);
            gridBagConstraints.gridx = 5;
            gridBagConstraints.ipadx = 50;
            supplementaryAgreementAdditionalProductsDiscount[i].setText(String.valueOf(0));
            supplementaryAgreementAdditionalProductsFullPrice[i].setName("supplementaryAgreementAdditionalProductsFullPrice"+i);
            supplementaryAgreementAdditionalProductsFullPrice[i].addKeyListener(controller);
            staticJPanel.add(supplementaryAgreementAdditionalProductsFullPrice[i],gridBagConstraints);
            gridBagConstraints.gridx = 6;
            gridBagConstraints.ipadx = 15;
            supplementaryAgreementAdditionalProductsDiscount[i].setName("supplementaryAgreementAdditionalProductsDiscount"+i);
            supplementaryAgreementAdditionalProductsDiscount[i].addKeyListener(controller);
            staticJPanel.add(supplementaryAgreementAdditionalProductsDiscount[i],gridBagConstraints);
            gridBagConstraints.gridx = 7;
            gridBagConstraints.ipadx = 50;
            supplementaryAgreementAdditionalProductsWithDiscount[i].setName("supplementaryAgreementAdditionalProductsWithDiscount"+i);
            supplementaryAgreementAdditionalProductsWithDiscount[i].addKeyListener(controller);
            staticJPanel.add(supplementaryAgreementAdditionalProductsWithDiscount[i],gridBagConstraints);
            try {
                supplementaryAgreementAdditionalProducts[i].setText(dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProductsSupplementaryAgreementUpSale()[i].getName());
                supplementaryAgreementAdditionalProductsCount[i].setText(dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProductsSupplementaryAgreementUpSale()[i].getCount());
                supplementaryAgreementAdditionalProductsDiscount[i].setText(dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProductsSupplementaryAgreementUpSale()[i].getDiscount());
                supplementaryAgreementAdditionalProductsFullPrice[i].setText(dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProductsSupplementaryAgreementUpSale()[i].getFullPrice());
                supplementaryAgreementAdditionalProductsWithDiscount[i].setText(dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProductsSupplementaryAgreementUpSale()[i].getPriceWithDiscount());

            } catch (NullPointerException e){

            }
        }

        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 0;
        staticJPanel.add(new JLabel("Сумма договора"),gridBagConstraints);

        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxSumUpSaleInBYNSupplementaryAgreement = new JCheckBox();
        checkBoxSumUpSaleInBYNSupplementaryAgreement.setName("checkBoxSumUpSaleInBYNSupplementaryAgreement");
        checkBoxSumUpSaleInBYNSupplementaryAgreement.addActionListener(controller);
        staticJPanel.add(checkBoxSumUpSaleInBYNSupplementaryAgreement,gridBagConstraints);


        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        sumUpSaleInBYNSupplementaryAgreement = new JTextField();
        sumUpSaleInBYNSupplementaryAgreement.setName("sumUpSaleInBYNSupplementaryAgreement");
        if(dataClient.getUpSaleContract().getAllSumUpSaleInBYN()!=0){
            sumUpSaleInBYNSupplementaryAgreement.setText(String.valueOf(dataClient.getSupplementaryAgreementUpSaleContract().getAllSumUpSaleInBYNSupplementaryAgreement()));
        }
        sumUpSaleInBYNSupplementaryAgreement.addKeyListener(controller);
        sumUpSaleInBYNSupplementaryAgreement.setEnabled(false);
        staticJPanel.add(sumUpSaleInBYNSupplementaryAgreement,gridBagConstraints);




        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        staticJPanel.add(new JLabel("Предоплата:"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth =1;
        JCheckBox checkBoxPrepaymentUpSaleSupplementaryAgreement  = new JCheckBox();
        checkBoxPrepaymentUpSaleSupplementaryAgreement.setName("checkBoxPrepaymentUpSaleSupplementaryAgreement");
        checkBoxPrepaymentUpSaleSupplementaryAgreement.addActionListener(controller);
        staticJPanel.add(checkBoxPrepaymentUpSaleSupplementaryAgreement,gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        prepaymentUpSaleSupplementaryAgreement = new JTextField();
        prepaymentUpSaleSupplementaryAgreement.setName("prepaymentUpSaleSupplementaryAgreement");
        prepaymentUpSaleSupplementaryAgreement.setText(String.valueOf(dataClient.getSupplementaryAgreementUpSaleContract().getPrepaymentUpSaleSupplementaryAgreement()));
        prepaymentUpSaleSupplementaryAgreement.addKeyListener(controller);
        prepaymentUpSaleSupplementaryAgreement.setEnabled(false);
        staticJPanel.add(prepaymentUpSaleSupplementaryAgreement,gridBagConstraints);

        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        staticJPanel.add(new JLabel("Оплата до 100%"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxPayUpTo100percentUpSaleSupplementaryAgreement  = new JCheckBox();
        checkBoxPayUpTo100percentUpSaleSupplementaryAgreement.setName("checkBoxPayUpTo100percentUpSaleSupplementaryAgreement");
        checkBoxPayUpTo100percentUpSaleSupplementaryAgreement.addActionListener(controller);
        staticJPanel.add(checkBoxPayUpTo100percentUpSaleSupplementaryAgreement,gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        payUpTo100percentUpSaleSupplementaryAgreement = new JTextField();
        payUpTo100percentUpSaleSupplementaryAgreement.setName("payUpTo100percentUpSaleSupplementaryAgreement");
        payUpTo100percentUpSaleSupplementaryAgreement.setText(String.valueOf(dataClient.getSupplementaryAgreementUpSaleContract().getPayUpTo100percentUpSaleSupplementaryAgreement()));
        payUpTo100percentUpSaleSupplementaryAgreement.addKeyListener(controller);
        payUpTo100percentUpSaleSupplementaryAgreement.setEnabled(false);
        staticJPanel.add(payUpTo100percentUpSaleSupplementaryAgreement,gridBagConstraints);

        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        staticJPanel.add(new JLabel("Дата UpSale"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxJust  = new JCheckBox();
        checkBoxJust.setName("checkBoxJust");
        checkBoxJust.addActionListener(controller);
        checkBoxJust.setEnabled(false);
        staticJPanel.add(checkBoxJust,gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        JFormattedTextField dateCreateUpSaleContract = new JFormattedTextField(dateCreateContractMask);
        dateCreateUpSaleContract.setName("dateCreateUpSaleContract");
        dateCreateUpSaleContract.setText(String.valueOf(dataClient.getUpSaleContract().getDateCreateUpSaleContract()));
        dateCreateUpSaleContract.addKeyListener(controller);
        dateCreateUpSaleContract.setEnabled(false);
        staticJPanel.add(dateCreateUpSaleContract,gridBagConstraints);

        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        staticJPanel.add(new JLabel("Дата подписания"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxDateCreateSupplementaryAgreementUpSaleContract  = new JCheckBox();
        checkBoxDateCreateSupplementaryAgreementUpSaleContract.setName("checkBoxDateCreateSupplementaryAgreementUpSaleContract");
        checkBoxDateCreateSupplementaryAgreementUpSaleContract.addActionListener(controller);
        staticJPanel.add(checkBoxDateCreateSupplementaryAgreementUpSaleContract,gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        dateCreateSupplementaryAgreementUpSaleContract = new JFormattedTextField(dateCreateContractMask);
        dateCreateSupplementaryAgreementUpSaleContract.setName("dateCreateSupplementaryAgreementUpSaleContract");
        dateCreateSupplementaryAgreementUpSaleContract.setText(String.valueOf(dataClient.getSupplementaryAgreementUpSaleContract().getDateCreateSupplementaryAgreementUpSaleContract()));
        dateCreateSupplementaryAgreementUpSaleContract.addKeyListener(controller);
        dateCreateSupplementaryAgreementUpSaleContract.setEnabled(false);
        staticJPanel.add(dateCreateSupplementaryAgreementUpSaleContract,gridBagConstraints);

        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        staticJPanel.add(new JLabel("Номер"),gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxNumberSupplementaryAgreementUpSale  = new JCheckBox();
        checkBoxNumberSupplementaryAgreementUpSale.setName("checkBoxNumberSupplementaryAgreementUpSale");
        checkBoxNumberSupplementaryAgreementUpSale.addActionListener(controller);
        staticJPanel.add(checkBoxNumberSupplementaryAgreementUpSale,gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        numberSupplementaryAgreementUpSale = new JTextField();
        numberSupplementaryAgreementUpSale.setName("numberSupplementaryAgreementUpSale");
        numberSupplementaryAgreementUpSale.setText(String.valueOf(dataClient.getSupplementaryAgreementBasicContract().getNumberSupplementaryAgreementBasicContract()));
        numberSupplementaryAgreementUpSale.addKeyListener(controller);
        numberSupplementaryAgreementUpSale.setEnabled(false);
        staticJPanel.add(numberSupplementaryAgreementUpSale,gridBagConstraints);


        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 0;
        JButton saveDataSupplementaryAgreementUpSaleContact = new JButton("Сохранить");
        saveDataSupplementaryAgreementUpSaleContact.setName("saveDataSupplementaryAgreementUpSaleContact");
        saveDataSupplementaryAgreementUpSaleContact.addActionListener(controller);
        staticJPanel.add(saveDataSupplementaryAgreementUpSaleContact,gridBagConstraints);

        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 4;
        JButton printSupplementaryAgreementUpSaleContract = new JButton("Распечатать 2 раза");
        printSupplementaryAgreementUpSaleContract.setName("printSupplementaryAgreementUpSaleContract");
        printSupplementaryAgreementUpSaleContract.addActionListener(controller);
        staticJPanel.add(printSupplementaryAgreementUpSaleContract,gridBagConstraints);

        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        JButton openDirectoryWithFile = new JButton("Открыть папку с файлом");
        openDirectoryWithFile.setName("openDirectoryWithFile");
        openDirectoryWithFile.addActionListener(controller);
        staticJPanel.add(openDirectoryWithFile,gridBagConstraints);

        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 4;
        JButton openFileUpSaleContract = new JButton("Открыть файл");
        openFileUpSaleContract.setName("openFileSupplementaryAgreementUpSaleContract");
        openFileUpSaleContract.addActionListener(controller);
        staticJPanel.add(openFileUpSaleContract,gridBagConstraints);

        gridBagConstraints.gridy = 19;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipady = 75;
        gridBagConstraints.gridwidth =8;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);

        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipady = 0;
        JButton backSelectClient = new JButton("Назад");
        backSelectClient.setName("backSelectClient");
        backSelectClient.addActionListener(controller);
        staticJPanel.add(backSelectClient,gridBagConstraints);

        gridBagConstraints.gridy = 21;
        gridBagConstraints.gridx = 0;
        JButton mainPage = new JButton("Главная страница");
        mainPage.setName("mainPage");
        mainPage.addActionListener(controller);
        staticJPanel.add(mainPage,gridBagConstraints);

        if(sumUpSaleInBYNSupplementaryAgreement.getText().equals("")||sumUpSaleInBYNSupplementaryAgreement.getText().equals("0")){
            fillData();
        }

        staticJPanel.revalidate();
        staticJPanel.repaint();
    }
    public void editSupplementaryAgreementUpSaleSumProductPriceAndDiscountAndCount(JTextField jTextField){
        int i = Integer.parseInt(jTextField.getName().substring(jTextField.getName().length()-1));
        int jCount = Integer.parseInt(supplementaryAgreementAdditionalProductsCount[i].getText());
        int jDiscount = Integer.parseInt(supplementaryAgreementAdditionalProductsDiscount[i].getText());
        int jPrice = Integer.parseInt(supplementaryAgreementAdditionalProductsFullPrice[i].getText());
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
        HashMap<String,String> map = new HashMap<>();
        for (int i = 0; i < supplementaryAgreementAdditionalProducts.length; i++) {
            map.put("supplementaryAgreementAdditionalProducts"+i,supplementaryAgreementAdditionalProducts[i].getText());
            map.put("supplementaryAgreementAdditionalProductsCount"+i,supplementaryAgreementAdditionalProductsCount[i].getText());
            map.put("supplementaryAgreementAdditionalProductsFullPrice"+i,supplementaryAgreementAdditionalProductsFullPrice[i].getText());
            map.put("supplementaryAgreementAdditionalProductsDiscount" + i,supplementaryAgreementAdditionalProductsDiscount[i].getText());
            map.put("supplementaryAgreementAdditionalProductsWithDiscount"+i,supplementaryAgreementAdditionalProductsWithDiscount[i].getText());
        }
        map.put("dateCreateSupplementaryAgreementUpSaleContract",dateCreateSupplementaryAgreementUpSaleContract.getText());
        map.put("numberSupplementaryAgreementUpSale",numberSupplementaryAgreementUpSale.getText());
        map.put("sumUpSaleInBYNSupplementaryAgreement",sumUpSaleInBYNSupplementaryAgreement.getText());
        map.put("prepaymentUpSaleSupplementaryAgreement",prepaymentUpSaleSupplementaryAgreement.getText());
        map.put("payUpTo100percentUpSaleSupplementaryAgreement",payUpTo100percentUpSaleSupplementaryAgreement.getText());
        return map;
    }
    private void fillData(){//Если данных нет берем все из UpSale
        for (int i = 0; i < supplementaryAgreementAdditionalProducts.length; i++) {
            try {
                supplementaryAgreementAdditionalProducts[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getName());
                supplementaryAgreementAdditionalProductsCount[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getCount());
                supplementaryAgreementAdditionalProductsDiscount[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getDiscount());
                supplementaryAgreementAdditionalProductsFullPrice[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getFullPrice());
                supplementaryAgreementAdditionalProductsWithDiscount[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getPriceWithDiscount());
            } catch (NullPointerException e) {

            }
        }
        sumUpSaleInBYNSupplementaryAgreement.setText(String.valueOf(dataClient.getUpSaleContract().getAllSumUpSaleInBYN()));
        prepaymentUpSaleSupplementaryAgreement.setText(String.valueOf(dataClient.getUpSaleContract().getPrepaymentUpSale()));
        payUpTo100percentUpSaleSupplementaryAgreement.setText(String.valueOf(dataClient.getUpSaleContract().getPayUpTo100percentUpSale()));
        dateCreateSupplementaryAgreementUpSaleContract.setText(String.valueOf(dataClient.getSupplementaryAgreementUpSaleContract().getDateCreateSupplementaryAgreementUpSaleContract()));

    }

}
