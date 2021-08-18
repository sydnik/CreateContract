package org.sydnik.createContract.view;

import org.sydnik.createContract.MyController;
import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.exception.DontHaveData;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.util.HashMap;

public class ViewUpSaleContract implements Display {
    private JPanel staticJPanel;
    private DataClient dataClient;
    private MyController controller;
    private JTextField additionalProductsWithDiscount[];
    private JTextField additionalProducts[];
    private JTextField additionalProductsCount[];
    private JTextField additionalProductsDiscount[];
    private JTextField additionalProductsFullPrice[];
    private JTextField allSumUpSaleInBYN;
    private JTextField prepaymentUpSale;
    private JTextField payUpTo100percentUpSale;
    private JFormattedTextField dateCreateUpSaleContract;

    public ViewUpSaleContract(JPanel staticJPanel, DataClient dataClient, MyController controller) {
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
        } catch (Exception e) {
        }

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
        staticJPanel.add(j1, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridwidth = 5;
        JLabel j2 = new JLabel(dataClient.getFullNameClient());
        j2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j2, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        JLabel j4 = new JLabel("UpSale договор ");
        j4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j4, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridwidth = 5;
        JLabel j3 = new JLabel(dataClient.getNumberContract() + " " + dataClient.getStrangeName());
        j3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j3, gridBagConstraints);

