package DAT250.Project.Domainclasses;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class Poll {
    private int userid;
    private String question;
    private Instant publishedAt;
    private Instant validUntil;
    private Set<VoteOption> voteOptions;

    public Poll(String question, Instant publishedAt, Instant validUntil) {
        this.question = question;
        this.publishedAt = publishedAt;
        this.validUntil = validUntil;
        this.voteOptions = new HashSet<>();

    }

    // Getters and Setters
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Instant getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Instant validUntil) {
        this.validUntil = validUntil;
    }

    public Set<VoteOption> getVoteOptions() {
        return voteOptions;
    }

    public void setVoteOptions(Set<VoteOption> voteOptions) {
        this.voteOptions = voteOptions;
    }

    public int getUserid() {
        return userid;
    }
}
