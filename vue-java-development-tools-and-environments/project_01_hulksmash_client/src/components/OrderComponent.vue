<template>
  <div class="container">
    <div class="row">
      <div class="col-lg-9">
        <h4 class="my-2">Adres</h4>
        <div class="row">
          <div class="col-lg-8">
            Ulica:<br />
            <input
              class="form-control"
              type="text"
              v-model.trim="userAddressStreet"
              placeholder="Nazwa"
              :class="{ 'is-invalid': v$.userAddressStreet.$invalid }"
            />
            <div v-if="v$.userAddressStreet.$invalid" class="invalid-feedback">
              {{ getErrorMessage(v$.userAddressStreet, 'Street address') }}
            </div>
          </div>
          <div class="col-lg-4">
            Numer domu:<br />
            <input
              class="form-control"
              type="text"
              v-model.trim="userAddressHomeNumber"
              placeholder="Home number"
              :class="{ 'is-invalid': v$.userAddressHomeNumber.$invalid }"
            />
          </div>
        </div>
        <div class="row">
          <div class="col-lg-4">
            Kod pocztowy:<br />
            <input
              class="form-control"
              type="text"
              v-model="userAddressPostCode"
              placeholder="Post code"
              :class="{ 'is-invalid': v$.userAddressPostCode.$invalid }"
            />
          </div>
          <div class="col-lg-8">
            Miasto:<br />
            <input
              class="form-control"
              type="text"
              v-model="userAddressCity"
              placeholder="City"
              :class="{ 'is-invalid': v$.userAddressCity.$invalid }"
            />
          </div>
        </div>

        <h4 class="mt-4">Kontakt</h4>

        <div class="row">
          <div class="col-lg-6">
            Imię:<br />
            <input
              class="form-control"
              type="text"
              v-model="userAddressName"
              placeholder="Name"
              :class="{ 'is-invalid': v$.userAddressName.$invalid }"
            />
          </div>
          <div class="col-lg-6">
            Nazwisko:<br />
            <input
              class="form-control"
              type="text"
              v-model="userAddressSurname"
              placeholder="Surname"
              :class="{ 'is-invalid': v$.userAddressSurname.$invalid }"
            />
          </div>
        </div>
        <div class="row">
          <div class="col-lg-6">
            Telefon:<br />
            <input
              class="form-control"
              type="text"
              v-model="userAddressPhone"
              placeholder="Phone"
              :class="{ 'is-invalid': v$.userAddressPhone.$invalid }"
            />
          </div>
          <div class="col-lg-6">
            Email:<br />
            <input
              class="form-control"
              type="text"
              v-model="userAddressEmail"
              placeholder="Email"
              :disabled="true"
              :class="{ 'is-invalid': v$.userAddressEmail.$invalid }"
            />
          </div>
        </div>
        <button
          class="btn btn-outline-secondary my-2"
          @click="goToPayment"
          type="button"
        >
          Zamow
        </button>
      </div>
      <div class="col-lg-3">
        <div class="my-4">
          <div class="card" style="width: 18rem">
            <div class="card-body">
              <h5 class="card-title">Koszyk:</h5>
            </div>
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">Produkt</th>
                  <th scope="col">Ilosc</th>
                  <th scope="col">Cena</th>
                </tr>
              </thead>
              <tbody
                v-for="item in shoppingCartList"
                v-bind:key="item.productId"
              >
                <tr>
                  <td>{{ item.productName }}</td>
                  <td>x{{ item.amount }}</td>
                  <td>{{ item.sumPrice }} zł</td>
                </tr>
              </tbody>
            </table>
            <div class="">
              <h6 class="">Promocje:</h6>
              <ul
                v-for="item in promotionsList"
                v-bind:key="item.name"
                class="list-group list-group-flush"
              >
                <li class="list-group-item">{{ item.name }}</li>
              </ul>
            </div>
            <div class="">
              <h6 class="">Suma: {{ sumPrice }}zł</h6>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import UserService from '../services/user.service'
import OrderRateService from '../services/data-services/order-rate.service'
import OrderDataService from '../services/data-services/order-data.service'
import OrderService from '../services/data-services/order.service'
import PaymentService from '../services/data-services/payment.service'
import useVuelidate from '@vuelidate/core'
import { required, minLength, maxLength, email } from '@vuelidate/validators'