        gridBagConstraints.gridy = 2;
        staticJPanel.add(new JLabel(" "), gridBagConstraints);
        gridBagConstraints.gridy = 3;
        staticJPanel.add(new JLabel(" "), gridBagConstraints);
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 0;
        staticJPanel.add(new JLabel("№"), gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 3;
        staticJPanel.add(new JLabel(" Наименование товара"), gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        staticJPanel.add(new JLabel("К"), gridBagConstraints);
        gridBagConstraints.gridx = 5;
        staticJPanel.add(new JLabel("Цена"), gridBagConstraints);
        gridBagConstraints.gridx = 6;
        staticJPanel.add(new JLabel("%"), gridBagConstraints);
        gridBagConstraints.gridx = 7;
        staticJPanel.add(new JLabel("Всего"), gridBagConstraints);
        additionalProducts = new JTextField[6];
        additionalProductsCount = new JTextField[6];
        additionalProductsDiscount = new JTextField[6];
        additionalProductsFullPrice = new JTextField[6];
        additionalProductsWithDiscount = new JTextField[6];
        for (int i = 0; i < 6; i++) {
            additionalProducts[i] = new JTextField();
            additionalProductsCount[i] = new JTextField();
            additionalProductsDiscount[i] = new JTextField();
            additionalProductsFullPrice[i] = new JTextField();
            additionalProductsWithDiscount[i] = new JTextField();
            gridBagConstraints.gridy = 5 + i;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.ipadx = 5;
            gridBagConstraints.gridwidth = 1;
            staticJPanel.add(new JLabel(String.valueOf(i + 1)), gridBagConstraints);
            gridBagConstraints.gridx = 1;
            gridBagConstraints.ipadx = 310;
            gridBagConstraints.gridwidth = 3;
            additionalProducts[i].setName("additionalProducts" + i);
            staticJPanel.add(additionalProducts[i], gridBagConstraints);
            gridBagConstraints.gridx = 4;
            gridBagConstraints.ipadx = 10;
            gridBagConstraints.gridwidth = 1;
            additionalProductsCount[i].setText(String.valueOf(1));
            additionalProductsCount[i].setName("additionalProductsCount" + i);
            additionalProductsCount[i].addKeyListener(controller);
            staticJPanel.add(additionalProductsCount[i], gridBagConstraints);
            gridBagConstraints.gridx = 5;
            gridBagConstraints.ipadx = 50;
            additionalProductsDiscount[i].setText(String.valueOf(0));
            additionalProductsFullPrice[i].setName("additionalProductsFullPrice" + i);
            additionalProductsFullPrice[i].addKeyListener(controller);
            staticJPanel.add(additionalProductsFullPrice[i], gridBagConstraints);
            gridBagConstraints.gridx = 6;
            gridBagConstraints.ipadx = 15;
            additionalProductsDiscount[i].setName("additionalProductsDiscount" + i);
            additionalProductsDiscount[i].addKeyListener(controller);
            staticJPanel.add(additionalProductsDiscount[i], gridBagConstraints);
            gridBagConstraints.gridx = 7;
            gridBagConstraints.ipadx = 50;
            additionalProductsWithDiscount[i].setName("additionalProductsWithDiscount" + i);
            additionalProductsWithDiscount[i].addKeyListener(controller);
            staticJPanel.add(additionalProductsWithDiscount[i], gridBagConstraints);
            try {
                if (dataClient.getUpSaleContract().getListAdditionalProducts()[i] != null) {
                    additionalProducts[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getName());
                    additionalProductsCount[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getCount());
                    additionalProductsDiscount[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getDiscount());
                    additionalProductsFullPrice[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getFullPrice());
                    additionalProductsWithDiscount[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getPriceWithDiscount());
                }
            } catch (NullPointerException e) {

            }


        }
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 0;
        staticJPanel.add(new JLabel("Сумма договора"), gridBagConstraints);


        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        allSumUpSaleInBYN = new JTextField();
        allSumUpSaleInBYN.setName("allSumUpSaleInBYN");
        if (dataClient.getUpSaleContract().getAllSumUpSaleInBYN() != 0) {
            allSumUpSaleInBYN.setText(String.valueOf(dataClient.getUpSaleContract().getAllSumUpSaleInBYN()));
        }
        allSumUpSaleInBYN.addKeyListener(controller);
        allSumUpSaleInBYN.setEnabled(false);
        staticJPanel.add(allSumUpSaleInBYN, gridBagConstraints);

        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxAllSumUpSaleInBYN = new JCheckBox();
        checkBoxAllSumUpSaleInBYN.setName("checkBoxAllSumUpSaleInBYN");
        checkBoxAllSumUpSaleInBYN.addActionListener(controller);
        staticJPanel.add(checkBoxAllSumUpSaleInBYN, gridBagConstraints);


        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 0;
        staticJPanel.add(new JLabel("Предоплата:"), gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxPrepaymentUpSale = new JCheckBox();
        checkBoxPrepaymentUpSale.setName("checkBoxPrepaymentUpSale");
        checkBoxPrepaymentUpSale.addActionListener(controller);
        staticJPanel.add(checkBoxPrepaymentUpSale, gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        prepaymentUpSale = new JTextField();
        prepaymentUpSale.setName("prepaymentUpSale");
        prepaymentUpSale.setText(String.valueOf(dataClient.getUpSaleContract().getPrepaymentUpSale()));
        prepaymentUpSale.addKeyListener(controller);
        prepaymentUpSale.setEnabled(false);
        staticJPanel.add(prepaymentUpSale, gridBagConstraints);

        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        staticJPanel.add(new JLabel("Оплата до 100%"), gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxPayUpTo100percentUpSale = new JCheckBox();
        checkBoxPayUpTo100percentUpSale.setName("checkBoxPayUpTo100percentUpSale");
        checkBoxPayUpTo100percentUpSale.addActionListener(controller);
        staticJPanel.add(checkBoxPayUpTo100percentUpSale, gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        payUpTo100percentUpSale = new JTextField();
        payUpTo100percentUpSale.setName("payUpTo100percentUpSale");
        payUpTo100percentUpSale.setText(String.valueOf(dataClient.getUpSaleContract().getPayUpTo100percentUpSale()));
        payUpTo100percentUpSale.addKeyListener(controller);
        payUpTo100percentUpSale.setEnabled(false);
        staticJPanel.add(payUpTo100percentUpSale, gridBagConstraints);

        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        staticJPanel.add(new JLabel("Дата подписания"), gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxDateCreateUpSaleContract = new JCheckBox();
        checkBoxDateCreateUpSaleContract.setName("checkBoxDateCreateUpSaleContract");
        checkBoxDateCreateUpSaleContract.addActionListener(controller);
        staticJPanel.add(checkBoxDateCreateUpSaleContract, gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        dateCreateUpSaleContract = new JFormattedTextField(dateCreateContractMask);
        dateCreateUpSaleContract.setName("dateCreateUpSaleContract");
        dateCreateUpSaleContract.setText(String.valueOf(dataClient.getUpSaleContract().getDateCreateUpSaleContract()));
        dateCreateUpSaleContract.addKeyListener(controller);
        dateCreateUpSaleContract.setEnabled(false);
        staticJPanel.add(dateCreateUpSaleContract, gridBagConstraints);

        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        JButton saveDataUpSaleContact = new JButton("Сохранить");
        saveDataUpSaleContact.setName("saveDataUpSaleContact");
        saveDataUpSaleContact.addActionListener(controller);
        staticJPanel.add(saveDataUpSaleContact, gridBagConstraints);

        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 4;
        JButton printUpSaleContract = new JButton("Распечатать 2 раза");
        printUpSaleContract.setName("printUpSaleContract");
        printUpSaleContract.addActionListener(controller);
        staticJPanel.add(printUpSaleContract, gridBagConstraints);

        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        JButton openDirectoryWithFile = new JButton("Открыть папку с файлом");
        openDirectoryWithFile.setName("openDirectoryWithFile");
        openDirectoryWithFile.addActionListener(controller);
        staticJPanel.add(openDirectoryWithFile, gridBagConstraints);

        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 4;
        JButton openFileUpSaleContract = new JButton("Открыть файл");
        openFileUpSaleContract.setName("openFileUpSaleContract");
        openFileUpSaleContract.addActionListener(controller);
        staticJPanel.add(openFileUpSaleContract, gridBagConstraints);

        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipady = 120;
        gridBagConstraints.gridwidth = 8;
        staticJPanel.add(new JLabel(" "), gridBagConstraints);

        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipady = 0;
        JButton backSelectClient = new JButton("Назад");
        backSelectClient.setName("backSelectClient");
        backSelectClient.addActionListener(controller);
        staticJPanel.add(backSelectClient, gridBagConstraints);

        gridBagConstraints.gridy = 19;
        gridBagConstraints.gridx = 0;
        JButton mainPage = new JButton("Главная страница");
        mainPage.setName("mainPage");
        mainPage.addActionListener(controller);
        staticJPanel.add(mainPage, gridBagConstraints);

        staticJPanel.revalidate();
        staticJPanel.repaint();
    }

    public void editUpSaleEditSumProductPriceAndDiscountAndCount(JTextField jTextField) {
        int i = Integer.parseInt(jTextField.getName().substring(jTextField.getName().length()-1));
        int jCount = Integer.parseInt(additionalProductsCount[i].getText());
        int jDiscount = Integer.parseInt(additionalProductsDiscount[i].getText());
        int jPrice = Integer.parseInt(additionalProductsFullPrice[i].getText());
        additionalProductsWithDiscount[i].setText(String.valueOf(((int) Math.round((double) jPrice * ((double) (100 - jDiscount) / 100))) * jCount));
        staticJPanel.revalidate();
        staticJPanel.repaint();
        editWithDiscount();


    }

    public void editWithDiscount() {
        int allSum = 0;
        for (int i = 0; i < additionalProductsWithDiscount.length; i++) {
            try {
                allSum = allSum + Integer.parseInt(additionalProductsWithDiscount[i].getText());

            } catch (NumberFormatException e) {

            }
        }
        allSumUpSaleInBYN.setText(String.valueOf(allSum));
        editAllSumUpSaleInBYN();
    }
    public void editAllSumUpSaleInBYN(){
        int allSum = Integer.parseInt(allSumUpSaleInBYN.getText());
        int intPrepaymentUpSale = (int) Math.round(((double)allSum)/10);
        prepaymentUpSale.setText(String.valueOf(intPrepaymentUpSale));
        staticJPanel.revalidate();
        staticJPanel.repaint();
        editPrepaymentUpSaleUpSale();
    }
    public void editPrepaymentUpSaleUpSale (){
        int prepayment = Integer.parseInt(prepaymentUpSale.getText());
        int allSum = Integer.parseInt (allSumUpSaleInBYN.getText());
        payUpTo100percentUpSale.setText(String.valueOf(allSum-prepayment));
        staticJPanel.revalidate();
        staticJPanel.repaint();

    }
    @Override
    public HashMap<String, String> getDataForSave () throws DontHaveData {
        if(allSumUpSaleInBYN.equals("")||allSumUpSaleInBYN.equals("0")||
                prepaymentUpSale.equals("")||payUpTo100percentUpSale.equals("")||
                dateCreateUpSaleContract.equals("  .  .    ")){
            throw  new DontHaveData("Заполните все поля техники должно быть минимум 1");
        }
        HashMap<String,String> map = new HashMap<>();
        for (int i = 0; i < additionalProducts.length; i++) {
            map.put("additionalProducts"+i,additionalProducts[i].getText());
            map.put("additionalProductsCount"+i,additionalProductsCount[i].getText());
            map.put("additionalProductsFullPrice"+i,additionalProductsFullPrice[i].getText());
            map.put("additionalProductsDiscount" + i,additionalProductsDiscount[i].getText());
            map.put("additionalProductsWithDiscount"+i,additionalProductsWithDiscount[i].getText());
        }
        map.put("dateCreateUpSaleContract",dateCreateUpSaleContract.getText());
        map.put("allSumUpSaleInBYN",allSumUpSaleInBYN.getText());
        map.put("prepaymentUpSale",prepaymentUpSale.getText());
        map.put("payUpTo100percentUpSale",payUpTo100percentUpSale.getText());
        return map;
    }
}
