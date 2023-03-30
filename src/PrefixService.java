import java.io.File;

public class PrefixService {

  private static PrefixService instance;
  private static final String EMPTY_SUBSTRING = "The field is empty. Add some text!";
  private static final String SUCCESS = "All files were renamed successfully.";
  private static final String FAILED_TO_RENAME = "Failed to rename files!";

  public PrefixService() {
  }

  public static PrefixService getInstance(){
    if (instance == null) {
      instance = new PrefixService();
    }
    return instance;
  }


  public String addStringInBeginningOfFileName(String fileAbsolutePath, String prefix) {
    File directory = new File(fileAbsolutePath);

    if (prefix.isEmpty()){
      return EMPTY_SUBSTRING;
    }

    File[] files = directory.listFiles();

      assert files != null;

      for (File file : files) {
        StringBuilder sb = new StringBuilder();
        String fileName = file.getName();
        sb.append(fileName);
        sb.insert(0, prefix);
        File newFile = new File(file.getParent(), sb.toString());

        if (!file.renameTo(newFile)) {
          return FAILED_TO_RENAME + fileName;
        }
      }

    return SUCCESS;
  }
}
