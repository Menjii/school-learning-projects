import http from "@/http-common";

class OrderRateService {
  createOrderRate(data) {
    return http.post('/api/restaurant/order-rate', data)
  }
}

export default new OrderRateService();
