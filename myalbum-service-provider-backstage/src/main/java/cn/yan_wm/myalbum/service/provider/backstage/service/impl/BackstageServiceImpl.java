package cn.yan_wm.myalbum.service.provider.backstage.service.impl;

import cn.yan_wm.myalbum.commons.domain.IndexPageView;
import cn.yan_wm.myalbum.commons.domain.SysRole;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.BackstageIndexDto;
import cn.yan_wm.myalbum.commons.service.framework.base.BaseServiceImpl;
import cn.yan_wm.myalbum.commons.utils.DateUtils;
import cn.yan_wm.myalbum.commons.utils.MapperUtils;
import cn.yan_wm.myalbum.service.provider.backstage.mapper.IndexPageViewMapper;
import cn.yan_wm.myalbum.service.provider.backstage.mapper.SysRoleMapper;
import cn.yan_wm.myalbum.service.provider.backstage.mapper.SysUserMapper;
import cn.yan_wm.myalbum.service.provider.backstage.service.BackstageService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import java.util.*;

/**
 * @program: MyAlbum-Boot
 * @description: 后台首页Service实现层
 * @author: yan_zt
 * @create: 2020-01-10 11:24
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
public class BackstageServiceImpl extends BaseServiceImpl<IndexPageView> implements BackstageService {
    @Autowired
    private SysUserMapper sysUserExtendMapper;

    @Autowired
    private SysRoleMapper roleExtendMapper;

    @Autowired
    private IndexPageViewMapper indexPageViewExtendMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public Mapper<IndexPageView> getMapper() {
        return indexPageViewExtendMapper;
    }

    @Override
    public List<BackstageIndexDto> getNewUsersAWeek() {
        DateUtils dateUtils = new DateUtils();
        List<BackstageIndexDto> list = new ArrayList<BackstageIndexDto>(8);
        for (int i=7;i>=0; i--){
            BackstageIndexDto backstageIndexDto = sysUserExtendMapper.getNewUsers(i);
            backstageIndexDto.setTitle("新增用户量");
            backstageIndexDto.setTime(dateUtils.getday("yyyy-MM-dd",i));
            list.add(backstageIndexDto);
        }
        return list;
    }

    @Override
    public List<BackstageIndexDto> getNewUsersMonth(int n) {
        DateUtils dateUtils = new DateUtils();
        List<BackstageIndexDto> list = new ArrayList<BackstageIndexDto>(8);
        for (int i=n;i>=0; i--){
            BackstageIndexDto backstageIndexDto = sysUserExtendMapper.getNewUsersMonth(i);
            backstageIndexDto.setTitle("新增用户量");
            backstageIndexDto.setTime(dateUtils.getMonth(i));
            list.add(backstageIndexDto);
        }
        return list;
    }

    @Override
    public List<BackstageIndexDto> getVipDistribution() {
        int total = roleExtendMapper.getTotal();
        List<SysRole> vipNum = roleExtendMapper.findVipIds();
        List<BackstageIndexDto> list = new ArrayList<BackstageIndexDto>(16);
        for (int i=0; i<vipNum.size();i++){
            BackstageIndexDto backstageIndexDto = new BackstageIndexDto();
            backstageIndexDto.setTitle(vipNum.get(i).getName());
            backstageIndexDto.setNumber(roleExtendMapper.getVipNumByRoleId(vipNum.get(i).getId()));
            backstageIndexDto.setTotal(total);
            list.add(backstageIndexDto);
        }
        return list;
    }

    @Override
    public int addFrequency() {
        int i = indexPageViewExtendMapper.add();
        return i;
    }

    @Override
    public int updaFrequencyById(int id) {
        int i = indexPageViewExtendMapper.addFrequencyById(id);
        return i;
    }


    @Override
    public void sendIndexPageViewIdToMPQ(Long indexPageViewId) {
        Map<String,Long> map = new HashMap<>();
        map.put("indexPageViewId",indexPageViewId);
        try {
            String mapJson = MapperUtils.obj2json(map);
            rabbitTemplate.convertAndSend("exchange.direct","indexPage-frequency",mapJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<IndexPageView> getFrequencyByTime(String time) {
        List<IndexPageView> indexPageViewList = indexPageViewExtendMapper.findByTime(time);
        return indexPageViewList;
    }

    @Override
    public List<BackstageIndexDto> getIndexPageViewsAWeek() {
        DateUtils dateUtils = new DateUtils();
        List<BackstageIndexDto> list = new ArrayList<BackstageIndexDto>(8);
        for (int i=7;i>=0; i--){
            BackstageIndexDto backstageIndexDto = indexPageViewExtendMapper.getIndexPageViews(i);
            if (backstageIndexDto == null ){
                backstageIndexDto = new BackstageIndexDto();
                backstageIndexDto.setNumber(0);
            }
            backstageIndexDto.setTitle("访问量");
            backstageIndexDto.setTime(dateUtils.getday("yyyy-MM-dd",i));
            list.add(backstageIndexDto);
        }
        return list;
    }

    @Override
    public List<BackstageIndexDto> getIndexPageViewsMonth(int n) {
        DateUtils dateUtils = new DateUtils();
        List<BackstageIndexDto> list = new ArrayList<BackstageIndexDto>(8);
        for (int i=n;i>=0; i--){
            BackstageIndexDto backstageIndexDto = indexPageViewExtendMapper.getIndexPageViewsMonth(i);
            if (backstageIndexDto == null ){
                backstageIndexDto = new BackstageIndexDto();
                backstageIndexDto.setNumber(0);
            }
            backstageIndexDto.setTitle("访问量");
            backstageIndexDto.setTime(dateUtils.getMonth(i));
            list.add(backstageIndexDto);
        }
        return list;
    }


}
