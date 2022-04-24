package learning;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import javax.xml.parsers.DocumentBuilder;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class JsoupTests {
    @Test
    void testJsoup() throws IOException {
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document doc = Jsoup.parse(html);

//        Document doc = Jsoup.connect("https://en.wikipedia.org/").get();
//        System.out.println(doc.title());
//        Elements newsHeadlines = doc.select("#mp-itn b a");
//        for (Element headline : newsHeadlines) {
//            System.out.println(headline.attr("title" + "%s\n\t%s" + headline.absUrl("href")));
//        }
    }

    @Test
    void test2() throws IOException {
        Document doc = Jsoup.connect("https://en.wikipedia.org/").get();

        Element content = doc.getElementById("content");
        Elements links = content.getElementsByTag("a");
        for (Element link : links) {
            String linkHref = link.attr("href");
            String linkText = link.text();
            System.out.println(linkText);
        }
    }

    @Test
    void test3() throws IOException {
        Document doc = Jsoup.connect("https://www.dhlottery.co.kr/store.do?method=topStore&pageGubun=L645").get();
        Elements links = doc.select(".group_content").first().select(".tbl_data tbody tr");
        for(Element link : links) {
            System.out.print(link.child(1));
            System.out.println();
        }
    }

    @Test
    void test4() throws IOException {
//        Document doc = Jsoup.connect("https://www.dhlottery.co.kr/store.do?method=topStore&pageGubun=L645").get();
//        Elements links = doc.select("#page_box a");
//        Element link = links.get(1);
//        System.out.println(link);

        int size = 0;
        for(int i = 1010; i > 0; i--) {
            Connection.Response con = Jsoup.connect("https://www.dhlottery.co.kr/store.do?method=topStore&pageGubun=L645")
                    .data("method", "topStore")
                    .data("nowPage", "1")
                    .data("gameNo", "5133")
                    .data("drwNo", String.valueOf(i))
                    .data("schKey", "all")
                    .method(Connection.Method.POST)
                    .execute();
            Document doc = con.parse();
            Elements links = doc.select(".group_content").first().select(".tbl_data tbody tr");
            System.out.println(links);
            for(Element link : links) {
                if(link.childNodeSize() > 3) {
                    String name = link.child(1).text();
                    size++;
                }
            }

            System.out.println("size : " + size);
        }
    }

    @Test
    void test5() throws IOException {
        int i = 1010;
//        Connection.Response con = Jsoup.connect("https://www.dhlottery.co.kr/gameResult.do?method=byWin&drwNo="+i+"&dwrNoList="+i)
//                .method(Connection.Method.POST)
//                .execute();
//        Document doc = con.parse();
//        String text = doc.select(".desc").text();
//        System.out.println(text.replace("(", "").replace(")", ""));
        String  text = "2022년 04월 09일 추첨";
        System.out.println(text);
        int year = Integer.parseInt(text.substring(0, 4));
        int month = Integer.parseInt(text.substring(6, 8));
        int day = Integer.parseInt(text.substring(10, 12));
        System.out.println(year);
        System.out.println(month);
        System.out.println(day);

        LocalDate localDate = LocalDate.of(year, month, day);
        System.out.println(localDate);
    }

    @Test
    void test6() throws IOException {
        Connection.Response con = Jsoup.connect("https://www.dhlottery.co.kr/store.do?method=topStore&pageGubun=L645")
                .data("method", "topStore")
                .data("nowPage", "1")
                .data("gameNo", "5133")
                .data("drwNo", "1010")
                .data("schKey", "all")
                .method(Connection.Method.POST)
                .execute();
        Document doc = con.parse();
        System.out.println(doc.select("#drwNo option[selected]").val());
    }
}
