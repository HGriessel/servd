<template>
  <div class="max-w-7xl mx-auto p-8 bg-gray-00">
    <h1 class="text-4xl font-bold text-gray-800 mb-8">Church Services</h1>
    <div class="bg-blue-50 shadow overflow-hidden sm:rounded-lg mb-8 p-6">
      <h2 class="text-2xl font-semibold text-gray-700 mb-4">Create or Update Service</h2>
      <form class="space-y-6" @submit.prevent="saveService">
        <div class="grid grid-cols-1 gap-y-6 sm:grid-cols-2 sm:gap-x-8">

          <div>
            <label for="title" class="block text-sm font-medium text-gray-700">Service Title</label>
            <input type="text" v-model="currentService.title" id="title"
              class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
              required />
          </div>

          <div>
            <label for="capacity" class="block text-sm font-medium text-gray-700">Capacity</label>
            <input type="number" v-model="currentService.capacity" min="1" id="capacity"
              class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
              required />
          </div>
        </div>

        <div class="grid grid-cols-1 gap-y-6 sm:grid-cols-2 sm:gap-x-8">
          <div>
            <label for="startTime" class="block text-sm font-medium text-gray-700">Start Time</label>
            <input type="datetime-local" v-model="currentService.startTime" id="startTime"
              class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
              required />
          </div>

          <div>
            <label for="endTime" class="block text-sm font-medium text-gray-700">End Time</label>
            <input type="datetime-local" v-model="currentService.endTime" id="endTime"
              class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
              required />
          </div>
        </div>

        <div class="grid grid-cols-1 gap-y-6 sm:grid-cols-2 sm:gap-x-8">

          <div>
            <label for="location" class="block text-sm font-medium text-gray-700">Service location</label>
            <input type="text" v-model="currentService.location" id="location"
              class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
              required />
          </div>

          <div>
            <label for="serviceType" class="block text-sm font-medium text-gray-700">Service Type</label>
            <select v-model="currentService.serviceType" id="serviceType"
              class="mt-1 block w-full border-gray-300 rounded-md shadow-sm bg-white focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
              required>
              <option v-for="type in serviceTypes" :key="type.id" :value="type.id">
                {{ type.name }}
              </option>
            </select>
          </div>
        </div>

        <div class="flex justify-end">
          <router-link to="/create-service-type"
            class="mx-2 px-4 py-2 bg-green-500 text-white font-bold rounded hover:bg-green-600">
            Create Service Type
          </router-link>

          <button type="submit" class="px-6 py-2 bg-indigo-600 text-white rounded-md font-semibold hover:bg-indigo-700">
            Save Service
          </button>
        </div>
      </form>
    </div>

    <div class="bg-blue-50 shadow overflow-hidden sm:rounded-lg p-6">
      <h2 class="text-2xl font-bold text-gray-800 mb-4">Services List</h2>

      <table class="min-w-full divide-y divide-gray-200">
        <thead class="bg-gray-50">
          <tr>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Title</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Start Time</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">End Time</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Capacity</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Location</th>
            <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Service Type</th>
            <th class="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase tracking-wider">Actions</th>
          </tr>
        </thead>
        <tbody class="bg-white divide-y divide-gray-200">
          <tr v-for="service in services" :key="service.id">
            <td class="px-6 py-4 whitespace-nowrap">{{ service.title }}</td>
            <td class="px-6 py-4 whitespace-nowrap">{{ formatDateTime(service.startTime) }}</td>
            <td class="px-6 py-4 whitespace-nowrap">{{ formatDateTime(service.endTime) }}</td>
            <td class="px-6 py-4 whitespace-nowrap">{{ service.capacity }}</td>
            <td class="px-6 py-4 whitespace-nowrap">{{ service.location }}</td>
            <td class="px-6 py-4 whitespace-nowrap">{{ getServiceTypeName(service.serviceType.id) }}</td>
            <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
              <button class="text-indigo-600 hover:text-indigo-900" @click="editService(service)">
                Edit
              </button>
              <button class="ml-4 text-red-600 hover:text-red-900" @click="deleteService(service.id)">
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
      services: [],
      currentService: {
        id: null,
        title: '',
        startTime: '',
        endTime: '',
        capacity: 1,
        serviceType: null
      }
    };
  },
  created() {
    this.getServiceTypes();
    this.getServices();
  },
  methods: {
    async getServiceTypes() {
      try {
        const response = await axios.get('http://localhost:8080/api/serviceTypes', {
          headers: {
            "Accept": "application/json"
          },
        });
        this.serviceTypes = response.data;
      } catch (error) {
        console.error('Failed to fetch service types:', error);
      }
    },
    async getServices() {
      try {
        const response = await axios.get('http://localhost:8080/api/services', {
          headers: {

            "Content-Type": "application/json",
            "Accept": "application/json",
          }

        });
        this.services = response.data;
      } catch (error) {
        console.error('Failed to fetch services:', error);
      }
    },
    getServiceTypeName(serviceTypeid) {
      const serviceType = this.serviceTypes.find(type => type.id === serviceTypeid);
      return serviceType ? serviceType.name : 'Unknown';
    },
    async saveService() {
      try {
        if (this.currentService.id) {

          await axios.put(`http://localhost:8080/api/services/${this.currentService.id}`, this.currentService);
        } else {

          await axios.post('http://localhost:8080/api/services', this.currentService);
        }

        this.resetServiceForm();
        this.getServices();
      } catch (error) {
        console.error('Failed to save service:', error);
      }
    },
    async deleteService(id) {
      try {
        await axios.delete(`http://localhost:8080/api/services/${id}`);
        this.getServices();
      } catch (error) {
        console.error('Failed to delete service:', error);
      }
    },
    editService(service) {
      this.currentService = {
        id: service.id,
        title: service.title,
        startTime: service.startTime,
        endTime: service.endTime,
        capacity: service.capacity,
        serviceType: service.serviceType.id
      };
    },
    resetServiceForm() {
      this.currentService = {
        id: null,
        title: '',
        startTime: '',
        endTime: '',
        capacity: 1,
        serviceType: null
      };
    },
    formatDateTime(dateTime) {
      return new Date(dateTime).toLocaleString();
    }
  }
};
</script>
