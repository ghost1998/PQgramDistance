/**
 * Created by anjan.m on 7/5/2017.
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

public class PQ
{



    public static void main(String argv[])
    {

        try {
//            File file = new File(".");
//            for(String fileNames : file.list()) System.out.println(fileNames);


//------------------------------------------------
//            File fXmlFile1 = new File("wiki.xml");
            File fXmlFile1 = new File("car.xml");

            DocumentBuilderFactory dbFactory1 = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder1 = dbFactory1.newDocumentBuilder();

            Document doc1 = dBuilder1.parse(fXmlFile1);
            doc1.getDocumentElement().normalize();
            Element docEl1 = doc1.getDocumentElement();


            ReadXMLFile XReader1 = new ReadXMLFile();
            XReader1.dfs(docEl1);

//---------------------------------------------------------
            File fXmlFile2 = new File("nk.xml");

            DocumentBuilderFactory dbFactory2 = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder2 = dbFactory2.newDocumentBuilder();

            Document doc2 = dBuilder2.parse(fXmlFile2);
            doc2.getDocumentElement().normalize();
            Element docEl2 = doc2.getDocumentElement();




            ReadXMLFile XReader2 = new ReadXMLFile();
            XReader2.dfs(docEl2);
//---------------------------------------------------------

//            File fXmlFile3 = new File("snt.xml");
//            File fXmlFile3 = new File("gc.xml");
//
//            DocumentBuilderFactory dbFactory3 = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder3 = dbFactory3.newDocumentBuilder();
//
//            Document doc3 = dBuilder3.parse(fXmlFile3);
//            doc3.getDocumentElement().normalize();
//            Element docEl3 = doc3.getDocumentElement();
//
//
//            ReadXMLFile XReader3 = new ReadXMLFile();
//            XReader3.dfs(docEl3);

//            XReader.printadjlist();
//            XReader.printtreenode("1");

//----------------------------------------------------------

//            We have two trees here. We have store and adj list here.
//            So we initialise 2 Pqgram instances here and call fillprofiles which will gives  us the pq gram profiles of both trees
            PQgram PQ1 = new PQgram();
            PQgram PQ2 = new PQgram();
//            PQgram PQ3 = new PQgram();
            PQ1.fillprofile(XReader1);
            PQ2.fillprofile(XReader2);
//            PQ3.fillprofile(XReader3);



//            XReader2.printadjlist();
//            System.out.println(PQ1.ProfileCollection);
//            System.out.println(PQ2.ProfileCollection);
//            System.out.println(PQ3.ProfileCollection.size());

//            System.out.println(XReader1.Store);
//            System.out.println(XReader2.Store.size());
//            System.out.println(XReader3.Store.size());
//
//            We now have PQ1.ProfileCollection and PQ2.ProfileCollection
//            We call a similarity function and find the similarity
            Similarity sim1 = new Similarity();
            double result1 = sim1.Similarityfunction(PQ1,PQ2,XReader1,XReader2);
//
//            Similarity sim2 = new Similarity();
//            double result2 = sim2.Similarityfunction(PQ1,PQ3,XReader1,XReader3);
            System.out.println(Double.toString(result1));
//            System.out.println(Double.toString(result2));
//            System.out.println(Double.toString(result2-result1));
//----------------------------------------------------------
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
