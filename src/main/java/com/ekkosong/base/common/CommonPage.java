package com.ekkosong.base.common;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * 分页数据封装类
 *
 * @param <T>
 */
@Data
public class CommonPage<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private List<T> records;

    /**
     * 将 PageHelper 分页后的 list 转为分页信息
     *
     * @param records
     * @param <T>
     * @return
     */
    public static <T> CommonPage<T> restPage(List<T> records) {
        CommonPage<T> result = new CommonPage<>();
        PageInfo<T> pageInfo = new PageInfo<>(records);
        result.setPageSize(pageInfo.getPageSize());
        result.setPageNum(pageInfo.getPageNum());
        result.setTotalPage(pageInfo.getPages());
        result.setTotal(pageInfo.getTotal());
        result.setRecords(pageInfo.getList());
        return result;
    }

}
