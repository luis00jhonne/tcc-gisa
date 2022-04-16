<template>
  <div>
    <div class="container-fluid p-0">
      <div class="row m-0">
        <div class="col-12 p-0">
          <div class="login-card">
            <div>
              <div class="login-main">
                <b-card no-body>
                  <b-tabs pills vertical>
                    <b-tab active>
                      <b-card-text>
                        <div>
                          <a class="logo">
                            <img
                              class="img-fluid for-light"
                              src="@/assets/images/logo_BXMED.png"
                              alt="looginpage"
                            />
                          </a>
                        </div>
                        <form v-if="!passwordReset" class="theme-form" @submit.prevent="onLogin">
                          <div class="form-group row align-self-center">
                           <div class="col-md-2 align-self-center">
                              <label for="username"><strong>Email:</strong></label>
                           </div>
                           <div class="col-md-10">
                                <input
                                type="email"
                                v-model="email"
                                class="form-control"
                                :class="{'is-invalid':v$.email.$error}"
                              />
                              <div
                                class="invalid-feedback"
                              >
                                Email é obrigatório
                              </div>
                           </div>
                          </div>
                          <div class="form-group row align-self-center">
                            <div class="col-md-2 align-self-center">
                              <label htmlFor="password"><strong>Senha:</strong></label>
                            </div>
                            <div class="col-md-10">
                                  <input
                                  type="password"
                                  v-model="password"
                                  class="form-control"
                                  :class="{'is-invalid':v$.password.$error}"/>
                                <div
                                  class="invalid-feedback"
                                >
                                  Senha é obrigatório
                                </div>
                            </div>
                          </div>
                          <!--<div class="pt-2 pb-2 d-flex justify-content-end">
                              <a href="javascript:void(0)"  @click="resetPassword()" class="esqueci"> Esqueci a senha</a>                           
                          </div>-->
                          <div class="form-group mt-3 mb-0 text-center">
                            <button
                              class="btn btn-primary btn-block"
                            >
                              Entrar
                            </button>
                          </div>
                        </form>
                        <!--<form v-if="passwordReset" class="theme-form" @submit.prevent="passwordSubmit">
                          <div class="txt-password mt-3 mb-3">
                            <strong>Esqueceu a senha?</strong>
                            Insira o e-mail cadastrado que enviaremos as instruções para alteração da senha.
                            
                            <div  v-if="messageReset"  class="mt-3 alert alert-success text-center">
                                Verifique seu e-mail.
                            </div>

                            <div  v-if="emailNotReal"  class="mt-3 alert alert-danger text-center">
                               E-mail não existe em nossa base.
                            </div>
                          </div>
                          <div class="form-group row align-self-center">
                           <div class="col-md-2 align-self-center">
                              <label for="username"><strong>Email:</strong></label>
                           </div>
                           <div class="col-md-10">
                                <input
                                type="email"
                                v-model="usernameReset"
                                name="username"
                                class="form-control"
                                :class="{ 'is-invalid': submittedReset && !usernameReset }"
                              />
                              <div
                                v-show="submittedReset && !usernameReset"
                                class="invalid-feedback"
                              >
                                Email é obrigatório
                              </div>
                           </div>
                          </div>
                          <div class="form-group mt-3 mb-0 text-center">
                            <button
                              class="btn btn-primary btn-block"
                            >
                              Recuperar senha
                            </button>
                          </div>
                        </form>-->
                      </b-card-text>
                    </b-tab>
                  </b-tabs>
                </b-card>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>    
  </div>
</template>

<script>
import { mapActions } from "vuex";
import useVuelidate from '@vuelidate/core';
import { required, email } from '@vuelidate/validators';
//import ClipLoader from 'vue-spinner/src/ClipLoader.vue';

export default {
  name: "Login",
  setup () {
    return { v$: useVuelidate() }
  }, 
  components: {
    //ClipLoader
  },
  data() {
    return {
      email: "",
      password: "",
      isLoading: false,
      passwordReset: false
    };
  },
  validations() {
     return {
        email: { email, required },
        password: { required }
     }
  },
  methods: {
    ...mapActions({
      login: "accounts/login",
    }),

    onLogin() {
      let self = this;
      self.v$.$touch();      
      if (self.v$.$invalid) {
        return false;
      }      
      this.isLoading = true;

      let login = { grant_type: "password", client_id: "backoffice", username: this.email, password: this.password };

      self.login(login).then(() => {
         self.$router.push('/dashboard');
      }).finally(() => {
          self.password = "";
          this.isLoading = false;
      });
    },
    resetPassword(){
         this.passwordReset = true;
    },
  },

  async mounted() {
    this.$store.commit('accounts/AUTH_LOGOUT');
  }
    
};

</script>