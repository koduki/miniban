<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <h3>Withdraw Simulator</h3>
      </div>
      <div class="container">
        <form v-on:submit.prevent="doWithdraw">
          <div v-show="message" class="alert alert-danger">{{message}}</div>
          <div class="form-group">
            <label class="col-sm-3">To</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                placeholder="e.g.) Credit Card Company"
                v-model="transaction.who"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3">Withdraw Name</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                placeholder="e.g.) Make payment"
                v-model="transaction.name"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3">Amount</label>
            <div class="col-sm-9">
              <input
                type="number"
                class="form-control"
                placeholder="$1,000"
                v-model="transaction.amount"
              />
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-3 col-sm-9">
              <button type="submit" class="btn btn-primary btn-lg btn-block">Withdraw</button>
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
      transaction: {},
      message: "",
      user_id: this.$store.state.userId
    };
  },
  methods: {
    doWithdraw() {
      const base_uri = "http://localhost:8080";
      const uri = base_uri + "/account/" + this.user_id + "/withdraw";
      this.axios
        .post(uri, this.transaction)
        .then(() => {
          this.$swal({
            icon: "success",
            text: "Withdraw Success!"
          });
          this.$router.push({ name: "Home" });
        })
        .catch(error => {
          this.message = `status: ${error.response.status}, message: ${error.response.data}`;
        });
    }
  }
};
</script>