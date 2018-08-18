package com.example.brice.spaghettify;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentTableInput extends Fragment {
    EditText edittext_x_as, edittext_y_as, edittext_nummer;
    Button submit;
    OnInputSetListener onInputSetListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_table_input,container, false);
        edittext_x_as = (EditText) view.findViewById(R.id.EditText_x_as);
        edittext_y_as = (EditText)view.findViewById(R.id.EditText_y_as);
        edittext_nummer = (EditText)view.findViewById(R.id.EditText_nummer);
        submit = (Button) view.findViewById(R.id.button_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x_as = Integer.parseInt(edittext_x_as.getText().toString());
                int y_as = Integer.parseInt(edittext_y_as.getText().toString());
                int nummer = Integer.parseInt(edittext_nummer.getText().toString());
                onInputSetListener.setVars(x_as, y_as, nummer);
            }
        });
        return view;
    }
    public interface OnInputSetListener
    {
        public void setVars(int x_as, int y_as, int nummer);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            onInputSetListener = (OnInputSetListener)activity;
        }catch (Exception e)
        {

        }

    }
}
