package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int i = 1;
        byte b = 22;
        short s = 344;
        long l = 4600L;
        double d = 5000.5d;
        float f = 5.5f;
        char c  = 'a';
        boolean is = true;
        LOG.debug("info i : {}, b : {}, s : {}, l : {}, d : {}, f : {}, c : {}, is : {}", i, b, s, l, d, f, c, is);
        LOG.error("Error");
    }
}