package cn.yan_wm.myalbum.commons.service.framework.translate;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: Service翻译
 * @author: yan_zt
 * @create: 2020-01-14 11:32
 */
@Component
public interface TranslateService {
    void translate(Object var1);

    <T> void translate(List<T> var1);
}
