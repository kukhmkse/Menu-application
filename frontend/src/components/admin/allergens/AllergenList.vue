<script setup>
import {onMounted, provide, ref} from "vue";
import {allergenApi} from "@/service/api";
import AllergenListRow from "@/components/admin/allergens/AllergenListRow.vue";
import LoadingScreen from "@/components/custom/LoadingScreen.vue";
import AllergenListHeader from "@/components/admin/allergens/AllergenListHeader.vue";
import AllergenListEditDialog from "@/components/admin/allergens/AllergenListEditDialog.vue";
import AllergenListDeleteDialog from "@/components/admin/allergens/AllergenListDeleteDialog.vue";

const allergens = ref(null)
const error = ref(null)

//Delete
const deleteDialog = ref(false)
const deleteAllergen = ref(null)
provide('deleteDialog', deleteDialog)
provide('deleteAllergen', deleteAllergen)

//Edit
const editDialog = ref(false)
const editAllergen = ref(null)
provide('editDialog', editDialog)
provide('editAllergen', editAllergen)

const loadAllergenList = async () => {
  allergenApi.listAllergens()
      .then((res) => {
        console.log("allergens - ", res)
        allergens.value = res.sort((a, b) => a.name.localeCompare(b.name));
      })
      .catch((err) => {
        error.value = err.code
      })
}

onMounted(async () => {
  await loadAllergenList()
})

</script>

<template>
  <AllergenListDeleteDialog :delete-allergen="deleteAllergen" :callback="loadAllergenList" />
  <AllergenListEditDialog :edit-allergen="editAllergen" :callback="loadAllergenList" />
  <div style="display: flex; flex-direction: column;">
    <AllergenListHeader :edit-allergen="editAllergen" :callback="loadAllergenList" />
    <v-card class="my-4 table-style" style="width: 90%; align-self: center;">
      <LoadingScreen :items="allergens" :error="error">
        <template #table>
          <AllergenListRow v-for="allergen in allergens" :key="allergen.id" :allergen="allergen" />
          <tr v-if="!allergens.length">
            <td>{{ "No allergens yet." }}</td>
          </tr>
        </template>
      </LoadingScreen>
    </v-card>
  </div>
</template>

<style scoped>
.table-style {
  border: 2px solid #c38cea;
  border-radius: 10px;
  width: 100%;
}
</style>