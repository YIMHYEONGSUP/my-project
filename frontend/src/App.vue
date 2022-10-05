<template>
  <div id="app">
  
   <!-- 헤더 -->
    <Header id="header"></Header>

    <content id="content">

    <!-- 라우터  -->
    <router-view/>

    </content>



    <!-- 푸터 -->
    <Footer id="footer"></Footer>

  </div>
</template>

<script>

import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
import Content from './components/Content.vue'

export default {
  name:'App',
  components: {
    Header,
    Footer,
    Content,
}
}
</script>


<style>


#content{
  margin-top: 5px;
  margin-bottom: 5px;
}

#app {
  min-width: 1000px;
  min-height: 1000px;

  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  /* padding: 5px; */
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
