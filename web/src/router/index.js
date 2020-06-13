import Vue from 'vue'
import VueRouter from 'vue-router'

// compornent
import Login from '../views/Login.vue'
import Home from '../views/Home.vue'
import Deposit from '../views/Deposit.vue'
import Withdraw from '../views/Withdraw.vue'

// store
import Store from '@/store/index.js'

Vue.use(VueRouter)

const routes = [
  {
    name: 'Login',
    path: '/login',
    component: Login
  },
  {
    name: 'Home',
    path: '/',
    component: Home,
    meta: { requiresAuth: true }
  },
  {
    name: 'Deposit',
    path: '/deposit',
    component: Deposit,
    meta: { requiresAuth: true }
  },
  {
    name: 'Withdraw',
    path: '/withdraw',
    component: Withdraw,
    meta: { requiresAuth: true }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth) && !Store.state.userToken) {
    next({ path: '/login', query: { redirect: to.fullPath } });
  } else {
    next();
  }
});

export default router