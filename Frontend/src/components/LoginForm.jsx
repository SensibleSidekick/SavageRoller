import React, {useState} from 'react';
import {Link, useNavigate} from 'react-router';
import api from '../service/api';
import {useAuth} from '../context/AuthContext';

function LoginForm() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [errors, setErrors] = useState({
        email:"",
        password: "",
        general: ""
    });
    const navigate = useNavigate();
    const {login} = useAuth();
    const {authService } = api;

    const handleLogin = async (e) => {
        e.preventDefault();

        setErrors({
            email: "",
            password: "",
            general: ""
        });

        let isValid = true;
        
        if(!email) {
            setErrors((prev) => ({...prev, email: "Email is required"}));
        } else if (!/\S+@\S+\.\S+/.test(email)) {
            setErrors((prev) => ({
                ...prev,
                email: "Please enter a valid email address."
            }));
            isValid = false;
        }

        if(!password) {
            setErrors((prev) => ({
                ...prev,
                password: "Password is required"
            }));
            isValid = false;
        }

        if(!isValid) {
            return;
        }

        try{
            const response = await authService.login({email, password});
            login(response);
            console.log("Login successful", response);

            navigate('/profile');
        } catch (error) {
            console.log('Login error:', error);

            if (error.error === "invalid_credentials") {
                setErrors((prev) => ({
                    ...prev,
                    general: "Invalid email or password combination",
                }));
            } else if (error.error === "validation_error") {
                const errorMessages = Array.isArray(error.message)
                ? error.message
                : [error.message];
                errorMessages.forEach((msg) => {
                    if(msg.toLowerCase().includes("email")) {
                        setErrors((prev) => ({
                            ...prev,
                            email: msg,
                        }));
                    } else if (msg.toLowerCase().includes("password")) {
                        setErrors((prev) => ({
                            ...prev,
                            password: msg,
                        }));
                    } else {
                        setErrors((prev) => ({
                            ...prev, 
                            general: msg,
                        }));
                    }
                });
            } else {
                setErrors((prev) => ({ ...prev, general: error.message || "An unexpected error occurred. Please try again." }));
            
            }
        }
    };

    return (
        <>
            <form onSubmit={handleLogin}>
                <label>Email: 
                    <input type="text" value={email} onChange={(e) => setEmail(e.target.value)} className = {`${errors.email ? 'border-red-500' : 'border-gray-300'}`}/>
                </label>
                {errors.email && (<p className= 'text-red-600 text-sm mt-1'>{errors.email}</p>)}

                <label>Password: 
                    <input type="text" value={password} onChange={(e) => setPassword(e.target.value)} className = {`${errors.password ? 'border-red-500' : 'border-gray-300'}`}/>
                </label>
                {errors.password && (<p className= 'text-red-600 text-sm mt-1'>{errors.password}</p>)}

                <button type="submit">Login</button>
                <div>
                    <p>
                        Don't have an account?
                        <Link 
                        to='/register'
                        >
                         Register
                        </Link>
                    </p>
                </div>
            </form>
        </>
    );
}

export default LoginForm;