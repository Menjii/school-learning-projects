<template>
  <div className="container">

    <div class="row">
      <div class="col-lg-10">
        <div class="text-center my-4 mb-4">
          <button
            @click="fetchProducts"
            class="mx-2 my-1 btn btn-outline-secondary"
            type="button"
          >
            Wszystkie
          </button>
          <button
            v-for="category in categoriesList"
            v-bind:key="category.id"
            @click="() => fetchProductsByCategory(category)"
            type="button"
            class="mx-2 my-1 btn btn-outline-secondary"
          >
            {{ category.name }}
          </button>
        </div>
        <button
          @click="addNewCategory"
          class="mx-4 my-3 btn btn-outline-secondary"
          type="button"
        >
          Stwórz nową
        </button>
        <br />
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
          class="add-category-card"
          @click="() => onCategoryClicked(product)"
        >
          <template #content>
            <div>{{ product.name }}</div>
            <small>{{ product.categoryDto.name }}</small>
            <br />
            <small class="text-muted">{{ product.description }}</small>
          </template>
        </Card>
      </div>
      <div class="col-lg-2 mt-4">
        Nazwa produktu <br />
        <input
          class="form-control"
          type="text"
          v-model="productName"
          placeholder="Nazwa"
        />
        <br />
        Zdjęcie produktu <br />
        <input
            class="form-control"
            type="text"
            v-model="productFotoLink"
            placeholder="Link do zdjęcia"
        />
        <br />
        Cena produktu <br />
        <input
          class="form-control"
          type="text"
          v-model="productPrice"
          placeholder="Cena"
        />
        <br />
        Kategoria produktu <br />
        <b-form-select
          v-model="productCategory"
          :options="categoriesList"
          optionLabel="name"
        ></b-form-select>
        <Dropdown
          v-model="productCategory"
          :options="categoriesList"
          optionLabel="name"
        ></Dropdown>
        <br />
        <label class="mt-2">
          Opis produktu<br />
          <textarea
            v-model="productDesc"
            class="form-control"
            id="exampleFormControlTextarea1"
            rows="3"
          ></textarea>
        </label>
        <button
          @click="onSaveClick"
          class="my-1 btn btn-outline-secondary"
          type="button"
        >
          {{ saveButtonLabel }}
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import CategoryService from '../services/data-services/category.service'
import ProtuctService from '../services/data-services/product.service'
import ProgressSpinner from 'primevue/progressspinner';
import Card from 'primevue/card'
import Dropdown from 'primevue/dropdown'

export default {
  name: 'AddProductComponent',
  components: {
    Card,
    Dropdown,
    ProgressSpinner
  },
  data() {
    return {
      productId: null,
      productName: null,
      productPrice: null,
      productCategory: null,
      productFotoLink: null,
      productDesc: null,
      productsList: [],
      categoriesList: [],
      saveButtonLabel: 'Stwórz',
      isLoading: true
    }
  },
  methods: {
    addNewCategory() {
      this.productId = null
      this.productPrice = null
      this.productCategory = null
      this.productFotoLink = null
      this.productName = null
      this.productDesc = null
      this.saveButtonLabel = 'Stwórz'
    },
    onCategoryClicked(product) {
      this.productId = product.id
      this.productName = product.name
      this.productPrice = product.prize
      this.productCategory = product.categoryDto
      this.productFotoLink = product.imageHref
      this.productDesc = product.description
      this.saveButtonLabel = 'Zapisz'
    },
    onSaveClick() {
      ProtuctService.saveProduct({
        id: this.productId,
        prize: this.productPrice,
        categoryDto: this.productCategory,
        imageHref: this.productFotoLink,
        name: this.productName,
        description: this.productDesc
      }).then(
        (response) => {
          this.content = response.data
          window.location.reload()
        },
        (error) => {
          this.content =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString()
        }
      )
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
    }
  },
  beforeMount() {
    this.fetchProducts()
    this.fetchCategories()
  }
}
</script>

<style lang="scss" scoped>
.add-category-card {
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
