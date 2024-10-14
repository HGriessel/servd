<template>
  <div>
    <h1>Edit Service Type</h1>
    <form @submit.prevent="updateServiceType">
      <div>
        <label>Name:</label>
        <input v-model="name" type="text" required />
      </div>
      <div>
        <label>Location:</label>
        <input v-model="location" type="text" required />
      </div>
      <div>
        <label>Description:</label>
        <input v-model="description" type="text" required />
      </div>
      <button type="submit">Update</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      name: '',
      location: '',
      description: '',
    };
  },
  created() {
    this.getServiceType();
  },
  methods: {
    async getServiceType() {
      try {
        const response = await axios.get(`http://localhost:8080/api/serviceTypes/${this.$route.params.id}`);
        const serviceType = response.data;
        this.name = serviceType.name;
        this.location = serviceType.location;
        this.description = serviceType.description;
      } catch (error) {
        console.error(error);
      }
    },
    async updateServiceType() {
      try {
        const payload = {
          name: this.name,
          location: this.location,
          description: this.description,
        };
        await axios.put(`http://localhost:8080/api/serviceTypes/${this.$route.params.id}`, payload);
        this.$router.push('/service-types');
      } catch (error) {
        console.error(error);
      }
    },
  },
};
</script>
