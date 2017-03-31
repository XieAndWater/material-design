package com.gordonwong.materialsheetfab.sample.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gordonwong.materialsheetfab.sample.R;
import com.gordonwong.materialsheetfab.sample.adapters.NotesAdapter;

/**
 * Created by Gordon Wong on 7/17/2015.
 *
 * All items fragment.
 */
public class AllFragment extends Fragment {
	private RecyclerView recyclerView;

	public static AllFragment newInstance() {
		return new AllFragment();
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(getLayoutResId(), container, false);

		// Setup list
		recyclerView = (RecyclerView) view.findViewById(R.id.notes_list);
		recyclerView.setLayoutManager(new StaggeredGridLayoutManager(getNumColumns(),
				StaggeredGridLayoutManager.VERTICAL));
		recyclerView.setAdapter(new NotesAdapter(getActivity(), getNumItems()));
		return view;
	}

	protected int getLayoutResId() {
		return R.layout.fragment_all;
	}

	protected int getNumColumns() {
		return 2;
	}

	protected int getNumItems() {
		return 20;
	}
}
