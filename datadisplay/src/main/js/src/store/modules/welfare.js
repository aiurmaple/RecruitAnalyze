import { getWelfareNumByJob } from '@/api/welfare'

const welfare = {
  actions: {
    getWelfareNumByJob({ commit }, info) {
      return new Promise((resolve, reject) => {
        getWelfareNumByJob(info.jobNameId).then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}

export default welfare
