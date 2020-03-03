package cn.yan_wm.myalbum.service.register.service;
/**
 * @program: MyAlbum-Boot
 * @description:  密码加密
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface MyPasswordEncoder {
    /**
     * 密码加密
     * @param password
     * @return
     */
    public String encode(String password);
}
