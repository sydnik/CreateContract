package org.sydnik.createContract.view;

import org.sydnik.createContract.MyController;
import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.exception.DontHaveData;
import org.sydnik.createContract.myComponent.MyButton;
import org.sydnik.createContract.myComponent.ValueButton;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ViewMainClient implements Display {
    private JPanel staticJPanel;
    private DataClient dataClient;
    private MyController controller;

    public ViewMainClient(JPanel staticJPanel, DataClient dataClient, MyController controller) {
        this.staticJPanel = staticJPanel;
        this.dataClient = dataClient;
        this.controller = controller;
    }

    @Override
    public void display() {
        int row =0;
        staticJPanel.removeAll();
        staticJPanel.setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        grid.weightx = 0.1;
        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.anchor = GridBagConstraints.WEST;


        grid.gridy = row++;
        grid.ipadx = 50;
        grid.gridx = 0;
        JLabel j1 = new JLabel("Клиент: ");
        j1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j1,grid);
        grid.gridx = 1;
        grid.ipadx = 40;
        grid.gridwidth = 2;
        JLabel j2 = new JLabel(dataClient.getFullNameClient());
        j2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j2,grid);

        grid.gridy = row++;
        grid.gridx = 0;
        grid.ipadx = 50;
        grid.gridwidth = 1;
        JLabel j4 = new JLabel("Номер договора: ");
        j4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j4,grid);
        grid.gridx = 1;
        grid.ipadx = 40;
        grid.gridwidth = 2;
        JLabel j3 = new JLabel(dataClient.getNumberContract());
        j3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j3,grid);

        grid.gridy = row++;
        staticJPanel.add(new JLabel(" "),grid);
        grid.gridy = row++;
        staticJPanel.add(new JLabel(" "),grid);


        grid.gridy = row++;
        grid.gridx = 0;
        grid.ipadx = 50;
        grid.gridwidth = 1;
        staticJPanel.add(new JLabel("Базовый договор"),grid);
        grid.gridx = 1;
        grid.ipadx = 40;
        staticJPanel.add(new JLabel("Сумма: " +dataClient.getBasicContract().getAllSumInEUR()),grid);
        grid.gridx = 2;
        grid.ipadx = 70;
        staticJPanel.add(new MyButton(ValueButton.VIEW_BASIC_CONTRACT,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        staticJPanel.add(new JLabel("Договор UpSale"),grid);
        grid.gridx = 1;
        staticJPanel.add(new JLabel("Сумма: " +dataClient.getUpSaleContract().getAllSumBYN()),grid);
        grid.gridx = 2;
        staticJPanel.add(new MyButton(ValueButton.VIEW_UP_SALE_CONTRACT,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        staticJPanel.add(new JLabel("Доп соглашение БД"),grid);
        grid.gridx = 1;
        staticJPanel.add(new JLabel("Новая сумма: "+ dataClient.getSupplementaryAgreementBasicContract().getAllSumInEUR()),
                grid);
        grid.gridx = 2;
        staticJPanel.add(new MyButton(ValueButton.VIEW_SUPPLEMENTARY_AGREEMENT_BASIC_CONTRACT,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        staticJPanel.add(new JLabel("Доп соглашение UpSale"),grid);
        grid.gridx = 1;
        staticJPanel.add(new JLabel("Новая сумма: "+ dataClient.getSupplementaryAgreementUpSaleContract().getAllSumBYN()),
                grid);
        grid.gridx = 2;
        staticJPanel.add(new MyButton(ValueButton.VIEW_SUPPLEMENTARY_AGREEMENT_UP_SALE,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        staticJPanel.add(new JLabel("Счет-фактура"),grid);
        grid.gridx = 1;
        staticJPanel.add(new JLabel("Сумма: "+dataClient.getInvoiceDocument().getPriceBYN()),grid);
        grid.gridx = 2;
        staticJPanel.add(new MyButton(ValueButton.VIEW_INVOICE_DOCUMENT,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        staticJPanel.add(new JLabel("Данные о клиенте"),grid);
        grid.gridx = 2;
        staticJPanel.add(new MyButton(ValueButton.VIEW_DATA_ABOUT_CLIENT,controller),grid);

        grid.gridy = row++;
        grid.gridx = 0;
        grid.gridwidth = 2;
        staticJPanel.add(new JLabel("Добавить файлы для распила"),grid);
        grid.gridx = 2;
        grid.gridwidth = 1;
        staticJPanel.add(new MyButton(ValueButton.VIEW_CREATE_FILE_FOR_CUTTING,controller),grid);

        grid.gridy = row++;
        grid.ipady = 210;
        grid.gridx = 1;
        staticJPanel.add(new JLabel(" "),grid);

        grid.gridy = row++;
        grid.ipady = 0;
        grid.gridx = 0;
        grid.gridwidth = 3;
        MyButton buttonSelectClient = new MyButton(ValueButton.VIEW_SELECT_CLIENT,controller);
        buttonSelectClient.setText("Вернутся к выбору клиента");
        staticJPanel.add(buttonSelectClient,grid);

        grid.gridy     = row++;
        staticJPanel.add(new MyButton(ValueButton.VIEW_MAIN_PAGE,controller), grid);

        staticJPanel.revalidate();
        staticJPanel.repaint();
    }

    @Override
    public HashMap<String, String> getData() throws DontHaveData {
        return null;
    }
}
