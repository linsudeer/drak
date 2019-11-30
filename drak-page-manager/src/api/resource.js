import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/resource/list',
    method: 'get',
    params: query
  })
}

export function createResource(data) {
  return request({
    url: '/resource/create',
    method: 'post',
    data
  })
}

export function updateResource(data) {
  return request({
    url: '/Resource/update',
    method: 'put',
    data
  })
}

export function deleteResource(id) {
  return request({
    url: '/Resource/delete',
    method: 'delete'
  })
}
