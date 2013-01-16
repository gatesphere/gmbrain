/* NodeWebManager - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
package gmbrain;

public class NodeWebManager {
  private NodeWeb primaryWeb;
  private boolean testMode = false;
    
  public NodeWebManager() {
    primaryWeb = new NodeWeb();
  }
    
  public NodeWebManager(NodeWeb n) {
    primaryWeb = n;
  }
    
  public void alphabetize(boolean preserveRoot) {
    boolean alphabetized = false;
    int s = 0;
    if (preserveRoot)
      s = 1;
    while (!alphabetized) {
      alphabetized = true;
      for (int i = s; i < primaryWeb.getLength() - 1; i++) {
        String firstName = primaryWeb.getTitleByIndex(i);
        String secondName = primaryWeb.getTitleByIndex(i + 1);
        if (firstName.compareToIgnoreCase(secondName) > 1) {
          primaryWeb.swapNodePositions(i, i + 1);
          alphabetized = false;
        }
      }
    }
  }
    
  public void groupByType(boolean preserveRoot) {
    int s = 0;
    if (preserveRoot)
      s = 1;
    int i = 0;
    boolean grouped = false;
    while (!grouped) {
      grouped = true;
      for (i = s; i < primaryWeb.getLength() - 1; i++) {
        String firstType = primaryWeb.getNodeByName(primaryWeb.getTitleByIndex(i)).getType();
        String secondType = primaryWeb.getNodeByName(primaryWeb.getTitleByIndex(i + 1)).getType();
        if (firstType.compareTo(secondType) >= 1) {
          primaryWeb.swapNodePositions(i, i + 1);
          grouped = false;
        }
      }
    }
  }
    
  public void setWeb(NodeWeb nw) {
    primaryWeb = nw;
    System.out.println("new web attached to manager");
  }
    
  public void showWebs(String title) {
    System.out.println(title);
    System.out.println(primaryWeb.getXMlString());
    reportTypes();
  }
    
  public void reportTypes() {
    System.out.println("GMNode.nodeTypes.size() = " + GMNode.nodeTypes.size());
    System.out.println(GMNode.nodeTypes.toString());
  }
    
  public void setNodeAsRoot(GMNode n) {
    try {
      int index = primaryWeb.getIndexByTitle(n.getTitle());
      primaryWeb.swapNodePositions(0, index);
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }
}
