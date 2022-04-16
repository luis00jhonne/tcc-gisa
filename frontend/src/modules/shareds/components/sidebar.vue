<template>
  <div>
      <div class="sidebar-wrapper">
        <div class="d-flex justify-content-between align-items-center">
          <!-- Menu -->
          <nav class="sidebar-main">
            <div id="sidebar-menu">
              <ul>
                <li>
                  <router-link to="/dashboard" :class="{active: $route.name == 'dashboard'}">Dashboard</router-link>
                </li>
                <li>
                  <router-link to="/companies">Empresas</router-link>
                </li>
                <li>
                  <router-link to="/customers">Tomadores</router-link>
                </li>
                <li>
                  <router-link to="/invoices">Faturamentos</router-link>
                </li>
                <li>
                  <router-link to="/requests">Solicitações</router-link>
                </li>
                <li>
                  <router-link to="/users">Usuários</router-link>
                </li>
              </ul>
            </div>
          </nav>
          <router-link to="/invoices/create" class="btn btn-light" type="button">Novo Faturamento</router-link>
        </div>
      </div>
  </div>
</template>
<script>
import { mapState } from "vuex";
export default {
  name: "Sidebar",
  data() {
    return {
      width: 0,
      height: 0,
      margin: 0,
      hideRightArrowRTL: true,
      hideLeftArrowRTL: true,
      hideRightArrow: true,
      hideLeftArrow: true,
      menuWidth: 0,
      toggle_sidebar_var: false,
      clicked: false
    };
  },
  computed: {
    ...mapState({
      menuItems: state => state.menu.data,
      layout: state => state.layout.layout,
      sidebar: state => state.layout.sidebarType
    })
  },
  created() {
    window.addEventListener("resize", this.handleResize);
    this.handleResize();
    if (this.width < 991) {
      this.layout.settings.sidebar.type = "default";
    }
    const val  = this.sidebar
      if (val == 'default') {			
				this.layout.settings.sidebar.type = 'default';
				this.layout.settings.sidebar.body_type = 'default';
			} else if (val == 'compact') {
				this.layout.settings.sidebar.type = 'compact-wrapper';
				this.layout.settings.sidebar.body_type = 'sidebar-icon';
			} else if (val == 'compact-icon') {
				this.layout.settings.sidebar.type = 'compact-page';
				this.layout.settings.sidebar.body_type = 'sidebar-hover';
			} else if (val == 'horizontal')  {
				this.layout.settings.sidebar.type = 'horizontal_sidebar';
        this.layout.settings.sidebar.body_type = '';
      }
      
  },
  destroyed() {
    window.removeEventListener("resize", this.handleResize);
  },
  mounted() {   
    this.menuItems.filter(items => {
      if (items.path === this.$route.path)
        this.$store.dispatch("menu/setActiveRoute", items);
      if (!items.children) return false;
      items.children.filter(subItems => {
        if (subItems.path === this.$route.path)
          this.$store.dispatch("menu/setActiveRoute", subItems);
        if (!subItems.children) return false;
        subItems.children.filter(subSubItems => {
          if (subSubItems.path === this.$route.path)
            this.$store.dispatch("menu/setActiveRoute", subSubItems);
        });
      });
    });
  },
  methods: {
    toggle_sidebar() {
      this.$store.dispatch("menu/opensidebar");
    },
    setNavActive(item) {
      this.$store.dispatch("menu/setNavActive", item);
    },
    handleResize() {
      this.width = window.innerWidth - 310;
    },
    scrollToRightRTL() {
      this.temp = this.width + this.margin;
      // Checking condition for remaing margin
      if (this.temp === 0) {
        this.margin = this.temp;
        this.hideRightArrowRTL = true;
      }
      // else
      else {
        this.margin += this.width;
        this.hideRightArrowRTL = false;
        this.hideLeftArrowRTL = false;
      }
    },
    scrollToLeftRTL() {
      // If Margin is reach between screen resolution
      console.log("this.margin", this.margin);
      if (this.margin <= -this.width) {
        this.margin += -this.width;
        this.hideLeftArrowRTL = true;
      }
      //Else
      else {
        this.margin += -this.width;
        this.hideRightArrowRTL = false;
      }
    },
    scrollToLeft() {
      console.log('left');
      
      // If Margin is reach between screen resolution
      if (this.margin >= -this.width) {
        this.margin = 0;
        this.hideLeftArrow = true;
      }
      //Else
      else {
        this.margin += this.width;
        this.hideRightArrow = false;
      }
    },
    scrollToRight() {
      this.temp = this.menuWidth + this.margin;
      // Checking condition for remaing margin
      if (this.temp < this.menuWidth) {
        this.margin = -this.temp;
        this.hideRightArrow = true;
      }
      // else
      else {
        this.margin += -this.width;
        this.hideLeftArrow = false;
      }
    }
  }
};
</script>
