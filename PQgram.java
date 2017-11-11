/**
 * Created by anjan.m on 7/6/2017.
 */

package org.fit.vips;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.w3c.dom.*;

import java.io.File;


import java.util.*;


public class PQgram
{

//    public List<Map<>> ProfileCollection;
    public List<List<String>> ProfileCollection;
    int P;
    int Q;
    int cnt;
//We initiallize a list of maps or some similar data stricture to store all the profiles of PQ grams.
// It will be filled in the function fillprofile
    PQgram()
    {
        P =4;
        Q = 4;
        cnt =0;
        //this.map = new map <>();
        ProfileCollection =  new ArrayList<List<String>>();
    }

    public  void makeprofiles(ReadXMLFile XReader,Deque<String> Ancestors, String id)
    {

//        List<Map<String ,String >> templist = new ArrayList<Map<String,String >>();
//        return;
//        XReader.printadjlist();

        if (XReader.AdjList.get(id) == null)
        {
//            It means it is a tempchild so it wont have val in store and adjlist so return
//            System.out.println("it is null");
            return;
        }
        if(id.startsWith("t"))
        {
            System.out.println("it is a teemp");
        }

//        System.out.println(id);
//        System.out.println(Ancestors);
//        System.out.println("--------------------------------------");
        for (int j =0;j< XReader.AdjList.get(id).size()-(Q-1);j++)
        {
            List<String > templist =  new ArrayList<String>();
            for(String it : Ancestors)
            {
//            System.out.println(it);
                templist.add(it);
            }
            templist.add(id);
            for (int k=0; k< Q; k++)
            {
                templist.add(XReader.AdjList.get(id).get(j+k));
            }
//            System.out.println(id);
//            System.out.println(XReader.AdjList.get(id));
//            System.out.println(Ancestors);
//            System.out.println(templist);
            ProfileCollection.add(templist);
//            this.cnt++;

//            System.out.println("--------------------------------------");
//            System.out.println(this.ProfileCollection.size());
//               System.out.println(this.cnt);
//            System.out.println(id);
//            System.out.println("--------------------------------------");
//            System.out.println("--------------------------------------");
        }
//        for(int j=0;j<Ancestors.size();j++)
//        {
//            templist.add()

//        }
//            Do whatever you want  here before chaanging the ancestors



//        Changing the ancestors
        String first = Ancestors.getFirst();
        Ancestors.removeFirst();
        boolean add = Ancestors.add(id);
        for(int i =0;i< XReader.AdjList.get(id).size();i++)
        {
            this.makeprofiles(XReader,Ancestors,XReader.AdjList.get(id).get(i));
//            Ancestors.removeLast();
//            System.out.println(XReader.AdjList.get(id).get(i));
        }
        Ancestors.removeLast();
        Ancestors.addFirst(first);

    }
    public  void fillprofile(ReadXMLFile XReader)
    {
//        System.out.println("filling the profile");
        //this.map = bla bla bla. it should work as in case of dfs

//        Now put temp p-1 parents.


        for (int i = 0;i<P-1;i++)
        {
            String id = "temppar" + Integer.toString(i);
//            System.out.println(id);
            XReader.AdjList.put(id,new LinkedList<String>());
            TreeNode tempnode = new TreeNode();
            (XReader.Store).put(id,tempnode);
            if(i>0)
            {
                String prid = "temppar" + Integer.toString(i-1);
                XReader.AdjList.get(prid).add(id);
            }
            if (i==P-2)
            {
                XReader.AdjList.get(id).add("1");
            }
//            XReader.printadjlist();

        }
//        XReader.printadjlist();
//        XReader.printtreenode("temppar0");

//        Then do a DFS and put Q-1 children for all non leaf nodes.Add then in the begining and at the end of each node
//        But do not traverse once you have an dummy child node. Mark that these are not to be done DFS.
//        No need of DFS just iterate over  the adjlist  and put Q-1 in front and at end for all notes which are not leaves
        for (Map.Entry<String, List<String >> entry : (XReader.AdjList).entrySet())
        {

//            System.out.println(entry.getKey() + "  :   " + entry.getValue());
//            System.out.println((XReader.Store.get(entry.getKey())).Params.get("ID"));
//            if(XReader.Store.get(entry.getKey()))
//            if ((XReader.Store.get(entry.getKey())).Params.get("ID").startsWith("te"))
//            {
//
//            }
            if (entry.getValue().size()>0)
            {
//                System.out.println(entry.getKey() + "  :   " + entry.getValue());
                for (int j =0;j<Q-1;j++)
                {
                    String id = "tempch" + Integer.toString(j);
                    XReader.AdjList.get(entry.getKey()).add(0,id);
                    XReader.AdjList.get(entry.getKey()).add(id);
                }
            }
            else
            {
                for (int j =0;j<Q;j++)
                {
                    String id = "tempch" + Integer.toString(j);
//                    XReader.AdjList.get(entry.getKey()).add(0,id);
                    XReader.AdjList.get(entry.getKey()).add(id);
                }
            }

        }
//        Temp Children is done
//        XReader.printadjlist();
//        System.out.println("----------------------------" );
//        List<String > Ancestors = new ArrayList<>();
        Deque Ancestors = new ArrayDeque();
        for (int i = 0;i<P-1;i++)
        {
            String id = "temppar" + Integer.toString(i);
            Ancestors.add(id);
        }
//        Ancestors.removeFirst();
//        System.out.println(Ancestors);
//        parents and children are made. Now do dfs and fill the profiles
        this.makeprofiles(XReader,Ancestors,"1");
//        System.out.println("________");
//        System.out.println(this.ProfileCollection.size());
//        System.out.println(this.cnt);
//        System.out.println("________");
    }


    public static void main(String argv[])
    {
        PQgram PQ = new PQgram();
        //We will give which tree to parse to fillprofile.
    }
}
