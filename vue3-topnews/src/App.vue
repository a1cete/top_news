<template>
  <div id="body">
    <Header v-show="isHeader"></Header>
    <router-view></router-view>
  </div>
</template>
<script>
import Header from './components/Header.vue'
import { defineComponent } from 'vue'
import { useUserInfoStore } from './stores/userInfo'
import pinia from './stores/index';
const userInfoStore = useUserInfoStore(pinia)
export default defineComponent({
  name: 'App'
})
</script>
<script setup>
import { computed, onMounted } from "vue"
import { useRoute } from 'vue-router'
const route = useRoute() // 路由信息对象
// 判断是否显示header组件
const isHeader = computed(() => {
  return route.name !== "Login" && route.name !== "Register" && route.name !== "addNews" && route.name !== "restPassword";
})

</script>

<style lang="less" scoped></style>