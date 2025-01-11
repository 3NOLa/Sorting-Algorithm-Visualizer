import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;


class SortingWindow {
    private Timer[] timers;
    private int speed = 25;
    private final Sorts[] sorts;
    private final GuiSorts parentFrame;
    private JPanel resultsPanel;
    private JPanel upperMenu;
    private JLabel[] durations;

    public SortingWindow(Sorts[] sorts, GuiSorts parentFrame) {
        this.sorts = sorts;
        this.parentFrame = parentFrame;
        this.timers = new Timer[sorts.length];
        this.durations = new JLabel[sorts.length];
        createAndShowSortingWindow();
    }

    private void createAndShowSortingWindow() {
        resultsPanel = new JPanel();
        int panelAmount = sorts.length;
        resultsPanel.setLayout(new GridLayout((panelAmount + 1) / 2, 2, 20, 20));
        resultsPanel.setBackground(Color.BLACK);

        createUpperMenu(panelAmount);
        createSortingPanels(panelAmount);

        parentFrame.add(upperMenu, BorderLayout.NORTH);
        parentFrame.add(resultsPanel, BorderLayout.CENTER);
        parentFrame.revalidate();
        parentFrame.repaint();
    }

    private void createUpperMenu(int panelAmount) {
        upperMenu = new JPanel();
        upperMenu.setBackground(Color.BLACK);

        JButton exitButton = new JButton("Back");
        exitButton.addActionListener(e -> handleExit());
        upperMenu.add(exitButton);

        JButton speedUpButton = new JButton("1x");
        speedUpButton.addActionListener(e -> handleSpeedChange(e, panelAmount));
        upperMenu.add(speedUpButton);
    }

    private void createSortingPanels(int panelAmount) {
        for (int i = 0; i < panelAmount; i++) {
            JPanel outerPanel = new JPanel(new BorderLayout());
            outerPanel.setBackground(Color.BLACK);

            JPanel sortingPanel = createAnimatedPanel(sorts[i], i);
            outerPanel.add(sortingPanel, BorderLayout.CENTER);

            addPanelLabels(outerPanel, sorts[i].name, i);
            resultsPanel.add(outerPanel);
        }
    }

    private void addPanelLabels(JPanel outerPanel, String sortName, int index) {
        JLabel nameLabel = new JLabel(sortName);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 30));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        outerPanel.add(nameLabel, BorderLayout.NORTH);

        JLabel durationLabel = new JLabel("0.000000s");
        durationLabel.setForeground(Color.WHITE);
        try{
            Font digitalFont = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\SortsJava\\Fonts\\Digital.ttf")).deriveFont(40f);
            durationLabel.setFont(digitalFont);
        }catch(FontFormatException | IOException e)
        {
            e.printStackTrace();
            durationLabel.setFont(new Font("Arial", Font.BOLD, 24));
        }
        durationLabel.setHorizontalAlignment(SwingConstants.CENTER);  // Center the text
        outerPanel.add(durationLabel, BorderLayout.SOUTH);
        durations[index] = durationLabel;  // Store the label reference
    }

    private JPanel createAnimatedPanel(Sorts type, int index) {
        type.sort();  // Run the sort
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawSortingBars(g, type.getArrayState());
            }
        };
        
        panel.setPreferredSize(new Dimension(1000, 500));
        panel.setBackground(Color.BLACK);
    
        Timer timer = new Timer(speed, e -> {
            if (type.getArrayState() == null) {
                ((Timer) e.getSource()).stop();
            } else {
                panel.repaint();
                // Format the duration to show seconds with 6 decimal places
                long currentTime = type.getCurrenttime();
                double seconds = currentTime / 1_000_000_000.0;  // Convert nanoseconds to seconds
                String formattedTime = String.format("%.6fs", seconds);
                durations[index].setText(formattedTime);
            }
        });
        
        timers[index] = timer;
        timer.start();
        
        return panel;
    }

    private void drawSortingBars(Graphics g, int[] array) {
        if (array == null) return;
        
        int spaceBetweenBars = 1;
        int width = (g.getClipBounds().width / array.length) - spaceBetweenBars;
        int panelHeight = g.getClipBounds().height;
        
        for (int i = 0; i < array.length; i++) {
            int height = (int) ((double) array[i] / 1000000 * panelHeight);
            g.setColor(Color.BLUE);
            g.fillRect(i * (width + spaceBetweenBars), panelHeight - height, width, height);
        }
    }

    private void handleSpeedChange(ActionEvent e, int panelAmount) {
        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();
        
        switch (buttonText) {
            case "4x":
                speed = 25;
                button.setText("1x");
                break;
            case "2x":
                speed /= 2;
                button.setText("4x");
                break;
            default:
                speed /= 2;
                button.setText("2x");
                break;
        }
        
        for (int i = 0; i < panelAmount; i++) {
            if (timers[i] != null) {
                timers[i].setDelay(buttonText.equals("2x") ? 1 : speed);
            }
        }
    }

    private void handleExit() {
        // Stop all timers
        for (Timer timer : timers) {
            if (timer != null && timer.isRunning()) {
                timer.stop();
            }
        }

        // Remove panels
        parentFrame.remove(resultsPanel);
        parentFrame.remove(upperMenu);
        
        // Reset parent frame
        parentFrame.resetToStartPanel();
        
        // Clean up
        System.gc();
    }
}