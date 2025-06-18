package com.ruoyi.hospital.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.hospital.domain.Appointments;
import com.ruoyi.hospital.domain.dto.AppointmentsDTO;
import com.ruoyi.hospital.service.IAppointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 预约管理Controller
 *
 * @author ruoyi
 * @date 2025-06-17
 */
@RestController
@RequestMapping("/appointments/appointments")
public class AppointmentsController extends BaseController {
    @Autowired
    private IAppointmentsService appointmentsService;

    /**
     * 查询预约管理列表
     */
    @PreAuthorize("@ss.hasPermi('appointments:appointments:list')")
    @GetMapping("/list")
    public TableDataInfo list(Appointments appointments) {
        startPage();
        List<AppointmentsDTO> list = appointmentsService.selectAppointmentsList(appointments);
        return getDataTable(list);
    }

    /**
     * 导出预约管理列表
     */
    @PreAuthorize("@ss.hasPermi('appointments:appointments:export')")
    @Log(title = "预约管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Appointments appointments) {
        List<AppointmentsDTO> list = appointmentsService.selectAppointmentsList(appointments);
        ExcelUtil<AppointmentsDTO> util = new ExcelUtil<AppointmentsDTO>(AppointmentsDTO.class);
        util.exportExcel(response, list, "预约管理数据");
    }

    /**
     * 获取预约管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('appointments:appointments:query')")
    @GetMapping(value = "/{appointmentId}")
    public AjaxResult getInfo(@PathVariable("appointmentId") String appointmentId) {
        return success(appointmentsService.getById(appointmentId));
    }

    /**
     * 新增预约管理
     */
    @PreAuthorize("@ss.hasPermi('appointments:appointments:add')")
    @Log(title = "预约管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Appointments appointments) {
        appointments.setAppointmentId(UUID.fastUUID().toString());
        return toAjax(appointmentsService.save(appointments));
    }

    /**
     * 修改预约管理
     */
    @PreAuthorize("@ss.hasPermi('appointments:appointments:edit')")
    @Log(title = "预约管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Appointments appointments) {
        return toAjax(appointmentsService.updateById(appointments));
    }

    /**
     * 删除预约管理
     */
    @PreAuthorize("@ss.hasPermi('appointments:appointments:remove')")
    @Log(title = "预约管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{appointmentIds}")
    public AjaxResult remove(@PathVariable List<String> appointmentIds) {
        return toAjax(appointmentsService.removeBatchByIds(appointmentIds));
    }

    // 统计不同科室每天的预约人数

    @PreAuthorize("@ss.hasPermi('appointments:appointments:countAppointmentsByDeptNameAndDate')")
    @GetMapping("/countAppointmentsByDeptNameAndDate")
    public AjaxResult countAppointmentsByDeptNameAndDate(){
        return success(appointmentsService.countAppointmentsByDeptNameAndDate());
    }
    // 统计不同医生每天的预约人数
    @PreAuthorize("@ss.hasPermi('appointments:appointments:countAppointmentsByDoctorNameAndDate')")
    @GetMapping("/countAppointmentsByDoctorNameAndDate")
    public AjaxResult countAppointmentsByDoctorNameAndDate(){
        return success(appointmentsService.countAppointmentsByDoctorNameAndDate());
    }

    //    统计不同医院不同就诊时段的预约人数
    @PreAuthorize("@ss.hasPermi('appointments:appointments:countAppointmentsByHospitalNameAndTime')")
    @GetMapping("/countAppointmentsByHospitalNameAndTime")
    public AjaxResult countAppointmentsByHospitalNameAndTime(){
        return success(appointmentsService.countAppointmentsByHospitalNameAndTime());
    }

    //    统计不同科室不同就诊时段的预约人数
    @PreAuthorize("@ss.hasPermi('appointments:appointments:countAppointmentsByDeptNameAndTime')")
    @GetMapping("/countAppointmentsByDeptNameAndTime")
    public AjaxResult countAppointmentsByDeptNameAndTime(){
        return success(appointmentsService.countAppointmentsByDeptNameAndTime());
    }

}
