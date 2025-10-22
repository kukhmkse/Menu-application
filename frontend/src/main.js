import { createApp } from 'vue'

import App from './App.vue'

import axios from './plugins/axios'
import VueAxios from 'vue-axios'

import router from './router'

import vuetify from './plugins/vuetify'
import '@vuepic/vue-datepicker/dist/main.css'


createApp(App)
    .use(VueAxios, axios)
    .use(router)
    .use(vuetify)
    .mount('#app')
