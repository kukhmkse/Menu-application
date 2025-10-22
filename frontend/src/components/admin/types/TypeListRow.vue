<script setup>

import {inject} from "vue";
import SvgIcon from '@jamescoyle/vue-icon';
import { mdiPencilCircle, mdiDeleteCircle } from '@mdi/js';

defineProps(['type']);

//Delete
const deleteDialog = inject('deleteDialog');
const deleteType = inject('deleteType');

//Edit
const editDialog = inject('editDialog');
const editType = inject('editType');

</script>

<template>
  <tr>
    <td>
      {{ type.name }}
    </td>
    <td>
      {{ type.weight }}
    </td>

    <td>
      <div style="text-align: right; display: flex; justify-content: flex-end; gap: 10px">
        <button
          class="pushable"
          @click="(e) => { e.stopPropagation(); editType = type; editDialog = true }"
        >
          <span class="shadow" />
          <span class="edge" />
          <span class="front"> 
            <svg-icon
              type="mdi"
              :path="mdiPencilCircle"
            /> 
          </span>
        </button>
        <button
          class="pushable"
          @click="(e) => { e.stopPropagation(); deleteType = type; deleteDialog = true }"
        >
          <span class="shadow" />
          <span class="edge" />
          <span class="front"> 
            <svg-icon
              type="mdi"
              :path="mdiDeleteCircle"
            /> 
          </span>
        </button>
      </div>
    </td>
  </tr>
</template>

<style scoped>
tr {
  border-bottom: 1px solid #ccc;
}

tr > td:first-child {
  width: 1%;
  white-space: nowrap;
}

tr > td:last-child {
  width: auto;
}

tr:nth-child(even) {
  background-color: #eed1ff;
}

tr:nth-child(odd) {
  background-color: rgba(238, 209, 255, 0.47);
}

td {
  padding: 8px;
}


.pushable {
  position: relative;
  background: transparent;
  padding: 0px;
  border: none;
  width: 36px;
  height: 36px;
  border-radius: 50%;
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
  border-radius: 50%;
  background: hsl(226, 25%, 69%);
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
  border-radius: 50%;
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
  width: 100%;
  height: 100%;
  border-radius: 50%;
  position: relative;
  background: hsl(287, 39%, 39%);
  padding: 6px 6px;
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