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
          Detalhe 
        </li>
      </ol>
    </nav>
    <!-- formulario -->
    <div class="card">
                    <div class="card-body">

                        <!-- <div id="menu-dados" class="row pt-1 mb-5 menu-center border-bottom ">
                          <div class="col-4 d-flex justify-content-center">
                            <div
                              @click="aba('dados')"
                              class="d-flex justify-content-center"
                            >
                              <a v-bind:class="{ active: nomeAba == 'dados' }">
                                Dados do Tomador
                              </a>
                            </div>
                          </div>
                          <div
                            v-bind:class="{ active: nomeAba == 'empresas_vinculadas' }"
                            @click="aba('empresas_vinculadas')"
                            class="col-4 d-flex justify-content-center"
                          >
                            <div class="col-6 b d-flex justify-content-center">
                              <a v-bind:class="{ active: nomeAba == 'empresas_vinculadas' }">Empresas Vinculadas</a>
                            </div>
                          </div>
                        </div> -->
                        <div class='form-cliente'  v-if="customer">
                            <form action="" >
                                <div class="row" v-if="nomeAba == 'dados'">
                                    <div class="title-form-step mb-4 font-16"><strong>Dados Básicos</strong></div>
                                    <div class="col-2">
                                        <label class="font-15" for="">CNPJ</label>
                                        <p class="font-16">{{customer.document|formatCnpj}}</p>
                                    </div>
                                    <div class="col-3">
                                        <label class="font-15" for="">Razão Social</label>
                                        <p class="font-16">{{customer.name}}</p>
                                    </div>
                                    <div class="col-3">
                                        <label class="font-15" for="">Nome fantasia</label>
                                        <p class="font-16">{{customer.companyName}}</p>
                                    </div>
                                    <div class="col-2">
                                        <label class="font-15" for="">Inscrição Municipal</label>
                                        <p class="font-16">{{customer.municipalRegistration}}</p>
                                    </div>
                                    <div class="col-2">
                                        <label class="font-15" for="">Inscrição Estadual</label>
                                        <p class="font-16">{{customer.stateRegistration}}</p>
                                    </div>
                                  
                                </div>

                                <div class="col-12 mt-4 mb-1" v-if="nomeAba == 'dados'">
                                    <hr />
                                </div>


                                <span v-for="item in customer.addresses" :key="item.addressId">
                                    <div class="row mt-4" v-if="nomeAba == 'dados'">
                                        <div class="title-form-step mb-4 font-16"><strong>Endereço Principal</strong></div>
                                        <div class="col-2">
                                            <label class="font-15" for="">CEP</label>
                                            <p class="font-16">{{item.zipCode}}</p>
                                        </div>
                                        <div class="col-4">
                                            <label class="font-15" for="">Endereço</label>
                                            <p class="font-16">{{item.street}}</p>
                                        </div>
                                        <div class="col-2">
                                            <label class="font-15" for="">Número</label>
                                            <p class="font-16">{{item.number}}</p>
                                        </div>
                                        <div class="col-2">
                                            <label class="font-15" for="">Estado</label>
                                            <p class="font-16">{{item.stateName}}</p>
                                        </div>
                                        <div class="col-2">
                                            <label class="font-15" for="">Cidade</label>
                                            <p class="font-16">{{item.cityName}}</p>
                                        </div>
                                    </div>
                                    <div class="row mt-3" v-if="nomeAba == 'dados'">
                                        <div class="col-4">
                                            <label class="font-15" for="">Complemento</label>
                                            <p class="font-16">{{item.complement}}</p>
                                        </div>
                                    </div>
                                </span>

                                <div class="col-12 mt-4 " v-if="nomeAba == 'dados'">
                                    <hr />
                                </div>

                                <!-- contato -->
                                <div class="mt-2" v-for="contact in customer.contacts" :key="contact.contactId">
                                    <div class="row mt-4">
                                        <div class="title-form-step mb-4 font-16"><strong>Contato Geral</strong></div>
                                        <div class="col-4">
                                            <label class="font-15" for="">Nome do contato</label>
                                            <p class="font-16">{{contact.contactPerson}}</p>
                                        </div>
                                        <div class="col-4">
                                            <label class="font-15" for="">E-mail</label>
                                            <p class="font-16">{{contact.email}}</p>
                                        </div>
                                        <div class="col-2">
                                            <label class="font-15" for="">Telefone</label>
                                            <p class="font-16">{{contact.cellPhone}}</p>
                                        </div>
                                        <div class="col-2">
                                            <label class="font-15" for="">Celular</label>
                                            <p class="font-16">{{contact.phone}}</p>
                                        </div>
                                    </div>
                                    <div class="col-12 mt-5 mb-1" v-if="nomeAba == 'dados'">
                                        <hr />
                                    </div>
                                    <div class="d-flex justify-content-center" v-if="nomeAba == 'dados'">
                                        <!-- <button  class="btn btn-padrao-inverse font-14 mt-4">Editar dados</button> -->
                                        <router-link 
                                        :to="`/customers/${customer.id}`" 
                                        class="btn btn-padrao-inverse font-14 mt-4" type="button"> 
                                        Editar dados
                                        </router-link>

                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
    </div>
    <div class="card" v-if="nomeAba == 'empresas_vinculadas'">
        <div class="card-body">
            <div class="row">
                <div class="title-form-step mb-4 font-16"><strong>Empresas Vinculadas</strong></div>
                <!-- Filtro -->
                <div class="col-12 xl-100 chart_data_left box-col-12">
                     <div class="mb-2"> 
                        <div class="">
                                <div class="box box-filter">
                                    <div>
                                    <!--Busca #novo -->
                                    <span class="form-filter ml-2 mr-4">
                                        <span class="form">
                                            <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M10.9167 9.66667H10.2583L10.025 9.44167C10.8417 8.49167 11.3333 7.25833 11.3333 5.91667C11.3333 2.925 8.90833 0.5 5.91667 0.5C2.925 0.5 0.5 2.925 0.5 5.91667C0.5 8.90833 2.925 11.3333 5.91667 11.3333C7.25833 11.3333 8.49167 10.8417 9.44167 10.025L9.66667 10.2583V10.9167L13.8333 15.075L15.075 13.8333L10.9167 9.66667ZM5.91667 9.66667C3.84167 9.66667 2.16667 7.99167 2.16667 5.91667C2.16667 3.84167 3.84167 2.16667 5.91667 2.16667C7.99167 2.16667 9.66667 3.84167 9.66667 5.91667C9.66667 7.99167 7.99167 9.66667 5.91667 9.66667Z" fill="#C2CFE0"></path></svg>
                                            <input type="search" placeholder="Busque por Nome, Documento, CNPJ" aria-label="Search" class="">
                                        </span>
                                    </span>
                                    <!--Empresa #novo -->
                                    <span class="mr-4">
                                        <label class="" for="empresa">Empresa:</label>
                                        <select  class="border-0">                              
                                            <option> Todos as Empresas</option>
                                        </select>
                                    </span>
                                    <!--/Empresa #novo -->
                                    <!--/Busca #novo -->
                                    <button class="btn btn-padrao ms-2">Filtrar</button>
                                    </div>
                                </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12 col-lg-12 col-xl-12">
                <div class="table-responsive mt-3 mb-0">
                  <table class="table">
                    <thead>
                      <tr>
                        <th
                          class="border-start-0 border-end-0 opacity-50 font-15"
                        >
                         Empresa
                        </th>
                        <th
                          class="border-start-0 border-end-0 opacity-50 font-15"
                        >
                         CNPJ da Empresa
                        </th>
                        <th
                          class="border-start-0 border-end-0 opacity-50 font-15"
                        >
                         Nome do Contato
                        </th>
                        <th
                          class="border-start-0 border-end-0 opacity-50 font-15"
                        >
                         E-mail do Contato
                        </th>
                        <th
                          class="border-start-0 border-end-0 opacity-50 font-15"
                        >
                         Telefone do Contato
                        </th>
                        <th
                          class="border-start-0 border-end-0 opacity-50 font-15"
                        >
                         Celular do Contato
                        </th>
                        <th
                          class="border-start-0 border-end-0 opacity-50 font-15"
                        >
                        Ações
                        </th>
                      </tr>
                     </thead>
                      <tbody>
                      <tr>
                        <td class="border-start-0 border-end-0 font-15">
                          Bezerra SA
                        </td>
                        <td class="border-start-0 border-end-0 font-15">
                          11.222.333/0001-44   
                        </td>
                        <td
                          class="border-start-0 border-end-0 font-15"
                        >
                         João Soares Braga
                        </td>
                        <td class="border-start-0 border-end-0 font-15">
                          email@dotomador.com.br
                        </td>
                        <td class="border-start-0 border-end-0 font-15">
                          (71) 8888 8888   
                        </td>
                        <td
                          class="border-start-0 border-end-0 font-15"
                        >
                         (71) 9 8888 8888
                        </td>
                        <td
                          class="border-start-0 border-end-0 font-15"
                        >
                         <span class="mr-4">
                             <a href="javascript:void(0);" @click="$bvModal.show('vincular')">
                                <svg class="mr-1" width="12" height="12" viewBox="0 0 12 12" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M0 9.33697V11.1945C0 11.3656 0.140289 11.5 0.318838 11.5H2.25737C2.34027 11.5 2.42317 11.4694 2.48056 11.4083L9.44398 4.74205L7.0527 2.4507L0.0956515 9.117C0.0318839 9.1781 0 9.25142 0 9.33697ZM11.2932 2.97007C11.5419 2.73177 11.5419 2.34683 11.2932 2.10853L9.80108 0.678725C9.55239 0.440425 9.15065 0.440425 8.90196 0.678725L7.73501 1.7969L10.1263 4.08825L11.2932 2.97007V2.97007Z" fill="#172B4D"/>
                                </svg>
                                Editar
                            </a>
                         </span>
                         <span>
                             <a href="" class="btn-excluir">
                                 <svg class="mr-1" width="10" height="12" viewBox="0 0 10 12" fill="none" xmlns="http://www.w3.org/2000/svg">
                                     <path d="M1.471 10.2778C1.471 10.95 2.01443 11.5 2.67863 11.5H7.50914C8.17334 11.5 8.71677 10.95 8.71677 10.2778V4.16667C8.71677 3.49444 8.17334 2.94444 7.50914 2.94444H2.67863C2.01443 2.94444 1.471 3.49444 1.471 4.16667V10.2778ZM7.20724 1.11111L6.77853 0.677222C6.66984 0.567222 6.51285 0.5 6.35586 0.5H3.83192C3.67492 0.5 3.51793 0.567222 3.40925 0.677222L2.98054 1.11111H1.471C1.1389 1.11111 0.867188 1.38611 0.867188 1.72222C0.867188 2.05833 1.1389 2.33333 1.471 2.33333H8.71677C9.04887 2.33333 9.32059 2.05833 9.32059 1.72222C9.32059 1.38611 9.04887 1.11111 8.71677 1.11111H7.20724Z" fill="#EB5757"/>
                                 </svg>
                                 Excluir
                            </a>
                         </span>
                        </td>
                      </tr>
                      <tr>
                        <td class="border-start-0 border-end-0 font-15">
                          Bezerra SA
                        </td>
                        <td class="border-start-0 border-end-0 font-15">
                          11.222.333/0001-44   
                        </td>
                        <td
                          class="border-start-0 border-end-0 font-15"
                        >
                         João Soares Braga
                        </td>
                        <td class="border-start-0 border-end-0 font-15">
                          email@dotomador.com.br
                        </td>
                        <td class="border-start-0 border-end-0 font-15">
                          (71) 8888 8888   
                        </td>
                        <td
                          class="border-start-0 border-end-0 font-15"
                        >
                         (71) 9 8888 8888
                        </td>
                        <td
                          class="border-start-0 border-end-0 font-15"
                        >
                         <span class="mr-4">
                             <a href="javascript:void(0);" @click="$bvModal.show('vincular')">
                                <svg class="mr-1" width="12" height="12" viewBox="0 0 12 12" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M0 9.33697V11.1945C0 11.3656 0.140289 11.5 0.318838 11.5H2.25737C2.34027 11.5 2.42317 11.4694 2.48056 11.4083L9.44398 4.74205L7.0527 2.4507L0.0956515 9.117C0.0318839 9.1781 0 9.25142 0 9.33697ZM11.2932 2.97007C11.5419 2.73177 11.5419 2.34683 11.2932 2.10853L9.80108 0.678725C9.55239 0.440425 9.15065 0.440425 8.90196 0.678725L7.73501 1.7969L10.1263 4.08825L11.2932 2.97007V2.97007Z" fill="#172B4D"/>
                                </svg>
                                Editar
                            </a>
                         </span>
                         <span>
                             <a href="" class="btn-excluir">
                                 <svg class="mr-1" width="10" height="12" viewBox="0 0 10 12" fill="none" xmlns="http://www.w3.org/2000/svg">
                                     <path d="M1.471 10.2778C1.471 10.95 2.01443 11.5 2.67863 11.5H7.50914C8.17334 11.5 8.71677 10.95 8.71677 10.2778V4.16667C8.71677 3.49444 8.17334 2.94444 7.50914 2.94444H2.67863C2.01443 2.94444 1.471 3.49444 1.471 4.16667V10.2778ZM7.20724 1.11111L6.77853 0.677222C6.66984 0.567222 6.51285 0.5 6.35586 0.5H3.83192C3.67492 0.5 3.51793 0.567222 3.40925 0.677222L2.98054 1.11111H1.471C1.1389 1.11111 0.867188 1.38611 0.867188 1.72222C0.867188 2.05833 1.1389 2.33333 1.471 2.33333H8.71677C9.04887 2.33333 9.32059 2.05833 9.32059 1.72222C9.32059 1.38611 9.04887 1.11111 8.71677 1.11111H7.20724Z" fill="#EB5757"/>
                                 </svg>
                                 Excluir
                            </a>
                         </span>
                        </td>
                      </tr>
                      <tr>
                        <td class="border-start-0 border-end-0 font-15">
                          Bezerra SA
                        </td>
                        <td class="border-start-0 border-end-0 font-15">
                          11.222.333/0001-44   
                        </td>
                        <td
                          class="border-start-0 border-end-0 font-15"
                        >
                         João Soares Braga
                        </td>
                        <td class="border-start-0 border-end-0 font-15">
                          email@dotomador.com.br
                        </td>
                        <td class="border-start-0 border-end-0 font-15">
                          (71) 8888 8888   
                        </td>
                        <td
                          class="border-start-0 border-end-0 font-15"
                        >
                         (71) 9 8888 8888
                        </td>
                        <td
                          class="border-start-0 border-end-0 font-15"
                        >
                         <span class="mr-4">
                             <a href="javascript:void(0);" @click="$bvModal.show('vincular')">
                                <svg class="mr-1" width="12" height="12" viewBox="0 0 12 12" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M0 9.33697V11.1945C0 11.3656 0.140289 11.5 0.318838 11.5H2.25737C2.34027 11.5 2.42317 11.4694 2.48056 11.4083L9.44398 4.74205L7.0527 2.4507L0.0956515 9.117C0.0318839 9.1781 0 9.25142 0 9.33697ZM11.2932 2.97007C11.5419 2.73177 11.5419 2.34683 11.2932 2.10853L9.80108 0.678725C9.55239 0.440425 9.15065 0.440425 8.90196 0.678725L7.73501 1.7969L10.1263 4.08825L11.2932 2.97007V2.97007Z" fill="#172B4D"/>
                                </svg>
                                Editar
                            </a>
                         </span>
                         <span>
                             <a href="" class="btn-excluir">
                                 <svg class="mr-1" width="10" height="12" viewBox="0 0 10 12" fill="none" xmlns="http://www.w3.org/2000/svg">
                                     <path d="M1.471 10.2778C1.471 10.95 2.01443 11.5 2.67863 11.5H7.50914C8.17334 11.5 8.71677 10.95 8.71677 10.2778V4.16667C8.71677 3.49444 8.17334 2.94444 7.50914 2.94444H2.67863C2.01443 2.94444 1.471 3.49444 1.471 4.16667V10.2778ZM7.20724 1.11111L6.77853 0.677222C6.66984 0.567222 6.51285 0.5 6.35586 0.5H3.83192C3.67492 0.5 3.51793 0.567222 3.40925 0.677222L2.98054 1.11111H1.471C1.1389 1.11111 0.867188 1.38611 0.867188 1.72222C0.867188 2.05833 1.1389 2.33333 1.471 2.33333H8.71677C9.04887 2.33333 9.32059 2.05833 9.32059 1.72222C9.32059 1.38611 9.04887 1.11111 8.71677 1.11111H7.20724Z" fill="#EB5757"/>
                                 </svg>
                                 Excluir
                            </a>
                         </span>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
        </div>
    </div>
  <Vincular/>
  </div>
</template>

<script>
   import { mapState, mapActions } from "vuex";
   import Vincular from '../components/vincular-modal.vue';

        export default {
        data() {
            return {
            tabIndex: 0,
             nomeAba: "dados",
              id : null 
            };
        },
        computed: {
          ...mapState('customers', {
              customer: state => state.current
          })
        },
        methods: { 
          ...mapActions({
            get: "customers/get",
          }),
          aba(args) {
            this.nomeAba = args;
          }
        }, 
        mounted(){
        var id = this.$route.params.id;
        if(id)
          this.get(id);

          this.id = id;
        },
        components:{
            Vincular
        },
        };
</script>

<style>
</style>