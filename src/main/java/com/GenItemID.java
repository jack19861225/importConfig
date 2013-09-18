package com; /**
 * Created with IntelliJ IDEA.
 * User: yanhui
 * Date: 13-8-2
 * Time: 下午5:30
 * To change this template use File | Settings | File Templates.
 */

import java.io.*;
import java.util.*;

public class GenItemID {
    public static Map<String, String> map = new HashMap<String, String>();
    public static Set<Integer> lines = new HashSet<Integer>();

    public static void main(String[] a) {

        readFileByLines("/Users/yanhui/Downloads/sale-cn/201206.txt");
        readFileByLines("/Users/yanhui/Downloads/sale-cn/201207.txt");
        readFileByLines("/Users/yanhui/Downloads/sale-cn/201208.txt");
        readFileByLines("/Users/yanhui/Downloads/sale-cn/201209.txt");
        readFileByLines("/Users/yanhui/Downloads/sale-cn/201210.txt");
//        readFileByLines("/Users/yanhui/Downloads/sale-cn/201211.txt");
        readFileByLines("/Users/yanhui/Downloads/sale-cn/201212.txt");

        readFileByLines("/Users/yanhui/Downloads/sale-cn/201301.txt");
        readFileByLines("/Users/yanhui/Downloads/sale-cn/201302.txt");
        readFileByLines("/Users/yanhui/Downloads/sale-cn/201303.txt");
        readFileByLines("/Users/yanhui/Downloads/sale-cn/201304.txt");
        readFileByLines("/Users/yanhui/Downloads/sale-cn/201305.txt");
        int num1 = 0;
        genFpg();
        //fpg();
        Set<String> set = map.keySet();
        generateItemFile("/Users/yanhui/Downloads/sale-cn/item.txt", null, list);
        for (String s : set) {

            //System.out.println(s + "-" + map.get(s).split(",").length + "   " + list.get(num1++).size());

            //System.out.println(map.get(s));
            //System.out.println(list.get(num1 - 1));
        }
        generate("/Users/yanhui/Downloads/sale-cn/text.txt");
        System.out.println(map.size());
        for (Integer in : lines) {
            //System.out.println(in + "===");
        }

    }

    public static void fpg() {
        for (Set<String> s : list) {
            Object[] obj = null;
            obj = s.toArray();
            //System.out.println(obj);
            for (int i = 0; i < obj.length; i++) {
                for (int j = i + 1; j < obj.length; j++) {
                    String str = obj[i] + "," + obj[j];
                    if (fpg.get(str) != null) {
                        fpg.put(str, fpg.get(str) + 1);
                    } else {
                        fpg.put(str, 1);
                    }
                }
            }
            for (String str : s) {
                //System.out.print(s + ",");
            }
            //System.out.println();
        }
        Set<String> mSet = fpg.keySet();
        for (String s : mSet) {
            System.out.println(s + "    " + fpg.get(s));
        }
    }

    public static Map<String, Integer> fpg = new HashMap<String, Integer>();


    public static HashMap<String, TreeSet> mapTree = new HashMap<String, TreeSet>();
    public static List<Set> list = new ArrayList<Set>();

    public static void genFpg() {
        Set<String> set = map.keySet();
        String value = null;
        Integer number = 0;
        String[] line = null;
        TreeSet<String> treeSet = null;
        int num = 0;
        for (String s : set) {
            treeSet = new TreeSet<String>();
            line = map.get(s).split(",");
            for (String str : line) {
                treeSet.add(str);
            }
            if (num++ == 0) {

                //System.out.println(s + "-" + map.get(s));

            }
            mapTree.put(s, treeSet);
            list.add(treeSet);
        }
        Set<String> sets = mapTree.keySet();
        int num1 = 0;
        for (String s : sets) {
            //System.out.println(s + "-" + mapTree.get(s).size()+"  "+ list.get(num1++).size());
            //System.out.println(mapTree.get(s));
            //System.out.println(list.get(num1-1));
        }

        for (Set<String> s : list) {
            System.out.println(s);
            for (String str : s) {
                //System.out.print(s+",");
            }
            System.out.println();
        }
        System.out.println(list.size());
    }

