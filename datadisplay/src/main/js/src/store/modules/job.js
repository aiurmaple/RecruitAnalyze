import { getJobsNum, getJobsNumByCity, getJobsName, getJobsSalaryByCity, getJobsSalaryByExp, getJobsNumByEdu } from '@/api/job'

const job = {
  actions: {
    getJobsNum({ commit }, info) {
      return new Promise((resolve, reject) => {
        getJobsNum(info.time).then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },


    getJobsNumByCity({ commit }, info) {
      return new Promise((resolve, reject) => {
        getJobsNumByCity(info.cityIds).then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },


    getJobsName({ commit }) {
      return new Promise((resolve, reject) => {
        getJobsName().then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },


    getJobsSalaryByCity({ commit }, info) {
      return new Promise((resolve, reject) => {
        getJobsSalaryByCity(info.jobNameId, info.cityIds, info.quarters).then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },


    getJobsSalaryByExp({ commit }, info) {
      return new Promise((resolve, reject) => {
        getJobsSalaryByExp(info.jobNameId, info.workingExpIds).then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },


    getJobsNumByEdu({ commit }, info) {
      return new Promise((resolve, reject) => {
        getJobsNumByEdu(info.jobNameId, info.eduLevelIds).then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
  }
}

export default job
