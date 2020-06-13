import Vue from 'vue'
import Vuex from 'vuex'

import createPersistedState from 'vuex-persistedstate'

// import auth from '@/store/modules/auth'
// import http from '@/store/modules/http'
// import message from '@/store/modules/message'

Vue.use(Vuex)

const store = new Vuex.Store({
    state: {
        userId: "",
        userToken: ""
    },
    mutations: {
        updateUser(state, user) {
            state.userId = user.userId;
            state.userToken = user.userToken;
        }
    },
    actions: {
        auth(context, user) {
            console.log(user)
            context.commit('updateUser', user);
        }
    },
    modules: {

    },
    // plugins: [createPersistedState({
    //     key: 'example',
    //     storage: window.sessionStorage
    // })]
})

export default store
