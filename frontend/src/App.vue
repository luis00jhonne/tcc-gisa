<template>
  <div id="app">
  <!-- Loader starts-->
    <div class="loader-wrapper" :class="{'loderhide' : !show }">
      <div class="loader-index">
        <span></span>
      </div>
    </div>
  <!-- Loader ends-->

    <!--<Main/>-->
    <router-view></router-view>
  </div>
</template>

<script>
import { mapActions, mapState, mapMutations } from "vuex";
export default {
  name: 'app',
  data(){
    return{
    }
  },
 computed: {
     ...mapState({
        show: state => state.isLoading
     })
 },
  methods:{
    ...mapMutations({
      hide: 'HIDE_LOADING'
    }),
    timeOut(){
          var self = this;
          setTimeout(function(){
              self.hide();
          }, 1000);
    }
  },
  mounted() {
      this.timeOut();
  },
}
</script>

<style lang="scss" scoped>
.loader-wrapper.loderhide {
  display: none;
}
</style>
