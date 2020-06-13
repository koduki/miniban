<template>
  <div id="app" class="container">
    <nav class="navbar navbar-expand-sm bg-light">
      <ul class="navbar-nav">
        <li class="nav-item">
          <router-link :to="{ name: 'Home' }" class="nav-link">Home</router-link>
        </li>
        <li class="nav-item">
          <router-link :to="{ name: 'Deposit' }" class="nav-link">Deposit</router-link>
        </li>
        <li class="nav-item">
          <router-link :to="{ name: 'Withdraw' }" class="nav-link">Withdraw</router-link>
        </li>
      </ul>
    </nav>
    <transition name="fade">
      <div class="gap">
        <router-view></router-view>
      </div>
    </transition>
  </div>
</template>

<script>
export default {};
</script>

<style>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter,
.fade-leave-active {
  opacity: 0;
}
.gap {
  margin-top: 50px;
}
</style>