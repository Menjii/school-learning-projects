import { createStore } from "vuex";
import { auth } from "./auth.module";
import { shoppingCart } from "./shopping-cart.module";

const store = createStore({
  modules: {
    auth,
    shoppingCart,
  },
});

export default store;
