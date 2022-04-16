<template>
  <div class="container-fluid">
    <nav
      class="mt-5"
      style="--bs-breadcrumb-divider: '>'"
      aria-label="breadcrumb"
    >
      <ol class="breadcrumb mt-5 d-flex align-items-center">
        <li class="breadcrumb-item mt-4">
          <router-link to="/customers">Tomadores</router-link>
        </li>
        <li class="breadcrumb-item font-20 mt-4" aria-current="page">
          Cadastro 
        </li>
      </ol>
    </nav>
    <!-- formulario -->
    <div class="card">
      <div class="card-body">
        <div class="form-cadastro">
          <form @submit.prevent="onSave">
            <div class="row">
              <div class="title-form-step mb-4 font-16"><strong>Dados Básicos</strong></div>
              <div class="col-3">
                <label for="">CNPJ <span class="red">*</span></label>
                <!-- <input
                  v-mask="'##.###.###/####-##'"
                  class="form-control"
                  id="input-text-1"
                  type="text"
                  required
                  v-model="customer.document"
                  
                  title=""
                /> -->
                 <the-mask 
                    type="text"
                    v-model="customer.document" 
                    class="form-control" :mask="'##.###.###/####-##'" /> 
              </div>
              <div class="col-6">
                <label for="">Razão Social<span class="red">*</span></label>
                <input
                  class="form-control"
                  id="input-text-1"
                  required
                  v-model="customer.name"
                  type="text"
                  data-bs-original-title=""
                  title=""
                />
              </div>
              <div class="col-3">
                <label for="">Nome Fantasia <span class="red">*</span></label>
                <input
                  class="form-control"
                  id="input-text-1"
                   required
                   v-model="customer.companyName"
                  type="text"
                  data-bs-original-title=""
                  title=""
                />
              </div>
              <!-- <div class="col-2">
                <label for="">Prazo de pagamento</label>
                <select class="form-select digits" id="select-1">
                  <option active value="Option 1">1 mês</option>
                  <option value="Option 2">2 meses</option>
                  <option value="Option 3">3 meses</option>
                </select>
              </div> -->
            </div>
            <div class="row mt-3">
              <div class="col-4">
                <label for="">Inscrição municipal </label>
                <input
                  class="form-control"
                  maxlength="20"
                  v-model="customer.municipalRegistration"
                  type="text"
                  data-bs-original-title=""
                  title=""
                />
              </div>
              <div class="col-3">
                <label for="">Inscrição Estadual </label>
                <input
                  class="form-control"
                  maxlength="20"
                  v-model="customer.stateRegistration"
                  type="text"
                  data-bs-original-title=""
                  title=""
                />
              </div>
            </div>

            <div class="col-12 mt-5 mb-5">
              <hr />
            </div>

            <div v-for="(address, index) in customer.addresses" :key="index"  class="row mt-3">
              <div class="title-form-step mb-4 font-16"> <strong>Endereço Principal<span class="red">*</span></strong></div>
              <div class="row mb-3">
                <div class="col-2">
                  <label for="">CEP <span class="red">*</span></label>
                  <the-mask 
                    type="text" required
                    v-model="customer.addresses[index].zipCode" 
                    class="form-control" :mask="'#####-###'" />
                </div>
              </div>
              <div class="col-10 mb-3">
                <label for="">Endereço <span class="red">*</span></label>
                <input
                  class="form-control"
                  id="input-text-1"
                  required
                  v-model="customer.addresses[index].street"
                  type="text"
                  data-bs-original-title=""
                  title=""
                />
              </div>
              <div class="col-2  mb-3">
                <label for="">Número <span class="red">*</span></label>
                <input
                  class="form-control"
                  id="input-text-1"
                  required
                  v-model="customer.addresses[index].number"
                  type="number"
                  data-bs-original-title=""
                  title=""
                />
              </div>
              <div class="col-3">
                <label for="">Estado <span class="red">*</span></label>
                <select required v-model="customer.addresses[index].stateId" class="form-control" @change="onChangeState(customer.addresses[index].stateId)">
                            <option value="" selected>Selecione...</option>
                            <option v-for="option in states" v-bind:value="option.id" :key="option.id">{{ option.name }}</option>
                </select>
              </div>
              <div class="col-3">
                <label for="">Cidade <span class="red">*</span></label>
                <select required v-model="customer.addresses[index].cityId" class="form-control">
                            <option value="" selected>Selecione...</option>
                            <option v-for="option in cities" v-bind:value="option.id" :key="option.id">{{ option.name }}</option>
                </select>
              </div>
              <div class="col-6">
                <label for="">Complemento</label>
                <input
                  class="form-control"
                  id="input-text-1"
                  v-model="customer.addresses[index].complement"
                  type="text"
                  data-bs-original-title=""
                  title=""
                />
              </div>
            </div>

            <div class="col-12 mt-5 mb-5">
              <hr />
            </div>
            <!-- contato -->
            <div class="mt-4">
              <div class="title-form-step mb-4 font-16"><strong>Contato Geral</strong></div>
              <div class="row">
                <div class="col-4">
                  <label for="">Nome de contato <span class="red">*</span></label>
                  <input
                    class="form-control"
                    id="input-text-1"
                    v-model="customer.contacts[0].contactPerson"
                    type="text"
                    required
                    data-bs-original-title=""
                    title=""
                  />
                </div>
                <div class="col-4">
                  <label for="">E-mail <span class="red">*</span></label>
                  <input
                    class="form-control"
                    id="input-text-1"
                    v-model="customer.contacts[0].email"
                    type="email"
                    required
                    data-bs-original-title=""
                    title=""
                  />
                </div>
                <div class="col-2">
                  <label for="">Celular <span class="red">*</span></label>
                   <the-mask 
                    type="text"
                    required
                    v-model="customer.contacts[0].cellPhone" 
                    class="form-control" :mask="['(##) ####-####', '(##) #####-####']" />
                </div>
                <div class="col-2">
                  <label for="">Telefone </label>
                   <the-mask 
                    type="text"
                    required
                    v-model="customer.contacts[0].phone" 
                    class="form-control" :mask="['(##) ####-####', '(##) #####-####']" />
                </div>
              </div>
              <div class="col-12 mt-5 ">
                <hr />
              </div>
              <div class="red text-right font-12 mb-3">(<strong>*</strong>) Campos obrigatórios</div>
              <div class="d-flex justify-content-between">
              
                <router-link  class="btn btn-padrao-inverse font-14 mt-3" to="/customers">Cancelar</router-link>
                <button type="submit" class="btn btn-padrao font-14 mt-3">
                  {{btn}}
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import {
    mapActions,
    mapState,
    mapMutations
} from "vuex";
import useVuelidate from '@vuelidate/core';

