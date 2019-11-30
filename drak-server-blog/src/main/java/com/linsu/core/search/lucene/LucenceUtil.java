package com.linsu.core.search.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * lucence 工具类
 */
public class LucenceUtil {

    /**
     * 目录
     */
    private static Directory directory;

    /**
     * 分词器
     */
    private static Analyzer analyzer;

    static {
        try {
            directory = FSDirectory.open(new File("D:\\javaweb\\demo\\lunceindex\\20181101"));
            analyzer = new IKAnalyzer(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Directory getDirectory() {
        return directory;
    }

    public static Analyzer getAnalyzer() {
        return analyzer;
    }

    public static Document getDocument(Object obj) {
        Document document = new Document();

        Class<?> clz = obj.getClass();
        Field[] fields = clz.getDeclaredFields();

        try {
            for(Field field : fields) {
                String fieldName = field.getName();
                String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method method = clz.getDeclaredMethod(methodName);
                String fieldValue = method.invoke(obj, new Object()).toString();
                document.add(new TextField(fieldName, fieldValue, org.apache.lucene.document.Field.Store.YES));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * 创建索引，会把 obj里面的所有属性创建为
     * @param obj
     */
    public static void createIndex(Object obj) {
        try {
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_47, analyzer);
            IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
            indexWriter.addDocument(getDocument(obj));
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
