import { Promise } from "core-js";
import HTTP from "@/utils/http";
const api = new HTTP("customers");
import qs from 'qs';

const search = async ({ commit }, payload) => {
  let res = await api
  .find(payload)
  .then(async (resp) => {
    commit('SEARCH_CUSTOMER', resp.data);
    return Promise.resolve(resp);
  });
  return res;
};

const get = async ({ commit }, id) => {
  let res = await api
  .getOne(id)
  .then(async (resp) => {
    commit('GET_CUSTOMER', resp.data);
    return Promise.resolve(resp);
  });
  return res;
};

const create = async ({ commit }, payload) => {
  let res = await api.post("customers", JSON.stringify(payload.data) ,{
      headers: {
          'Content-Type': 'application/json',
      }
  })  
  .then(async (resp) => {
    commit('CREATE_CUSTOMER', resp.data);
    return Promise.resolve(resp);
  });
  return res;
};

const update = async ({ commit }, payload) => {
  let res = await api
  .put(`customers/${payload.id}`, payload.data)
  .then(async (resp) => {
    commit('UPDATE_CUSTOMER', resp.data);
    return Promise.resolve(resp);
  });
  return res;
};

const select = async ({ commit }, payload) => {
  let res = await api
  .get("customers/select", payload)
  .then(async (resp) => {
    commit('SELECT_CUSTOMER', resp.data);
    return Promise.resolve(resp);
  });
  return res;
};

const remove = async ({ commit }, id) => {
  let res = await api.remove(id)  
  .then(async (resp) => {
    commit('REMOVE_CUSTOMER', {id:id});
    return Promise.resolve(resp);
  });
  return res;
};

const getSummary = async ({ commit }, payload) => {
  let url = "customers/summary";
  if (payload) url = url + '?' + qs.stringify(payload);

  let res = await api
  .get(url)
  .then(async (resp) => {
    commit('GET_SUMMARY', resp.data);
    return Promise.resolve(resp);
  });
  return res;
};


export default {
  search,
  get,
  select,
  create,
  update,
  remove,
  getSummary
};
