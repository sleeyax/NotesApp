package be.thomasmore.noteservice.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Note {
    @Id
    // NOTE: mongodb uses an ObjectId, which is a hexadecimal string
    // More info here: https://docs.mongodb.com/manual/reference/method/ObjectId/
    private String id;
    private long userId;
    private String note;
    private Date createdAt;

    public Note() {}

    public Note(long userId, String note) {
        this.userId = userId;
        this.note = note;
        this.createdAt = new Date();
    }

    @Override
    public String toString() {
        return String.format("%s[id=%s, userId=%s, note=%s]", getClass().toString(), this.id, this.userId, this.note);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
