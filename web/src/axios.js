import axios from "axios"
import router from  "./router"
import Element from "element-ui";
axios.defaults.baseURL = process.env.VUE_APP_SERVER
//axios.defaults.baseURL='/api'

const request = axios.create({
    timeout: 5000,
    headers: {}
})

request.interceptors.request.use(config => {
    config.headers["token"] = localStorage.getItem("token")
    return config
})

request.interceptors.response.use(
    response => {
        //console.log(response)
        let res=response.data
        if(res.code===200)
        {
            return  response.data
        }else
        {
            //权限不足
            if(res.code===301 || res.code===401 ){
              Element.Message.error(res)
                //跳转登录页面
              router.push({path:"/login"})
              return  Promise.reject(res.msg)
            }else
            {
                //其他错误
                Element.Message.error(!res.msg?"系统异常，请联系系统管理员":res.msg)
                return  Promise.reject(res.msg)
            }

        }
        return  res
    },
    error => {
        //console.log(error)
        return Promise.reject(error)
    })


export  default  request