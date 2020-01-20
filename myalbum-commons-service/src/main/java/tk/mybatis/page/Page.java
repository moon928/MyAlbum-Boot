package tk.mybatis.page;

import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;

/**
 * @program: MyAlbum-Boot
 * @description: 分页模型
 * @author: yan_zt
 * @create: 2020-01-14 11:46
 */
public class Page extends RowBounds implements Serializable {
    private static final long serialVersionUID = 1L;

    // --分页参数 --//
    /**
     * 页编号 : 第几页
     */
    protected int pageNo = 1;

    /**
     * 页大小 : 每页的数量
     */
    protected int pageSize = 15;

    /**
     * 偏移量 : 第一条数据在表中的位置
     */
    private int pageOffset;

    /**
     * 限定数 : 每页的数量
     */
    private int pageLimit;
    /**
     * 总条数
     */
    protected int totalCount;

    /**
     * 总页数
     */
    protected int totalPages;

    /**
     * 排序字段
     *
     */
    public String sidx;

    /**
     * 排序方式
     */
    public String sord;

    /**
     * 自定义SQL条件
     */
    protected String sqlCond = "";

    // --计算 数据库 查询的参数 : LIMIT 3, 3; LIMIT offset, limit; --//
    /**
     * 计算偏移量
     */
    private void calcOffset() {
        this.pageOffset = ((pageNo - 1) * pageSize);
    }

    /**
     * 计算限定数
     */
    private void calcLimit() {
        this.pageLimit = pageSize;
    }

    // -- 构造函数 --//
    public Page() {
        this.calcOffset();
        this.calcLimit();
    }

    public Page(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.calcOffset();
        this.calcLimit();
    }

    // -- 访问查询参数函数 --//
    /**
     * 获得当前页的页号,序号从1开始,默认为1.
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 获得每页的记录数量,默认为1.
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
     */
    public int getFirst() {
        return ((pageNo - 1) * pageSize) + 1;
    }

    /**
     * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从0开始.
     */
    public int getPageOffset() {
        return pageOffset;
    }

    public int getPageLimit() {
        return pageLimit;
    }

    /**
     * 取得总记录数, 默认值为-1.
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 设置总记录数.
     */
    public void setTotalCount(final int totalCount) {
        this.totalCount = totalCount;
        this.totalPages = this.getTotalPages();
    }

    /**
     * 根据pageSize与totalCount计算总页数, 默认值为-1.
     */
    public int getTotalPages() {
        if (totalCount < 0) {
            return -1;
        }
        int pages = totalCount / pageSize;
        return totalCount % pageSize > 0 ? ++pages : pages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
        this.calcOffset();
        this.calcLimit();
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        this.calcOffset();
        this.calcLimit();
    }

    public String getSidx() {
        return sidx;
    }

    /**
     *
     * @Title: setSidx
     * @Description: 设置排序字段； 此参数作为分页时必传参数
     * @param @param sidx 参数
     * @return void 返回类型
     * @throws @date 2017年9月9日 下午2:45:31
     */
    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public String getSqlCond() {
        return sqlCond;
    }

    public void setSqlCond(String sqlCond) {
        this.sqlCond = sqlCond == null ? "" : sqlCond;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", pageOffset=" + pageOffset +
                ", pageLimit=" + pageLimit +
                ", totalCount=" + totalCount +
                ", totalPages=" + totalPages +
                ", sidx='" + sidx + '\'' +
                ", sord='" + sord + '\'' +
                ", sqlCond='" + sqlCond + '\'' +
                '}';
    }
}
