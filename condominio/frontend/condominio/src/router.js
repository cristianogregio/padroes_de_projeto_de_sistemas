import Vue from 'vue';
import Router from 'vue-router';
import Home from './views/Home.vue';
import Login from './views/Login.vue';
import Cadastro from './views/Cadastro.vue';

Vue.use(Router);

export const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/home',
      component: Home
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/cadastro',
      component: Cadastro
    },

    {
      path: '/sindico',
      name: 'sindico',
      // lazy-loaded
      component: () => import('./views/sindico/Sindico.vue')
    },
    {
      path: '/profile',
      name: 'profile',
      // lazy-loaded
      component: () => import('./views/Profile.vue')
    },
    {
      path: '/morador',
      name: 'morador',
      // lazy-loaded
      component: () => import('./views/morador/Morador.vue')
    }
  ]
});
