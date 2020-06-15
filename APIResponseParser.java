public class APIResponseParser {
    
     /**
	 * Parses the input text and returns a Book instance containing
	 * the parsed data. 
	 * @param response text to be parsed
	 * @return Book instance containing parsed data
	 */
     public static Book parse(String response) {
        Book book = new Book();
		
		
		// String startRule = "<title>";
		// Your code
       	String endRule = "<";
		String[] startRules = { "<title>", 
								"<name>", 
								"<original_publication_year type=\"integer\">", 
								"<average_rating>",
								"<ratings_count type=\"integer\">",
								"<image_url>"
		};
		
		
		
		String title = parse(response, startRules, endRule);
	    book.setTitle(title);
	    
	    String author = parse(response, startRules, endRule);
	    book.setAuthor(author);
	    
	    String publicationYear = parse(response, startRules, endRule);
	    book.setPublicationYear(Integer.valueOf(publicationYear));
	    
	    String averageRating = parse(response, startRules, endRule);
	    book.setAverageRating(Double.valueOf(averageRating));
	    
	    String ratingsCount = parse(response, startRules, endRule);
	    String stripRatingsCount = "";
	    if (ratingsCount.contains(",")) {
			stripRatingsCount = ratingsCount.replaceAll(",", "");
		} else {
			stripRatingsCount = ratingsCount;
		}
	    book.setRatingsCount(Integer.valueOf(stripRatingsCount));
	    
	    String imageUrl = parse(response, startRules, endRule);
	    book.setImageUrl(imageUrl);
	    
		return book;
     }
     
     // write overloaded parse method with the 3 parameters response, startRule, endRule
     private static String parse(String response, String[] startRule, String endRule) {
		int currentStartRuleIndex = 0;
    	
    	// response = <title>Walden</title>
    	int startRuleIndex = response.indexOf(startRule[currentStartRuleIndex]);
		int startRuleLength = startRule[currentStartRuleIndex++].length();
		
		int endRuleIndex = startRuleIndex + response.substring(startRuleIndex + 1).indexOf(endRule);
		
		String tagValue = response.substring(startRuleIndex + startRuleLength, endRuleIndex);
		
		return tagValue;
	}
     
     public static void main(String[] args) {
		String response = "<work>" + 
	                            "<id type=\"integer\">2361393</id>" +
	                            "<books_count type=\"integer\">813</books_count>" +
	                            "<ratings_count type=\"integer\">1,16,315</ratings_count>" + 
	                            "<text_reviews_count type=\"integer\">3439</text_reviews_count>" +
	                            "<original_publication_year type=\"integer\">1854</original_publication_year>" +
								"<original_publication_month type=\"integer\" nil=\"true\"/>" +
								"<original_publication_day type=\"integer\" nil=\"true\"/>" +
								"<average_rating>3.79</average_rating>" +
								"<best_book type=\"Book\">" +
									"<id type=\"integer\">16902</id>" +
									"<title>Walden</title>" + 
									"<author>" +
										"<id type=\"integer\">10264</id>" + 
										"<name>Henry David Thoreau</name>" + 
									"</author>" +
									"<image_url>" + 
										"http://images.gr-assets.com/books/1465675526m/16902.jpg" +
									"</image_url>" +
									"<small_image_url>" + 
										"http://images.gr-assets.com/books/1465675526s/16902.jpg" +
									"</small_image_url>" +
								"</best_book>" +
							"</work>";
		
		APIResponseParser.parse(response);
	}
}
