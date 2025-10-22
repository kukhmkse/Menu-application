<script setup>
import {inject} from "vue";

const editDialog = inject('editDialog')
const editAllergen = inject('editAllergenEntity')

</script>
<template>
  <v-toolbar class="toolbar" flat>
    <v-toolbar-title class="font-weight-bold">
      {{ "Allergen list" }}
    </v-toolbar-title>
    <div style="text-align: right; display: flex; justify-content: flex-end; padding-right: 20px"
         @click="(e) => { e.stopPropagation(); editAllergen = null; editDialog = true }">
      <button
        class="pushable">
        <span class="shadow" />
        <span class="edge" />
        <span class="front"> Create allergen </span>
      </button>
    </div>
  </v-toolbar>
</template>

<style>
.toolbar {
  background-color: #f9ecd4 ;
  box-shadow: none ;
  border: none ;
}


.pushable {
  position: relative;
  background: transparent;
  padding: 0px;
  border: none;
  cursor: pointer;
  outline-offset: 4px;
  outline-color: deeppink;
  transition: filter 250ms;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}

.shadow {
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  background: hsl(226, 25%, 69%);
  border-radius: 8px;
  filter: blur(2px);
  will-change: transform;
  transform: translateY(2px);
  transition: transform 600ms cubic-bezier(0.3, 0.7, 0.4, 1);
}

.edge {
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  border-radius: 8px;
  background: linear-gradient(
      to right,
      hsl(290, 13%, 63%) 0%,
      hsl(292, 49%, 27%) 8%,
      hsl(290, 13%, 63%) 92%,
      hsl(286, 19%, 53%) 100%
  );
}

.front {
  display: block;
  position: relative;
  border-radius: 8px;
  background: hsl(287, 39%, 39%);
  padding: 4px 8px;
  color: white;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 1.5px;
  font-size: 1rem;
  transform: translateY(-4px);
  transition: transform 600ms cubic-bezier(0.3, 0.7, 0.4, 1);
}

.pushable:hover {
  filter: brightness(110%);
}

.pushable:hover .front {
  transform: translateY(-6px);
  transition: transform 250ms cubic-bezier(0.3, 0.7, 0.4, 1.5);
}

.pushable:active .front {
  transform: translateY(-2px);
  transition: transform 34ms;
}

.pushable:hover .shadow {
  transform: translateY(4px);
  transition: transform 250ms cubic-bezier(0.3, 0.7, 0.4, 1.5);
}

.pushable:active .shadow {
  transform: translateY(1px);
  transition: transform 34ms;
}

.pushable:focus:not(:focus-visible) {
  outline: none;
}
</style>