import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FolderOrganizer {

  private static FolderOrganizer instance;
  private static final String PATH_NOT_DIRECTORY = "Error: Provided path is not a directory.";
  private static final String UNABLE_FILE_ACCESS = "Error: Unable to access files in the directory.";


  public FolderOrganizer() {
  }

  public static FolderOrganizer getInstance(){
    if (instance == null) {
      instance = new FolderOrganizer();
    }
    return instance;
  }

  public String organizeFiles(String sourceDirectory) {
    File folder = new File(sourceDirectory);
    if (!folder.isDirectory()) {
      return PATH_NOT_DIRECTORY;
    }

    File[] listOfFiles = folder.listFiles();
    if (listOfFiles == null) {
      return UNABLE_FILE_ACCESS;
    }

    int movedCount = 0;
    int failedCount = 0;

    for (File file : listOfFiles) {
      if (file.isFile()) {
        String fileExtension = getFileExtension(file);
        boolean result = moveFileToFolder(file, sourceDirectory, fileExtension);
        if (result) {
          movedCount++;
        } else {
          failedCount++;
        }
      }
    }

    StringBuilder resultMessage = new StringBuilder();

    if (movedCount > 0) {
      resultMessage.append("Moved ").append(movedCount).append(" files");
    }

    if (failedCount > 0) {
      if (resultMessage.length() > 0) {
        resultMessage.append(". ");
      }
      resultMessage.append("Failed to move ").append(failedCount).append(" files");
    }

    if (resultMessage.length() == 0) {
      resultMessage.append("No files moved");
    }

    resultMessage.append(".");

    return resultMessage.toString();
  }

  private String getFileExtension(File file) {
    String fileName = file.getName();
    int lastIndexOfDot = fileName.lastIndexOf('.');
    if (lastIndexOfDot > 0) {
      return fileName.substring(lastIndexOfDot + 1);
    }
    return "";
  }

  private boolean moveFileToFolder(File file, String sourceDirectory, String fileExtension) {
    String destinationDirectory = sourceDirectory + File.separator + fileExtension;
    Path destinationPath = Paths.get(destinationDirectory);

    if (!Files.exists(destinationPath)) {
      try {
        Files.createDirectory(destinationPath);
      } catch (IOException e) {
        e.printStackTrace();
        return false;
      }
    }

    Path sourcePath = file.toPath();
    Path targetPath = destinationPath.resolve(file.getName());

    try {
      Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

}
