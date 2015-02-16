/**
 * Feel free to change/modify this if you know how,
 * but please don't distribute it without giving me credit!
 * Do the right thing!
 *
 * Creator: dankydrank
 * Modified by: TepigMC
 * Nov 22, 2014
 *
 * http://youtu.be/MEawKJm-t28
 */

package tepigmc.commandcompressor;

import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;

import javax.swing.*;
import javax.swing.border.*;

public class CommandCompressor implements KeyListener, ActionListener {
  public JFrame frame;
  private JTextArea input, output;

  public CommandCompressor() {
    try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
    catch (Exception e) { e.printStackTrace(); }

    frame = new JFrame("Command Compressor");
    input = new JTextArea();
    output = new JTextArea(1, 20);
    JPanel topPanel = new JPanel();
    JPanel middlePanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    
    JLabel info = new JLabel("Type your command block commands. Place each command on a new line.");
    JScrollPane inputScroll = new JScrollPane();
    JScrollPane outputScroll = new JScrollPane();
    JButton button = new JButton("Copy to Clipboard");

    input.addKeyListener(this);
    input.setFont(new Font("Monospaced", 0, 14));

    output.setEditable(false);
    output.setLineWrap(true);
    output.setFont(new Font("Monospaced", 0, 14));

    button.addActionListener(this);

    inputScroll = new JScrollPane(input);
    inputScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    inputScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    inputScroll.setBorder(new TitledBorder(new EmptyBorder(0, 10, 0, 0), "Input:")); //EmptyBorder(n, w, s, e)

    outputScroll = new JScrollPane(output);
    outputScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    outputScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    outputScroll.setBorder(new TitledBorder(new EmptyBorder(0, 0, 0, 10), "Output:"));

    topPanel.setLayout(new FlowLayout());
    topPanel.add(info);

    middlePanel.setLayout(new BorderLayout());
    middlePanel.add(inputScroll, BorderLayout.CENTER);
    middlePanel.add(outputScroll, BorderLayout.LINE_END);

    bottomPanel.setLayout(new FlowLayout());
    bottomPanel.add(button);

    frame.setLayout(new BorderLayout());
    frame.add(topPanel, BorderLayout.PAGE_START);
    frame.add(middlePanel, BorderLayout.CENTER);
    frame.add(bottomPanel, BorderLayout.PAGE_END);

    //frame.pack();
    frame.setSize(650, 350);
    frame.setPreferredSize(new Dimension(650, 350));
    frame.setMinimumSize(new Dimension(500, 300));
    //frame.setMaximumSize(new Dimension(800, 600));
    frame.setLocationRelativeTo(null);
    frame.setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  public void updateOutput() {
    output.setText(convert(input.getText()));
  }

  public static String convert(String commands) {
    //String[] lines = commands.replaceAll("\r+", "\n").replaceAll("\n+", "\n").replaceAll("^\n", "").replaceAll("\n$", "").split("\r\n|\r|\n");
    String[] lines = commands.replaceAll("\r+|\n+|(\r\n)+", "\n").replaceAll("(^\n)|(\n$)", "").split("\r\n|\r|\n");
    if (lines.length == 1) { return lines[0]; }
    String s = "summon MinecartCommandBlock ~ ~1 ~ {";
    String s2 = "";
    for (String line : lines) {
      if (line != null && line != "") {
        s += "Riding:{id:MinecartCommandBlock,";
        s2 += ",Command:" + line + "}";
      }
    }
    s += "Riding:{id:FallingSand,TileID:157,Time:1}" + s2 + ",Command:setblock ~ ~-1 ~ 10 7}";
    return s;
  }

  public void copy() {
    StringSelection selection = new StringSelection(output.getText());
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    clipboard.setContents(selection, selection);
  }

  public void keyPressed(KeyEvent e) {}
  public void keyReleased(KeyEvent e) {
    updateOutput();
    int key = e.getKeyCode();
    if (e.isControlDown() && key == 67 || key == 114) { // ctrl+c or F3
      copy();
    }
  }
  public void keyTyped(KeyEvent e) {}

  public void actionPerformed(ActionEvent e) {
    updateOutput();
    copy();
  }

  public static void main(String[] args) {
    @SuppressWarnings("unused")
    CommandCompressor app = new CommandCompressor();
  }
}