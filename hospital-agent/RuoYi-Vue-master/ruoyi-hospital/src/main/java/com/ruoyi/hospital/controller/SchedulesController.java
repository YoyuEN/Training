package com.ruoyi.hospital.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.hospital.domain.Schedules;
import com.ruoyi.hospital.domain.dto.SchedulesDTO;
import com.ruoyi.hospital.service.ISchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 排班管理Controller
 * 
 * @author YoyuEN
 * @date 2025-06-16
 */
@RestController
@RequestMapping("/schedules/schedules")
public class SchedulesController extends BaseController
{
    @Autowired
    private ISchedulesService schedulesService;

    /**
     * 查询排班管理列表
     */
    @PreAuthorize("@ss.hasPermi('schedules:schedules:list')")
    @GetMapping("/list")
    public TableDataInfo list(Schedules schedules)
    {
        startPage();
        List<SchedulesDTO> list = schedulesService.selectSchedulesList(schedules);
        return getDataTable(list);
    }

    /**
     * 导出排班管理列表
     */
    @PreAuthorize("@ss.hasPermi('schedules:schedules:export')")
    @Log(title = "排班管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Schedules schedules)
    {
        List<SchedulesDTO> list = schedulesService.selectSchedulesList(schedules);
        ExcelUtil<SchedulesDTO> util = new ExcelUtil<SchedulesDTO>(SchedulesDTO.class);
        util.exportExcel(response, list, "排班管理数据");
    }

    /**
     * 获取排班管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('schedules:schedules:query')")
    @GetMapping(value = "/{scheduleId}")
    public AjaxResult getInfo(@PathVariable("scheduleId") String scheduleId)
    {
        return success(schedulesService.getById(scheduleId));
    }

    /**
     * 新增排班管理
     */
    @PreAuthorize("@ss.hasPermi('schedules:schedules:add')")
    @Log(title = "排班管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Schedules schedules)
    {
        schedules.setScheduleId(UUID.fastUUID().toString());
        schedules.setDate(StringUtils.substring(schedules.getDate(),0,10));
        return toAjax(schedulesService.save(schedules));
    }

    /**
     * 修改排班管理
     */
    @PreAuthorize("@ss.hasPermi('schedules:schedules:edit')")
    @Log(title = "排班管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Schedules schedules)
    {
        return toAjax(schedulesService.updateById(schedules));
    }

    /**
     * 删除排班管理
     */
    @PreAuthorize("@ss.hasPermi('schedules:schedules:remove')")
    @Log(title = "排班管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{scheduleIds}")
    public AjaxResult remove(@PathVariable List<String> scheduleIds)
    {
        return toAjax(schedulesService.removeBatchByIds(scheduleIds));
    }
}
