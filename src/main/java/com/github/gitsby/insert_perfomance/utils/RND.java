package com.github.gitsby.insert_perfomance.utils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class RND {
    private static final String rus = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String RUS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя".toUpperCase();
    private static final String eng = "abcdefghijklmnopqrstuvwxyz";
    private static final String ENG = "abcdefghijklmnopqrstuvwxyz".toUpperCase();
    private static final String DEG = "0123456789";
    private static final String ALL;
    private static final char[] ALL_CHARS;
    private static final int ALL_CHARS_LENGTH;
    private static final String ALL_ENG;
    private static final char[] ALL_ENG_CHARS;
    private static final int ALL_ENG_CHARS_LENGTH;
    private static final Random rnd;

    public RND() {
    }

    public static String str(int len) {
        char[] charArray = new char[len];

        for(int i = 0; i < len; ++i) {
            charArray[i] = ALL_CHARS[rnd.nextInt(ALL_CHARS_LENGTH)];
        }

        return new String(charArray);
    }

    public static String strEng(int len) {
        char[] charArray = new char[len];

        for(int i = 0; i < len; ++i) {
            charArray[i] = ALL_ENG_CHARS[rnd.nextInt(ALL_ENG_CHARS_LENGTH)];
        }

        return new String(charArray);
    }

    /** @deprecated */
    @Deprecated
    public static String intStr(int len) {
        return strInt(len);
    }

    public static String strInt(int len) {
        char[] charArray = new char[len];

        for(int i = 0; i < len; ++i) {
            charArray[i] = "0123456789".charAt(rnd.nextInt("0123456789".length()));
        }

        return new String(charArray);
    }

    public static long plusLong(long max) {
        long L = rnd.nextLong();
        if (L < 0L) {
            L = -L;
        }

        return L % max;
    }

    public static int plusInt(int max) {
        return rnd.nextInt(max);
    }

    public static Date dateYears(int yearFrom, int yearTo) {
        Calendar cal = new GregorianCalendar();
        cal.add(1, yearFrom);
        long from = cal.getTimeInMillis();
        cal.add(1, yearTo - yearFrom);
        long to = cal.getTimeInMillis();
        long time;
        if (from > to) {
            time = from;
            from = to;
            to = time;
        }

        time = from + plusLong(to - from);
        return new Date(time);
    }

    public static Date dateDays(int fromDaysBeforeNow, int toDayAfterNow) {
        Calendar cal = new GregorianCalendar();
        cal.add(6, fromDaysBeforeNow);
        long from = cal.getTimeInMillis();
        cal.add(6, toDayAfterNow - fromDaysBeforeNow);
        long to = cal.getTimeInMillis();
        long x = plusLong(to - from);
        cal.setTimeInMillis(from + x);
        return cal.getTime();
    }

    public static byte[] byteArray(int len) {
        byte[] ret = new byte[len];
        rnd.nextBytes(ret);
        return ret;
    }

    public static boolean bool() {
        return plusInt(10) % 2 == 0;
    }

    @SafeVarargs
    public static <E extends Enum<E>> E someEnum(E... values) {
        return values[rnd.nextInt(values.length)];
    }

    public static double plusDouble(double max, int point) {
        double ret = rnd.nextDouble();
        ret *= max;

        int i;
        for(i = 0; i < point; ++i) {
            ret *= 10.0D;
        }

        ret = Math.floor(ret);

        for(i = 0; i < point; ++i) {
            ret /= 10.0D;
        }

        return ret;
    }

    public static BigDecimal bd(long max, int afterPoint) {
        BigDecimal ret = new BigDecimal(plusLong(max));

        int i;
        for(i = 0; i < afterPoint; ++i) {
            ret = ret.multiply(BigDecimal.TEN);
            ret = ret.add(new BigDecimal(plusInt(10)));
        }

        for(i = 0; i < afterPoint; ++i) {
            ret = ret.divide(BigDecimal.TEN);
        }

        return ret;
    }

    static {
        ALL = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" + RUS + "abcdefghijklmnopqrstuvwxyz" + ENG + "0123456789";
        ALL_CHARS = ALL.toCharArray();
        ALL_CHARS_LENGTH = ALL_CHARS.length;
        ALL_ENG = "abcdefghijklmnopqrstuvwxyz" + ENG + "0123456789";
        ALL_ENG_CHARS = ALL_ENG.toCharArray();
        ALL_ENG_CHARS_LENGTH = ALL_ENG_CHARS.length;
        rnd = new Random();
    }
}

