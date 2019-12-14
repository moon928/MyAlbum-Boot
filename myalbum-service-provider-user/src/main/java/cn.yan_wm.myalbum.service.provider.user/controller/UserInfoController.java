package cn.yan_wm.myalbum.service.provider.user.controller;

import cn.yan_wm.myalbum.commons.domain.TbUserInfo;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserInfoExtend;
import cn.yan_wm.myalbum.service.provider.user.service.UserInfoService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value = "修改用户信息")
    @PutMapping("update")
    public int update(@ApiParam(name = "userInfo",value = "用户信息Model") TbUserInfo userInfo){
        return userInfoService.updateUserInfo(userInfo);
    }

    @ApiOperation(value = "通过id查单个用户", notes = "id为用户id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "path", dataType = "Long")
    }) //多个请求参数
    @GetMapping(value = "getById/{id}")
    public TbUserInfo getUserInfoById(@PathVariable Long id){
        TbUserInfo userInfo = userInfoService.getUserInfoById(id);
        return userInfo;
    }

    @ApiOperation(value = "通过昵称模糊查用户", notes = "NickName为用户昵称")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nickName", value = "用户昵称", required = true, paramType = "path", dataType = "String")
    }) //多个请求参数
    @GetMapping(value = "getByNickName/{nickName}")
    public PageInfo<UserInfoExtend> getByNickName(@PathVariable String nickName){
        System.out.println(nickName);
        PageInfo pageInfo = userInfoService.getByNickNamePage(nickName);
        return pageInfo;
    }

    @ApiOperation(value = "分页查寻UserInfo", notes = "num为页数，size为条数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "条数", required = true, paramType = "path", dataType = "int")
    }) //多个请求参数
    @GetMapping(value = "page/{num}/{size}")
    public PageInfo<UserInfoExtend> page(
            @PathVariable int num,
            @PathVariable int size
    ){
        return userInfoService.UserInfoExtendPage(num,size);
    }
}