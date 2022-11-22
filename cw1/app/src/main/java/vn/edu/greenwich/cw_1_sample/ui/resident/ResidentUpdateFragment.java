package vn.edu.greenwich.cw_1_sample.ui.resident;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import vn.edu.greenwich.cw_1_sample.models.Resident;
import vn.edu.greenwich.cw_1_sample.R;

public class ResidentUpdateFragment extends Fragment implements ResidentRegisterFragment.FragmentListener {
    public static final String ARG_PARAM_RESIDENT = "Detail_trip";

    public ResidentUpdateFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resident_update, container, false);

        if (getArguments() != null) {
            Resident resident = (Resident) getArguments().getSerializable(ARG_PARAM_RESIDENT);

            Bundle bundle = new Bundle();
            bundle.putSerializable(ResidentRegisterFragment.ARG_PARAM_RESIDENT, resident);

            // Send arguments (resident info) to ResidentRegisterFragment.
            getChildFragmentManager().getFragments().get(0).setArguments(bundle);
        }

        return view;
    }

    @Override
    public void sendFromResidentRegisterFragment(long status) {
        switch ((int) status) {
            case 0:
                Toast.makeText(getContext(), R.string.notification_update_fail, Toast.LENGTH_SHORT).show();
                return;

            default:
                Toast.makeText(getContext(), R.string.notification_update_success, Toast.LENGTH_SHORT).show();
                Navigation.findNavController(getView()).navigateUp();
        }
    }
}