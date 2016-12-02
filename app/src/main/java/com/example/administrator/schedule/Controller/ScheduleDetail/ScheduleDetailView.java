package com.example.administrator.schedule.Controller.ScheduleDetail;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.administrator.schedule.R;


public class ScheduleDetailView extends RelativeLayout {

    Button pickTimeButton ;
    Button pickTypeButton ;
    EditText titleEditText ;
    EditText contentEditText ;

    public ScheduleDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(){
        pickTimeButton = (Button) findViewById(R.id.pick_time);
        pickTypeButton = (Button) this.findViewById(R.id.pick_type);
        titleEditText = (EditText) this.findViewById(R.id.schedule_title);
        contentEditText = (EditText) this.findViewById(R.id.schedule_content);
    }

    public final static int IMPORTANT = 0;
    public final static int NORMAL = 1;
    public final static int EASY = 2;

    public void setPickTimeButtonListener(OnClickListener listener){ pickTimeButton.setOnClickListener(listener);}
    public void setPickTypeButtonListener(OnClickListener listener){pickTypeButton.setOnClickListener(listener);}
    public void setPickTimeButtonText(String str){ pickTimeButton.setText(str);}
    public void setTitleEditText(String str){ titleEditText.setText(str);}
    public void setContentEditText(String str){ contentEditText.setText(str);}

    public void setPickTypeButtonIcon(int typeButtonIcon){
        switch (typeButtonIcon){
            case IMPORTANT:
                pickTypeButton.setBackground(getResources().getDrawable(R.drawable.ic_star_black_24dp));
                break;
            case NORMAL:
                pickTypeButton.setBackground(getResources().getDrawable(R.drawable.ic_star_half_black_24dp));
                break;
            case EASY:
                pickTypeButton.setBackground(getResources().getDrawable(R.drawable.ic_star_border_black_24dp));
                break;
        }
    }

    public String getTitleEditTextText(){ return titleEditText.getText().toString();}
    public String getContentEditTextText(){ return contentEditText.getText().toString();}
}
