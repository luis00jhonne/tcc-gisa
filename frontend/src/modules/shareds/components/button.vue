<template>
  <button class="vue-btn" :class="{getBackgroundClass, hideText: loading}" type="submit">
    <transition name="fade" mode="out-in">
      <div :class="getSpinnerClass"></div>
    </transition>
    <slot v-if="showSlot"></slot>
  </button>
</template>

<script>
  export default {
    name: 'vue-button-loading',
    props: {
      isLoading: {
        type: Boolean,
        default: false
      },
      status: {
        type: String,
        required: false,
        default: ''
      }
    },
    computed: {
      getSpinnerClass() {
        return {
          'spinner': this.loading,
          'check': !this.emptyStatus && this.isSuccess && !this.loading,
          'cross': !this.emptyStatus && !this.isSuccess && !this.loading
        }
      },
      getBackgroundClass() {
        return {
          'vue-btn-loader-error': !this.emptyStatus && !this.isSuccess,
          'vue-btn-loader-success': this.isSuccess,
          'is-loading': this.loading
        }
      },
      loading() {
        return this.isLoading
      },
      isSuccess() {
        return this.status === 'success' || this.status === true
      },
      emptyStatus() {
        return this.status === ''
      },
      showSlot() {
        return (this.loading) || (!this.loading && this.emptyStatus)
      }
    }
  }
</script>

<style lang="css" scoped>
  .fade-enter-active,
  .fade-leave-active {
    transition: opacity 1s;
  }
  .fade-enter,
  .fade-leave-active {
    opacity: 0;
    will-change: opacity;
  }
  @keyframes rotation {
    from {
      transform: rotate(0deg)
    }

    to {
      transform: rotate(359deg)
    }
  }

  .vue-btn {
    -moz-appearance: none;
    -webkit-appearance: none;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
    -webkit-box-shadow: none;
    box-shadow: none;
    display: -webkit-inline-box;
    display: -ms-inline-flexbox;
    display: inline-flex;
    -webkit-box-pack: start;
    -ms-flex-pack: start;
    justify-content: flex-start;
    position: relative;
    vertical-align: top;
    -webkit-touch-callout: none;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    cursor: pointer;
    -webkit-box-pack: center;
    -ms-flex-pack: center;
    justify-content: center;
    text-align: center;
    white-space: nowrap;
    transition: .3s all ease;
  }
  .vue-btn.hideText > span {
    opacity: 0;
  }
  .vue-btn > div {
    border-radius: 50%;
  }

  button.vue-btn-loader-error:not(.is-loading) {
    width: 48px;
    background-color: #F44336;
    color: #fff;
  }

  button.vue-btn-loader-success:not(.is-loading) {
    width: 48px;
    background-color: #8BC34A;
    color: #fff;
  }

  button.vue-btn:disabled {
    cursor: not-allowed;
  }
  /**
          Spinner Icon
      **/
  .spinner {
    position: absolute;
    top: 7px;
    left: calc((100% - 30px)/2);
    height: 30px;
    width: 30px;
    /* margin-right: 8px; */
    opacity: 1;
    filter: alpha(opacity=100);
    animation: rotation .7s infinite linear;
    border: 4px solid #dbdbdb;
    border-top-color: #fff;
    border-radius: 100%;
    transition: .3s all ease;
  }
  /**
          Check Icon
      **/
  .check {
    display: inline-block;
    width: 23px;
    height: 24px;
    border-radius: 50%;
    transform: rotate(45deg);
    color: white;
    will-change: transform;
  }

    .check:before {
      content: "";
      position: absolute;
      width: 3px;
      height: 9px;
      background-color: #fff;
      left: 11px;
      top: 6px;
    }

    .check:after {
      content: "";
      position: absolute;
      width: 3px;
      height: 3px;
      background-color: #fff;
      left: 8px;
      top: 12px;
    }
  /**
          Cross Icon
      **/
  .cross {
    display: inline-block;
    width: 17px;
    height: 16px;
    position: relative;
  }

    .cross:before,
    .cross:after {
      position: absolute;
      left: 8px;
      content: ' ';
      height: 16px;
      width: 2px;
      background-color: #fff;
    }

    .cross:before {
      transform: rotate(45deg);
      will-change: transform;
    }

    .cross:after {
      transform: rotate(-45deg);
      will-change: transform;
    }
</style>
