import java.awt.EventQueue;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadGame extends Thread{

	public DownloadGame(final String fileURL, final String fileName)
	{
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
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
					int writingbytes = 0;
					while ((read = input.read(buffer)) > 0){
						if(fileName.contentEquals("Voxelion.jar")){
							writingbytes++;
							Main.getMain().getWindow().getProgressBar().setProgress(writingbytes*1f/connection.getContentLength()*1f);
						}
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
		});
	}
}