package cn.yan_wm.myalbum.service.tools.server;

public enum Server {
    
    SERVER01("47.106.199.167","root","19980502an"),

    SERVER02("39.105.137.236","root","Yan980518"),

    SERVER03("118.89.190.234","root","@nyQB_5m");

    private final String host;
    private final String username;
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
