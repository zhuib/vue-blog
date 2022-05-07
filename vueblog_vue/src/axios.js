import axios from 'axios'
import Element from 'element-ui'
import router from "./router";


axios.defaults.baseURL = "http://localhost:8080"

// 前置拦截
axios.interceptors.request.use(config =>{
    // console.log(config)
    return config
})

// 后置拦截
axios.interceptors.response.use(response =>{

    let res = response.data;
    console.log("======================")
    console.log(res)
    console.log("======================")

    if (res.code == 200){
        return response
    }else {
        // 设置请求超时时间 duration :3
        Element.Message.error('错了哦，这是一条错误消息',{  duration :3 * 1000});

        return Promise.reject(response.data.msg)
    }
},error => {
    // console.log(error)

    if (error.response.data){
        // console.log(error.response.data.msg)
        error.message = error.response.data.msg
    }

    if (error.response.status === 401){
        store.commit("REMOVE_INFO")
        router.push("/login")
    }
    Element.Message.error(error.message,{  duration :3 * 1000});
    return Promise.reject(error)
})
