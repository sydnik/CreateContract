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


public class ViewUpSaleContract extends MainViewContract implements Display, DataForViber {
    public static final int ROW_ADDITIONAL_PRODUCT = 9;
    private JTextField additionalProductsWithDiscount[];
    private JTextField additionalProducts[];
    private JTextField additionalProductsCount[];
    private JTextField additionalProductsDiscount[];
    private JTextField additionalProductsFullPrice[];
    private MyTextField allSumBYN;
    private MyTextField prepayment;
    private MyTextField payUpTo100percent;
    private JFormattedTextField dateCreateUpSaleContract;
    private MyButton[] buttonsForDataForViber;

    public ViewUpSaleContract(JPanel staticJPanel, DataClient dataClient, MyController controller) {
        super(staticJPanel,dataClient,"UpSale",controller);
    }

    @Override
    public void display() {
        int row =0;
        startPage();
        MaskFormatter
                dateCreateContractMask = null;
        try {
            dateCreateContractMask = new MaskFormatter("##.##.####");
        } catch (Exception e) {
        }
        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.anchor = GridBagConstraints.WEST;

        grid.gridy = row++;
        grid.gridx = 0;
        workingWindow.add(new JLabel("№"), grid);
        grid.gridx = 1;
        grid.gridwidth = 3;
        workingWindow.add(new JLabel(" Наименование товара"), grid);
        grid.gridx = 4;
        grid.gridwidth = 1;
        workingWindow.add(new JLabel("К"), grid);
        grid.gridx = 5;
        workingWindow.add(new JLabel("Цена"), grid);
        grid.gridx = 6;
        workingWindow.add(new JLabel("%"), grid);
        grid.gridx = 7;
        workingWindow.add(new JLabel("Всего"), grid);
        additionalProducts = new JTextField[ROW_ADDITIONAL_PRODUCT];
        additionalProductsCount = new JTextField[ROW_ADDITIONAL_PRODUCT];
        additionalProductsDiscount = new JTextField[ROW_ADDITIONAL_PRODUCT];
        additionalProductsFullPrice = new JTextField[ROW_ADDITIONAL_PRODUCT];
        additionalProductsWithDiscount = new JTextField[ROW_ADDITIONAL_PRODUCT];
        int i =0;
        row++;
        for (; i < ROW_ADDITIONAL_PRODUCT; i++) {
            additionalProducts[i] = new JTextField();
            additionalProductsCount[i] = new JTextField();
            additionalProductsDiscount[i] = new JTextField();
            additionalProductsFullPrice[i] = new JTextField();
            additionalProductsWithDiscount[i] = new JTextField();
            grid.gridy = row + i;
            grid.gridx = 0;
            grid.ipadx = 5;
            grid.gridwidth = 1;
            workingWindow.add(new JLabel(String.valueOf(i + 1)), grid);
            grid.gridx = 1;
            grid.ipadx = 310;
            grid.gridwidth = 3;
            additionalProducts[i].setName("additionalProducts" + i);
            workingWindow.add(additionalProducts[i], grid);
            grid.gridx = 4;
            grid.ipadx = 10;
            grid.gridwidth = 1;
            additionalProductsCount[i].setText(String.valueOf(1));
            additionalProductsCount[i].setName("additionalProductsCount" + i);
            additionalProductsCount[i].addKeyListener(controller);
            workingWindow.add(additionalProductsCount[i], grid);
            grid.gridx = 5;
            grid.ipadx = 50;
            additionalProductsDiscount[i].setText(String.valueOf(0));
            additionalProductsFullPrice[i].setName("additionalProductsFullPrice" + i);
            additionalProductsFullPrice[i].addKeyListener(controller);
            workingWindow.add(additionalProductsFullPrice[i], grid);
            grid.gridx = 6;
            grid.ipadx = 15;
            additionalProductsDiscount[i].setName("additionalProductsDiscount" + i);
            additionalProductsDiscount[i].addKeyListener(controller);
            workingWindow.add(additionalProductsDiscount[i], grid);
            grid.gridx = 7;
            grid.ipadx = 50;
            additionalProductsWithDiscount[i].setName("additionalProductsWithDiscount" + i);
            additionalProductsWithDiscount[i].addKeyListener(controller);
            workingWindow.add(additionalProductsWithDiscount[i], grid);
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
        row = row +i;
        grid.gridy = ++row;
        grid.gridx = 0;
        grid.gridwidth = 4;
        grid.ipadx = 0;
        workingWindow.add(new JLabel("Сумма договора"), grid);


        grid.gridx = 5;
        grid.gridwidth = 3;
        allSumBYN = new MyTextField(ValueTextField.ALL_SUM_UP_SALE_IN_BYN,
                String.valueOf(dataClient.getUpSaleContract().getAllSumBYN()), false, controller);
        workingWindow.add(allSumBYN, grid);

        grid.gridx = 4;
        grid.gridwidth = 1;
        workingWindow.add(new MyCheckBox(allSumBYN,controller), grid);


        grid.gridy = ++row;
        grid.gridx = 0;
        grid.gridwidth = 4;
        grid.ipadx = 0;
        workingWindow.add(new JLabel("Предоплата:"), grid);
        grid.gridx = 5;
        grid.gridwidth = 3;
        prepayment = new MyTextField(ValueTextField.PREPAYMENT_UP_SALE,
                String.valueOf(dataClient.getUpSaleContract().getPrepayment()),false,controller);
        workingWindow.add(prepayment, grid);

        grid.gridx = 4;
        grid.gridwidth = 1;
        workingWindow.add(new MyCheckBox(prepayment,controller), grid);

        grid.gridy = ++row;
        grid.gridx = 0;
        grid.gridwidth = 4;
        workingWindow.add(new JLabel("Оплата до 100%"), grid);
        grid.gridx = 5;
        grid.gridwidth = 3;
        payUpTo100percent = new MyTextField(ValueTextField.PAY_UP_TO_100_PERCENT_UP_SALE,
                String.valueOf(dataClient.getUpSaleContract().getPayUpTo100percent()),false,controller);
        workingWindow.add(payUpTo100percent, grid);

        grid.gridx = 4;
        grid.gridwidth = 1;
        workingWindow.add(new MyCheckBox(payUpTo100percent,controller), grid);

        grid.gridy = ++row;
        grid.gridx = 0;
        grid.gridwidth = 4;
        workingWindow.add(new JLabel("Дата подписания"), grid);
        grid.gridx = 5;
        grid.gridwidth = 3;
        dateCreateUpSaleContract = new JFormattedTextField(dateCreateContractMask);
        dateCreateUpSaleContract.setText(String.valueOf(dataClient.getUpSaleContract().getDateCreate()));
        dateCreateUpSaleContract.setEnabled(false);
        workingWindow.add(dateCreateUpSaleContract, grid);

        grid.gridx = 4;
        grid.gridwidth = 1;
        workingWindow.add(new MyCheckBox(dateCreateUpSaleContract,controller), grid);

        grid.gridy = ++row;
        grid.gridx = 0;
        grid.gridwidth = 4;
        workingWindow.add(new MyButton(ValueButton.SAVE_DATA_UP_SALE_CONTACT,controller), grid);
        grid.gridx = 4;
        grid.gridwidth = 4;
        workingWindow.add(new MyButton(ValueButton.PRINT_UP_SALE_CONTRACT,controller), grid);

        grid.gridy = ++row;
        grid.gridx = 0;
        grid.gridwidth = 4;
        workingWindow.add(new MyButton(ValueButton.OPEN_DIRECTORY_WITH_FILE,controller), grid);
        grid.gridx = 4;
        grid.gridwidth = 4;
        workingWindow.add(new MyButton(ValueButton.OPEN_FILE_UP_SALE_CONTRACT,controller), grid);

        grid.gridy = ++row;
        grid.gridx = 0;
        grid.gridwidth = 8;
        workingWindow.add(new MyButton(ValueButton.VIEW_DATA_FOR_VIBER,controller),grid);

        super.endPage();
    }
    @Override
    public HashMap<String, String> getData() throws DontHaveData {
        if(allSumBYN.equals("")|| allSumBYN.equals("0")||
                prepayment.equals("")|| payUpTo100percent.equals("")||
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
        result.put("allSumUpSaleInBYN", allSumBYN.getText());
        result.put("prepaymentUpSale", prepayment.getText());
        result.put("payUpTo100percentUpSale", payUpTo100percent.getText());
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
        additionalProductsWithDiscount[i].setText(String.valueOf(
                ((int) Math.round((double) jPrice * ((double) (100 - jDiscount) / 100))) * jCount));
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
        editAllSumUpSaleInBYN();
    }
    public void editAllSumUpSaleInBYN(){
        int allSum = Integer.parseInt(allSumBYN.getText());
        int intPrepaymentUpSale = (int) Math.round(((double)allSum)/10);
        prepayment.setText(String.valueOf(intPrepaymentUpSale));
        staticJPanel.revalidate();
        staticJPanel.repaint();
        editPrepaymentUpSaleUpSale();
    }
    public void editPrepaymentUpSaleUpSale (){
        int prepayment = Integer.parseInt(this.prepayment.getText());
        int allSum = Integer.parseInt (allSumBYN.getText());
        payUpTo100percent.setText(String.valueOf(allSum-prepayment));
        staticJPanel.revalidate();
        staticJPanel.repaint();

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
        buttons[2] = new MyButton(ValueButton.SELECT_PAYMENT_NON_CASH,controller);
        jPanel.add(buttons[2],gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 155;
        buttons[3] = new MyButton(ValueButton.COPY_PREPAYMENT,controller);
        buttons[3].setEnabled(false);
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
        buttons[6] = new MyButton(ValueButton.ORDER_UP_SALE_MAUNFELD,controller);
        jPanel.add(buttons[6], gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridwidth = 1;
        buttons[7] = new MyButton(ValueButton.ORDER_UP_SALE_SINK,controller);
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
        String methodPayment = "";
        for (int i = 0; i < 3; i++) {
            if(!buttonsForDataForViber[i].isEnabled()){
                methodPayment = buttonsForDataForViber[i].getText();
                break;
            }
        }
        String result = "Договор " +dataClient.getNumberContract()+"/1 на сумму " + allSumBYN.getText()+
                "руб., " + "предоплата "  + prepayment.getText() +"руб., " + methodPayment;
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
                payUpTo100percent.getText() +"руб., " + methodPayment;
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(result),null);
        return result;
    }

}


