package by.bsu.ti.lab6.action;

import org.apache.commons.lang3.StringUtils;

public class PrefixGenerator {
    private int length;
    private int currentValue;

    public PrefixGenerator(int length) {
        this.length = length;
        this.currentValue = 0;
    }

    public PrefixGenerator(int start, int length) {
        this.length = length;
        this.currentValue = start;
    }

    public String next() {
        return StringUtils.leftPad(Integer.toBinaryString(currentValue++), length, '0');
    }

    public String convert(int i) {
        return StringUtils.leftPad(Integer.toBinaryString(i), length, '0');
    }
}
