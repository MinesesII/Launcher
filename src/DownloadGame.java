import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadGame extends Thread{

	private String fileurl, name;

	public DownloadGame(String fileURL, String fileName){
		fileurl = fileURL;
		name = fileName;
	}

	public void run() {
		InputStream input = null;
		FileOutputStream writeFile = null;

		try
		{
			URL url = new URL(fileurl);
			URLConnection connection = url.openConnection();
			input = connection.getInputStream();
			writeFile = new FileOutputStream(name);
			byte[] buffer = new byte[1024];
			int read;
			long downloaded = 0;
			while ((read = input.read(buffer)) > 0){
				downloaded+=read;
				Main.getMain().getWindow().getProgressBar().setProgress(downloaded*1f/connection.getContentLength()*1f);
				writeFile.write(buffer, 0, read);
				writeFile.flush();
			}
			try {
				new Loader().runGame(0);
				System.exit(0);
			} catch (Exception e) {
				e.printStackTrace();
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