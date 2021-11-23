import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import 'primevue/resources/themes/md-light-indigo/theme.css'
import 'primevue/resources/primevue.min.css' //core css
import 'primeicons/primeicons.css' //icon
import store from './store'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import { FontAwesomeIcon } from './plugins/font-awesome'

import PrimeVue from 'primevue/config'
import Toast from 'primevue/toast'
import Button from 'primevue/button'
import { VuelidatePlugin } from '@vuelidate/core'

const app = createApp(App)

app.component('Toast', Toast)
app.component('Button', Button)
app.component('font-awesome-icon', FontAwesomeIcon)

app.use(VuelidatePlugin)
app.use(PrimeVue)
app.use(router)
app.use(store)
app.mount('#app')
