package kr.co.crossarc.extract.values.file.ui;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static String inputDirectory(){
        return scanner.nextLine();
    }
}
