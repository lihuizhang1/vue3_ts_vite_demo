<template>
    <div class="home-body" ref="homeBody">
        <el-container style="place-content: center;align-items: center;margin-top: 25%"
                      v-if="router.currentRoute.value.path === '/login'">
            <RouterView/>
        </el-container>
        <el-container v-else>
            <div ref="containerBody" style="background-color: #D3DCE6;">
                <el-aside>
                    <Navigation></Navigation>
                </el-aside>
            </div>
            <RouterView/>
        </el-container>
    </div>
</template>

<script lang="ts" setup>
import {watch, onMounted, ref} from 'vue'
import Navigation from "./components/Navigation.vue";
import PageHeader from "@/components/PageHeader.vue";
import Home from "@/views/home/Home.vue";
import Project from "@/views/project/Project.vue";
import {RouterView, useRouter} from "vue-router";
import LocalCache from "@/utils/catch";
const router = useRouter();

let homeBody = ref();
let containerBody = ref();
let routerViewBody = ref();
let navName = ref("home");
let pageName = ref("home");
let loginUser = null;
onMounted(()=>{
    changeWidthAndHeight()
})

const changeWidthAndHeight = () => {
    if(homeBody.value && homeBody.value.style){
        homeBody.value.style.width = `${window.innerWidth}px`;
        homeBody.value.style.height = `${window.innerHeight}px`;
    }
    if(containerBody.value && containerBody.value.style){
        containerBody.value.style.height = `${window.innerHeight}px`;
    }
    if(routerViewBody.value && routerViewBody.value.style){
        routerViewBody.value.style.width = `${window.innerWidth}px`;
    }
}

const getSelectNavName = (value: string) => {
    navName.value = value;
}

const changePageName = (value) => {
    console.log('changePageName',value)
    pageName.value = value;
}

if(router.currentRoute.value.path == "/"){
    loginUser = LocalCache.getCache('loginUser');
    if(loginUser){
        router.push('/home');
    }else{
        router.push('/login');
    }
}

</script>

<style scoped>

</style>