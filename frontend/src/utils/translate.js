import Vue from "vue"

const words = {
  CREATED: "Criado",
  ISSUED: "Emitido",
  PAID: "Pago",
  OPENED: 'Em aberto',
  ANALYSIS: "Em análise",
  SOLVED: "Concluído",
  CANCELLED: "Cancelado",
  WAITING : "Aguardando",
  IN_REGISTRATION : "Em cadastro",
  ACTIVED: "Ativa",
  OVERDUE : "Em Atraso",
  REGISTERED: "Cadastrada",
  VIABILITY: "Viabilidade",
  TAX : "Taxas",
  PAYMENT_TAX: "Aguardando Pagamento",
  DIGITAL_REGISTER: "Registro Digital",
  ACCOUNT_OPENING: "Abertura de Conta",
  ENABLED: "Habilitada",
  DOC_10: "Faturamento emitido",
  DOC_11: "Nota Fiscal",
  DOC_20: "Contrato Social",
  DOC_21: "DBE",
  DOC_22: "Taxa de abertura",
  DOC_23: "Cartão CNPJ",
  DOC_24: "Alvará",
  DOC_25: "Comprovante de residência",  
  DOC_26: "Certificado digital",
  OPENING: "Abertura",
  TRANSFER: "Transferência",
  COMPANY_DATA: "Dados Empresariais",
  COMPANY_ADDRESS: "Endereço Empresarial",  
  PARTNERS_DATA: "Dados dos Sócios",
  DOCUMENTATION: "Documentação",
  FULL_COMMUNITY_PROPERTY: "Comunhão total de bens",
  PARTIAL_COMMUNITY_PROPERTY: "Comunhão parcial de bens",
  SEPARATION_OF_PROPERTY: "Separação de bens"
};


export const translate = (value, prefix) => {
  let resp = "";
  if (prefix) {
    resp = words[`${prefix}_${value}`];
    if (!resp)
      resp = words[value];
  }
  else resp = words[value];
  return resp ? resp : `**${value} - NOT FOUND**`;
}

Vue.filter('translate', translate);

export default {
  translate
};
