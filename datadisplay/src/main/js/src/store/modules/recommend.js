import { getSalaryRanking, getEduLevelRanking, getJobRanking, getJobRankingByCity, getJobRankingByWorkingExp } from '@/api/recommend'

const recommend = {
  actions: {
    getSalaryRanking({ commit }) {
      return new Promise((resolve, reject) => {
        getSalaryRanking().then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },

    getEduLevelRanking({ commit }) {
      return new Promise((resolve, reject) => {
        getEduLevelRanking().then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },

    getJobRanking({ commit }) {
      return new Promise((resolve, reject) => {
        getJobRanking().then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },

    getJobRankingByCity({ commit }) {
      return new Promise((resolve, reject) => {
        getJobRankingByCity().then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },

    getJobRankingByWorkingExp({ commit }) {
      return new Promise((resolve, reject) => {
        getJobRankingByWorkingExp().then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}

export default recommend
