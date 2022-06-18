package springMVC.mvcSpring.model;

public class MessageForm {

    /*
    The field name in our home.html and the String name should always match
    i.e th:field="*{text}" == text as seen below
     */
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
