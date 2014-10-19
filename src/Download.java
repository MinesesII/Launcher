import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Download {
	
	public Download(String fileURL, String fileName){
		InputStream input = null;
		FileOutputStream writeFile = null;

		try
		{
			URL url = new URL(fileURL);
			URLConnection connection = url.openConnection();
			input = connection.getInputStream();
			writeFile = new FileOutputStream(fileName);
			byte[] buffer = new byte[1024];
			int read;
			while ((read = input.read(buffer)) > 0){
				writeFile.write(buffer, 0, read);
				writeFile.flush();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				writeFile.close();
				input.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}