<template>
    <v-container>

      <v-dialog
      v-model="join"
      width="500">
        <v-card>
          <v-card-title class="text-h5 grey lighten-2">
            회원가입

            <vbtn
              @click="join = false"
            >
              X
            </vbtn>
          </v-card-title>

          <v-card-text>
            <v-card>
              <VBtn>
                네이버
              </VBtn>
            </v-card>
            <v-card>
              <VBtn>
                카카오
              </VBtn>
            </v-card><v-card>
              <VBtn>
                구글
              </VBtn>
            </v-card>
          </v-card-text>

          <v-divider></v-divider>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="primary"
              text
              @click="join = false"
            >
              I accept
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

      <v-dialog
      v-model="login"
      width="500">
        <v-card>
          <v-card-title class="text-h5 grey lighten-2">
            로그인

            <vbtn
              @click="login = false"
            >
              X
            </vbtn>
          </v-card-title>

          <v-card-text>
            <v-card>
              <VBtn>
                네이버
              </VBtn>
            </v-card>
            <v-card>
              <VBtn>
                카카오
              </VBtn>
            </v-card><v-card>
              <VBtn>
                구글
              </VBtn>
            </v-card>
          </v-card-text>

          <v-divider></v-divider>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="primary"
              text
              @click="login = false"
            >
              I accept
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>



      <v-layout>

        <v-flex>
          <v-sheet>로고</v-sheet>
        </v-flex>

        <v-flex>
          <v-sheet>
            <input 
            v-model="searchKeyword"
            placeholder="검색창"
            @keyup.enter="search">

            <vBtn
            @click="search"
            >검색</vBtn>
          </v-sheet>
        </v-flex>

        <v-flex>
          <v-sheet>
            <vBtn
              @click="openLogin()"
            >로그인</vBtn>
            <vBtn
              @click="openJoin()"
            >회원가입</vBtn>
          </v-sheet>
        </v-flex>

      </v-layout>
      

    </v-container>
</template>
  
  <script>

  export default {
    name: 'Header',

    data(){
      return {
        searchKeyword: '',
        join: false,
        login: false,
      }
    },
    
    methods:{
      async search() {
        alert(this.searchKeyword)
        window.open()
      },
      async openLogin(){
        this.login = true;
      },
      async openJoin(){
        this.join = true;
      }
      
    }

  }
  </script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>

    
    v-layout{
      border: 3px solid orange;
    }
    v-flex {
      height: 50px;
      width: 30%;
      border: 3px solid orange;
    }
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
  