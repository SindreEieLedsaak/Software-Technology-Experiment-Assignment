package DAT250.Project.Domainclasses;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class DomainManager {

    private final Map<Integer, User> users = new HashMap<>();
    private final Map<Integer, Poll> polls = new HashMap<>();
    private final Map<Integer, Vote> votes = new HashMap<>();
    private final Map<Integer, VoteOption> voteOptions = new HashMap<>();

    private final AtomicInteger userId = new AtomicInteger(0);
    private final AtomicInteger pollId = new AtomicInteger(0);
    private final AtomicInteger voteId = new AtomicInteger(0);
    private final AtomicInteger voteOptionId = new AtomicInteger(0);

    // Function to generate new IDs
    public int getNextId(AtomicInteger id) {
        return id.incrementAndGet();
    }

    // User management
    public Map.Entry<Integer, User> addUser(User user) {
        Integer id = getNextId(userId);
        users.put(id, user);
        return new AbstractMap.SimpleEntry<>(id, user);
    }

    public Map.Entry<Integer, User> getUser(Integer id) {
        return new AbstractMap.SimpleEntry<>(id, users.get(id));
    }

    public Map<Integer, User> getAllUsers() {
        return users;
    }

    public Map.Entry<Integer, User> updateUser(Integer id, User user) {
        users.put(id, user);
        return new AbstractMap.SimpleEntry<>(id, user);
    }

    public Map.Entry<Integer, User> deleteUser(Integer id) {
        User deletedUser = users.remove(id);
        return new AbstractMap.SimpleEntry<>(id, deletedUser);
    }

    // Poll management
    public Map.Entry<Integer, Poll> addPoll(Poll poll) {
        Integer id = getNextId(pollId);
        polls.put(id, poll);
        return new AbstractMap.SimpleEntry<>(id, poll);
    }

    public Map.Entry<Integer, Poll> getPoll(Integer id) {
        return new AbstractMap.SimpleEntry<>(id, polls.get(id));
    }

    public Map<Integer, Poll> getAllPolls() {
        return polls;
    }

    public Map.Entry<Integer, Poll> updatePoll(Integer id, Poll poll) {
        polls.put(id, poll);
        return new AbstractMap.SimpleEntry<>(id, poll);
    }

    public Map.Entry<Integer, Poll> deletePoll(Integer id) {
        Poll deletedPoll = polls.remove(id);
        return new AbstractMap.SimpleEntry<>(id, deletedPoll);
    }

    // Vote management
    public Map.Entry<Integer, Vote> addVote(Vote vote) {
        Integer id = getNextId(voteId);
        votes.put(id, vote);
        return new AbstractMap.SimpleEntry<>(id, vote);
    }

    public Map.Entry<Integer, Vote> getVote(Integer id) {
        return new AbstractMap.SimpleEntry<>(id, votes.get(id));
    }

    public Map.Entry<Integer, Vote> updateVote(Integer id, Vote vote) {
        votes.put(id, vote);
        return new AbstractMap.SimpleEntry<>(id, vote);
    }

    public Map.Entry<Integer, Vote> deleteVote(Integer id) {
        Vote deletedVote = votes.remove(id);
        return new AbstractMap.SimpleEntry<>(id, deletedVote);
    }

    public Map<Integer, Vote> getAllVotes() {
        return votes;
    }

    // VoteOption management
    public Map.Entry<Integer, VoteOption> addVoteOption(VoteOption voteOption) {
        Integer id = getNextId(voteOptionId);
        voteOptions.put(id, voteOption);
        return new AbstractMap.SimpleEntry<>(id, voteOption);
    }

    public Map<Integer, VoteOption> getAllVoteOptions() {
        return voteOptions;
    }

    public Map.Entry<Integer, VoteOption> getVoteOption(Integer id) {
        return new AbstractMap.SimpleEntry<>(id, voteOptions.get(id));
    }

    public Map.Entry<Integer, VoteOption> updateVoteOption(Integer id, VoteOption voteOption) {
        voteOptions.put(id, voteOption);
        return new AbstractMap.SimpleEntry<>(id, voteOption);
    }

    public Map.Entry<Integer, VoteOption> upVoteOption(Integer id) {
        VoteOption voteOption = voteOptions.get(id);
        voteOption.setUpvotes(voteOption.getUpvotes() + 1);
        return new AbstractMap.SimpleEntry<>(id, voteOption);
    }

    public Map.Entry<Integer, VoteOption> downVoteOption(Integer id) {
        VoteOption voteOption = voteOptions.get(id);
        voteOption.setDownvotes(voteOption.getDownvotes() + 1);
        return new AbstractMap.SimpleEntry<>(id, voteOption);
    }

    public Map.Entry<Integer, VoteOption> deleteVoteOption(Integer id) {
        VoteOption deletedVoteOption = voteOptions.remove(id);
        return new AbstractMap.SimpleEntry<>(id, deletedVoteOption);
    }
}
