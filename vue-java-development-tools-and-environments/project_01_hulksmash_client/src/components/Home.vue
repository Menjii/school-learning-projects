<style scoped>
  .carousel-inner {
    top: 0;
    left: 0;
    min-width: 100%;
    height: 550px;
    max-height: 550px;
    width: auto;
    height:fit-content;
    box-shadow: 0 5px 15px rgba(0,0,0,0.6);
}

.carousel-item{
    /*position: relative;*/
}

.carousel-item .prom {
    position: absolute;
    bottom: 50%;
    left: 50%;
    padding: 4%;
    max-height: 40%;
    max-width: 60%;
    box-shadow: 0 5px 15px rgba(0,0,0,0.6);
    border-radius: 1em;
    transform: translate(-50%, -50%);
    overflow: hidden;
    backdrop-filter: blur(3px);
}

.carousel-item .prom p{
  position: relative;
  font-weight: bold;
  font-size: 3vw;
  text-align: center;
  color: white;
  text-shadow: 0 1px 5px rgba(0,0,0,0.6);
}

.overlay {
    position: absolute;
    display:block;
    min-height: 100%;
    min-width: 100%;
    left:0;
    top:0;
    background: rgba(0,0,0,0.6);
}
</style>

<template>
  <div
    id="carouselExampleIndicators"
    class="carousel slide"
    data-ride="carousel"
  >
    <ol class="carousel-indicators">
      <li
        data-target="#carouselExampleIndicators"
        data-slide-to="0"
        class="active"
      ></li>
      <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
      <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <div class="prom">
          <p>Aktualne promocje
            <br>PIZZA 50/50</p>
        </div>
        <img class="d-block w-100" src="@/assets/slider/pizza_slider_1.jpg" alt="First slide" />
      </div>
      <div class="carousel-item">
        <div class="prom">
          <p>Aktualne promocje
            <br>PIZZA 50/50</p>
        </div>
        <img class="d-block w-100" src="@/assets/slider/pizza_slider_2.jpg" alt="Second slide" />
      </div>
      <div class="carousel-item">
        <div class="prom">
          <p>Aktualne promocje
            <br>PIZZA 50/50</p>
        </div>
        <img class="d-block w-100" src="@/assets/slider/pizza_slider_3.jpg" alt="Third slide" />
      </div>
    </div>
    <a
      class="carousel-control-prev"
      href="#carouselExampleIndicators"
      role="button"
      data-slide="prev"
    >
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a
      class="carousel-control-next"
      href="#carouselExampleIndicators"
      role="button"
      data-slide="next"
    >
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
  <br /><br />
  <center><h1>Restauracja HulkSmash</h1></center>
</template>

<script>
import UserService from '../services/user.service'

export default {
  name: 'Home',

  data() {
    return {
      content: ''
    }
  },
  mounted() {
    UserService.getPublicContent().then(
      (response) => {
        this.content = response.data
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
  }
}
</script>
