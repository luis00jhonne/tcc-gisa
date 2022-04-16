import actions from "./actions";
import getters from "./getters";
import mutations from "./mutations";

const state = {
  current: null,  
  customer:null,
  list: {
    itens: []
  },
  select: []
};

export default {
  namespaced: true,
  state,
  actions,
  getters,
  mutations,
};
