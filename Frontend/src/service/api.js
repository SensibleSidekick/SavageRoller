import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:8080",
    headers: {
        "Content-Type": "application/json",
    },
    withCredentials: true,
});

const authService = {

    login: async (credentials) => {
        try {
            const response = await api.post('/login', credentials);
            console.log("Login response data", response.data);
            localStorage.setItem('user', JSON.stringify(response.data));
            return response.data;
        } catch (error) {
            if (error.response) {
                throw error.response.data;
            }
            throw { message: "Network error occurred"};
        }
    },

    register: async (userData) => {
        try {
            const response = await api.post('/register', userData);
            return response.data;
        } catch (error) {
            if(error.response) {
                throw error.response.data;
            }
            throw { message: "Network error occurred"};
        }
    }
};

export default authService;