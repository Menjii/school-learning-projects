<template>
  <div class="container">
    <br /><br /><br />
    <header class="">
      <h3>
        <strong>{{ currentUser.username }}</strong> Profile
      </h3>
    </header>
    <div class="">
    <p>
      <strong>Email:</strong>
      {{ currentUser.email }}
    </p>
    </div>
    <div class="jumbotron">
    <strong>Authorities:</strong>
    <ul>
      <li v-for="role in currentUser.roles" :key="role">{{ role }}</li>
    </ul>
    </div>
    <h3>Twoje zamówienia</h3>
    <center
        v-if="isLoading"
    >
      <br /><br /><br /><br /><br /><br /><br />
      <ProgressSpinner

      />
      <br /><br /><br /><br /><br /><br /><br />
    </center>
    <div
      v-for="order in allOrders.filter((o) => !o['products'].isEmpty)"
      v-bind:key="order.id"
      class="single-order card border-secondary my-4 shadow p-3 mb-5 bg-light rounded"
    >
      <div class="card-header shadow-sm p-3 mb-2 bg-light-lt rounded">
        {{ order.order.realizationStatus }} - {{ order.client.username }}
      </div>
      <div class="row">
        <div class="col-lg-6">
          <div class="card-body">
            <h5 class="card-title">Address</h5>
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
      </div>
    </div>
  </div>
</template>

<script>
import OrderService from '@/services/data-services/order.service'
import ProgressSpinner from 'primevue/progressspinner';
export default {
  name: 'Profile',
  components: {
    ProgressSpinner
  },
  data() {
    return {
      allOrders: [],
      isLoading: true
    }
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login')
    } else {
      OrderService.getAllUser(this.currentUser.id).then((response) => {
        this.allOrders = response.data
        console.log(this.allOrders)
        this.isLoading = false
      })
    }
  }
}
</script>

<style scoped>
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
