const STATES_SHARED = (state, model) => {
    state.states = model;
};

const CITIES_SHARED = (state, model) => {
    state.cities = model;
};

const SERVICETYPES_SHARED = (state, model) => {
    state.serviceTypes = model;
};

const BANKS_SHARED = (state, model) => {
    state.banks = model;
};

export default {
    STATES_SHARED,
    CITIES_SHARED,
    SERVICETYPES_SHARED,
    BANKS_SHARED
};
