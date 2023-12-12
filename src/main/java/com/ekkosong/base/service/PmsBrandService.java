package com.ekkosong.base.service;

import com.ekkosong.base.mbg.model.PmsBrand;

import java.util.List;

public interface PmsBrandService {
    /**
     * 获取所有品牌
     *
     * @return
     */
    List<PmsBrand> listAllBrand();

    /**
     * 新增品牌
     *
     * @param brand
     * @return
     */
    int createBrand(PmsBrand brand);

    /**
     * 根据 id 更新品牌信息
     *
     * @param id
     * @param brand
     * @return
     */
    int updateBrand(Long id, PmsBrand brand);

    /**
     * 根据id删除品牌
     *
     * @param id
     * @return
     */
    int deleteBrand(Long id);

    /**
     * 分页查询品牌
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<PmsBrand> listBrand(int pageNum, int pageSize);

    /**
     * 根据id获取品牌
     *
     * @param id
     * @return
     */
    PmsBrand getBrand(Long id);

}
