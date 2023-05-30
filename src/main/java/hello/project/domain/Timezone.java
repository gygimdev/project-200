package hello.project.domain;

public enum Timezone {
    KST("Asia/Seoul"),
    EST("US/Eastern")
    ;

    private String code;

    private Timezone(String code) {
        this.code = code;
    }

    public String getTimeCode() {
        return code;
    }

}
