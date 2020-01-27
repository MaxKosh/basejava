package com.maxkosh.webapp;

import com.maxkosh.webapp.util.DateUtil;

import java.time.LocalDate;
import java.time.LocalTime;

public class MainDate {
    public static void main(String[] args) {
        LocalDate ld = LocalDate.now();
        System.out.println(ld);
        LocalTime lt = LocalTime.now();
        System.out.println(lt);

        String dateNOW = DateUtil.NOW.toString();

        LocalDate dateFromString = LocalDate.parse(dateNOW);

        System.out.println(dateFromString);
    }
}
