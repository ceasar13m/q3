package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

public class Main {

    private static final int NP_MORE_THAN_1_DAY = 1;
    private static final int NP_MORE_THAN_30_DAYS = 2;
    private static final int NP_MORE_THAN_50_DAYS = 3;

    private static final int ZERO_CODE = 48;


    public static void main(String[] args) {
        final String creditAgreement1 = "00010100000000101";
        final String creditAgreement2 = "0010000123000100C";
        final String creditAgreement3 = "00001000000000000";

        showStatus(getCounts(creditAgreement1));
        showStatus(getCounts(creditAgreement2));
        showStatus(getCounts(creditAgreement3));

    }

    /**
     * С помощью адресной арифметики определяется количество вхождений
     * различных символов в потоке.
     */
    private static ArrayList<Integer> getCounts(String creditAgreement) {
        ArrayList<Integer> counts = new ArrayList<>((Collections.nCopies(4, 0)));

        IntStream intStream = creditAgreement.chars();
        intStream.forEach(x -> {
            if (x != 'C') counts.set(x - ZERO_CODE, counts.get(x - ZERO_CODE) + 1);
        });

       return counts;
    }


    private static void showStatus(ArrayList<Integer> counts) {
        /**
         * Кредитная история определяется как «Отрицательная»:
         * Если у заемщика более 3-х фактов выхода на просрочку с длительностью более 30 дней
         * Если у заемщика имеется просрочка более 50 дней.
         */
        if (counts.get(NP_MORE_THAN_30_DAYS) > 3 || counts.get(NP_MORE_THAN_50_DAYS) != 0) {
            System.out.println("Кредитная история «Отрицательная»");
        }
        /**
         * Кредитная история определяется как «Сомнительная»:
         * Если у заемщика более 1-го факта выхода на просрочку с длительностью более 30 дней
         * Если у заемщика более 3-х фактов выхода на просрочку с длительностью более 1 день
         */
        else if (counts.get(NP_MORE_THAN_30_DAYS) > 1 || counts.get(NP_MORE_THAN_1_DAY) > 3) {
            System.out.println("Кредитная история «Сомнительная»");
        }
        /**
         * Кредитная история определяется как «Положительная»:
         * Если у заемщика не более 3-х фактов выхода на просрочку с длительностью более 1 день
         * Если отсутствует информация о выходе на просрочку.
         */
        else if (counts.get(NP_MORE_THAN_1_DAY) + counts.get(NP_MORE_THAN_30_DAYS) + counts.get(NP_MORE_THAN_50_DAYS) == 0 ||
                counts.get(NP_MORE_THAN_1_DAY) <= 3) {
            System.out.println("Кредитная история «Положительная»");
        }
    }
}
