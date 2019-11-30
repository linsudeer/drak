import Axios from 'axios'
import Qs from 'qs'
import Store from 'store'
import Global from '@/global'


const instance = Axios.create({
    baseURL: process.env.BASE_API, // 配置所有请求的根路径
    timeout:30000, //请求超时设置
    withCredentials:true
});

// 请求拦截
instance.interceptors.request.use(config => {
    // 这里做一些请求前配置 ...
    // config.headers['X-Token'] = getToken(); 需定义获取的token
    let contentType = config.headers['Content-Type'];
    if(contentType != 'multipart/form-data'){
        config.data = Qs.stringify(config.data) // 解决后台接受不到json数据
    }
    return config;
}, error => {
    console.log('请求错误');
    Promise.reject(error);
});
instance.interceptors.response.use(function(response) {
    
    let result = response.data;
    // console.log(response)
    if(result.code != 200){
        let code = result.code;
        switch (code) {
            case -1:
                Store.set(Global.ISLOGIN, false);
                Store.remove(Global.USER);
                break;
            default:
                break;
        }
        return Promise.reject(result.message);
    }else {
        return result;
    }
}, function(error) {
    if(error.message === 'Network Error'){
        Store.set(Global.ISLOGIN, false);
        Store.remove(Global.USER);
        console.log("网络连接失败!");
    }
    return Promise.reject(error);
});

export default instance;