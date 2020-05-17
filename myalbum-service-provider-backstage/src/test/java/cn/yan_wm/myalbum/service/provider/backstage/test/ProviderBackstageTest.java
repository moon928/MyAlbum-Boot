package cn.yan_wm.myalbum.service.provider.backstage.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: MyAlbum-Boot
 * @description: 后台服务提供者测试类
 * @author: yan_zt
 * @create: 2020-01-10 09:11
 */
@SpringBootTest
//@RunWith(SpringRunner.class)
public class ProviderBackstageTest {
    @Test
    public void test(){

        Long l = System.currentTimeMillis() - (1000 * 60 * 60 * 24)*11;
        Date date = new Date(l);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nyr = dateFormat.format(date);
        System.out.println(nyr);
    }

    @Test
    public void test02(){
        LocalDate today = LocalDate.now();
//        today = today.minusMonths(4);
        today = today.minusDays(2);
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String nyr = formatters.format(today);
        System.out.println(nyr);
    }

    @Test
    public void testTime() throws ParseException {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = dateformat.parse("2020-05-12");

        //创建Calendar实例
        Calendar cal = Calendar.getInstance();

        cal.setTime(now);   //设置当前时间
        cal.add(Calendar.DATE, -365);  //在当前时间基础上加一年

        System.out.println(cal.getTime());

        cal.setTime(now);   //设置当前时间
        cal.add(Calendar.YEAR, -1);  //在当前时间基础上加一年

        System.out.println(cal.getTime());
    }
}
