package com.ekkosong.base.controller;

import com.ekkosong.base.common.CommonPage;
import com.ekkosong.base.common.CommonResult;
import com.ekkosong.base.mbg.model.PmsBrand;
import com.ekkosong.base.service.PmsBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌管理 Controller（以品牌管理为例，测试脚手架的搭建）
 */
@RestController
@RequestMapping("/brand")
@Slf4j
public class PmsBrandController {
    @Autowired
    private PmsBrandService brandService;

    @GetMapping("/all")
    public CommonResult<List<PmsBrand>> all() {
        return CommonResult.success(brandService.listAllBrand());
    }

    @PostMapping
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand) {
        int count = brandService.createBrand(pmsBrand);
        if (1 != count) {
            log.error("createBrand failed:{}", pmsBrand);
            return CommonResult.failed("操作失败");
        }
        log.info("createBrand success:{}", pmsBrand);
        return CommonResult.success();
    }

    @PutMapping("/{id}")
    public CommonResult updateBrand(@PathVariable Long id, @RequestBody PmsBrand pmsBrandDto, BindingResult result) {
        log.info("update brand id is {}, brand dto is {}", id, pmsBrandDto);
        int count = brandService.updateBrand(id, pmsBrandDto);
        if (count != 1) {
            log.error("update brand failed:{}", pmsBrandDto);
            return CommonResult.failed("操作失败");
        }
        log.info("update brand success:{}", pmsBrandDto);
        return CommonResult.success();
    }

    @DeleteMapping("/{id}")
    public CommonResult deleteBrand(@PathVariable Long id) {
        log.info("删除品牌，id {}", id);
        int count = brandService.deleteBrand(id);
        if (count != 1) {
            log.error("delete brand failed:{}", id);
            return CommonResult.failed("操作失败");
        }
        log.info("delete brand success:{}", id);
        return CommonResult.success();
    }

    @GetMapping
    public CommonResult<CommonPage<PmsBrand>> listBrand(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        log.info("分页数据查询：pageNum is {}, pageSize is {}", pageNum, pageSize);
        List<PmsBrand> brandList = brandService.listBrand(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }

    @GetMapping("/{id}")
    public CommonResult<PmsBrand> getBrand(@PathVariable Long id) {
        log.info("根据id查询品牌，id 为 {}", id);
        PmsBrand brand = brandService.getBrand(id);
        return CommonResult.success(brand);
    }


}
