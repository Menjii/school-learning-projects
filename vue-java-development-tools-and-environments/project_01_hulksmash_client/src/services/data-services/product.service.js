import http from "@/http-common";

class ProtuctService {
  saveProduct(data) {
    return http.post('/api/restaurant/food', data);
  }

  getAll() {
    return http.get('/api/restaurant/food');
  }

  getByCategory(id) {
    return http.get('/api/restaurant/foodByCategory?categoryId='+id);
  }
}

export default new ProtuctService();
