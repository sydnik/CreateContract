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

public class ViewSupplementaryAgreementUpSale extends MainViewContract implements Display, DataForViber {
    public static final int ROW_ADDITIONAL_PRODUCT = 9;

    private JTextField additionalProductsName[];
    private JTextField additionalProductsCount[];
    private JTextField additionalProductsDiscount[];
    private JTextField additionalProductsFullPrice[];
    private JTextField additionalProductsWithDiscount[];
    private MyTextField allSumBYN;
    private MyTextField numberSupplementaryAgreementUpSale;
    private MyTextField prepayment;
    private MyTextField payUpTo100percent;
    private JFormattedTextField dateCreateSupplementaryAgreement;
    private MyButton[] buttonsForDataForViber;

    public ViewSupplementaryAgreementUpSale(JPanel staticJPanel, DataClient dataClient, MyController controller) {
        super(staticJPanel,dataClient,"Доп Upsale",controller);
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
        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.anchor = GridBagConstraints.CENTER;

        grid.gridy = row++;
        grid.gridx = 0;
        workingWindow.add(new JLabel("№"),grid);
        grid.gridx = 1;
        grid.gridwidth = 3;
        workingWindow.add(new JLabel(" Наименование товара"),grid);
        grid.gridx = 4;
        grid.gridwidth = 1;
        workingWindow.add(new JLabel("К"),grid);
        grid.gridx = 5;
        workingWindow.add(new JLabel("Цена"),grid);
        grid.gridx = 6;
        workingWindow.add(new JLabel("%"),grid);
        grid.gridx = 7;
        workingWindow.add(new JLabel("Всего"),grid);
        additionalProductsName = new JTextField[ROW_ADDITIONAL_PRODUCT];
        additionalProductsCount = new JTextField[ROW_ADDITIONAL_PRODUCT];
        additionalProductsDiscount = new JTextField[ROW_ADDITIONAL_PRODUCT];
        additionalProductsFullPrice = new JTextField[ROW_ADDITIONAL_PRODUCT];
        additionalProductsWithDiscount = new JTextField[ROW_ADDITIONAL_PRODUCT];
        int i =0;
        for (; i < ROW_ADDITIONAL_PRODUCT; i++) {
            additionalProductsName[i] = new JTextField();
            additionalProductsCount[i] = new JTextField();
            additionalProductsDiscount[i] = new JTextField();
            additionalProductsFullPrice[i] = new JTextField();
            additionalProductsWithDiscount[i] = new JTextField();

            grid.gridy = row+i;
            grid.gridx = 0;
            grid.ipadx = 5;
            grid.gridwidth = 1;
            workingWindow.add(new JLabel(String.valueOf(i+1)),grid);
            grid.gridx = 1;
            grid.ipadx = 310;
            grid.gridwidth = 3;
            additionalProductsName[i].setName("supplementaryAgreementAdditionalProducts"+i);
            workingWindow.add(additionalProductsName[i],grid);
            grid.gridx = 4;
            grid.ipadx = 10;
            grid.gridwidth = 1;
            additionalProductsCount[i].setText(String.valueOf(1));
            additionalProductsCount[i].setName("supplementaryAgreementAdditionalProductsCount"+i);
            additionalProductsCount[i].addKeyListener(controller);
            workingWindow.add(additionalProductsCount[i],grid);
            grid.gridx = 5;
            grid.ipadx = 50;
            additionalProductsDiscount[i].setText(String.valueOf(0));
            additionalProductsFullPrice[i].setName("supplementaryAgreementAdditionalProductsFullPrice"+i);
            additionalProductsFullPrice[i].addKeyListener(controller);
            workingWindow.add(additionalProductsFullPrice[i],grid);
            grid.gridx = 6;
            grid.ipadx = 15;
            additionalProductsDiscount[i].setName("supplementaryAgreementAdditionalProductsDiscount"+i);
            additionalProductsDiscount[i].addKeyListener(controller);
            workingWindow.add(additionalProductsDiscount[i],grid);
            grid.gridx = 7;
            grid.ipadx = 50;
            additionalProductsWithDiscount[i].setName("supplementaryAgreementAdditionalProductsWithDiscount"+i);
            additionalProductsWithDiscount[i].addKeyListener(controller);
            workingWindow.add(additionalProductsWithDiscount[i],grid);
            try {
                additionalProductsName[i].setText(dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProducts()[i].getName());
                additionalProductsCount[i].setText(dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProducts()[i].getCount());
                additionalProductsDiscount[i].setText(dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProducts()[i].getDiscount());
                additionalProductsFullPrice[i].setText(dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProducts()[i].getFullPrice());
                additionalProductsWithDiscount[i].setText(dataClient.getSupplementaryAgreementUpSaleContract().getListAdditionalProducts()[i].getPriceWithDiscount());

            } catch (Exception e){

            }
        }
        row = row +i;
        grid.gridy = ++row;
        grid.gridx = 0;
        grid.gridwidth = 4;
        grid.ipadx = 0;
        workingWindow.add(new JLabel("Сумма договора"),grid);
        grid.gridx = 5;
        grid.gridwidth = 3;
        allSumBYN = new MyTextField(ValueTextField.SUM_UP_SALE_IN_BYN_SUPPLEMENTARY_AGREEMENT,
                String.valueOf(dataClient.getSupplementaryAgreementUpSaleContract().getAllSumBYN()),false,controller);
        workingWindow.add(allSumBYN,grid);

        grid.gridx = 4;
        grid.gridwidth = 1;
        workingWindow.add(new MyCheckBox(allSumBYN,controller),grid);

        grid.gridy = ++row;
        grid.gridx = 0;
        grid.gridwidth = 4;
        workingWindow.add(new JLabel("Предоплата:"),grid);
        grid.gridx = 5;
        grid.gridwidth = 3;
        prepayment = new MyTextField(ValueTextField.PREPAYMENT_UP_SALE_SUPPLEMENTARY_AGREEMENT,
                String.valueOf(dataClient.getSupplementaryAgreementUpSaleContract().getPrepayment()),false,controller);
        workingWindow.add(prepayment,grid);

        grid.gridx = 4;
        grid.gridwidth =1;
        workingWindow.add(new MyCheckBox(prepayment,controller),grid);

        grid.gridy = ++row;
        grid.gridx = 0;
        grid.gridwidth = 4;
        workingWindow.add(new JLabel("Оплата до 100%"),grid);
        grid.gridx = 5;
        grid.gridwidth = 3;
        payUpTo100percent = new MyTextField(ValueTextField.PAY_UP_TO_100_PERCENT_UP_SALE_SUPPLEMENTARY_AGREEMENT,
                String.valueOf(dataClient.getSupplementaryAgreementUpSaleContract().getPayUpTo100percent()),false,controller);
        workingWindow.add(payUpTo100percent,grid);

        grid.gridx = 4;
        grid.gridwidth = 1;
        workingWindow.add(new MyCheckBox(payUpTo100percent,controller),grid);

        grid.gridy = ++row;
        grid.gridx = 0;
        grid.gridwidth = 4;
        workingWindow.add(new JLabel("Дата UpSale"),grid);
        grid.gridx = 4;
        grid.gridwidth = 1;
        JCheckBox forBeauty  = new JCheckBox();//For beauty
        forBeauty.setEnabled(false);
        workingWindow.add(forBeauty,grid);
        grid.gridx = 5;
        grid.gridwidth = 3;
        JTextField dateCreateUpSaleContract = new JTextField(dataClient.getUpSaleContract().getDateCreate());
        dateCreateUpSaleContract.setEnabled(false);
        workingWindow.add(dateCreateUpSaleContract,grid);

        grid.gridy = ++row;
        grid.gridx = 0;
        grid.gridwidth = 4;
        workingWindow.add(new JLabel("Дата доп соглаш"),grid);
        grid.gridx = 5;
        grid.gridwidth = 3;
        dateCreateSupplementaryAgreement = new JFormattedTextField(dateMask);
        dateCreateSupplementaryAgreement.setText(dataClient.getSupplementaryAgreementUpSaleContract().getDateCreate());
        dateCreateSupplementaryAgreement.setEnabled(false);
        workingWindow.add(dateCreateSupplementaryAgreement,grid);
        grid.gridx = 4;
        grid.gridwidth = 1;
        workingWindow.add(new MyCheckBox(dateCreateSupplementaryAgreement,controller),grid);

        grid.gridy = ++row;
        grid.gridx = 0;
        grid.gridwidth = 4;
        workingWindow.add(new JLabel("Номер"),grid);
        grid.gridx = 5;
        grid.gridwidth = 3;
        numberSupplementaryAgreementUpSale = new MyTextField(ValueTextField.NUMBER_SUPPLEMENTARY_AGREEMENT_UP_SALE,
                String.valueOf(dataClient.getSupplementaryAgreementUpSaleContract().getNumber()),false,controller);
        workingWindow.add(numberSupplementaryAgreementUpSale,grid);
        grid.gridx = 4;
        grid.gridwidth = 1;
        workingWindow.add(new MyCheckBox(numberSupplementaryAgreementUpSale,controller),grid);

        grid.gridy = ++row;
        grid.gridx = 0;
        grid.gridwidth = 4;
        grid.ipadx = 0;
        workingWindow.add(new MyButton(ValueButton.SAVE_DATA_SUPPLEMENTARY_AGREEMENT_UP_SALE_CONTACT,controller),grid);
        grid.gridx = 4;
        grid.gridwidth = 4;
        workingWindow.add(new MyButton(ValueButton.PRINT_SUPPLEMENTARY_AGREEMENT_UP_SALE_CONTRACT,controller),grid);

        grid.gridy = ++row;
        grid.gridx = 0;
        grid.gridwidth = 4;
        workingWindow.add(new MyButton(ValueButton.OPEN_DIRECTORY_WITH_FILE,controller),grid);
        grid.gridx = 4;
        grid.gridwidth = 4;
        workingWindow.add(new MyButton(ValueButton.OPEN_FILE_SUPPLEMENTARY_AGREEMENT_UP_SALE_CONTRACT,controller),grid);

        grid.gridy = ++row;
        grid.gridx = 0;
        grid.gridwidth = 8;
        workingWindow.add(new MyButton(ValueButton.VIEW_DATA_FOR_VIBER,controller),grid);

        if(allSumBYN.getText().equals("")|| allSumBYN.getText().equals("0")){
            fillData();
        }
        endPage();
    }

