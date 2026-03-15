public class Question {
    private String text;
    private String[] options;
    private int correctIndex;
    private String hint;

    public Question(String text, String[] options, int correctIndex, String hint) {
        this.text = text;
        this.options = options;
        this.correctIndex = correctIndex;
        this.hint = hint;
    }

    public String getText() { return text; }
    public String[] getOptions() { return options; }
    public int getCorrectIndex() { return correctIndex; }
    public String getHint() { return hint; }
}
