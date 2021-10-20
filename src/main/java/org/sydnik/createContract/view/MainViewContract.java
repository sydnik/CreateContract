package org.sydnik.createContract.view;

import org.sydnik.createContract.MyController;
import org.sydnik.createContract.MyView;
import org.sydnik.createContract.data.DataClient;

import javax.swing.*;
import java.awt.*;

public abstract class MainViewContract {
    private static final int HEIGHT_WORKING_ZONE = MyView.HEIGHT_WINDOW_APPS-60;//
    public static final int INDEX_WORK_WINDOW = 0;//Под это число завязана работа чекбоксов


    protected JPanel staticJPanel;
    protected DataClient dataClient;
    protected String name;
    protected JPanel workingWindow;
    protected MyController controller;
    protected GridBagConstraints gridBagConstraints;

    public MainViewContract(JPanel staticJPanel, DataClient dataClient, String name, MyController controller) {
        workingWindow = new JPanel();
        workingWindow.setLayout(new GridBagLayout());
        this.staticJPanel = staticJPanel;
        this.dataClient = dataClient;
        this.name = name;
        this.controller = controller;
    }
    protected void startPage(){
        staticJPanel.removeAll();
        staticJPanel.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 0.1;

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        JLabel j1 = new JLabel("Клиент: ");
        j1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j1,gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        JLabel j2 = new JLabel(dataClient.getFullNameClient());
        j2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j2,gridBagConstraints);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        JLabel j4 = new JLabel(name);
        j4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j4,gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        JLabel j3 = new JLabel(dataClient.getNumberContract()+" " + dataClient.getStrangeName());
        j3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        staticJPanel.add(j3,gridBagConstraints);

        gridBagConstraints.gridy = 2;
        staticJPanel.add(new JLabel(" "),gridBagConstraints);
        gridBagConstraints.gridy = 3;
        staticJPanel.add((new JLabel(" ")),gridBagConstraints);

    }
    protected void endPage (){
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth =3;
        staticJPanel.add(workingWindow,gridBagConstraints,INDEX_WORK_WINDOW);

        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.gridwidth =3;
        JButton backSelectClient = new JButton("Назад");
        backSelectClient.setName("backSelectClient");
        backSelectClient.addActionListener(controller);
        staticJPanel.add(backSelectClient,gridBagConstraints);

        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 3;
        JButton mainPage = new JButton("Главная страница");
        mainPage.setName("mainPage");
        mainPage.addActionListener(controller);
        staticJPanel.add(mainPage,gridBagConstraints);

        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth =3;
        gridBagConstraints.ipady = (int) (HEIGHT_WORKING_ZONE - staticJPanel.getMinimumSize().getHeight());
        staticJPanel.add(new JPanel(),gridBagConstraints);

        staticJPanel.repaint();
        staticJPanel.revalidate();
    }


}
