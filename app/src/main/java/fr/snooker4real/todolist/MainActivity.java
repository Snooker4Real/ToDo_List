package fr.snooker4real.todolist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import fr.snooker4real.todolist.model.Todo;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_TODO = "todo";
    private TextView tvTodo;
    private String todoString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTodo = findViewById(R.id.tvTodo);
        if (savedInstanceState != null) {
            todoString = savedInstanceState.getString(KEY_TODO);
        }

        tvTodo.setText(todoString);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_TODO, todoString);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate teh menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_todos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_todos) {
            Intent intent = new Intent(getApplicationContext(), AddTodoActivity.class);
            startActivityForResult(intent, 2);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // check if the request code is same as what is passed here it is 2
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            Todo todo = (Todo) data.getSerializableExtra(AddTodoActivity.KEY_TODO);
            todoString += todo.getName() + " -- " + todo.getUrgency() + "\n";
            tvTodo.setText(todoString);
        }
    }
}