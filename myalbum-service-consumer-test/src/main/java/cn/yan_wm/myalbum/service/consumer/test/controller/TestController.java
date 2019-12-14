package cn.yan_wm.myalbum.service.consumer.test.controller;

import cn.yan_wm.myalbum.commons.domain.TbUserInfo;
import cn.yan_wm.myalbum.commons.dto.AbstractBaseResult;
import cn.yan_wm.myalbum.commons.utils.MapperUtils;
import cn.yan_wm.myalbum.commons.web.AbstractBaseController;
import cn.yan_wm.myalbum.service.consumer.test.service.TestService;
import com.fasterxml.jackson.databind.JavaType;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController extends AbstractBaseController<TbUserInfo> {
    @Autowired
    private TestService testService;
    @GetMapping("page/{num}/{size}")
    public AbstractBaseResult page(@PathVariable int num, @PathVariable int size){
//        String pageJson = testService.page(num, size);
//        if(pageJson.equals("Provider-Test Down")){
//            return error("Provider-Test Down",null);
//        }else{
//            try {
//                JavaType javaType = MapperUtils.getCollectionType(PageInfo.class, TbUserInfo.class);
//                PageInfo<TbUserInfo> pageInfo = MapperUtils.getInstance().readValue(pageJson, javaType);
//                return success(request.getRequestURI(),pageInfo.getNextPage(),pageInfo.getPages(),pageInfo.getList());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        PageInfo<TbUserInfo> page = testService.page(num, size);
        if(page != null){
            return success(request.getRequestURI(),page.getNextPage(),page.getPages(),page.getList());
        }else{
            return error("Provider-Test Dwon!",null);
        }
    }

    @GetMapping("hello")
    public String index(){
        return testService.index();
    }
}
