package icu.jogeen.fishbook.service;

import java.util.List;

/**
 * @Author jogeen
 * @Date 14:28 2020/6/24
 * @Description
 */
public interface BookScanner {
     String bookName();
     long getBookSize();
     long getTotalLines();
     String getContentForPage(int page);
     String getChapterForPage(int page);
     Integer getPosition(String Chapter);
     long size();
}
