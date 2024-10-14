<template>
  <div>
    <h2>Edit Service</h2>
    <form @submit.prevent="updateService">
      <input v-model="service.title" placeholder="Service Title" required />
      <input v-model="service.location" placeholder="Location" required />
      <input v-model="service.startTime" type="datetime-local" required />
      <input v-model="service.endTime" type="datetime-local" required />
      <input v-model="service.capacity" type="number" required />
      <button type="submit">Update Service</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      service: {
        title: '',
        location: '',
        startTime: '',
        endTime: '',
        capacity: null,
        serviceTypeId: null // Include if necessary
      }
    };
  },
  created() {
    this.getService();
  },
  methods: {
    async getService() {
      const id = this.$route.params.id; // Assumes you're using Vue Router to get the ID
      try {
        const response = await axios.get(`http://localhost:8080/api/services/${id}`);
        this.service = response.data;
      } catch (error) {
        console.error(error);
      }
    },
    async updateService() {
      const id = this.$route.params.id;
      try {
        await axios.put(`http://localhost:8080/api/services/${id}`, this.service);
        alert('Service Updated Successfully');
        // Optionally, redirect or display a success message
      } catch (error) {
        console.error(error);
      }
    }
  }
};
</script>

