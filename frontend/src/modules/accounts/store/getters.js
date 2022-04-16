const current = state => { return (state.current === null ? {isAuthenticated: false } : Object.assign(state.current, {isAuthenticated: state.isAuthenticated})); };
const isAuthenticated = state => (state.isAuthenticated === null ? false : state.isAuthenticated);

export default {
  current,
  isAuthenticated
};
