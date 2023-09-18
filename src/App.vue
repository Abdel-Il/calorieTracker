<template>
  <img alt="Vue logo" src="./assets/logo.png">
  <HelloWorld msg="Welcome to Your Vue.js App"/>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import {FoodAdaptor} from '@/services/food';
import CONFIG from '../app-config.js'

export default {
  name: 'App',
  components: {
    HelloWorld
  },
    provide() {
      return {
          foodService: new FoodAdaptor(CONFIG.BACKEND_URL)
      }
    }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
    background-color: #282838;
    min-height: 100vh;
}
</style>
