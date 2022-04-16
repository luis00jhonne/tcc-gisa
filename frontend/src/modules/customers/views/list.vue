<template>
<div class="container-fluid">
    <!-- Table Row Starts-->
    <div class="page-title">
        <div class="row">
            <div class="col-6">
                <h5>Tomadores</h5>
            </div>
            <div class="col-6 d-flex justify-content-end">
                <router-link to="/customers/create" class="btn btn-padrao" type="button">
                    Cadastrar tomador
                </router-link>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <!-- Filtro -->
            <div class="col-12 xl-100 chart_data_left box-col-12">
                <div class="card rounded mb-2">
                    <div class="card-body ps-2 p-2">
                        <form @submit.prevent="onFilter">
                            <div class="box box-filter">
                                <div>
                                    <!--Busca #novo -->
                                    <span class="form-filter ">
                                        <span class="form">
                                            <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                <path d="M10.9167 9.66667H10.2583L10.025 9.44167C10.8417 8.49167 11.3333 7.25833 11.3333 5.91667C11.3333 2.925 8.90833 0.5 5.91667 0.5C2.925 0.5 0.5 2.925 0.5 5.91667C0.5 8.90833 2.925 11.3333 5.91667 11.3333C7.25833 11.3333 8.49167 10.8417 9.44167 10.025L9.66667 10.2583V10.9167L13.8333 15.075L15.075 13.8333L10.9167 9.66667ZM5.91667 9.66667C3.84167 9.66667 2.16667 7.99167 2.16667 5.91667C2.16667 3.84167 3.84167 2.16667 5.91667 2.16667C7.99167 2.16667 9.66667 3.84167 9.66667 5.91667C9.66667 7.99167 7.99167 9.66667 5.91667 9.66667Z" fill="#C2CFE0"></path>
                                            </svg>
                                            <input type="search" v-model="filter.text" placeholder="Busque por Nome, CNPJ" aria-label="Search" class="">
                                        </span>
                                    </span>
                                    <!--<span class="ml-2 mr-4">
                                        <label class="" for="status">Status:</label>
                                        <select class="border-0">
                                            <option> Todos os Status</option>
                                        </select>
                                    </span>
                                    <span class="mr-4">
                                        <label class="" for="empresa">Empresa:</label>
                                        <select class="border-0">
                                            <option> Todos as Empresas</option>
                                        </select>
                                    </span>-->
                                    <button class="btn btn-padrao ms-2" type="submit">Filtrar</button>
                                </div>
                            </div>

                        </form>

                    </div>
                </div>
            </div>
            <div class="card">
                <div>
                    <div class="card-block row">
                        <div class="col-sm-12 col-lg-12 col-xl-12">
                            <div class="table-responsive mb-0">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th class="border-start-0 border-end-0 opacity-50 font-15">
                                                Nome Fantasia
                                            </th>
                                            <th class="border-start-0 border-end-0 opacity-50 font-15">
                                                Razão Social
                                            </th>
                                            <th class="border-start-0 border-end-0 opacity-50 font-15">
                                                CNPJ
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr v-for="item in itens" :key="item.name">
                                            <td class="border-start-0 border-end-0 font-15">
                                                <router-link :to="`/customers/${item.id}/detail`">{{item.companyName}}</router-link>
                                            </td>
                                            <td class="border-start-0 border-end-0 font-15">
                                                {{item.name}}
                                            </td>
                                            <td class="border-start-0 border-end-0 font-15">
                                                {{item.document|formatCnpj}}
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- <div class="d-flex justify-content-center mb-4">
          <nav aria-label="...">
            <ul class="pagination">
              <li class="page-item me-3">
                <a class="page-link rounded border-primary fw-bold" href="#">&lt;</a>
              </li>
              <li class="page-item active me-3">
                <a class="page-link rounded border-primary fw-bold" href="#">1</a>
              </li>
              <li class="page-item  me-3" aria-current="page">
                <span class="page-link rounded border-primary fw-bold">2</span>
              </li>
              <li class="page-item me-3">
                <a class="page-link rounded border-primary fw-bold" href="#">3</a>
              </li>
              <li class="page-item me-3">
                <a class="page-link rounded border-primary fw-bold" href="#">4</a>
              </li>
              <li class="page-item me-3">
                <a class="page-link rounded border-primary fw-bold" href="#">5</a>
              </li>
              <li class="page-item me-3">
                <a class="page-link rounded border-primary fw-bold" href="#">6</a>
              </li>
              <li class="page-item me-3">
                <a class="page-link rounded border-primary fw-bold" href="#">></a>
              </li>
            </ul>
          </nav>
        </div> -->
        </div>
    </div>
</div>
</template>

<script>
import {
    mapState,
    mapActions
} from 'vuex';
export default {
    data() {
        return {
            filter: {
                text: null,
                page: 1,
                pageSize: 100
            },
            pageCurrent: 0
        }
    },
    computed: {
        ...mapState('customers', {
            itens: state => state.list.itens
        })
    },
    methods: {
        ...mapActions({
            load: 'customers/search'
        }),
        onFilter() {
            this.load(this.filter);
        }
    },
    mounted() {
        this.onFilter();
    },
    async beforeRouteUpdate(to, from, next) {
        next();
        await this.onFilter();
    }
}
</script>
