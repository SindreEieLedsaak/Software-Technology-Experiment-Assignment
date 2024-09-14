import React, { useEffect, useState } from 'react';
import PollList from './components/Poll/PollList';
import CreatePoll from './components/Poll/CreatePoll';
import { Poll } from './services/pollService';
import { User } from './services/userService';
import UserRegistration from './components/User/UserRegistration';
import './App.css';

const App: React.FC = () => {
  const [currentUser, setCurrentUser] = useState<User | null>(null);

  // Check if user exists in localStorage when app loads
  useEffect(() => {
    const user = localStorage.getItem('currentUser');
    if (user) {
      // Retrieve the user object directly from localStorage
      const parsedUser = JSON.parse(user);
      const userId = Object.keys(parsedUser)[0];  // Extract the user ID
      const actualUser = parsedUser[userId];  // Get the actual user object
      setCurrentUser(actualUser);  // Set the actual user in state
    }
  }, []);

  const handleUserCreated = (user: User) => {
    // Store only the user object (not wrapped in an ID)
    localStorage.setItem('currentUser', JSON.stringify(user));
    setCurrentUser(user);  // Set the current user after registration
  };

  return (
    <div>
      {!currentUser ? (
        <UserRegistration onUserCreated={handleUserCreated} />
      ) : (
        <div>
          <h1 className='page-title'>Welcome, {currentUser.username}</h1>  {/* Display the username */}
          <div style={{ padding: '20px 0' }}> {/* Add padding here */}
            <CreatePoll onPollCreated={(_: Poll) => { /* Implement poll creation */ }} />
          </div>
          <PollList />
        </div>
      )}
    </div>
  );
};

export default App;
