import { getEduLevels } from '@/api/edu'

const edu = {
  actions: {
    getEduLevels({ commit }) {
      return new Promise((resolve, reject) => {
        getEduLevels().then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}

export default edu
