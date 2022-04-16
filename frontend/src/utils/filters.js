import Vue from "vue"
import moment from 'moment';

moment.locale('pt-br');
Vue.prototype.$myMoment = moment;

export const formatDate = (d) => {
  if (d === undefined || d === null) return '';
  return moment(d).format('DD/MM/YYYY');
};

export const formatDateTime = (d) => {
  if (d === undefined || d === null) return '';
  return moment(d).format('MM/DD/YYYY hh:mm');
};

export const formatCpf = (cpf) => {
  if (cpf && cpf.length == 11) {
    cpf = cpf.replace(/[^\d]/g, "");
    return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, "$1.$2.$3-$4");
  }
  return cpf;
}

export const formatCnpj = (cnpj) => {
  if (cnpj) {
    cnpj=cnpj.replace(/[^\d]/g, "");
    cnpj=cnpj.replace(/^(\d{2})(\d)/,"$1.$2"); 
    cnpj=cnpj.replace(/^(\d{2})\.(\d{3})(\d)/,"$1.$2.$3");
    cnpj=cnpj.replace(/\.(\d{3})(\d)/,".$1/$2");
    cnpj=cnpj.replace(/(\d{4})(\d)/,"$1-$2");   
    return cnpj;
  }
  return cnpj;
}

export const formatMoney = (amount, decimalCount = 2, decimal = ",", thousands = ".") => {
  if (amount == 0) return "R$ 0,00"; // se o valor for 0, retorna o 0
  try {
    decimalCount = Math.abs(decimalCount);
    decimalCount = isNaN(decimalCount) ? 2 : decimalCount;

    const negativeSign = amount < 0 ? "-" : "";

    let i = parseInt(
      (amount = Math.abs(Number(amount) || 0).toFixed(decimalCount))
    ).toString();
    let j = i.length > 3 ? i.length % 3 : 0;

    return (
      "R$ " +
      negativeSign +
      (j ? i.substr(0, j) + thousands : "") +
      i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousands) +
      (decimalCount
        ? decimal +
        Math.abs(amount - i)
          .toFixed(decimalCount)
          .slice(2)
        : "")
    );
  } catch (e) {
    console.log(e);
  }
}

export const toLower = (value) => {
  if (value) {
    return value.toLowerCase();
  }
  return value;
}

export const  formatCep = (str) => {
  if (str) {
    str = str.replace(/[^\d]/g, "");      
    var re = /^([\d]{2})\.*([\d]{3})-*([\d]{3})/;
    if(re.test(str)){
      return str.replace(re,"$1.$2-$3");
    }
  }
	return "";
}

export const notInfo = (str) => {
  if (str && str.trim() !== "") {
    return str;
  }
	return "Não Informado";
}

Vue.filter('formatDate', formatDate);
Vue.filter('formatDateTime', formatDateTime);
Vue.filter('formatCpf', formatCpf);
Vue.filter('formatCnpj', formatCnpj);
Vue.filter('formatMoney', formatMoney);
Vue.filter('toLower', toLower);
Vue.filter('formatCep', formatCep);
Vue.filter('notInfo', notInfo);

export default {
  formatDate,
  formatDateTime,
  formatCpf,
  formatMoney,
  toLower,
  notInfo
};
