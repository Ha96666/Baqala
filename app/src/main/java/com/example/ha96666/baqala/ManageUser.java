package com.example.ha96666.baqala;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ManageUser extends Activity {

    private EditText etName;
    private EditText etMail;
    private EditText etPhone_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeruser);

        etName = findViewById(R.id.etName);
        etMail = findViewById(R.id.etMail);
        etPhone_number = findViewById(R.id.etPhone_number);

    }

    public void doAdd(View view) {
        String userName = etName.getText().toString();
        String userEmail = etMail.getText().toString();
        String phone = etPhone_number.getText().toString();

        if (userName.isEmpty() || userEmail.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill all field", Toast.LENGTH_LONG).show();
            return;
        }


        UsersDatabase cdb = UsersDatabase.getDatabase(this.getApplicationContext());
        UsersDAO usersDAO = cdb.usersDAO();

        if (usersDAO.getUsers(userName) != null) {
            Toast.makeText(this,
                    "user" + userName + " exists",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        Users users = new Users(userName, userEmail, phone);
        usersDAO.insert(users);

        Toast.makeText(this, "User Added!", Toast.LENGTH_SHORT).show();


    }


    public void doFind(View view) {
        UsersDatabase cdb = UsersDatabase.getDatabase(this.getApplicationContext());
        UsersDAO usersDAO = cdb.usersDAO();

        String Name = etName.getText().toString();
        Users users = usersDAO.getUsers(Name);



        if (users == null) {
            Toast.makeText(this, Name + " NOT found!", Toast.LENGTH_LONG).show();
            return;
        }

        //fill the course name and credits
        etMail.setText(users.getUserEmail());
        etPhone_number.setText(users.getUserPhone());

    }

    public void doDelete(View view) {
        UsersDatabase cdb = UsersDatabase.getDatabase(this.getApplicationContext());
        UsersDAO usersDAO = cdb.usersDAO();

        String userName = etName.getText().toString();

        //delete by user name

        Users users = usersDAO.getUsers(userName);
        if (users == null) {
            Toast.makeText(this, userName + " NOT found!", Toast.LENGTH_LONG).show();
            return;
        }

        //check if code exists
        int deleteCount = usersDAO.deleteByUserName(userName);
        if (deleteCount == 1) {
            Toast.makeText(this, userName + " deleted", Toast.LENGTH_LONG).show();
            etName.setText("");
            etMail.setText("");
            etPhone_number.setText("");
        } else {
            Toast.makeText(this, userName + " could NOT be deleted", Toast.LENGTH_LONG).show();
        }

    }


    public void doUpdate(View view) {
        UsersDatabase bdb = UsersDatabase.getDatabase(this.getApplicationContext());
        UsersDAO usersDAO = bdb.usersDAO();

        String name = etName.getText().toString();
        String email = etMail.getText().toString();
        String phonenumber = etPhone_number.getText().toString();

        Users users = new Users(name, email, phonenumber);
        int updateCount = usersDAO.updateUser(users);

        if (updateCount == 1) {
            Toast.makeText(this, name + "data updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, name + "could NOT update", Toast.LENGTH_SHORT).show();
        }
    }

    public void ShowDatabase(View view) {
        Intent intent = new Intent(this, ViewAllUsers.class);
        startActivity(intent);
    }

    public void Goshop(View view) {
        Intent intent = new Intent(this, Shop.class);
        startActivity(intent);
    }


}


