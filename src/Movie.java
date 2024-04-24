
public class Movie implements Comparable<Movie> {

	private String movieTitle; // The title of the movie
	private int releaseYear; // The year the movie was released
	private String rating; // The rating of the movie
	private int movieReview; // The amount of reviews the movie has
	
	public Movie (String movieTitle, int releaseYear, String rating, int movieReview) {
		this.movieTitle = movieTitle;
		this.releaseYear = releaseYear;
		this.rating = rating;
		this.movieReview = movieReview;
	}
	
	public String getTitle() {
		return movieTitle;
	}
	
	public int getYear() {
		return releaseYear;
	}
	
	public String getRating() {
		return rating;
	}
	
	public int getReview() {
		return movieReview;
	}
	
	@Override
	public int compareTo(Movie other) {
		if(movieTitle.compareTo(other.movieTitle) != 0) {
			return movieTitle.compareTo(other.movieTitle);
		}
		return Integer.compare(releaseYear, other.releaseYear);
	}
	
	@Override
	public String toString() {
		return String.format("Title: %s, Year: %d, Rating: %s, Review %d;", movieTitle, releaseYear, rating, movieReview);
	}

}
