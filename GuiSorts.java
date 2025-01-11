import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.util.ArrayList;

public class GuiSorts extends JFrame {

    static final int AMOUNT_SORT = 8;
    static final String[] NamesSorts = {"Bubble Sort", "Selection Sort", "Insert Sort", "Merge Sort", "Quick Sort","Heap Sort","Bucket Sort","Radix Sort"};
    JButton[] sorts_buttons;
    JPanel StartPanel, buttonsPanel, arrayPanel;
    JButton arrayActionButton;
    ArrayList<String> selectedSorts = new ArrayList<>();
    int[] unsortedArray;
    Sorts[] sorts;
    Font fontArial = new Font("Arial", Font.BOLD, 40);
    static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public GuiSorts() {
        setTitle("Sort Competition");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(screenSize);
        setLayout(new BorderLayout());
    
        // Start Panel with relative layout
        StartPanel = new JPanel(new GridBagLayout());
        StartPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
    
        // Title Label
        JLabel Title = new JLabel("Sorting Algorithm Visualization", SwingConstants.CENTER);
        Title.setFont(fontArial);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 40, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        StartPanel.add(Title, gbc);
    
        // Array Panel
        arrayPanel = new JPanel(new GridBagLayout());
        arrayPanel.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints arrayGbc = new GridBagConstraints();
    
        JLabel createArray = new JLabel("Generate Array");
        createArray.setFont(new Font("Arial", Font.BOLD, 30));
        arrayGbc.gridx = 0;
        arrayGbc.gridy = 0;
        arrayGbc.gridwidth = 2;
        arrayGbc.insets = new Insets(10, 10, 20, 10);
        arrayPanel.add(createArray, arrayGbc);
    
        JTextField arrayAmount = new JTextField(40);
        arrayAmount.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        arrayGbc.gridx = 0;
        arrayGbc.gridy = 1;
        arrayGbc.gridwidth = 2;
        arrayGbc.fill = GridBagConstraints.HORIZONTAL;
        arrayGbc.weightx = 1.0;
        arrayGbc.insets = new Insets(0, 10, 10, 5);
        arrayPanel.add(arrayAmount, arrayGbc);
    
        arrayActionButton = new JButton("Generate Array");
        arrayActionButton.setBackground(Color.BLACK);
        arrayActionButton.setForeground(Color.WHITE);
        arrayActionButton.addActionListener(new ArrayActionListener(arrayAmount));
        arrayGbc.gridx = 2;
        arrayGbc.insets = new Insets(0, 5, 10, 10);
        arrayPanel.add(arrayActionButton, arrayGbc);
    
        gbc.gridx = 0;                   // Place it in the first column
        gbc.gridy = 1;                   // Place it in the second row
        gbc.gridwidth = 1;               // Occupy only 1 column (half width of StartPanel)
        gbc.fill = GridBagConstraints.BOTH; // Allow resizing both horizontally and vertically
        gbc.weightx = 0.5;               // Give it 50% of the horizontal space
        gbc.insets = new Insets(0, 50, 20, 10); // Add padding (right padding adjusted)
        StartPanel.add(arrayPanel, gbc);

        JPanel placeholderPanel = new JPanel();
        placeholderPanel.setBackground(Color.WHITE); // Optional: Set a background color

        // Set constraints for placeholderPanel
        gbc.gridx = 1;                   // Place it in the second column
        gbc.gridy = 1;                   // Align it with arrayPanel in the second row
        gbc.gridwidth = 1;               // Occupy only 1 column
        gbc.fill = GridBagConstraints.BOTH; // Allow resizing both horizontally and vertically
        gbc.weightx = 0.5;               // Give it 50% of the horizontal space
        gbc.insets = new Insets(0, 50, 20, 50); // Add padding (left padding adjusted)
        StartPanel.add(placeholderPanel, gbc);
        
        JLabel seconedTitle = new JLabel("Choose Alogoritems");
        seconedTitle.setFont(fontArial);
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH; // Allow resizing both horizontally and vertically
        gbc.weightx = 0.5;
        gbc.gridy = 2;
        StartPanel.add(seconedTitle, gbc);

        // Sorting Buttons Panel
        buttonsPanel = new JPanel(new GridLayout(4, 2, 20, 20));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        sorts_buttons = new JButton[AMOUNT_SORT];
        for (int i = 0; i < AMOUNT_SORT; i++) {
            sorts_buttons[i] = new JButton(NamesSorts[i]);
            sorts_buttons[i].setBackground(Color.WHITE);
            sorts_buttons[i].addActionListener(new SortActionListener());
            buttonsPanel.add(sorts_buttons[i]);
        }
        gbc.gridy = 3;
        gbc.weighty = 1.0;
        gbc.gridx = 0;                   
        gbc.gridwidth = 2; 
        gbc.insets = new Insets(0, 50, 20, 50);             
        gbc.fill = GridBagConstraints.BOTH;
        StartPanel.add(buttonsPanel, gbc);
    
        // Start Button
        JButton startButton = new JButton("Start Visualization");
        startButton.setBackground(Color.WHITE);
        startButton.setForeground(Color.BLACK);
        startButton.addActionListener(e -> {
            if (unsortedArray == null) {
                JOptionPane.showMessageDialog(GuiSorts.this,
                        "Can't start: You didn't create the unsorted array");
            } else if (selectedSorts.isEmpty()) {
                JOptionPane.showMessageDialog(GuiSorts.this,
                        "Can't start: You didn't select any sorting method");
            } else {
                remove(StartPanel);
                displaySortingResults();
            }
        });
        gbc.gridy = 4;
        gbc.weighty = 0;
        gbc.insets = new Insets(0, 50, 20, 50);
        StartPanel.add(startButton, gbc);
    
        add(StartPanel, BorderLayout.CENTER);
        setVisible(true);
    }   

