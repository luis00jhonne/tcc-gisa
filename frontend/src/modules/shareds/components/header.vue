<template>
  <div class="page-header">
      <div class="header-wrapper row m-0">
        <form class="form-inline search-full col" action="#" method="get">
          <div class="form-group w-100">
            <div class="Typeahead Typeahead--twitterUsers">
              <div class="u-posRelative">
                <input class="demo-input Typeahead-input form-control-plaintext w-100" type="text"
                  placeholder="Search Cuba .." name="q" title="" autofocus>
                <div class="spinner-border Typeahead-spinner" role="status"><span class="sr-only">Loading...</span>
                </div><i class="close-search" data-feather="x"></i>
              </div>
              <div class="Typeahead-menu"></div>
            </div>
            <div class="Typeahead-menu"></div>
          </div>
        </form>
        <div class="left-header col-8 horizontal-wrapper ps-0">
          <div class="logo-BxMED">
            <img class="img-fluid" src="@/assets/images/BXMED.png" alt="">
          </div>
          <form @submit.prevent="onSearch">
            <svg width="16" height="16" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
              <path
                d="M10.9167 9.66667H10.2583L10.025 9.44167C10.8417 8.49167 11.3333 7.25833 11.3333 5.91667C11.3333 2.925 8.90833 0.5 5.91667 0.5C2.925 0.5 0.5 2.925 0.5 5.91667C0.5 8.90833 2.925 11.3333 5.91667 11.3333C7.25833 11.3333 8.49167 10.8417 9.44167 10.025L9.66667 10.2583V10.9167L13.8333 15.075L15.075 13.8333L10.9167 9.66667ZM5.91667 9.66667C3.84167 9.66667 2.16667 7.99167 2.16667 5.91667C2.16667 3.84167 3.84167 2.16667 5.91667 2.16667C7.99167 2.16667 9.66667 3.84167 9.66667 5.91667C9.66667 7.99167 7.99167 9.66667 5.91667 9.66667Z"
                fill="#C2CFE0" />
            </svg>
            <input class="form-control me-2" type="search" v-model="text" placeholder="Digite o que procura" aria-label="Search">
          </form>
        </div>
        <div class="nav-right col-4 pull-right right-header p-0">
          <ul class="nav-menus">

            <li><img class="img-fluid" src="@/assets/images/notification/Vector.png" alt=""></li>
            <li class="profile-nav onhover-dropdown p-0 me-0">
              <div class="media profile-media text-center border rounded-circle">
                <div><span style="font-size: 18px; color: white;">AD</span>

                </div>
              </div>
              <ul class="profile-dropdown onhover-show-div">
                <li class="name-perfil">
                  <span>{{account.name}}</span>
                  <span>ADMINSTRADOR</span>
                </li>
                <!--<li><router-link to="/accounts"><i data-feather="settings"></i><span>Configuração</span></router-link></li>-->
                <li><a @click="logout()"><i data-feather="log-in"> </i><span>Logout</span></a></li>
              </ul>
            </li>
          </ul>
        </div>
        <!-- Menu mobile -->
        <div id="menu-mobile">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
            stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
            class="feather feather-layers">
            <polygon points="12 2 2 7 12 12 22 7 12 2"></polygon>
            <polyline points="2 17 12 22 22 17"></polyline>
            <polyline points="2 12 12 17 22 12"></polyline>
          </svg>
        </div>
        <script class="result-template" type="text/x-handlebars-template">
            <div class="ProfileCard u-cf">                        
            <div class="ProfileCard-avatar"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-airplay m-0"><path d="M5 17H4a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h16a2 2 0 0 1 2 2v10a2 2 0 0 1-2 2h-1"></path><polygon points="12 15 17 21 7 21 12 15"></polygon></svg></div>
            <div class="ProfileCard-details">
            </div>
            </div>
        </script>
      </div>
    </div>
</template>
<script>
import { mapState, mapMutations } from "vuex";
export default {
  name: 'Header',
  data() {
    return {
      text:null,
    };
  },
   computed: {
     ...mapState('accounts', {
        account: state => state.current
     }),
      ...mapState({
        search: state => state.search
     })
   }, 
   watch:{
    $route (to){
        this.text = to.query.search;
    }
   },
   methods: {  
     ...mapMutations({
        setSearch: 'SET_SEARCH'
      }),
     logout(){
        this.$store.commit('accounts/AUTH_LOGOUT');
        this.$router.push('/login');
     },
     onSearch(){       
        let text = this.text;
        this.setSearch(text);

        if(text != null && text !== "") {
         this.$router.push('/companies?search=' + encodeURI(text));
        }else {
           this.$router.push('/companies');
        }
     },
     onLoad() {
        this.text = this.$route.query.search;
     }
  },  
  mounted(){
    this.onLoad();
  }
}
</script>
