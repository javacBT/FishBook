package icu.jogeen.fishbook.service;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author jogeen
 * @Date 14:28 2020/6/24
 * @Description
 */
public class TxtBookScanner implements BookScanner {

    private List<String> chapter = new ArrayList(); //章节
    private List<String> content = new ArrayList(); //内容



    File file = null;

    public TxtBookScanner(String filePath) {

        file = new File(filePath);

        BookContent mbookContent = new BookContent(filePath);
        content.addAll(mbookContent.getContent());
        chapter.addAll(mbookContent.getChapter());
    }

    @Override
    public String bookName() {
        return file.getName();
    }

    @Override
    public long getBookSize() {
        return file.length();
    }

    @Override
    public long getTotalLines() {
        return chapter.size();

    }



    @Override
    public String getContentForPage(int pageNum) {

        return content.get(pageNum);
    }

    @Override
    public String getChapterForPage(int pageNum) {

        return chapter.get(pageNum);
    }

    @Override
    public Integer getPosition(String Chapter) {

        if (Chapter==null)return null;

        for (int i =0;i<chapter.size();i++){

            if (chapter.get(i).indexOf(Chapter)!=-1){

                return i;
            }
        }

        return null;
    }

    @Override
    public long size() {
        return chapter.size();
    }


}
