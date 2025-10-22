import axios from '@/plugins/axios'

export const dishApi = {

    async listDishes() {
        const response = await axios.get('/dishes')
        return response.data
    },

    async dishDetail(id){
        const response = await axios.get(`/dishes/${id}`)
        return response.data
    },

    async dishDelete(id){
        const response = await axios.delete(`/dishes/${id}`)
        return response.data
    },

    async dishCreate(dish){
        const response = await axios.post(`/dishes`, dish)
        return response.data
    },

    async dishEdit(id, dish){
        console.log("dishEdit - ", dish)
        const response = await axios.patch(`/dishes/${id}`, dish)
        return response.data
    },

    async addDishAllergen(dishId, allergenId){
        const response = await axios.put(`/dishes/${dishId}/allergens/${allergenId}`)
        return response.data
    },

    async deleteDishAllergen(dishId, allergenId){
        const response = await axios.delete(`/dishes/${dishId}/allergens/${allergenId}`)
        return response.data
    },

    async findDishesWithoutAllergens(allergens){
        const allergenList = allergens.map( id => `allergens=${encodeURIComponent(id)}`).join(`&`)
        if (allergenList.length > 0){
            const response = await axios.get(`/dishes/without?${allergenList}`)
            return response.data
        } else {
            const response = await axios.get(`/dishes`)
            return response.data
        }


    },

    async findDishesByType(typeId) {
        const response = await axios.get(`/dishes/type/${typeId}`)
        return response.data
    }
}

export const dishTypeApi = {

    async listDishTypes() {
        const response = await axios.get('/types')
        return response.data
    },

    async dishTypeDetail(id){
        const response = await axios.get(`/types/${id}`)
        return response.data
    },

    async dishTypeDelete(id){
        const response = await axios.delete(`/types/${id}`)
        return response.data
    },

    async dishTypeCreate(dish){
        const response = await axios.post(`/types`, dish)
        return response.data
    },

    async dishTypeEdit(id, dish){
        const response = await axios.patch(`/types/${id}`, dish)
        return response.data
    },
}

export const allergenApi = {

    async listAllergens() {
        const response = await axios.get('/allergens')
        return response.data
    },

    async AllergenDetail(id){
        const response = await axios.get(`/allergens/${id}`)
        return response.data
    },

    async allergenDelete(id){
        const response = await axios.delete(`/allergens/${id}`)
        return response.data
    },

    async allergenCreate(dish){
        const response = await axios.post(`/allergens`, dish)
        return response.data
    },

    async allergenEdit(id, dish){
        const response = await axios.patch(`/allergens/${id}`, dish)
        return response.data
    },

    async findAllergensByDish(dishId) {
        const response = await axios.get(`/allergens/dish/${dishId}`)
        return response.data
    }
}

