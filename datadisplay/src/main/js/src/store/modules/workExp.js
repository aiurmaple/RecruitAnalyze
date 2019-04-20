import { getWorkingExp } from '@/api/workExp'

const workExp = {
  actions: {
    getWorkingExp({ commit }) {
      return new Promise((resolve, reject) => {
        getWorkingExp().then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}

export default workExp
