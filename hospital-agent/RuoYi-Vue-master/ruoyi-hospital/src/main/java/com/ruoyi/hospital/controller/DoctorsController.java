package com.ruoyi.hospital.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.hospital.domain.Doctors;
import com.ruoyi.hospital.domain.dto.DoctorsDTO;
import com.ruoyi.hospital.service.IDoctorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 医生管理Controller
 * 
 * @author YoyuEN
 * @date 2025-06-16
 */
@RestController
@RequestMapping("/doctors/doctors")
public class DoctorsController extends BaseController
{
    @Autowired
    private IDoctorsService doctorsService;

    /**
     * 查询医生管理列表
     */
    @PreAuthorize("@ss.hasPermi('doctors:doctors:list')")
    @GetMapping("/list")
    public TableDataInfo list(Doctors doctors)
    {
        startPage();
        List<DoctorsDTO> list = doctorsService.selectDoctorsList(doctors);
        return getDataTable(list);
    }

    /**
     * 导出医生管理列表
     */
    @PreAuthorize("@ss.hasPermi('doctors:doctors:export')")
    @Log(title = "医生管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Doctors doctors)
    {
        List<DoctorsDTO> list = doctorsService.selectDoctorsList(doctors);
        ExcelUtil<DoctorsDTO> util = new ExcelUtil<DoctorsDTO>(DoctorsDTO.class);
        util.exportExcel(response, list, "医生管理数据");
    }

    /**
     * 获取医生管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('doctors:doctors:query')")
    @GetMapping(value = "/{doctorId}")
    public AjaxResult getInfo(@PathVariable("doctorId") String doctorId)
    {
        return success(doctorsService.getById(doctorId));
    }

    /**
     * 新增医生管理
     */
    @PreAuthorize("@ss.hasPermi('doctors:doctors:add')")
    @Log(title = "医生管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Doctors doctors)
    {
        return toAjax(doctorsService.save(doctors));
    }

    /**
     * 修改医生管理
     */
    @PreAuthorize("@ss.hasPermi('doctors:doctors:edit')")
    @Log(title = "医生管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Doctors doctors)
    {
        return toAjax(doctorsService.updateById(doctors));
    }

    /**
     * 删除医生管理
     */
    @PreAuthorize("@ss.hasPermi('doctors:doctors:remove')")
    @Log(title = "医生管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{doctorIds}")
    public AjaxResult remove(@PathVariable List<String> doctorIds)
    {
        return toAjax(doctorsService.removeBatchByIds(doctorIds));
    }
}