export default {
  name: 'OrderComponent',
  components: {},
  props: {
    onUpdate: { type: Function },
    makeLoading: { type: Function }
  },
  data() {
    return {
      pageId: 0,
      userAddressStreet: null,
      userAddressHomeNumber: null,
      userAddressCity: null,
      userAddressName: null,
      userAddressId: null,
      userAddressSurname: null,
      userAddressPhone: null,
      userAddressEmail: null,
      userAddressPostCode: null
    }
  },
  setup() {
    return { v$: useVuelidate() }
  },
  validations() {
    return {
      userAddressStreet: {
        required,
        minLength: minLength(3),
        maxLength: maxLength(20)
      },
      userAddressHomeNumber: {
        required,
        minLength: minLength(3),
        maxLength: maxLength(10)
      },
      userAddressCity: {
        required,
        minLength: minLength(3),
        maxLength: maxLength(20)
      },
      userAddressName: {
        required,
        minLength: minLength(3),
        maxLength: maxLength(20)
      },
      userAddressSurname: {
        required,
        minLength: minLength(3),
        maxLength: maxLength(20)
      },
      userAddressPhone: {
        required,
        minLength: minLength(3),
        maxLength: maxLength(12)
      },
      userAddressEmail: {
        required,
        email,
        minLength: minLength(3),
        maxLength: maxLength(25)
      },
      userAddressPostCode: {
        required,
        minLength: minLength(3),
        maxLength: maxLength(10)
      }
    }
  },
  computed: {
    currentUser() {
      console.log(this.$store.state.auth.user)
      return this.$store.state.auth.user
    },
    shoppingCartList() {
      return this.$store.state.shoppingCart.items
    },
    promotionsList() {
      return this.$store.state.shoppingCart.promotions
    },
    sumPrice() {
      return this.$store.state.shoppingCart.sumPrice
    }
  },
  methods: {
    async goToPayment() {
      if( this.shoppingCartList.length === 0 ) {
        return
      }
      this.makeLoading(true)
      let address = {
        id: this.userAddressId,
        street: this.userAddressStreet,
        homeNumber: this.userAddressHomeNumber,
        city: this.userAddressCity,
        postNumber: this.userAddressPostCode
      }
      await UserService.updateUser({
        id: this.currentUser.id,
        username: this.currentUser.username,
        email: this.currentUser.email,
        phone: this.userAddressPhone,
        name: this.userAddressName,
        surname: this.userAddressSurname,
        address: address
      })

      let orderDetails = []
      let payment = {
        id: null,
        amount: this.sumPrice,
        method: 'ONLINE',
        startData: '26.05.2021',
        paymentData: 'IN PROGRESS'
      }
      await PaymentService.createPayment(payment).then((response) => {
        payment = response.data
      })

      let orderRate = {
        id: null,
        points: 0,
        comments: ''
      }

      await OrderRateService.createOrderRate(orderRate).then((response) => {
        orderRate = response.data
      })

      let order = {
        id: null,
        worker: {
          id: 1
        },
        client: {
          id: this.currentUser.id
        },
        rate: orderRate,
        paymentData: payment,
        realizationStatus: 'PAYMENT',
        comments: ''
      }

      await OrderService.createOrder(order).then((response) => {
        order = response.data
      })

      this.shoppingCartList.forEach((item) => {
        orderDetails.push({
          id: null,
          order: order,
          food: { id: item.productId },
          count: item.amount
        })
      })

      OrderDataService.createOrderData(orderDetails).then(() => {
        this.pageId = 1
      })
      this.pageId = 1
      this.makeLoading(false)
      this.onUpdate(this.pageId, order, this.sumPrice, address)
      this.shoppingCartList = []
      this.promotionsList = []
      this.sumPrice = 0
    },

    getErrorMessage(validation, fieldName) {
      //here it's false because of undefined values in validations (description)
      if (!validation.required) {
        return `The ${fieldName} field is required.`
      } else if (!validation.minLength) {
        return `The ${fieldName} field is too short, limit is ${validation.$params.minLength.min} letters.`
      } else if (!validation.maxLength) {
        return `The ${fieldName} field is too long, limit is ${validation.$params.maxLength.max} letters.`
      }
    }
  },
  mounted() {
    if (this.currentUser) {
      if (this.currentUser['address']) {
        this.userAddressId = this.currentUser['address']['id']
        this.userAddressStreet = this.currentUser['address']['street']
        this.userAddressCity = this.currentUser['address']['city']
        this.userAddressPostCode = this.currentUser['address']['postNumber']
        this.userAddressHomeNumber = this.currentUser['address']['homeNumber']
      }

      this.userAddressName = this.currentUser.name
      this.userAddressSurname = this.currentUser.surname
      this.userAddressPhone = this.currentUser.phone
      this.userAddressEmail = this.currentUser.email
    }
  }
}
</script>

<style>
.form-control {
  height: 3rem;
}
</style>
