<template>
  <div class="container">
    <header class="jumbotron">

 {{contentErro}}
<div class="row">
  <div class="col-3">
    <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
      <a class="nav-link active" id="v-pills-home-tab" data-toggle="pill" href="#v-pills-home" role="tab" aria-controls="v-pills-home" aria-selected="true">INÍCIO</a>
      <a class="nav-link" id="v-pills-infouni-tab" data-toggle="pill" href="#v-pills-infouni" role="tab" aria-controls="v-pills-infouni" aria-selected="false">INFORMAÇÃO UNIDADE</a>
      <a class="nav-link" id="v-pills-boleto-tab" data-toggle="pill" href="#v-pills-boleto" role="tab" aria-controls="v-pills-boleto" aria-selected="false">2ª VIA BOLETO</a>
      <a class="nav-link" id="v-pills-ocorrrencias-tab" data-toggle="pill" href="#v-pills-ocorrrencias" role="tab" aria-controls="v-pills-ocorrrencias" aria-selected="false">OCORRÊNCIAS</a>
      <a class="nav-link" id="v-pills-reserva-tab" data-toggle="pill" href="#v-pills-reserva" role="tab" aria-controls="v-pills-reserva" aria-selected="false">RESERVAS</a>
    </div>
  </div>
  <div class="col-9">
    <div class="tab-content" id="v-pills-tabContent">
      <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel" aria-labelledby="v-pills-home-tab"><img align="center" src="@/assets/unidade.jpg"> </div>
      <div class="tab-pane fade" id="v-pills-infouni" role="tabpanel" aria-labelledby="v-pills-infouni-tab"> Não informação sobre sua unidade ainda.</div>
      <div class="tab-pane fade" id="v-pills-boleto" role="tabpanel" aria-labelledby="v-pills-boleto-tab"> Não há boletos pendentes! Tudo pagdo :)</div>
      <div class="tab-pane fade" id="v-pills-ocorrrencias" role="tabpanel" aria-labelledby="v-pills-ocorrrencias-tab">Não há ocorrrências!</div>
      <div class="tab-pane fade" id="v-pills-reserva" role="tabpanel" aria-labelledby="v-pills-reserva-tab">
        
      <h4 align="center"> Reserva de Salão de Festa </h4><br>
 
        <table align="center">
            <tr align="center">
              <th>#</th>
              <th>Salão</th>
              <th>Churrasqueira</th>
              <th>Reservas / Datas</th>
            </tr>
            <tr align="center" v-for="(item) in content" :key="item" >
                <td>{{ item.id}}</td>
                <td>{{ item.nome}}</td>
                <td>{{ item.churrasqueira ? 'Sim' : 'Não' }}</td>
                <td>
                  <ul>
                    <li v-for="(reserva) in item.reservas" :key="reserva">
                      {{reserva.data.substring(0, 10)}} - {{reserva.aprovado ? "Reservado" : "Não Aprovado"}}
                    </li>
                </ul>
                </td>
            </tr>
           
          </table>
  <br>

    <div class="col-md-12">
    <div class="card card-container">
      <form action="" align="center" name="form" @submit.prevent="reservar">
         <div class="form-group">
          <label for="salao">Salão  </label>
        <select v-model="reserva.salao" name="salao">
            <option disabled value="">Escolha um salão</option>
              <option v-for="(item) in content" :key="item" >{{ item.id}}</option>
          </select>
         </div>
         <div class="form-group">
          <label for="salao">Data da Reserva</label>  
              <input type="date" name="data" value="2020-11-01" min="2020-11-01" max="2032-12-31" v-model="reserva.data">
            </div> 
         <div class="form-group">
              <button type="submit">Reservar</button>
          </div>
          </form>
          </div>
    </div>

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
    reservar() {
      if (this.reserva.salao && this.reserva.data) {
      SalaoService.reservarSalao(this.reserva).then(
      () => {
        //location.href = '/morador/#v-pills-reserva';
        //this.$router.push('/morador/');
        location.reload();
      },
      error => {
        this.contentErro = 
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
      }
    );
      
      }  
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