import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from '@/store'
import axios from 'axios';
import VueAxios from 'vue-axios';
import VueSweetalert2 from 'vue-sweetalert2';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import '../node_modules/sweetalert2/dist/sweetalert2.min.css';

Vue.config.productionTip = false
Vue.use(VueSweetalert2);
Vue.use(VueAxios, axios);

new Vue({
  router,
  render: h => h(App),
  store
}).$mount('#app')