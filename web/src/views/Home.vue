<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <h3>{{ user_id }}'s Accounts</h3>
      </div>
      <div class="container">
        <strong>Available Balance</strong>
        <div class="h4">{{ balance | toMoney }}</div>

        <table class="table">
          <tr>
            <th>Date</th>
            <th>Name</th>
            <th>Method</th>
            <th>Amount</th>
          </tr>
          <tr v-for="item in summary" :key="item._id">
            <td>{{ item.usedDateTime | moment }}</td>
            <td>{{ item.name }}</td>
            <td>{{ item.method }}</td>
            <td>{{ item.amount | toMoney}}</td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import moment from "moment";
export default {
  filters: {
    toMoney: function(money) {
      return "$" + money.toLocaleString();
    },
    moment(date) {
      return moment(date).format("YYYY/MM/DD HH:mm");
    }
  },
  data() {
    return {
      summary: [],
      balance: 0,
      user_id: this.$store.state.userId
    };
  },
  created: function() {
    this.fetchSummary();
  },
  methods: {
    fetchSummary() {
      const base_uri = "http://localhost:8080";
      const uri = base_uri + "/account/" + this.user_id + "/summary";
      this.axios.get(uri).then(response => {
        this.summary = response.data;
        this.fetchBalance();
      });
    },
    fetchBalance() {
      const base_uri = "http://localhost:8080";
      const uri = base_uri + "/account/" + this.user_id + "/balance";
      this.axios.get(uri).then(response => {
        this.balance = response.data.balance;
      });
    }
  }
};
</script>