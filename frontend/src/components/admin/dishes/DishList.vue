<script setup>
import {onMounted, provide, ref} from "vue";
import {dishApi} from "@/service/api";
import DishListRow from "@/components/admin/dishes/DishListRow.vue";
import LoadingScreen from "@/components/custom/LoadingScreen.vue";
import DishListHeader from "@/components/admin/dishes/DishListHeader.vue";
import DishListEditDialog from "@/components/admin/dishes/DishListEditDialog.vue";
import DishListDeleteDialog from "@/components/admin/dishes/DishListDeleteDialog.vue";
import AddAllergenDialog from "@/components/admin/dishes/AddAllergenDialog.vue";

const dishes = ref(null)
const error = ref(null)

//Delete
const deleteDialog = ref(false)
const deleteDish = ref(null)
provide('deleteDialog', deleteDialog)
provide('deleteDish', deleteDish)

const addAllergenDialog = ref(false)
provide('addAllergenDialog', addAllergenDialog)

//Edit
const editDialog = ref(false)
const editDish = ref(null)
provide('editDialog', editDialog)
provide('editDish', editDish)

const loadDishList = async () => {
  dishApi.listDishes()
      .then((res) => {
        console.log("dishes - ", res)
        dishes.value = res.sort((a, b) => a.name.localeCompare(b.name));
      })
      .catch((err) => {
        error.value = err.code
      })
}

onMounted(async () => {
  await loadDishList()
})

</script>

<template>
  <AddAllergenDialog :edit-dish="editDish" :callback="loadDishList" />
  <DishListDeleteDialog :delete-dish="deleteDish" :callback="loadDishList" />
  <DishListEditDialog :edit-dish="editDish" :callback="loadDishList" />
  <div style="display: flex; flex-direction: column;">
    <DishListHeader :edit-dish="editDish" :callback="loadDishList" :is-admin="true" />
    <v-card class="my-4 table-style" style="width: 90%; align-self: center;">
      <LoadingScreen :items="dishes" :error="error">
        <template #table>
          <DishListRow v-for="dish in dishes" :key="dish.id" :dish="dish" :is-admin="true" />
          <tr v-if="!dishes.length">
            <td>{{ "No dishes yet." }}</td>
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