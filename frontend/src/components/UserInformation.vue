<template>
  
  <v-container>
      <v-card>
        <input v-model="id" placeholder="아이디"/>
        <input v-model="password" type="search" placeholder="패스워드"/>
      </v-card>
            <v-btn v-model="button">로그인</v-btn>

            <v-card>
              <v-btn v-model="button" to="/join">회원가입</v-btn>
             <v-btn v-model="button" to="/find">아이디 | 비밀번호 찾기</v-btn>
            </v-card>
  </v-container>
        
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
  