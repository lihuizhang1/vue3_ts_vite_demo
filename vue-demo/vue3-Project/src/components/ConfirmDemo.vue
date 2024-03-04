<template>
    <el-dialog
            v-model="centerDialogVisible"
            :title="props.confirmTittle"
            width="500"
            align-center
            :show-close="false"
    >
        <span>{{ props.confirmContext }}</span>
        <template #footer>
            <div class="dialog-footer">
                <el-button @click="cancel">Cancel</el-button>
                <el-button @click="confirm" type="primary">
                    Ok And Back
                </el-button>
            </div>

        </template>
    </el-dialog>
</template>
<script lang="ts" setup>
import {ref, watch} from 'vue'

const centerDialogVisible = ref(false)
const props = defineProps(['confirmShowFlag', 'confirmTittle', 'confirmContext'])
const emit = defineEmits(["changeConfirmShowFlag", "backPage"])

watch(()=>props.confirmShowFlag,(newValue,oldValue)=>{
    if(newValue){
        centerDialogVisible.value = newValue;
    }
},{immediate:true,deep:true})

const cancel = ()=>{
    centerDialogVisible.value = false;
    emit("changeConfirmShowFlag", false);
}

const confirm = ()=>{
    cancel();
    emit("backPage");
}
</script>
