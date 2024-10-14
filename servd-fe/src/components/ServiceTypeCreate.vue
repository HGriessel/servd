<template>
  <div class="max-w-7xl mx-auto p-6 bg-gray-50">

    <div class="bg-blue-50 shadow overflow-hidden sm:rounded-lg mb-8 p-6">
      <h1 class="text-2xl font-bold mb-6">Create or Update Service Type</h1>
      <form class="space-y-4 mb-8" @submit.prevent="saveServiceType">
        <div>
          <label class="block font-medium text-gray-600" for="name">Service Type Name:</label>
          <input type="text" v-model="currentServiceType.name"
            class="mt-1 block w-full border-gray-300 rounded-md shadow-sm" required />
        </div>
        <div>

          <label for="location" class="block font-medium text-gray-600">Location:</label>
          <input type="text" v-model="currentServiceType.location"
            class="mt-1 block w-full border-gray-300 rounded-md shadow-sm" required />
        </div>
        <div>
          <label for="description" class="block font-medium text-gray-600">Description:</label>
          <textarea v-model="currentServiceType.description"
            class="mt-1 block w-full border-gray-300 rounded-md shadow-sm" required></textarea>
        </div>
        <div class="mt-4">
          <button type="submit" class="px-4 py-2 bg-blue-500 text-white font-bold rounded hover:bg-blue-600">
            Save Service Type
          </button>
        </div>
      </form>
    </div>
    <div class="bg-blue-50 shadow overflow-hidden sm:rounded-lg p-6">

      <h2 class="text-xl font-bold mb-4">List of Service Types</h2>

      <table class="min-w-full divide-y divide-gray-200">
        <thead class="bg-gray-50">
          <tr>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Name</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Location</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Description</th>
            <th class="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase tracking-wider">Actions</th>
          </tr>
        </thead>
        <tbody class="bg-white divide-y divide-gray-200">
          <tr v-for="type in serviceTypes" :key="type.id">
            <td class="px-6 py-4 whitespace-nowrap">{{ type.name }}</td>
            <td class="px-6 py-4 whitespace-nowrap">{{ type.location }}</td>
            <td class="px-6 py-4 whitespace-nowrap">{{ type.description }}</td>
            <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
              <button class="text-indigo-600 hover:text-indigo-900" @click="editServiceType(type)">
                Edit
              </button>
              <button class="ml-4 text-red-600 hover:text-red-900" @click="deleteServiceType(type.id)">
                Delete
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      serviceTypes: [],
      currentServiceType: {
        id: null,
        name: '',
        location: '',
        description: ''
      }
    };
  },
  created() {
    this.getServiceTypes();
  },
  methods: {
    async getServiceTypes() {
      try {
        const response = await axios.get('http://localhost:8080/api/serviceTypes');
        this.serviceTypes = response.data;
      } catch (error) {
        console.error('Failed to fetch service types:', error);
      }
    },
    async saveServiceType() {
      try {
        if (this.currentServiceType.id) {
          await axios.put(`http://localhost:8080/api/serviceTypes/${this.currentServiceType.id}`, this.currentServiceType);
        } else {
          await axios.post('http://localhost:8080/api/serviceTypes', this.currentServiceType);
        }

        // Reset the form after saving
        this.resetServiceTypeForm();
        this.getServiceTypes(); // Reload the list of service types
      } catch (error) {
        console.error('Failed to save service type:', error);
      }
    },
    async deleteServiceType(id) {
      try {
        await axios.delete(`http://localhost:8080/api/serviceTypes/${id}`);
        this.getServiceTypes();
      } catch (error) {
        console.error('Failed to delete service type:', error);
      }
    },
    editServiceType(serviceType) {
      this.currentServiceType = {
        id: serviceType.id,
        name: serviceType.name,
        location: serviceType.location,
        description: serviceType.description
      };
    },
    resetServiceTypeForm() {
      this.currentServiceType = {
        id: null,
        name: '',
        location: '',
        description: ''
      };
    }
  }
};
</script>
