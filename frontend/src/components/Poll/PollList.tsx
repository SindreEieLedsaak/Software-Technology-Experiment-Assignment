import React, { useEffect, useState } from 'react';
import pollService, { Poll, VoteOption } from '../../services/pollService';
import './PollList.css';

const PollList: React.FC = () => {
    const [polls, setPolls] = useState<Poll[]>([]);

    useEffect(() => {
        pollService.fetchPolls()
            .then(fetchedPolls => {
                const pollsArray = Object.entries(fetchedPolls).map(([id, poll]) => ({
                    ...poll,
                    id: Number(id),  // Add the poll id to the poll object
                }));
                setPolls(pollsArray);  // Set pollsArray with the id field attached
            })
            .catch(error => {
                console.error("Error fetching polls: ", error);
            });
    }, []);

    const handleUpvote = (poll: Poll, presentationOrder: number) => {
        const updatedOptions = poll.voteOptions.map(option => {
            if (option.presentationOrder === presentationOrder) {
                return { ...option, upvotes: option.upvotes + 1 };
            }
            return option;
        });

        const updatedPoll = { ...poll, voteOptions: updatedOptions };

        pollService.updatePoll(updatedPoll)
            .then(() => {
                const updatedPolls = polls.map(p => (p.id === poll.id ? updatedPoll : p));
                setPolls(updatedPolls);
            })
            .catch(error => {
                console.error("Error upvoting option: ", error);
            });
    };

    const handleDownvote = (poll: Poll, presentationOrder: number) => {
        const updatedOptions = poll.voteOptions.map(option => {
            if (option.presentationOrder === presentationOrder) {
                return { ...option, downvotes: option.downvotes + 1 };
            }
            return option;
        });

        const updatedPoll = { ...poll, voteOptions: updatedOptions };

        pollService.updatePoll(updatedPoll)
            .then(() => {
                const updatedPolls = polls.map(p => (p.id === poll.id ? updatedPoll : p));
                setPolls(updatedPolls);
            })
            .catch(error => {
                console.error("Error downvoting option: ", error);
            });
    };

    return (
        <div className='container'>
            <h1>Polls</h1>
            {Array.isArray(polls) && polls.length > 0 ? (
                polls.map((poll) => (
                    <div key={poll.id} className="poll">  {/* Use poll.id for key */}
                        <h2 className='poll-title'>{poll.question}</h2>
                        {poll.voteOptions.map((option: VoteOption, index: number) => (
                            <div key={index} className="poll-option">
                                <p>{option.caption}</p>
                                <button
                                    className='upvote'
                                    onClick={() => handleUpvote(poll, option.presentationOrder)}>
                                    Upvote
                                </button>
                                <button
                                    className='downvote'

                                    onClick={() => handleDownvote(poll, option.presentationOrder)}>
                                    Downvote
                                </button>
                                <span className='votes'>
                                    Upvotes: {option.upvotes || 0}</span>
                                |
                                <span className='votes'>Downvotes: {option.downvotes || 0}</span>
                            </div>
                        ))}
                    </div>
                ))
            ) : (
                <p>No polls available</p>
            )}
        </div>
    );
};

export default PollList;
