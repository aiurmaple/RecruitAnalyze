import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import user from './modules/user'
import job from './modules/job'
import city from './modules/city'
import edu from './modules/edu'
import workExp from './modules/workExp'
import welfare from './modules/welfare'
import getters from './getters'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    user,
    job,
    city,
    edu,
    workExp,
    welfare
  },
  getters
})

export default store
