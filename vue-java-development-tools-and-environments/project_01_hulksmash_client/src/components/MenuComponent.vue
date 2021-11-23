<template>
  <div className="container">
    <div class="row">
      <div class="col-lg-10">
        <div class="text-center my-4">
          <button
            @click="fetchProducts"
            class="mx-2 my-1 btn btn-outline-secondary"
            type="button"
          >
            Wszystkie
          </button>
          <button
            class="mx-2 btn btn-outline-secondary"
            type="button"
            v-for="category in categoriesList"
            v-bind:key="category.id"
            @click="() => fetchProductsByCategory(category)"
          >
            {{ category.name }}
          </button>
        </div>
        <center
            v-if="isLoading"
        >
          <br /><br /><br /><br /><br /><br /><br />
          <ProgressSpinner

          />
        </center>
        <Card
          v-for="product in productsList"
          v-bind:key="product.id"
          class="product"
          @click="() => onCategoryClicked(product)"
        >
          <template #header>
            <img class="product__image" :src="product.imageHref" />
          </template>
          <template #content>
            <div class="row">
              <div class="col-lg-8">
                <div class="product__title">{{ product.name }}</div>
                <small class="product__category">{{
                  product.categoryDto.name
                }}</small>
                <div class="product__description">
                  {{ product.description }}
                </div>
                <button
                  class="btn-info"
                  type="button"
                  @click="() => onOpenModal(product)"
                >
                  Wiecej informacji
                </button>
              </div>
              <div class="col-lg-4">
                <div class="product__price">
                  <span class="product__price__amount">{{
                    product.prize
                  }}</span>
                  zł
                </div>
                <div
                  class="product__add-to-card"
                  @click="() => addToStore(product)"
                >
                  Koszyk
                </div>
              </div>
            </div>
          </template>
        </Card>
        <Modal
          :selectedProduct="selectedProduct"
          v-show="isModalVisible"
          @close="isModalVisible = false"
        />
      </div>
      <div class="col-lg-2">
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
            <div class="card-body">
              <h6 class="card-title">Promocje:</h6>
              <ul
                v-for="item in promotionsList"
                v-bind:key="item.name"
                class="list-group list-group-flush"
              >
                <li class="list-group-item">{{ item.name }}</li>
              </ul>
            </div>
            <div class="card-body">
              <h6 class="card-title">Suma: {{ sumPrice }}zł</h6>
            </div>
            <router-link to="'/store/order" class="nav-link">
              <button class="btn btn-outline-secondary" type="button">
                Do koszyka
              </button>
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CategoryService from '../services/data-services/category.service'
import ProtuctService from '../services/data-services/product.service'
import Card from 'primevue/card'
import PromotionService from '@/services/data-services/promotion.service'
import Modal from '../components/ModalComponent.vue'
import ProgressSpinner from 'primevue/progressspinner';

export default {
  name: 'AddProductComponent',
  components: {
    Card,
    Modal,
    ProgressSpinner
  },
  computed: {
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
  data() {
    return {
      productId: null,
      productName: null,
      productPrice: null,
      productCategory: null,
      productDesc: null,
      productsList: [],
      categoriesList: [],
      saveButtonLabel: 'Stwórz',
      isPromotion1Active: false,
      isPromotion2Active: false,
      isPromotion3Active: false,
      isModalVisible: false,
      isLoading: true,
      selectedProduct: {}
    }
  },
  methods: {
    addToStore(product) {
      this.$store.dispatch('shoppingCart/addItem', product)
      this.$store.dispatch(
        'shoppingCart/calcPromotions',
        this.isPromotion1Active,
        this.isPromotion2Active,
        this.isPromotion3Active
      )
    },
    addNewCategory() {
      this.productId = null
      this.productPrice = null
      this.productCategory = null
      this.productName = null
      this.productDesc = null
      this.saveButtonLabel = 'Stwórz'
    },
    onCategoryClicked(product) {
      this.productId = product.id
      this.productName = product.name
      this.productPrice = product.prize
      this.productCategory = product.categoryDto
      this.productDesc = product.description
      this.saveButtonLabel = 'Zapisz'
    },
    fetchProducts() {
      ProtuctService.getAll().then(
        (response) => {
          this.productsList = response.data
          this.isLoading = false
        },
        (error) => {
          this.productsList =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString()
        }
      )
    },
    fetchProductsByCategory(category) {
      ProtuctService.getByCategory(category.id).then(
        (response) => {
          this.productsList = response.data
        },
        (error) => {
          this.productsList =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString()
        }
      )
    },
    fetchCategories() {
      CategoryService.getAll().then(
        (response) => {
          this.categoriesList = response.data
        },
        (error) => {
          this.categoriesList =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString()
        }
      )
    },
    onOpenModal(product) {
      this.selectedProduct = product
      this.isModalVisible = true
    }
  },
  beforeMount() {
    this.fetchProducts()
    this.fetchCategories()

    this.promotionsList = []
    PromotionService.getPromotionById(1).then((response) => {
      this.isPromotion1Active = response.data.active
      if (
        this.isPromotion1Active &&
        !this.promotionsList.some((e) => e.name === 'Pizza 50/50')
      )
        this.promotionsList.push({ name: 'Pizza 50/50' })
    })
    PromotionService.getPromotionById(2).then((response) => {
      this.isPromotion2Active = response.data.active
      if (
        this.isPromotion2Active &&
        !this.promotionsList.some((e) => e.name === '2xPizza=Napój')
      )
        this.promotionsList.push({ name: '2xPizza=Napój' })
    })
    PromotionService.getPromotionById(3).then((response) => {
      this.isPromotion3Active = response.data.active
      if (
        this.isPromotion3Active &&
        !this.promotionsList.some((e) => e.name === '20%zniżka')
      )
        this.promotionsList.push({ name: '20%zniżka' })
    })
  }
}
</script>

<style lang="scss" scoped>
.product {
  width: 29%;
  height: 350px;
  margin: 2%;
  display: inline-block;
  border-radius: 5px;
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
  -webkit-transition: all 0.6s cubic-bezier(0.165, 0.84, 0.44, 1);
  transition: all 0.6s cubic-bezier(0.165, 0.84, 0.44, 1);

  &__image {
    height: 150px;
    object-fit: cover;
  }

  &__description {
    color: #777;
    font-size: 0.8em;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  &__add-to-card {
    text-align: right;
    padding-top: 20px;
    cursor: pointer;
    font-size: 14px;
  }

  &__price {
    text-align: right;

    &__amount {
      font-size: 25px;
      font-weight: 400;
    }
  }

  &:hover {
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
    -webkit-transition: all 0.6s cubic-bezier(0.165, 0.84, 0.44, 1);
    transition: all 0.6s cubic-bezier(0.165, 0.84, 0.44, 1);
  }

  &:hover::after {
    opacity: 1;
  }
}

.btn-info {
  color: white;
  background: #bdbebd;
  border: 1px solid #bdbebd;
  border-radius: 2px;
  padding: 2px 4px;
  margin: 15px 0px;
}
</style>
