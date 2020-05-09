package cn.yan_wm.myalbum.service.provider.backstage.service.impl;

import cn.yan_wm.myalbum.commons.domain.SysPermission;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysPermissionExtend;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.service.provider.backstage.mapper.SysPermissionMapper;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: SysPermissionService的实现类
 * @author: yan_zt
 * @create: 2020-01-09 14:58
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermission> implements SysPermissionService {
    @Autowired
    private SysPermissionMapper permissionMapper;

    @Override
    public Mapper<SysPermission> getMapper() {
        return permissionMapper;
    }
    @Override
    public List<SysPermissionExtend> getSysPermissionTree() {
        List<SysPermissionExtend> list = permissionMapper.findAll();
        List<SysPermissionExtend> listToTree = listToTree(list);
        return listToTree;
    }

    @Override
    public SysPermission save(SysPermission sysPermission) {
        //result 表示受影响的行数
        int result = 0;
//        Date currentDate = new Date();
        //创建
        if (sysPermission.getId()==null){
            result = permissionMapper.insertUseGeneratedKeys(sysPermission);
        }
        //更新
        else {
            result = permissionMapper.updateByPrimaryKey(sysPermission);
        }
        //保存数据成功
        if (result>0){
            return sysPermission;
        }
        //保存失败
        return null;
    }

    @Override
    public int deleteById(Integer id) {
        SysPermission sysPermission = new SysPermission();
        sysPermission.setId(id);
        int i = permissionMapper.delete(sysPermission);
        return i;
    }

//    @Cacheable(cacheNames = {"permission"})
    @Override
    public List<SysPermission> getSysPermissionByZuulPrefix(String zuulPrefix,String principal) {
        List<SysPermission> permissionList = permissionMapper.getSysPermissionByZuulPrefix(zuulPrefix,principal);
        return permissionList;
    }

    @Override
    public String note(Integer root) {
        List<SysPermissionExtend> permissionList = permissionMapper.findAll();
        StringBuilder childIds = new StringBuilder();
        childIds.append(root + ",");

        this.getChildIds(root, childIds, permissionList);
        return childIds.toString().substring(0, childIds.length()-1);
    }

    private void getChildIds(Integer id, StringBuilder childIds, List<SysPermissionExtend> TaxBureauList) {
        for (SysPermissionExtend bureau : TaxBureauList) {
            //过滤父节点为空的数据
            if (bureau.getParentId()==null){
                continue;
            }
            // 判断是否存在子节点
            if (bureau.getParentId() == id) {
                childIds.append(bureau.getId()+",");
                // 递归遍历下一级
                getChildIds(bureau.getId(), childIds, TaxBureauList);
            }
        }
        return;
    }



    public static List<SysPermissionExtend> listToTree(List<SysPermissionExtend> list) {
        //用递归找子。
        List<SysPermissionExtend> treeList = new ArrayList<SysPermissionExtend>();
        for (SysPermissionExtend tree : list) {
            if (tree.getParentId() == 0) {
                treeList.add(findChildren(tree, list));
            }
        }
        return treeList;
    }

    private static SysPermissionExtend findChildren(SysPermissionExtend tree, List<SysPermissionExtend> list) {
        for (SysPermissionExtend node : list) {
            if (node.getParentId().equals(tree.getId())) {
                if (tree.getChildren() == null) {
                    tree.setChildren(new ArrayList<SysPermissionExtend>());
                }
                tree.getChildren().add(findChildren(node, list));
            }
        }
        return tree;
    }


}
