<template>
    <h1></h1>
    <el-form ref="form" :model="project" label-width="120px" style="margin-right: 60px">
        <el-form-item label="">
            <div>{{props.projectFlagIndex === '2' ? 'This is Update!' : 'This is Create!'}}</div>
        </el-form-item>
        <el-form-item label="image:">
            <el-upload :auto-upload="false" action="#" list-type="picture-card">
                <el-icon>
                    <Plus/>
                </el-icon>

                <template #file="{ file }">
                    <div>
                        <img :src="file.url" alt="" class="el-upload-list__item-thumbnail"/>
                        <span class="el-upload-list__item-actions">
                         <span
                             class="el-upload-list__item-preview"
                             @click="handlePictureCardPreview(file)"
                         >
                           <el-icon><zoom-in/></el-icon>
                         </span>
                         <span
                             v-if="!disabled"
                             class="el-upload-list__item-delete"
                             @click="handleDownload(file)"
                         >
                           <el-icon><Download/></el-icon>
                         </span>
                         <span
                             v-if="!disabled"
                             class="el-upload-list__item-delete"
                             @click="handleRemove(file)"
                         >
                           <el-icon><Delete/></el-icon>
                         </span>
                       </span>
                    </div>
                </template>
            </el-upload>

            <el-dialog v-model="dialogVisible">
                <img :src="dialogImageUrl" alt="Preview Image" w-full/>
            </el-dialog>
        </el-form-item>
        <el-form-item label="tag:">
            <el-radio-group v-model="project.tag">
                <el-radio label="1" :disabled="props.projectFlagIndex !== '2'">ready</el-radio>
                <el-radio label="2" :disabled="props.projectFlagIndex !== '2'">finish</el-radio>
            </el-radio-group>
        </el-form-item>
        <el-form-item label="name:">
            <el-input v-model="project.project_name" placeholder=""></el-input>
        </el-form-item>
        <el-form-item label="place:">
            <el-input v-model="project.place" placeholder=""></el-input>
        </el-form-item>
        <el-form-item label="mail:">
            <el-input v-model="project.mail" placeholder=""></el-input>
        </el-form-item>
        <el-form-item>
            <el-button @click="cancel()">cancel</el-button>
            <el-button type="primary" @click="store()">
                {{props.projectFlagIndex === '2' ? 'update' : 'create'}}
            </el-button>
        </el-form-item>
    </el-form>
    <ConfirmDemo :confirmShowFlag="confirmShowFlag"
                 :confirmTittle="confirmTittle"
                 :confirmContext="confirmContext"
                 @changeConfirmShowFlag="changeConfirmShowFlag"
                 @backPage="backPage">
    </ConfirmDemo>
</template>

<script lang="ts" setup>
import axios from "axios";
import {reactive, ref} from "vue";
import {Delete, Download, Plus, ZoomIn} from '@element-plus/icons-vue'

import type {UploadFile} from 'element-plus'
import ConfirmDemo from "@/components/ConfirmDemo.vue";
// axios.defaults.baseURL = 'http://localhost:8080';

let project = reactive({
    id: '',
    project_name: '',
    place: '',
    image: '',
    mail: '',
    tag: "1",
})
const emit = defineEmits(["changeProjectFlag"])
const props = defineProps(['projectFlagIndex', "editProject"])
const confirmShowFlag = ref(false)
const dialogImageUrl = ref('')
const dialogVisible = ref(false)
const disabled = ref(false)
const confirmTittle = ref("PROJECT")
const confirmContext = ref("")

if(props.editProject.id !== 0 && props.projectFlagIndex === '2'){
    project = props.editProject;
}

const handleRemove = (file: UploadFile) => {
    console.log(file)
}

const handlePictureCardPreview = (file: UploadFile) => {
    dialogImageUrl.value = file.url!
    dialogVisible.value = true
}

const handleDownload = (file: UploadFile) => {
    console.log(file)
}

let store = () => {
    let url = "/api/project/create";
    if(props.projectFlagIndex === '2'){
        url = "/api/project/update";
    }
    axios.post(url, project).then((res) => {
        if(props.projectFlagIndex === '2'){
            if(res.data > 0){
                confirmShowFlag.value = true;
                confirmContext.value = "update success!"
            } else {
                alert('failed')
            }
        }else{
            if (res.data.id) {
                confirmShowFlag.value = true;
                confirmContext.value = "create success!"
            } else {
                alert('failed')
            }
        }
    }).catch((err) => {
        console.log(err);
    })

}

const cancel = () => {
    emit("changeProjectFlag", '0')
}

const changeConfirmShowFlag = (value) => {
    confirmShowFlag.value = value;
}
const backPage = () => {
    cancel();
}
</script>

<style scoped>

</style>