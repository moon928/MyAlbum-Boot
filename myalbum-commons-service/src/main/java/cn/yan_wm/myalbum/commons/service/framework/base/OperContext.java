package cn.yan_wm.myalbum.commons.service.framework.base;

import java.io.Serializable;

/**
 * @program: MyAlbum-Boot
 * @description: 操作上下文
 * @author: yan_zt
 * @create: 2020-01-14 11:34
 */
public class OperContext implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final Long SYSTEM_USER_ID = 0L;
    /** 系统操作上下文. */
    private static OperContext systemOperContext;
    static {
        systemOperContext = new OperContext();
        systemOperContext.setOperUserId(SYSTEM_USER_ID);
    }

    public static OperContext getSystemOperContext() {
        return systemOperContext;
    }

    /**
     * 操作的用户ID.
     */
    private Long operUserId;
    /**
     * token
     */
    private String token;

    public Long getOperUserId() {
        return operUserId;
    }

    public void setOperUserId(Long operUserId) {
        this.operUserId = operUserId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
