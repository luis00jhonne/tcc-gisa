<template>
  <div class="container-fluid">
    <!-- formulario -->
    <div class="card pt-0">
      <div class="card-body pt-0">
        <div
          id="menu-dados"
          class="row pt-4 border-bottom justify-content-center"
        >
          <div class="col-4 d-flex justify-content-center">
            <div
              class="col-6 d-flex justify-content-center"
              @click="aba('usuario')"
            >
              <a v-bind:class="{ active: nomeAba == 'usuario' }"
                >Usuário sistema</a
              >
            </div>
          </div>
          <!--<div class="col-4 d-flex justify-content-center">
            <div
              class="col-6 b d-flex justify-content-center"
              @click="aba('perfil')"
            >
              <a v-bind:class="{ active: nomeAba == 'perfil' }">Perfil</a>
            </div>
          </div>-->
        </div>
        <!-- USUARIO -->
        <div
          v-if="nomeAba == 'usuario'"
          id="usuario"
          class="form-cadastro border-top"
        >
          <table class="table table-bordered">
            <tbody>
              <tr>
                <td class="border-start-0 border-end-0 opacity-50 font-13">
                  Nome
                </td>
                <td class="border-start-0 border-end-0 opacity-50 font-13">
                  E-mail
                </td>
                <td class="border-start-0 border-end-0 opacity-50 font-13">
                  Perfil
                </td>
              </tr>
              <tr v-for="item in itens" :key="item.id">
                <td class="border-start-0 border-end-0 font-15">
                  <a
                    v-b-modal.detalhe-usuario
                  >
                    {{item.name}}
                  </a>
                </td>
                <td class="border-start-0 border-end-0 font-13">
                  <p class="mb-0">
                     {{item.email}}
                  </p>
                </td>
                <td class="border-start-0 border-end-0 font-12">
                  <p class="status mb-0 border p-1 status-white font-14">
                    {{item.roleKey}}
                  </p>
                </td>
              </tr>             
            </tbody>
          </table>
          <!--<div class="d-flex mt-5 justify-content-center">
            <a
            v-b-modal.cadastro
              class="btn btn-padrao-inverse"
            >
              Cadastrar novo usuário do sistema
            </a>
          </div>-->
        </div>
        <!--profiles-->
      </div>
    </div>
    <CreateEditModal/>
    <CreateEditConfirmModal/>
    <CreateEditRoleModal/>
    <DetailModal/>
    <!-- Container-fluid Ends-->
  </div>
  
</template>

<script>
import { mapState, mapActions } from 'vuex';
import CreateEditModal from '../components/create-edit-modal.vue';
import CreateEditConfirmModal from '../components/create_edit_confirm_modal.vue';
import CreateEditRoleModal from '../components/create-edit-role-modal.vue';
import DetailModal from '../components/detail-modal.vue';

export default {
  name: "AccountList",
  data() {
    return {
      nomeAba: "usuario",
    };
  },
  components:{
    CreateEditModal,
    CreateEditConfirmModal,
    CreateEditRoleModal,
    DetailModal
  },
  computed: {
     ...mapState('accounts', {
        itens: state => state.list
     })
  },
  methods: {
    ...mapActions({
        load: 'accounts/search'
    }),
    aba(args) {
      this.nomeAba = args;
    },
  },
  mounted() {
    this.load();
  }
};
</script>