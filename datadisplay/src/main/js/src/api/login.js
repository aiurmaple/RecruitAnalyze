import request from '@/utils/request'

export function login(username, password) {
  return request({
    url: '/user/login',
    method: 'post',
    data: {
      username,
      password
    }
  })
}

export function getInfo(userName) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { userName }
  })
}

export function logout(userName) {
  return request({
    url: '/user/logout',
    method: 'get',
    params: { userName }
  })
}
