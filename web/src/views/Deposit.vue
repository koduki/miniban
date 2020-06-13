<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <h3>Deposit Simulator</h3>
      </div>
      <div class="container">
        <form v-on:submit.prevent="doDeposit">
          <div v-show="message" class="alert alert-danger">{{message}}</div>
          <div class="form-group">
            <label class="col-sm-3">From</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                placeholder="e.g.) Your company"
                v-model="transaction.who"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3">Deposit Name</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                placeholder="e.g.) Salaryt from your company"
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
              <button type="submit" class="btn btn-primary btn-lg btn-block">Deposit</button>
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
    doDeposit() {
      const base_uri = "http://localhost:8080";
      const uri = base_uri + "/account/" + this.user_id + "/deposit";
      this.axios
        .post(uri, this.transaction)
        .then(() => {
          this.$swal({
            icon: "success",
            text: "Deposit Success!"
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