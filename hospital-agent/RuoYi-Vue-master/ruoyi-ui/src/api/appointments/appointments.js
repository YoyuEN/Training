import request from '@/utils/request'

// 查询预约管理列表
export function listAppointments(query) {
  return request({
    url: '/appointments/appointments/list',
    method: 'get',
    params: query
  })
}

// 查询预约管理详细
export function getAppointments(appointmentId) {
  return request({
    url: '/appointments/appointments/' + appointmentId,
    method: 'get'
  })
}

// 新增预约管理
export function addAppointments(data) {
  return request({
    url: '/appointments/appointments',
    method: 'post',
    data: data
  })
}

// 修改预约管理
export function updateAppointments(data) {
  return request({
    url: '/appointments/appointments',
    method: 'put',
    data: data
  })
}

// 删除预约管理
export function delAppointments(appointmentId) {
  return request({
    url: '/appointments/appointments/' + appointmentId,
    method: 'delete'
  })
}

// 统计不同科室每天的预约人数
export function countAppointmentsByDeptNameAndDate() {
  return request({
    url: '/appointments/appointments/countAppointmentsByDeptNameAndDate',
    method: 'get'
  })
}

// 统计不同医生每天的预约人数
export function countAppointmentsByDoctorNameAndDate() {
  return request({
    url: '/appointments/appointments/countAppointmentsByDoctorNameAndDate',
    method: 'get'
  })
}

//    统计不同医院不同就诊时段的预约人数
export function countAppointmentsByHospitalNameAndTime() {
  return request({
    url: '/appointments/appointments/countAppointmentsByHospitalNameAndTime',
    method: 'get'
  })
}

//    统计不同科室不同就诊时段的预约人数
export function countAppointmentsByDeptNameAndTime() {
  return request({
    url: '/appointments/appointments/countAppointmentsByDeptNameAndTime',
    method: 'get'
  })
}

