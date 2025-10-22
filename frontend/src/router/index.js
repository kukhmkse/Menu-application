import { createRouter, createWebHistory } from 'vue-router'
import AllergenListView from "@/views/AllergenListView.vue";
import DishListView from "@/views/DishListView.vue";
import TypeListView from "@/views/TypeListView.vue";
import UserView from "@/views/UserView.vue";
import AdminView from "@/views/AdminView.vue";
import HomeView from "@/views/HomeView.vue";

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomeView,
  },
  {
    path: '/admin',
    name: 'Admin',
    component: AdminView,
  },
  {
    path: '/user',
    name: 'User',
    component: UserView,
  },
  {
    path: '/types',
    name: 'Types',
    component: TypeListView,
  },
  {
    path: '/allergens',
    name: 'Allergens',
    component: AllergenListView,
  },
  {
    path: '/dishes',
    name: 'Dishes',
    component: DishListView,
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})


export default router
