package vn.edu.greenwich.cw_1_sample.ui.resident;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.switchmaterial.SwitchMaterial;

import vn.edu.greenwich.cw_1_sample.database.ResimaDAO;
import vn.edu.greenwich.cw_1_sample.models.Resident;
import vn.edu.greenwich.cw_1_sample.ui.dialog.CalendarFragment;
import vn.edu.greenwich.cw_1_sample.R;

public class ResidentRegisterFragment extends Fragment
        implements ResidentRegisterConfirmFragment.FragmentListener, CalendarFragment.FragmentListener {
    public static final String ARG_PARAM_RESIDENT = "Detail_trip";

    protected EditText fmResidentRegisterName,fmResidentRegisterDestination,fmResidentRegisterDiscription
            , fmResidentRegisterStartDate,fmResidentRegisterEndDate,fmResidentRegisterOther ;
    protected LinearLayout fmResidentRegisterLinearLayout;
    protected SwitchMaterial fmResidentRegisterOwner;
    protected TextView fmResidentRegisterError;
    protected Button fmResidentRegisterButton;

    protected ResimaDAO _db;

    public ResidentRegisterFragment() {}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        _db = new ResimaDAO(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resident_register, container, false);

        fmResidentRegisterError = view.findViewById(R.id.fmResidentRegisterError);
        fmResidentRegisterName = view.findViewById(R.id.fmResidentRegisterName);
        fmResidentRegisterDestination = view.findViewById(R.id.fmResidentRegisterDestination);
        fmResidentRegisterDiscription = view.findViewById(R.id.fmResidentRegisterDiscription);
        fmResidentRegisterOther = view.findViewById(R.id.fmResidentRegisterOther);
        fmResidentRegisterEndDate = view.findViewById(R.id.fmResidentRegisterEndDate);
        fmResidentRegisterStartDate = view.findViewById(R.id.fmResidentRegisterStartDate);
        fmResidentRegisterOwner = view.findViewById(R.id.fmResidentRegisterOwner);
        fmResidentRegisterButton = view.findViewById(R.id.fmResidentRegisterButton);
        fmResidentRegisterLinearLayout = view.findViewById(R.id.fmResidentRegisterLinearLayout);

        // Show Calendar for choosing a date.
        fmResidentRegisterStartDate.setOnTouchListener((v, motionEvent) -> showCalendar(motionEvent));

        // Update current resident.
        if (getArguments() != null) {
            Resident resident = (Resident) getArguments().getSerializable(ARG_PARAM_RESIDENT);

            fmResidentRegisterName.setText(resident.getName_of_trip());
            fmResidentRegisterDestination.setText(resident.getDestination());
            fmResidentRegisterDiscription.setText(resident.getDescription());
            fmResidentRegisterOther.setText(resident.getOther());
            fmResidentRegisterEndDate.setText(resident.getEndDate());
            fmResidentRegisterStartDate.setText(resident.getStartDate());
            fmResidentRegisterOwner.setChecked(resident.getRequiresRisk() == 1 ? true : false);

            fmResidentRegisterButton.setText(R.string.label_update);
            fmResidentRegisterButton.setOnClickListener(v -> update(resident.getId()));

            return view;
        }

        // Create new resident.
        fmResidentRegisterButton.setOnClickListener(v -> register());

        return view;
    }

    protected void register() {
        if (isValidForm()) {
            Resident resident = getResidentFromInput(-1);

            new ResidentRegisterConfirmFragment(resident).show(getChildFragmentManager(), null);

            return;
        }
    }

    protected void update(long id) {
        if (isValidForm()) {
            Resident resident = getResidentFromInput(id);

            long status = _db.updateResident(resident);

            FragmentListener listener = (FragmentListener) getParentFragment();
            listener.sendFromResidentRegisterFragment(status);

            return;
        }
    }

    protected boolean showCalendar(MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            new CalendarFragment().show(getChildFragmentManager(), null);
        }

        return false;
    }

    protected Resident getResidentFromInput(long id) {
        String name = fmResidentRegisterName.getText().toString();
        String destination = fmResidentRegisterDestination.getText().toString();
        String description = fmResidentRegisterDiscription.getText().toString();
        String other = fmResidentRegisterOther.getText().toString();
        String endDate = fmResidentRegisterEndDate.getText().toString();
        String startDate = fmResidentRegisterStartDate.getText().toString();
        int owner = fmResidentRegisterOwner.isChecked() ? 1 : 0;

        return new Resident(id, name, startDate, owner,destination,description,other,endDate);
    }

    protected boolean isValidForm() {
        boolean isValid = true;

        String error = "";
        String name = fmResidentRegisterName.getText().toString();
        String other = fmResidentRegisterOther.getText().toString();
        String endDate = fmResidentRegisterEndDate.getText().toString();
        String destination = fmResidentRegisterDestination.getText().toString();
        String discription = fmResidentRegisterDiscription.getText().toString();
        String startDate = fmResidentRegisterStartDate.getText().toString();

        if (name == null || name.trim().isEmpty()) {
            error += "* " + getString(R.string.error_blank_name) + "\n";
            isValid = false;
        }

        if (destination == null || destination.trim().isEmpty()) {
            error += "* " + getString(R.string.error_blank_destination) + "\n";

            isValid = false;
        }
        if (startDate == null   || startDate.trim().isEmpty()) {
            error += "* " + getString(R.string.error_blank_start_date) + "\n";
            isValid = false;
        }
        if (endDate == null   || endDate.trim().isEmpty()) {
            error += "* " + "Total of Date can't blank." + "\n";
            isValid = false;
        }

        fmResidentRegisterError.setText(error);

        return isValid;
    }

    @Override
    public void sendFromResidentRegisterConfirmFragment(long status) {
        switch ((int) status) {
            case -1:
                Toast.makeText(getContext(), R.string.notification_create_fail, Toast.LENGTH_SHORT).show();
                return;

            default:
                Toast.makeText(getContext(), R.string.notification_create_success, Toast.LENGTH_SHORT).show();

                fmResidentRegisterName.setText("");
                fmResidentRegisterOther.setText("");
                fmResidentRegisterEndDate.setText("");
                fmResidentRegisterDestination.setText("");
                fmResidentRegisterDiscription.setText("");
                fmResidentRegisterStartDate.setText("");

                fmResidentRegisterName.requestFocus();
        }
    }

    @Override
    public void sendFromCalendarFragment(String date) {
        fmResidentRegisterStartDate.setText(date);
    }

    public interface FragmentListener {
        void sendFromResidentRegisterFragment(long status);
    }
}