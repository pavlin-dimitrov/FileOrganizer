import java.io.File;

public class ReplaceService {

  private static ReplaceService instance;
  private static final String SUCCESS = "Find and replace completed.";
  private static final String INVALID_PATH = "Invalid directory path.";
  private static final String FAILED_TO_RENAME = "Failed to rename file: ";
  private static final String FIND_REPLACE_NULL = "Both FIND and REPLACE fields are empty!";


  public ReplaceService() {
  }

  public static ReplaceService getInstance() {
    if (instance == null) {
      instance = new ReplaceService();
    }
    return instance;
  }

  public String replaceSubstringInFileName(String folderPath, String find, String replace) {
    File folder = new File(folderPath);

    if (find.isEmpty() && replace.isEmpty()){
      return FIND_REPLACE_NULL;
    }

    if (!folder.isDirectory()) {
      return INVALID_PATH;
    }

    File[] files = folder.listFiles();

    assert files != null;
    for (File file : files) {
      String fileName = file.getName();

      if (fileName.contains(find)) {
        String newFileName = fileName.replace(find, replace);
        File newFile = new File(file.getParent(), newFileName);

        if (!file.renameTo(newFile)) {
          return FAILED_TO_RENAME + fileName;
        }
      }
    }
    return SUCCESS;
  }
}
