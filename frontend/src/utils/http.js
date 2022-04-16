import axios from "axios";
import qs from 'qs';
import store from "@/store";
import router from "@/router";

class HTTP {  
  constructor(url) {
    let startUrl = (url == "identity" ? process.env.VUE_APP_IDENTITY_URL : process.env.VUE_APP_GATEWAY_URL);
    //alert(startUrl);
    var instance = axios.create({
      baseURL: startUrl,
      json: true,
    });

    instance.interceptors.request.use(
      function(config) {
        store.commit("SHOW_LOADING");
        const account = store.state.accounts;
        if (account.isAuthenticated)
          config.headers.Authorization = `Bearer ${account.current.access_token}`;
        return config;
      },
      function(error) {
        return Promise.reject(error);
      }
    );

    instance.interceptors.response.use(
      function(response) {
        store.commit("HIDE_LOADING");
        return response;
      },
      function(error) {
        store.commit("HIDE_LOADING");
        if (error.response) {
          let status = error.response.status;
          if (status === 400) {
            store.commit('SHOW_ERROR', error.response.data);
          }
          else if (status === 401) {
            store.dispatch("accounts/logout").then(() => router.push("/login"));
          }
          else if (status === 403) {
            store.dispatch("accounts/logout").then(() => router.push("/login"));
          }
          else if (status === 500) {
            store.commit('SHOW_ERROR', { Errors: [{ Message: "Ocorreu um erro no sistema, por favor, tente novamente mais tarde." }] });
          }
        }
        return Promise.reject(error);
      }
    );

    instance.url = url;

    instance.getOne = function(id) {
      return this.get(url + `/${id}`);
    };

    instance.getAll = function(path) {
      let _url = null;

      _url = (path === null ? this.url : path);      

      return this.get(_url);
    };

    instance.find = function (data) {
      let url = this.url;
      if (data) url = url + '?' + qs.stringify(data);
      return this.get(url);
    };

    instance.update = function(id, toUpdate) {
      return this.put(`/${id}`, toUpdate);
    };

    instance.create = function(toCreate) {
      return this.post(this.url, toCreate);
    };

    instance.remove = function(id) {
      return this.delete(this.url + `/${id}`);
    };

    return instance;
  }
}

export default HTTP;
