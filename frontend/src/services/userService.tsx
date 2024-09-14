
export interface User {
    id?: number;
    username: string;
    email: string;
}

const BASE_URL = 'http://localhost:8080/api/users';

const userService = {
    // Create a new user
    createUser: async (user: User): Promise<User> => {
        const response = await fetch(BASE_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(user),
        });

        if (!response.ok) {
            throw new Error('Failed to create user');
        }

        return response.json();  // Return created user with its ID
    },

    // Fetch all users
    fetchUsers: async (): Promise<User[]> => {
        const response = await fetch(BASE_URL);

        if (!response.ok) {
            throw new Error('Failed to fetch users');
        }

        return response.json();
    },

    // Update a user
    updateUser: async (id: number, user: User): Promise<User> => {
        const response = await fetch(`${BASE_URL}/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(user),
        });

        if (!response.ok) {
            throw new Error('Failed to update user');
        }

        return response.json();
    },

    // Delete a user
    deleteUser: async (id: number): Promise<void> => {
        const response = await fetch(`${BASE_URL}/${id}`, {
            method: "DELETE",
        });

        if (!response.ok) {
            throw new Error('Failed to delete user');
        }
    },
};

export default userService;
