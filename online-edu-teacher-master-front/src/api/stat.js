import request from '@/utils/request'

const api_prefix = '/api/teacher/stat'

export function getStat() {
  return request({
    url: `${api_prefix}/get/common`,
    method: 'get'
  })
}

export function getDaily(data) {
  return request({
    url: `${api_prefix}/get/daily`,
    method: 'post',
    headers: { 'Content-Type': 'multipart/form-data' },
    data
  })
}