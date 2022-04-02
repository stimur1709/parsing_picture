import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static List<Picture> parsingPicture(String website) {
        List<Picture> list = new ArrayList<>();
        try {
            Document document = Jsoup.connect(website).get();
            Elements elements = document.select("img");
            for (Element element : elements) {
                String url = element.attr("src");
                if (!url.startsWith("https")) {
                    url = "https:" + url;
                }
                String name = url.substring(url.lastIndexOf("/") + 1);
                if (!name.endsWith("jpg")) {
                    name = (name + ".jpg").replace("?", "");
                }
                list.add(new Picture(name, url));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
