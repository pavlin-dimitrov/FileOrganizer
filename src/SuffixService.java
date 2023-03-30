import java.io.File;

public class SuffixService {

  private static SuffixService instance;
  private static final String EMPTY_SUBSTRING = "The field is empty. Add some text!";
  private static final String SUCCESS = "All files were renamed successfully.";
  private static final String FAILED_TO_RENAME = "Failed to rename files!";

  public SuffixService() {
  }

  public static SuffixService getInstance(){
    if (instance == null) {
      instance = new SuffixService();
    }
    return instance;
  }

  public String addSuffixToFileName(String fileAbsolutePath, String suffix) {
    File directory = new File(fileAbsolutePath);

    if (suffix.isEmpty()){
      return EMPTY_SUBSTRING;
    }

    File[] files = directory.listFiles();

    assert files != null;

    for (File file : files) {
      String fileName = file.getName();
      int dotIndex = fileName.lastIndexOf(".");
      if (dotIndex >= 0) {
        String newFileName = fileName.substring(0, dotIndex) + suffix + fileName.substring(dotIndex);
        File newFile = new File(file.getParent(), newFileName);
        if (!file.renameTo(newFile)) {
          return FAILED_TO_RENAME + fileName;
        }
      }
    }

    return SUCCESS;
  }
}
