import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './AdminPage.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/theme-chalk/index.css'

const app = createApp(App)

app.use(createPinia())
app.use(ElementPlus)
app.use(router)

app.mount('#app')
