package DAT250.Project.Domainclasses;

import java.time.Instant;

public class Vote {
    private Instant publishedAt;
    private VoteOption voteOption;
    private boolean vote;

    public Vote(Instant publishedAt, VoteOption voteOption, boolean vote) {
        this.publishedAt = publishedAt;
        this.voteOption = voteOption;
        this.vote = vote;

    }

    // Getters and Setters
    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public VoteOption getVoteOption() {
        return voteOption;
    }

    public void setVoteOption(VoteOption voteOption) {
        this.voteOption = voteOption;
    }

    public boolean getVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }

}
