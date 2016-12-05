package by.bsu.ti.lab6.action;

import java.util.HashMap;
import java.util.HashSet;

public class LempelZivTextProcessor {
    public static String compressBinaryText(String sourceText) {
        int uniquePhrasesNumber = countUniquePhrases(sourceText);
        int prefixLength = countPrefixSize(uniquePhrasesNumber);
        return processText(sourceText, prefixLength);
    }

    private static String processText(String text, int prefixLength) {
        StringBuilder result = new StringBuilder();
        PrefixGenerator prefixGenerator = new PrefixGenerator(1, prefixLength);
        HashMap<String, String> map = new HashMap<>();
        int currentPosition = 0;
        main:
        while (currentPosition < text.length()) {
            int phraseLength = 0;
            String phrase = null;
            do {
                phraseLength++;
                if (currentPosition + phraseLength > text.length()) {
                    result.append(map.get(phrase));
                    break main;
                }
                phrase = text.substring(currentPosition, currentPosition + phraseLength);
            } while (map.containsKey(phrase));

            String prefix = prefixGenerator.next();
            map.put(phrase, prefix);
            if (phraseLength > 1) {
                result.append(map.get(phrase.substring(0, phrase.length() - 1)));
            } else {
                result.append(prefixGenerator.convert(0));
            }
            result.append(phrase.substring(phrase.length() - 1));
            currentPosition += phraseLength;
        }
        return result.toString();
    }

    public static int countUniquePhrases(String text) {
        HashSet<String> set = new HashSet<>();
        int currentPosition = 0;
        while (currentPosition < text.length()) {
            int phraseLength = 0;
            String phrase;
            do {
                phraseLength++;
                phrase = text.substring(currentPosition, currentPosition + phraseLength);
            } while (set.contains(phrase) && currentPosition + phraseLength < text.length());
            set.add(phrase);
            currentPosition += phraseLength;
        }
        return set.size();
    }

    private static int countPrefixSize(int uniquePhrasesNumber) {
        return (int) Math.floor(Math.log(uniquePhrasesNumber) / Math.log(2)) + 1;
    }
}
