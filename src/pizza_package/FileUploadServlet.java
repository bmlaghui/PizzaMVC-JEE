
package pizza_package;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileUtils;

/**
 * Servlet implementation class FileUploadServlet2
 */
@WebServlet("/uploadImage")
@MultipartConfig(maxFileSize = 1024 * 1024 * 2)
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// https://www.codejava.net/java-ee/servlet/java-file-upload-example-with-servlet-30-api
		
		
		//get the file chosen by the user
				Part filePart = request.getPart("image");
				// Getting Application Path
		        String appPath = request.getServletContext().getRealPath("");
		 
		      //get the InputStream to store the file somewhere
			    InputStream fileInputStream = filePart.getInputStream();
		        
		        // File path where all files will be stored
		        String imagePath = appPath + "assets/images";//!!!!!!!!
		        System.out.println(imagePath);
		        // Creates the file directory if it does not exists
		        File fileDir = new File(imagePath);
		        if (!fileDir.exists()) {
		            fileDir.mkdirs();
		        }
		        System.out.println("****** get submit**********");
		        System.out.println(filePart.getSubmittedFileName());
		        System.out.println("********filename********");
		        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			    System.out.println(fileName);
			    System.out.println("****************");  
		        System.out.println( request.getSession().getServletContext().getRealPath("/"));
		        
		      //Get Image Name
		        String imageName = filePart.getSubmittedFileName();
		        System.out.println("*****image name********");  
		        
		        System.out.println(imageName);
		         String path = this.getServletContext().getRealPath("/WebContent/assets/images"); 
		         System.out.println(path);
		          //filePart.write(imagePath + File.separator + imageName);
		        //filePart.write("D:/imagesjava"+ File.separator + fileName);
		         
		      System.out.println("*****ecriture dans eclipse********");
		      filePart.write("C:\\Users\\Bmlaghui\\Documents\\GitHub\\Pizzademerde\\WebContent\\assets\\images"+ File.separator + fileName);
		       filePart.write(imagePath + File.separator + fileName); 
		       
		       System.out.println("*****copy fichier  ********");
		       //File source = new File( "D:/imagesjava"+ File.separator + fileName);
		       //File dest =new File("D:/imagesjava2"+ File.separator + fileName);
		      // Files.copy(source.toPath(), dest.toPath());
		       
		       // AWS S3  ces serait mieux sotckage amazone
		       
		       String sturl ="http://localhost:8080/Pizzademerde/assets/images/"+fileName;

		      response.getWriter().append(fileName);
		      //.append("<p><a href=\"" + sturl + "\">" + sturl + "</a></p>")
		      //.append( "<p>Upload another file <a href=\"http://localhost:8080/Pizzademerde/index.html\">here</a>.</p>")
		      //.append(imagePath + File.separator + fileName);
	}
	 
}
