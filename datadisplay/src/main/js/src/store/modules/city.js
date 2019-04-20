import { getCitys } from '@/api/city'

const city = {
  actions: {
    getCitys({ commit }) {
      return new Promise((resolve, reject) => {
        getCitys().then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}

export default city
