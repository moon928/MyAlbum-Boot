package cn.yan_wm.myalbum.service.provider.user.service;

import cn.yan_wm.myalbum.commons.domainExtend.user.UserFanExtend;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserFanService<T extends AbstractBaseDomain> extends BaseCrudService<T>{

    int addFan(Long userId, Long fanId);

    int deleteFan(Long userId, Long fanId);

    int updateFanNote(Long userId, Long fanId, String note);

    UserFanExtend getByFanId(Long userId, Long fanId);

    List<UserFanExtend> getByUserId(Long userId);

    default PageInfo<T> UserFanExtendPage(Long userId, int pageNum, int pageSize) {
        return null;
    }
}
