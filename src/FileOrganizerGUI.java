import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.nio.file.Path;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FileOrganizerGUI extends JFrame implements ActionListener {

  private final FolderOrganizer folderOrganizer;
  private final JTextField folderPathTextField;
  private final JTextField findTextField;
  private final JTextField replaceTextField;
  private final JButton organizeButton;

  public FileOrganizerGUI() {
    this.folderOrganizer = FolderOrganizer.getInstance();

    JPanel mainPanel = new JPanel(new GridLayout(6, 2));
    Dimension dimension = new Dimension();
    dimension.setSize(900, 500);
    setResizable(false);
    mainPanel.setPreferredSize(dimension);

    //1. EMPTY PANEL
    mainPanel.add(new JPanel());

    //2. folderPathPanel
    JPanel folderPathPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
    folderPathPanel.setBorder(BorderFactory.createTitledBorder("Folder Path"));

    folderPathTextField = new JTextField(30);
    folderPathPanel.add(folderPathTextField);

    JButton selectFolderButton = new JButton("Select Folder");
    selectFolderButton.setPreferredSize(folderPathTextField.getPreferredSize());
    folderPathPanel.add(selectFolderButton);

    mainPanel.add(folderPathPanel);

    //1. EXTENSION CHECK BOX
    JCheckBox organizeByExtensionCheckBox = new JCheckBox("Organize by Extension");
    mainPanel.add(organizeByExtensionCheckBox);

    //2. EXTENSION LABEL
    JLabel organizeByExtensionLabel = new JLabel(
        "Organize your folder by creating Sub folders for different file extensions.");
    mainPanel.add(organizeByExtensionLabel);

    //1. findAndReplaceCheckBox
    JCheckBox findAndReplaceCheckBox = new JCheckBox("Find and Replace");
    mainPanel.add(findAndReplaceCheckBox);

    //2. findAndReplacePanel
    JPanel findAndReplacePanel = new JPanel(new GridLayout(3, 2));
    findAndReplacePanel.setBorder(BorderFactory.createTitledBorder("Find and Replace"));
    JLabel findLabel = new JLabel("Find:");
    findAndReplacePanel.add(findLabel);
    findTextField = new JTextField();
    findAndReplacePanel.add(findTextField);
    findTextField.setEnabled(false);
    JLabel replaceLabel = new JLabel("Replace:");
    findAndReplacePanel.add(replaceLabel);
    replaceTextField = new JTextField();
    findAndReplacePanel.add(replaceTextField);
    replaceTextField.setEnabled(false);
    mainPanel.add(findAndReplacePanel);

    //1. PREFIX CHECK BOX
    JCheckBox addPrefixCheckBox = new JCheckBox("Add prefix to file names");
    mainPanel.add(addPrefixCheckBox);

    //2. PREFIX PANEL
    JPanel prefixPanel = new JPanel(new GridLayout(1, 2));
    prefixPanel.setBorder(BorderFactory.createTitledBorder("Prefix"));
    JLabel prefixLabel = new JLabel("Add prefix here...");
    prefixPanel.add(prefixLabel);
    JTextField prefixTextField = new JTextField();
    prefixTextField.setEnabled(false);
    prefixPanel.add(prefixTextField);
    mainPanel.add(prefixPanel);

    //1. PREFIX CHECK BOX
    JCheckBox addSuffixCheckBox = new JCheckBox("Add suffix to file names");
    mainPanel.add(addSuffixCheckBox);

    //2. PREFIX PANEL
    JPanel suffixPanel = new JPanel(new GridLayout(1, 2));
    suffixPanel.setBorder(BorderFactory.createTitledBorder("Suffix"));
    JLabel suffixLabel = new JLabel("Add suffix here...");
    suffixPanel.add(suffixLabel);
    JTextField suffixTextField = new JTextField();
    suffixTextField.setEnabled(false);
    suffixPanel.add(suffixTextField);
    mainPanel.add(suffixPanel);

    //1. EMPTY PANEL
    mainPanel.add(new JPanel());

    //2. BUTTON
    organizeButton = new JButton("Organize");
    organizeButton.setEnabled(false);
    mainPanel.add(organizeButton);

    selectFolderButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser folderChooser = new JFileChooser();
        folderChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        int option = folderChooser.showOpenDialog(FileOrganizerGUI.this);

        if (option == JFileChooser.APPROVE_OPTION) {
          File selectedFile = folderChooser.getSelectedFile();

          if (selectedFile.isDirectory()) {
            Path path = selectedFile.toPath();
            folderPathTextField.setText(path.toString());
          } else if (selectedFile.isFile()) {
            String fileName = selectedFile.getName();
            folderPathTextField.setText(fileName);

          }

        } else {
          folderPathTextField.setText("Open command canceled");
        }
      }
    });

    findAndReplaceCheckBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (findAndReplaceCheckBox.isSelected()) {
          findTextField.setEnabled(true);
          replaceTextField.setEnabled(true);
          organizeButton.setEnabled(true);
          organizeByExtensionCheckBox.setEnabled(false);
        } else {
          findTextField.setEnabled(false);
          replaceTextField.setEnabled(false);
          organizeButton.setEnabled(false);
          organizeByExtensionCheckBox.setEnabled(true);
        }
      }
    });

    organizeByExtensionCheckBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        if (organizeByExtensionCheckBox.isSelected()) {
          organizeButton.setEnabled(true);
          findAndReplaceCheckBox.setEnabled(false);
          addPrefixCheckBox.setEnabled(false);
          addSuffixCheckBox.setEnabled(false);
        } else {
          organizeButton.setEnabled(false);
          findAndReplaceCheckBox.setEnabled(true);
          addPrefixCheckBox.setEnabled(true);
          addSuffixCheckBox.setEnabled(true);
        }
      }
    });

    addPrefixCheckBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (addPrefixCheckBox.isSelected()) {
          prefixTextField.setEnabled(true);
          organizeButton.setEnabled(true);
          organizeByExtensionCheckBox.setEnabled(false);
        } else {
          prefixTextField.setEnabled(false);
          organizeButton.setEnabled(false);
          organizeByExtensionCheckBox.setEnabled(true);
        }
      }
    });

    addSuffixCheckBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (addSuffixCheckBox.isSelected()) {
          suffixTextField.setEnabled(true);
          organizeButton.setEnabled(true);
          organizeByExtensionCheckBox.setEnabled(false);
        } else {
          suffixTextField.setEnabled(false);
          organizeButton.setEnabled(false);
          organizeByExtensionCheckBox.setEnabled(true);
        }
      }
    });

    this.add(mainPanel);
    this.setTitle("File Organizer");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
    this.setVisible(true);

    organizeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        final boolean organizeByExtension = organizeByExtensionCheckBox.isSelected();
        final boolean findAndReplace = findAndReplaceCheckBox.isSelected();
        final boolean prefixing = addPrefixCheckBox.isSelected();
        final boolean suffixing = addSuffixCheckBox.isSelected();
        // get the selected checkbox
        if (organizeByExtension) {
          organizeByExtension();

        } else if (findAndReplace && prefixing && suffixing) {
          findAndReplace();
          prefixing();
          suffixing();

        } else if (findAndReplace && prefixing) {
          findAndReplace();
          prefixing();

        } else if (findAndReplace && suffixing) {
          findAndReplace();
          suffixing();

        } else if (prefixing && suffixing) {
          prefixing();
          suffixing();

        } else if (findAndReplace) {
          findAndReplace();

        } else if (prefixing) {
          prefixing();

        } else if (suffixing) {
          suffixing();
        }
      }

      private void organizeByExtension() {
        String result = folderOrganizer.organizeFiles(folderPathTextField.getText());

        JOptionPane.showMessageDialog(FileOrganizerGUI.this, result, "Info",
            JOptionPane.INFORMATION_MESSAGE);
      }

      private void suffixing() {
        String result = SuffixService.getInstance().addSuffixToFileName(folderPathTextField.getText(),
            suffixTextField.getText());

        JOptionPane.showMessageDialog(FileOrganizerGUI.this, result, "Info",
            JOptionPane.INFORMATION_MESSAGE);
        suffixTextField.setText("");
      }

      private void prefixing() {
        String result = PrefixService.getInstance()
            .addStringInBeginningOfFileName(folderPathTextField.getText(),
                prefixTextField.getText());

        JOptionPane.showMessageDialog(FileOrganizerGUI.this, result, "Info",
            JOptionPane.INFORMATION_MESSAGE);
        prefixTextField.setText("");
      }

      private void findAndReplace() {
        // find and replace
        String folderPath = folderPathTextField.getText();
        String find = findTextField.getText();
        String replace = replaceTextField.getText();

        String result = ReplaceService.getInstance()
            .replaceSubstringInFileName(folderPath, find, replace);

        JOptionPane.showMessageDialog(FileOrganizerGUI.this, result, "Info",
            JOptionPane.INFORMATION_MESSAGE);

        findTextField.setText("");
        replaceTextField.setText("");
      }
    });
  }

  @Override
  public void actionPerformed(ActionEvent e) {
  }
}