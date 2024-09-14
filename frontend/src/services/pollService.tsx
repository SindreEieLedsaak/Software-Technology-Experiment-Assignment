

// Poll interface
export interface Poll {
    id?: number;  // Optional because the backend assigns the ID
    creatorid: string;
    question: string;
    publishedAt?: string;
    validUntil?: string;
    voteOptions: VoteOption[];
}

// VoteOption interface
export interface VoteOption {
    id?: number;  // Optional because the backend assigns the ID
    caption: string;
    presentationOrder: number;
    upvotes: number;
    downvotes: number;
}

const POLL_URL = 'http://localhost:8080/api/polls';


const pollService = {
    // Create a new poll
    createPoll: async (poll: Poll): Promise<Poll> => {
        const response = await fetch(POLL_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(poll),
        });

        if (!response.ok) {
            throw new Error("Failed to create poll");
        }

        return response.json();  // Return created poll with its ID
    },

    // Update the poll, including vote updates
    updatePoll: async (poll: Poll) => {
        const response = await fetch(`${POLL_URL}/${poll.id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(poll),
        });

        if (!response.ok) {
            throw new Error("Failed to update poll");
        }

        return response.json(); // Return updated poll
    },



    // Fetch all polls
    fetchPolls: async (): Promise<Poll[]> => {
        const response = await fetch(POLL_URL);

        if (!response.ok) {
            throw new Error('Failed to fetch polls');
        }

        return response.json();
    }
};

export default pollService;
