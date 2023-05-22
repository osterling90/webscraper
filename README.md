## Web Scraper
### Creating the program as a **Maven** project allowed for the import of the **jsoup** library. **Jsoup** is an open source library that provides an API for fetching URL's and working with HTML data.
This program also constructs a Java applet using the **javax.swing** and **java.awt**
libraries. The user inputs a query into the text field and clicks the "Search" button. The application searches Google for basic results
and parses search result headings and their associated links. These headings and links are added added to an ArrayList
that is then passed to an exportFile() method to be printed to a text file.

## Notes:
** This program was designed to be used on STATIC web pages ** </br>
** Search results are not exhaustive. Some queries return no results ** </br>
** This implementation was intended as a simple demonstration the utility of the jsoup library for HTML parsing **
