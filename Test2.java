import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Test2 {
    public static void main(String[] args) {

        Test2 pdfReader = new Test2();
        try {
            // 取得pdf的内容
//            pdfReader.readPdf("F:/NEU_FILE/UNFINISHED/practical_training/Nsfocus/2021-6-21week5/tagging/corpus0-32/pdf/rpt_APT37.pdf");
            pdfReader.readPdf("D:/python/pdf2txt/UEBA.pdf");
//            pdfReader.readPdf("F:/NEU_FILE/UNFINISHED/practical_training/Nsfocus/2021-6-21week5/tagging/corpus0-32/pdf/Skygofree_ Following in the footsteps of HackingTeam - Securelist.pdf");
//            pdfReader.readPdf("F:/NEU_FILE/UNFINISHED/practical_training/Nsfocus/2021-6-21week5/tagging/corpus0-32/pdf/unc1151-ghostwriter-update-report.pdf");
            System.out.println("finish");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void readPdf(String file) throws Exception {
        // 是否排序
        boolean sort = false;
        // pdf文件名
        String pdfFile = file;
        // 输入文本文件名称
        String textFile = null;
        // 编码方式
        String encoding = "UTF-8";
        // 开始提取页数
        int startPage = 1;
        // 结束提取页数
        int endPage = Integer.MAX_VALUE;
        // 文件输入流，生成文本文件
        Writer output = null;
        // 内存中存储的PDF Document
        PDDocument document = null;
        try {
            try {
                // 首先当作一个URL来装载文件，如果得到异常再从本地文件系统//去装载文件
                URL url = new URL(pdfFile);
                //注意参数已不是以前版本中的URL.而是File。
                document = PDDocument.load(new File(pdfFile));
                // 获取PDF的文件名
                String fileName = url.getFile();
                // 以原来PDF的名称来命名新产生的txt文件
                if (fileName.length() > 4) {
                    File outputFile = new File(fileName.substring(0, fileName.length() - 4)+ ".txt");
                    textFile =outputFile.getName();
                }
            } catch (MalformedURLException e) {
                // 如果作为URL装载得到异常则从文件系统装载
                //注意参数已不是以前版本中的URL.而是File。
                document = PDDocument.load(new File(pdfFile));
                if (pdfFile.length() > 4) {
                    textFile = pdfFile.substring(0, pdfFile.length() - 4)+ ".txt";
                }
            }
                // 文件输入流，写入文件倒textFile
            File outputfile = new File(textFile);
            if(!outputfile.exists()){
                try {
                    outputfile.createNewFile();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            output = new OutputStreamWriter(new FileOutputStream(textFile),encoding);
            // PDFTextStripper来提取文本
            PDFTextStripper stripper = null;
            stripper = new PDFTextStripper();
            // 设置是否排序
            stripper.setSortByPosition(sort);
            // 设置起始页
            stripper.setStartPage(startPage);
            // 设置结束页
            stripper.setEndPage(endPage);
            // 调用PDFTextStripper的writeText提取并输出文本
            stripper.writeText(document, output);
        } finally {
            if (output != null) {
                // 关闭输出流
                output.close();
            }
            if (document != null) {
                // 关闭PDF Document
                document.close();
            }
        }
    }
}
