<template>
<div class="container my-3">
  <div class="row">
    <div class="col-sm">
      <div class="cir" :style="pageId === 0 ? 'background-color: black' : '' ">

      </div>
    </div>
    <div class="col-sm">
      <div class="cir" :style="pageId === 1 ? 'background-color: black' : '' ">

      </div>
    </div>
    <div class="col-sm">
      <div class="cir" :style="pageId === 2 ? 'background-color: black' : '' ">

      </div>
    </div>
  </div>
  <br />
  <div class="row">
    <div class="col-sm">
      <h5>Dane zamówienia</h5>
    </div>
    <div class="col-sm">
      <h5>Zapłać</h5>
    </div>
    <div class="col-sm">
      <h5>Twoje zamówienie</h5>
    </div>
  </div>
</div>
  <center
      v-if="isLoading"
  >
  <ProgressSpinner

  />
  </center>
  <div
    v-else
  >
    <OrderComponent
        v-if="pageId === 0"
        :onUpdate="onUpdate"
        :makeLoading="makeLoading"
    />

    <MakePaymentComponent
      v-else-if="pageId === 1"
      :sumPrice="sumPrice"
      :onPayment="onPayment"
      :makeLoading="makeLoading"
      :orderDetails="orderDetails"/>

      <OrderDetailsComponent
        v-else-if="pageId === 2"
        :orderDetails="orderDetails"
        :address="address"
      />
  </div>
</template>

<script>

import OrderComponent from "@/components/OrderComponent";
import ProgressSpinner from 'primevue/progressspinner';
import MakePaymentComponent from "@/components/MakePaymentComponent";
import OrderDetailsComponent from "@/components/OrderDetailsComponent";

export default {
  name: 'MakeOrderComponent',
  components: {OrderDetailsComponent, MakePaymentComponent, OrderComponent, ProgressSpinner},
  computed: {},
  data() {
    return {
      pageId: 0,
      orderDetails: null,
      sumPrice: 1,
      isLoading: false,
      address: {},
    }
  },
  methods: {
    onUpdate(page, order, price, address) {
      console.log(order)
      this.sumPrice = price
      this.orderDetails = order
      this.pageId = page
      this.address = address
    },
    onPayment(page, order) {
      this.pageId = page
      this.orderDetails = order
    },
    makeLoading(status) {
      this.isLoading = status
    }
  },
  mounted() {
  }
}
</script>

<style scoped>

.cir{
  background-color: rgb(230, 230, 230);
  position: relative;
  left: 40%;
  width: 80px;
  height: 80px;
  border-radius: 80px;
  box-shadow: 0 3px 3px rgba(0,0,0,0.6);
  border: 1px solid rgb(97, 97, 97);
  
}
.col-sm h5{
  text-align: center;
}
</style>
