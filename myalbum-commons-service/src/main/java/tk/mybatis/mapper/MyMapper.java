package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @program: MyAlbum-Boot
 * @description: tk.mybatis MyMapper
 * @author: yan_zt
 * @create: 2020-01-14 13:01
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