    public void editSupplementaryAgreementUpSaleSumProductPriceAndDiscountAndCount(JTextField jTextField){
        int i = Integer.parseInt(jTextField.getName().substring(jTextField.getName().length()-1));
        int jCount = Integer.parseInt(additionalProductsCount[i].getText());
        int jDiscount = Integer.parseInt(additionalProductsDiscount[i].getText());
        int jPrice = 0;
        try {
            jPrice = Integer.parseInt(additionalProductsFullPrice[i].getText());
        }catch (Exception e){

        }
        additionalProductsWithDiscount[i].setText(String.valueOf(((int) Math.round((double) jPrice*((double) (100-jDiscount)/100)))*jCount));
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
        allSumBYN.setText(String.valueOf(allSum));
        editPrepaymentOrAllSumSupplementaryAgreementUpSale();
    }
    public void editPrepaymentOrAllSumSupplementaryAgreementUpSale (){
        int allSum = Integer.parseInt(allSumBYN.getText());
        int prepayment = Integer.parseInt(this.prepayment.getText());
        payUpTo100percent.setText(String.valueOf(allSum-prepayment));
    }
    @Override
    public HashMap<String, String> getData() throws DontHaveData {
        if(allSumBYN.equals("")|| allSumBYN.equals("0")||
                prepayment.equals("")|| payUpTo100percent.equals("")||
                dateCreateSupplementaryAgreement.equals("  .  .    ")||numberSupplementaryAgreementUpSale.equals("")){
            throw  new DontHaveData("Заполните все поля техники должно быть минимум 1");
        }
        HashMap<String,String> result = new HashMap<>();
        for (int i = 0; i < additionalProductsName.length; i++) {
            result.put("supplementaryAgreementAdditionalProducts"+i, additionalProductsName[i].getText());
            result.put("supplementaryAgreementAdditionalProductsCount"+i, additionalProductsCount[i].getText());
            result.put("supplementaryAgreementAdditionalProductsFullPrice"+i, additionalProductsFullPrice[i].getText());
            result.put("supplementaryAgreementAdditionalProductsDiscount" + i, additionalProductsDiscount[i].getText());
            result.put("supplementaryAgreementAdditionalProductsWithDiscount"+i, additionalProductsWithDiscount[i].getText());
        }

        result.put("dateCreateSupplementaryAgreementUpSaleContract", dateCreateSupplementaryAgreement.getText());
        result.put("numberSupplementaryAgreementUpSale",numberSupplementaryAgreementUpSale.getText());
        result.put("allSumUpSaleInBYNSupplementaryAgreement", allSumBYN.getText());
        result.put("prepaymentUpSaleSupplementaryAgreement", prepayment.getText());
        result.put("payUpTo100percentUpSaleSupplementaryAgreement", payUpTo100percent.getText());
        return result;
    }

