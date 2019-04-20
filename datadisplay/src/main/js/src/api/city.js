import request from '@/utils/request'

export function getCitys() {
  return request({
    url: '/citys',
    method: 'get'
  })
}
