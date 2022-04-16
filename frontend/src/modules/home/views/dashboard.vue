<template>
  <div>
    <!-- Container-fluid starts-->
    <div class="container-fluid">
      <div class="row second-chart-list third-news-update">
        <!-- Filtro -->
        <div class="col-12 xl-100 mt-5 chart_data_left box-col-12">
          <div class="card">
            <div class="card-body p-4">
              <div class="box">
                <form action="">
                  <span class="box-filtro guide mx-0">
                    <label for="dataInicio">De:</label>
                    <input class="border-0 " type="date" v-model="filter.fromDate"/>
                  </span>
                  <span class="box-filtro guide">
                    <label class="" for="dataInicio">Até:</label>
                    <input class="border-0 " type="date" v-model="filter.toDate" />
                  </span>
                  <span class="box-filtro guide">
                    <img class="" src="@/assets/images/divider.svg" alt="">
                    <label class="" for="local">Localização</label>
                    <select class="border-0" name="local" id="local">
                      <option value="1">BA</option>
                    </select>
                    <select class="border-0 ms-3" name="local" id="local">
                      <option value="1">Salvador</option>
                    </select>
                  </span>
                  <span class="box-filtro">
                    <button class="btn btn-padrao ms-3" type="button" @click="onSearch">Filtrar</button>
                  </span>
                </form>
              </div>
            </div>
          </div>
        </div>
        <!-- Empresas -->
        <div class="col-lg-6  chart_data_left box-col-12">
          <div class="card">
            <div class="card-body cd-painel p-0">
              <div class="row m-0 chart-main">
                <div class="col-12 title-card">
                  <h6>EMPRESAS</h6>
                </div>
                <div class="col-xl-3 col-md-6 col-sm-6 p-0 box-col-6">
                  <div class="media align-items-center">
                    <div class="media-body d-flex justify-content-center row">
                      <div class="col-12 text-center">
                        <h2 class="text-success">{{companySummary.totalQuantity}}</h2>
                        <p>EMPRESAS NO BxMED </p>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-xl-3 col-md-6 col-sm-6 p-0 box-col-6">
                  <div class="media align-items-center">
                    <div class="media-body d-flex justify-content-center row">
                      <div class="col-12 text-center">
                        <h2 class="text-orange">{{companySummary.inRegistrationQuantity}}</h2>
                        <p>EMPRESAS EM CADASTRO</p>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-xl-3 col-md-6 col-sm-6 p-0 box-col-6">
                  <div class="media align-items-center">
                    <div class="media-body d-flex justify-content-center row">
                      <div class="col-12 text-center">
                        <h2 class="text-warning">{{companySummary.openingQuantity}}</h2>
                        <p>EMPRESAS EM ABERTURA</p>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-xl-3 col-md-6 col-sm-6 p-0 box-col-6">
                  <div class="media align-items-center border-0">
                    <div class="media-body d-flex justify-content-center row">
                      <div class="col-12 text-center">
                        <h2 class="text-danger">{{companySummary.pendingQuantity}}</h2>
                        <p>EMPRESAS COM PENDÊNCIA</p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- Faturamento -->
        <div class="col-lg-6 chart_data_left box-col-12">
          <div class="card">
            <div class="card-body cd-painel p-0">
              <div class="row m-0 chart-main">
                <div class="col-12 title-card">
                  <h6>FATURAMENTO</h6>
                </div>
                <div class="col-xl-3 col-md-6 col-sm-6 p-0 box-col-6">
                  <div class="media align-items-center">
                    <div class="media-body d-flex justify-content-center row">
                      <div class="col-12 text-center">
                        <h2 class="text-warning">{{invoiceTotal('CREATED')}}</h2>
                        <p class="">PENDENTES</p>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-xl-3 col-md-6 col-sm-6 p-0 box-col-6">
                  <div class="media align-items-center">
                    <div class="media-body d-flex justify-content-center row">
                      <div class="col-12 text-center">
                        <h2 class="text-orange">{{invoiceTotal('ISSUED')}}</h2>
                        <p>EMITIDAS</p>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-xl-3 col-md-6 col-sm-6 p-0 box-col-6">
                  <div class="media align-items-center">
                    <div class="media-body d-flex justify-content-center row">
                      <div class="col-12 text-center">
                        <h2 class="text-success">{{invoiceTotal('PAID')}}</h2>
                        <p>COMPENSADAS</p>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-xl-3 col-md-6 col-sm-6 p-0 box-col-6">
                  <div class="media align-items-center  border-0">
                    <div class="media-body d-flex justify-content-center row">
                      <div class="col-12 text-center">
                        <h2 class="text-danger">{{invoiceTotal('OVERDUE')}}</h2>
                        <p>EM ATRASO</p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="container-fluid">
      <div class="row second-chart-list third-news-update">
        <div class="col-xl-12 xl-100 chart_data_left box-col-12">
          <div>
            <div class="card-body p-0">
              <div class="row m-0 chart-main">
                <div class="row">
                  <!--<div class="col-xs-12 col-md-6 title-card d-flex justify-content-end">
                    <select class="border-0  " name="" id="">
                      <option value="">Filtre pelo tipo de solicitação</option>
                    </select>
                  </div>-->
                </div>
                <!-- Solicitações -->
                <div class="row">
                  <!-- Em análise -->
                  <TicketsResponsable titulo="Solicitações ao meu cuidado" status="ANALYSIS" :filter="filter" :index="reloadIndex"/>
                  <!-- Em aberto -->
                  <Tickets titulo="Solicitações Em Aberto" status="OPENED" :filter="filter" :index="reloadIndex"/>
                  <!-- 
                  <Tickets status="SOLVED" :filter="filter" :index="reloadIndex"/>
                  <Tickets status="CANCELLED" :filter="filter" :index="reloadIndex"/>
                  -->
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- modal solicitação aberta -->
     <router-view></router-view>
  </div>
</template>
<script>
  import { mapState, mapActions } from 'vuex';
  import Tickets from '../components/tickets.vue';
  import TicketsResponsable from '../components/ticketsResponsable.vue';
  export default {
    data() {
      return {
        reloadIndex:0,
        filter: {
          fromDate: null,
          toDate: null
        }
      }
    },
    components:{
      Tickets,
      TicketsResponsable
    },
    computed: {
        ...mapState('companies', {
            companySummary: state => state.summary
        }),
        ...mapState('invoices', {
            invoiceSummary: state => state.summary
        })
    },
    methods: {
      ...mapActions({
        getCompanySummary: 'companies/getSummary',
        getInvoiceSummary: 'invoices/getSummary'
      }),
      invoiceTotal(status) {
        let exists = this.invoiceSummary.find(x => x.status == status);
        if(exists)
          return exists.quantity;
                  
        return 0;
      },
      onSearch() {
        this.getCompanySummary(this.filter);
        this.getInvoiceSummary(this.filter);
        this.reloadIndex += 1;
        return false;
      }
    },
    mounted() {
      this.onSearch();
    },
    async beforeRouteUpdate(to, from, next) {
      next();
      await this.onSearch();
    }
  }
</script>