package by.bsu.ti.lab6.util;

import by.bsu.ti.lab6.action.LempelZivTextProcessor;
import by.bsu.ti.lab6.action.TextProcessor;

public class Main {
    private static final String FILENAME = "input.txt";

    public static void main(String[] args) {
        String text = TextProcessor.loadText(FILENAME);
        text = TextProcessor.prepareRussianText(text);
        text = TextProcessor.convertToBinary(text);
        System.out.println(text);
        System.out.println("n = " + text.length());
        System.out.println("c(n) = " + LempelZivTextProcessor.countUniquePhrases(text));
        System.out.println("n/log(n) = " + text.length() * Math.log(2) / Math.log(text.length()));
        String compressedText = LempelZivTextProcessor.compressBinaryText(text);
        System.out.println(compressedText);
        System.out.println(compressedText.length());
    }
}
