# DAT250 Experiment Assignment 3 - Hand-in

## 1. Technical Problems Encountered

During the completion of the tutorial, I encountered several technical challenges:

- **Polling Issues**: When creating polls, the initial problem was the need to refresh the page to see new polls. This was solved by modifying the poll creation component to notify the `PollList` when a new poll was created.
  
- **Unique Keys Warning**: A recurring issue was the warning in React regarding missing or non-unique `key` props in the list of polls and vote options. This was fixed by ensuring that the `poll.id` and `index` for each vote option were used to generate a unique key for each child in the list.

- **User ID Retrieval**: Another challenge was retrieving and setting the correct user ID when creating new polls. The initial problem was that the user object was stored incorrectly in `localStorage`, and the ID extraction process was flawed. This was addressed by fixing how the user object was parsed and stored from `localStorage`.

- **Vote Handling**: Handling votes (upvotes/downvotes) initially caused some issues because the poll was not correctly updated after voting. This was solved by ensuring that the poll update was correctly propagated back to the `PollList` and that the poll state was updated properly in the frontend.

## 2. Link to Code for Experiments 1-2

You can find my code for experiments 1-2 in the following GitHub repository:

[GitHub Repository Link](https://github.com/SindreEieLedsaak/Software-Technology-Experiment-Assignment/tree/main/frontend)

### Repository Structure:

- **src/components/Poll**: Contains components for creating polls, listing polls, and handling voting.
- **src/components/User**: Contains components related to user registration and login.
- **src/services**: Contains service files to handle API requests for both polls and users.
- **App.tsx**: The main file that manages user authentication and links the `CreatePoll` and `PollList` components.

## 3. Pending Issues

Despite the progress made, there are still some pending issues:

- **Error Boundary**: Although React gives recommendations to use error boundaries for better error handling in the app, I did not manage to implement this feature to improve the app’s robustness.
  
- **Vote State Handling**: Although the voting mechanism works, further improvements could be made to handle errors gracefully if the backend fails to update the vote count (e.g., providing feedback to the user that their vote failed).
And also link the votes to the user. 
  
- **User Sessions**: The current implementation relies on `localStorage` for user management. A more robust solution would be to implement session management on the backend to handle logged-in users more securely.
