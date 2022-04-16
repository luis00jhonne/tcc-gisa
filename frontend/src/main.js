import Vue from 'vue'
import App from './App.vue'
import BootstrapVue from 'bootstrap-vue'
import router from './router'
import Breadcrumbs from './modules/shareds/components/bread_crumbs'
import store from './store';
import VueFeather from 'vue-feather';
import VueTheMask from "vue-the-mask";
import VueToastify from "vue-toastify";
import "@/utils/filters";
import "@/utils/translate";

import PxCard  from './modules/shareds/components/Pxcard.vue'
Vue.component(PxCard.name, PxCard)

// Import Theme scss
import './assets/scss/app.scss'

Vue.use(VueFeather);
Vue.use(BootstrapVue);
Vue.use(VueTheMask);
Vue.use(VueToastify, {
  my: "settings",
  position:"top-right",
  successDuration:3000,
});

Vue.component('Breadcrumbs', Breadcrumbs);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')