import request from '@/utils/request'

const api_prefix = '/api/app/user/number'

// 登录
export function saveVideoNumber(data) {
  return request({
    url: `${api_prefix}/insert`,
    method: 'post',
    data
  })
}
