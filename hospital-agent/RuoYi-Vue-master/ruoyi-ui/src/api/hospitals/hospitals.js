import request from '@/utils/request'

// 查询医院信息列表
export function listHospitals(query) {
  return request({
    url: '/hospitals/hospitals/list',
    method: 'get',
    params: query
  })
}

// 查询医院信息详细
export function getHospitals(hospitalId) {
  return request({
    url: '/hospitals/hospitals/' + hospitalId,
    method: 'get'
  })
}

// 新增医院信息
export function addHospitals(data) {
  return request({
    url: '/hospitals/hospitals',
    method: 'post',
    data: data
  })
}

// 修改医院信息
export function updateHospitals(data) {
  return request({
    url: '/hospitals/hospitals',
    method: 'put',
    data: data
  })
}

// 删除医院信息
export function delHospitals(hospitalId) {
  return request({
    url: '/hospitals/hospitals/' + hospitalId,
    method: 'delete'
  })
}
