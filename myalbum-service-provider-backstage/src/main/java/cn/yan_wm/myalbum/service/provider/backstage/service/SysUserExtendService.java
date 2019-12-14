package cn.yan_wm.myalbum.service.provider.backstage.service;

import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import cn.yan_wm.myalbum.commons.service.BaseCrudService;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SysUserExtendService<T extends AbstractBaseDomain> extends BaseCrudService<T> {
    SysUserExtend getByUsername(String username);

    SysUserExtend getById(Long id);

    List<SysUserExtend> getAll();

    default PageInfo<T> sysUserExtendPage(int pageNum,int pageSize) {
        return null;
    }
}
