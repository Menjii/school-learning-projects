<style scoped>
.brand {
  display: none;
  position: absolute;
  left: 50%;
  left: 0;
  top: 0;
  width: 150px;
  height: 150px;
  line-height: 150px;
  text-align: center;
  transform: translateX(-50%);
  transform: none;
  background: #1d1d1d;
  margin-left: 8%;
  z-index: 2;
}

.navbar-expand .navbar-nav .nav-link {
  font-size: 1.1rem;
}

.navbar-nav {
  margin-right: 3.7rem;
  margin-left: 3.7rem;
}

footer{
    width: 100%;
    position: fixed;
    bottom: 0;
}
</style>

<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-dark bg-dark">
      <a href="/" class="brand"><img src="@/assets/images/logo.png" /></a>
      <div class="my-3 navbar-nav ml-auto">
        <li class="mx-4 nav-item">
          <router-link to="/home" class="nav-link">
           Home
          </router-link>
        </li>
        <li class="mx-4 nav-item">
          <router-link to="/menu" class="nav-link">Menu</router-link>
        </li>
        <!--        <li v-if="showAdminBoard" class="nav-item">-->
        <!--          <router-link to="/admin" class="nav-link">Admin Board</router-link>-->
        <!--        </li>-->
        <li v-if="showModeratorBoard" class="mx-4 nav-item">
          <router-link to="/add-product" class="nav-link"
            >Dodaj produkt</router-link
          >
        </li>
        <li v-if="showModeratorBoard" class="mx-4 nav-item">
          <router-link to="/add-category" class="nav-link"
            >Dodaj kategorię</router-link
          >
        </li>
        <li v-if="showModeratorBoard" class="mx-4 nav-item">
          <router-link to="/all-orders" class="nav-link"
          >Zamówienia</router-link
          >
        </li>
        <li v-if="showModeratorBoard" class="mx-4 nav-item">
          <router-link to="/add-promotion" class="nav-link"
            >Aktywuj promocje</router-link
          >
        </li>
        <li v-if="!currentUser" class="mx-4 nav-item">
          <router-link to="/register" class="nav-link">
            <font-awesome-icon icon="user-plus" /> Sign Up
          </router-link>
        </li>
        <li v-if="!currentUser" class="mx-4 nav-item">
          <router-link to="/login" class="nav-link">
            <font-awesome-icon icon="sign-in-alt" /> Login
          </router-link>
        </li>
        <li v-if="currentUser" class="mx-4 nav-item">
          <router-link to="/profile" class="nav-link">
            <font-awesome-icon icon="user" />
            {{ currentUser.username }}
          </router-link>
        </li>
        <li v-if="currentUser" class="mx-4 nav-item">
          <a class="nav-link" @click.prevent="logOut">
            <font-awesome-icon icon="sign-out-alt" /> LogOut
          </a>
        </li>
      </div>
    </nav>

    <div>
      <router-view />
    </div>
  </div>

<footer class="bg-dark text-center text-lg-start">
  <div class="text-center bg-dark pt-2">
    <p class="text-light">
      <img :src="github" style="height: 25px"/><br />
      <a class="text-light" href="https://github.com/dpajak99">Dominik Pająk</a>
      |
      <a class="text-light" href="https://github.com/Menjii">Bartosz Dymański</a>
      |
      <a class="text-light" href="https://github.com/Robert-Mal">Robert Małek</a>
      |
      <a class="text-light" href="https://github.com/skruli">Grzegorz Gancarz</a>
    </p>
  </div>
</footer>
</template>

<script>
import GithubIcon from "./assets/images/github.png"
export default {
  data() {
    return {
      github: GithubIcon
    }
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user
    },
    showAdminBoard() {
      if (this.currentUser && this.currentUser['roles']) {
        return this.currentUser['roles'].includes('ROLE_ADMIN')
      }
      return false
    },
    showModeratorBoard() {
      if (this.currentUser && this.currentUser['roles']) {
        return this.currentUser['roles'].includes('ROLE_MODERATOR')
      }
      return false
    }
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout')
      this.$router.push('/login')
    }
  }
}
</script>
