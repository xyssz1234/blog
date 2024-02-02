import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    children:[
      {
        path: '/',
        name: '',
        component: () => import('../views/admin/HomePage')
      },
      {
        path: '/test',
        name: 'Test',
        component: () => import('../views/admin/test')
      },
      {
        path: '/categoryManage',
        name: 'CategoryManage',
        component: () => import('../views/admin/categoryManage')
      },
      {
        path: '/articleManage',
        name: 'ArticleManage',
        component: () => import('../views/admin/articleManage')
      },
      {
        path: '/sysUser',
        name: 'SysUser',
        component: () => import('../views/admin/sysUserManage')
       },
      {
        path: '/sysRole',
        name: 'SysRole',
        component: () => import('../views/admin/sysRoleManage')
      },
      {
        path: '/sysMenu',
        name: 'SysMenu',
        component: () => import('../views/admin/sysMenuManage')
      },
      {
        path: '/editUserInfo',
        name: 'EditUserInfo',
        component: () => import('../views/admin/editUserInfo')
      }
    ]
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../views/Register.vue')
  },
  {
    path: '/detail',
    name: 'Detail',
    component: () => import('../views/front/Detail')
  },
  {
    path: '/article',
    name: 'Article',
    component: () => import('../views/front/Article')
  }


]

const router = new VueRouter({
  //路由模式
  //mode: 'history',
  routes
})

export default router
