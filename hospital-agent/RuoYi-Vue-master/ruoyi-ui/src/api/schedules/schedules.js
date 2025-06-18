import request from '@/utils/request'

// 查询排班管理列表
export function listSchedules(query) {
  return request({
    url: '/schedules/schedules/list',
    method: 'get',
    params: query
  })
}

// 查询排班管理详细
export function getSchedules(scheduleId) {
  return request({
    url: '/schedules/schedules/' + scheduleId,
    method: 'get'
  })
}

// 新增排班管理
export function addSchedules(data) {
  return request({
    url: '/schedules/schedules',
    method: 'post',
    data: data
  })
}

// 修改排班管理
export function updateSchedules(data) {
  return request({
    url: '/schedules/schedules',
    method: 'put',
    data: data
  })
}

// 删除排班管理
export function delSchedules(scheduleId) {
  return request({
    url: '/schedules/schedules/' + scheduleId,
    method: 'delete'
  })
}
