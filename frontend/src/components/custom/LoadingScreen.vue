<script setup>
import { watch } from 'vue'

const props = defineProps(['items', 'error'])
watch(props, async (newProps, oldProps) => {
  if (newProps !== oldProps) console.log(newProps.error ? newProps.error : newProps.items)
})
</script>

<template>
  <slot v-if="items && !error" name="items">
    <div class="no-padding">
      <slot name="prepend" />
      <slot name="content">
        <v-table>
          <tbody>
            <slot name="table" />
          </tbody>
        </v-table>
      </slot>
    </div>
  </slot>
  <div v-else-if="error">
    <slot name="error">
      {{ "Error" }}
    </slot>
  </div>
  <v-card-item v-else class="pa-8 flex justify-center">
    <slot name="loading">
      <v-progress-circular indeterminate="true" />
    </slot>
  </v-card-item>
</template>

<style scoped>
.no-padding {
  padding: 0;
  margin: 0;
}
</style>