package thanhdnh.ueh.edu.article_app;

import android.app.Activity;
import android.content.Context;
import android.widget.GridView;
import android.os.Handler;
import android.os.Looper;
import android.widget.ProgressBar;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArticleData {
  public static UserList data;
  private Context context;
  private GridView gridview;
  private ProgressBar progressBar;
  private final ExecutorService executor = Executors.newSingleThreadExecutor();

  public ArticleData(Context context, GridView gridview, ProgressBar progressBar) {
    this.context = context;
    this.gridview = gridview;
    this.progressBar = progressBar;
  }

  public static UserProfile getPhotoFromId(int id) {
    for (int i = 0; i < data.getUsers().size(); i++)
      if (data.getUsers().get(i).getId() == id)
        return data.getUsers().get(i);
    return null;
  }

  public void loadData(String url, Activity activity){
      executor.execute(()->{
          Downloader.downloadWithProgress(url, new Handler(Looper.getMainLooper()), context, context.getCacheDir(), progressBar, null, ()->{
            File file = new File(Downloader.cached_file_path);
            if(file.exists())
              activity.runOnUiThread(()->{
                Gson gson = new Gson();
                data = gson.fromJson(readText(file), (Type) UserList.class);
                ArticleAdapter adapter = new ArticleAdapter(data.getUsers(), context);
                gridview.setAdapter(adapter);
              });
          });
        });
  }

  public String readText(File file){
    BufferedReader reader = null;
    try {
      InputStream stream = new FileInputStream(file);
      reader = new BufferedReader(new InputStreamReader(stream));
      StringBuffer buffer = new StringBuffer();
      String line = "";
      while ((line = reader.readLine()) != null) {
        buffer.append(line + "\n");
      }
      return buffer.toString();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
    }
    return reader.toString();
  }
}
