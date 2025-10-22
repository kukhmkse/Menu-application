<script setup>
import {onMounted, ref} from "vue";
import {allergenApi, dishApi, dishTypeApi} from "@/service/api";
import DishListRow from "@/components/admin/dishes/DishListRow.vue";
import LoadingScreen from "@/components/custom/LoadingScreen.vue";
import DishListHeader from "@/components/admin/dishes/DishListHeader.vue";
import NoAllergenDishListHeader from "@/components/user/NoAllergenDishListHeader.vue";

const dishes = ref([])
const allergens = ref([])
const error = ref(null)
const allergensList = ref([])
const typesList = ref([])
const types = ref([])
/*const loadDishList = async () => {
  dishApi.listDishes()
      .then((res) => {
        console.log("dishes - ", res)
        dishes.value = res.sort((a, b) => a.name.localeCompare(b.name));
        allergenApi.listAllergens()
            .then((res2) => {
              allergens.value = res2.sort((a, b) => a.name.localeCompare(b.name));
              console.log("allergens - ", res2)
            })
            .catch((err) => {
              error.value = err.code
            })
      })
      .catch((err) => {
        error.value = err.code
      })
}*/

const reload = () => {
  Promise.all([
    dishApi.listDishes(),
    allergenApi.listAllergens(),
    dishTypeApi.listDishTypes(),
  ])
      .then((result) => {
        let [di, al, dit] = result
        dishes.value = di
        allergens.value = al
        types.value = dit
      })
      .catch((err) => { error.value = err.code })
}

const findDishes = () => {
  console.log("allergensChosen - ", allergensList.value)
  console.log("typesChosen - ", typesList.value)
  dishApi.findDishesWithoutAllergens(allergensList.value)
      .then((res) => {
        console.log("dishes - ", res)
        if (typesList.value.length > 0) {
          dishes.value = res
              .filter(dish => typesList.value.includes(dish.type.id))
              .sort((a, b) => a.name.localeCompare(b.name));
        } else {
          dishes.value = res
              .sort((a, b) => a.name.localeCompare(b.name));
        }

      })
      .catch((err) => {
        error.value = err.code
      })
}

/*onMounted(async () => {
  await loadDishList()
})*/

onMounted(async () => { await reload() })
</script>

<template>
  <div style="display: flex; flex-direction: column;">
    <DishListHeader :edit-dish="editDish" :callback="reload" :is-admin="false" />
    <div style="font-weight: bold; padding-left: 30px; margin-bottom: 4px; color:#6a1b9a">
      Exclude allergens:
    </div>
    <div style="display: flex; gap: 12px; padding-left: 30px; margin-bottom: 8px; justify-content: flex-start; width:50%">
      <v-select
        v-model="allergensList"
        :items="allergens"
        item-title="name"
        item-value="id"
        multiple
        label="Select Allergens"
        variant="solo"
        class="custom-colored-field"
        style="width: 180px;"
      />
      <v-select
        v-model="typesList"
        :items="types"
        item-title="name"
        item-value="id"
        multiple
        label="Select Types"
        variant="solo"
        class="custom-colored-field"
        style="width: 180px;"
      />
    </div>
    <div style="padding-left: 30px; margin-top: 4px; width: 50px" @click="findDishes">
      <button
        class="pushable">
        <span class="shadow" />
        <span class="edge" />
        <span class="front"> Find </span>
      </button>
    </div>
    <v-card class="my-4 table-style" style="width: 90%; align-self: center;">
      <LoadingScreen :items="dishes" :error="error">
        <template #table>
          <table style="width: 100%; border-collapse: collapse;">
            <thead>
              <NoAllergenDishListHeader />
            </thead>
            <tbody>
              <DishListRow v-for="dish in dishes" :key="dish.id" :dish="dish" :is-admin="false" />
              <tr v-if="!dishes.length">
                <td colspan="5" style="text-align:center;">
                  No dishes yet.
                </td>
              </tr>
            </tbody>
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