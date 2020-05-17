package cn.yan_wm.myalbum.service.provider.user.service;

import cn.yan_wm.myalbum.commons.domain.TbUserAttention;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysRoleExtend;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserAttentionExtend;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserFriendExtend;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;
import com.github.pagehelper.PageInfo;
import tk.mybatis.page.Page;

import java.util.List;
/**
 * @program: MyAlbum-Boot
 * @description: 用户关注管理Service
 * @author: yan_zt
 * @create: 2020-03-03 13:57
 */
public interface UserAttentionService{

    /**
     * 添加关注
     * @param userAttention
     * @return
     */
    int addAttention(TbUserAttention userAttention);

    /**
     * 删除关注
     * @param userId
     * @param attentionId
     * @return
     */
    int deleteAttention(Integer userId, Integer attentionId);

    /**
     * 更新关注人备注
     * @param userId
     * @param attentionId
     * @param note
     * @return
     */
    int updateAttentionNote(Integer userId, Integer attentionId, String note);

    /**
     * 获取关注人信息
     * @param userId
     * @param attentionId
     * @return
     */
    UserAttentionExtend getByAttentionId(Integer userId, Integer attentionId);

    /**
     * 获取用户关注人员列表
     * @param userId
     * @return
     */
    List<UserAttentionExtend> getByUserId(Integer userId);

    /**
     * 分页获取用户关注人列表
     * @param userId
     * @param page
     * @return
     */
    DataSet<UserAttentionExtend> page(Integer userId,Page page);


    /**
     * 判断是否是粉丝（是否是我关注的）
     * @param userId
     * @param othersId
     * @return
     */
    int getRelationshipWithOthers(Integer userId, Integer othersId);
}
