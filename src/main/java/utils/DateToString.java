package utils;

import lombok.SneakyThrows;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToString<T> {
    public String convert(T date){
        Format formatter = new SimpleDateFormat("yyyy/MM/dd");
        String str = formatter.format(date);
        return str;
    }

    public String convertDetail(T date){
        Format formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String str = formatter.format(date);
        return str;
    }

    @SneakyThrows
    public Date reConvert(T str){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        //String str = formatter.format(date);
        return formatter.parse((String) str);
    }
}

