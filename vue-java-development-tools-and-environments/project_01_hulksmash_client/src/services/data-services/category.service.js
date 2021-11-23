import http from "@/http-common";

class CategoryService {
  createNewCategory(data) {
    return http.post('/api/restaurant/category', data);
  }

  getAll() {
    return http.get('/api/restaurant/category');
  }
}

export default new CategoryService();
