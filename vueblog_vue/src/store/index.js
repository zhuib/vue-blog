import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  // 存放属性
  state: {
    // token:'',
    // userInfo:{}
    token:'',
    userInfo:JSON.parse(sessionStorage.getItem("userInfo"))
  },
  // 相当于get/set 的方法
  mutations: {
    //  javabean中的set
    // state参数是上面的state ，token是传入的参数
    SET_TOKEN:(state,token) =>{
      state.token = token
      localStorage.setItem("token",token)
    },
    SET_USERINFO:(state,userInfo) =>{
      state.userInfo = userInfo
      sessionStorage.setItem("userInfo",JSON.stringify(userInfo))
    },
    REMOVE_INFO:(state) =>{
      state.token = ''
      state.userInfo = {}
      localStorage.setItem("token",'')
      sessionStorage.setItem("userInfo",JSON.stringify(''))
    }

  },
  //  javabean中的get
  getters:{
      getUser: state => {
        return state.userInfo
      }
  },
  actions: {
  },
  modules: {
  }
})
