package com.ruoyi.hospital.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.hospital.domain.Departments;
import com.ruoyi.hospital.domain.dto.DepartmentsDTO;
import com.ruoyi.hospital.service.IDepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 科室管理Controller
 *
 * @author YoyuEN
 * @date 2025-06-15
 */
@RestController
@RequestMapping("/departments/departments")
public class DepartmentsController extends BaseController
{
    @Autowired
    private IDepartmentsService departmentsService;

    /**
     * 查询科室管理列表
     */
    @PreAuthorize("@ss.hasPermi('departments:departments:list')")
    @GetMapping("/list")
    public TableDataInfo list(Departments departments)
    {
        startPage();
        List<DepartmentsDTO> list = departmentsService.selectDepartmentsList(departments);
        return getDataTable(list);
    }

    /*
    * 获取所有科室信息，初始化医生添加对话框的下拉列表
    *
    * */
    @PreAuthorize("@ss.hasPermi('departments:departments:getDepartmentsList')")
    @GetMapping("/getDepartmentsList")
    public AjaxResult getDepartmentsList()
    {
        return success(departmentsService.list());
    }


    /**
     * 获取科室管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('departments:departments:query')")
    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable("deptId") String deptId)
    {
        return success(departmentsService.getById(deptId));
    }

    /**
     * 新增科室管理
     */
    @PreAuthorize("@ss.hasPermi('departments:departments:add')")
    @Log(title = "科室管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Departments departments)
    {
        return toAjax(departmentsService.save(departments));
    }

    /**
     * 修改科室管理
     */
    @PreAuthorize("@ss.hasPermi('departments:departments:edit')")
    @Log(title = "科室管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Departments departments)
    {
        return toAjax(departmentsService.updateById(departments));
    }

    /**
     * 删除科室管理
     */
    @PreAuthorize("@ss.hasPermi('departments:departments:remove')")
    @Log(title = "科室管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{deptIds}")
    public AjaxResult remove(@PathVariable List<String> deptIds)
    {
        return toAjax(departmentsService.removeBatchByIds(deptIds));
    }

    /*
    * 根据医院名称查询医院ID再查询科室信息
    * */
    @PreAuthorize("@ss.hasPermi('departments:departments:getDepartmentsByHospitalName')")
    @GetMapping("/getDepartmentsByHospitalName/{hospitalName}")
    public AjaxResult getDepartmentsByHospitalName(@PathVariable("hospitalName") String hospitalName) {
        return success(departmentsService.getDepartmentsByHospitalName(hospitalName));
    }

    /*
    * 根据科室名称查询医生信息
    * */
    @PreAuthorize("@ss.hasPermi('departments:departments:searchDeptNameDoctors')")
    @GetMapping("/searchDeptNameDoctors/{deptName}")
    public AjaxResult searchDeptNameDoctors(@PathVariable("deptName") String deptName) {
        return success(departmentsService.searchDeptNameDoctors(deptName));
    }
}
