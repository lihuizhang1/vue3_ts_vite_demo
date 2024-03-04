<template>
    <div>
        <div class="loginForm">
            <el-form ref="form" :model="user" label-width="80px">
                <el-form-item label="name:">
                    <el-input v-model="user.name" placeholder="001"></el-input>
                </el-form-item>
                <el-form-item label="password:">
                    <el-input v-model="user.password" placeholder="admin"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button @click="clear()">clear</el-button>
                    <el-button @click="setLogin()" type="primary">login</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script setup lang="ts">
import axios from "axios";
import LocalCache from "@/utils/catch"
// axios.defaults.baseURL = '/api';
// axios.defaults.baseURL = 'http://localhost:8080';
import {ref, reactive, onMounted} from "vue";
import {useRouter, RouterLink, RouterView } from 'vue-router'

const router = useRouter();
let homeBody = ref();
let loginFlag = ref(false);
let user = reactive({
    id: '',
    name: '001',
    age: '',
    mail: '',
    password: 'admin',
})
let loginUser = null;
let setLogin = () => {
    if(user.name.length > 0 && user.password.length > 0){
        axios.post('/api/user/loginUser', user).then(res => {
            if(res.data){
                loginFlag.value = true;
                LocalCache.setCathe('loginUser', res.data);
                router.push('/home');
            }else{
                loginFlag.value = false;
                alert("login failed!")
            }
        }).catch(err => {
            loginFlag.value = false;
        })
    }else {
        alert("please input mail and password!")
    }
}
let clear = () => {
    user.name = '';
    user.password = '';
}
onMounted(()=>{
    loginUser = LocalCache.getCache('loginUser');
    if(loginUser){
        loginFlag.value = true;
        // router.push('/home');
    }
})

</script>

<style scoped>
    .loginForm {
        padding: 30px;
        border-radius: 5px;
        background: aliceblue;
    }
    .home-container {
        height: 100%;
    }
</style>