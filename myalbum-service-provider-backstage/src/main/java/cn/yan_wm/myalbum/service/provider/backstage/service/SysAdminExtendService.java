package cn.yan_wm.myalbum.service.provider.backstage.service;

import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysAdminExtend;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SysAdminExtendService<T extends AbstractBaseDomain> extends BaseCrudService<T> {
    SysAdminExtend getByUsername(String username);

    List<SysAdminExtend> getAll();

    default PageInfo<T> adminExtendPage(int pageNum, int pageSize) {
        return null;
    }
}
