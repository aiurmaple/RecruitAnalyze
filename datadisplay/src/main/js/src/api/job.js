import request from '@/utils/request'
const qs = require('qs');

export function getJobsNum(time) {
  return request({
    url: '/jobs/num',
    method: 'get',
    params: {
      time,
    }
  })
}

export function getJobsNumByCity(cityIds) {
  return request({
    url: '/jobs/num/city',
    method: 'get',
    params: {
      cityIds
    },
    paramsSerializer: function(params) {
      return qs.stringify(params, {arrayFormat: 'repeat'})
    }
  })
}

export function getJobsName() {
  return request({
    url: '/jobs/name',
    method: 'get'
  })
}

export function getJobsSalaryByCity(jobNameId, cityIds, quarters) {
  return request({
    url: '/jobs/salary/city',
    method: 'get',
    params: {
      jobNameId,
      cityIds,
      quarters
    },
    paramsSerializer: function(params) {
      return qs.stringify(params, {arrayFormat: 'repeat'})
    }
  })
}

export function getJobsSalaryByExp(jobNameId, workingExpIds) {
  return request({
    url: '/jobs/salary/experience',
    method: 'get',
    params: {
      jobNameId,
      workingExpIds
    },
    paramsSerializer: function(params) {
      return qs.stringify(params, {arrayFormat: 'repeat'})
    }
  })
}

export function getJobsNumByEdu(jobNameId, eduLevelIds) {
  return request({
    url: '/jobs/num/edu',
    method: 'get',
    params: {
      jobNameId,
      eduLevelIds
    },
    paramsSerializer: function(params) {
      return qs.stringify(params, {arrayFormat: 'repeat'})
    }
  })
}

export function getJobsNumByQuarter(jobNameId) {
  return request({
    url: '/jobs/quarter/num',
    method: 'get',
    params: {
      jobNameId
    }
  })
}

export function getJobsNumByCityId(cityId) {
  return request({
    url: '/jobs/num/city/id',
    method: 'get',
    params: {
      cityId
    }
  })
}
