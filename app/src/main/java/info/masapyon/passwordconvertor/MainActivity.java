package info.masapyon.passwordconvertor;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private String toEncryptedHashValue(String algorithmName, String value) {
        MessageDigest md = null;
        StringBuilder sb = null;
        try {
            md = MessageDigest.getInstance(algorithmName);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(value.getBytes());
        sb = new StringBuilder();
        for (byte b : md.digest()) {
            String hex = String.format("%02x", b);
            sb.append(hex);
        }
        return sb.toString();
    }

    public void sendMessage(View view) {
        EditText input_pass_text = findViewById(R.id.input_pass);
        EditText pass_length_text = findViewById(R.id.pass_length);
        TextView output_pass_text = findViewById(R.id.output_pass);

        String input_pass = input_pass_text.getText().toString();
//        String pass_length = pass_length_text.getText().toString();

        String output_pass = toEncryptedHashValue("SHA-256", input_pass);

        output_pass_text.setText(output_pass);
    }
}
