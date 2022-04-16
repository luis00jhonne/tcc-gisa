import VueJwtDecode from 'vue-jwt-decode'

const hasToken = (model) => {
  if (model.access_token) {
    return true;
  }
  return false;
};

const AUTH_SUCCESS = (state, model) => {
  console.log("AUTH_SUCCESS");
  let isAuth = hasToken(model);
  if (!isAuth) {
    isAuth = false;
  } else {
    let data = VueJwtDecode.decode(model.access_token);

    model = Object.assign(model, 
      { 
        isAuthenticated: isAuth, 
        name: data.name, 
        id: data.uid, 
        email: data.email
      });
    
    state.isAuthenticated = isAuth;
    state.current = model;
    localStorage.setItem("account", JSON.stringify(model));
  }
};

const AUTH_LOGOUT = (state) => {
  console.log("AUTH_LOGOUT");
  state.current = null;
  state.user = null;
  state.isAuthenticated = false;
  localStorage.removeItem("account");  
};

const SEARCH_ACCOUNT = (state, model) => {
  state.list = model;
};

export default {
  AUTH_SUCCESS,
  AUTH_LOGOUT,
  SEARCH_ACCOUNT
};
