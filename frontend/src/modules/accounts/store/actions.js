import { Promise } from "core-js";
import HTTP from "@/utils/http";
const identityApi = new HTTP("identity");
const api = new HTTP("accounts");
import qs from 'qs';

const login = async ({ commit }, payload) => {
  return await identityApi
    .post("oauth/token", qs.stringify(payload))
    .then(async (resp) => {
      commit("AUTH_SUCCESS", resp.data);
      return Promise.resolve(resp);
    })
    .catch((err) => {
      commit("AUTH_LOGOUT");
      return Promise.reject(err);
    });
};

const logout = ({ commit }) => {
  commit("AUTH_LOGOUT");
};

const recoverPassword = async ({ commit }, payload) => {
  return await identityApi
    .post("users/resetpasswordemail", payload)
    .then(async (resp) => {
      commit('RECOVED_PASSWORD', resp.data);
      return Promise.resolve(resp);
    })
    .catch((err) => {
      return Promise.reject(err);
    });
};

const resetPassword = async ({ commit }, payload) => {
  return await api
    .updateAll(`users/resetpassword/${payload.investorId}`, payload)
    .then(async (resp) => {
      commit('RESETTED_PASSWORD', resp.data);
      return Promise.resolve(resp);
    })
    .catch((err) => {
      return Promise.reject(err);
    });
};

const search = async ({ commit }, payload) => {
  let url = 'backoffices';
  if (payload) url = url + '?' + qs.stringify(payload);
  let res = await api
  .get(url)
  .then(async (resp) => {
    commit('SEARCH_ACCOUNT', resp.data);
    return Promise.resolve(resp);
  });
  return res;
};

export default {
  login,
  logout,
  recoverPassword,
  resetPassword,
  search
};
