package DAT250.Project.Domainclasses;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String username;
    private String email;
    private Set<Poll> pollsCreated;
    private Set<Vote> votes;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.pollsCreated = new HashSet<>();
        this.votes = new HashSet<>();
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Poll> getPollsCreated() {
        return pollsCreated;
    }

    public void setPollsCreated(Set<Poll> pollsCreated) {
        this.pollsCreated = pollsCreated;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }
}
