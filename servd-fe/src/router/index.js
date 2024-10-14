import { createRouter, createWebHistory } from 'vue-router';
import ServiceTypeList from '../components/ServiceTypeList.vue';
import ServiceTypeCreate from '../components/ServiceTypeCreate.vue';
import ServiceTypeEdit from '../components/ServiceTypeEdit.vue';
import ServiceTypeDetail from '../components/ServiceTypeDetail.vue';
import ServiceList from '@/components/ServiceList';
import ServiceCreate from '@/components/ServiceCreate';
import ServiceEdit from '@/components/ServiceEdit';

const routes = [
  {
    path: '/',
    redirect: '/create-service',
  },
  {
    path: '/service-types',
    name: 'ServiceTypeList',
    component: ServiceTypeList,
  },
  {
    path: '/create-service-type',
    name: 'ServiceTypeCreate',
    component: ServiceTypeCreate,
  },
  {
    path: '/edit-service-type/:id',
    name: 'ServiceTypeEdit',
    component: ServiceTypeEdit,
  },
  {
    path: '/service-types/:id',
    name: 'ServiceTypeDetail',
    component: ServiceTypeDetail,
  },
  {
    path: '/service-list',
    name: 'ServiceList',
    component: ServiceList
  },
  {
    path: '/create-service',
    name: 'ServiceCreate',
    component: ServiceCreate
  },
  {
    path: '/edit-service/:id',
    name: 'ServiceEdit',
    component: ServiceEdit
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;

