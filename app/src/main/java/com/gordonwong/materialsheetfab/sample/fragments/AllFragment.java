package com.gordonwong.materialsheetfab.sample.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gordonwong.materialsheetfab.sample.R;
import com.gordonwong.materialsheetfab.sample.activity.DetailsActivity;
import com.gordonwong.materialsheetfab.sample.adapters.RecyclerViewAdapter;

/**
 * Created by Gordon Wong on 7/17/2015.
 *
 * All items fragment.
 */
public class AllFragment extends Fragment {
	private RecyclerView recyclerView;
	private RecyclerViewAdapter recyclerAdapter;

	public static AllFragment newInstance() {
		return new AllFragment();
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(getLayoutResId(), container, false);

		// Setup list
		recyclerView = (RecyclerView) view.findViewById(R.id.notes_list);
		recyclerView.setLayoutManager(new StaggeredGridLayoutManager(getNumColumns(),
				StaggeredGridLayoutManager.VERTICAL));
		recyclerView.setAdapter(recyclerAdapter=new RecyclerViewAdapter(getContext(),getNumItems()));
		recyclerAdapter.setItemClickListener(new RecyclerViewAdapter.MyItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
//				Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
				Intent i=new Intent(getActivity(), DetailsActivity.class);
				startActivity(i);
			}
		});
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
