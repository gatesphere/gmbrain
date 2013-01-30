package gmbrain;

import java.swing.*;

public class GMBrainTool {
  private String name;
  private String tooltip;
  private JFrame contentFrame;
  
  public GMBrainTool(String name, String tooltip) {
    this.name = name;
    this.tooltip = tooltip;
    this.contentFrame = new JFrame();
  }
  
  public String getName() {
    return name;
  }
  
  public String getTooltip() {
    return tooltip;
  }
  
  public JFrame getContentFrame() {
    return contentFrame;
  }
  
  public void init() {}
  public void cleanup() {}  
}
