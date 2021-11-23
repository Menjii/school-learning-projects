<template>
  <div className="container">
    <div class="row">
      <div class="col-lg-9">
        <button
          @click="addNewCategory"
          class="mx-3 my-3 btn btn-outline-secondary"
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
          v-for="category in categoriesList"
          v-bind:key="category.id"
          class="add-category-card"
          @click="() => onCategoryClicked(category)"
        >
          <template #content>
            {{ category.name }}
          </template>
        </Card>
      </div>
      <div class="col-lg-3">
        Nazwa kategorii <br />
        <input
          class="form-control"
          type="text"
          v-model="categoryName"
          placeholder="Nazwa"
        />
        <br />
        <label>
          Opis kategorii<br />
          <textarea
            v-model="categoryDesc"
            class="form-control"
            id="exampleFormControlTextarea1"
            rows="3"
          ></textarea>
        </label>
        <br />
        <button
          @click="onSaveClick"
          class="my-3 btn btn-outline-secondary"
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
import Card from 'primevue/card'
import ProgressSpinner from 'primevue/progressspinner';

export default {
  name: 'AddCategoryComponent',
  components: {
    Card,
    ProgressSpinner
  },
  data() {
    return {
      categoryId: null,
      categoryName: null,
      categoryDesc: null,
      isLoading: true,
      categoriesList: [],
      saveButtonLabel: 'Stwórz'
    }
  },
  methods: {
    addNewCategory() {
      this.categoryId = null
      this.categoryName = null
      this.categoryDesc = null
      this.saveButtonLabel = 'Stwórz'
    },
    onCategoryClicked(category) {
      this.categoryId = category.id
      this.categoryName = category.name
      this.categoryDesc = category.desc
      this.saveButtonLabel = 'Zapisz'
    },
    onSaveClick() {
      CategoryService.createNewCategory({
        id: this.categoryId,
        name: this.categoryName,
        desc: this.categoryDesc
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
    fetchCategories() {
      CategoryService.getAll().then(
        (response) => {
          this.categoriesList = response.data
          this.isLoading = false
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
