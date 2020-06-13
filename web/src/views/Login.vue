<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <h3>ようこそ、みにばん！へ</h3>
      </div>
      <div class="container">
        <form v-on:submit.prevent="doLogin">
          <div class="form-group">
            <label for="input-username" class="col-sm-3">Customer ID</label>
            <div class="col-sm-9">
              <input
                id="input-username"
                type="text"
                class="form-control"
                placeholder="customer id"
                v-model="user.userId"
              />
            </div>
          </div>
          <div class="form-group">
            <label for="input-password" class="col-sm-3">Password</label>
            <div class="col-sm-9">
              <input
                type="password"
                class="form-control"
                id="input-password"
                placeholder="password"
                v-model="user.password"
              />
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-3 col-sm-9">
              <button type="submit" class="btn btn-primary btn-lg btn-block">Sign In</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: {}
    };
  },
  methods: {
    doLogin() {
      const uri = "http://localhost:8080/login";
      this.axios.post(uri, this.user).then(response => {
        this.$store.dispatch("auth", {
          userId: response.data.userId,
          userToken: response.data.token
        });
        this.$router.push(this.$route.query.redirect);
      });
    }
  }
};
</script>