package cn.yan_wm.myalbum.service.provider.backstage.service;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;

public interface SysUserService<T extends AbstractBaseDomain> extends BaseCrudService<T> {
    /**
     * updatePwd
     * @param userId
     * @param password
     * @return
     */
    int updatePwd(Long userId,String password);

    /**
     * updateStatus
     * @param userId
     * @param status
     * @return
     */
    int updateStatus(Long userId,int status);

    /**
     * Describe this class
     *
     * @param userId
     * @param username
     * @exception
     * @return
     * @author Yzn_zt
     * @date 2020/1/9 14:12
     */
    int updateUsername(Long userId,String username);
}
