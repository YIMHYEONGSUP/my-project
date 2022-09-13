<template>
  <div id="app">
    <!-- 추가된 헤더 컴포넌트 사용 -->
    <Header></Header>
    
    <HelloWorld msg="Welcome to Your Vue.js App"/>
	
    <!-- 추가된 풋터 컴포넌트 사용 -->
    <Footer></Footer>
  </div>
</template>

<script>
import Header from './components/common/Header'; //import 헤더 추가
import HelloWorld from './components/HelloWorld.vue'; 
import Footer from './components/common/Footer';

export default {
  name: 'App',
  components: {
    Header //헤더 컴포넌트 추가
    ,
    HelloWorld,
    Footer //풋터 컴포넌트 추가
}
}
</script>

<style>
html,body{padding:0; margin:0;}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin:0; padding:0;
}
</style>