import request from '@/utils/request'

export function getWelfareNumByJob(jobNameId) {
  return request({
    url: '/welfare/num',
    method: 'get',
    params: {
      jobNameId
    },
  })
}
