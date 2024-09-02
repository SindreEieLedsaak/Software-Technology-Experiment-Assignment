package DAT250.Project.Domainclasses;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class DomainManager {

    private final Map<UUID, User> users = new HashMap<>();
    private final Map<UUID, Poll> polls = new HashMap<>();
    private final Map<UUID, Vote> votes = new HashMap<>();
    private final Map<UUID, VoteOption> voteOptions = new HashMap<>();

    // User management
    public HashMap<UUID,User> addUser(User user) {
        UUID id = UUID.randomUUID();
        users.put(id, user);
        HashMap<UUID,User> userMap = new HashMap<>();
        userMap.put(id, user);
        return userMap;
    }

    public User getUser(UUID id) {
        return users.get(id);
    }

    public User updateUser(UUID id, User user) {
        return users.put(id, user);
    }

    public User deleteUser(UUID id) {
        return users.remove(id);
    }

    // Poll management
    public HashMap<UUID, Poll> addPoll(Poll poll) {
        UUID id = UUID.randomUUID();
        polls.put(id, poll);
        HashMap<UUID,Poll> map = new HashMap<>();
        map.put(id, poll);
        return map;
    }

    public Poll getPoll(UUID id) {
        return polls.get(id);
    }

    public Poll updatePoll(UUID id, Poll poll) {
        return polls.put(id, poll);
    }

    public Poll deletePoll(UUID id) {
        return polls.remove(id);
    }

    // Vote management
    public Vote addVote(Vote vote) {
        UUID id = UUID.randomUUID();
        votes.put(id, vote);
        return vote;
    }

    public Vote getVote(UUID id) {
        return votes.get(id);
    }

    public Vote updateVote(UUID id, Vote vote) {
        return votes.put(id, vote);
    }

    public Vote deleteVote(UUID id) {
        return votes.remove(id);
    }

    // VoteOption management
    public VoteOption addVoteOption(VoteOption voteOption) {
        UUID id = UUID.randomUUID();
        voteOptions.put(id, voteOption);
        return voteOption;
    }

    public VoteOption getVoteOption(UUID id) {
        return voteOptions.get(id);
    }

    public VoteOption updateVoteOption(UUID id, VoteOption voteOption) {
        return voteOptions.put(id, voteOption);
    }

    public VoteOption deleteVoteOption(UUID id) {
        return voteOptions.remove(id);
    }
}
