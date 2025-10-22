<script setup>
import AppBar from "@/components/custom/AppBar.vue";
</script>

<template>
  <v-app>
    <AppBar />
    <v-main style="background-color: #f9ecd4">
      <router-view />
    </v-main>
  </v-app>
</template>
