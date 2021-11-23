import http from "@/http-common";

class OrderService {
  getAll() {
    return http.get('/api/restaurant/orders/actual')
  }
  getAllUser(id) {
    return http.get('/api/restaurant/orders/user?id='+id)
  }
  updateOrder(data) {
    return http.post('/api/restaurant/order', data)
  }
  createOrder(data) {
    return http.post('/api/restaurant/order', data)
  }
}

export default new OrderService();
