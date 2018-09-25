package au.com.codycodes.tpk.tamizhpallikoodam;

public class Quiz {

    private String tamilTranslation;

    private String answer;

    private String category;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    private int audioResourceId;

    private int imageResourceId;

    public Quiz(String tamilTranslation, String answer, String category, String option1, String option2, String option3, String option4, int imageResourceId, int audioResourceId) {
        this.tamilTranslation = tamilTranslation;
        this.answer = answer;
        this.category = category;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.imageResourceId = imageResourceId;
        this.audioResourceId = audioResourceId;
    }

    public String getTamilTranslation() {
        return tamilTranslation;
    }

    public String getAnswer() {
        return answer;
    }

    public String getCategory() {
        return category;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }


    public int getAudioResourceId() {
        return audioResourceId;
    }

    public String toString() {
        return "Tamil Translation: " + tamilTranslation + " Answer: " + answer + " Category: " + category + " Option 1: " + option1 + " Option 2: " + option2 + " Option 3: " + option3 + " Option 4: " + option4 + " Image Resource id: " + imageResourceId + " Audio Resource id: " + audioResourceId;
    }
}