package com.owen;
/* Note: this program works best with STATIC web pages */

/* Importing all of the necessary jsoup and java libraries */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.net.URLEncoder;
import javax.swing.*;
import java.util.*;

import java.awt.event.*;
import java.io.PrintWriter;

public class App extends JFrame implements ActionListener{
    final JFrame frame = new JFrame("StaticScraper");
    final JPanel searchPanel = new JPanel();
    final JPanel displayPanel = new JPanel();
    final JTextField field = new JTextField(20);
    final JButton searchButton = new JButton("Search");

    /* WebScraper constructor initializes GUI class attributes. */
    public App(){
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        searchPanel.setBounds(50,50,300,300);
        displayPanel.setBounds(100, 100, 300, 200);
        field.addActionListener(this);
        searchButton.addActionListener(this);
        searchPanel.add(field);
        searchPanel.add(searchButton);
        frame.add(searchPanel);
        frame.add(displayPanel);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        String input = field.getText();
        try{
            if(e.getSource() != ""){
                webScraper(input);
            }
        }
        catch(Exception a){
            System.out.println("An error has occured.");
        }
    }
    public static void webScraper(String input)throws Exception{
        /* URL: https://www.google.com/search?q= */
        /* CSS Selector: h3 a *
        /* Identifies the device and browser to the server. The user agent string allows
        * our program to act as a browser. In this case, Chrome.
        */
        final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36";
        /* Declares and initializes the thing we're searching for */
        final String query = input;
        ArrayList<String> list = new ArrayList<String>();
        final Document page = Jsoup.connect("https://www.google.com/search?q=" + URLEncoder.encode(query, "UTF-8")).userAgent(USER_AGENT).get();
        for(Element searchResult: page.select("h3 a")){
            final String info = searchResult.text();
            final String link = searchResult.attr("href");
            list.add(info + ":" + link);
        }
        exportFile(list);
    }
    /* Exports results to a txt file */
    public static void exportFile(ArrayList<String> list){
        try{
            final PrintWriter out = new PrintWriter("results.txt");
            for(int i = 0; i < list.size(); i++){
                out.write(list.get(i) + "\n");
            }
            out.close();
        }
        catch(Exception b){
            System.out.println("An error has occured. Unable to write to file.");
        }
    }
    public static void main(String[] args)throws Exception{
        new App();
    }
}
