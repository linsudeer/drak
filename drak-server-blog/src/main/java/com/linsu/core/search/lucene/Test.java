package com.linsu.core.search.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Test {

    private static List<Book> books = new ArrayList<Book>();

    private static final String INDEX_PATH = "D:\\javaweb\\demo\\lunceindex\\20181101";

    static {
        books.add(new Book(1, "Java编程思想", "这本书详细讲解了java语言基础，java高级开发"));
        books.add(new Book(2, "Hoodop指南", "大数据，java处理大数据，如果处理大数据，Hive/Hbase/HDFS"));
        books.add(new Book(3, "Spring开发", "web开发的必读之书，java,从底层Servlet到springmvc,hibernate,mybatis等"));
        books.add(new Book(4, "Vue开发指南", "前端单页面应用开发，mvvm响应式dom操作"));
        books.add(new Book(5, "JavaScript高级教程", "js基础，从零开始学习js"));
    }

    public static void createIndex() {
        //创建文档对象
        List<Document> documents = new ArrayList<Document>();
        Document document = null;
        for(Book book : books) {
            //创建Field对象
            document = new Document();
            Field id = new StoredField("id",book.getId());
            Field bookname = new TextField("bookname", book.getBookname(), Field.Store.YES);
            bookname.setBoost(2.0F);// 设置相关度
            Field desc = new TextField("description", book.getDescription(), Field.Store.YES);
            document.add(id);
            document.add(bookname);
            document.add(desc);
            documents.add(document);
        }


        // 创建索引
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
        try {
            Directory directory = FSDirectory.open(new File(INDEX_PATH));
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_47, analyzer);
            IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
            for(Document doc : documents){
                indexWriter.addDocument(doc);
            }
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除指定域索引
     */
    public static void deleteIndex(String fieldName, String text){
        try {
            StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
            Directory directory = FSDirectory.open(new File(INDEX_PATH));
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_47, analyzer);
            IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
            indexWriter.deleteDocuments(new Term("bookname", "java"));
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateIndex(){
        StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
        try {
            Directory directory = FSDirectory.open(new File(INDEX_PATH));
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_47, analyzer);
            IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

            Document document = new Document();

            Field field = new TextField("bookname","java", Field.Store.YES);
            document.add(field);
            indexWriter.updateDocument(new Term("bookname", "java"), document);
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void search(String queryCase) {
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
//        QueryParser queryParser = new QueryParser(Version.LUCENE_47, "description", analyzer);
        String[] fileds = {"bookname","description"};
        QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_47, fileds, analyzer);
        try {
            Query query = queryParser.parse(queryCase);
            try {
                Directory directory = FSDirectory.open(new File(INDEX_PATH));
                IndexReader indexReader = DirectoryReader.open(directory);
                IndexSearcher indexSearcher = new IndexSearcher(indexReader);
                TopDocs topDocs = indexSearcher.search(query,5);
                //查出来的文档
                System.out.println("共搜索的文章数: "+topDocs.totalHits);
                ScoreDoc[] scoreDocs = topDocs.scoreDocs;

                // 设置关键字高亮
                Formatter formatter = new SimpleHTMLFormatter("<em>", "</em>");
                Highlighter highlighter = new Highlighter(formatter, new QueryScorer(query));

                for(ScoreDoc score : scoreDocs){
                    int docId = score.doc;
                    Document doc = indexSearcher.doc(docId);

                    try {
                        String bookname = highlighter.getBestFragment(analyzer, "bookname", doc.get("bookname"));
                        bookname = bookname==null?doc.get("bookname"):bookname;
                        String description = highlighter.getBestFragment(analyzer, "description", doc.get("description"));
                        description = description==null?doc.get("description"):description;
                        System.out.println("文档ID: "+ docId);
                        System.out.println("图书ID: "+ doc.get("id"));
                        System.out.println("图书名字: "+ bookname);
                        System.out.println("图书描述: "+ description);
                    } catch (InvalidTokenOffsetsException e) {
                        e.printStackTrace();
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        //创建索引
        //createIndex();
        //查询
        search("description:高级");

    }

    public static class Book {
        private Integer id;
        private String bookname;
        private String description;

        Book(Integer id, String bookname, String description) {
            this.id = id;
            this.bookname = bookname;
            this.description = description;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getBookname() {
            return bookname;
        }

        public void setBookname(String bookname) {
            this.bookname = bookname;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
