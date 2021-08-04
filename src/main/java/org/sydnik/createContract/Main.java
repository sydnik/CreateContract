package org.sydnik.createContract;



public class Main {
    public static void main(String[] args)  {
        try {
            Model model = new Model();
            model.setCurrency();
            MyView view = new MyView();
            MyController controller = new MyController(model,view);
            controller.displayMainPage();

        } catch (Exception e){
            Model model = new Model();
            MyView view = new MyView();
            MyController controller = new MyController(model,view);
            controller.displayRateEUR();
        }

    }
}

