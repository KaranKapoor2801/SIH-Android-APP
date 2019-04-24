package com.example.workerattendance.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.workerattendance.AddMaterialActivity;
import com.example.workerattendance.MaterialSahil;
import com.example.workerattendance.R;

import java.util.ArrayList;

public class MaterialAdapter extends ArrayAdapter<MaterialSahil> {
    Context mContext;
    ArrayList<MaterialSahil> mMainSiteData;


    public MaterialAdapter(@NonNull Context context, @NonNull ArrayList<MaterialSahil> objects) {
        super(context, R.layout.material_list_element, objects);
        mContext=context;
        mMainSiteData=objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=convertView;
        final CardViewHolder viewHolder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view= inflater.inflate(R.layout.material_list_element,parent, false);
            viewHolder = new MaterialAdapter.CardViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (CardViewHolder) view.getTag();
        }
        viewHolder.materialName.setText(mMainSiteData.get(position).getMaterialName());
        viewHolder.materialCost.setText(""+mMainSiteData.get(position).getPerUnitCost());
        viewHolder.materialIndex.setText(""+(position+1));
        viewHolder.materialQuantity.setText(""+mMainSiteData.get(position).getMaterialQuantity());
        setBackgroundColor(viewHolder, position);
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    AddMaterialActivity.materialList.get(position).setMaterialSelected(!AddMaterialActivity.materialList.get(position).isMaterialSelected());
                setBackgroundColor(viewHolder, position);
            }
        });
        viewHolder.editMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo change name or duration
            }
        });
        return view;
    }

    private void setBackgroundColor(CardViewHolder viewHolder, int position) {
        if(AddMaterialActivity.materialList.get(position).isMaterialSelected()) {
            viewHolder.linearLayout.setBackgroundColor(getContext().getResources().getColor(R.color.white_background));
        } else {
            viewHolder.linearLayout.setBackgroundColor(getContext().getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    class CardViewHolder{
        TextView materialName;
        TextView materialQuantity;
        TextView materialIndex;
        TextView materialCost;
        Button editMaterial;

        LinearLayout linearLayout;
        CardViewHolder(View view)
        {
            materialName=view.findViewById(R.id.materialName);
            materialQuantity=view.findViewById(R.id.materialQuantity);
            materialIndex=view.findViewById(R.id.materialIndex);
            materialCost= view.findViewById(R.id.materialCost);
            editMaterial = view.findViewById(R.id.editMaterial);
            linearLayout = view.findViewById(R.id.materialListElement);
        }
    }
}
