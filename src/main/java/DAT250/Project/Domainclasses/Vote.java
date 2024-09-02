package DAT250.Project.Domainclasses;

import java.time.Instant;

public class Vote {
    private Instant publishedAt;
    private VoteOption voteOption;

    public Vote(Instant publishedAt, VoteOption voteOption) {
        this.publishedAt = publishedAt;
        this.voteOption = voteOption;
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
}
