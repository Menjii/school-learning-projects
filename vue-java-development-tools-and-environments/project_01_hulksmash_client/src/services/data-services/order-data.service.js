import http from "@/http-common";

class OrderDataService {
  createOrderData(data) {
    return http.post('/api/restaurant/order-data/all', data)
  }
}

export default new OrderDataService();
