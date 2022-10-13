<template>
  <div class="text-center">

   
  </div>
</template>
  
  <script>
  export default {
    name: "Content",
    data(){
      return {
        dialog : true,
      }
    },
    methods : {
      async open(){
        window.alert();
        this.dialog = true;
      }
    }
}
  </script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>

    #content {
        display: flex;
        width: auto;
        height: 1000px;
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
  