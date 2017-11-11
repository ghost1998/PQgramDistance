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
//        import java.util.ArrayList;


public class Similarity
{
    public double compare(PQgram PQ1,PQgram PQ2,ReadXMLFile XReader1,ReadXMLFile XReader2,int i,int j)
    {

        double temp2=0;
        List<Double> temp25 = new ArrayList<Double> ();
//                Compare list of strings with another list of strings
//        System.out.println("--------------------------------------");
//        System.out.println(PQ1.ProfileCollection.get(i));
//        System.out.println(PQ2.ProfileCollection.get(j));
//        System.out.println("_______");
        for  (int k =0;k<PQ1.ProfileCollection.get(i).size();k++)
        {

            List<Double> temp3 = new ArrayList<Double> ();
            Double totalweight = 0.00;
//            Double
            String id1 = PQ1.ProfileCollection.get(i).get(k);
            String id2 = PQ2.ProfileCollection.get(j).get(k);
//            System.out.println(id1);
//            System.out.println(id2);
//            System.out.println("-------------------------------------");
//            System.out.println(PQ2.ProfileCollection.get(j).size());
//            System.out.println(id2);
//                    System.out.println(id1);
//                    double temp3=0;


            if(id1.startsWith("1") && id2.startsWith("1"))
            {
//                System.out.println("in loop");
//                        List<Double> temp3 = new ArrayList<Double> ();
                //real nodes so compare
//                        temp3=0.5 //real code
                TreeNode tn1 = XReader1.Store.get(id1);
                TreeNode tn2 = XReader2.Store.get(id2);
                Map<String ,String > tn1params = tn1.Params;
                Map<String ,String > tn2params = tn2.Params;
//                System.out.println("foundin1");
                if(tn1params.containsKey("BgColor") && tn2params.containsKey("BgColor"))
                {
//                    System.out.println("found");
                    Double widweight = 20.0;
                    if (tn1params.get("BgColor").equals(tn2params.get("BgColor")))
                    {
                        temp3.add(widweight * 1.00);

//                                System.out.println("Contains Bgcolor" + tn1params.get("BgColor") );
                    }
                    totalweight = totalweight + widweight;


                }
                if(tn1params.containsKey("ContainImg") && tn2params.containsKey("ContainImg"))
                {

                    int imnum1 = Integer.parseInt(tn1params.get("ContainImg"));
                    int imnum2 = Integer.parseInt(tn2params.get("ContainImg"));
                    if(imnum1>0 && imnum2>0)
                    {
                        if (imnum1==imnum2)
                        {
                            Double widweight = 20.05;
                            temp3.add(widweight * 1.00);
                            totalweight = totalweight + widweight;
                        }
                        else
                        {
                            Double widweight = 7.05;
                            temp3.add(0.6* widweight);
                            totalweight = totalweight + widweight;
                        }
                    }
                    else if(imnum1 == imnum2)
                    {
                        Double widweight = 12.05;
                        temp3.add(0.6* widweight);
                        totalweight = totalweight + widweight;
                    }
//                            System.out.println("Contains ContainImg " + tn1params.get("ContainImg") );

                }
                if(tn1params.containsKey("ContainTable") && tn2params.containsKey("ContainTable"))
                {
                    Double widweight = 3.0;
//                            System.out.println("Contains table  " + tn1params.get("ContainTable") );
                    if(tn1params.get("ContainTable").equals(tn2params.get("ContainTable")))
                    {
                        if (tn1params.get("ContainTable").equals("false"))
                        {
//                                    System.out.println(("--------------- "));
                            temp3.add(0.7* widweight);
                        }
                        else
                        {
                            temp3.add(1.00 * widweight);
                        }
//                                System.out.println(tn1params.get("ContainTable"));
//                                System.out.println(tn2params.get("ContainTable"));
//                                System.out.println(("--------------- "));
                    }
                    totalweight = totalweight + widweight;
                }

                if(tn1params.containsKey("DoC") && tn2params.containsKey("DoC"))
                {
                    Double widweight = 15.0005;
                    int docnum1 = Integer.parseInt(tn1params.get("DoC"));
                    int docnum2 = Integer.parseInt(tn2params.get("DoC"));
                    if(docnum1 == docnum2)
                    {
                        temp3.add(widweight * 1.00);
                    }
                    if(docnum1 == docnum2+1 || docnum1 == docnum2-1 )
                    {
                        temp3.add(widweight * 0.80);
                    }
                    if (docnum1 == docnum2+2 || docnum1 == docnum2-2 )
                    {
                        temp3.add(0.50 * widweight);
                    }
//                            System.out.println(docnum1);
                    totalweight = totalweight + widweight;
                }
//                        FontSize
                if(tn1params.containsKey("FontSize") && tn2params.containsKey("FontSize"))
                {
                    Double widweight = 25.0;
                    int fs1 = Integer.parseInt(tn1params.get("FontSize"));
                    int fs2 = Integer.parseInt(tn2params.get("FontSize"));

                    if(fs1 == fs2)
                    {
//                                System.out.println("fonts are " + fs1 + " "+ fs2);
                        temp3.add(1.00 * widweight);
                    }
                    totalweight = totalweight + widweight;
                }
                if(tn1params.containsKey("FontWeight") && tn2params.containsKey("FontWeight"))
                {
                    Double widweight = 20.0;
//                            System.out.println(tn1params.get("FontWeight")+ " " + tn2params.get("FontWeight"));
                    if(tn1params.get("FontWeight").equals(tn2params.get("FontWeight")))
                    {
                        temp3.add(1.00 * widweight);
                    }
                    totalweight = totalweight + widweight;
                }
                if(tn1params.containsKey("IsImg") && tn2params.containsKey("IsImg"))
                {

//                            System.out.println(tn1params.get("IsImg")+ " " + tn2params.get("IsImg"));
                    if(tn1params.get("IsImg").equals(tn2params.get("IsImg")))
                    {
                        if(tn1params.get("IsImg").equals("false"))
                        {
                            Double widweight = 25.0;
                            temp3.add(1.00 * widweight);
                            totalweight = totalweight + widweight;
//                                    System.out.println("-------------");
                        }
                        else
                        {
                            Double widweight = 50.0;
                            temp3.add(1.00 * widweight);
                            totalweight = totalweight + widweight;
                        }

                    }
                }
                if(tn1params.containsKey("ObjectRectWidth") && tn2params.containsKey("ObjectRectWidth"))
                {

                    Double fract1 =  Double.parseDouble(tn1params.get("ObjectRectWidth"));
                    Double fract2 =  Double.parseDouble(tn2params.get("ObjectRectWidth"));

                    if(fract1 == fract2)
                    {
                        Double widweight = 75.0;
//                                System.out.println("fonts are " + fs1 + " "+ fs2);
                        temp3.add(1.00 * widweight);
                        totalweight = totalweight + widweight;
                    }
                    else
                    {
                        Double widweight = 50.0;
                        if(Math.abs(fract1-fract2)<15)
                        {
                            temp3.add(0.90 * widweight);
                            totalweight = totalweight + widweight;
                        }
                    }

                }
                if(tn1params.containsKey("ObjectRectLeft") && tn2params.containsKey("ObjectRectLeft"))
                {
                    Double fract1 =  Double.parseDouble(tn1params.get("ObjectRectLeft"));
                    Double fract2 =  Double.parseDouble(tn2params.get("ObjectRectLeft"));

                    if(fract1 == fract2)
                    {
                        Double widweight = 75.0;
//                                System.out.println("fonts are " + fs1 + " "+ fs2);
                        temp3.add(1.00 * widweight);
                        totalweight = totalweight + widweight;
                    }
                    else
                    {
                        Double widweight = 50.0;
                        if(Math.abs(fract1-fract2)<15)
                        {
                            temp3.add(0.90 * widweight);
                        }
                        totalweight = totalweight + widweight;
                    }

                }
//                        if(tn1params.containsKey("IsImg") && tn2params.containsKey("IsImg"))
                if(k>0)
                {
                    String pid1 = "t";
                    String pid2 = "t";
//                            "ObjectRectHeight"
//                            "ObjectRectLeft"
//                            "ObjectRectTop"
//                            "ObjectRectWidth"
                    if(k<PQ1.P)
                    {
//                                parents are k-1 element
                        pid1 = PQ1.ProfileCollection.get(i).get(k-1);
                        pid2 = PQ2.ProfileCollection.get(j).get(k-1);
                    }
                    else if (k >= PQ1.P)
                    {

//                                parent is PQ.P -1 elemtnt
                        pid1 = PQ1.ProfileCollection.get(i).get(PQ1.P-1);
                        pid2 = PQ2.ProfileCollection.get(j).get(PQ1.P-1);
//                                System.out.println(id1 + " " + id2 + " "+ pid1+ " " + pid2);
                    }
                    else
                    {
                        System.out.println("in else");
                    }
                    if(id1.startsWith("1")  && id2.startsWith("1") && pid1.startsWith("1") && pid2.startsWith("1"))
                    {
                        Double widweight = 100.0;
                        TreeNode ptn1 = XReader1.Store.get(pid1);
                        TreeNode ptn2 = XReader2.Store.get(pid2);
                        Map<String ,String > ptn1params = ptn1.Params;
                        Map<String ,String > ptn2params = ptn2.Params;
//                                System.out.println(id1 + " " + id2 + " "+ pid1+ " " + pid2);
                        if(tn1params.containsKey("ObjectRectWidth") && ptn1params.containsKey("ObjectRectWidth")
                                && tn2params.containsKey("ObjectRectWidth") && ptn2params.containsKey("ObjectRectWidth"))
                        {
                            Double fract1 =  Double.parseDouble(tn1params.get("ObjectRectWidth"))
                                    /Double.parseDouble(ptn1params.get("ObjectRectWidth"));
                            Double fract2 =  Double.parseDouble(tn2params.get("ObjectRectWidth"))
                                    /Double.parseDouble(ptn2params.get("ObjectRectWidth"));
                            if(fract1 == fract2)
                            {
                                temp3.add(1*widweight);
                                totalweight = totalweight +  widweight;
                            }
                            else if(Math.abs(fract1-fract2) < 0.05)
                            {
                                temp3.add(0.925*widweight);
                                totalweight = totalweight +  widweight;
                            }
                            else if(Math.abs(fract1-fract2) < 0.25)
                            {
                                temp3.add(0.85*widweight);
                                totalweight = totalweight +  widweight;
                            }
                            else
                            {
                                totalweight = totalweight + widweight/20;
                            }
                        }

                        if(tn1params.containsKey("ObjectRectLeft") && ptn1params.containsKey("ObjectRectLeft")
                                && tn2params.containsKey("ObjectRectLeft") && ptn2params.containsKey("ObjectRectLeft"))
                        {
                            Double fract1 =  Double.parseDouble(tn1params.get("ObjectRectLeft"))
                                    /Double.parseDouble(ptn1params.get("ObjectRectLeft"));
                            Double fract2 =  Double.parseDouble(tn2params.get("ObjectRectLeft"))
                                    /Double.parseDouble(ptn2params.get("ObjectRectLeft"));
                            if(fract1 == fract2)
                            {
                                temp3.add(1*widweight);
                                totalweight = totalweight +  widweight;
                            }
                            else if(Math.abs(fract1-fract2) < 0.05)
                            {
                                temp3.add(0.925*widweight);
                                totalweight = totalweight +  widweight;
                            }
                            else if(Math.abs(fract1-fract2) < 0.25)
                            {
                                temp3.add(0.85*widweight);
                                totalweight = totalweight +  widweight;
                            }
                        }
                    }

                }

//                        if (k>0)
            }
            else if(PQ1.ProfileCollection.get(i).get(k).startsWith("t") && PQ2.ProfileCollection.get(j).get(k).startsWith("t"))
            {
//                double mat=1;
//                temp25.add(mat);
//                        temp3.add(1.00);
//                        totalweight = totalweight +  1;
//                        temp3=1;
            }
            else
            {
                temp3.add(0.00);
                totalweight = totalweight +  1;
            }
            double numer =0;
            double denom = 0;
            double mat = 0;
            if (totalweight==0 || temp3.isEmpty())
            {
//                System.out.println("w is 0");
                mat =0;
            }
            else
            {
                List<Double> newtemp3 = new ArrayList<Double>();
                for(Double it : temp3)
                {
                    newtemp3.add(it/totalweight);
//                    numer+=it/totalweight;
                }
                for(Double it : newtemp3)
                {
                    numer+=(it);
                }
                for(Double it : newtemp3)
                {
                    denom = 1;
                }

//                System.out.println(numer);

                if(denom<0.000000000001)
                {
                    mat =0.0;
//                    System.out.println("---------");
//                    System.out.println(denom);
//                    System.out.println(numer);
//                    System.out.println(newtemp3.size());
//                    System.out.println("---------");
                }
                else
                {
                    mat = numer/denom;
                }
                temp25.add(mat);

            }



        }



        if(temp25.size() ==0 )
        {
            temp2=0;
        }
        else
        {
            for(Double it : temp25)
                temp2 += it;
            temp2 = temp2/temp25.size();
        }
//        System.out.println("---------");
//        System.out.println(temp25);
//        System.out.println(temp2);
//        System.out.println("__________");

        return temp2;
    }

