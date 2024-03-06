<template>
    <el-container>
        <el-header>
            <el-page-header @back="goBack">
                <template #breadcrumb>
                    <el-breadcrumb separator="/">
                        <el-breadcrumb-item :to="{ path: './home' }">
                            home
                        </el-breadcrumb-item>
                        <el-breadcrumb-item>
                            lists
<!--                            <a href="./home">lists</a>-->
                        </el-breadcrumb-item>
<!--                        <el-breadcrumb-item>route 2</el-breadcrumb-item>-->
                    </el-breadcrumb>
                </template>
                <template #content>
                    <span class="text-large font-600 mr-3"> Home Page </span>
                </template>
                <template #extra>
                    <span style="position: fixed;top: 15px;margin-left: -30px;">
                        userName : {{loginUser.name}}
                    </span>
                    <el-button type="success" class="ml-2" @click="signOut()">sign out</el-button>
                </template>
            </el-page-header>
        </el-header>
        <el-main>
            This is chat test!
            <div style="height: 300px;width: 250px;border:2px solid black;background-color: white">
                <div style="display: flex;border-bottom: 1px solid #cccccc;"
                     v-for="item in chatLists">
                    {{ item }}
                </div>
            </div>
            <el-input style="width: 250px;margin: 10px" v-model="inputMsg"/>
            <div>
                <el-button @click="join">join room</el-button>
                <el-button @click="sendMsg">sendMsg</el-button>
<!--                <el-button @click="showData">show data</el-button>-->
<!--                <el-button @click="updateData">update data</el-button>-->
            </div>
        </el-main>
    </el-container>
</template>

<script lang="ts" setup>
import {onBeforeUnmount, onMounted, reactive, ref} from "vue";
import LocalCache from "@/utils/catch";
import {useRouter} from "vue-router";

let inputMsg = ref("");
let heartNumber = 0;
const router = useRouter()
let loginUser = reactive({
    name:"",
});
let chatLists = reactive([])
//1、创建websocket
// let ws = new WebSocket("ws://echo.websocket.org")
let ws = new WebSocket("ws://localhost:9001")

onMounted(()=>{
    initConnection();
    // setInterval(()=>{
    //     // 传输数据一定要是json
    //     let data = {
    //         type: "heart",
    //         value: "heart"
    //     }
    //     // console.log('ws',ws)
    //     ws.send(JSON.stringify(data))
    //     ws.onmessage = function (msg) {
    //         // console.log('msg:', msg.data)
    //         if(JSON.parse(msg.data).type == "heart"){
    //             heartNumber++;
    //             if(heartNumber > 5){
    //                 //重连方法
    //                 heartNumber = 0;
    //                 initConnection();
    //             }
    //         }
    //     }
    // },5000)
})

onBeforeUnmount(()=>{
    ws.close();
})

const initConnection = () => {
    if(LocalCache.getCache('loginUser').name){
        loginUser.name = LocalCache.getCache('loginUser').name
    }
    //2、open： 连接建立时触发
    ws.onopen = function () {
        console.log("打开连接了！")
        console.log(loginUser.name)
    }
    ws.onclose = function (event) {
        console.log('连接已断开,正在重连');
        initConnection()
    }
    ws.onerror = function (event) {
        console.log('连接出现错误');
    };
}

const goBack = () => {
    router.push('/project');
}

const signOut = () => {
    LocalCache.deleteCatch('loginUser');
    router.push('/login');
}

const join=()=>{
    let data = {
        type: "message",
        value: loginUser.name+"进入房间"
    }
    ws.send(JSON.stringify(data))
    ws.onmessage = function (msg) {
        console.log('msg:', msg.data)
        chatLists.push(msg.data);
    }
}

const showData=()=>{
    let data = {
        type: "showData",
        value: "hello world!_show"
    }
    ws.send(JSON.stringify(data))
    ws.onmessage = function (msg) {
        console.log('msg:', msg.data)
        inputMsg.value = msg.data;
    }
}

const updateData=()=>{
    let data = {
        type: "updateData",
        value: "hello world!_update"
    }
    ws.send(JSON.stringify(data))
    ws.onmessage = function (msg) {
        console.log('msg:', msg.data)
        inputMsg.value = msg.data
    }
}
const sendMsg=()=>{
    let data = {
        type: "message",
        value: inputMsg.value
    }
    ws.send(JSON.stringify(data))
    ws.onmessage = function (msg) {
        console.log('msg:', msg.data)
        chatLists.push(msg.data)
    }
}

</script>

<style scoped>

</style>