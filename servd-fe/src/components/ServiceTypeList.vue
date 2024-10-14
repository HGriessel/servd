<template>
  <div>
    <h1>Service Types</h1>
    <ul>
      <li v-for="service in serviceTypes" :key="service.id">
        <router-link :to="`/edit-service-type/${service.id}`"> {{ service.name }} - {{ service.location
          }}</router-link>_
        <button @click="deleteService(service.id)">Delete</button>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      serviceTypes: [],
    };
  },
  created() {
    this.getServiceTypes();
  },
  methods: {
    async getServiceTypes() {
      try {
        const response = await axios.get('http://localhost:8080/api/serviceTypes', {
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          }
        }); this.serviceTypes = response.data;

      } catch (error) {
        console.error(error);
      }
    },
    async deleteService(id) {
      try {
        await axios.delete(`http://localhost:8080/api/serviceTypes/${id}`);
        this.getServiceTypes();
      } catch (error) {
        console.error(error);
      }
    },
  },
};
</script>
