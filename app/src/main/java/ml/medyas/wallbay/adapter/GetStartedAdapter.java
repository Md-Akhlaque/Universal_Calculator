package ml.medyas.wallbay.adapter;

import android.animation.Animator;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ml.medyas.wallbay.R;
import ml.medyas.wallbay.databinding.GetStartedItemLayoutBinding;
import ml.medyas.wallbay.entities.GetStartedEntity;

public class GetStartedAdapter extends RecyclerView.Adapter<GetStartedAdapter.GetStartedItemViewholder> {
    private List<GetStartedEntity> items;

    public GetStartedAdapter(List<GetStartedEntity> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public GetStartedItemViewholder onCreateViewHolder(@NonNull ViewGroup parent,
                                                       int viewType) {
        GetStartedItemLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.get_started_item_layout, parent, false);
        return new GetStartedItemViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final GetStartedItemViewholder holder, int position) {
        final GetStartedEntity item = items.get(position);
        holder.itemLayoutBinding.setCategory(item);
        if (item.isSelected()) {
            holder.itemLayoutBinding.lottieCheckAnimation.setProgress(1.0f);
        }
        //holder.itemLayoutBinding.lottieCheckAnimation.setScaleX(1.5f);
        //holder.itemLayoutBinding.lottieCheckAnimation.setScaleY(1.5f);
        /*holder.itemLayoutBinding.lottieCheckAnimation.addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                new LottieValueCallback<ColorFilter>(new SimpleColorFilter(Color.GREEN)));*/
        holder.itemLayoutBinding.categoryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setSelected(!item.isSelected());
                holder.itemLayoutBinding.invalidateAll();
                if (item.isSelected()) {
                    holder.itemLayoutBinding.lottieCheckAnimation.playAnimation();
                    holder.itemLayoutBinding.lottieCheckAnimation.addAnimatorListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            holder.itemLayoutBinding.lottieCheckAnimation.setProgress(1.0f);
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public class GetStartedItemViewholder extends RecyclerView.ViewHolder {
        private GetStartedItemLayoutBinding itemLayoutBinding;

        public GetStartedItemViewholder(GetStartedItemLayoutBinding itemView) {
            super(itemView.getRoot());
            itemLayoutBinding = itemView;
        }
    }
}