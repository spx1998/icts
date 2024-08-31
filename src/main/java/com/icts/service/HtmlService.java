package com.icts.service;

import lombok.extern.slf4j.Slf4j;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class HtmlService {
    public List<String> extractTitleFromHtml(String htmlStr) {
        List<String> subtitleList = new ArrayList<>();
        try {
            Parser parser = new Parser(htmlStr);
            NodeFilter filter = new TagNameFilter("h2");
            NodeList nodes = parser.parse(filter);

            for (int i = 0; i < nodes.size(); i++) {
                Node h2Node = nodes.elementAt(i);
                subtitleList.add(h2Node.toPlainTextString());
            }
            return subtitleList;

        } catch (
                ParserException e) {
            log.error("html parse error", e);
        }
        return subtitleList;
    }
}