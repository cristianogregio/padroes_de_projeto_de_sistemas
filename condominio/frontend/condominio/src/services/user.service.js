import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8082/condominio/';

class UserService {
  getPublicContent() {
    return axios.get(API_URL + 'publico');
  }

  getModeratorBoard() {
    return axios.get(API_URL + 'morador', { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_URL + 'sindico', { headers: authHeader() });
  }
}

export default new UserService();
