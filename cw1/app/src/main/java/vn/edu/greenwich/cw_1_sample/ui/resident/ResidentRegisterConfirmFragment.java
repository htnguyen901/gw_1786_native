package vn.edu.greenwich.cw_1_sample.ui.resident;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import vn.edu.greenwich.cw_1_sample.database.ResimaDAO;
import vn.edu.greenwich.cw_1_sample.models.Resident;
import vn.edu.greenwich.cw_1_sample.R;

public class ResidentRegisterConfirmFragment extends DialogFragment {
    protected ResimaDAO _db;
    protected Resident _resident;
    protected Button fmResidentRegisterConfirmButtonConfirm, fmResidentRegisterConfirmButtonCancel;
    protected TextView fmResidentRegisterConfirmName,fmResidentRegisterConfirmDestination
            ,fmResidentRegisterConfirmDiscription, fmResidentRegisterConfirmStartDate,
            fmResidentRegisterConfirmOwner, fmResidentRegisterConfirmOther,fmResidentRegisterConfirmEndDate;

    public ResidentRegisterConfirmFragment() {
        _resident = new Resident();
    }

    public ResidentRegisterConfirmFragment(Resident resident) {
        _resident = resident;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        _db = new ResimaDAO(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();

        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resident_register_confirm, container, false);

        String name = getString(R.string.error_no_info);
        String destination = getString(R.string.error_no_info);
        String discription = getString(R.string.error_no_info);
        String other = getString(R.string.error_no_info);
        String endDate = getString(R.string.error_no_info);
        String startDate = getString(R.string.error_no_info);
        String ownerType = getString(R.string.error_no_info);

        fmResidentRegisterConfirmName = view.findViewById(R.id.fmResidentRegisterConfirmName);
        fmResidentRegisterConfirmDestination = view.findViewById(R.id.fmResidentRegisterConfirmDestination);
        fmResidentRegisterConfirmDiscription = view.findViewById(R.id.fmResidentRegisterConfirmDiscription);
        fmResidentRegisterConfirmEndDate = view.findViewById(R.id.fmResidentRegisterConfirmEndDate);
        fmResidentRegisterConfirmOther = view.findViewById(R.id.fmResidentRegisterConfirmOther);
        fmResidentRegisterConfirmStartDate = view.findViewById(R.id.fmResidentRegisterConfirmStartDate);
        fmResidentRegisterConfirmOwner = view.findViewById(R.id.fmResidentRegisterConfirmOwner);
        fmResidentRegisterConfirmButtonCancel = view.findViewById(R.id.fmResidentRegisterConfirmButtonCancel);
        fmResidentRegisterConfirmButtonConfirm = view.findViewById(R.id.fmResidentRegisterConfirmButtonConfirm);

        if (_resident.getRequiresRisk() != -1) {
            ownerType = _resident.getRequiresRisk() == 1 ? getString(R.string.label_owner) : getString(R.string.label_tenant);
        }

        if (_resident.getName_of_trip() != null && !_resident.getName_of_trip().trim().isEmpty()) {
            name = _resident.getName_of_trip();
        }
        if (_resident.getDestination() != null && !_resident.getDestination().trim().isEmpty()) {
            destination = _resident.getDestination();
        }
        if (_resident.getDescription() != null && !_resident.getDescription().trim().isEmpty()) {
            discription = _resident.getDescription();
        }
        if (_resident.getOther() != null && !_resident.getOther().trim().isEmpty()) {
            other = _resident.getOther();
        }
        if (_resident.getEndDate() != null && !_resident.getEndDate().trim().isEmpty()) {
            endDate = _resident.getEndDate();
        }

        if (_resident.getStartDate() != null && !_resident.getStartDate().trim().isEmpty()) {
            startDate = _resident.getStartDate();
        }


        fmResidentRegisterConfirmName.setText(name);
        fmResidentRegisterConfirmDestination.setText(destination);
        fmResidentRegisterConfirmDiscription.setText(discription);
        fmResidentRegisterConfirmEndDate.setText(endDate);
        fmResidentRegisterConfirmOther.setText(other);
        fmResidentRegisterConfirmStartDate.setText(startDate);
        fmResidentRegisterConfirmOwner.setText(ownerType);

        fmResidentRegisterConfirmButtonCancel.setOnClickListener(v -> dismiss());
        fmResidentRegisterConfirmButtonConfirm.setOnClickListener(v -> confirm());

        return view;
    }

    protected void confirm() {
        long status = _db.insertResident(_resident);

        FragmentListener listener = (FragmentListener) getParentFragment();
        listener.sendFromResidentRegisterConfirmFragment(status);

        dismiss();
    }

    public interface FragmentListener {
        void sendFromResidentRegisterConfirmFragment(long status);
    }
}