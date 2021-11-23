import axios from 'axios'

export default axios.create({
  // baseURL: 'https://tarbus-server.herokuapp.com/',
  baseURL: 'http://localhost:8001/',
  // baseURL: 'https://michalus.tarbus.pl/',
  headers: {
    'Content-type': 'application/json'
  }
})
