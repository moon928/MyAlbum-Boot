package cn.yan_wm.myalbum.service.provider.user.mapper;

import cn.yan_wm.myalbum.commons.domain.TbUserInfo;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserInfoExtend;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;
/**
 * @program: MyAlbum-Boot
 * @description: 用户基本信息数据库操作mapper
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface TbUserInfoExtendMapper extends MyMapper<UserInfoExtend> {

    /**
     * 修改用户信息
     * @param userInfo
     * @return
     */
    int updateUserInfo(TbUserInfo userInfo);

    /**
     * 通过id查询用户
     * @param id
     * @return
     */
    UserInfoExtend findById(@Param("id") Long id);

    /**
     * 查找所有的用户
     * @return
     */
    List<UserInfoExtend> findAll();

    /**
     * 通过昵称模糊查询用户信息
     * @param nickName
     * @return
     */
    List<UserInfoExtend> findByNickName(@Param("nickName") String nickName);
}