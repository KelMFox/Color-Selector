import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Kelly on 4/12/2016.
 */
public class ColorSelector {

    private JPanel colorPanel, textPanel;   // Panel to hold radio buttons
    private final JFrame colorFrame;        // JFrame to hold panel
    private JRadioButton colorButton;       // Radio button for selecting color
    private ButtonGroup bg;                 // Button group for radio buttons
    private JTextArea hexText;              // Text area to display hex code
    private TitledBorder titleBorder;       // Custom titled border
    private Border emptyBorder;             // Border for compound border

    /**
     * Hash map for storing color names (keys) and their unique
     * hex codes (values)
     */
    private final Map<String, String> colorHashMap = new HashMap();

    /*
    * The ColorSelector constructor builds the GUI, adds entries to the hash map
    */
    public ColorSelector() {

        // Create JFrame and set title
        colorFrame = new JFrame("Color Selector");

        // Specify close operation
        colorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set window size to accomadate at most 20 radio buttons
        colorFrame.setSize(600, 300);

        // Make frame size static
        colorFrame.setResizable(false);

        // Set layout manager for the frame
        colorFrame.setLayout(new BorderLayout());

        // Set window to launch in the center of screen
        colorFrame.setLocationRelativeTo(null);

        // Make panels
        createButtons();
        textArea();

        // Add panels to the frame and set their positions
        colorFrame.add(colorPanel, BorderLayout.NORTH);
        colorFrame.add(textPanel, BorderLayout.CENTER);

        // Set frame to visible
        colorFrame.setVisible(true);
    }

    /**
     * buildColors method
     * @return Hash map with keys and values
     * (color names and their respective hex value)
     */
    public Map<String, String> buildColors() {

        colorHashMap.put("Cotton Candy", "0xFFBCD9");
        colorHashMap.put("Brick Red", "0xCB4154");
        colorHashMap.put("Purple Heart", "0x7442C8");
        colorHashMap.put("British Racing Green", "0x006600");
        colorHashMap.put("Pacific Blue", "0x1CA9C9");
        colorHashMap.put("Cornflower", "0x9ACEEB");
        colorHashMap.put("Electric Lime", "0x1DF914");
        colorHashMap.put("Mellow Yellow", "0xFDFC74");
        colorHashMap.put("Olive Drab", "0x6B8E23");

        colorHashMap.put("Sand", "0xEFCDB8");
        colorHashMap.put("Sepia", "0xA5694F");
        colorHashMap.put("Cerulean", "0x1DACD6");
        colorHashMap.put("Silver", "0xCDC5C2");
        colorHashMap.put("Mauve", "0xEF98AA");
        colorHashMap.put("Yellow Green", "0xC5E384");
        colorHashMap.put("Peach", "0xFFCFAB");
        colorHashMap.put("Mahogany", "0xCD4A4A");
        colorHashMap.put("Blue Bell", "0xADADD6");


        // Used for test case. Uncomment hash map entries
        // to test if-statement.
        // colorHashMap.put("White", "0xEDEDED");
        // colorHashMap.put("Forest Green", "0x6DAE81");
        // colorHashMap.put("Gray", "0x95918C");

        // Determine the size of the HashMap and make sure it
        // is within bounds
        if (colorHashMap.size() > 20 || colorHashMap.size() < 8) {

            System.err.println("Hash map error. The hash map does not " +
                    "contain the specified number of entries.");

            JOptionPane.showMessageDialog(colorFrame, "There must be at " +
                            "least 8 and at most 20 entries.", "Entry Problem",
                    JOptionPane.ERROR_MESSAGE);
        }
        return colorHashMap;
    }

    /**
     * createButtons method creates the radio buttons
     * for the hash map keys.
     */
    private void createButtons() {

        // Create panel for the radio buttons
        colorPanel = new JPanel();

        // Make the panel transparent
        colorPanel.setOpaque(false);

        // Set layout for the color panel
        colorPanel.setLayout(new GridLayout(5,5,5,5));

        // Create button group
        bg = new ButtonGroup();

        // Create and add the radio buttons (keys) to
        // the panel and add the buttons to the button group
        for (String b : getKeys())
        {
            colorButton = new JRadioButton(b);

            // Make buttons transparent
            colorButton.setOpaque(false);

            bg.add(colorButton);
            colorPanel.add(colorButton);

            // Add action listener to the radio buttons
            colorButton.addActionListener(new RButtonListener());
        }
    }

    /**
     * textArea method builds a text area to display the
     * hex value associated with a particular color
     */
    private void textArea() {

        // Create panel for the text area
        textPanel = new JPanel();

        // Make panel transparent
        textPanel.setOpaque(false);

        // Set font for the message text area and titled border
        Font hexFont = new Font("Arial", Font.BOLD, 14);

        // Set borders
        emptyBorder = BorderFactory.createEmptyBorder(10,220,20,220);

        // Customize appearance of the titled border
        titleBorder = BorderFactory.createTitledBorder(null, "Hex Code",
                TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, hexFont,
                Color.BLACK);

        // Change color of the titled border
        titleBorder.setBorder(new LineBorder(Color.BLACK));

        // Add a compound border around the text panel
        textPanel.setBorder(BorderFactory.createCompoundBorder(emptyBorder,
                titleBorder));

        // Text area for displaying the hex codes
        hexText = new JTextArea(3,10);

        // Add a black lineborder around the text area
        hexText.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Make the text area uneditable
        hexText.setEditable(false);

        // Set text area font
        hexText.setFont(hexFont);

        // Add text area to the panel
        textPanel.add(hexText);
    }

    /**
     * getKeys method returns the set of keys in the hash map.
     * @return The keys associated with a hex
     */
    private Set<String> getKeys() {

        Set<String> k = buildColors().keySet();

        return k;
    }

    /**
     * RButtonListener action listener class for the radio buttons.
     */
    private class RButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            // Determine the source of the action event
            colorButton = (JRadioButton)e.getSource();

            // Change background color when radio button is selected
            if (colorButton.isSelected())

                colorFrame.getContentPane().setBackground
                        (Color.decode(colorHashMap.get(colorButton.getText())));

            // Display the hex code associated with a particular color
            // in the text area and remove the 0x in front of the hex value
            hexText.setText(colorHashMap.get(colorButton.getText())
                    .substring(2));
        }
    }
    
    public static void main(String[] args) {

        ColorSelector selectGUI = new ColorSelector();
    }
}
