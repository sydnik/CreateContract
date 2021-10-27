package org.sydnik.createContract.view;

import org.sydnik.createContract.MyController;
import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.exception.DontHaveData;
import org.sydnik.createContract.myComponent.MyButton;
import org.sydnik.createContract.myComponent.ValueButton;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.HashMap;

public class ViewDataClient implements Display {
    private JPanel staticJPanel;
    private DataClient dataClient;
    private MyController controller;
    private JFormattedTextField numberContract;
    private JTextField strangeName;
    private JTextField fullNameClient;
    private JFormattedTextField numberPassport;
    private JTextField issuedByPassport;
    private JFormattedTextField whenIssued;
    private JFormattedTextField identificationNumber;
    private JTextField addressRegistration;
    private JTextField addressDelivery;
    private JFormattedTextField numberPhoneClient;
    private MyButton buttonSave;
    private MyButton buttonBackSelectClient;

    public ViewDataClient(JPanel staticJPanel, DataClient dataClient, MyController controller) {
        this.staticJPanel = staticJPanel;
        this.dataClient = dataClient;
        this.controller = controller;
    }

    @Override
    public void display() {
        staticJPanel.removeAll();
        staticJPanel.setLayout(new GridLayout(23,1));
        MaskFormatter
                identificationNumberFormatter = null,
                whenIssuedFormatter = null,
                numberPassportFormatter = null,
                phoneFormatter = null,
                numberContractFormatter = null;
        try {
            numberContractFormatter = new MaskFormatter("UU#-######-##*");
            phoneFormatter = new MaskFormatter("+375(##) ### ####");
            numberPassportFormatter = new MaskFormatter("UU#######");
            whenIssuedFormatter = new MaskFormatter("##.##.####");
            identificationNumberFormatter = new MaskFormatter(("#######U###UU#"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        staticJPanel.add(new JLabel("Номер договора:"));
        numberContract = new JFormattedTextField(numberContractFormatter);
        numberContract.setName("numberContract");
        staticJPanel.add(numberContract);

        staticJPanel.add(new JLabel("Странное название"));
        strangeName = new JTextField();
        strangeName.setName("strangeName");
        staticJPanel.add(strangeName);

        staticJPanel.add(new JLabel("ФИО клиенто полностью:"));
        fullNameClient = new JTextField();
        fullNameClient.setName("fullNameClient");
        staticJPanel.add(fullNameClient);

        staticJPanel.add(new JLabel("Номер паспорта:"));
        numberPassport = new JFormattedTextField(numberPassportFormatter);
        numberPassport.setName("numberPassport");
        staticJPanel.add(numberPassport);

        staticJPanel.add(new JLabel("Кем выдан:"));
        issuedByPassport = new JTextField();
        issuedByPassport.setName("issuedByPassport");
        staticJPanel.add(issuedByPassport);

        staticJPanel.add(new JLabel("Когда выдан:"));
        whenIssued = new JFormattedTextField(whenIssuedFormatter);
        whenIssued.setName("whenIssued");
        staticJPanel.add(whenIssued);

        staticJPanel.add(new JLabel("Идентификационный номер:"));
        identificationNumber = new JFormattedTextField(identificationNumberFormatter);
        identificationNumber.setName("identificationNumber");
        staticJPanel.add(identificationNumber);

        staticJPanel.add(new JLabel("Адрес регистрации:"));
        addressRegistration = new JTextField();
        addressRegistration.setName("addressRegistration");
        staticJPanel.add(addressRegistration);

        staticJPanel.add(new JLabel("Адрес доставки:"));
        addressDelivery = new JTextField();
        addressDelivery.setName("addressDelivery");
        staticJPanel.add(addressDelivery);

        staticJPanel.add(new JLabel("Мобильный телефон:"));
        numberPhoneClient = new JFormattedTextField(phoneFormatter);
        numberPhoneClient.setName("numberPhoneClient");
        staticJPanel.add(numberPhoneClient);

        buttonSave = new MyButton(ValueButton.SAVE_NEW_DATA_CLIENT,controller);
        staticJPanel.add(buttonSave);

        buttonBackSelectClient = new MyButton(ValueButton.VIEW_BACK_SELECT_CLIENT,controller);
        staticJPanel.add(buttonBackSelectClient);

        staticJPanel.add(new MyButton(ValueButton.VIEW_MAIN_PAGE,controller));

        fillDataAboutClient();

        staticJPanel.revalidate();
        staticJPanel.repaint();
    }
    @Override
    public HashMap<String, String> getData() throws DontHaveData {
        if(numberContract.getText().equals("   -      -   ")||strangeName.getText().equals("")||
                fullNameClient.getText().equals("")||numberPassport.getText().trim().equals("")||
                issuedByPassport.getText().equals("")||whenIssued.getText().equals("  .  .    ")||
                identificationNumber.getText().trim().equals("")||addressRegistration.getText().equals("")||
                addressDelivery.getText().equals("")||numberPhoneClient.getText().equals("+375(  )         ")){
            throw new DontHaveData("Заполните все поля");
        }
        HashMap<String,String> result = new HashMap<>();
        result.put("numberContract", numberContract.getText());
        result.put("strangeName",  strangeName.getText());
        result.put("fullNameClient", fullNameClient.getText());
        result.put("numberPassport", numberPassport.getText());
        result.put("issuedByPassport", issuedByPassport.getText());
        result.put("whenIssued", whenIssued.getText());
        result.put("identificationNumber", identificationNumber.getText());
        result.put("addressRegistration", addressRegistration.getText());
        result.put("addressDelivery", addressDelivery.getText());
        result.put("numberPhoneClient", numberPhoneClient.getText());
        return result;
    }

    private void fillDataAboutClient (){
        if(dataClient==null){
            staticJPanel.remove(buttonBackSelectClient);
            staticJPanel.setLayout(new GridLayout(22,1));
            return;
        }
        numberContract.setText(dataClient.getNumberContract());
        numberContract.setEnabled(false);
        strangeName.setText(dataClient.getStrangeName());
        strangeName.setEnabled(false);
        fullNameClient.setText(dataClient.getFullNameClient());
        numberPassport.setText(dataClient.getNumberPassport());
        issuedByPassport.setText(dataClient.getIssuedByPassport());
        whenIssued.setText(dataClient.getWhenIssued());
        identificationNumber.setText(dataClient.getIdentificationNumber());
        addressRegistration.setText(dataClient.getAddressRegistration());
        addressDelivery.setText(dataClient.getAddressDelivery());
        numberPhoneClient.setText(dataClient.getNumberPhoneClient());
        buttonSave = new MyButton(ValueButton.SAVE_DATA_ABOUT_CLIENT,controller);
    }
}
