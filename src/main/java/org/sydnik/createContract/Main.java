package org.sydnik.createContract;

import javax.swing.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Model model = new Model();

        JFrame window = new JFrame();
        MyController controller = new MyController(model);




        window.setTitle("CreateContract");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(450, 500);
        window.setResizable(false);
//        controller.createWindow();

        window.add(controller.getView());



        window.setLocationRelativeTo(null);
        window.setVisible(true);

//        String docWord = new String(Files.readAllBytes(Paths.get(
//                "saveContract/Судникович Виталий Олегович МН5-210402-76/testXML.xml")), StandardCharsets.UTF_8);
//        docWord = docWord.replace("NumberContract", "МН5-210402-76");
//
//        Files.write(Paths.get("saveContract/Судникович Виталий Олегович МН5-210402-76/testXML2.xml"), docWord.getBytes(StandardCharsets.UTF_8));

//        String fileName = "saveContract/Судникович Виталий Олегович МН5-210402-76/testXML.xml";
//        String search = "NumberContract";
//        String replace = "МН5-210402-76";
//        Charset charset = StandardCharsets.UTF_8;
//        Path path = Paths.get(fileName);
//        Files.write(path,
//                new String(Files.readAllBytes(path), charset).replace(search, replace)
//                        .getBytes(charset));
//    }
    }
}

