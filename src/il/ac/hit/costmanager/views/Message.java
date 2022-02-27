package il.ac.hit.costmanager.views;

/**
 * This class describes Message.
 * @author Ziv Hochman, Anzor Torikashvili.
 */
public class Message
{
    private String content;
    /**
     * Message's Constructor.
     * @param content Contains content to insert into this content field.
     */
    public Message(String content) {
        setText(content);
    }
    /**
     * returns String variable.
     * @return Content of this content field.
     */
    public String getText() {
        return this.content;
    }
    /**
     * Sets the content in this content field.
     * @param content The content of the content field.
     */
    public void setText(String content) {
        if (content != null && !content.isEmpty()){
            this.content = content;
        }
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                '}';
    }
}


















