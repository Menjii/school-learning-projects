<template>
<div class="container">
    <div class="card border-secondary my-4 shadow p-3 mb-5 bg-light rounded">
  <h5>Adres dostarczenia:</h5><br />
  {{address.street}}, {{address.homeNumber}}<br />
  {{address.postNumber}} {{address.city}} <br />

  Status: 
  {{ orderDetails.realizationStatus }}<br/>

  Czas realizacji 60 minut
    </div>
</div>
</template>

<script>

export default {
  name: 'OrderDetailsComponent',
  components: {},
  computed: {},
  props: {
    orderDetails: {},
    address: {}
  },
  data() {
    return {
      pageId: 0,
    }
  },
  methods: {

  },
  mounted() {
    console.log(this.orderDetails)
  }
}
</script>

<style>

</style>
