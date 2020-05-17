package cn.yan_wm.myalbum.service.provider.user.service;

import cn.yan_wm.myalbum.commons.domain.TbUserInfo;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserInfoExtend;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;
import com.github.pagehelper.PageInfo;
import tk.mybatis.page.Page;

import java.util.List;
/**
 * @program: MyAlbum-Boot
 * @description: 用户基本信息管理Service
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface UserInfoService{

    /**
     * 修改用户基本信息
     * @param userInfo
     * @return
     */
    int updateUserInfo(TbUserInfo userInfo);

    /**
     * 通过id查看用户基本信息
     * @param id
     * @return
     */
    UserInfoExtend getUserInfoById(Long id);

    /**
     * 通过用户名获取用户信息
     * @param username
     * @return
     */
    UserInfoExtend getUserInfoByUsername(String username);

    /**
     * 查询所有用户信息
     * @return
     */
    List<UserInfoExtend> getAll();

    /**
     * 通过昵称模糊查询所有用户信息
     * @param nickName
     * @return
     */
    List<UserInfoExtend> getByNickName(String nickName);

    /**
     * 通过昵称模糊分页查询用户信息
     * @param nickName
     * @param page
     * @return
     */
    DataSet<UserInfoExtend> getByNickNamePage(String nickName, Page page);

    /**
     *分页查询用户信息
     * @param page
     * @return
     */
    DataSet<UserInfoExtend> getUserInfoExtendPage(Page page);
}
