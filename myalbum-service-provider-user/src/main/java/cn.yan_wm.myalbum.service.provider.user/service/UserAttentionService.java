package cn.yan_wm.myalbum.service.provider.user.service;

import cn.yan_wm.myalbum.commons.domainExtend.user.UserAttentionExtend;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserAttentionService<T extends AbstractBaseDomain> extends BaseCrudService<T>{

    int addAttention(Long userId, Long attentionId);

    int deleteAttention(Long userId, Long attentionId);

    int updateAttentionNote(Long userId, Long attentionId, String note);

    UserAttentionExtend getByAttentionId(Long userId, Long attentionId);

    List<UserAttentionExtend> getByUserId(Long userId);

    default PageInfo<T> UserAttentionExtendPage(Long userId, int pageNum, int pageSize) {
        return null;
    }
}
