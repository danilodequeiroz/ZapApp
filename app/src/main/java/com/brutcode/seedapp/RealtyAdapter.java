package com.brutcode.seedapp;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brutcode.seedapp.model.Realty;
import com.brutcode.seedapp.model.RealtyAddress;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Danilo on 24/06/2016.
 */
public class RealtyAdapter extends RecyclerView.Adapter<RealtyAdapter.ContentViewHolder> {
    private static final String TAG = RealtyAdapter.class.getSimpleName();
    // Set numbers of List in RecyclerView.
    private List<Realty> mRealties;
    private Context mContext;
    private OnItemClickListener mListener;


    @Inject
    public RealtyAdapter(Context context, OnItemClickListener listenet) {
        mContext = context;
        mRealties = new ArrayList<>();
        mListener = listenet;
    }

    public void insertItens(List<Realty> realties) {
        mRealties.addAll(realties);
        notifyDataSetChanged();
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public Realty getItem(int position){
        if(mRealties == null  || mRealties.isEmpty())
            return null;
        return mRealties.get(position);
    }

    public void insertOneItem(Realty realty) {
        mRealties.add(realty);
        notifyDataSetChanged();
    }

    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false);
        return new ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContentViewHolder holder, int position) {
        Realty realty = mRealties.get(position);
        holder.tagView.setTag(position);
        Uri uri = Uri.parse(realty.getImgUrl());
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(uri).build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .setAutoPlayAnimations(true)
                .build();
        holder.photo.setController(controller);
        NumberFormat brlFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        String title = new StringBuilder()
                .append("<b>")
                .append(realty.getRealtyType())
                .append("</b><br>")
                .append(brlFormat.format(realty.getSellPrice())).toString();
        String parkingSpaces = mContext.getResources().getString(R.string.str_item_parking_spaces);
        String sqMeters = mContext.getResources().getString(R.string.str_item_square_metre);
        String dorms = mContext.getResources().getString(R.string.str_item_dorms);
        String suits = mContext.getResources().getString(R.string.str_item_suite);
        RealtyAddress realtyAddress = realty.getRealtyAddress();
        String address = Util.addressFormatted(realty.getRealtyAddress());
        String ufCity = new StringBuilder()
                .append(realtyAddress.getCidade() + " - " + realtyAddress.getEstado())
                .toString();
        String detail = new StringBuilder()
                .append(realty.getDorms() + dorms)
                .append((realty.getSuites() > 0) ? ", " + realty.getSuites() + suits : "")
                .append((realty.getParkingSpaces() > 0) ? ", " + realty.getParkingSpaces() + parkingSpaces : "")
                .append(", ")
                .append(realty.getTotalArea() + sqMeters)
                .toString();
        holder.title.setText(Util.fromHtml(title));
        holder.address.setText(address);
        holder.ufAndCity.setText(ufCity);
        holder.details.setText(detail);
    }

    public void sortItems(int which ){
        Comparator<Realty> comparator = null;
        switch (which){
            case 0:
                comparator = new Comparator<Realty>() {
                    @Override
                    public int compare(Realty realty, Realty t1) {
                        return new Integer(realty.getDorms()).compareTo(t1.getDorms());
                    }
                };
                break;
            case 1:
                comparator = new Comparator<Realty>() {
                    @Override
                    public int compare(Realty realty, Realty t1) {
                        return new Integer(realty.getSellPrice()).compareTo(t1.getSellPrice());
                    }
                };
                break;
            case 2:
                comparator = new Comparator<Realty>() {
                    @Override
                    public int compare(Realty realty, Realty t1) {
                        return (new Integer(realty.getSellPrice()).compareTo(t1.getSellPrice())) * -1;
                    }
                };
                break;
            case 3:
                comparator = new Comparator<Realty>() {
                    @Override
                    public int compare(Realty realty, Realty t1) {
                        return new Integer(realty.getTotalArea()).compareTo(t1.getTotalArea());
                    }
                };
                break;
            default:
                return;
        }
        Collections.sort(mRealties, comparator);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mRealties.size();
    }

    public class ContentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_main_item_top)
        public View tagView;

        @BindView(R.id.drawee_main_item_photo)
        public DraweeView photo;

        @BindView(R.id.text_item_title_price)
        public TextView title;

        @BindView(R.id.text_item_address)
        public TextView address;

        @BindView(R.id.text_item_uf_city)
        public TextView ufAndCity;

        @BindView(R.id.text_item_details)
        public TextView details;

        @OnClick(R.id.card_main_item_top)
        public void itemClick(View view) {
            Integer position = ((Integer)view.getTag());
            mListener.onItemClick(position);
        }

        public ContentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

