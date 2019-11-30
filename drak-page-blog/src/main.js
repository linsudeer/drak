// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from '@/App'
import router from '@/router/index'
import veeValidator from 'vee-validate'
import Global from '@/global'
import Store from 'store'
import '@/css/common'
import ElementUI from 'element-ui'
import { Message } from 'element-ui';
import UserApi from '@/api/user'
import 'element-ui/lib/theme-chalk/index.css'

Vue.use(ElementUI)
Vue.use(veeValidator, {autoHint: true});
Vue.config.productionTip = false;



var vue = new Vue({
  el: '#app',
  router: router,
  mixins: ["error"],
  render: h => h(App)
  
})

//router.push("/");
router.beforeEach((to, from, next) => {
  console.log(from.path+" -> "+to.path)
  if (to.meta.auth) {
    UserApi.checkLogin().then(function(res){
      console.log(res)
      if(res.code == -1){
        Message("请先登录！")
        Store.set(Global.ISLOGIN, false);
            Store.set(Global.USER, null);
      }else {
        next()
      }
      
    }).catch(function(erro){
      Message(erro)
      next("/")
    })
} else {
    next()//若点击的是不需要验证的页面,则进行正常的路由跳转
}



})
