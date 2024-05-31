// src/lib/axiosInstance.ts
import axios from 'axios';
import Cookies from 'js-cookie';

// Create an axios instance
const instance = axios.create({
  baseURL: 'http://localhost:8081'
});

// Add a request interceptor
instance.interceptors.request.use((config) => {
  // Get the token from cookie
  const token = Cookies.get('token');

  // If the token is present, set it on the Authorization header
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }

  return config;
}, (error) => {
  // If there's an error, reject the Promise with the error
  return Promise.reject(error);
});

export default instance;
