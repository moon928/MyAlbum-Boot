package cn.yan_wm.myalbum.service.provider.user.service;

import cn.yan_wm.myalbum.commons.domain.TbUserInfo;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserInfoExtend;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserInfoService<T extends AbstractBaseDomain> extends BaseCrudService<T> {

    int updateUserInfo(TbUserInfo userInfo);

    UserInfoExtend getUserInfoById(Long id);

    List<UserInfoExtend> getAll();

    List<UserInfoExtend> getByNickName(String nickName);

    default PageInfo<T> UserInfoExtendPage(int pageNum, int pageSize) {
        return null;
    }

    default PageInfo<T> getByNickNamePage(String nickName) {
        return null;
    }
}