    public double compare1(PQgram PQ1,PQgram PQ2,ReadXMLFile XReader1,ReadXMLFile XReader2,int i,int j)
    {

        double temp2=0;
        List<Double> temp25 = new ArrayList<Double> ();
//                Compare list of strings with another list of strings
//        System.out.println("--------------------------------------");
//        System.out.println(PQ1.ProfileCollection.get(i));
//        System.out.println(PQ2.ProfileCollection.get(j));
//        System.out.println("_______");
        for  (int k =0;k<PQ1.ProfileCollection.get(i).size();k++)
        {

            List<Double> temp3 = new ArrayList<Double> ();
            Double totalweight = 0.00;
//            Double
            String id1 = PQ1.ProfileCollection.get(i).get(k);
            String id2 = PQ2.ProfileCollection.get(j).get(k);
//            System.out.println(id1);
//            System.out.println(id2);
//            System.out.println("-------------------------------------");
//            System.out.println(PQ2.ProfileCollection.get(j).size());
//            System.out.println(id2);
//                    System.out.println(id1);
//                    double temp3=0;


            if(id1.startsWith("1") && id2.startsWith("1"))
            {
//                System.out.println("in loop");
//                        List<Double> temp3 = new ArrayList<Double> ();
                //real nodes so compare
//                        temp3=0.5 //real code
                TreeNode tn1 = XReader1.Store.get(id1);
                TreeNode tn2 = XReader2.Store.get(id2);
                Map<String ,String > tn1params = tn1.Params;
                Map<String ,String > tn2params = tn2.Params;
//                System.out.println("foundin1");
                if(tn1params.containsKey("BgColor") && tn2params.containsKey("BgColor"))
                {
//                    System.out.println("found");
                    Double widweight = 20.0;
                    if (tn1params.get("BgColor").equals(tn2params.get("BgColor")))
                    {
                        temp3.add(widweight * 1.00);

//                                System.out.println("Contains Bgcolor" + tn1params.get("BgColor") );
                    }
                    totalweight = totalweight + widweight;


                }
                if(tn1params.containsKey("ContainImg") && tn2params.containsKey("ContainImg"))
                {

                    int imnum1 = Integer.parseInt(tn1params.get("ContainImg"));
                    int imnum2 = Integer.parseInt(tn2params.get("ContainImg"));
                    if(imnum1>0 && imnum2>0)
                    {
                        if (imnum1==imnum2)
                        {
                            Double widweight = 20.05;
                            temp3.add(widweight * 1.00);
                            totalweight = totalweight + widweight;
                        }
                        else
                        {
                            Double widweight = 7.05;
                            temp3.add(0.6* widweight);
                            totalweight = totalweight + widweight;
                        }
                    }
                    else if(imnum1 == imnum2)
                    {
                        Double widweight = 12.05;
                        temp3.add(0.6* widweight);
                        totalweight = totalweight + widweight;
                    }
//                            System.out.println("Contains ContainImg " + tn1params.get("ContainImg") );

                }
                if(tn1params.containsKey("ContainTable") && tn2params.containsKey("ContainTable"))
                {
                    Double widweight = 3.0;
//                            System.out.println("Contains table  " + tn1params.get("ContainTable") );
                    if(tn1params.get("ContainTable").equals(tn2params.get("ContainTable")))
                    {
                        if (tn1params.get("ContainTable").equals("false"))
                        {
//                                    System.out.println(("--------------- "));
                            temp3.add(0.7* widweight);
                        }
                        else
                        {
                            temp3.add(1.00 * widweight);
                        }
//                                System.out.println(tn1params.get("ContainTable"));
//                                System.out.println(tn2params.get("ContainTable"));
//                                System.out.println(("--------------- "));
                    }
                    totalweight = totalweight + widweight;
                }

                if(tn1params.containsKey("DoC") && tn2params.containsKey("DoC"))
                {
                    Double widweight = 15.0005;
                    int docnum1 = Integer.parseInt(tn1params.get("DoC"));
                    int docnum2 = Integer.parseInt(tn2params.get("DoC"));
                    if(docnum1 == docnum2)
                    {
                        temp3.add(widweight * 1.00);
                    }
                    if(docnum1 == docnum2+1 || docnum1 == docnum2-1 )
                    {
                        temp3.add(widweight * 0.80);
                    }
                    if (docnum1 == docnum2+2 || docnum1 == docnum2-2 )
                    {
                        temp3.add(0.50 * widweight);
                    }
//                            System.out.println(docnum1);
                    totalweight = totalweight + widweight;
                }
//                        FontSize
                if(tn1params.containsKey("FontSize") && tn2params.containsKey("FontSize"))
                {
                    Double widweight = 15.0;
                    int fs1 = Integer.parseInt(tn1params.get("FontSize"));
                    int fs2 = Integer.parseInt(tn2params.get("FontSize"));

                    if(fs1 == fs2)
                    {
//                                System.out.println("fonts are " + fs1 + " "+ fs2);
                        temp3.add(1.00 * widweight);
                    }
                    totalweight = totalweight + widweight;
                }
                if(tn1params.containsKey("FontWeight") && tn2params.containsKey("FontWeight"))
                {
                    Double widweight = 10.0;
//                            System.out.println(tn1params.get("FontWeight")+ " " + tn2params.get("FontWeight"));
                    if(tn1params.get("FontWeight").equals(tn2params.get("FontWeight")))
                    {
                        temp3.add(1.00 * widweight);
                    }
                    totalweight = totalweight + widweight;
                }
                if(tn1params.containsKey("IsImg") && tn2params.containsKey("IsImg"))
                {

//                            System.out.println(tn1params.get("IsImg")+ " " + tn2params.get("IsImg"));
                    if(tn1params.get("IsImg").equals(tn2params.get("IsImg")))
                    {
                        if(tn1params.get("IsImg").equals("false"))
                        {
                            Double widweight = 50.0;
                            temp3.add(1.00 * widweight);
                            totalweight = totalweight + widweight;
//                                    System.out.println("-------------");
                        }
                        else
                        {
                            Double widweight = 20.0;
                            temp3.add(1.00 * widweight);
                            totalweight = totalweight + widweight;
                        }

                    }
                }
                if(tn1params.containsKey("ObjectRectWidth") && tn2params.containsKey("ObjectRectWidth"))
                {

                    Double fract1 =  Double.parseDouble(tn1params.get("ObjectRectWidth"));
                    Double fract2 =  Double.parseDouble(tn2params.get("ObjectRectWidth"));

                    if(fract1 == fract2)
                    {
                        Double widweight = 20.0;
//                                System.out.println("fonts are " + fs1 + " "+ fs2);
                        temp3.add(1.00 * widweight);
                        totalweight = totalweight + widweight;
                    }
                    else
                    {
                        Double widweight = 20.0;
                        if(Math.abs(fract1-fract2)<15)
                        {
                            temp3.add(0.90 * widweight);
                            totalweight = totalweight + widweight;
                        }
                    }

                }
                if(tn1params.containsKey("ObjectRectLeft") && tn2params.containsKey("ObjectRectLeft"))
                {
                    Double fract1 =  Double.parseDouble(tn1params.get("ObjectRectLeft"));
                    Double fract2 =  Double.parseDouble(tn2params.get("ObjectRectLeft"));

                    if(fract1 == fract2)
                    {
                        Double widweight = 20.0;
//                                System.out.println("fonts are " + fs1 + " "+ fs2);
                        temp3.add(1.00 * widweight);
                        totalweight = totalweight + widweight;
                    }
                    else
                    {
                        Double widweight = 20.0;
                        if(Math.abs(fract1-fract2)<15)
                        {
                            temp3.add(0.90 * widweight);
                        }
                        totalweight = totalweight + widweight;
                    }

                }
//                        if(tn1params.containsKey("IsImg") && tn2params.containsKey("IsImg"))
                if(k>0)
                {
                    String pid1 = "t";
                    String pid2 = "t";
//                            "ObjectRectHeight"
//                            "ObjectRectLeft"
//                            "ObjectRectTop"
//                            "ObjectRectWidth"
                    if(k<PQ1.P)
                    {
//                                parents are k-1 element
                        pid1 = PQ1.ProfileCollection.get(i).get(k-1);
                        pid2 = PQ2.ProfileCollection.get(j).get(k-1);
                    }
                    else if (k >= PQ1.P)
                    {

//                                parent is PQ.P -1 elemtnt
                        pid1 = PQ1.ProfileCollection.get(i).get(PQ1.P-1);
                        pid2 = PQ2.ProfileCollection.get(j).get(PQ1.P-1);
//                                System.out.println(id1 + " " + id2 + " "+ pid1+ " " + pid2);
                    }
                    else
                    {
                        System.out.println("in else");
                    }
                    if(id1.startsWith("1")  && id2.startsWith("1") && pid1.startsWith("1") && pid2.startsWith("1"))
                    {
                        Double widweight = 10.0;
                        TreeNode ptn1 = XReader1.Store.get(pid1);
                        TreeNode ptn2 = XReader2.Store.get(pid2);
                        Map<String ,String > ptn1params = ptn1.Params;
                        Map<String ,String > ptn2params = ptn2.Params;
//                                System.out.println(id1 + " " + id2 + " "+ pid1+ " " + pid2);
                        if(tn1params.containsKey("ObjectRectWidth") && ptn1params.containsKey("ObjectRectWidth")
                                && tn2params.containsKey("ObjectRectWidth") && ptn2params.containsKey("ObjectRectWidth"))
                        {
                            Double fract1 =  Double.parseDouble(tn1params.get("ObjectRectWidth"))
                                    /Double.parseDouble(ptn1params.get("ObjectRectWidth"));
                            Double fract2 =  Double.parseDouble(tn2params.get("ObjectRectWidth"))
                                    /Double.parseDouble(ptn2params.get("ObjectRectWidth"));
                            if(fract1 == fract2)
                            {
                                temp3.add(1*widweight);
                                totalweight = totalweight +  widweight;
                            }
                            else if(Math.abs(fract1-fract2) < 0.05)
                            {
                                temp3.add(0.925*widweight);
                                totalweight = totalweight +  widweight;
                            }
                            else if(Math.abs(fract1-fract2) < 0.25)
                            {
                                temp3.add(0.85*widweight);
                                totalweight = totalweight +  widweight;
                            }
                        }

                        if(tn1params.containsKey("ObjectRectLeft") && ptn1params.containsKey("ObjectRectLeft")
                                && tn2params.containsKey("ObjectRectLeft") && ptn2params.containsKey("ObjectRectLeft"))
                        {
                            Double fract1 =  Double.parseDouble(tn1params.get("ObjectRectLeft"))
                                    /Double.parseDouble(ptn1params.get("ObjectRectLeft"));
                            Double fract2 =  Double.parseDouble(tn2params.get("ObjectRectLeft"))
                                    /Double.parseDouble(ptn2params.get("ObjectRectLeft"));
                            if(fract1 == fract2)
                            {
                                temp3.add(1*widweight);
                                totalweight = totalweight +  widweight;
                            }
                            else if(Math.abs(fract1-fract2) < 0.05)
                            {
                                temp3.add(0.925*widweight);
                                totalweight = totalweight +  widweight;
                            }
                            else if(Math.abs(fract1-fract2) < 0.25)
                            {
                                temp3.add(0.85*widweight);
                                totalweight = totalweight +  widweight;
                            }
                        }
                    }

                }

//                        if (k>0)
            }
            else if(PQ1.ProfileCollection.get(i).get(k).startsWith("t") && PQ2.ProfileCollection.get(j).get(k).startsWith("t"))
            {
//                double mat=1;
//                temp25.add(mat);
//                        temp3.add(1.00);
//                        totalweight = totalweight +  1;
//                        temp3=1;
            }
            else
            {
                temp3.add(0.00);
                totalweight = totalweight +  1;
            }
            double numer =0;
            double denom = 0;
            double mat = 0;
            if (totalweight==0 || temp3.isEmpty())
            {
//                System.out.println("w is 0");
                mat =0;
            }
            else
            {
                List<Double> newtemp3 = new ArrayList<Double>();
                for(Double it : temp3)
                {
                    newtemp3.add(it/totalweight);
//                    numer+=it/totalweight;
                }
                for(Double it : newtemp3)
                {
                    numer+=(it);
                }
                for(Double it : newtemp3)
                {
                    denom = 1;
                }

//                System.out.println(numer);

                if(denom<0.000000000001)
                {
                    mat =0.0;
//                    System.out.println("---------");
//                    System.out.println(denom);
//                    System.out.println(numer);
//                    System.out.println(newtemp3.size());
//                    System.out.println("---------");
                }
                else
                {
                    mat = numer/denom;
                }
                temp25.add(mat);

            }



        }



        if(temp25.size() ==0 )
        {
            temp2=0;
        }
        else
        {
            for(Double it : temp25)
                temp2 += it;
            temp2 = temp2/temp25.size();
        }
//        System.out.println("---------");
//        System.out.println(temp25);
//        System.out.println(temp2);
//        System.out.println("__________");

        return temp2;
    }
    public  double Similarityfunction(PQgram PQ1,PQgram PQ2,ReadXMLFile XReader1,ReadXMLFile XReader2)
    {
//        System.out.println(PQ1.ProfileCollection);
//        System.out.println(PQ2.ProfileCollection);
        double sim = 0.2;
        double ans1=0;
        double ans2 =0;
        double temp = 0;
//        PQ1.ProfileCollection.size();

        for (int i = 0;i< PQ1.ProfileCollection.size();i++)
        {
            double temp1=0;
            for (int j = 0;j< PQ2.ProfileCollection.size();j++)
            {
                double temp2 = this.compare(PQ1,PQ2,XReader1,XReader2,i,j);
                temp1 = Math.max(temp1,temp2) ;
            }
//            By here we get a temp1. Temp1 is the closest similarity. Instead of 1 in original pq gram paper we have a fraction.
            ans1 = ans1 + temp1;
        }
//        System.out.println("pq1 size is ");
//        System.out.println(PQ1.ProfileCollection.size());







//        ---------------------------------------------------------------------------------------


        for (int i = 0;i< PQ2.ProfileCollection.size();i++)
        {
            double temp1=0;
            for (int j = 0;j< PQ1.ProfileCollection.size();j++)
            {
                double temp2 = this.compare(PQ2,PQ1,XReader2,XReader1,i,j);
                temp1 = Math.max(temp1,temp2) ;
//                System.out.println(temp1);
            }

//            By here we get a temp1. Temp1 is the closest similarity. Instead of 1 in original pq gram paper we have a fraction.
            ans2 = ans2 + temp1;
        }


//        ---------------------------------------------------------------------------------------

//        sim = (ans1+ans2)/((PQ1.ProfileCollection.size()+PQ2.ProfileCollection.size()));
//        System.out.println(ans1);
//        System.out.println(ans2);
        System.out.println("-------------------");
//        System.out.println(ans1);
//        System.out.println(PQ1.ProfileCollection.size());
//        System.out.println(ans2);
//        System.out.println(PQ2.ProfileCollection.size());
        sim = ((ans1)/PQ1.ProfileCollection.size()) + ((ans2)/PQ2.ProfileCollection.size());
        sim = sim/2 ;
        return sim;
    }
}
