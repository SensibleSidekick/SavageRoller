import {createContext, useContext, useState, useEffect } from 'react';

const AuthContext = createContext();

export const AuthProvider = ({children}) => {
    const [user, setUser] = useState(null);

    useEffect(() => {
        const storedUser = JSON.parse(localStorage.getItem("user"));
        if (storedUser && storedUser.userID) {
            console.log("User loaded from local storage", storedUser);
            setUser(storedUser);
        } else {
            console.warn("No user found in localstorage");
        }
    }, []);

    const login = (userData) => {
        if (!userData || !userData.userId) {
            console.error("Invalid login response:", userData);
            return;
        }
        localStorage.setItem("user", JSON.stringify(userData));
        setUser(userData);
    };

    const logout = () => {
        localStorage.removeItem("user");
        setUser(null);
    };

    return (
        <AuthContext.Provider value={{user, login, logout}}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => {
    return useContext(AuthContext);
}