import http from "@/http-common";

class PaymentService {
  createPayment(data) {
    return http.post('/api/restaurant/payment', data)
  }
}

export default new PaymentService();
