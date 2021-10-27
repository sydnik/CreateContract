package org.sydnik.createContract;

import org.sydnik.createContract.data.OrderUpSale;
import org.sydnik.createContract.exception.CantWriteDoc;
import org.sydnik.createContract.exception.DontHaveData;
import org.sydnik.createContract.exception.DontHaveFilePattern;
import org.sydnik.createContract.myComponent.EnumValue;
import org.sydnik.createContract.myComponent.MyButton;
import org.sydnik.createContract.myComponent.MyCheckBox;
import org.sydnik.createContract.view.*;
import org.sydnik.createContract.view.document.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MyController implements ActionListener, KeyListener, MouseListener {
    private Model model;
    private MainView view;
    private Display currentView;


    public MyController(Model model, MainView view) {
        this.model = model;
        this.view =view;
        view.setController(this);

    }

    public void setCurrentView(Display currentView){
        this.currentView = currentView;
    }

    public void displayMainPage(){
        view.viewMainPage(model.getSalesManager());
    }
    public void displayRateEUR() {
        view.displayInputRate();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        long s = System.currentTimeMillis();
        try {
            if(e.getSource() instanceof MyButton ){
                switch (((MyButton) e.getSource()).getEnumValue()) {
                    case VIEW_SETTINGS_MANAGER: {
                        view.displaySettingsManager(model.getSalesManager());
                        break;
                    }
                    case VIEW_MAIN_PAGE: {
                        view.viewMainPage(model.getSalesManager());
                        model.clearDataClient();
                        currentView = null;
                        break;
                    }
                    case VIEW_SELECT_CLIENT: {
                        view.displayListClients(model.listSelectClient());
                        model.clearDataClient();
                        break;
                    }
                    case VIEW_CLIENT: {
                        model.writeDataClient(currentView.getData());
                        view.displayMainClient(model.getDataClient());

                        break;
                    }
                    case VIEW_BASIC_CONTRACT: {
                        view.displayBasicContract(model.getDataClient(), model.getCurrencyValue());
                        break;
                    }
                    case VIEW_BACK_SELECT_CLIENT: {
                        view.displayMainClient(model.getDataClient());
                        currentView = null;
                        break;
                    }
                    case VIEW_DATA_ABOUT_CLIENT: {
                        view.displayEditDataAboutClient(model.getDataClient());
                        break;
                    }
                    case VIEW_NEW_CLIENT: {
                        model.clearDataClient();
                        view.displayNewClient();
                        break;
                    }
                    case VIEW_UP_SALE_CONTRACT: {
                        view.displayUpSaleContract(model.getDataClient());
                        break;
                    }
                    case VIEW_SUPPLEMENTARY_AGREEMENT_UP_SALE: {
                        view.displaySupplementaryAgreementUpSale(model.getDataClient());
                        break;
                    }
                    case VIEW_SELECT_PATH: {
                        ((ViewSetting) currentView).selectPathForSaveContract();
                        break;
                    }
                    case VIEW_INVOICE_DOCUMENT: {
                        view.displayInvoiceDocument(model.getDataClient(), model.getCurrencyValue());
                        break;
                    }
                    case VIEW_SUPPLEMENTARY_AGREEMENT_BASIC_CONTRACT: {
                        view.displaySupplementaryAgreementBasicContract(model.getDataClient(), model.getCurrencyValue());
                        break;
                    }
                    case VIEW_CREATE_FILE_FOR_CUTTING: {
                        view.displayCreateFileForCutting(model.getDataClient());
                        break;
                    }
                    case VIEW_ADD_NEW_DECOR: {
                        ((ViewSetting) currentView).addNewDecor();
                        break;
                    }
                    case VIEW_DATA_FOR_VIBER: {
                        ((DataForViber) currentView).displayDataForViber();
                        break;
                    }
                    case VIEW_SUM_IN_BYN_TODAY: {
                        view.writeJustMessage(((InfoForPayment) currentView).getInfoForPayment(), JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }

                    case SAVE_DECOR: {
                        model.addNewDecor(currentView);
                        view.writeJustMessage("Декор добавлен", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    case SAVE_SETTING_MANAGER: {
                        model.setSalesManager(currentView);
                        view.writeJustMessage("Настройки сохранены", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    case SAVE_NEW_DATA_CLIENT: {
                        model.createNewClient(currentView);
                        view.displayMainClient(model.getDataClient());
                        break;
                    }
                    case SAVE_DATA_ABOUT_CLIENT: {
                        model.saveDataAboutClient(currentView);
                        view.displayMainClient(model.getDataClient());
                        break;
                    }
                    case SAVE_DATA_BASE_CONTRACT_CLIENT: {
                        model.saveDataBaseContractClient(currentView);
                        model.createBaseContract();
                        view.writeJustMessage("Настройки сохранены", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    case SAVE_DATA_UP_SALE_CONTACT: {
                        model.saveDataUpSale(currentView);
                        view.writeJustMessage("Настройки сохранены", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    case SAVE_DATA_SUPPLEMENTARY_AGREEMENT_BASIC_CONTRACT: {
                        try {
                            model.saveDataSupplementaryAgreementBasicContract(currentView);
                            view.writeJustMessage("Настройки сохранены", JOptionPane.INFORMATION_MESSAGE);
                        } catch (CantWriteDoc | DontHaveFilePattern ex) {
                            view.writeJustMessage(ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    }
                    case SAVE_CURRENCY: {
                        model.setCurrency(currentView);
                        view.viewMainPage(model.getSalesManager());
                        break;
                    }
                    case SAVE_DATA_SUPPLEMENTARY_AGREEMENT_UP_SALE_CONTACT: {
                        model.saveDataSupplementaryAgreementUpSaleContract(currentView);
                        view.writeJustMessage("Настройки сохранены", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    case SAVE_DATA_INVOICE_DOCUMENT: {
                        model.saveDataInvoiceDocument(currentView);
                        view.writeJustMessage("Настройки сохранены", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    case SAVE_FILE_FOR_CUTTING: {
                        model.SaveFileForCutting(currentView);
                        break;
                    }

                    case OPEN_FILE_INVOICE_DOCUMENT: {
                        model.saveDataInvoiceDocument(currentView);
                        model.openDoc("openFileInvoiceDocument");
                        break;
                    }
                    case OPEN_FILE_SUPPLEMENTARY_AGREEMENT_UP_SALE_CONTRACT: {
                        model.saveDataSupplementaryAgreementUpSaleContract(currentView);
                        model.openDoc("openFileSupplementaryAgreementUpSaleContract");
                        break;
                    }
                    case OPEN_FILE_BASIC_CONTRACT: {
                        model.saveDataBaseContractClient(currentView);
                        model.openDoc("openFileBasicContract");
                        break;
                    }
                    case OPEN_FILE_SUPPLEMENTARY_AGREEMENT_BASIC_CONTRACT: {
                        model.saveDataSupplementaryAgreementBasicContract(currentView);
                        model.openDoc("openFileSupplementaryAgreementBasicContract");
                        break;
                    }
                    case OPEN_DIRECTORY_WITH_FILE: {
                        model.openDirectory();
                        break;
                    }
                    case OPEN_FILE_UP_SALE_CONTRACT: {
                        model.saveDataUpSale(currentView);
                        model.openDoc("openFileUpSaleContract");
                        break;
                    }

                    case PRINT_UP_SALE_CONTRACT: {
                        model.saveDataUpSale(currentView);
                        model.printDoc("printUpSaleContract");
                        break;
                    }
                    case PRINT_SUPPLEMENTARY_AGREEMENT_BASIC_CONTRACT: {

                        model.saveDataSupplementaryAgreementBasicContract(currentView);
                        model.printDoc("printSupplementaryAgreementBasicContract");
                        break;
                    }
                    case PRINT_BASE_CONTRACT: {
                        model.saveDataBaseContractClient(currentView);
                        model.printDoc("printBaseContract");
                        break;
                    }
                    case PRINT_SUPPLEMENTARY_AGREEMENT_UP_SALE_CONTRACT: {
                        model.saveDataSupplementaryAgreementUpSaleContract(currentView);
                        model.printDoc("printSupplementaryAgreementUpSaleContract");
                        break;
                    }
                    case PRINT_INVOICE_DOCUMENT: {
                        model.saveDataInvoiceDocument(currentView);
                        model.printDoc("printInvoiceDocument");
                        break;
                    }

                    case ORDER_UP_SALE_MAUNFELD: {
                        OrderUpSale orderUpSale = new OrderUpSale(model.getDataClient().getUpSaleContract().getListAdditionalProducts(),
                                model.getDataClient().getNumberContract());
                        orderUpSale.copyTextBuffer(OrderUpSale.MAUNFELD);
                        view.writeJustMessage("Техника скопирована,можете вставить в вайбер ;)", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    case ORDER_UP_SALE_SINK: {
                        OrderUpSale orderUpSale = new OrderUpSale(model.getDataClient().getUpSaleContract().getListAdditionalProducts(),
                                model.getDataClient().getNumberContract());
                        orderUpSale.copyTextBuffer(OrderUpSale.EMAR);
                        view.writeJustMessage("Техника скопирована,можете вставить в вайбер ;)", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    case ORDER_SUPPLEMENTARY_AGREEMENT_UP_SALE_SINK: {
                        OrderUpSale orderUpSale = new OrderUpSale(
                                model.getDataClient().getSupplementaryAgreementUpSaleContract().getListAdditionalProducts(),
                                model.getDataClient().getNumberContract());
                        orderUpSale.copyTextBuffer(OrderUpSale.EMAR);
                        view.writeJustMessage("Техника скопирована,можете вставить в вайбер ;)", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    case ORDER_SUPPLEMENTARY_AGREEMENT_UP_SALE_MAUNFELD: {
                        OrderUpSale orderUpSale = new OrderUpSale(
                                model.getDataClient().getSupplementaryAgreementUpSaleContract().getListAdditionalProducts(),
                                model.getDataClient().getNumberContract());
                        orderUpSale.copyTextBuffer(OrderUpSale.MAUNFELD);
                        view.writeJustMessage("Техника скопирована,можете вставить в вайбер ;)", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }

                    case SELECT_PAYMENT_CARD:
                    case SELECT_PAYMENT_CASH:
                    case SELECT_PAYMENT_NON_CASH: {
                        ((DataForViber) currentView).setSelectMethodPayment(((JButton) e.getSource()).getText());
                        break;
                    }

                    case COPY_PREPAYMENT: {
                        ((DataForViber) currentView).getDataFirstPayment();
                        view.writeJustMessage("Текст скопирован,можете вставить в вайбер ;)", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    case COPY_PAY_TO_50_PERCENT: {
                        ((DataForViber) currentView).getDataSecondPayment();
                        view.writeJustMessage("Текст скопирован,можете вставить в вайбер ;)", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    case COPY_PAY_TO_100_PERCENT: {
                        ((DataForViber) currentView).getDataThirdPayment();
                        view.writeJustMessage("Текст скопирован,можете вставить в вайбер ;)", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }

                    case SEARCH_CLIENT_BUTTON: {
                        ((ViewListClients)currentView).filter();
                        break;
                    }

                }
            }
            else if (e.getSource() instanceof MyCheckBox) {
                ((MyCheckBox) e.getSource()).setSelectedLinkToComponent();
            }
            else if(e.getSource() instanceof JComboBox){
                switch (((JComboBox)e.getSource()).getName()) {
                    case "boxListMaterial" : {
                        if (e.getModifiers() != 0) {
                            ((ViewCreateFileForCutting) currentView).updateBoxListDecor();
                        }
                        break;
                    }
                }
            }
        }
        catch (CantWriteDoc | DontHaveFilePattern cantWriteDoc) {
            view.writeJustMessage(cantWriteDoc.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
        catch (DontHaveData dontHaveData) {
            view.writeJustMessage(dontHaveData.getMessage(), JOptionPane.WARNING_MESSAGE);
        }
        System.out.println(System.currentTimeMillis() - s);

    }
    public void keyReleased(KeyEvent e) {

        if(e.getComponent() instanceof EnumValue) {
            switch (((EnumValue) e.getComponent()).getEnumValue()) {
                case ALL_SUM_IN_EUR: {
                    view.onlyNumber((JTextField) e.getSource());
                    ((ViewBasicContract) currentView).editBasicContractEditAllSumInEUR();
                    break;
                }
                case SEARCH_CLIENT: {
                    ((ViewListClients)currentView).filter();
                    break;
                }
                case PREPAYMENT_OR_10_PERCENT_SUM: {
                    view.onlyNumber((JTextField) e.getSource());
                    ((ViewBasicContract) currentView).editBasicContractEditPrepaymentOr10PercentSum();
                    break;
                }
                case PAY_UP_TO_50_PERCENT_SUM: {
                    view.onlyNumber((JTextField) e.getSource());
                    ((ViewBasicContract) currentView).editBasicContractEditPayUpTo50PercentSum();
                    break;
                }
                case ALL_SUM_UP_SALE_IN_BYN: {
                    view.onlyNumber((JTextField) e.getComponent());
                    ((ViewUpSaleContract) currentView).editAllSumUpSaleInBYN();
                    break;
                }
                case PREPAYMENT_UP_SALE: {
                    view.onlyNumber((JTextField) e.getComponent());
                    ((ViewUpSaleContract) currentView).editPrepaymentUpSaleUpSale();
                    break;
                }
                case RATE: {
                    view.onlyDoubleNumber((JTextField) e.getComponent(), 4);
                    ((ViewBasicContract) currentView).editCurrencyBasicContract();
                    break;
                }
                case ALL_SUM_IN_EUR_SUPPLEMENTARY_AGREEMENT: {
                    view.onlyNumber((JTextField) e.getComponent());
                    ((ViewSupplementaryAgreementBasicContract) currentView).editAllEURSum();
                    break;
                }
                case PREPAYMENT_OR_10_PERCENT_SUM_SUPPLEMENTARY_AGREEMENT: {
                    view.onlyNumber((JTextField) e.getComponent());
                    ((ViewSupplementaryAgreementBasicContract) currentView).editPrepayment();
                    break;
                }
                case PAY_UP_TO_50_PERCENT_SUM_SUPPLEMENTARY_AGREEMENT: {
                    view.onlyNumber((JTextField) e.getComponent());
                    ((ViewSupplementaryAgreementBasicContract) currentView).editPayUpTo50percent();
                    break;
                }
                case PREPAYMENT_UP_SALE_SUPPLEMENTARY_AGREEMENT:
                case SUM_UP_SALE_IN_BYN_SUPPLEMENTARY_AGREEMENT: {
                    view.onlyNumber((JTextField) e.getComponent());
                    ((ViewSupplementaryAgreementUpSale) currentView).editPrepaymentOrAllSumSupplementaryAgreementUpSale();
                    break;
                }
                case RATE_SUM_SUPPLEMENTARY_AGREEMENT: {
                    view.onlyDoubleNumber((JTextField) e.getComponent(), 4);
                    ((ViewSupplementaryAgreementBasicContract) currentView).editCurrency();
                    break;
                }
                case PRICE_IN_EUR_INVOICE_DOCUMENT: {
                    view.onlyNumber((JTextField) e.getComponent());
                    ((ViewInvoiceDocument)currentView).editPriceInEURInvoiceDocument();
                    break;
                }
                case PRICE_BYN_INVOICE_DOCUMENT: {
                    view.onlyNumber((JTextField) e.getComponent());
                    ((ViewInvoiceDocument) currentView).editPriceBYNInvoiceDocument();
                    break;
                }
                case RATE_INVOICE_DOCUMENT: {
                    view.onlyDoubleNumber((JTextField) e.getComponent(), 4);
                    ((ViewInvoiceDocument) currentView).editCurrencyInvoiceDocument();
                    break;
                }
                case VAT_20_INVOICE_DOCUMENT: {
                    view.onlyDoubleNumber(((JTextField) e.getComponent()), 2);
                    break;
                }
                case DATE_CREATE_SUPPLEMENTARY_AGREEMENT_BASIC_CONTRACT: {
                    if (!model.isDateCurrency(((JTextField) e.getComponent()).getText())) {
                        ((ViewSupplementaryAgreementBasicContract) currentView).setCurrencyZero(false);
                    } else {
                        ((ViewSupplementaryAgreementBasicContract) currentView).setCurrencyZero(true);
                    }
                    ((ViewSupplementaryAgreementBasicContract) currentView).editCurrency();
                    break;
                }
                case DATE_CREATE_CONTRACT: {
                    if (!model.isDateCurrency(((JTextField) e.getComponent()).getText())) {
                        ((ViewBasicContract) currentView).setCurrencyZero(false);
                    } else {
                        ((ViewBasicContract) currentView).setCurrencyZero(true);
                    }
                    ((ViewBasicContract) currentView).editCurrencyBasicContract();
                    break;
                }
                case CREATE_DATE_INVOICE_DOCUMENT: {
                    if (!model.isDateCurrency(((JTextField) e.getComponent()).getText())) {
                        ((ViewInvoiceDocument) currentView).setCurrencyZero(false);
                    } else {
                        ((ViewInvoiceDocument) currentView).setCurrencyZero(true);
                    }
                    ((ViewInvoiceDocument) currentView).editCurrencyInvoiceDocument();
                    break;
                }
                case VALUE_RATE: {
                    view.onlyDoubleNumber((JTextField) e.getComponent(), 4);
                    break;
                }
                case NUMBER_SUPPLEMENTARY_AGREEMENT_BASIC_CONTRACT:
                case NUMBER_POWER_OF_ATTORNEY:
                case PAY_UP_TO_100_PERCENT_SUM_SUPPLEMENTARY_AGREEMENT:
                case ALL_SUM_IN_BYN_SUPPLEMENTARY_AGREEMENT:
                case PAY_UP_TO_100_PERCENT_SUM:
                case PAY_UP_TO_100_PERCENT_UP_SALE_SUPPLEMENTARY_AGREEMENT:
                case NUMBER_SUPPLEMENTARY_AGREEMENT_UP_SALE:
                case PAY_UP_TO_100_PERCENT_UP_SALE:
                case ALL_SUM_IN_BYN: {
                    view.onlyNumber((JTextField) e.getComponent());
                    break;
                }
            }
        }
        else if(e.getComponent() instanceof  JTextField) {
            switch (e.getComponent().getName()) {
                case "boxListMaterial": {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        ((ViewCreateFileForCutting) currentView).updateBoxListDecor();
                    } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                        ((ViewCreateFileForCutting) currentView).filterBoxListMaterial(((JTextField) e.getComponent()).getText());
                    } else if (String.valueOf(e.getKeyChar()).matches("[а-яА-ЯёЁa-zA-Z0-1]")) {
                        ((ViewCreateFileForCutting) currentView).filterBoxListMaterial(((JTextField) e.getComponent()).getText());
                    }
                    break;
                }
                case "boxListDecor": {
                    if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                        ((ViewCreateFileForCutting) currentView).filterBoxListDecor(((JTextField) e.getComponent()).getText());
                    } else if (String.valueOf(e.getKeyChar()).matches("[а-яА-ЯёЁa-zA-Z0-1]")) {
                        ((ViewCreateFileForCutting) currentView).filterBoxListDecor(((JTextField) e.getComponent()).getText());
                    }
                    break;
                }
            }
            if (e.getComponent().getName().contains("dditionalProduct")) {
                if (e.getComponent().getName().contains("supplementaryAgreement")) {
                    //"Этот блок отвественный за Доп соглашение Апсейл
                    if (e.getComponent().getName().contains("Count") || e.getComponent().getName().contains("sDiscount") ||
                            e.getComponent().getName().contains("FullPrice")) {
                        view.onlyNumber((JTextField) e.getComponent());
                        ((ViewSupplementaryAgreementUpSale) currentView).editSupplementaryAgreementUpSaleSumProductPriceAndDiscountAndCount((JTextField) e.getComponent());

                    }
                    if (e.getComponent().getName().contains("WithDiscount")) {
                        view.onlyNumber((JTextField) e.getComponent());
                        ((ViewSupplementaryAgreementUpSale) currentView).editWithDiscount();
                    }
                } else {

                    if (e.getComponent().getName().contains("additionalProductsCount") || e.getComponent().getName().contains("additionalProductsDiscount") || e.getComponent().getName().contains("additionalProductsFullPrice")) {
                        view.onlyNumber((JTextField) e.getComponent());
                        ((ViewUpSaleContract) currentView).editUpSaleEditSumProductPriceAndDiscountAndCount((JTextField) e.getComponent());


                    }
                    if (e.getComponent().getName().contains("additionalProductsWithDiscount")) {
                        view.onlyNumber((JTextField) e.getComponent());
                        ((ViewUpSaleContract) currentView).editWithDiscount();
                    }
                }
            }
        }
    }
/*    public void keyReleased(KeyEvent e) {
        //свич реализует логику если кнопку нажал и находится  в определеном поел то делаем это
        switch (e.getComponent().getName()){
            case "allSumInEUR":{
                view.onlyNumber((JTextField) e.getSource());
                ((ViewBasicContract)display).editBasicContractEditAllSumInEUR();
                break;
            }
            case "searchClient" :{
                view.listClientsAndSelect(model.listSelectClientSearch(view.getComponentsStaticPanel()),((JTextField)e.getComponent()).getText());
                break;
            }
            case "prepaymentOr10PercentSum" :{
                view.onlyNumber((JTextField) e.getSource());
                ((ViewBasicContract)display).editBasicContractEditPrepaymentOr10PercentSum();
                break;
            }
            case "payUpTo50PercentSum":{
                view.onlyNumber((JTextField) e.getSource());
                ((ViewBasicContract)display).editBasicContractEditPayUpTo50PercentSum();
                break;
            }
            case "allSumUpSaleInBYN" :{
                view.onlyNumber((JTextField) e.getComponent());
                ((ViewUpSaleContract)display).editAllSumUpSaleInBYN();
                break;
            }
            case "prepaymentUpSale" : {
                view.onlyNumber((JTextField) e.getComponent());
                ((ViewUpSaleContract)display).editPrepaymentUpSaleUpSale();
                break;
            }
            case "currency":{
                view.onlyDoubleNumber((JTextField) e.getComponent(),4);
                ((ViewBasicContract)display).editCurrencyBasicContract();
                break;
            }
            case "allSumInEURSupplementaryAgreement" : {
                view.onlyNumber((JTextField) e.getComponent());
                ((ViewSupplementaryAgreementBasicContract)display).editAllEURSumAgreementBasicContract();
                break;
            }
            case "prepaymentOr10PercentSumSupplementaryAgreement" : {
                view.onlyNumber((JTextField) e.getComponent());
                ((ViewSupplementaryAgreementBasicContract)display).editPrepaymentOr10PercentSumSupplementaryAgreement();
                break;
            }
            case "payUpTo50PercentSumSupplementaryAgreement" : {
                view.onlyNumber((JTextField) e.getComponent());
                ((ViewSupplementaryAgreementBasicContract)display).editPayUpTo50percentAgreementBasicContract();
                break;
            }
            case "prepaymentUpSaleSupplementaryAgreement" :
            case "sumUpSaleInBYNSupplementaryAgreement" : {
                view.onlyNumber((JTextField) e.getComponent());
                ((ViewSupplementaryAgreementUpSale)display).editPrepaymentOrAllSumSupplementaryAgreementUpSale();
                break;
            }
            case "currencySumSupplementaryAgreement" :{
                view.onlyDoubleNumber((JTextField) e.getComponent(),4);
                ((ViewSupplementaryAgreementBasicContract)display).editCurrencyAgreementBasicContract();
                break;
            }
            case "priceInEURInvoiceDocument" :{
                view.onlyNumber((JTextField) e.getComponent());
                ((ViewInvoiceDocument) display).editPriceInEURInvoiceDocument();
                break;
            }
            case "priceBYNInvoiceDocument" :{
                view.onlyNumber((JTextField) e.getComponent());
                ((ViewInvoiceDocument) display).editPriceBYNInvoiceDocument();
                break;
            }
            case "currencyInvoiceDocument" :{
                view.onlyDoubleNumber((JTextField)e.getComponent(),4 );
                ((ViewInvoiceDocument) display).editCurrencyInvoiceDocument();
                break;
            }
            case "numberSupplementaryAgreementBasicContract" :
            case "numberPowerOfAttorney" : {
                view.onlyNumber((JTextField) e.getComponent());
                break;
            }
            case "vat20InvoiceDocument" : {
                view.onlyDoubleNumber(((JTextField)e.getComponent()),2);
                break;
            }
            case "dateCreateSupplementaryAgreementBasicContract" : {
                if(!model.isDateCurrency(((JTextField)e.getComponent()).getText())){
                    ((ViewSupplementaryAgreementBasicContract)display).setCurrencyZero(false);
                }
                else {
                    ((ViewSupplementaryAgreementBasicContract)display).setCurrencyZero(true);
                }
                ((ViewSupplementaryAgreementBasicContract) display).editCurrencyAgreementBasicContract();
                break;
            }
            case "dateCreateContract" : {
                if(!model.isDateCurrency(((JTextField)e.getComponent()).getText())){
                    ((ViewBasicContract)display).setCurrencyZero(false);
                }
                else {
                    ((ViewBasicContract)display).setCurrencyZero(true);
                }
                ((ViewBasicContract) display).editCurrencyBasicContract();
                break;
            }
            case "createDateInvoiceDocument" :{
                if(!model.isDateCurrency(((JTextField)e.getComponent()).getText())){
                    ((ViewInvoiceDocument)display).setCurrencyZero(false);
                }
                else {
                    ((ViewInvoiceDocument)display).setCurrencyZero(true);
                }
                ((ViewInvoiceDocument)display).editCurrencyInvoiceDocument();
                break;
            }
            case "valueCurrency" : {
                view.onlyDoubleNumber((JTextField) e.getComponent(),4);
                break;
            }
            case "boxListMaterial" :{
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    ((ViewCreateFileForCutting)display).updateBoxListDecor();
                }
                else if (e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    ((ViewCreateFileForCutting) display).filterBoxListMaterial(((JTextField) e.getComponent()).getText());
                }
                else if(String.valueOf(e.getKeyChar()).matches("[а-яА-ЯёЁa-zA-Z0-1]")) {
                    ((ViewCreateFileForCutting) display).filterBoxListMaterial(((JTextField) e.getComponent()).getText());
                }
                break;
            }
            case "boxListDecor" : {
                if (e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    ((ViewCreateFileForCutting)display).filterBoxListDecor(((JTextField)e.getComponent()).getText());
                }
                else if(String.valueOf(e.getKeyChar()).matches("[а-яА-ЯёЁa-zA-Z0-1]")) {
                    ((ViewCreateFileForCutting)display).filterBoxListDecor(((JTextField)e.getComponent()).getText());
                }
                break;
            }
        }
        if(e.getComponent().getName().contains("dditionalProduct")){
            if(e.getComponent().getName().contains("supplementaryAgreement")){
                //"Этот блок отвественный за Доп соглашение Апсейл
                if (e.getComponent().getName().contains("Count")||e.getComponent().getName().contains("sDiscount")||
                        e.getComponent().getName().contains("FullPrice")){
                    view.onlyNumber((JTextField) e.getComponent());
                    ((ViewSupplementaryAgreementUpSale)display).editSupplementaryAgreementUpSaleSumProductPriceAndDiscountAndCount((JTextField) e.getComponent());

                }  if  (e.getComponent().getName().contains("WithDiscount")){
                    view.onlyNumber((JTextField) e.getComponent());
                    ((ViewSupplementaryAgreementUpSale)display).editWithDiscount();
                }
            }
            else {

                if (e.getComponent().getName().contains("additionalProductsCount")||e.getComponent().getName().contains("additionalProductsDiscount")|| e.getComponent().getName().contains("additionalProductsFullPrice")){
                    view.onlyNumber((JTextField) e.getComponent());
                    ((ViewUpSaleContract)display).editUpSaleEditSumProductPriceAndDiscountAndCount((JTextField) e.getComponent());


                }  if  (e.getComponent().getName().contains("additionalProductsWithDiscount")){
                    view.onlyNumber((JTextField) e.getComponent());
                    ((ViewUpSaleContract)display).editWithDiscount();
                }
            }
        }
    }*/
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (((Component) e.getSource()).getName()) {
            case "jListClients": {
                if (e.getClickCount() == 2) {
                    int index = ((JList) e.getSource()).locationToIndex(e.getPoint());
                    if(index!=-1) {
                        try {
                        model.writeDataClient(currentView.getData());
                        view.displayMainClient(model.getDataClient());
                        } catch (DontHaveData dontHaveData) {
                            dontHaveData.printStackTrace();
                        }
                    }
                }
                break;
            }
            case "boxListMaterial" :{
                ((ViewCreateFileForCutting) currentView).setVisibleBoxMaterial();
                break;
            }
            case "boxListDecor" :{
                ((ViewCreateFileForCutting) currentView).setVisibleBoxDecor();
                break;
            }
            case "boxListEdge" :{
                ((ViewCreateFileForCutting) currentView).setVisibleBoxEdge();
                break;
            }

        }

    }


    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {

    }
}
