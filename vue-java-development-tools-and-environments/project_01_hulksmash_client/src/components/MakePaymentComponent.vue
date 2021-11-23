<template>
  <div class="container">
    <div class="card border-secondary my-4 shadow p-3 mb-5 bg-light rounded">
      <center class="">
        <h4>DO ZAPŁATY: </h4>
        <h1> {{this.sumPrice}} zł</h1>
      </center>
      <button class="btn btn-outline-secondary" @click="onStartPayment">Zapłać</button>
    </div>
  </div>
</template>

<script>

import {number} from "yup";
import OrderService from '../services/data-services/order.service'
import PaymentService from '../services/data-services/payment.service'

export default {
  name: 'MakePaymentComponent',
  computed: {},
  props: {
    sumPrice: number,
    orderDetails: {},
    onPayment: {type: Function},
    makeLoading: {type: Function},
  },
  data() {
    return {

    }
  },
  methods: {
    async onStartPayment() {
      this.makeLoading(true)
      let orderDetails = this.orderDetails
      let payment = this.orderDetails.paymentData
      let currentdate = new Date();
      let datetime = currentdate.getDate() + "/"
          + (currentdate.getMonth()+1)  + "/"
          + currentdate.getFullYear() + " @ "
          + currentdate.getHours() + ":"
          + currentdate.getMinutes() + ":"
          + currentdate.getSeconds();
      payment.paymentData = datetime
      orderDetails.realizationStatus = "ZAPŁACONE"

      await OrderService.updateOrder(orderDetails)
      await PaymentService.createPayment(payment)

      this.makeLoading(false)
      this.onPayment(2, orderDetails)
    }
  },
  mounted() {
  }
}
</script>

<style>

</style>
