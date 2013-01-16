/* GMNode - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
package gmbrain;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class GMNode implements Serializable {
  private gmNodeType type;
  private String title;
  private String description;
  private Vector nodeLinks;
  public static Vector nodeTypes = new Vector(3, 3);
  static final long serialVersionUID = -758913069199709057L;
  /*synthetic*/ static Class class$gmbrain$GMNode;
    
  private static class gmNodeType implements Serializable {
    private String typeName = "Undefined";
    private Icon typeIcon;
    private URL iconURL;
  
    public gmNodeType(String t, URL iURL) {
      typeIcon = new ImageIcon((GMNode.class$gmbrain$GMNode == null
              ? (GMNode.class$gmbrain$GMNode
                = class$("gmbrain.GMNode"))
              : GMNode.class$gmbrain$GMNode)
                .getResource("qmark.gif"));
      iconURL = (GMNode.class$gmbrain$GMNode == null
           ? GMNode.class$gmbrain$GMNode = class$("gmbrain.GMNode")
           : GMNode.class$gmbrain$GMNode)
             .getResource("qmark.gif");
      setName(t);
      setIconURL(iURL);
    }
  
    public gmNodeType() {
      typeIcon = new ImageIcon((GMNode.class$gmbrain$GMNode == null
              ? (GMNode.class$gmbrain$GMNode
                = class$("gmbrain.GMNode"))
              : GMNode.class$gmbrain$GMNode)
                .getResource("qmark.gif"));
      iconURL = (GMNode.class$gmbrain$GMNode == null
           ? GMNode.class$gmbrain$GMNode = class$("gmbrain.GMNode")
           : GMNode.class$gmbrain$GMNode)
             .getResource("qmark.gif");
    }
  
    public gmNodeType(String t) {
      typeIcon = new ImageIcon((GMNode.class$gmbrain$GMNode == null
              ? (GMNode.class$gmbrain$GMNode
                = class$("gmbrain.GMNode"))
              : GMNode.class$gmbrain$GMNode)
                .getResource("qmark.gif"));
      iconURL = (GMNode.class$gmbrain$GMNode == null
           ? GMNode.class$gmbrain$GMNode = class$("gmbrain.GMNode")
           : GMNode.class$gmbrain$GMNode)
             .getResource("qmark.gif");
      setName(t);
    }
  
    public void setIconURL(URL u) {
      try {
        typeIcon = new ImageIcon(u);
        iconURL = u;
      } catch (Exception e) {
        System.out.println("Something's wrong with Icon URL for " + getName());
      }
    }
  
    public String toString() {
      return typeName;
    }
  
    public Icon getIcon() {
      return typeIcon;
    }
  
    public void setName(String n) {
      typeName = n;
      if (n == null)
        System.out.println("set typeName to null");
    }
  
    public URL getIconURL() {
      return iconURL;
    }
  
    public String getName() {
      return typeName;
    }
  }
    
  public GMNode(String t) {
    type = new gmNodeType();
    title = " ";
    description = " ";
    nodeLinks = new Vector(5, 5);
    setTitle(t);
    setType("Undefined");
  }
    
  public GMNode(String t, String y) {
    type = new gmNodeType();
    title = " ";
    description = " ";
    nodeLinks = new Vector(5, 5);
    setTitle(t);
    setType(y);
  }
    
  public GMNode() {
    type = new gmNodeType();
    title = " ";
    description = " ";
    nodeLinks = new Vector(5, 5);
    setTitle("Untitled");
    setType("Undefined");
  }
    
  public String getTitle() {
    return title;
  }
    
  public void setTitle(String t) {
    title = t;
  }
    
  public void setDescription(String d) {
    description = d;
  }
    
  public String getDescription() {
    return description;
  }
    
  public String getType() {
    try {
      return type.toString();
    } catch (Exception e) {
      System.out.println(e.toString());
      return "Error-Warning!";
    }
  }
    
  public void setType(String t) {
    boolean isokay = false;
    for (int i = 0; i < nodeTypes.size(); i++) {
      if (t.equals(nodeTypes.elementAt(i).toString())) {
        isokay = true;
        type = (gmNodeType) nodeTypes.elementAt(i);
      }
    }
    if (!isokay) {
      addGMNodeType(t);
      setType(t);
    }
  }
    
  public void addLinkTo(GMNode n, boolean twoWay) {
    nodeLinks.addElement(n);
    if (twoWay)
      n.addLinkTo(this, false);
  }
    
  public void unLinkFrom(GMNode n, boolean twoWay) {
    if (n != null) {
      try {
        nodeLinks.removeElement(n);
        if (twoWay)
          n.unLinkFrom(this, false);
      } catch (Exception e) {
        System.out.println(e.toString());
      }
    }
  }
    
  public int getLinkCount() {
    int i = nodeLinks.size();
    return i;
  }
    
  public String toString() {
    return title;
  }
    
  public Vector getLinks() {
    orderLinks();
    return nodeLinks;
  }
    
  public Icon getIcon() {
    Icon icn = new ImageIcon((class$gmbrain$GMNode == null
           ? class$gmbrain$GMNode = class$("gmbrain.GMNode")
           : class$gmbrain$GMNode)
             .getResource("qmark.gif"));
    icn = type.getIcon();
    return icn;
  }
    
  private void writeObject(ObjectOutputStream out) throws IOException {
    out.writeUTF(getTitle());
    out.writeUTF(getDescription());
    out.writeUTF(getType());
    out.writeUTF(type.getIconURL().toString());
    out.writeObject(nodeLinks);
  }
    
  private void readObject(ObjectInputStream in) throws IOException {
    try {
      title = in.readUTF();
      description = in.readUTF();
      String tt = in.readUTF();
      if (tt != null)
        setType(tt);
      type.setIconURL(new URL(in.readUTF()));
      Object obj = in.readObject();
      nodeLinks = obj instanceof Vector ? (Vector) obj : null;
    } catch (Exception e) {
      System.out.println("Error reading a node: " + e.toString());
    }
  }
    
  private void orderLinks() {
    boolean sorted = false;
    if (!nodeLinks.isEmpty()) {
      while (!sorted) {
        sorted = true;
        for (int i = 0; i < nodeLinks.size() - 1; i++) {
          if (nodeLinks.elementAt(i).toString()
              .compareTo(nodeLinks.elementAt(i + 1).toString()) >= 1) {
            sorted = false;
            GMNode n = (GMNode) nodeLinks.elementAt(i + 1);
            nodeLinks.removeElementAt(i + 1);
            nodeLinks.insertElementAt(n, i);
          }
        }
      }
    }
  }
    
  public static void addGMNodeType(String n, URL u) {
    nodeTypes.add(new gmNodeType(n, u));
  }
    
  public void addGMNodeType(gmNodeType n) {
    nodeTypes.add(n);
  }
    
  public void setType(gmNodeType t) {
    boolean isokay = false;
    for (int i = 0; i < nodeTypes.size(); i++) {
      if (t.toString().equals(nodeTypes.elementAt(i).toString())) {
        isokay = true;
        type = (gmNodeType) nodeTypes.elementAt(i);
      }
    }
    if (!isokay) {
      addGMNodeType(t);
      setType(t);
    }
  }
    
  public void setNewType(String n, URL u) {
    addGMNodeType(n, u);
    setType(n);
  }
    
  public void setNewType(String n) {
    addGMNodeType(n);
    setType(n);
  }
    
  public void addGMNodeType(String n) {
    nodeTypes.add(new gmNodeType(n));
  }
    
  public void setIconURL(URL u) {
    type.setIconURL(u);
  }
    
  public void setTypeName(String t) {
    type.setName(t);
  }
    
  /*synthetic*/ static Class class$(String x0) {
    try {
      return Class.forName(x0);
    } catch (ClassNotFoundException x1) {
      throw new NoClassDefFoundError(x1.getMessage());
    }
  }
}
