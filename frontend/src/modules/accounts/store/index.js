import actions from "./actions";
import getters from "./getters";
import mutations from "./mutations";

let account = JSON.parse(localStorage.getItem("account"));

const state = {
  current: account, 
  isAuthenticated: account && account.isAuthenticated,
  list:[]
};

export default {
  namespaced: true,
  state,
  actions,
  getters,
  mutations,
};
