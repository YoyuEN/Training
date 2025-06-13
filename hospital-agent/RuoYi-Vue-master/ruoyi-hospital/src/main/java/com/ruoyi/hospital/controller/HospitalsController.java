package com.ruoyi.hospital.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.hospital.domain.Hospitals;
import com.ruoyi.hospital.service.IHospitalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 医院信息Controller
 *
 * @author ruoyi
 * @date 2025-06-12
 */
@RestController
@RequestMapping("/hospitals/hospitals")
public class HospitalsController extends BaseController
{
    @Autowired
    private IHospitalsService hospitalsService;

    /**
     * 查询医院信息列表
     */
    @PreAuthorize("@ss.hasPermi('hospitals:hospitals:list')")
    @GetMapping("/list")
    public TableDataInfo list(Hospitals hospitals)
    {
        startPage();
        List<Hospitals> list = hospitalsService.selectHospitalsList(hospitals);
        return getDataTable(list);
    }

    /**
     * 导出医院信息列表
     */
//    @PreAuthorize("@ss.hasPermi('hospitals:hospitals:export')")
//    @Log(title = "医院信息", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, Hospitals hospitals)
//    {
//        List<Hospitals> list = hospitalsService.selectHospitalsList(hospitals);
//        ExcelUtil<Hospitals> util = new ExcelUtil<Hospitals>(Hospitals.class);
//        util.exportExcel(response, list, "医院信息数据");
//    }

    /**
     * 获取医院信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('hospitals:hospitals:query')")
    @GetMapping(value = "/{hospitalId}")
    public AjaxResult getInfo(@PathVariable("hospitalId") Long hospitalId)
    {
        return success(hospitalsService.getById(hospitalId));
    }

    /**
     * 新增医院信息
     */
    @PreAuthorize("@ss.hasPermi('hospitals:hospitals:add')")
    @Log(title = "医院信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Hospitals hospitals)
    {
        return toAjax(hospitalsService.save(hospitals));
    }

    /**
     * 修改医院信息
     */
    @PreAuthorize("@ss.hasPermi('hospitals:hospitals:edit')")
    @Log(title = "医院信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Hospitals hospitals)
    {
        return toAjax(hospitalsService.updateById(hospitals));
    }

    /**
     * 删除医院信息
     */
    @PreAuthorize("@ss.hasPermi('hospitals:hospitals:remove')")
    @Log(title = "医院信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{hospitalIds}")
    public AjaxResult remove(@PathVariable List<Long> hospitalIds)
    {
        return toAjax(hospitalsService.removeBatchByIds(hospitalIds));
    }
}
