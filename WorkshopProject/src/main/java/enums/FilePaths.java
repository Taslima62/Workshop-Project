package enums;

public enum FilePaths {
    CONFIG_FILE("config");
    String filePath;
    public String getPath(){
        return this.filePath;
    }
    FilePaths(String filePath) {
        this.filePath = filePath;
    }
}
