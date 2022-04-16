import Vue from "vue";
import moment from "moment";

const urlCepApi = () => {
  let url = "https://correios.grupogaia.com.br/api/address";
  return url;
};

const toastMsg = ({ text, time, type }) => {
  let typeTime = typeof time;
  let same = typeTime == "number" || typeTime == "boolean";
  let options = { timeout: same ? time : 3000 , type: type || "success" };
  Vue.$toast(text, options);
};

const validDocument = (document) => {
  let cleanDoc = "";
  cleanDoc = document.replace(/[^0-9]+/g, "");
  
  if (cleanDoc.length === 11 || cleanDoc.length === 14) {
    const validarCpfIngual = [
      "00000000000",
      "11111111111",
      "22222222222",
      "33333333333",
      "44444444444",
      "55555555555",
      "66666666666",
      "77777777777",
      "88888888888",
      "99999999999",
      "12345678900",
      "98765432100",
    ];
    
    const validarCnpjIngual = [
      "00000000000000",
      "11111111111111",
      "22222222222222",
      "33333333333333",
      "44444444444444",
      "55555555555555",
      "66666666666666",
      "77777777777777",
      "88888888888888",
      "99999999999999",
    ];

    if (cleanDoc.length == 11) {
      //CPF
      if (validarCpfIngual.includes(document)) {
        return false;
      }

      let soma = 0;
      let resto = 0;

      for (let i = 1; i <= 9; i++) soma += parseInt(cleanDoc.substring(i - 1, i)) * (11 - i);
      resto = (soma * 10) % 11;
      if (resto == 10 || resto == 11) resto = 0;
      if (resto != parseInt(cleanDoc.substring(9, 10))) {
        return false;
      }

      soma = 0;

      for (let i = 1; i <= 10; i++) soma += parseInt(cleanDoc.substring(i - 1, i)) * (12 - i);
      resto = (soma * 10) % 11;
      if (resto == 10 || resto == 11) resto = 0;
      if (resto != parseInt(cleanDoc.substring(10, 11))) {
        return false;
      }

      return true;
    }

    if (cleanDoc.length == 14) {
      //CNPJ
      if (validarCnpjIngual.includes(document)) {
        return false;
      }

      let tamanho = cleanDoc.length - 2;
      let numeros = cleanDoc.substring(0, tamanho);
      let digitos = cleanDoc.substring(tamanho);
      let soma = 0;
      let pos = tamanho - 7;

      for (let i = tamanho; i >= 1; i--) {
        soma += numeros.charAt(tamanho - i) * pos--;
        if (pos < 2) pos = 9;
      }

      let resultado = soma % 11 < 2 ? 0 : 11 - (soma % 11);

      if (resultado != digitos.charAt(0)) {
        return false;
      }

      tamanho = tamanho + 1;
      numeros = cleanDoc.substring(0, tamanho);
      soma = 0;
      pos = tamanho - 7;

      for (let i = tamanho; i >= 1; i--) {
        soma += numeros.charAt(tamanho - i) * pos--;
        if (pos < 2) pos = 9;
      }

      resultado = soma % 11 < 2 ? 0 : 11 - (soma % 11);

      if (resultado != digitos.charAt(1)) {
        return false;
      }

      return true;
    }

    return true;
  } else {
    return false;
  }
};

const documentExists = (doc, list, externalMsg) => {
  let arr = [];

  Array.from(list).forEach((objList) => removeSpecials(objList.document) == removeSpecials(doc) ? arr.push(true) : arr.push(false));

  if (Object.values(arr).some((v) => v)) {
    toastMsg({text: externalMsg || "Documento já cadastrado", type: "info"});
    return false;
  }

  return true;
};

const removeSpecials = (str) => {
  if (str !== undefined && str !== null) {
    return str.replace(/[^0-9]+/g, "");
  }
  return str;
};

const dateLimit = (date, year) => {
  let normDate = date.replace("/", "-");
  let minDate = moment.utc(`${year ? year : '1900'}-01-01`);
  if (!moment.utc(normDate).isAfter(minDate)) {
    return true;
  }

  return false;
};

const dateHigherToday = (date) => {
  let normDate = date.replace("/", "-");
  let todayDate = moment(new Date());
  if (!todayDate.isAfter(normDate)) {
    return true;
  }

  return false;
};

const jsonToFormData = (obj, rootName, ignoreList) => {
  var formData = new FormData();

  function appendFormData(data, root) {
      if (!ignore(root)) {
          root = root || '';
          if (data instanceof File) {
              formData.append(root, data);
          } else if (Array.isArray(data)) {
              for (var i = 0; i < data.length; i++) {
                  appendFormData(data[i], root + '[' + i + ']');
              }
          } else if (typeof data === 'object' && data) {
              for (var key in data) {
                  if (data.hasOwnProperty(key)) {
                      if (root === '') {
                          appendFormData(data[key], key);
                      } else {
                          appendFormData(data[key], root + '.' + key);
                      }
                  }
              }
          } else {
              if (data !== null && typeof data !== 'undefined') {
                  formData.append(root, data);
              }
          }
      }
  }

  function ignore(root){
      return Array.isArray(ignoreList)
          && ignoreList.some(function(x) { return x === root; });
  }

  appendFormData(obj, rootName);

  return formData;
}

export default {
  urlCepApi,
  toastMsg,
  validDocument,
  dateLimit,
  dateHigherToday,
  documentExists,
  removeSpecials,
  jsonToFormData
};
