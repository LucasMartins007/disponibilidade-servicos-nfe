/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.selecaoviasoft.statusnfe.domain.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author lucas
 */
public class DateUtil {
    
    public static final String DATE_PATTERN = "dd.MM.yyyy HH:mm:ss";

    public static Date toDate(String dateString, String pattern) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat(pattern);
        return formato.parse(dateString);
    }
}
