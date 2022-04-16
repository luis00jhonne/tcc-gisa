import HTTP from "@/utils/http";
const api = new HTTP("shareds");

const getStates = async ({ commit }, payload) => {
  let res = await api
  .get("states", payload)
  .then(async (resp) => {
    commit('STATES_SHARED', resp.data);
    return Promise.resolve(resp);
  });
  return res;
};

const getCities = async ({ commit }, payload) => {
  let res = await api
  .get("cities", payload)
  .then(async (resp) => {
    commit('CITIES_SHARED', resp.data);
    return Promise.resolve(resp);
  });
  return res;
};

const getServiceTypes = async ({ commit }, payload) => {
  let res = await api
  .get("servicetypes", payload)
  .then(async (resp) => {
    commit('SERVICETYPES_SHARED', resp.data);
    return Promise.resolve(resp);
  });
  return res;
};

const getBanks = async ({ commit }, payload) => {
  let res = await api
  .get("banks", payload)
  .then(async (resp) => {
    commit('BANKS_SHARED', resp.data);
    return Promise.resolve(resp);
  });
  return res;
};

export default {
  getStates,
  getCities,
  getServiceTypes,
  getBanks
};
