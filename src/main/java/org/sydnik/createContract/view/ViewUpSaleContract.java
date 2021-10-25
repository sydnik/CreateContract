package org.sydnik.createContract.view;

import org.sydnik.createContract.MyController;
import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.exception.DontHaveData;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.HashMap;


public class ViewUpSaleContract extends MainViewContract implements Display,DataForViber {
    public static final int COUNT_ADDITIONAL_PRODUCT = 9;
    private JTextField additionalProductsWithDiscount[];
    private JTextField additionalProducts[];
    private JTextField additionalProductsCount[];
    private JTextField additionalProductsDiscount[];
    private JTextField additionalProductsFullPrice[];
    private JTextField allSumUpSaleInBYN;
    private JTextField prepaymentUpSale;
    private JTextField payUpTo100percentUpSale;
    private JFormattedTextField dateCreateUpSaleContract;
    private JButton[] buttonsForDataForViber;

    public ViewUpSaleContract(JPanel staticJPanel, DataClient dataClient, MyController controller) {
        super(staticJPanel,dataClient,"UpSale",controller);
    }

    @Override
    public void display() {
        int line =-1;
        startPage();
        MaskFormatter
                dateCreateContractMask = null;
        try {
            dateCreateContractMask = new MaskFormatter("##.##.####");
        } catch (Exception e) {
        }
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        gridBagConstraints.gridy = ++line;
        gridBagConstraints.gridx = 0;
        workingWindow.add(new JLabel("№"), gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 3;
        workingWindow.add(new JLabel(" Наименование товара"), gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        workingWindow.add(new JLabel("К"), gridBagConstraints);
        gridBagConstraints.gridx = 5;
        workingWindow.add(new JLabel("Цена"), gridBagConstraints);
        gridBagConstraints.gridx = 6;
        workingWindow.add(new JLabel("%"), gridBagConstraints);
        gridBagConstraints.gridx = 7;
        workingWindow.add(new JLabel("Всего"), gridBagConstraints);
        additionalProducts = new JTextField[COUNT_ADDITIONAL_PRODUCT];
        additionalProductsCount = new JTextField[COUNT_ADDITIONAL_PRODUCT];
        additionalProductsDiscount = new JTextField[COUNT_ADDITIONAL_PRODUCT];
        additionalProductsFullPrice = new JTextField[COUNT_ADDITIONAL_PRODUCT];
        additionalProductsWithDiscount = new JTextField[COUNT_ADDITIONAL_PRODUCT];
        int i =0;
        line++;
        for (; i < COUNT_ADDITIONAL_PRODUCT; i++) {
            additionalProducts[i] = new JTextField();
            additionalProductsCount[i] = new JTextField();
            additionalProductsDiscount[i] = new JTextField();
            additionalProductsFullPrice[i] = new JTextField();
            additionalProductsWithDiscount[i] = new JTextField();
            gridBagConstraints.gridy = line + i;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.ipadx = 5;
            gridBagConstraints.gridwidth = 1;
            workingWindow.add(new JLabel(String.valueOf(i + 1)), gridBagConstraints);
            gridBagConstraints.gridx = 1;
            gridBagConstraints.ipadx = 310;
            gridBagConstraints.gridwidth = 3;
            additionalProducts[i].setName("additionalProducts" + i);
            workingWindow.add(additionalProducts[i], gridBagConstraints);
            gridBagConstraints.gridx = 4;
            gridBagConstraints.ipadx = 10;
            gridBagConstraints.gridwidth = 1;
            additionalProductsCount[i].setText(String.valueOf(1));
            additionalProductsCount[i].setName("additionalProductsCount" + i);
            additionalProductsCount[i].addKeyListener(controller);
            workingWindow.add(additionalProductsCount[i], gridBagConstraints);
            gridBagConstraints.gridx = 5;
            gridBagConstraints.ipadx = 50;
            additionalProductsDiscount[i].setText(String.valueOf(0));
            additionalProductsFullPrice[i].setName("additionalProductsFullPrice" + i);
            additionalProductsFullPrice[i].addKeyListener(controller);
            workingWindow.add(additionalProductsFullPrice[i], gridBagConstraints);
            gridBagConstraints.gridx = 6;
            gridBagConstraints.ipadx = 15;
            additionalProductsDiscount[i].setName("additionalProductsDiscount" + i);
            additionalProductsDiscount[i].addKeyListener(controller);
            workingWindow.add(additionalProductsDiscount[i], gridBagConstraints);
            gridBagConstraints.gridx = 7;
            gridBagConstraints.ipadx = 50;
            additionalProductsWithDiscount[i].setName("additionalProductsWithDiscount" + i);
            additionalProductsWithDiscount[i].addKeyListener(controller);
            workingWindow.add(additionalProductsWithDiscount[i], gridBagConstraints);
            try {
                if (dataClient.getUpSaleContract().getListAdditionalProducts()[i] != null) {
                    additionalProducts[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getName());
                    additionalProductsCount[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getCount());
                    additionalProductsDiscount[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getDiscount());
                    additionalProductsFullPrice[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getFullPrice());
                    additionalProductsWithDiscount[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getPriceWithDiscount());
                }
            } catch (Exception e) {

            }


        }
        line = line +i;
        gridBagConstraints.gridy = ++line;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 0;
        workingWindow.add(new JLabel("Сумма договора"), gridBagConstraints);


        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        allSumUpSaleInBYN = new JTextField();
        allSumUpSaleInBYN.setName("allSumUpSaleInBYN");
        if (dataClient.getUpSaleContract().getAllSumUpSaleInBYN() != 0) {
            allSumUpSaleInBYN.setText(String.valueOf(dataClient.getUpSaleContract().getAllSumUpSaleInBYN()));
        }
        allSumUpSaleInBYN.addKeyListener(controller);
        allSumUpSaleInBYN.setEnabled(false);
        workingWindow.add(allSumUpSaleInBYN, gridBagConstraints);

        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxAllSumUpSaleInBYN = new JCheckBox();
        checkBoxAllSumUpSaleInBYN.setName("checkBoxAllSumUpSaleInBYN");
        checkBoxAllSumUpSaleInBYN.addActionListener(controller);
        workingWindow.add(checkBoxAllSumUpSaleInBYN, gridBagConstraints);


        gridBagConstraints.gridy = ++line;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 0;
        workingWindow.add(new JLabel("Предоплата:"), gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxPrepaymentUpSale = new JCheckBox();
        checkBoxPrepaymentUpSale.setName("checkBoxPrepaymentUpSale");
        checkBoxPrepaymentUpSale.addActionListener(controller);
        workingWindow.add(checkBoxPrepaymentUpSale, gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        prepaymentUpSale = new JTextField();
        prepaymentUpSale.setName("prepaymentUpSale");
        prepaymentUpSale.setText(String.valueOf(dataClient.getUpSaleContract().getPrepaymentUpSale()));
        prepaymentUpSale.addKeyListener(controller);
        prepaymentUpSale.setEnabled(false);
        workingWindow.add(prepaymentUpSale, gridBagConstraints);

        gridBagConstraints.gridy = ++line;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        workingWindow.add(new JLabel("Оплата до 100%"), gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxPayUpTo100percentUpSale = new JCheckBox();
        checkBoxPayUpTo100percentUpSale.setName("checkBoxPayUpTo100percentUpSale");
        checkBoxPayUpTo100percentUpSale.addActionListener(controller);
        workingWindow.add(checkBoxPayUpTo100percentUpSale, gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        payUpTo100percentUpSale = new JTextField();
        payUpTo100percentUpSale.setName("payUpTo100percentUpSale");
        payUpTo100percentUpSale.setText(String.valueOf(dataClient.getUpSaleContract().getPayUpTo100percentUpSale()));
        payUpTo100percentUpSale.addKeyListener(controller);
        payUpTo100percentUpSale.setEnabled(false);
        workingWindow.add(payUpTo100percentUpSale, gridBagConstraints);

        gridBagConstraints.gridy = ++line;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        workingWindow.add(new JLabel("Дата подписания"), gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 1;
        JCheckBox checkBoxDateCreateUpSaleContract = new JCheckBox();
        checkBoxDateCreateUpSaleContract.setName("checkBoxDateCreateUpSaleContract");
        checkBoxDateCreateUpSaleContract.addActionListener(controller);
        workingWindow.add(checkBoxDateCreateUpSaleContract, gridBagConstraints);
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 3;
        dateCreateUpSaleContract = new JFormattedTextField(dateCreateContractMask);
        dateCreateUpSaleContract.setName("dateCreateUpSaleContract");
        dateCreateUpSaleContract.setText(String.valueOf(dataClient.getUpSaleContract().getDateCreateUpSaleContract()));
        dateCreateUpSaleContract.addKeyListener(controller);
        dateCreateUpSaleContract.setEnabled(false);
        workingWindow.add(dateCreateUpSaleContract, gridBagConstraints);

        gridBagConstraints.gridy = ++line;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        JButton saveDataUpSaleContact = new JButton("Сохранить");
        saveDataUpSaleContact.setName("saveDataUpSaleContact");
        saveDataUpSaleContact.addActionListener(controller);
        workingWindow.add(saveDataUpSaleContact, gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 4;
        JButton printUpSaleContract = new JButton("Распечатать 2 раза");
        printUpSaleContract.setName("printUpSaleContract");
        printUpSaleContract.addActionListener(controller);
        workingWindow.add(printUpSaleContract, gridBagConstraints);

        gridBagConstraints.gridy = ++line;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 4;
        JButton openDirectoryWithFile = new JButton("Открыть папку с файлом");
        openDirectoryWithFile.setName("openDirectoryWithFile");
        openDirectoryWithFile.addActionListener(controller);
        workingWindow.add(openDirectoryWithFile, gridBagConstraints);
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridwidth = 4;
        JButton openFileUpSaleContract = new JButton("Открыть файл");
        openFileUpSaleContract.setName("openFileUpSaleContract");
        openFileUpSaleContract.addActionListener(controller);
        workingWindow.add(openFileUpSaleContract, gridBagConstraints);

        gridBagConstraints.gridy = ++line;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 8;
        JButton dataForViberBasicContract = new JButton("Данные для вайбер");
        dataForViberBasicContract.setName("dataForViber");
        dataForViberBasicContract.addActionListener(controller);
        workingWindow.add(dataForViberBasicContract,gridBagConstraints);

        super.endPage();
    }
    @Override
    public HashMap<String, String> getDataForSave () throws DontHaveData {
        if(allSumUpSaleInBYN.equals("")||allSumUpSaleInBYN.equals("0")||
                prepaymentUpSale.equals("")||payUpTo100percentUpSale.equals("")||
                dateCreateUpSaleContract.equals("  .  .    ")){
            throw  new DontHaveData("Заполните все поля техники должно быть минимум 1");
        }
        HashMap<String,String> result = new HashMap<>();
        for (int i = 0; i < additionalProducts.length; i++) {
            result.put("additionalProducts"+i,additionalProducts[i].getText());
            result.put("additionalProductsCount"+i,additionalProductsCount[i].getText());
            result.put("additionalProductsFullPrice"+i,additionalProductsFullPrice[i].getText());
            result.put("additionalProductsDiscount" + i,additionalProductsDiscount[i].getText());
            result.put("additionalProductsWithDiscount"+i,additionalProductsWithDiscount[i].getText());
        }
        result.put("dateCreateUpSaleContract",dateCreateUpSaleContract.getText());
        result.put("allSumUpSaleInBYN",allSumUpSaleInBYN.getText());
        result.put("prepaymentUpSale",prepaymentUpSale.getText());
        result.put("payUpTo100percentUpSale",payUpTo100percentUpSale.getText());
        return result;
    }
    public void editUpSaleEditSumProductPriceAndDiscountAndCount(JTextField jTextField) {
        int i = Integer.parseInt(jTextField.getName().substring(jTextField.getName().length()-1));
        int jCount = Integer.parseInt(additionalProductsCount[i].getText());
        int jDiscount = Integer.parseInt(additionalProductsDiscount[i].getText());
        int jPrice =0;
        try {
            jPrice = Integer.parseInt(additionalProductsFullPrice[i].getText());
        }catch (Exception e){}
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
        jButtons[6].setName("orderUpSaleMaunfeld");
        jButtons[6].addActionListener(controller);
        jPanel.add(jButtons[6], gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridwidth = 1;
        jButtons[7] = new JButton("Скопировать Emar");
        jButtons[7].setName("orderUpSaleSink");
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
        String methodPayment = "";
        for (int i = 0; i < 3; i++) {
            if(!buttonsForDataForViber[i].isEnabled()){
                methodPayment = buttonsForDataForViber[i].getText();
                break;
            }
        }
        String result = "Договор " +dataClient.getNumberContract()+"/1 на сумму " + allSumUpSaleInBYN.getText()+
                "руб., " + "предоплата "  + prepaymentUpSale.getText() +"руб., " + methodPayment;
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(result),null);

        return result;
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
                payUpTo100percentUpSale.getText() +"руб., " + methodPayment;
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(result),null);
        return result;
    }

}


