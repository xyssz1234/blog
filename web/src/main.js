import Vue from 'vue'
import App from './App.vue'
import router from './router'

Vue.config.productionTip = false
import Element from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
Vue.use(Element)

import axios from '@/axios'
Vue.prototype.$axios = axios

// console.log("环境",process.env.NODE_ENV)
// console.log("服务器",process.env.VUE_APP_SERVER)

import TestComponent from "@/components/TestComponent";
import CustomPages from "@/components/CustomPages";
import CustomDrawer from "@/components/CustomDrawer";
import CustomerEditor from "@/components/CustomerEditor";
Vue.component("TestComponent",TestComponent)
Vue.component("CustomPages",CustomPages)
Vue.component("CustomDrawer",CustomDrawer)
Vue.component("CustomerEditor",CustomerEditor)

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
