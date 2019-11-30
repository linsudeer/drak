import Request from '@/utils/request'

export default {
    login: (username, password) => {
        return Request({
            url: '/user/login',
            method: 'post',
            data: {
                userName: username,
                password: password
            }
        })
    },
    checkLogin: () => {
        return Request({
            url:'/user/checkLogin',
            method:'post'
        })
    },
    register: (parmas)=> {
        return Request({
            url:'/user/register',
            method:'post',
            data:parmas
        })
    },
    logout: () => {
        return Request({
            url: '/user/logout',
            method: 'post'
        })
    }
}