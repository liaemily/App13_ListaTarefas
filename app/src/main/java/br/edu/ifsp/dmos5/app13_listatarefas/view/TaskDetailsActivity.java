package br.edu.ifsp.dmos5.app13_listatarefas.view;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.edu.ifsp.dmos5.app13_listatarefas.R;
import br.edu.ifsp.dmos5.app13_listatarefas.mvp.TaskDetailsMVP;
import br.edu.ifsp.dmos5.app13_listatarefas.presenter.TaskDetailsPresenter;

public class TaskDetailsActivity extends AppCompatActivity
        implements TaskDetailsMVP.View, View.OnClickListener {

    private TaskDetailsMVP.Presenter presenter;
    private EditText titleEditText;
    private EditText descriptionEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        presenter = new TaskDetailsPresenter(this);
        findViews();
        setListener();
        setToolbar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.verifyUpdate();
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if(v == saveButton){
            presenter.saveTask(
                    titleEditText.getText().toString(),
                    descriptionEditText.getText().toString());
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void updateUI(String title, String description) {
        titleEditText.setText(title);
        descriptionEditText.setText(description);
    }

    @Override
    public Bundle getBundle() {
        return getIntent().getExtras();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void close() {
        presenter.detach();
        finish();
    }

    private void findViews(){
        titleEditText = findViewById(R.id.edittext_title_details);
        descriptionEditText = findViewById(R.id.edittext_description_details);
        saveButton = findViewById(R.id.button_save_task);
    }

    private void setListener(){
        saveButton.setOnClickListener(this);
    }

    private void setToolbar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
