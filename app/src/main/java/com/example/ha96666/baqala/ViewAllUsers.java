package com.example.ha96666.baqala;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class ViewAllUsers extends Activity {

    private TextView tvViewusers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_users);

        tvViewusers = findViewById(R.id.tvViewusers);

        UsersDatabase cdb = UsersDatabase.getDatabase(this.getApplicationContext());
        UsersDAO usersDAO = cdb.usersDAO();

        List<Users> usersList = usersDAO.getAllUsers();
        StringBuilder sbUsers = new StringBuilder();
        for (Users users : usersList) {
            sbUsers.append(users.getUserName()
                    + " " + users.getUserEmail()
                    + " " + users.getUserPhone());

        }
        tvViewusers.setText(sbUsers.toString());
    }
}
