import React, { createContext, useContext, useState } from 'react';

const UserContext = createContext(null); // Default value set to null

export const useUser = () => {
    return useContext(UserContext);
};

export const UserProvider = ({ children }) => {
    const [user, setUser] = useState(null);

    const login = (userInfo) => {
        setUser(userInfo);
    };

    const logout = () => {
        setUser(null);
    };

    return (
        <UserContext.Provider value={{ user, login, logout }}>
            {children}
        </UserContext.Provider>
    );
};
