package com.service.gtc.utilities;

public class DateStringConverter {
    public static String convertDbDateToUiDate(String dbDate){
        String[] strings = dbDate.split("-");
        return strings[2] + "/" + strings[1] + "/" + strings[0];
    }
}
