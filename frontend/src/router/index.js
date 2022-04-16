import Vue from "vue";
import Router from "vue-router";
import store from '@/store';
import Layout from '../modules/shareds/views/layout'

/* conta */
import Login from '../modules/accounts/views/login';
import AccountList from '../modules/accounts/views/list';

/* home */
import Dashboard from '../modules/home/views/dashboard'

/* Cliente */
import CustomerList from '../modules/customers/views/list'
import CustomerDetail from '../modules/customers/views/detail'
import CustomerCreateEdit from '../modules/customers/views/create-edit.vue'


const ifNotAuthenticated = (to, from, next) => {
  let account = store.state.accounts;
  if (!account.isAuthenticated) {
    next();
    return;
  }
  next('/');
};

const ifAuthenticated = (to, from, next) => {
  let account = store.state.accounts;
  if (account.isAuthenticated) {
    next();
    return;
  }
  next('/login');
};

Vue.use(Router);

const routes = [
  {
    path: '',
    name: "dashboard",
    redirect: "/dashboard",
    beforeEnter: ifAuthenticated
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    beforeEnter: ifNotAuthenticated
  },
  {
    path: '/',
    component: Layout,
    beforeEnter: ifAuthenticated,
    children: [
      {
        path: '/dashboard',
        component: Dashboard,
        meta: {
          title: 'Dashboard | BxMED',
        }
      },
      {
        path: '/customers',
        name: 'customer_list',
        component: CustomerList,
        beforeEnter: ifAuthenticated,
        meta: {
          title: 'Cliente | BxMED',
        }
      },
      {
        path: '/customers/:id/detail',
        name: 'customer_detail',
        component: CustomerDetail,
        beforeEnter: ifAuthenticated,
        meta: {
          title: 'Cliente | BxMED',
        }
      },
      {
        path: '/customers/create',
        name: 'customer_create',
        component: CustomerCreateEdit,
        beforeEnter: ifAuthenticated,
        meta: {
          title: 'Cliente | BxMED',
        }
      },
      {
        path: '/customers/:id',
        name: 'customer_update',
        component: CustomerCreateEdit,
        beforeEnter: ifAuthenticated,
        meta: {
          title: 'Cliente | BxMED',
        }
      },
      {
        path: '/accounts',
        name: 'account_list',
        component: AccountList,
        beforeEnter: ifAuthenticated,
        meta: {
          title: 'Configurações | BxMED',
        }
      }
    ]
  }];

const router = new Router({
  routes,
  base: process.env.VUE_APP_BASE_URL,
  mode: "history",
  linkActiveClass: "active",
  scrollBehavior() {
    return { x: 0, y: 0 };
  },
});

export default router;