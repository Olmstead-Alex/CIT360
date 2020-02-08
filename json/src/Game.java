public class Game {

    //initializing variables
    public String name;
    public int year;
    public String publisher;
    public String rating;

    //hooray setters and getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

   //print out the gathered information
    public String toString() {
        return "Name: " + name + "  Year: " + year + "  Publisher: " + publisher + "  Rating: " + rating;
    }
}