    public static String generate(String fileName) {
        StringBuffer out = new StringBuffer();
        BufferedWriter bw = null;
        FileWriter fw = null;
        File file = null;
        PrintWriter pw = null;
        OutputStreamWriter osw = null;

        FileOutputStream fo = null;

        try {
//            fo=new FileOutputStream("/Users/yanhui/test.txt");
//            osw=new OutputStreamWriter(fo,true)
//            pw = new PrintWriter(new OutputStreamWriter(),true);

            file = new File(fileName);
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);

            Set<String> set = map.keySet();
            for (String s : set) {
                //System.out.println(s + "-" + map.get(s));


                bw.write(map.get(s));
                bw.newLine();

            }
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }

        }
        return out.toString();
    }

    public static Set<String> itemIds = new HashSet<String>();
    public static int itemNumber = 8;
    public static Set<String> userIds = new HashSet<String>();
    public static int userNumber = 5;

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;

        FileInputStream fi = null;
        InputStreamReader isr = null;

        BufferedReader br = null;

        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：" + fileName);
            fi = new FileInputStream(fileName);
            isr = new InputStreamReader(fi, code);
            reader = new BufferedReader(isr);
            String tempString = null;
            int line = 1;
            String str[] = null;
            String uid = "";
            String iid = "";
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                //System.out.println(tempString);
                if (line > 1) {
                    tempString = tempString.replaceAll("\"", "");
                    //System.out.println(tempString);
                    str = tempString.split("\t");
                    if (str.length != 10) {

                        continue;
                    }
                    uid = str[userNumber];
                    iid = str[itemNumber];
                    uid = uid.replaceAll("'", "");
                    uid = uid.replaceAll(" ", "");
                    iid = iid.replaceAll("'", "");
                    iid = iid.replaceAll(" ", "");

                    itemIds.add(iid);
                    userIds.add(uid);
                    if (map.get(uid) != null) {
                        //System.out.println(iid);
                        if (map.get(uid).indexOf(iid + ",") >= 0 || map.get(uid).indexOf("," + iid) >= 0 || map.get(uid).equals(iid)) {

                        } else {
                            map.put(uid, map.get(uid) + "," + iid);
                        }
                    } else {
                        map.put(uid, iid);
                    }
                }
                line++;
            }
            lines.add(line);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public static String code = "Unicode";

    public static boolean generateItemFile(String fileName, Set<String> set, List<Set> listTree) {
        boolean ret = false;
        BufferedWriter bw = null;
        FileWriter fw = null;
        File file = null;
        PrintWriter pw = null;
        OutputStreamWriter osw = null;

        FileOutputStream fo = null;

        try {

            file = new File(fileName);
            fw = new FileWriter(file);
            fo = new FileOutputStream(file);
            osw = new OutputStreamWriter(fo, code);
            bw = new BufferedWriter(osw);
            //fi=new FileInputStream(fileName);
            //isr=new InputStreamReader(fi,"UTF-8");
            //reader = new BufferedReader(isr);
            int number = 1;

            String str=null;
            if (listTree != null) {
                for (Set s : listTree) {
                    str=s.toString().substring(1,s.toString().length()-1);
                    str=str.replaceAll(", "," ");

                    bw.write(str);
                    bw.newLine();
                }
            }
            if (set != null) {


                for (String s : set) {
                    //System.out.println(s + "-" + map.get(s));
                    bw.write(number++ + ",");
                    bw.write(s);
                    bw.newLine();

                }
            }
            bw.flush();
            ret = true;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }

        }
        return ret;
    }

    public static boolean generateUserFile(String fileName) {
        boolean ret = false;
        BufferedWriter bw = null;
        FileWriter fw = null;
        File file = null;
        PrintWriter pw = null;
        OutputStreamWriter osw = null;

        FileOutputStream fo = null;

        try {

            file = new File(fileName);
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);

            int number = 1;
            for (String s : itemIds) {
                //System.out.println(s + "-" + map.get(s));
                bw.write(number++);
                bw.write(",");
                bw.write(s);
                bw.newLine();

            }
            bw.flush();
            ret = true;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }

        }
        return ret;
    }

}
