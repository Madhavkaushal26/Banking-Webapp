// src/services/axiosInstance.js

import axios from 'axios';

// Create an Axios instance with the base URL and default headers
const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080', // Your backend URL
    headers: {
        'Content-Type': 'application/json',
        'Authorization': `Basic ${btoa('admin:admin')}` // Basic Auth with encoded username:password
    }
});

export default axiosInstance;
