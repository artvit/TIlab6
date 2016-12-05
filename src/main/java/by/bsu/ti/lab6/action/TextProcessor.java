package by.bsu.ti.lab6.action;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class TextProcessor {

    public static String loadText(String filename) {
        try {
            return new String(Files.readAllBytes(Paths.get(TextProcessor.class.getResource("/" + filename).toURI())));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("CANNOT LOAD FILE!!!!!!");
        }
    }

    public static String prepareRussianText(String sourceText) {
        sourceText = sourceText.toLowerCase();
        sourceText = sourceText.replaceAll("ё", "е");
        sourceText = sourceText.replaceAll("ъ", "ь");
        sourceText = sourceText.replaceAll("[^ а-я]", "");
        return sourceText;
    }

    public static String convertToBinary(String text) {
        char[] chars = text.toCharArray();
        StringBuilder encodedText = new StringBuilder();
        for (char symbol : chars) {
            encodedText.append(Integer.toBinaryString(symbol));
        }
        return encodedText.toString();
    }

}
