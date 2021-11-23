<script>
export default {
  name: 'Modal',
  props: ['selectedProduct'],
  methods: {
    close() {
      this.$emit('close')
    }
  }
}
</script>

<template>
  <transition name="modal-fade">
    <div class="modal-backdrop">
      <div
        class="modal"
        role="dialog"
        aria-labelledby="modalTitle"
        aria-describedby="modalDescription"
      >
        <header class="modal-header" id="modalTitle">
          <slot name="header"> {{ selectedProduct.name }} </slot>
          <button
            type="button"
            class="btn-close"
            @click="close"
            aria-label="Close modal"
          >
            x
          </button>
        </header>

        <section class="modal-body" id="modalDescription">
          <img class="pizza-slide" :src="selectedProduct.imageHref" />
          <h5 class="my-3">{{ selectedProduct.name }}</h5>
          <div class="text-wrap">
            <h6>Kategoria</h6>
            <slot> {{ selectedProduct.description }} </slot>
          </div>
        </section>

        <footer class="modal-footer">
          <button
            type="button"
            class="btn-green"
            @click="close"
            aria-label="Close modal"
          >
            Zamknij
          </button>
        </footer>
      </div>
    </div>
  </transition>
</template>

<style>
.modal-backdrop {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.3);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal {
  background: #ffffff;
  box-shadow: 2px 2px 20px 1px;
  overflow-x: auto;
  display: flex;
  flex-direction: column;

  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 99;

  width: 100%;
  max-width: 600px;
  max-height: 90%;
  background-color: #fff;
  border-radius: 16px;

  padding: 25px;
}

.modal-header,
.modal-footer {
  padding: 15px;
  display: flex;
}

.modal-header {
  position: relative;
  border-bottom: 1px solid #eeeeee;
  color: #6c757d;
  justify-content: space-between;
}

.modal-footer {
  border-top: 1px solid #eeeeee;
  flex-direction: column;
}

.modal-body {
  position: relative;
  padding: 20px 10px;
  color: #6c757d;
  justify-content: space-between;
}

.text-wrap {
  margin: 15px;
}

.btn-close {
  position: absolute;
  top: 0;
  right: 0;
  border: none;
  font-size: 20px;
  padding: 10px;
  cursor: pointer;
  font-weight: bold;
  color: #6c757d;
  background: transparent;
}

.btn-green {
  color: white;
  background: #6c757d;
  border: 1px solid #6c757d;
  border-radius: 2px;
  padding: 7px 14px;
}

.modal-fade-enter,
.modal-fade-leave-to {
  opacity: 0;
}

.modal-fade-enter-active,
.modal-fade-leave-active {
  transition: opacity 0.5s ease;
}

.pizza-slide {
  width: 100%;
  max-height: 150px;
  min-height: 50px;
  object-fit: cover;
}
</style>
