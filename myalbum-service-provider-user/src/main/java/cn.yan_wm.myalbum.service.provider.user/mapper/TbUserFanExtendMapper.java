package cn.yan_wm.myalbum.service.provider.user.mapper;

import cn.yan_wm.myalbum.commons.domainExtend.user.UserFanExtend;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;
/**
 * @program: MyAlbum-Boot
 * @description: 用户粉丝数据库操作mapper
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface TbUserFanExtendMapper extends MyMapper<UserFanExtend> {
    /**
     * 添加粉丝
     * @param userId
     * @param fanId
     * @return
     */
    int addFan(@Param("userId") Integer userId,@Param("fanId") Integer fanId);

    /**
     * 删除粉丝
     * @param userId
     * @param fanId
     * @return
     */
    int deleteFan(@Param("userId") Integer userId,@Param("fanId") Integer fanId);

    /**
     * 修改粉丝备注
     * @param userId
     * @param fanId
     * @param note
     * @return
     */
    int updateFanNote(@Param("userId") Integer userId,@Param("fanId") Integer fanId,@Param("note") String note);

    /**
     * 通过粉丝id查看粉丝信息
     * @param userId
     * @param fanId
     * @return
     */
    UserFanExtend findFanByFanId(@Param("userId") Integer userId,@Param("fanId") Integer fanId);

    /**
     * 通过用户id查询所有的粉丝
     * @param userId
     * @return
     */
    List<UserFanExtend> findAll(@Param("userId") Integer userId);
}