package cn.yan_wm.myalbum.commons.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @program: MyAlbum-Boot
 * @description: 时间日期工具类
 * @author: yan_zt
 * @create: 2020-01-10 12:35
 */
public class DateUtils {

    /**
     * 获取过去 n天的日期
     * @param n
     * @return
     */
    public String getday(String pattern, int n){
        Long l = System.currentTimeMillis() - (1000 * 60 * 60 * 24)*n;
        Date date = new Date(l);
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        String nyr = dateFormat.format(date);
        return nyr;
    }

    /**
     * 获取前第n个月的时间
     * @param n
     * @return
     */
    public String getMonth(int n){
        LocalDate today = LocalDate.now();
        today = today.minusMonths(n);
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM");
        String ny = formatters.format(today);
        return ny;
    }
}
