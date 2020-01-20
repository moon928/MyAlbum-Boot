package cn.yan_wm.myalbum.service.tools.server;

public enum Server {
    /**服务器一*/
    SERVER01("47.106.199.167","root","19980502an"),
    /**服务器二*/
    SERVER02("39.105.137.236","root","Yan980518"),
    /**服务器三*/
    SERVER03("118.89.190.234","root","@nyQB_5m");
    /**地址*/
    private final String host;
    /**用户名*/
    private final String username;
    /**密码*/
    private final String password;
    private Server(String host, String username, String password){
        this.host = host;
        this.username = username;
        this.password = password;
    }

    public String host() {
        return this.host;
    }

    public String username() {
        return this.username;
    }

    public String password() {
        return this.password;
    }

}
