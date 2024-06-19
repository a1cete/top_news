<template>
  <div class="login-container">
    <el-form :model="loginForm" ref="formRef" label-width="80px" class="login-form" :rules="loginRules">
      <h2>重置密码</h2>
      <el-form-item label="密码" prop="userPwd">
        <el-input type="password" v-model="loginForm.userPwd" autocomplete="off" placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="newUserPwd">
        <el-input type="password" v-model="loginForm.newPassword" autocomplete="off" placeholder="请输入新密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="success" @click="restPasswords">重置</el-button>
        <el-button type="primary" @click="out">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts">
import { ElMessage, FormInstance } from 'element-plus';
import { defineComponent, ref } from 'vue'
import { restPassword } from '../api';
import { useRouter } from 'vue-router';
import { useUserInfoStore } from '../stores/userInfo'
export default defineComponent({
  name: 'RestPassword'
})
</script>
<script lang="ts" setup>
const router = useRouter()
const formRef = ref<FormInstance>()

//账号密码参数
const loginForm = ref({
  uid: "",
  userPwd: "",
  newPassword: ""
})
// 校验规则
const validateUsername = (rule: any, value: any, callback: any) => {
  if (value.length < 4) {
    callback(new Error('用户名长度不能小于4位'))
  } else {
    callback()
  }
}
// 校验规则
const validatePassword = (rule: any, value: any, callback: any) => {
  if (value.length < 6) {
    callback(new Error('密码长度不能小于6位'))
  } else {
    callback()
  }
}
const checkRePassword = (rule: any, value: any, callback: any) => {
  if (loginForm.value.newPassword == loginForm.value.userPwd) {
    callback(new Error("两次输入密码一致"))
  } else {
    callback()
  }
}
// 校验规则
const loginRules = {
  username: [{ required: true, validator: validateUsername }],
  userPwd: [{ required: true, trigger: 'blur', validator: validatePassword }],
  newUserPwd: [{ required: true, trigger: 'blur', validator: checkRePassword }]
}
const out = () => {
  window.history.back()
}

//重置密码
const restPasswords = async () => {
  if (loginForm.value.userPwd != loginForm.value.newPassword) {
    const obj = {
      uid: "",
      userPwd: "",
      newPassword: ''
    }
    obj.uid = useUserInfoStore().uid
    obj.userPwd = loginForm.value.userPwd
    obj.newPassword = loginForm.value.newPassword
    await restPassword(obj)
    formRef.value?.resetFields()
    router.push("Login")
    ElMessage.success("修改成功")
  } else {
    return;
  }
}

</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 80vh;
}

.login-form {
  width: 400px;
  text-align: center;
}
</style>
