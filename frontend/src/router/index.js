import Vue from "vue";
import Router from "vue-router";
import store from '@/store';
import Layout from '../modules/shareds/views/layout'

/* conta */
import Login from '../modules/accounts/views/login';
import AccountList from '../modules/accounts/views/list';

/* home */
import Dashboard from '../modules/home/views/dashboard'

// tickets
//import TicketOpen from '../modules/tickets/components/open-modal'
import TicketDetail from '../modules/tickets/components/detail-modal'
import TicketCancel from '../modules/tickets/components/cancel-modal'

/* Empresa */
import CompanyList from '../modules/companies/views/list'
import CompanyDetail from '../modules/companies/views/detail'
import CompanyDetailData from '../modules/companies/components/data.vue'
import CompanyDetailInvoices from '../modules/companies/components/invoices.vue'
import CompanyCreateEdit from '../modules/companies/views/create-edit.vue'
import CompanyOpen from '../modules/companies/views/open.vue'
import CompanyTransfer from '../modules/companies/views/transfer.vue'

/* Cliente */
import CustomerList from '../modules/customers/views/list'
import CustomerDetail from '../modules/customers/views/detail'
import CustomerCreateEdit from '../modules/customers/views/create-edit.vue'

/* Solicitações */
import RequestsList from '../modules/requests/views/list'


/* Faturamento */
import InvoiceList from '../modules/invoices/views/list'
import InvoiceDetail from '../modules/invoices/views/detail'
import InvoiceCreateEdit from '../modules/invoices/views/create-edit.vue'

/* Usuario */
import UserList from '../modules/users/views/list'
import UserDetail from '../modules/users/views/detail'

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
        children: [
          {
            path: 'tickets/:id/detail',
            component: TicketDetail,
          },
          /*{
            path: 'tickets/:id/detail',
            component: TicketOpen,
          },*/
          {
            path: 'tickets/:id/cancel',
            component: TicketCancel,
          }
        ],
        meta: {
          title: 'Dashboard | BxMED',
        }
      },
      {
        path: '/companies',
        name: 'company_list',
        component: CompanyList,
        beforeEnter: ifAuthenticated,
        meta: {
          title: 'Empresa | BxMED',
        }
      },
      {
        path: '/companies/:id',
        name: 'company_detail',
        component: CompanyDetail,
        beforeEnter: ifAuthenticated,
        meta: {
          title: 'Empresa | BxMED',
        },
        children: [
          {
            path: 'data',
            component: CompanyDetailData,
          },
          {
            path: 'invoices',
            component: CompanyDetailInvoices,
          }
        ],
      },
      {
        path: '/companies/create',
        name: 'company_create',
        component: CompanyCreateEdit,
        beforeEnter: ifAuthenticated,
        meta: {
          title: 'Empresa | BxMED',
        }
      },
      {
        path: '/companies/:id/open',
        name: 'company_open',
        component: CompanyOpen,
        beforeEnter: ifAuthenticated,
        meta: {
          title: 'Empresa | BxMED',
        }
      },
      {
        path: '/companies/:id/transfer',
        name: 'company_transfer',
        component: CompanyTransfer,
        beforeEnter: ifAuthenticated,
        meta: {
          title: 'Empresa | BxMED',
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
        path: '/requests',
        name: 'request_list',
        component: RequestsList,
        beforeEnter: ifAuthenticated,
        children: [
          {
            path: 'tickets/:id/detail',
            component: TicketDetail,
          },
          /*{
            path: 'tickets/:id/detail',
            component: TicketOpen,
          },*/
          {
            path: 'tickets/:id/cancel',
            component: TicketCancel,
          }
        ],
        meta: {
          title: 'Solicitações | BxMED',
        }
      },
      {
        path: '/invoices',
        name: 'invoice_list',
        component: InvoiceList,
        beforeEnter: ifAuthenticated,
        meta: {
          title: 'Faturamento | BxMED',
        }
      },
      {
        path: '/invoices/:id/detail',
        name: 'invoice_detail',
        component: InvoiceDetail,
        beforeEnter: ifAuthenticated,
        meta: {
          title: 'Faturamento | BxMED',
        }
      },
      {
        path: '/invoices/create',
        name: 'invoice_create',
        component: InvoiceCreateEdit,
        beforeEnter: ifAuthenticated,
        meta: {
          title: 'Faturamento | BxMED',
        }
      },
      {
        path: '/invoices/:id',
        name: 'invoice_update',
        component: InvoiceCreateEdit,
        beforeEnter: ifAuthenticated,
        meta: {
          title: 'Faturamento | BxMED',
        }
      },
      {
        path: '/users',
        name: 'user_list',
        component: UserList,
        meta: {
          title: 'Usuarios | BxMED',
        }
      },
      {
        path: '/users/:id/detail',
        name: 'user_detail',
        component: UserDetail,
        beforeEnter: ifAuthenticated,
        meta: {
          title: 'Usuarios | BxMED',
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