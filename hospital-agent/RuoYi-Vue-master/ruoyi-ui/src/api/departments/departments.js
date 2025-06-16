import request from '@/utils/request'

// 查询科室管理列表
export function listDepartments(query) {
  return request({
    url: '/departments/departments/list',
    method: 'get',
    params: query
  })
}

// 查询科室管理详细
export function getDepartments(deptId) {
  return request({
    url: '/departments/departments/' + deptId,
    method: 'get'
  })
}

// 新增科室管理
export function addDepartments(data) {
  return request({
    url: '/departments/departments',
    method: 'post',
    data: data
  })
}

// 修改科室管理
export function updateDepartments(data) {
  return request({
    url: '/departments/departments',
    method: 'put',
    data: data
  })
}

// 删除科室管理
export function delDepartments(deptId) {
  return request({
    url: '/departments/departments/' + deptId,
    method: 'delete'
  })
}
