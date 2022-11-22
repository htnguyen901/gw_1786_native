package vn.edu.greenwich.cw_1_sample.ui.resident.list;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.greenwich.cw_1_sample.database.ResimaDAO;
import vn.edu.greenwich.cw_1_sample.models.Resident;
import vn.edu.greenwich.cw_1_sample.ui.resident.ResidentSearchFragment;
import vn.edu.greenwich.cw_1_sample.R;

public class ResidentListFragment extends Fragment implements ResidentSearchFragment.FragmentListener {
    protected ArrayList<Resident> residentList = new ArrayList<>();

    protected ResimaDAO _db;
    protected EditText fmResidentListFilter;
    protected ResidentAdapter residentAdapter;
    protected TextView fmResidentListEmptyNotice;
    protected RecyclerView fmResidentListRecylerView;
    protected ImageButton fmResidentListButtonSearch, fmResidentListButtonResetSearch;

    public ResidentListFragment() {}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        _db = new ResimaDAO(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resident_list, container, false);

        fmResidentListRecylerView = view.findViewById(R.id.fmResidentListRecylerView);
        fmResidentListEmptyNotice = view.findViewById(R.id.fmResidentListEmptyNotice);

        fmResidentListButtonResetSearch = view.findViewById(R.id.fmResidentListButtonResetSearch);
        fmResidentListButtonResetSearch.setOnClickListener(v -> resetSearch());

        fmResidentListButtonSearch = view.findViewById(R.id.fmResidentListButtonSearch);
        fmResidentListButtonSearch.setOnClickListener(v -> showSearchDialog());

        fmResidentListFilter = view.findViewById(R.id.fmResidentListFilter);
        fmResidentListFilter.addTextChangedListener(filter());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation());

        residentAdapter = new ResidentAdapter(residentList);

        fmResidentListRecylerView.addItemDecoration(dividerItemDecoration);
        fmResidentListRecylerView.setAdapter(residentAdapter);
        fmResidentListRecylerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        reloadList(null);
    }

    protected void reloadList(Resident resident) {
        residentList = _db.getResidentList(resident, null, false);
        residentAdapter.updateList(residentList);

        // Show no trip message
        fmResidentListEmptyNotice.setVisibility(residentList.isEmpty() ? View.VISIBLE : View.GONE);
    }

    protected TextWatcher filter() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                residentAdapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        };
    }

    protected void resetSearch() {
        fmResidentListFilter.setText("");
        reloadList(null);
    }

    protected void showSearchDialog() {
        new ResidentSearchFragment().show(getChildFragmentManager(), null);
    }

    @Override
    public void sendFromResidentSearchFragment(Resident resident) {
        if (!resident.isEmpty()) {
            reloadList(resident);
            return;
        }

        reloadList(null);
    }
}