    public void displayDataForViber(JPanel staticJPanel, MyController controller, MyButton[] buttons){
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
        buttons[0] = new MyButton(ValueButton.SELECT_PAYMENT_CASH,controller);
        jPanel.add(buttons[0],gridBagConstraints);
        gridBagConstraints.gridx = 1;
        buttons[1] = new MyButton(ValueButton.SELECT_PAYMENT_CARD,controller);
        jPanel.add(buttons[1],gridBagConstraints);
        gridBagConstraints.gridx = 2;
        buttons[2] = new MyButton(ValueButton.SELECT_PAYMENT_CARD,controller);
        jPanel.add(buttons[2],gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 155;
        buttons[3] = new MyButton(ValueButton.COPY_PREPAYMENT,controller);
        buttons[3].setEnabled(false);
        buttons[3].setVisible(false);
        jPanel.add(buttons[3],gridBagConstraints);

        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        buttons[4] = new MyButton(ValueButton.COPY_PAY_TO_50_PERCENT,controller);
        buttons[4].setEnabled(false);
        buttons[4].setVisible(false);
        jPanel.add(buttons[4],gridBagConstraints);

        gridBagConstraints.gridy = 3;
        buttons[5] = new MyButton(ValueButton.COPY_PAY_TO_100_PERCENT,controller);
        buttons[5].setEnabled(false);
        jPanel.add(buttons[5],gridBagConstraints);

        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 0;
        buttons[6] = new MyButton(ValueButton.ORDER_SUPPLEMENTARY_AGREEMENT_UP_SALE_MAUNFELD,controller);
        jPanel.add(buttons[6], gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridwidth = 1;
        buttons[7] = new MyButton(ValueButton.ORDER_SUPPLEMENTARY_AGREEMENT_UP_SALE_SINK,controller);
        jPanel.add(buttons[7], gridBagConstraints);

        dialog.setVisible(true);
    }

    @Override
    public void displayDataForViber() {
        buttonsForDataForViber = new MyButton[8];
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
                payUpTo100percent.getText() +"руб., " + methodPayment;
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(result),null);
        return result;
    }

    private void fillData(){//Если данных нет берем все из UpSale
        for (int i = 0; i < additionalProductsName.length; i++) {
            try {
                additionalProductsName[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getName());
                additionalProductsCount[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getCount());
                additionalProductsDiscount[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getDiscount());
                additionalProductsFullPrice[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getFullPrice());
                additionalProductsWithDiscount[i].setText(dataClient.getUpSaleContract().getListAdditionalProducts()[i].getPriceWithDiscount());
            } catch (Exception e) {

            }
        }
        allSumBYN.setText(String.valueOf(dataClient.getUpSaleContract().getAllSumBYN()));
        prepayment.setText(String.valueOf(dataClient.getUpSaleContract().getPrepayment()));
        payUpTo100percent.setText(String.valueOf(dataClient.getUpSaleContract().getPayUpTo100percent()));
        dateCreateSupplementaryAgreement.setText(String.valueOf(dataClient.getSupplementaryAgreementUpSaleContract().getDateCreate()));

    }

}
