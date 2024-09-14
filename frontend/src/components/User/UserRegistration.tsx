import React, { useState } from 'react';
import userService, { User } from '../../services/userService';

interface UserRegistrationProps {
    onUserCreated: (user: User) => void;
}

const UserRegistration: React.FC<UserRegistrationProps> = ({ onUserCreated }) => {
    const [username, setUsername] = useState<string>('');
    const [email, setEmail] = useState<string>('');
    const [errorMessage, setErrorMessage] = useState<string | null>(null);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setErrorMessage(null);

        const newUser: User = { username, email };

        try {
            const createdUser = await userService.createUser(newUser);
            setUsername('');  // Reset form
            setEmail('');

            // Save user to localStorage
            localStorage.setItem('currentUser', JSON.stringify(createdUser));

            onUserCreated(createdUser);  // Pass the created user to the parent component
        } catch (error) {
            setErrorMessage('Failed to create user');
        }
    };

    return (
        <div>
            <h2>Create User</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Username:</label>
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Email:</label>
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">Register</button>
                {errorMessage && <p style={{ color: 'red' }}>{errorMessage}</p>}
            </form>
        </div>
    );
};

export default UserRegistration;
