import React, {useState} from 'react';
import {Link, useNavigate} from 'react-router';
import api from '../service/api'


function RegistrationForm() {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const navigate = useNavigate();
    const {authService} = api;
    const [errors, setErrors] = useState({});

    const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()_+={}\[\]:;"'<>,.?/\\|`~]).{8,16}$/;
    const validatePassword = (password) =>{
        return (password.length >=8 && password.length <= 16);
    }

    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    const validateForm = () => {
        const newErrors = {};

        if (!username || username.length <3 || username.length > 30){
            newErrors.username = "Username must be between 3 and 30 characters";
        }
        if (!password.match(passwordRegex) || validatePassword(password)) {
            newErrors.password = "Password must be between 8 and 16 characters, and include at least one capital and lower case letter, one number, and one special character."
        }
        if(confirmPassword !== password) {
            newErrors.confirmPassword = "Passwords do not match";
        }
        if(!email.match(emailPattern)) {
            newErrors.email = "Please enter a valid email address";
        }

        setErrors(newErrors);
        if(Object.keys(newErrors).length >0){
            setValidationMessage('Some fields have been filled out incorrectly. Please review your information.')
        } else {
            setValidationMessage('');
        }
        return Object.keys(newErrors).length === 0;
    };

    const handleSignup = async (e) => {
        e.preventDefault();

        if (validateForm()) {
            try{
                const userData = {
                    username,
                    email,
                    password,
                    confirmPassword
                };
                const response = await authService.register(userData);
                console.log("Registration successful:", response);
                setValidationMessage('Registration successful!');

                setUsername('');
                setEmail('');
                setPassword('');
                setConfirmPassword('');

                setTimeout(() => {
                    navigate('/login');
                }, 2000);
            } catch (error) {
                if (error.error === 'email_registered') {
                    setErrors( prev => ({
                        ...prev,
                        email: 'This email is already registered'
                    }));
                } else if (error.error === 'username_taken') {
                    setErrors(prev => ({
                        ...prev,
                        username: 'This username is already taken'
                    }));
                } else {
                    setValidationMessage(error.message || 'Registration failed. Please try again.');
                }
            }
        }
    };

    return(
        <>
        <form onSubmit={handleSignup}>
            <h1>Register</h1>
            {setValidationMessage && <div>{setValidationMessage}</div>}

            <label>Username
                <input type="text" value={username} onChange={(e) =>setUsername(e.target.value)} required/>
            </label>
            {errors.username && <span className= 'text-red-500 text-sm'>{errors.username}</span>}
            <label>Email
                <input type="text" value={email} onChange={(e) =>setEmail(e.target.value)}>
                </input>
            </label>
            {errors.email && <span className= 'text-red-500 text-sm'>{errors.email}</span>}
            <label>Password
                <input type="text" value={password} onChange={(e) =>setPassword(e.target.value)}>
                </input>
            </label>
            {errors.password && <span className= 'text-red-500 text-sm'>{errors.password}</span>}
            <label>Confirm Password
                <input type="text" value={confirmPassword} onChange={(e) =>setConfirmPassword(e.target.value)}>
                </input>
            </label>
            {errors.confirmPassword && <span className= 'text-red-500 text-sm'>{errors.confirmPassword}</span>}

            <button type="submit">Submit</button>
            <Link to='/'>
                Cancel
            </Link>

            <div>
                <p>Already have an account?<Link to= '/login'>Login</Link></p>
            </div>
        </form>
        </>
    )


};

export default RegistrationForm;