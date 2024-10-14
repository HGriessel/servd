<template>
  <div>
    <h1>Service List</h1>
    <ul>
      <li v-for="service in services" :key="service.id">
        {{ service.title }} - {{ service.startTime }} ({{ service.endTime }})
        <router-link :to="`/edit-service/${service.id}`">Edit</router-link>
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
      services: []
    };
  },
  created() {
    this.getServices();
  },
  methods: {
    async getServices() {
      try {
        const response = await axios.get('http://localhost:8080/api/services');
        this.services = response.data;
        console.log(response.data)
      } catch (error) {
        console.error(error);
      }
    },
    async deleteService(id) {
      try {
        await axios.delete(`http://localhost:8080/api/services/${id}`);
        this.getServices(); // Refresh the list after deletion
      } catch (error) {
        console.error(error);
      }
    }
  }
};
</script>
