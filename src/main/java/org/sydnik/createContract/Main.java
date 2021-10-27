package org.sydnik.createContract;


import org.sydnik.createContract.view.MainView;

public class Main {
    public static void main(String[] args)  {
        try {
            Model model = new Model();
            model.setCurrency();
            MainView view = new MainView();
            MyController controller = new MyController(model,view);
            controller.displayMainPage();

        } catch (Exception e){
            Model model = new Model();
            MainView view = new MainView();
            MyController controller = new MyController(model,view);
            controller.displayRateEUR();
        }

    }
}

