<template>

  <v-app>
    <v-app-bar app>
      <Header></Header>
    </v-app-bar>

    <v-main>
      <Content>
        <router-view/>
      </Content>
    </v-main>

    <Footer></Footer>

  </v-app>


</template>

<script>
import Header from './components/Header.vue';
import Footer from './components/Footer.vue';
import Content from './components/Content.vue';

export default {
    name: "App",
    data: () => ({
    //
    }),
    components: { Header, Footer, Content }
}
</script>
