package com.exampleee.agroapk.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exampleee.agroapk.R;
import com.exampleee.agroapk.models.Address;
import com.exampleee.agroapk.models.AddressSelect;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressHolder> {


    List<Address> list;

    AddressSelect addressSelect;


    public AddressAdapter(List<Address> list, AddressSelect addressSelect) {
        this.list = list;
        this.addressSelect = addressSelect;
    }

    @NonNull
    @Override
    public AddressHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AddressHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AddressHolder holder, int position) {

        Address address = list.get(position);

        holder.check.setChecked(address.isSelected());

        holder.name.setText(address.getName());

        holder.mobile.setText(address.getMobile());


        String adr = "<b>Address</b> : "+address.getAddress()+","+"<b>City</b> : "+address.getCity()+","+"<b>State</b> : "+address.getState()+","+"<b>Pine Code</b> : "+address.getPin_code();

        holder.itemView.setOnClickListener(v -> {

            addressSelect.select(address,position);
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void insertData(List<Address> items){

        int positionStart = getItemCount();
        int item_count = items.size();
        this.list.addAll(items);
        notifyItemRangeInserted(positionStart,item_count);

    }

    public void clearAll(){

        this.list.clear();
        notifyDataSetChanged();
    }

    public static class AddressHolder extends RecyclerView.ViewHolder {

        RadioButton check;

        TextView name,address,mobile;
        public AddressHolder(@NonNull View itemView) {
            super(itemView);


            check=itemView.findViewById(R.id.item_address_check);
            name=itemView.findViewById(R.id.item_address_name);
            address=itemView.findViewById(R.id.item_address_address);
            mobile=itemView.findViewById(R.id.item_address_mobile);
        }
    }
}
