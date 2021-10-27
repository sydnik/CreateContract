package org.sydnik.createContract.view;


import org.sydnik.createContract.MyController;
import org.sydnik.createContract.data.DataClient;
import org.sydnik.createContract.data.SalesManager;
import org.sydnik.createContract.myComponent.MyButton;
import org.sydnik.createContract.myComponent.ValueButton;
import org.sydnik.createContract.view.document.*;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    public static final int HEIGHT_WINDOW_APPS = 600;
    public static final int WIDTH_WINDOW_APPS = 500;

    private MyController controller;
    private JPanel staticJPanel;


    public MainView() {
        super("CreateContract");
        setSize(WIDTH_WINDOW_APPS, HEIGHT_WINDOW_APPS);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);//окно по центру
        setVisible(true);
        staticJPanel = new JPanel();
        staticJPanel.setLayout(new GridLayout(20,1));
        this.add(staticJPanel);
        UIManager.put("Label.font", new Font("Aria", Font.BOLD, 15));

    }

    public void viewMainPage(SalesManager salesManager){
        staticJPanel.removeAll();
        staticJPanel.setLayout(new GridLayout(20,1));
        staticJPanel.add(new JLabel("Добрый день "+salesManager.getMiniName()));
        staticJPanel.add(new MyButton(ValueButton.VIEW_NEW_CLIENT,controller));
        staticJPanel.add(new MyButton(ValueButton.VIEW_SELECT_CLIENT,controller));
        staticJPanel.add(new MyButton(ValueButton.VIEW_SETTINGS_MANAGER,controller));
        staticJPanel.revalidate();
        staticJPanel.repaint();
    }

    public void displayMainClient(DataClient dataClient){
        ViewMainClient viewMainClient = new ViewMainClient(staticJPanel,dataClient,controller);
        viewMainClient.display();
    }
    public void displayInputRate() {
        ViewRate viewRate = new ViewRate(staticJPanel,controller);
        viewRate.display();
        controller.setCurrentView(viewRate);
    }
    public void displaySettingsManager(SalesManager salesManager){
        ViewSetting viewSetting = new ViewSetting(controller,staticJPanel,salesManager);
        viewSetting.display();
        controller.setCurrentView(viewSetting);
    }
    public void displayListClients(String[] list) {
        ViewListClients view = new ViewListClients(staticJPanel,controller,list);
        view.display();
        controller.setCurrentView(view);
    }
    public void displayNewClient(){
        ViewDataClient view = new ViewDataClient(staticJPanel,null,controller);
        view.display();
        controller.setCurrentView(view);
    }
    public void displayEditDataAboutClient(DataClient dataClient){
        ViewDataClient view = new ViewDataClient(staticJPanel,dataClient,controller);
        view.display();
        controller.setCurrentView(view);
    }
    public void displayBasicContract(DataClient dataClient, double currencyValue){
        ViewBasicContract vid = new ViewBasicContract(staticJPanel,dataClient,currencyValue,controller);
        vid.display();
        controller.setCurrentView(vid);

    }
    public void displaySupplementaryAgreementBasicContract(DataClient dataClient, double currencyValue){
        ViewSupplementaryAgreementBasicContract vid = new ViewSupplementaryAgreementBasicContract(
                staticJPanel,dataClient,currencyValue,controller);
        vid.display();
        controller.setCurrentView(vid);

    }
    public void displayUpSaleContract(DataClient dataClient){
        ViewUpSaleContract vid = new ViewUpSaleContract(staticJPanel,dataClient,controller);
        vid.display();
        controller.setCurrentView(vid);

    }
    public void displaySupplementaryAgreementUpSale(DataClient dataClient){
        ViewSupplementaryAgreementUpSale vid = new ViewSupplementaryAgreementUpSale(staticJPanel,dataClient,controller);
        vid.display();
        controller.setCurrentView(vid);

    }
    public void displayInvoiceDocument(DataClient dataClient, double currencyValue){
        ViewInvoiceDocument vid = new ViewInvoiceDocument(staticJPanel,dataClient,currencyValue,controller);
        vid.display();
        controller.setCurrentView(vid);

    }
    public void displayCreateFileForCutting(DataClient dataClient){
        ViewCreateFileForCutting viewCreateFileForCutting = new ViewCreateFileForCutting(staticJPanel,dataClient,controller);
        viewCreateFileForCutting.display();
        controller.setCurrentView(viewCreateFileForCutting);
    }

    //Общие методы которые работают везде
    public void onlyNumber(JTextField jTextField){
        jTextField.setText(jTextField.getText().replaceAll("[^0-9]",""));

    }
    public void onlyDoubleNumber(JTextField jTextField,int howManySymbolAfterDot){
        if(!jTextField.getText().matches("[\\d]{0,6}[\\.]?[\\d]{0,"+howManySymbolAfterDot+"}")){
            String result = "";
            String[] forTest = null;
            result = jTextField.getText().replace(",",".");
            forTest = result.split("\\.");
            result = "";
            for (int j = 0; j < forTest.length ; j++) {
                forTest[j] = forTest[j].replaceAll("[^\\d]","");
                result = result + forTest[j];
                if(j==0){
                    result = result+".";
                }
                if (j==1){
                    break;
                }
            }
            try {
                result=result.substring(0,result.indexOf(".")+1+howManySymbolAfterDot);
            } catch (Exception e){

            }
            jTextField.setText(result);
        }
    }
    public void writeJustMessage(String message,int messageType) {
        JOptionPane.showMessageDialog(this, message, "Сообщение", messageType);
    }

    public void setController(MyController controller) {
        this.controller = controller;
    }
}
