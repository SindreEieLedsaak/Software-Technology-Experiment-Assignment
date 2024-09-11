
import React, { useState } from 'react';

// NewPoll Component
function NewPoll() {
  const [question, setQuestion] = useState('');
  const [options, setOptions] = useState(['', '']);

  const handleOptionChange = (index: number, value: string) => {
    const newOptions = [...options];
    newOptions[index] = value;
    setOptions(newOptions);
  };

  const addOption = () => {
    setOptions([...options, '']);
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // Handle poll creation logic here
    console.log('Poll Created:', { question, options });
  };

  return (
    <div>
      <h2>Create a New Poll</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Question:</label>
          <input
            type="text"
            value={question}
            onChange={(e) => setQuestion(e.target.value)}
          />
        </div>
        {options.map((option, index) => (
          <div key={index}>
            <label>Option {index + 1}:</label>
            <input
              type="text"
              value={option}
              onChange={(e) => handleOptionChange(index, e.target.value)}
            />
          </div>
        ))}
        <button type="button" onClick={addOption}>Add Option</button>
        <button type="submit">Create Poll</button>
      </form>
    </div>
  );
}

// VotePoll Component
function VotePoll() {
  const poll = {
    question: 'What is your favorite color?',
    options: ['Red', 'Blue', 'Green', 'Yellow']
  };
  const [selectedOption, setSelectedOption] = useState('');

  const handleVote = (e: React.FormEvent) => {
    e.preventDefault();
    // Handle voting logic here
    console.log('Voted for:', selectedOption);
  };

  return (
    <div>
      <h2>{poll.question}</h2>
      <form onSubmit={handleVote}>
        {poll.options.map((option, index) => (
          <div key={index}>
            <label>
              <input
                type="radio"
                value={option}
                checked={selectedOption === option}
                onChange={(e) => setSelectedOption(e.target.value)}
              />
              {option}
            </label>
          </div>
        ))}
        <button type="submit">Vote</button>
      </form>
    </div>
  );
}

// App Component
function App() {
  return (
    <div>
      <h1>Hello World</h1>
      <NewPoll />
      <VotePoll />
    </div>
  );
}

export default App;