    public class SortActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String buttonText = button.getText();

            if (button.getBackground() == Color.blue) {
                button.setBackground(Color.WHITE);
                selectedSorts.remove(buttonText);
            } else {
                button.setBackground(Color.blue);
                selectedSorts.add(buttonText);
            }

            System.out.println("Selected sorts: " + selectedSorts);
        }
    }

    public class ArrayActionListener implements ActionListener {
        private JTextField inputField;

        public ArrayActionListener(JTextField inputField) {
            this.inputField = inputField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String input = inputField.getText();

            try {
                int arraySize = Integer.parseInt(input);
                unsortedArray = new int[arraySize];
                for (int i = 0; i < arraySize; i++) {
                    unsortedArray[i] = (int) (Math.random() * 1000000);
                }
                System.out.println("Array size submitted: " + arraySize);
                JOptionPane.showMessageDialog(GuiSorts.this,"Array size of " + arraySize + " submitted!");
            } catch (NumberFormatException  ex) {
                JOptionPane.showMessageDialog(GuiSorts.this,"Invalid number. Please enter a valid integer.");
            }
        }
    }

    private void displaySortingResults() {
        // Make a copy of selectedSorts before creating the sort array
        ArrayList<String> sortsCopy = new ArrayList<>(selectedSorts);
        createSortArray(sortsCopy);
        new SortingWindow(sorts, this);
    }

    private void createSortArray(ArrayList<String> sortsList) {
        sorts = new Sorts[sortsList.size()];
        int i = 0;
        while (!sortsList.isEmpty()) {
            String name = sortsList.remove(0);
            switch (name) {
                case "Bubble Sort":
                    sorts[i] = new bubble_sort(unsortedArray.clone());
                    break;
                case "Selection Sort":
                    sorts[i] = new selection_sort(unsortedArray.clone());
                    break;
                case "Insert Sort":
                    sorts[i] = new insert_sort(unsortedArray.clone());
                    break;
                case "Merge Sort":
                    sorts[i] = new merge_sort(unsortedArray.clone());
                    break;
                case "Quick Sort":
                    sorts[i] = new quick_sort(unsortedArray.clone());
                    break;
                case "Heap Sort":
                    sorts[i] = new HeapSort(unsortedArray.clone());
                    break;
                case "Bucket Sort":
                    sorts[i] = new bucket_sort(unsortedArray.clone());
                    break;
                case "Radix Sort":
                    sorts[i] = new radix_sort(unsortedArray.clone());
                    break;
            }
            i++;
        }
    }

    public void resetToStartPanel() {
        // Reset buttons
        for (JButton button : sorts_buttons) {
            button.setBackground(Color.WHITE);
        }
        
        // Clear selected sorts
        selectedSorts.clear();
        
        // Add back the start panel
        add(StartPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        new GuiSorts();
    }

}

