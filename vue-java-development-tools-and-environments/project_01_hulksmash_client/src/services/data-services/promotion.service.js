import http from "@/http-common";

class PromotionService {
  getPromotionById(id) {
    return http.get('/api/restaurant/promotion?id='+id);
  }

  changePromotionStatus(id) {
    return http.get('/api/restaurant/promotionStatus?id='+id);
  }
}

export default new PromotionService();
