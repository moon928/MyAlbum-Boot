package cn.yan_wm.myalbum.service.provider.album.service.impl;

import cn.yan_wm.myalbum.commons.domain.TbImage;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserAttentionExtend;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.service.provider.album.mapper.TbImageMapper;
import cn.yan_wm.myalbum.service.provider.album.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.PrimitiveIterator;

/**
 * @program: MyAlbum-Boot
 * @description: TestServiceImpl
 * @author: yan_zt
 * @create: 2020-03-03 14:22
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = true)
public class TestServiceImpl extends BaseServiceImpl<TbImage> implements  TestService {

    @Autowired
    private TbImageMapper imageMapper;

    @Override
    public Mapper<TbImage> getMapper() {
        return imageMapper;
    }

    @Override
    public List<TbImage> all() {
//        Example example = new Example(TbImage.class);
//        Example.Criteria criteria = example.createCriteria();
        return imageMapper.all();
    }

}
