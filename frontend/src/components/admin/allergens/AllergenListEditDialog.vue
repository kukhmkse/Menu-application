<script setup>
import {inject, onMounted, ref, watch} from "vue";
import {allergenApi} from "@/service/api";
import LoadingScreen from "@/components/custom/LoadingScreen.vue";


const editDialog = inject('editDialog');

const props = defineProps(['editAllergen', 'callback']);

const error = ref(null)
const loading = ref(false)

const allergenData = ref(null)


const submitAllowed = () => {
  return !!allergenData.value?.name
}
const submit = () => {
  if (!submitAllowed())
    return false

  loading.value = true


  const promise = props.editAllergen ? allergenApi.allergenEdit(props.editAllergen.id, allergenData.value) : allergenApi.allergenCreate(allergenData.value)
  promise
      .then(async () => {
        props.callback()
        loading.value = false
        editDialog.value = false
      })
      .catch((err) => {
        error.value = err.code
      })
  if (allergenData.value)
    allergenData.value.id = ""
    allergenData.value.name = ""
}

const loaded = () => {
  if (props.editAllergen) {
    allergenData.value = {...props.editAllergen}
  } else {
    allergenData.value = {
      id: "",
      name: "",
    }
  }
  loading.value = false
}


watch(props, loaded)
onMounted(loaded)

</script>

<template>
  <v-dialog v-model="editDialog" width="400px" style="z-index: 9999;">
    <v-card :title="'Edit allergen'" class="dialog">
      <LoadingScreen :items="!loading" :error="error">
        <template #content>
          <div class="d-flex flex-column align-center justify-center">
            <v-form :v-if="allergenData" style="width: 80%;" @submit.prevent="submit">
              <v-text-field v-if="allergenData" v-model="allergenData.name" :label="'name'" autofocus clearable
                            variant="solo" class="custom-colored-field" />
              <v-card-actions>
                <div style="width: 100%" class="d-flex flex-row justify-space-between pa-2">
                  <button type="submit" class="pushable" :disabled="!submitAllowed()">
                    <span class="shadow" />
                    <span class="edge" />
                    <span class="front">EDIT</span>
                  </button>
                  <button :block="true" variant="tonal" class="pushable"
                          @click="editDialog = false">
                    <span class="shadow" />
                    <span class="edge" />
                    <span class="front"> CLOSE </span>
                  </button>
                </div>
              </v-card-actions>
            </v-form>
          </div>
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