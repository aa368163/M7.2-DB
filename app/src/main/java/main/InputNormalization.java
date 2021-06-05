package main;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class InputNormalization {



    public static void regularize(Method method, String type, String tip, String error)
           throws InvocationTargetException, IllegalAccessException {
        System.out.println(tip);
        Scanner input = new Scanner(System.in);
        String inputWord = input.nextLine();
        switch (type) {
            case "int":
                int number = 0;
                try {
                    number = Integer.parseInt(inputWord);
                } catch (NumberFormatException exception) {
                    exception.printStackTrace();
                    System.out.println(error);
                    method.invoke(null);
                }
                public static final int digital = number;
                break;
            case "String":
                public static final String string = inputWord;
                break;
            case "boolean":
                try {
                    if (inputWord.equalsIgnoreCase("Y")) {
                        public static final boolean bool = true;
                    } else if (inputWord.equalsIgnoreCase("N")) {
                        public static final boolean bool = false;
                    } else {
                        throw new IOException();
                    }
                } catch (IOException exception) {
                    exception.printStackTrace();
                    System.out.println(error);
                    method.invoke(null);
                }
                break;
        }
    }

    public static int extractHour(String dateTime) {
        String times = dateTime.split(" ")[2];
        return Integer.parseInt(times.split(":")[0]);
    }

    public static int extractMin(String dateTime) {
        String times = dateTime.split(" ")[2];
        return Integer.parseInt(times.split(":")[1]);
    }

    public static String extractWeek(String dateTime) {
        return dateTime.split(" ")[1];
    }
}
