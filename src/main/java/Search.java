import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/Search")
public class Search extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Getting keyword from frontend
        String keyword = request.getParameter("keyword");

        // Setting up connection to database
        Connection connection=DatabaseConnection.getConnection();
        try {

            // getting results after running the ranking query
            ResultSet resultSet = connection.createStatement().executeQuery("select pageTitle , pagelink , (length(lower(pageText))-length(replace(lower(pageText),'" + keyword.toLowerCase() + "','')))/length('" + keyword.toLowerCase() + "') as countoccurance from pages order by countoccurance desc limit 30;");
            ArrayList<SearchResult> results = new ArrayList<SearchResult>();

            // Transferring values from resultset to result arraylist;
            while (resultSet.next()) {
                SearchResult searchResult = new SearchResult();
                searchResult.setTitle(resultSet.getString("pageTitle"));
                searchResult.setLink(resultSet.getString("pageLink"));
                results.add(searchResult);

            }

            // displaying result in console
            for(SearchResult result : results){
                System.out.println(result.getTitle()+"\n"+result.getLink());
            }

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
