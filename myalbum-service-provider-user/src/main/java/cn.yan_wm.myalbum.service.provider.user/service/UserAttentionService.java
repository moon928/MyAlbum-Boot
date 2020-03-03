package cn.yan_wm.myalbum.service.provider.user.service;

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
     * @param userId
     * @param attentionId
     * @return
     */
    int addAttention(Long userId, Long attentionId);

    /**
     * 删除关注
     * @param userId
     * @param attentionId
     * @return
     */
    int deleteAttention(Long userId, Long attentionId);

    /**
     * 更新关注人备注
     * @param userId
     * @param attentionId
     * @param note
     * @return
     */
    int updateAttentionNote(Long userId, Long attentionId, String note);

    /**
     * 获取关注人信息
     * @param userId
     * @param attentionId
     * @return
     */
    UserAttentionExtend getByAttentionId(Long userId, Long attentionId);

    /**
     * 获取用户关注人员列表
     * @param userId
     * @return
     */
    List<UserAttentionExtend> getByUserId(Long userId);

    /**
     * 分页获取用户关注人列表
     * @param userId
     * @param page
     * @return
     */
    DataSet<UserAttentionExtend> page(Long userId,Page page);
}
