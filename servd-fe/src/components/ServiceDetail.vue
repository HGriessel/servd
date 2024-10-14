<template>
  <div>
    <h1>Service Type Details</h1>
    <p><strong>Name:</strong> {{ serviceType.name }}</p>
    <p><strong>Location:</strong> {{ serviceType.location }}</p>
    <p><strong>Description:</strong> {{ serviceType.description }}</p>
    <router-link :to="`/edit-service-type/${serviceType.id}`">Edit</router-link>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      serviceType: {},
    };
  },
  created() {
    this.getServiceType();
  },
  methods: {
    async getServiceType() {
      try {
        const response = await axios.get(`http://localhost:8080/api/serviceTypes/${this.$route.params.id}`);
        this.serviceType = response.data;
      } catch (error) {
        console.error(error);
      }
    },
  },
};
</script>
