/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import com.ernieyu.feedparser.Feed;
import com.ernieyu.feedparser.FeedException;
import com.ernieyu.feedparser.FeedParser;
import com.ernieyu.feedparser.FeedParserFactory;
import com.ernieyu.feedparser.Item;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;




/**
 *
 * @author aminos
 */
public class RssReader {

    public List<Item> getStreamElements() throws MalformedURLException, IOException, FeedException {
         URL url = new URL("http://127.0.0.1:8000/blog/rss");
    HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
    if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
        InputStream feedStream = httpConnection.getInputStream();
         FeedParser parser = FeedParserFactory.newParser();
    Feed feed = parser.parse(feedStream);
    
    return feed.getItemList();
    }
    return null;
    }
    
}
