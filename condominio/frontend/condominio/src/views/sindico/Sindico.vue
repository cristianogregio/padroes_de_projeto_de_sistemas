<template>
  <div class="container">
    <header class="jumbotron">
        
{{contentErro}}
<div class="row">
  <div class="col-3">
    <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
      <a class="nav-link active" id="v-pills-home-tab" data-toggle="pill" href="#v-pills-home" role="tab" aria-controls="v-pills-home" aria-selected="true">INÍCIO</a>
      <a class="nav-link" id="v-pills-ocorrrencias-tab" data-toggle="pill" href="#v-pills-ocorrrencias" role="tab" aria-controls="v-pills-ocorrrencias" aria-selected="false">OCORRÊNCIAS</a>
      <a class="nav-link" id="v-pills-reserva-tab" data-toggle="pill" href="#v-pills-reserva" role="tab" aria-controls="v-pills-reserva" aria-selected="false">RESERVAS</a>
    </div>
  </div>
  <div class="col-9">
    <div class="tab-content" id="v-pills-tabContent">
      <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel" aria-labelledby="v-pills-home-tab"><img align="center" src="@/assets/unidade.jpg"> </div>
      <div class="tab-pane fade" id="v-pills-ocorrrencias" role="tabpanel" aria-labelledby="v-pills-ocorrrencias-tab">Não há ocorrrências!</div>
      <div class="tab-pane fade" id="v-pills-reserva" role="tabpanel" aria-labelledby="v-pills-reserva-tab">
        
      <h4 align="center"> Reserva de Salão de Festa </h4><br>
 
          <table align="center">
            <tr align="center">
              <th>Salão</th>
              <th>Reservas / Datas</th>
            </tr>
            <tr align="center" v-for="(item) in content" :key="item" >
                <td>{{ item.nome}}</td>
                <td>
                  <ul>
                    <li v-for="(reserva) in item.reservas" :key="reserva" v-on:click="deletarReserva(reserva, item.nome)">
                      {{reserva.data.substring(0, 10)}} - {{reserva.aprovado ? 'RESERVADO' : "Em aprovaçao"}}
                    </li>
                </ul>
                </td>
            </tr>
          </table>

      </div>
    </div>
  </div>
</div>
    </header>
  </div>
</template>

<script>
import SalaoService from '@/services/salao.service';
import Reserva from '@/models/reserva';

export default {
  name: 'salao',
  data() {
    return {
      content: '',
      contentErro: '',
      reserva: new Reserva('', '')
    };
  },
  methods:{
    deletarReserva(reserva, nome) {
        var r = confirm("Deseja aprovar a reserva **" + reserva.data.substring(0, 10) + "** do salão " + nome);
        if (r == true) {
      //alert(reserva.id);
      SalaoService.aprovarReserva(reserva.id).then(
      () => {
        location.reload();
      },
      error => {
        this.contentErro = 
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
      }
    );
      
        } else {
        return {};
        }
    },
    aprovarReserva(){
        
    }
  },
  mounted() {
    SalaoService.getTodosSaloes().then(
      response => {
        this.content = response.data;
      },
      error => {
        this.contentErro = 
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
      }
    );
  }
};
</script>

<style>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
</style>