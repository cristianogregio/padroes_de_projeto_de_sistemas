export default function reservaCadastroJSON(reserva){

  var obj = { "data": reserva.data, "salao": [ { "id" : reserva.salao }]};
  //var res = JSON.stringify(obj);

  return obj;

}
