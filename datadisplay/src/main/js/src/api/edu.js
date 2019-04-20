import request from '@/utils/request'

export function getEduLevels() {
  return request({
    url: '/edus',
    method: 'get'
  })
}
