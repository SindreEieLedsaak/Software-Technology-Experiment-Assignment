import React, { useState, useEffect } from 'react';
import pollService, { Poll, VoteOption } from '../../services/pollService';
import './CreatePoll.css';
interface CreatePollProps {
    onPollCreated: (poll: Poll) => void;
}

const CreatePoll: React.FC<CreatePollProps> = ({ onPollCreated }) => {
    const [question, setQuestion] = useState('');
    const [options, setOptions] = useState<string[]>(['', '']);
    const [creatorid, setCreatorId] = useState<string>("0"); // Store the creator's ID

    // Fetch the current user ID from localStorage on component mount
    useEffect(() => {
        const savedUser = localStorage.getItem('currentUser');
        if (savedUser) {
            const parsedUser = JSON.parse(savedUser);
            const userId = Object.keys(parsedUser)[0]; // Assuming user is stored as { "id": user }
            setCreatorId(userId); // Set the creatorId
        }
    }, []);

    const handleAddOption = () => {
        setOptions([...options, '']);
    };

    const handleOptionChange = (index: number, value: string) => {
        const newOptions = [...options];
        newOptions[index] = value;
        setOptions(newOptions);
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        const voteOptions: VoteOption[] = options.map((option, index) => ({
            caption: option,
            presentationOrder: index,
            upvotes: 0,
            downvotes: 0
        }));

        // Add creatorId to the poll
        const newPoll: Poll = {
            creatorid,  // Add the creator ID to the poll
            question,
            voteOptions
        };

        try {
            const createdPoll = await pollService.createPoll(newPoll);
            onPollCreated(createdPoll); // Call the callback to notify the parent component
            setQuestion('');
            setOptions(['', '']);
        } catch (error) {
            console.error("Error creating poll: ", error);
        }
    };

    return (
        <div className='container'>
            <h1>Create New Poll</h1>
            <form onSubmit={handleSubmit}>
                <div className='poll-question'>
                    <label>Question:</label>
                    <input
                        type="text"
                        value={question}
                        onChange={e => setQuestion(e.target.value)}
                        required
                    />
                </div>
                {options.map((option, index) => (
                    <div className="poll-option" key={index}>
                        <label>Option {index + 1}:</label>
                        <input
                            type="text"
                            value={option}
                            onChange={e => handleOptionChange(index, e.target.value)}
                            required
                        />
                    </div>
                ))}
                <button type="button" onClick={handleAddOption}>Add another option</button>
                <button type="submit" disabled={!creatorid}>Create Poll</button>
            </form>
        </div>
    );
};

export default CreatePoll;
