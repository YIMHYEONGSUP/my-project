<template>
    <div id="header">
        
        <div>로고</div>

        <div>Header Page</div>

        <router-link to="/">Home</router-link> |
        <router-link to="/about">About</router-link>

        <div><input type="search" placeholder="검색"></div>
       
    
     
    </div>
</template>
  
  <script>
  export default {
    name: 'Header'

  }
  </script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>
#header {
    width: auto;
    height: 100px;
    border: 3px solid black;
}

  h3 {
    margin: 40px 0 0;
  }
  ul {
    list-style-type: none;
    padding: 0;
  }
  li {
    display: inline-block;
    margin: 0 10px;
  }
  a {
    color: #42b983;
  }
  </style>
  