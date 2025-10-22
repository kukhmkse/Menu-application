<script setup>


defineProps(['isAdmin']);


</script>

<template>
  <tr class="header-row">
    <td>
      {{ "id" }}
    </td>
    <td>
      {{ "name" }}
    </td>
    <td>
      {{ "cuisine" }}
    </td>
    <td>
      {{ "price" }}
    </td>
    <td>
      {{ "taste" }}
    </td>
    <td>
      {{ "type" }}
    </td>
    <td>
      {{ "allergens" }}
    </td>
  </tr>
</template>

<style scoped>
.header-row {
  background-color: #82449f;
  color: white;
  height: 40px;
}

.header-row td {
  padding-left: 15px;
}
</style>