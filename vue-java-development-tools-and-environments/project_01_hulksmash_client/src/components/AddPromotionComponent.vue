<template>
  <div className="container">
    <Card class="promotion-card">
      <template #title> Pizza 50/50 </template>
      <template #content> Kup jedną pizzę - drugą dostajesz gratis </template>
      <template #footer>
        <span v-if="isPromotion1Active"> Aktywna </span>

        <span v-else> Wyłączona </span>
        <br />
        <button
          @click="() => changePromotionStatus(1)"
          class="my-3 btn btn-outline-secondary"
          type="button"
        >
          Zmień status
        </button>
      </template>
    </Card>
    <Card class="promotion-card">
      <template #title> Pizza 2+napój </template>
      <template #content> Przy zakupie dwóch pizz, napój gratis </template>
      <template #footer>
        <span v-if="isPromotion2Active"> Aktywna </span>

        <span v-else> Wyłączona </span>

        <br />
        <button
          @click="() => changePromotionStatus(2)"
          class="my-3 btn btn-outline-secondary"
          type="button"
        >
          Zmień status
        </button>
      </template>
    </Card>
    <Card class="promotion-card">
      <template #title> 20% taniej </template>
      <template #content> Przy zakupie powyżej 100zł, 20% taniej </template>
      <template #footer>
        <span v-if="isPromotion3Active"> Aktywna </span>

        <span v-else> Wyłączona </span>
        <br />
        <button
          @click="() => changePromotionStatus(3)"
          class="my-3 btn btn-outline-secondary"
          type="button"
        >
          Zmień status
        </button>
      </template>
    </Card>
  </div>
</template>

<script>
import Card from 'primevue/card'
import PromotionService from '../services/data-services/promotion.service'
// import CategoryService from "@/services/data-services/category.service";

export default {
  name: 'PromotionComponent',
  components: {
    Card
  },
  data() {
    return {
      isPromotion1Active: false,
      isPromotion2Active: false,
      isPromotion3Active: false
    }
  },
  methods: {
    changePromotionStatus(id) {
      PromotionService.changePromotionStatus(id).then((response) => {
        console.log(response.data)
        this.refresh()
      })
    },
    refresh() {
      PromotionService.getPromotionById(1).then((response) => {
        this.isPromotion1Active = response.data.active
      })
      PromotionService.getPromotionById(2).then((response) => {
        this.isPromotion2Active = response.data.active
      })
      PromotionService.getPromotionById(3).then((response) => {
        this.isPromotion3Active = response.data.active
      })
    }
  },
  beforeMount() {
    this.refresh()
  }
}
</script>

<style lang="scss" scoped>
.promotion-card {
  width: 29%;
  margin: 2%;
  display: inline-block;
  border-radius: 5px;
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
  -webkit-transition: all 0.6s cubic-bezier(0.165, 0.84, 0.44, 1);
  transition: all 0.6s cubic-bezier(0.165, 0.84, 0.44, 1);

  &:hover {
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
    -webkit-transition: all 0.6s cubic-bezier(0.165, 0.84, 0.44, 1);
    transition: all 0.6s cubic-bezier(0.165, 0.84, 0.44, 1);
  }
}
</style>
