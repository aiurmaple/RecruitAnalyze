import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if false, the item will hidden in breadcrumb(default is true)
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index'),
      name: '首页',
      meta: { title: '首页', icon: 'global' }
    }]
  },

  {
    path: '/salary',
    component: Layout,
    redirect: '/salary/city',
    name: 'Salary',
    meta: { title: '薪资分析', icon: 'salary' },
    children: [
      {
        path: 'city',
        name: 'City',
        component: () => import('@/views/salary/city'),
        meta: { title: '主要城市薪资', icon: 'city' }
      },
      {
        path: 'workExp',
        name: 'WorkExp',
        component: () => import('@/views/salary/workExp'),
        meta: { title: '工作经验薪资', icon: 'work-exp' }
      }
    ]
  },

  {
    path: '/study',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Study',
        component: () => import('@/views/study/index'),
        meta: { title: '学历需求分析', icon: 'study' }
      }
    ]
  },

  {
    path: '/welfare',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Welfare',
        component: () => import('@/views/welfare/index'),
        meta: { title: '福利标签词云', icon: 'welfare' }
      }
    ]
  },

  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
