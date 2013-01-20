/* NodeWeb - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
package gmbrain;

import java.io.Serializable;
import java.util.Vector;
import org.markdown4j.*;

public class NodeWeb implements Serializable {
  private Vector nodeList;
  static final long serialVersionUID = 7816144560997400379L;
    
  @SuppressWarnings("unchecked")
  public NodeWeb() {
    nodeList = new Vector(10, 5);
    GMNode welcomeNode = new GMNode("Welcome to Your Second Brain!");
    welcomeNode.setDescription("This is a blank list and a (pretty much)"
      + " blank node. You can begin by editing this Web or by Loading a "
      + "pre-existing Web.\n If you are making a new web, use this node "
      + "as an Introduction or starting node.");
    nodeList.add(0, welcomeNode);
    GMNode.nodeTypes.setSize(1);
  }
    
  public NodeWeb(boolean hollow) {
    nodeList = new Vector(10, 5);
  }
    
  public int getLength() {
    return nodeList.size();
  }
    
  public GMNode getNodeByName(String nm) {
    GMNode tempnode = null;
    for (int i = 0; i < getLength(); i++) {
      if (nodeList.elementAt(i) instanceof GMNode)
        tempnode = (GMNode) nodeList.elementAt(i);
      if (tempnode.getTitle().equals(nm))
        break;
      tempnode = null;
    }
    return tempnode;
  }
  
  @SuppressWarnings("unchecked")
  public void addNode(GMNode n) {
    if (n instanceof GMNode) {
      nodeList.addElement(n);
      n.setType(n.getType());
    }
  }
    
  public void deleteNode(GMNode n) {
    nodeList.removeElement(n);
    int i = 0;
    GMNode tempNode = new GMNode();
    if (n.getLinkCount() > 0) {
      Vector nlinks = n.getLinks();
      for (i = 0; i < n.getLinkCount(); i++) {
        tempNode = (GMNode) nlinks.elementAt(i);
        tempNode.unLinkFrom(n, true);
      }
    }
  }
  
  @SuppressWarnings("unchecked")  
  public Vector retrieveOrphans() {
    Vector orphanList = new Vector();
    GMNode tempnode = new GMNode();
    for (int i = 0; i < getLength(); i++) {
      tempnode = (GMNode) nodeList.elementAt(i);
      if (tempnode.getLinkCount() == 0)
        orphanList.addElement(nodeList.elementAt(i));
    }
    return orphanList;
  }
    
  public GMNode getRoot() {
    return (GMNode) nodeList.elementAt(0);
  }
    
  public String getTitleByIndex(int n) {
    GMNode m = (GMNode) nodeList.elementAt(n);
    return m.getTitle();
  }
    
  public String getPrintputByTitle(String t) {
    String p = " ";
    String nl = System.getProperty("line.separator");
    GMNode n = getNodeByName(t);
    Vector nlinks = n.getLinks();
    p += "=" + n.getTitle() + "=" + nl + "<" + n.getType() + ">" + nl;
    p += n.getDescription() + nl;
    if (nlinks.size() > 0) {
      p += "See Also: ";
      int i;
      for (i = 0; i < nlinks.size() - 1; i++)
        p += nlinks.elementAt(i).toString() + ", ";
      p += nlinks.elementAt(i).toString();
    }
    return p;
  }
    
  public String getXMlString() {
    String xout = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?> <NodeWeb>";
    GMNode tempNode = new GMNode();
    GMNode temperNode = new GMNode();
    for (int i = 0; i < nodeList.size(); i++) {
      xout += "<node idnum=\"" + i + "\">";
      if (nodeList.elementAt(i) instanceof GMNode)
        tempNode = (GMNode) nodeList.elementAt(i);
      xout += "<title>" + tempNode.getTitle() + "</title>";
      xout += ("<description>" + tempNode.getDescription() + "</description>");
      xout += "<type>" + tempNode.getType() + "</type>";
      xout += "<linklist>";
      for (int j = 0; j < tempNode.getLinkCount(); j++) {
        temperNode = (GMNode) tempNode.getLinks().elementAt(j);
        xout += "<link> <name>" + temperNode.getTitle() + "</name>";
        xout += "<id>" + nodeList.indexOf(temperNode) + "</id> </link>";
      }
      xout += "</linklist></node>";
    }
    xout += "</NodeWeb>";
    return xout;
  }
    
  public int getIndexByTitle(String t) {
    int i = -1;
    try {
      i = nodeList.indexOf(getNodeByName(t));
    } catch (Exception e) {
      i = -1;
    }
    return i;
  }
    
  public String getHTMLString() {
    String xout = "<HTML> <! output from a NodeWeb><BODY>";
    GMNode tempNode = new GMNode();
    GMNode temperNode = new GMNode();
    String nl = System.getProperty("line.separator");
    for (int i = 0; i < nodeList.size(); i++) {
      if (nodeList.elementAt(i) instanceof GMNode)
        tempNode = (GMNode) nodeList.elementAt(i);
      xout += "<P><A NAME=\"node" + i + "\">";
      xout += "<STRONG>" + tempNode.getTitle() + "</STRONG></A><BR>";
      xout += "Type: " + tempNode.getType() + "<BR>";
      try {
        String D = tempNode.getDescription();
        D = D.toString();
        String nodeHtml = new Markdown4jProcessor().process(D);
        System.out.println(nodeHtml);
        xout += nodeHtml;
        /*
        for (int k = 0; k < D.length(); k++) {
          if (D.substring(k, k + 1).equals("\n") || D.substring(k, k + 1).equals("\r"))
            xout += "<BR>";
          else
            xout += D.substring(k, k + 1);
        }
        */
      } catch (Exception exception) {
        /* empty */
      }
      xout += "<BR>See Also:";
      for (int j = 0; j < tempNode.getLinkCount(); j++) {
        temperNode = (GMNode) tempNode.getLinks().elementAt(j);
        xout += ("<A HREF=#node" + nodeList.indexOf(temperNode) + ">"
          + temperNode.getTitle() + "</A>,");
      }
      xout += "<P>";
    }
    xout += "</BODY><HTML>";
    return xout;
  }
  
  @SuppressWarnings("unchecked")
  public void swapNodePositions(int firstIndex, int secondIndex) {
    int t = 0;
    if (firstIndex > secondIndex) {
      t = firstIndex;
      firstIndex = secondIndex;
      secondIndex = t;
    }
    try {
      GMNode tempNode = (GMNode) nodeList.elementAt(secondIndex);
      nodeList.setElementAt(nodeList.elementAt(firstIndex), secondIndex);
      nodeList.setElementAt(tempNode, firstIndex);
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }
    
  public void trimNodeTypes() {
    boolean[] stillUsed = new boolean[GMNode.nodeTypes.size()];
    int i = 0;
    for (i = 0; i < stillUsed.length; i++)
      stillUsed[i] = false;
    int j = 0;
    GMNode tempNode = null;
    for (j = 0; j < GMNode.nodeTypes.size(); j++) {
      for (i = 0; i < nodeList.size(); i++) {
        tempNode = (GMNode) nodeList.elementAt(i);
        if (tempNode.getType().equals(GMNode.nodeTypes.elementAt(j).toString()))
          stillUsed[j] = true;
        if (stillUsed[j])
          break;
      }
    }
    for (j = stillUsed.length; j > 0; j--) {
      if (!stillUsed[j - 1])
      GMNode.nodeTypes.removeElementAt(j - 1);
    }
  }
}
