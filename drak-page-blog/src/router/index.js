import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [{
    path: '/',
    component: () => import('@/view/index'),
    meta:{auth:true}, //是否需要登陆权限验证
    children: [
        { path: '', component: () => import('@/view/article/articleList') },
        { path: '/articles/:type/:oid',  component: () => import('@/view/article/articleList')},
        { path: '/read', component: () => import('@/view/read') }
    ]
  },{
        path: '/write',
        component:() => import('@/view/article/articleWrite'),
        meta:{auth:true} //是否需要登陆权限验证
    },{
        path:'/articles/:id',
        component:() => import('@/view/article/articleDetail')
    }
]
})


