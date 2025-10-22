<script setup>
import {inject, onMounted, provide, ref, watch} from "vue";
import {allergenApi} from "@/service/api";
import LoadingScreen from "@/components/custom/LoadingScreen.vue";
import AddAllergenRow from "@/components/admin/dishes/AddAllergenRow.vue";



const props = defineProps(['editDish', 'callback']);

const error = ref(null)

const addAllergenDialog = inject('addAllergenDialog');
provide('addAllergenDialog', addAllergenDialog)
const allergens = ref([])

const reloadDialog = () => {
  console.log("editdish - ", props.editDish)
  Promise.all([
    allergenApi.listAllergens(),
  ])
      .then((result) => {
        let [al] = result
        allergens.value = al
      })
      .catch((err) => { error.value = err.code })
}
onMounted(async () => { reloadDialog() })

const isPresent = (allergenId) => {
  return props.editDish.value?.allergens?.some(a => a.id === allergenId)
}

watch(props, reloadDialog)

</script>

<template>
  <v-dialog v-model="addAllergenDialog" width="400px" style="z-index: 9999;">
    <v-card :title="'Add allergen'" class="dialog">
      <LoadingScreen :items="allergens" :error="error">
        <template #table>
          <AddAllergenRow v-for="allergen in allergens" :key="allergen.id" :allergen="allergen" :is-present="isPresent(allergen.id)"
                          :edit-dish="editDish" :callback="callback" />
          <tr v-if="!allergens.length">
            <td>{{ "No allergens yet." }}</td>
          </tr>
        </template>
      </LoadingScreen>
    </v-card>
  </v-dialog>
</template>

<style>

.v-overlay__content {
  border-radius: 32px !important;
  overflow: hidden;
}

.custom-colored-field .v-field {
  background-color: #f3e5f5 !important;
  border-radius: 8px;
}

.custom-colored-field input {
  color: #6a1b9a !important;
  font-weight: 500;
}

.custom-colored-field .v-label {
  color: #6a1b9a !important;
}

.custom-colored-field .v-field__input {
  background-color: transparent !important;
}

.v-dialog__content {
  border-radius: 32px !important; /* make dialog container corners rounded */
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