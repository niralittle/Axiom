 package axiom.entity;

import java.sql.Date;

/**
 *
 * @author Nira
 */
public class Post {

    String name;
    int userID;
    Date date;
    String content;

    public Post() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int authorID) {
        this.userID = authorID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
