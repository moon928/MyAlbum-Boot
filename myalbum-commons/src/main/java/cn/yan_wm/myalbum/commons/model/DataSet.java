package cn.yan_wm.myalbum.commons.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @program: MyAlbum-Boot
 * @description: 返回分页数据类型
 * @author: yan_zt
 * @create: 2020-01-14 11:45
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DataSet<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 第N页
     */
    private Integer pageNo = 1;

    /**
     * 每页N个
     */
    private Integer pageSize = 10;

    /**
     * 总页数
     */
    private Integer pageCount;

    /**
     * 总记录数
     */
    private Integer totalCount;

    /**
     * 数据
     */
    private List<T> rows;
}
