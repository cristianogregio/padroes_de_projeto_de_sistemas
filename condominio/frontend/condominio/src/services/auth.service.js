import axios from 'axios';

const API_URL = 'http://localhost:8082/condominio/autenticacao/';

class AuthService {
  login(user) {
    return axios
      .post(API_URL + 'login', {
        nome: user.nome,
        senha: user.senha
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem('user');
  }

  register(user) {
    return axios.post(API_URL + 'cadastro', {
      nome: user.nome,
      senha: user.senha
    });
  }
}

export default new AuthService();
