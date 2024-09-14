package DAT250.Project.Domainclasses;

public class VoteOption {
    private String caption;
    private int presentationOrder;
    private int upvotes;
    private int downvotes;

    public VoteOption(String caption, int presentationOrder, int upvotes, int downvotes) {
        this.caption = caption;
        this.presentationOrder = presentationOrder;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
    }

    // Getters and Setters
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getPresentationOrder() {
        return presentationOrder;
    }

    public void setPresentationOrder(int presentationOrder) {
        this.presentationOrder = presentationOrder;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }
}
