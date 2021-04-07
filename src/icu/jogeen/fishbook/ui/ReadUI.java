package icu.jogeen.fishbook.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageDialogBuilder;
import com.intellij.openapi.wm.ToolWindow;
import icu.jogeen.fishbook.service.BookScanner;
import icu.jogeen.fishbook.service.BookScannerBuilder;
import icu.jogeen.fishbook.service.PersistentState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author jogeen
 * @Date 14:28 2020/6/24
 * @Description
 */
public class ReadUI {

    private JPanel contentPanel;
    private JButton btnPre;
    private JButton btnNext;
    private JTextField tfPageNum;
    private JButton btnJump;
    private JButton btnFirst;
    private JButton btnLast;
    private JTextPane txContetnt;
    private JLabel labTotalPages;
    private JLabel labBookName;
    private JScrollPane Scroll;
    private Long totalPage;
    private PersistentState persistentState = PersistentState.getInstance();

    private int PageNum;

    private void initBookScanner() {
        BookScanner scanner = BookScannerBuilder.getBookScaner();
        if (scanner == null) {
            scanner = BookScannerBuilder.getBookScaner();
            if (scanner == null) {
                MessageDialogBuilder.yesNo("操作结果", "请先配置图书路径").show();
                return;
            }
        }
        totalPage = scanner.size();
        labTotalPages.setText(""+totalPage);
        labBookName.setText(scanner.bookName());
    }

    public ReadUI(Project project, ToolWindow toolWindow) {

        btnFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turnPage(0);

            }
        });
        btnPre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                turnPage(persistentState.getPageNum() - 1);
            }
        });
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turnPage(persistentState.getPageNum() + 1);
            }
        });
        btnLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turnPage(totalPage.intValue()-1);
            }
        });
        btnJump.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                turnPage(tfPageNum.getText());
            }
        });
    }

    public void turnPage(int i) {

        if ((i>BookScannerBuilder.getBookScaner().getBookSize()-1) || i<0)return;

        initBookScanner();

        persistentState.setPageNum(i);

        String neron = BookScannerBuilder.getBookScaner().getChapterForPage(persistentState.getPageNum()) + "\n\n\n" + BookScannerBuilder.getBookScaner().getContentForPage(persistentState.getPageNum());

        txContetnt.setText(neron);

        tfPageNum.setText(BookScannerBuilder.getBookScaner().getChapterForPage(persistentState.getPageNum()));

        PageNum = i;

        labTotalPages.setText(totalPage + "--" + (PageNum+1));

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                Scroll.getVerticalScrollBar().setValue(0);
            }
        });
    }

    public void turnPage(String Chapter){

        initBookScanner();

        Integer position = BookScannerBuilder.getBookScaner().getPosition(Chapter);

        if (position!=null){

            turnPage(position.intValue());
        }
    }

    public JPanel getJcontent() {
        return contentPanel;
    }

}
