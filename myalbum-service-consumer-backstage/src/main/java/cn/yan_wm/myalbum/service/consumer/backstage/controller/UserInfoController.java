package cn.yan_wm.myalbum.service.consumer.backstage.controller;

import cn.yan_wm.myalbum.commons.domain.SysUser;
import cn.yan_wm.myalbum.commons.domain.TbUserInfo;
import cn.yan_wm.myalbum.commons.domainExtend.backstage.SysUserExtend;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserInfoExtend;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseResult;
import cn.yan_wm.myalbum.commons.web.AbstractBaseController;
import cn.yan_wm.myalbum.service.consumer.backstage.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserInfoController extends AbstractBaseController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "根据用户id查用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "path", dataType = "Long")
    })
    @GetMapping(value = "getById/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AbstractBaseResult getUserInfoById(@PathVariable("id") Long id){
        SysUserExtend sysUserExtend = userService.findById(id);
        if (sysUserExtend!=null)
            if (sysUserExtend.getId() == -1L){
                return error(HttpStatus.SERVICE_UNAVAILABLE.value(),"网络不给力啊！","MYALBUM-SERVICE-PROVIDER-BACKSTAGE 服务熔断");
            }
        return  success(request.getRequestURI(),sysUserExtend);
    }

    @ApiOperation(value = "根据用户名查用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "path", dataType = "String")
    })
    @GetMapping(value = "getByUsername/{username}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AbstractBaseResult getUserInfoByUsername(@PathVariable("username") String username){
        SysUserExtend sysUserExtend = userService.FindByUsername(username);
        return  success(request.getRequestURI(),sysUserExtend);
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "num", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "条数", required = true, paramType = "path", dataType = "int")
    }) //多个请求参数
    @GetMapping(value = "page/{num}/{size}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AbstractBaseResult page(
            @PathVariable int num,
            @PathVariable int size
    ){
        PageInfo<SysUser> page = userService.page(num,size);
        if(page != null){
            return success(request.getRequestURI(),page.getTotal(),page.getPages(),page.getPageNum(),page.getPageSize(),page.getList());
        }else{
            return error(HttpStatus.SERVICE_UNAVAILABLE.value(),"Provider-Backstage Dwon !",null);
        }
    }
}
