package demo.core.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangbolun on 15/1/4.
 */
public class DropdownListUtil {
    //我要买我要卖下拉列表生成器

    //低热值map
    public static Map<String, Object> genMapInt() {
        Map<String, Object> map = new LinkedHashMap<>();
//        map.put("0", "全部");
        map.put("1", "3500以下");
        int min = 3500;
        int max = 6500;
        int interval = 200;

        for (int i = min; i <= max; i += interval) {
            Integer integer = new Integer(i);
            map.put(integer.toString(), integer.toString());
        }

        return map;
    }

    //高热值map
    public static Map<String, Object> genMapInt(int start) {
        Map<String, Object> map = new LinkedHashMap<>();
        int min = start + 100;
        int max = 6400;
        int interval = 200;

        for (int i = min; i <= max; i += interval) {
            Integer integer = new Integer(i);
            map.put(integer.toString(), integer.toString());
        }

        map.put("10000", "6500以上");
        return map;
    }

    //低硫分map
    public static Map<String, Object> genMapDouble() {
        Map<String, Object> map = new LinkedHashMap<>();
        //map.put("0", "全部");
        map.put("0.1", "0.2以下");
        double min = 0.3;
        double max = 3.9;
        double interval = 0.2;

        for (double i = min; i <= (max + interval); i += interval) {
            BigDecimal bigDecimal = new BigDecimal(i).setScale(1, BigDecimal.ROUND_HALF_EVEN);
            map.put(bigDecimal.toString(), bigDecimal.toString());
        }

        return map;
    }

    //高硫分map
    public static Map<String, Object> genMapDouble(double start) {
        Map<String, Object> map = new LinkedHashMap<>();
        double min = start + 0.1;
        double max = 4.0;
        double interval = 0.2;

        for (double i = min; i <= (max + interval); i += interval) {
            BigDecimal bigDecimal = new BigDecimal(i).setScale(1, BigDecimal.ROUND_HALF_EVEN);
            map.put(bigDecimal.toString(), bigDecimal.toString());
        }

        map.put("10", "4.0以上");

        return map;
    }

    //低热值集合
    public static List<String> genMapIntList() {
        List<String> list = new ArrayList<>();
        //list.add("全部");
        list.add("3500以下");
        int min = 3500;
        int max = 6500;
        int interval = 200;

        for (int i = min; i <= max; i += interval) {
            Integer integer = new Integer(i);
            list.add(integer.toString());
        }

        return list;
    }

    //高热值集合
    public static List<String> genMapIntList(int start) {
        List<String> list = new ArrayList<>();
        int min = start + 100;
        int max = 6400;
        int interval = 200;

        for (int i = min; i <= max; i += interval) {
            Integer integer = new Integer(i);
            list.add(integer.toString());
        }

        list.add("6500以上");
        return list;
    }

    //低硫分集合
    public static List<String> genMapDoubleList() {
        List<String> list = new ArrayList<>();
        //list.add("全部");
        list.add("0.2以下");
        double min = 0.3;
        double max = 3.9;
        double interval = 0.2;

        for (double i = min; i <= (max + interval); i += interval) {
            BigDecimal bigDecimal = new BigDecimal(i).setScale(1, BigDecimal.ROUND_HALF_EVEN);
            list.add(bigDecimal.toString());
        }

        return list;
    }

    //高硫分集合
    public static List<String> genMapDoubleList(double start) {
        List<String> list = new ArrayList<>();
        double min = start + 0.1;
        double max = 4.0;
        double interval = 0.2;

        for (double i = min; i <= (max + interval); i += interval) {
            BigDecimal bigDecimal = new BigDecimal(i).setScale(1, BigDecimal.ROUND_HALF_EVEN);
            list.add(bigDecimal.toString());
        }

        list.add("4.0以上");

        return list;
    }

    //低热值map
    public static Map<Integer, String> mapKeyIntValueString() {
        Map<Integer, String> map = new LinkedHashMap<>();
        //map.put(0, "全部");
        map.put(1, "3500以下");
        int min = 3500;
        int max = 6500;
        int interval = 200;

        for (int i = min; i <= max; i += interval) {
            Integer integer = new Integer(i);
            map.put(integer, integer.toString());
        }

        return map;
    }

    //高热值map
    public static Map<Integer, String> mapKeyIntValueString(int start) {
        Map<Integer, String> map = new LinkedHashMap<>();
        int min = start + 100;
        int max = 6400;
        int interval = 200;

        for (int i = min; i <= max; i += interval) {
            Integer integer = new Integer(i);
            map.put(integer, integer.toString());
        }

        map.put(10000, "6500以上");
        return map;
    }

    //低硫分map
    public static Map<BigDecimal, String> mapKeyDecimalValueString() {
        Map<BigDecimal, String> map = new LinkedHashMap<>();
        //map.put(new BigDecimal(0), "全部");
        map.put(new BigDecimal(0.1), "0.2以下");
        double min = 0.3;
        double max = 3.9;
        double interval = 0.2;

        for (double i = min; i <= (max + interval); i += interval) {
            BigDecimal bigDecimal = new BigDecimal(i).setScale(1, BigDecimal.ROUND_HALF_EVEN);
            map.put(bigDecimal, bigDecimal.toString());
        }

        return map;
    }

    //高硫分map
    public static Map<BigDecimal, String> mapKeyDecimalValueString(double start) {
        Map<BigDecimal, String> map = new LinkedHashMap<>();
        double min = start + 0.1;
        double max = 4.0;
        double interval = 0.2;

        for (double i = min; i <= (max + interval); i += interval) {
            BigDecimal bigDecimal = new BigDecimal(i).setScale(1, BigDecimal.ROUND_HALF_EVEN);
            map.put(bigDecimal, bigDecimal.toString());
        }

        map.put(new BigDecimal(10), "4.0以上");

        return map;
    }

}
