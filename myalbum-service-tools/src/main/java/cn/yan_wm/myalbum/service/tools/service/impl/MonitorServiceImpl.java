package cn.yan_wm.myalbum.service.tools.service.impl;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import cn.yan_wm.myalbum.commons.domain.Cpu;
import cn.yan_wm.myalbum.commons.domain.Disk;
import cn.yan_wm.myalbum.commons.domain.Ram;
import cn.yan_wm.myalbum.service.tools.job.WebSocketJob;
import cn.yan_wm.myalbum.service.tools.server.Server;
import cn.yan_wm.myalbum.service.tools.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class MonitorServiceImpl implements MonitorService {

    @Autowired
    private WebSocketJob myJob;

    Session ssh = null;
    String hostname;
    String username;
    String password;

    public Connection connection(Server server){
        this.hostname=server.host();
        this.username=server.username();
        this.password=server.password();
        int port = 22;
        Connection conn = new Connection(hostname,port);
        return conn;
    }

    @Override
    public Disk monitorDisk(Server server) {
        Connection conn = connection(server);
        try
        {
            //连接到主机
            conn.connect();
            //使用用户名和密码校验
            boolean isconn = conn.authenticateWithPassword(username, password);
            if (!isconn)
            {
                System.out.println("用户名称或者是密码不正确");
                return null;
            }
            else
            {
                String statement = "df -h";
                String str = read(conn, statement);
                return diskSplit(str);
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            //连接的Session和Connection对象都需要关闭
            if(ssh!=null)
            {
                ssh.close();
            }
            if(conn!=null)
            {
                conn.close();
            }
        }
        return null;
    }

    @Override
    public String monitorCpu(String num,Server server) {
        Connection conn = connection(server);
        try
        {
            //连接到主机
            conn.connect();
            //使用用户名和密码校验
            boolean isconn = conn.authenticateWithPassword(username, password);
            if (!isconn)
            {
                System.out.println("用户名称或者是密码不正确");
                return null;
            }
            else
            {
                readCpu(num,conn);
                return null;
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            //连接的Session和Connection对象都需要关闭
            if(ssh!=null)
            {
                ssh.close();
            }
            if(conn!=null)
            {
                conn.close();
            }
        }
        return null;
    }

    @Override
    public Ram monitorRam(Server server) {
        Connection conn = connection(server);
        try
        {
            //连接到主机
            conn.connect();
            //使用用户名和密码校验
            boolean isconn = conn.authenticateWithPassword(username, password);
            if (!isconn)
            {
                System.out.println("用户名称或者是密码不正确");
                return null;
            }
            else
            {
                String statement = "free -m";
                String str = read(conn, statement);
                return ramSplit(str);
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            //连接的Session和Connection对象都需要关闭
            if(ssh!=null)
            {
                ssh.close();
            }
            if(conn!=null)
            {
                conn.close();
            }
        }
        return null;
    }

    public String readCpu(String num,Connection conn) throws IOException {
        System.out.println("已经连接OK");
        ssh = conn.openSession();
        ssh.execCommand("iostat -c 1");
        InputStream is = new StreamGobbler(ssh.getStdout());
        BufferedReader brs = new BufferedReader(new InputStreamReader(is));
        String str = "";
        int i=1;
        while (true)
        {
            String line = brs.readLine();
            if (line == null || i>400)
            {
                break;
            }
            if(i>3 && (i-1)%3==0){
                Cpu cpu = cpuSplit(line);
                //websocket实时发送cpu监控
                myJob.sendCpuInfo(num,cpu);
            }
            i++;
        }
        return null;
    }

    public String read(Connection conn,String statement) throws IOException {
        System.out.println("已经连接OK");
        ssh = conn.openSession();
        ssh.execCommand(statement);
        InputStream is = new StreamGobbler(ssh.getStdout());
        BufferedReader brs = new BufferedReader(new InputStreamReader(is));
        String str = "";
        int i=1;
        while (true)
        {
            String line = brs.readLine();
            if (line == null)
            {
                break;
            }
            str += line;
            i++;
        }
        return str;
    }

    public Disk diskSplit(String str){
        String [] arr = str.split("\\s+");
        int i=0;
        Disk disk = new Disk();
        for(String ss : arr){
            if(i==6){
                ss = ss.substring(2, ss.length()-0);
                disk.setFilesystem(ss);
            }else if(i==7){
                ss = ss.substring(0, ss.length() - 1);
                disk.setSize(Double.parseDouble(ss));
            }else if(i==8){
                ss = ss.substring(0, ss.length() - 1);
                disk.setUsed(Double.parseDouble(ss));
            }else if(i==9){
                ss = ss.substring(0, ss.length() - 1);
                disk.setAvail(Double.parseDouble(ss));
            }else if(i==10){
                ss = ss.substring(0, ss.length() - 1);
                disk.setUse(Double.parseDouble(ss));
            }
            i++;
        }
        return disk;
    }

    public Cpu cpuSplit(String str){
        String [] arr = str.split("\\s+");
        int i=0;
        Cpu cpu = new Cpu();
        for(String ss : arr){
//            System.out.println(ss+"--"+i);
            if(i==1){
                cpu.setUser(Float.parseFloat(ss));
            }else if(i==2){
                cpu.setNice(Float.parseFloat(ss));
            }else if(i==3){
                cpu.setSystem(Float.parseFloat(ss));
            }else if(i==4){
                cpu.setIowait(Float.parseFloat(ss));
            }else if(i==5){
                cpu.setSteal(Float.parseFloat(ss));
            }else if(i==6){
                cpu.setIdle(Float.parseFloat(ss));
            }
            i++;
        }
        return cpu;
    }

    public Ram ramSplit(String str){
        String [] arr = str.split("\\s+");
        int i=0;
        Ram ram = new Ram();
        for(String ss : arr){
//            System.out.println(ss+"--"+i);
            if(i==7){
                ram.setTotal(Double.parseDouble(ss));
            }else if(i==8){
                ram.setUserd(Double.parseDouble(ss));
            } else if(i==9){
                ram.setFree(Double.parseDouble(ss));
            }else if(i==10){
                ram.setShared(Double.parseDouble(ss));
            }else if(i==11){
                ram.setBuff_cache(Double.parseDouble(ss));
            }else if(i==12){
                ss = ss.substring(0, ss.length()-5);
                ram.setAvailable(Double.parseDouble(ss));
            }
            i++;
        }
        return ram;
    }


}
