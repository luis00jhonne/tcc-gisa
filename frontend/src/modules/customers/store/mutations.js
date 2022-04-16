const SEARCH_CUSTOMER = (state, model) => {
    state.list = model;
};

const SELECT_CUSTOMER = (state, model) => {
    state.select = model;
};
const SELECT_STATES = (state, model) => {
  state.states = model;
};

const SELECT_CITIES = (state, model) => {
  state.cities = model;
};

const GET_CUSTOMER = (state, model) => {
  state.current = model;
};

const CREATE_CUSTOMER = (state, model) => {
};


const UPDATE_CUSTOMER = (state, model) => {
};

const REMOVE_CUSTOMER = (state, model) => {
};

const CLEAR_CUSTOMER = (state, model) => {
  state.current = {
    customer:null
  };
};

const GET_SUMMARY = (state, model) => {
  state.summary = model;
};

export default {
    SEARCH_CUSTOMER,
    SELECT_CUSTOMER,
    GET_CUSTOMER,
    CREATE_CUSTOMER,
    UPDATE_CUSTOMER,
    REMOVE_CUSTOMER,
    CLEAR_CUSTOMER,
    SELECT_STATES,
    SELECT_CITIES,
    GET_SUMMARY
};