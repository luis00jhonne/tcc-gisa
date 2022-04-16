<template>
<div class="spin">
  <img class="spinning" src="../../assets/grupogaia_simbol.png" />
</div>
</template>
<script>
export default {};
</script>
<style>
@keyframes spin {
  0% {
    transform: rotate(0);
  }
  100% {
    transform: rotate(360deg);
  }
}
.spin {
  position: absolute;
  height: 100%;
  min-height: 100vh;
  width: 100%;
  top: 45px;
  left: 0;
  z-index: 10;
  color: #b9b9b9;
  background-color: rgba(255, 255, 255, .75);
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 4em;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.075);
}

.spin i {
  animation-name: spinning;
  animation-duration: 3s;
  animation-iteration-count: infinite;
  animation-timing-function: linear;
}
.spin img {
  max-width: 110px;
  margin-top: -21px;
}
</style>
