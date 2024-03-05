<template>
    <div>
        This is home page!
        Socket show data is :  {{text}}
        <div>
            <el-button @click="showData">show data</el-button>
            <el-button @click="updateData">update data</el-button>
        </div>
    </div>
</template>

<script lang="ts" setup>
import {onMounted, ref} from "vue";

let ws = new WebSocket("ws://localhost:9001")
let text = ref("");
onMounted(()=>{
    ws.onopen = function () {
        console.log("打开连接了！")
    }
})

const showData=()=>{
    let data = {
        type: "showData",
        value: "hello world!_show"
    }
    ws.send(JSON.stringify(data))
    ws.onmessage = function (msg) {
        console.log('msg:', msg.data)
        text.value = msg.data;
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
        text.value = msg.data;
    }
}
</script>

<style scoped>

</style>