import { createRouter, createWebHistory } from 'vue-router';
import ServiceTypeCreate from '../components/ServiceTypeCreate.vue';
import ServiceCreate from '@/components/ServiceCreate';

const routes = [
  {
    path: '/',
    redirect: '/create-service',
  },
  {
    path: '/create-service-type',
    name: 'ServiceTypeCreate',
    component: ServiceTypeCreate,
  },
  {
    path: '/create-service',
    name: 'ServiceCreate',
    component: ServiceCreate
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;

