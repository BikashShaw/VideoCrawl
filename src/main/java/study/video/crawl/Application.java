/**
 * Created by Bikash on 3/10/2016.
 */

package study.video.crawl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@SpringBootApplication
@EnableAutoConfiguration
public class Application implements CommandLineRunner{

    public static final String VIEWKEY = "watch?v";

    @Override
    public void run(String... args) throws Exception {
        System.out.println("There!!!");
        Document doc = Jsoup.connect("https://www.youtube.com/feed/trending").get();

        Elements questions = doc.select("a[href]");
        int counter = 0;
        for(Element link: questions){
            if(link.attr("href").contains(VIEWKEY) && !link.attr("title").isEmpty())
                System.out.println("Video# " + ++counter + " : " + link.attr("title") + " : " + link.attr("href").substring(link.attr("href").indexOf("=") + 1));
        }
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
