const initialState = {
  items: [],
  promotions: [],
  sumPrice: 0,
}

export const shoppingCart = {
  namespaced: true,
  state: initialState,
  actions: {
    addItem({ commit }, product) {
      commit('addItem', product);

    },
    calcPromotions({commit}, isPromotion1Active, isPromotion2Active, isPromotion3Active) {
      commit('calcPromotions', isPromotion1Active, isPromotion2Active, isPromotion3Active);
    }
  },
  mutations: {
    addItem(state, product) {
      let productExists = false;
      state.items.forEach((item) => {
        if (item.productId === product.id) {
          productExists = true
          item.amount += 1
          item.sumPrice += item.singlePrice
        }
      })
      if (!productExists) {
        state.items.push({
          productId: product.id,
          productName: product.name,
          productCategory: product.categoryDto.name,
          amount: 1,
          sumPrice: product.prize,
          singlePrice: product.prize
        });
      }
    },
    calcPromotions(state, isPromotion1Active, isPromotion2Active, isPromotion3Active) {

      let pizzaArray = state.items.filter((a) => a.productCategory === 'Pizza').sort((a, b) => a.singlePrice - b.singlePrice);
      let juiceArray = state.items.filter((a) => a.productCategory === 'Napoje').sort((a, b) => a.singlePrice - b.singlePrice);
      console.log("pizza")
      console.log(pizzaArray)
      console.log("napoje")
      console.log(juiceArray)

      let cashSum = 0;
      let promotion1Discount = 0;
      let promotion2Discount = 0;
      // let promotion3Discount = 0;

      if (isPromotion1Active) {
        pizzaArray.forEach((item) => {
          if (item.amount % 2 === 0) {
            promotion1Discount = item.sumPrice / 2
          }
        })

      }
      if (isPromotion2Active) {
        if (pizzaArray.length > 1) {
          if (juiceArray.length > 0)
            promotion2Discount += juiceArray[0].singlePrice
        }
      }

      state.items.forEach((item) => {
        cashSum += parseFloat(item.sumPrice)
      })
      cashSum -= promotion1Discount
      cashSum -= promotion2Discount

      if (isPromotion3Active && cashSum > 99) {
        cashSum -= cashSum * 0.20
        // promotion3Discount = cashSum * 0.20
      }
      state.sumPrice = Math.round(cashSum * 100) / 100
    }
  }
};
