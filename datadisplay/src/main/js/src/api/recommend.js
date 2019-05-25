import request from '@/utils/request'

export function getSalaryRanking() {
  return request({
    url: '/recommend/salary',
    method: 'get',
  })
}

export function getEduLevelRanking() {
  return request({
    url: '/recommend/edu',
    method: 'get',
  })
}

export function getJobRanking() {
  return request({
    url: '/recommend/job',
    method: 'get',
  })
}

export function getJobRankingByCity() {
  return request({
    url: '/recommend/city',
    method: 'get',
  })
}


export function getJobRankingByWorkingExp() {
  return request({
    url: '/recommend/working',
    method: 'get',
  })
}

