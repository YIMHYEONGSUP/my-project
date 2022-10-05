<template>

        <div id="userInformation">
            <router-link to="/">일반 로그인</router-link> |
            <router-link to="/about">마켓 로그인</router-link>
        </div>

</template>
  
  <script>
  export default {
    name: 'UserInfomation'

  }
  </script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>
    #sideContent{
        width: 500px;
        height: 200px;
        background-color: royalblue;
    }
    #userInformation { 
        width: 95%;
        min-width: 200px;
        height: 250px;
        margin: auto;
        border: 3px solid black;
        background-color: rebeccapurple;

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
  