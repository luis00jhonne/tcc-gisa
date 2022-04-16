<template>
     <div class="col-xl-12 col-md-12 mt-2 chart_data_left box-col-12">
        <div class="">
            <div class="box">
                <div class="d-flex">
                    <h6 v-if="list.length > 1" class="title-strong mb-4">{{titulo }}</h6>
                </div>
                <div class="row">
                   
                    <div class="col-sm-3" v-for="item in list" :key="item.id">
                        <router-link :to="`/dashboard/tickets/${item.id}/detail`">
                            <div class="card" :class="{ 'card-aberto': status === 'OPENED', 
                                                        'card-analise': status === 'ANALYSIS',
                                                        'card-conc': status === 'SOLVED',
                                                        'card-canc': status === 'CANCELLED'}">
                                <div class="card-body p-4">
                                    <div class="box">
                                        <div class="row">
                                            <h6 class="title-strong">{{item.ticketTypeName}}</h6>
                                            <p class="mb-0 font-9">Protocolo nº {{item.protocolNumber}}</p>
                                            <p class="mb-2 font-8">gerado em {{item.createdOn | formatDateTime}}</p>
                                            <p class="mb-0 font-10">Empresa: {{item.companyName}}</p>
                                            <p class="mb-2 font-10">Solicitante: {{item.createdUserName}}</p>
                                            <p class="mb-2 font-10" v-if="item.responsibleUserId">Responsável: {{item.responsibleUserName}}</p>
                                            <p class="status ms-2 px-2" 
                                                    :class="{ 'status-yellow': status === 'OPENED', 
                                                                'status-orange': status === 'ANALYSIS',
                                                                'status-green2': status === 'SOLVED',
                                                                'status-canc': status === 'CANCELLED'}">{{status | translate}}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </router-link>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
  import { mapActions } from 'vuex';
  export default {
    name: 'TicketsResponsable',
    props: ['status','titulo' , 'filter', 'index'],
    data() {
        return {
            list:[],
            
        }
    },
    computed: {
    },
    watch:{
        index: function () {
            this.onLoad();
        }
    },
    methods: {  
        ...mapActions({
        searchAnalysis: 'tickets/searchAnalysis'
      }),
      onLoad() {
        let account = JSON.parse(localStorage.getItem('account'));
        let self = this;
      
       self.searchAnalysis({
            status:'ANALYSIS',
            fromDate: this.filter.fromDate,
            toDate: this.filter.toDate,
            pageSize:20,
            ResponsibleUserId:account.id,
       }).
       then((resp)=> {
           self.list = resp.data.itens;
       });


      }
    },
    mounted() {
       this.onLoad();
       
    }
  }
</script>
