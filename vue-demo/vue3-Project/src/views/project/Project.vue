<template>
    <el-container>
        <el-header>
            <el-page-header>
                <template #breadcrumb>
                    <el-breadcrumb separator="/">
                        <el-breadcrumb-item :to="{ path: './page-header.html' }">
                            homepage
                        </el-breadcrumb-item>
                        <el-breadcrumb-item
                        ><a href="./page-header.html">route 1</a></el-breadcrumb-item
                        >
                        <el-breadcrumb-item>route 2</el-breadcrumb-item>
                    </el-breadcrumb>
                </template>
                <template #content>
                    <span class="text-large font-600 mr-3"> Title </span>
                </template>
                <template #extra>
                    <el-button type="success" class="ml-2" @click="signOut()">sign out</el-button>
                </template>
            </el-page-header>
        </el-header>
        <el-main>
            <div v-if="projectFlagIndex === '0'">
                <el-button @click="createProject" class="create-project-button">Create Project</el-button>
                <el-table
                    :data="tableData.projectLists"
                    style="width: 100%"
                    @selection-change="handleSelectionChange"
                >
                    <el-table-column type="selection" width="55" />
                    <el-table-column label="Name" prop="project_name" sortable/>
                    <el-table-column label="Place" prop="place" />
                    <el-table-column
                        prop="tag"
                        label="Tag"
                        width="100"
                        :filters="[
            { text: 'ready', value: '1' },
            { text: 'finish', value: '2' },
          ]"
                        :filter-method="filterTag"
                        filter-placement="bottom-end"
                    >
                        <template #default="scope">
                            <el-tag
                                :type="scope.row.tag === '1' ? 'primary' : 'danger'"
                                disable-transitions
                            >
                                {{ scope.row.tag === '1' ? 'ready' : 'finish' }}
                            </el-tag>
                        </template>

                    </el-table-column>
                    <el-table-column align="right">
                        <template #header>
                            <el-input v-model="search" size="small" placeholder="Type to search" />
                        </template>
                        <template #default="scope">
                            <el-button size="small" @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
                            <el-button
                                size="small"
                                type="danger"
                                @click="handleDelete(scope.$index, scope.row)"
                            >Delete</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <ProjectCreate
                v-else
                @changeProjectFlag="changeProjectFlag"
                :projectFlagIndex="projectFlagIndex"
                :editProject="editProject">
            </ProjectCreate>
            <ConfirmDemo :confirmShowFlag="confirmShowFlag"
                         :confirmTittle="confirmTittle"
                         :confirmContext="confirmContext"
                         @changeConfirmShowFlag="changeConfirmShowFlag">
            </ConfirmDemo>
        </el-main>
    </el-container>


</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from 'vue'
import {useRouter} from "vue-router";
import ProjectCreate from "@/views/project/ProjectCreate.vue";
import axios from "axios";
import ConfirmDemo from "@/components/ConfirmDemo.vue";
import Home from "@/views/home/Home.vue";
import PageHeader from "@/LoginPage.vue";
import LocalCache from "@/utils/catch";

interface Project {
    id: string,
    date: string
    project_name: string
    place: string
    tag: string
}
class ProjectSelectDemo {
    ids: string
}
const router = useRouter()
const tableData = reactive({
    projectLists:[],
    projectIds:[]
})
const editProject = reactive({
    id: 0,
    project_name: "",
    place: "",
    mail: "",
    tag: "1"
})
const emit = defineEmits(["changePageName"])
const confirmShowFlag = ref(false)
const confirmTittle = ref("PROJECT")
const confirmContext = ref("")
const search = ref('')
const projectFlagIndex = ref("0");// 1:create 2:update 0 and other:detail
// let filterTableData = computed(() =>
//     tableData.filter(
//         (data) =>
//             !search.value ||
//             data.name.toLowerCase().includes(search.value.toLowerCase())
//     )
// )
const getProjectLists = () => {
    axios.post('/api/project/getProjectLists', {}).then((res) => {
        tableData.projectLists = res.data;
    }).catch((err) => {
        console.log(err);
    })
}

const changeConfirmShowFlag = (value) => {
    confirmShowFlag.value = value;
}

const handleEdit = (index: number, row: Project) => {
    for (let i = 0; i < tableData.projectLists.length; i++) {
        if(i === index){
            editProject.id = tableData.projectLists[i].id;
            editProject.project_name = tableData.projectLists[i].project_name;
            editProject.place = tableData.projectLists[i].place;
            editProject.mail = tableData.projectLists[i].mail;
            editProject.tag = tableData.projectLists[i].tag;
            projectFlagIndex.value = '2';
            console.log("projectUpdate")
            emit("changePageName", "projectUpdate")
            break;
        }
    }
}
const handleDelete = (index: number, row: Project) => {
    let ids = row.id;
    if(tableData.projectIds.length > 1){
        ids = tableData.projectIds.join(',')
    }
    let data = new ProjectSelectDemo();
    data.ids = ids;

    axios.post('/api/project/delete', data).then((res) => {
        confirmContext.value = "delete success!"
        confirmShowFlag.value = true;
        tableData.projectLists = res.data;
    }).catch((err) => {
        console.log(err);
    })
}

const handleSelectionChange = (value: Project[]) => {
    let arr = [];
    for (let i = 0; i < value.length; i++) {
        arr.push(value[i].id);
    }
    tableData.projectIds = arr;
}

const filterTag = (value: string, row: Project) => {
    return row.tag === value
}

const createProject = () => {
    projectFlagIndex.value = '1';
    console.log("projectCreate")
    emit("changePageName", "projectCreate")
}

const changeProjectFlag = (value) => {
    emit("changePageName", "projectList")
    projectFlagIndex.value = value;
    getProjectLists();
}

const signOut = () => {
    LocalCache.deleteCatch('loginUser');
    router.push('/login');
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