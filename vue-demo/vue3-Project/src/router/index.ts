import { createRouter, createWebHistory } from 'vue-router'
import Project from "../views/project/Project.vue";
import LoginPage from "@/LoginPage.vue";
import AdminPage from "@/AdminPage.vue";
import ProjectCreate from "@/views/project/ProjectCreate.vue";
import HomeView from "@/views/HomeView.vue";
import Home from "@/views/home/Home.vue";
// import useStore from "element-plus/lib/components/table/src/store";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'admin',
      component: LoginPage
    },
    {
      path: '/login',
      name: 'login',
      component: LoginPage
    },
    {
      path: '/home',
      name: 'home',
      component: Home
    },
    {
      path: '/project',
      name: 'project',
      component: Project
    },
    {
      path: '/project/create',
      name: 'projectCreate',
      component: ProjectCreate
    },
  ]
})

// router.beforeEach(to => {
//   // 解决持久化失效问题
//   const { websocket } = useStore();
//   // 建立链接
//   if (!websocket.exhibitWs) {
//     websocket.initExhibitSocket();
//   }
// });


export default router
