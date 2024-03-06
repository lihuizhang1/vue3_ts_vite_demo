<template>
    <TableDemo
        :projectLists="tableData.projectLists"
        :showCheckboxFlag="showCheckboxFlag"
        :colNames="colNames"
    ></TableDemo>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from 'vue'
import {useRouter} from "vue-router";
import TableDemo from "@/components/TableDemo.vue";

const showCheckboxFlag = true;
const colNames = [
    {
        label:"Name",
        prop:"project_name",
        sortFlag:true,
    },
    {
        label:"Place",
        prop:"place",
        sortFlag:false,
    },
    {
        label:"Tag",
        prop:"tagName",
        sortFlag:false,
    },
]
const router = useRouter()
const tableData = reactive({
    projectLists: [
        {
            "id": 1,
            "project_name": "project_11_1",
            "place": "22",
            "mail": "33",
            "tag": "1",
        },
        {
            "id": 2,
            "project_name": "project_22",
            "place": "22",
            "mail": "33",
            "tag": "1"
        },
        {
            "id": 3,
            "project_name": "project_33",
            "place": "22",
            "mail": "33",
            "tag": "1"
        },
        {
            "id": 4,
            "project_name": "project_44",
            "place": "22",
            "mail": "33",
            "tag": "1"
        },
        {
            "id": 5,
            "project_name": "project_55",
            "place": "22",
            "mail": "33",
            "tag": "2"
        },
        {
            "id": 11,
            "project_name": "project_11_update",
            "place": "11",
            "mail": "11@11.com",
            "tag": "2"
        },
        {
            "id": 12,
            "project_name": "project_66",
            "place": "66",
            "mail": "",
            "tag": "1"
        }
    ],
    projectIds:[]
})
const emit = defineEmits(["changePageName"])
const projectFlagIndex = ref("0");// 1:create 2:update 0 and other:detail

const getProjectLists = () => {
    for (let i = 0; i < tableData.projectLists.length; i++) {
        if(tableData.projectLists[i].tag == 1){
            tableData.projectLists[i]['tagName'] = "private"
        }else{
            tableData.projectLists[i]['tagName'] = "public"
        }
    }
}

getProjectLists();
if(projectFlagIndex.value === "0"){
    emit("changePageName", "projectList")
}

</script>
<style scoped>
.create-project-button {
    display: flex;
    margin-bottom: 10px;
}
</style>