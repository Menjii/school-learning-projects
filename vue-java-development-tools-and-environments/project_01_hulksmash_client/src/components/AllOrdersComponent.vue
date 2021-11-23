<template>
  <div class="container">
    <center
        v-if="isLoading"
    >
      <br /><br /><br /><br /><br /><br /><br />
      <ProgressSpinner

      />
    </center>
    <div
      v-for="order in allOrders.filter((o) => !o['products'].isEmpty)"
      v-bind:key="order.id"
      class="card border-secondary my-4 shadow p-3 mb-5 bg-light rounded"
    >
      <div class="card-header shadow-sm p-3 mb-2 bg-light-lt rounded">
        {{ order.order.realizationStatus }} - {{ order.client.username }}
      </div>
      <div class="row">
        <div class="col-lg-6">
          <div class="card-body">
            <h5 class="card-title">Adres</h5>
            <p class="card-text">Miasto: {{ order.client.address.city }}</p>
            <p class="card-text">Ulica: {{ order.client.address.street }}</p>
            <p class="card-text">
              Nr. domu: {{ order.client.address.postNumber }}
            </p>
          </div>

          <div class="card-body">
            <h5 class="card-title">Dane klienta</h5>
            <p class="card-text">Email: {{ order.client.email }}</p>
            <p class="card-text">
              Imie i nazwisko: {{ order.client.name }}
              {{ order.client.surname }}
            </p>
            <p class="card-text">
              Metoda platnosci: {{ order.order.paymentData.method }}
            </p>
            <p class="card-text">
              Data platnosci: {{ order.order.paymentData.paymentData }}
            </p>
          </div>
        </div>

        <div class="col-lg-6">
          <div class="card-body">
            <h5 class="card-title">Zamowione dania</h5>
            <div class="card-body" v-for="product in order['products']" v-bind:key="product.id">
              <p class="card-text">
                Id dania: {{ product.food.categoryDto.id }}
              </p>
              <p class="card-text">
                Typ dania: {{ product.food.categoryDto.name }}
              </p>
              <p class="card-text">Nazwa dania: {{ product.food.name }}</p>
              <p class="card-text">Ilosc: {{ product.count }}</p>
            </div>
          </div>
        </div>

        <div class="col-lg-12">
          <button
            @click="
              () =>
                changeStatus(
                  order.client.id,
                  order.order,
                  'W TRAKCIE REALIZACJI'
                )
            "
            class="mx-2 my-2 btn btn-outline-success"
            type="button"
          >
            Status: W trakcie realizacji
          </button>

          <button
            @click="
              () => changeStatus(order.client.id, order.order, 'W DOSTAWIE')
            "
            class="mx-2 my-2 btn btn-outline-success"
            type="button"
          >
            Status: W dostawie
          </button>

          <button
            @click="
              () => changeStatus(order.client.id, order.order, 'DOSTARCZONE')
            "
            class="mx-2 my-2 btn btn-outline-success"
            type="button"
          >
            Status: Dostarczone
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import OrderRateService from '../services/data-services/order-rate.service'
// import OrderDataService from '../services/data-services/order-data.service'
import ProgressSpinner from 'primevue/progressspinner';
import OrderService from '../services/data-services/order.service'

export default {
  name: 'AllOrdersComponent',
  components: {
    ProgressSpinner
  },
  computed: {},
  data() {
    return {
      allOrders: [],
      isLoading: true
    }
  },
  methods: {
    changeStatus(clientId, order, status) {
      order.client = {
        id: clientId
      }
      order.realizationStatus = status

      OrderService.updateOrder(order).then(() => {})
      window.location.reload()
    }
  },
  mounted() {
    OrderService.getAll().then((response) => {
      this.allOrders = response.data
      console.log(this.allOrders)
      this.isLoading = false
    })
  }
}
</script>

<style scoped>
.form-control {
  height: 3rem;
}

.single-order {
  padding: 20px;
  border: solid 1px #000;
}

.card-body{
  background-color: rgba(58, 58, 58, 0.822);
  border-radius: 1em;
  box-shadow: 0 5px 8px rgba(0,0,0,0.6);
  margin: 1em;
  max-width: 90%;
}

.card-body p{
  color: white;
}


.card-title{
  color:rgb(2, 184, 84);
  border-bottom: 2px solid rgb(2, 184, 84);
  line-height: 30px;
  width: 10em;
}

</style>
