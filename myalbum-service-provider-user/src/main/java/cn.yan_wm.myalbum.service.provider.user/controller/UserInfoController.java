package cn.yan_wm.myalbum.service.provider.user.controller;

import cn.yan_wm.myalbum.commons.domain.TbUserInfo;
import cn.yan_wm.myalbum.commons.domainExtend.user.UserInfoExtend;
import cn.yan_wm.myalbum.commons.dto.ReturnResult;
import cn.yan_wm.myalbum.commons.model.DataSet;
import cn.yan_wm.myalbum.service.provider.user.service.UserInfoService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.page.Page;

/**
 * @program: MyAlbum-Boot
 * @description: 用户信息
 * @author: yan_zt
 * @create: 2020-03-02 14:40
 */
@Slf4j
@RestController
@RequestMapping(value = "user",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "用户基本信息管理")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value = "修改用户信息")
    @PutMapping("update")
    public ReturnResult update(@ApiParam(name = "userInfo",value = "用户信息Model") TbUserInfo userInfo){
        try{
            int i = userInfoService.updateUserInfo(userInfo);
            if (i>0){
                return ReturnResult.success("修改成功");
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("修改失败");
    }

    @ApiOperation(value = "通过id查单个用户", notes = "id为用户id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "path", dataType = "Long")
    })
    @GetMapping(value = "getById/{id}")
    public ReturnResult<TbUserInfo> getUserInfoById(@PathVariable Long id){
        try{
            TbUserInfo userInfo = userInfoService.getUserInfoById(id);
            if (userInfo != null){
                return ReturnResult.success(userInfo);
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("未找到相应数据");
    }

    @ApiOperation(value = "通过昵称模糊查用户", notes = "NickName为用户昵称")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nickName", value = "用户昵称", required = true, paramType = "path", dataType = "String")
    })
    @GetMapping(value = "getByNickName/{nickName}")
    public ReturnResult<DataSet<UserInfoExtend>> getByNickName(@PathVariable String nickName, @ApiParam(name = "分页模型") @ModelAttribute Page page){
        try{
            DataSet<UserInfoExtend> data = userInfoService.getByNickNamePage(nickName, page);
            return ReturnResult.success(data);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("未找到相应数据");
    }

    @ApiOperation(value = "分页查寻UserInfo", notes = "num为页数，size为条数")
    @GetMapping(value = "page")
    public ReturnResult<DataSet<UserInfoExtend>> page( @ApiParam(name = "分页模型") @ModelAttribute Page page){
        try{
            DataSet<UserInfoExtend> data = userInfoService.getUserInfoExtendPage(page);
            return ReturnResult.success(data);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return ReturnResult.failure("为找到相应数据");
    }
}