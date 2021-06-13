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
        MyView view = new MyView();
        MyController controller = new MyController(model,view);


        //Тут кароче список как подправить word документы
    }
}

