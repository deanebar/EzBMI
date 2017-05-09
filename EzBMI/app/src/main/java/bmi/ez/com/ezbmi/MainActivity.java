package bmi.ez.com.ezbmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCalculate = (Button) findViewById(R.id.btn_calculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bmi = calculateBmi(getTextAsDouble(R.id.txt_height), getTextAsDouble(R.id.txt_weight));
                TextView viewBmi = (TextView) findViewById(R.id.view_result);
                viewBmi.setText(bmi);
            }
        });
    }

    private double getTextAsDouble(int viewId) {
        EditText txt = (EditText) findViewById(viewId);
        return Double.parseDouble(txt.getText().toString());
    }

    private String calculateBodyFat(double bmi) {
        if (bmi >= 30) {
            return "OBESE";
        } else if (bmi >= 25) {
            return "OVERWEIGHT";
        } else if (bmi >= 18.5) {
            return "IDEAL";
        }
        return "UNDERWEIGHT";
    }

    private String calculateBmi(double height, double weight) {
        double bmi = (weight / (height / 100 * height / 100));
        return String.format("Your BMI of %.1f is %s.", bmi, calculateBodyFat(bmi));
    }
}
