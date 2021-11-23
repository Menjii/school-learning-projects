import http from '../http-common'
import authHeader from './auth-header';

class UserService {
  getPublicContent() {
    return http.get('all');
  }

  getUserBoard() {
    return http.get('/api/test/user', { headers: authHeader() });
  }

  getModeratorBoard() {
    return http.get('/api/test/mod', { headers: authHeader() });
  }

  getAdminBoard() {
    return http.get('/api/test/admin', { headers: authHeader() });
  }

  updateUser(user) {
    console.log(user);
    return http.post('/api/auth/updateUser', user);
  }
}

export default new UserService();
