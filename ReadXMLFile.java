/**
 * Created by anjan.m on 7/4/2017.
 */

package org.fit.vips;
        import javax.xml.parsers.DocumentBuilderFactory;
        import javax.xml.parsers.DocumentBuilder;

        import com.sun.xml.internal.bind.v2.model.core.ID;
        import org.w3c.dom.*;

        import java.io.File;


        import java.util.HashMap;
        import java.util.InputMismatchException;
        import java.util.LinkedList;
        import java.util.List;
        import java.util.Iterator;
//        import java.util.ArrayList;
        import java.util.Map;
        import java.util.Scanner;



public class ReadXMLFile
{

    public  Map<String, List<String>> AdjList;
    public Map<String,TreeNode> Store;
    public int qwe;
    ReadXMLFile()
    {
        this.qwe = 3;
        this.AdjList = new HashMap<String, List<String>>();
        this.Store = new HashMap<String,TreeNode>();
    }




    public  void printadjlist()
    {
        for (Map.Entry<String, List<String >> entry : (this.AdjList).entrySet())
            {

                System.out.println(entry.getKey() + "  :   " + entry.getValue());
            }
            System.out.println("----------------------------" );
    }

    public  void printtreenode(String ID)
    {
        TreeNode treenode =  this.Store.get(ID);
//        System.out.println(treenode.Params.size());
        for (Map.Entry<String, String> entry : (treenode.Params).entrySet())
        {
//            System.out.println(entry.getKey() + "  :   " + entry.getValue());

        }
//                System.out.println("----------------------------" );
    }




    public  void dfs(Node el)
    {
        //Note VIPS doesnt have an ID no do dfs for children in the main function
        if ((el.getNodeName()).equals("VIPSPage"))
        {
//            System.out.println("Page");
            NodeList chl = el.getChildNodes();
            for(int i = 0; i < chl.getLength(); i++)
            {
                if ((chl.item(i)).getNodeType() == Node.ELEMENT_NODE)
                {
                    TreeNode tempnode = new TreeNode();
                    NamedNodeMap attributes = (chl.item(i)).getAttributes();
                    int numAttrs = attributes.getLength();
                    for (int j = 0; j < numAttrs; j++)
                    {
                        Attr attr = (Attr) attributes.item(j);
                        String attrName = attr.getNodeName();
                        String attrValue = attr.getNodeValue();
                        (tempnode.Params).put(attrName, attrValue);
                    }
                    (this.Store).put((tempnode.Params).get("ID"),tempnode);

                    this.dfs(chl.item(i));
                }
            }
            return;
        }

        Element e = (Element)el;
        String name = e.getAttribute("ID");


        List<String> templist = new LinkedList <String>();
        (this.AdjList).put(name,templist);
        NodeList chl = el.getChildNodes();
        for(int i = 0; i < chl.getLength(); i++)
        {
            if ((chl.item(i)).getNodeType() == Node.ELEMENT_NODE)
            {
                TreeNode tempnode = new TreeNode();
                NamedNodeMap attributes = (chl.item(i)).getAttributes();
                int numAttrs = attributes.getLength();
                for (int j = 0; j < numAttrs; j++)
                {
                    Attr attr = (Attr) attributes.item(j);
                    String attrName = attr.getNodeName();
                    String attrValue = attr.getNodeValue();
                    (tempnode.Params).put(attrName, attrValue);
                }
                this.AdjList.get(name).add(tempnode.Params.get("ID"));
                //Tree node for each node is created. Now we have to fill the store.
                (this.Store).put((tempnode.Params).get("ID"),tempnode);
            }

            if ((chl.item(i)).getNodeType() == Node.ELEMENT_NODE)
            {
                this.dfs(chl.item(i));
            }

        }
        return;
    }

    public static void main(String argv[])
    {

        try {
//            File file = new File(".");
//            for(String fileNames : file.list()) System.out.println(fileNames);

            File fXmlFile = new File("wiki.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work

            doc.getDocumentElement().normalize();


//            System.out.println("Root element :" + (doc.getDocumentElement()).getNextSibling().getNodeName());

            Element docEl = doc.getDocumentElement();

            ReadXMLFile XReader = new ReadXMLFile();
            XReader.dfs(docEl);
            System.out.println("dfs over----------------------");

            for (Map.Entry<String, List<String >> entry : (XReader.AdjList).entrySet())
                {

//                    System.out.println(entry.getKey() + "  :   " + entry.getValue());
//                    System.out.println(entry.getKey() + "  :   " + (entry.getValue()).Params.get("ID"));
                }
//                System.out.println("----------------------------" );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
