package cn.yan_wm.myalbum.service.tools.service.impl;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import cn.yan_wm.myalbum.service.tools.server.Server;
import cn.yan_wm.myalbum.service.tools.service.logService;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class LogServiceImpl implements logService {

    Session ssh = null;
    String hostname;
    String username;
    String password;
    @Override
    public String monitoringLog(Server server, String servicePath) {
        this.hostname=server.host();
        this.username=server.username();
        this.password=server.password();
        int port = 22;
        Connection conn = new Connection(hostname,port);
        try
        {
            //连接到主机
            conn.connect();
            //使用用户名和密码校验
            boolean isconn = conn.authenticateWithPassword(username, password);
            if (!isconn)
            {
                System.out.println("用户名称或者是密码不正确");
                return "用户名称或者是密码不正确";
            }
            else
            {
                return read(conn,servicePath);
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

    public String read(Connection conn,String servicePath) throws IOException {
        System.out.println("已经连接OK");
        SCPClient clt = conn.createSCPClient();
        ssh = conn.openSession();
        ssh.execCommand("find "+servicePath+" -name '*.out' ");
        InputStream is = new StreamGobbler(ssh.getStdout());
        BufferedReader brs = new BufferedReader(new InputStreamReader(is));
        while (true)
        {
            String line = brs.readLine();
            if (line == null)
            {
                break;
            }
            System.out.println(line);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            clt.get(line,baos);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray()); // 读取文件字节流
            InputStreamReader input = new InputStreamReader(bais);
            BufferedReader bf = new BufferedReader(input);
            String readline = null;
            StringBuilder sb = new StringBuilder();
            while((readline=bf.readLine()) != null){
                sb.append(readline);
            }
            return sb.toString();
        }
        return null;
    }
}
