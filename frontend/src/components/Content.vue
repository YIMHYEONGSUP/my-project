<template>
    <div id="content">
       

        <div id="mainContent"> 메인컨텐츠 </div>
        <div id="sideContent">

            <InformationTab></InformationTab>
            
        </div>
    </div>
</template>
  
  <script>
import InformationTab from './informationTabs/InformationTab.vue';
  export default {
    name: "Content",
    components: { InformationTab }
}
  </script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped>
    #mainContent{
        width: 70%;
        border: 1px solid black;
    }
    #sideContent{
        margin-left: 5px;
        width: 29%;
        border: 1px solid black;
    }
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
  