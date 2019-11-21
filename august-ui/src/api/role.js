import request from '@/utils/request'

export function listRole(queryParam) {

  return request({
    url: '/role/list',
    method: 'get',
    params: queryParam
  })

}

export function updateRole(data) {
  return request({
    url: '/role/update',
    method: 'post',
    data
  });

}

export function deleteRole(data) {
  return request({
    url: '/role/delete',
    method: 'post',
    data
  })
}
