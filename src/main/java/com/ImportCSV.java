package com;

import com.yao.dao.impl.ImportIndex;
import com.yao.entity.AllConfig;
import com.yao.io.LoadConfig;

import java.io.*;

/**
 * Generte  Data!
 */
public class ImportCSV extends Thread {


    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("Plase Input : <filePath> <fileCount> <fileLine>");
            System.exit(0);
        }
        String filePath = args[0];
        if (filePath.length() <= 0) {
            System.out.println("Plase Input : <filePath> <fileCount> <fileLine>");
            System.exit(0);
        }
        int fileCount = Integer.parseInt(args[1]);
        if (fileCount <= 0) {
            System.out.println("Plase Input : <filePath> <fileCount> <fileLine>");
            System.exit(0);
        }
        double fileLine = Double.parseDouble(args[2]);
        if (fileLine <= 0) {
            System.out.println("Plase Input : <filePath> <fileCount> <fileLine>");
            System.exit(0);
        }
        System.out.println(filePath + fileCount + fileLine);
        long startTime = System.currentTimeMillis();
        ImportCSV thread = null;
        for (int i = 1; i <= fileCount; i++) {
            thread = new ImportCSV();
            if (filePath.endsWith("/")) {
                thread.generteFileName = filePath + "file" + i + ".txt";
            } else {
                thread.generteFileName = filePath + File.separator + "file" + i + ".txt";
            }

            //thread.generteFileName = "/Users/yanhui/file"+i+"";
            thread.generteNumber = fileLine;
            thread.start();
        }


        System.out.println("   done    " + (System.currentTimeMillis() - startTime) / 1000 + "");
        //new GenerteData().generate(3,"/Users/yanhui/test.txt");
    }

    private double generteNumber;
    private String generteFileName;

    public void GenerteData(double number, String fileName) {
        this.generteNumber = number;
        this.generteFileName = fileName;
    }

    @Override
    public void run() {
        super.run();    //To change body of overridden methods use File | Settings | File Templates.
        //this.generate(generteNumber, generteFileName);
        readFile(generteFileName);
        System.out.println(generteFileName);

    }

    public static String code = "Unicode";


    public String readFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()){
            System.exit(0);
        }


        AllConfig config=null;
        System.out.println("sss");
        try {
            config= LoadConfig.load();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("读取配置文件错误");
        }

        ImportIndex importIndex = new ImportIndex();
        importIndex.initClusterClient(config);

        System.out.println("sss");

        BufferedReader reader = null;

        FileInputStream fi = null;
        InputStreamReader isr = null;

        BufferedReader br = null;

        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：" + fileName);
            fi = new FileInputStream(fileName);
            isr = new InputStreamReader(fi);
            reader = new BufferedReader(isr);
            String tempString = null;
            int line = 1;
            String str[] = null;
            String uid = "";
            String iid = "";
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {


                //tempString = tempString.replaceAll("\"", "");
                System.out.println(tempString);
                str = tempString.split(",");
                System.out.println(str.length);
                try {
                    importIndex.importIndex(config, str);
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

            }

            System.out.println("以行为单位读取文件内容，一次读一整行：" + fileName);
            reader.close();
            importIndex.close();
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
        return "";
    }




}
