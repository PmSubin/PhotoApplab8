package thanhdnh.ueh.edu.article_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ViewArticleActivity extends AppCompatActivity {
  ImageView iv_detail;
  TextView tv_detail_title, tv_detail_description, tv_detail_email, tv_detail_hobby;
  Button btn_back;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_article);
    getSupportActionBar().hide();

    iv_detail = findViewById(R.id.iv_detail);
    tv_detail_title = findViewById(R.id.tv_detail_title);
    tv_detail_description = findViewById(R.id.tv_detail_description);
    tv_detail_email = findViewById(R.id.tv_detail_email);
    tv_detail_hobby = findViewById(R.id.tv_detail_hobby);
    btn_back = findViewById(R.id.btn_back);

    int id = (int) getIntent().getLongExtra("id", 0);

    Picasso.get().load(ArticleData.getPhotoFromId(id).getAvatar_url()).resize(400, 500).centerCrop().into(iv_detail);
    tv_detail_title.setText(ArticleData.getPhotoFromId(id).getUsername());
    tv_detail_description.setText(ArticleData.getPhotoFromId(id).getDesc());
    tv_detail_email.setText("Email: " + ArticleData.getPhotoFromId(id).getEmail());
    tv_detail_hobby.setText("Hobby: " + ArticleData.getPhotoFromId(id).getHobby());

    // Nút Back: đóng màn Detail, quay về màn Home
    btn_back.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
  }
}
