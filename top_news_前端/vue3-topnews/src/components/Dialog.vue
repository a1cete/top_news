<template>
  <el-dialog v-model="dialogVisible" title="注销账号" width="400px">
    <span>确定注销账号吗？<span style="color: red">(此操作将永远注销账号，无法找回)</span></span>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="logOutAccounts()" type="danger">确定</el-button>
        <el-button type="primary" @click="dialogVisible = false">
          取消
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import {ref} from 'vue'
import {useUserInfoStore} from '../stores/userInfo';
import {logOutAccount} from '../api/index'
import {ElMessage} from 'element-plus';
import pinia from '../stores/index';
import {useRouter} from 'vue-router';
import {removeToken} from '../utils/token-utils'

const dialogVisible = ref(false)
const userInfo = useUserInfoStore(pinia)
const router = useRouter()
defineExpose({show})

function show() {
  dialogVisible.value = true
}


const logOutAccounts = () => {


  const uids = {
    uid: userInfo.uid
  }
  logOutAccount(uids)
  ElMessage.success("删除成功")
  dialogVisible.value = false
  removeToken()
  userInfo.initUserInfo()
  router.go({name: "HeadlineNews"});
}
</script>
<style lang="css">
.dialog-footer {
  display: flex;
}
</style>