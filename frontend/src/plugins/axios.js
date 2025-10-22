import axios from 'axios'


axios.defaults.baseURL = 'http://localhost:8081'
axios.defaults.withCredentials = true
console.log(`Using API URL: ${axios.defaults.baseURL}`)


export default axios
