<template>
    <div aria-label="A complete example of page header" style="margin-top: 20px">
        <el-page-header @back="onBack">
            <template #breadcrumb>
                <el-breadcrumb separator="/">
                    <el-breadcrumb-item>
<!--                                        :to="{ path: './'+props.navName }">-->
                        <span style="font-weight: bolder;color: black;font-size: 16px;">
                            {{ props.navName }}
                        </span>
                    </el-breadcrumb-item>
                    <el-breadcrumb-item v-if="props.navName!='home'">
<!--                        <a href="./admin">{{props.pageName}}</a>-->
                        {{props.pageName}}
                    </el-breadcrumb-item>
                </el-breadcrumb>
            </template>
            <template #content>
                <div class="flex items-center">
                    <span class="text-large font-600 mr-3"> {{props.navName}} </span>
                    <span class="text-sm mr-2"
                            style="color: var(--el-text-color-regular)"
                            v-if="props.navName!='home'">
                        {{" - " + props.pageName}}
                    </span>
                </div>
            </template>
            <template #extra>
                <div class="flex items-center">
                    <el-button type="success" class="ml-2" @click="signOut()">sign out</el-button>
                </div>
            </template>

        </el-page-header>
    </div>
</template>

<script setup lang="ts">
import { ElNotification as notify } from 'element-plus'
import {useRouter} from "vue-router";
import LocalCache from "@/utils/catch";
import {ref, watch} from 'vue'
const router = useRouter();
const props = defineProps(['navName', 'pageName'])
let pageName = ref("");

const onBack = () => {
    // notify('Back')
    // router.push('/home');
}

const signOut = () => {
    LocalCache.deleteCatch('loginUser');
    router.push('/login');
}

watch(()=>pageName,(newValue,oldValue)=>{
    pageName.value = newValue.value;
},{immediate:true,deep:true})
</script>
