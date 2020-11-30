import axios from 'axios';
import authHeader from './auth-header';
import reservaCadastroJSON from './reserva';
import aprovarReservaJSON from './reserva2';

const API_URL = 'http://localhost:8082/condominio/';

class SalaoService {
  getTodosSaloes() {
    return axios.get(API_URL + 'salao/todos', { headers: authHeader() });
  }

  reservarSalao(reserva) {
    //return axios.post(API_URL + 'reserva/cadastrar', { headers: authHeader(), data: reservaCadastroJSON(reserva) });
    return axios({
      method: "POST",
      url: API_URL + 'reserva/cadastrar',
      headers: authHeader(),
      data: reservaCadastroJSON(reserva)
       
    });
  }

  aprovarReserva(reserva) {
    return axios({
      method: "POST",
      url: API_URL + 'reserva/aprovar',
      headers: authHeader(),
      data: aprovarReservaJSON(reserva)
       
    });
  }

}

export default new SalaoService();