package cn.yan_wm.myalbum.service.provider.backstage.controller;

import cn.yan_wm.myalbum.commons.domain.SysAdmin;
import cn.yan_wm.myalbum.commons.domain.SysAdminRole;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysAdminExtend;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysAdminRoleService;
import cn.yan_wm.myalbum.service.provider.backstage.service.SysAdminService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.page.Page;

/**
 * @program: MyAlbum-Boot
 * @description: 管理员信息管理
 * @author: yan_zt
 * @create: 2020-01-07 19:54
 */

@Slf4j
@RestController
@RequestMapping("/admin")
@Api(tags = "管理员信息管理",hidden = true)
public class SysAdminController {
    @Autowired
    private SysAdminService adminService;

    @Autowired
    private SysAdminRoleService adminRoleService;

    @ApiOperation(value = "校验管理员账号是否存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "path", dataType = "String")
    })
    @GetMapping(value = "unique/{username}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<Boolean> uniqueUsername(@PathVariable("username") String username){
        boolean b = false;
        try{
            b = adminService.unique("username", username);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return ReturnResult.failure("/admin/unique/{username} ===== 异常");
        }
        return ReturnResult.success(b);
    }

    @ApiOperation(value = "添加管理员账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "String")
    })
    @PostMapping(value = "add",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<SysAdmin> add(@ApiParam(name = "sysAdmin",value = "SysAdmin Model") @RequestBody SysAdmin sysAdmin, @RequestParam("passwprd") String password){
        sysAdmin.setPassword(password);
        SysAdmin admin = (SysAdmin) adminService.save(sysAdmin);
        if (admin != null){
            SysAdminRole adminRole = new SysAdminRole();
            adminRole.setAdminId(admin.getId());
            adminRole.setRoleId(10);
            int i = adminRoleService.insert(admin.getId(), 10);
            return ReturnResult.success(admin);
        }else {
            return ReturnResult.failure();
        }
    }

    @ApiOperation(value = "删除管理员账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "管理员id", required = true, paramType = "path", dataType = "Long")
    })
    @DeleteMapping(value = "delete/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int delete(@PathVariable("id") Long id){
        return 0;
    }


    @ApiOperation(value = "分页查寻管理员", notes = "num为页数，size为条数")
    @GetMapping(value = "page",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReturnResult<DataSet<SysAdminExtend>> page(@ApiParam(name = "分页模型") @ModelAttribute Page page){
        DataSet<SysAdminExtend> data = adminService.page(page);
        return ReturnResult.success(data);
    }

    @ApiOperation(value = "根据用户名查管理员账号信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户账号", required = true, paramType = "path", dataType = "String")
    })
    @GetMapping(value = "findByUsername/{username}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SysAdminExtend findByUsername(@PathVariable("username") String username) throws Exception {
        SysAdminExtend adminExtend = adminService.getByUsername(username);
        if(adminExtend == null){
            return null;
        }else{
            return adminExtend;
        }
    }
}
