package kmitl.lab05.thitiphat58070059.myapplication;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    String message;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        TextView tvFragment = (TextView) rootView.findViewById(R.id.tvFragment);
        tvFragment.setText(getArguments().getString("message"));

        if (!message.isEmpty()) {
            tvFragment.setText(message);
        }
        return rootView;
    }

    public static MainFragment newInstance(String a) {

        Bundle args = new Bundle();
        args.putString("message", a);
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        message = getArguments().getString("message");
    }
}
