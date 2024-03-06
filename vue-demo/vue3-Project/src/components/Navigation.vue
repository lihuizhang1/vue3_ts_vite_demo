<template>
    <el-row class="tac" style="display: block;">
        <el-col :span="12" style="max-width: 100%;">
            <img alt="Vue logo" class="nav-image" src="@/assets/logo.svg"/>
            <el-menu
                active-text-color="#ffd04b"
                background-color="#545c64"
                class="el-menu-vertical-demo"
                default-active="1"
                text-color="#fff"
                @open="handleOpen"
                @close="handleClose"
                @select="handleSelect"
                style="margin-top: -6px;"
            >
                <el-menu-item index="1">
                    <el-icon><document /></el-icon>
                    <span>Home</span>
                </el-menu-item>
                <el-menu-item index="2">
                    <el-icon><icon-menu /></el-icon>
                    <span>Project</span>
                </el-menu-item>
                <el-menu-item index="3">
                    <el-icon><location /></el-icon>
                    <span>Test</span>
                </el-menu-item>

                <el-sub-menu index="4">
                    <template #title>
                        <el-icon><setting /></el-icon>
                        <span>Setting</span>
                    </template>
                    <el-menu-item-group title="Group One">
                        <el-menu-item index="4-1">item one</el-menu-item>
                        <el-menu-item index="4-2">item two</el-menu-item>
                    </el-menu-item-group>
                    <el-menu-item-group title="Group Two">
                        <el-menu-item index="4-3">item three</el-menu-item>
                    </el-menu-item-group>
                    <el-sub-menu index="4-4">
                        <template #title>item four</template>
                        <el-menu-item index="4-4-1">item one</el-menu-item>
                    </el-sub-menu>
                </el-sub-menu>

            </el-menu>
        </el-col>
    </el-row>
</template>

<script lang="ts" setup>
import {
    Document,
    Menu as IconMenu,
    Location,
    Setting,
} from '@element-plus/icons-vue'
import {ref} from "vue";
import {useRouter} from "vue-router";

let selectNavName = ref();
let selectNavKey = ref();
const emit = defineEmits(["getSelectNavName"]);
const router = useRouter();
const handleOpen = (key: string, keyPath: string[]) => {
    console.log(key);
    console.log(keyPath);
}
const handleClose = (key: string, keyPath: string[]) => {
    console.log(key, keyPath)
}

const handleSelect = (key: string, keyPath: string[]) => {
    if(selectNavKey.value == key){
        return;
    }
    selectNavKey.value = key;
    switch (key) {
        case "1":
            selectNavName.value = "home";
            router.push('/home');
            break;
        case "2":
            selectNavName.value = "project";
            router.push('/project');
            break;
        case "3":
            selectNavName.value = "test";
            router.push('/test');
            break;
        case "4":
            selectNavName.value = "setting";
            break;
        default:
            selectNavName.value = "home";
    }
    console.log(selectNavName.value);
    // emit("getSelectNavName", selectNavName.value)
}
</script>

<style scoped>
    .nav-image {
        width: 200px;
        height: 100px;
    }
</style>
