<template>

    <div id="InformationTab">
    
        <v-tabs>
            <v-tab @Click="toggleTab('UserInformation')">Item One</v-tab>
            <v-tab @Click="toggleTab('MarketInformation')">Item Two</v-tab>
        </v-tabs>
        <component :is="currentTab" keep-alive/>

    </div>

</template>

<script>

import UserInformation from '../UserInformation.vue';
import MarketInformation from '../MarketInformation.vue';

export default {
name: 'InfomationTab',
data() {
    return {
        currentTab : 'UserInformation' // current tab
    };
},
components: {
    UserInformation,
    MarketInformation,
},
methods: {
    toggleTab: function(tab){
        this.currentTab = tab;
    }
}

}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

#InformationTab { 
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
