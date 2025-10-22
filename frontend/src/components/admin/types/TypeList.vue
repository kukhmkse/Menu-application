<script setup>
import {onMounted, provide, ref} from "vue";
import {dishTypeApi} from "@/service/api";
import TypeListRow from "@/components/admin/types/TypeListRow.vue";
import LoadingScreen from "@/components/custom/LoadingScreen.vue";
import TypeListHeader from "@/components/admin/types/TypeListHeader.vue";
import TypeListEditDialog from "@/components/admin/types/TypeListEditDialog.vue";
import TypeListDeleteDialog from "@/components/admin/types/TypeListDeleteDialog.vue";

const types = ref(null)
const error = ref(null)

//Delete
const deleteDialog = ref(false)
const deleteType = ref(null)
provide('deleteDialog', deleteDialog)
provide('deleteType', deleteType)

//Edit
const editDialog = ref(false)
const editType = ref(null)
provide('editDialog', editDialog)
provide('editType', editType)

const loadTypeList = async () => {
  dishTypeApi.listDishTypes()
      .then((res) => {
        console.log("types - ", res)
        types.value = res.sort((a, b) => a.name.localeCompare(b.name));
        })
        .catch((err) => {
          error.value = err.code
        })
}

onMounted(async () => {
  await loadTypeList()
})

</script>

<template>
  <TypeListDeleteDialog :delete-type="deleteType" :callback="loadTypeList" />
  <TypeListEditDialog :edit-type="editType" :callback="loadTypeList" />
  <div style="display: flex; flex-direction: column;">
    <TypeListHeader :edit-type="editType" :callback="loadTypeList" />
    <v-card class="my-4 table-style pa-0" style="width: 90%; align-self: center;">
      <LoadingScreen :items="types" :error="error">
        <template #table>
          <table class="full-width-table">
            <TypeListRow v-for="type in types" :key="type.id" :type="type" />
            <tr v-if="!types.length">
              <td>{{ "No dish types yet." }}</td>
            </tr>
          </table>
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
  border-collapse: collapse;
  margin: 0;
}

.full-width-table {
  width: 100%;
  border-collapse: collapse;
  border-spacing: 0;
  margin: 0;
  padding: 0;
}

.full-width-table tr,
.full-width-table td {
  margin: 0;
  padding: 8px;
  border: none;
}
</style>