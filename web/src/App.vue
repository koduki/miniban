<template>
  <div id="app" class="container">
    <herdear id="topbar">
      <a href="/">
        <p class="title">みにばん！</p>
        <p class="subtitle">オンラインバンキングサービス</p>
      </a>
    </herdear>
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

@font-face {
  font-family: "keion-font";
  src: url("~@/assets/fonts/keifont-kana.ttf") format("truetype");
}
#topbar {
  text-decoration: none;
}
#topbar a:hover {
  text-decoration: none;
}
#topbar .title {
  color:rgb(255, 0, 15);
text-shadow: 
    rgb(255, 255, 215) 2px 0px,  black -2px 0px,
    rgb(255, 255, 215)  0px -2px, rgb(255, 255, 215)  0px 2px,
    rgb(255, 255, 215)  2px 2px , rgb(255, 255, 215)  -2px 2px,
    rgb(255, 255, 215)  2px -2px, rgb(255, 255, 215)  -2px -2px,
    rgb(255, 255, 215)  1px 2px,  rgb(255, 255, 215)  -1px 2px,
    rgb(255, 255, 215)  1px -2px, rgb(255, 255, 215)  -1px -2px,
    rgb(255, 255, 215)  2px 1px,  rgb(255, 255, 215)  -2px 1px,
    rgb(255, 255, 215)  2px -1px,  black  -2px -1px;
  font-size: 64px;
  font-family: "keion-font";
  margin-bottom: 0px;
  text-decoration: none;
}
#topbar .subtitle {
  color:black;
  font-weight: 900;
  text-decoration: none;
}
</style>