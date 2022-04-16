import Vue from "vue";
import Vuex from "vuex";

// import 'es6-promise/auto';
import layout from '../modules/shareds/store/layout'
import menu from '../modules/shareds/store/menu'
import accounts from "../modules/accounts/store";
import customers from "../modules/customers/store";
import home from "../modules/home/store"; 
import shareds from "../modules/shareds/store";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    layout,
    menu,
    accounts: accounts,
    customers: customers,
    home: home,
    shareds: shareds,
  },
  state: {
    isLoading: true,
    errors: [],
    search: null
  },
  mutations: {
    SHOW_LOADING() {
      this.state.isLoading = true;
    },
    HIDE_LOADING() {
      this.state.isLoading = false;
    },
    SET_SEARCH(state, text) {
      state.search = text;
    },
    SHOW_ERROR(state, data) {
      let list = this.state.errors;
      if (data && data.Errors) {
        for (var i = 0, len = data.Errors.length; i < len; i++) {
          list.push(data.Errors[i].Message);
        }
      } else {
        list.push("Ocorreu um erro no sistema, por favor, tente novamente.");
      }
      alert(list.join('\n'));
      setTimeout(function () {
        list.shift();
      }, 4000);
    }
  },
  actions: {
  }
});