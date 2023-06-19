package hello.project.domain;

public enum TaskStatus {
    TODO("TODO"),
    PROG("PROG"),
    DONE("DONE"),
    HOLD("HOLD"),
    ;

    private String name;

    private TaskStatus(String name) {
        this.name = name;
    }

    public String getStatusName() {
        return name;
    }
}
