import request from '@/utils/request'

// 查询医生管理列表
export function listDoctors(query) {
  return request({
    url: '/doctors/doctors/list',
    method: 'get',
    params: query
  })
}

// 查询医生管理详细
export function getDoctors(doctorId) {
  return request({
    url: '/doctors/doctors/' + doctorId,
    method: 'get'
  })
}

// 新增医生管理
export function addDoctors(data) {
  return request({
    url: '/doctors/doctors',
    method: 'post',
    data: data
  })
}

// 修改医生管理
export function updateDoctors(data) {
  return request({
    url: '/doctors/doctors',
    method: 'put',
    data: data
  })
}

// 删除医生管理
export function delDoctors(doctorId) {
  return request({
    url: '/doctors/doctors/' + doctorId,
    method: 'delete'
  })
}