import {
    required
} from '@vuelidate/validators';
import Vue from 'vue'
import VueTheMask from 'vue-the-mask'
Vue.use(VueTheMask);

export default {
    setup() {
        return {
            v$: useVuelidate()
        }
    },
    data() {
        return {
            isLoading: false,
            isUpdate: false,
            btn:'Cadastrar Tomador',
            states: [],
            cities: [],
            customer: {
              companyName: null,
              document: null,
              municipalRegistration: null,
              stateRegistration: null,
              name: null,
              addresses: [{
                isPrincipal:true,
                complement:null,
                street:null,
                zipCode:null,
                neighborhood:"null",
                number:null,
                cityId:0,
                stateId:0,
                stateName:null,
                cityName:null
                }],
              contacts: [{
                cellPhone: null,
                phone: null,
                email:null,
                contactPerson: null
              }],
            },
        }
    },
    validations() {
        return {
            customer: {
                companyName: {
                    required
                },
                document: {
                    required
                },
                name: {
                    required
                },
            }
        }
    },
    computed: {
        ...mapState('customers', {
            customers: state => state.select
        }),
    },
    methods: {
        ...mapActions({
            create: "customers/create",
            update: "customers/update",
            get: "customers/get",
            getStates: "customers/getStates",
            getCities: "customers/getCities",
        }),
        ...mapMutations({
            new: 'customers/CLEAR_CUSTOMER'
        }),
        
        onSave() {
            this.v$.$touch();
            if (this.v$.$invalid) return false;

            this.isLoading = true;
            let self = this;
            this.customer.addresses[0].stateId = parseFloat(this.customer.addresses[0].stateId); 
            this.customer.addresses[0].cityId = parseFloat(this.customer.addresses[0].cityId); 
            this.customer.document = this.customer.document.toString(); 
            let data = self.customer;
            console.log(data);   
            var rota = this.$route.params.id;
            if (rota) {
                self.update({
                    id: rota,
                    data: data
                }).then(() => {
                    self.$router.push('/customers/' + rota + '/detail');
                });
            } else {
                self.create({
                    data: data
                }).then((resp) => {
                    self.$router.push('/customers/' + resp.data.id + '/detail');
                });
            }
        },
        onChangeState(id) {
            this.getCities(id).then(async (resp) => {
              this.cities = resp.data;
            });
        },
    },
    created() {
        this.new();
    },
    async mounted() {

       // await Promise.all(this.getCustomers());

       this.getStates().then((result) => {
                this.states = result.data;
       });

        var id = this.$route.params.id;
        if (id && id != 'create') {
            await this.get(id).then((result) => {
                    this.btn = 'Atualizar Tomador';
                  
                    
                    //this.customer = result.data;
                
                   
                    let address = result.data.addresses[0];
                    let contact = result.data.contacts[0];
                    
                    this.customer = {
                        companyName: result.data.companyName,
                        document: result.data.document,
                        municipalRegistration: result.data.municipalRegistration,
                        stateRegistration: result.data.stateRegistration,
                        name: result.data.name,
                        isActived: result.data.isActived,
                        createdOn: result.data.createdOn,
                        addresses: [{
                          id: address.addressId,
                          isPrincipal:address.isPrincipal,
                          complement:address.complement,
                          street:address.street,
                          zipCode:address.zipCode,
                          neighborhood:"null",
                          number:address.number,
                          cityId:parseFloat(address.cityId),
                          stateId:parseFloat(address.stateId)
                          }],
                        contacts: [{
                          id: contact.contactId,
                          cellPhone: contact.cellPhone,
                          phone: contact.phone,
                          email:contact.email,
                          contactPerson: contact.contactPerson
                        }],
                    },
                    this.getCities(address.stateId).then(async (resp) => {
                      this.cities = resp.data;
                    });
                
            });
            this.isUpdate = true;
        }
    }
};

   
</script>

<style>
</style>