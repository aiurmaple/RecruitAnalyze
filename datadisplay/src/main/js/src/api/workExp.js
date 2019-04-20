import request from '@/utils/request'

export function getWorkingExp() {
  return request({
    url: '/experience',
    method: 'get'
  })
}
