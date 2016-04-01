package com.akiniyalocts.pagingrecycler;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by AKiniyalocts on 3/21/16.
 *
 * An extension of @see android.support.v7.widget.RecyclerView.Adapter that simplifies the common
 * "paging" pattern we see in clients. @see PagingDelegate
 */
public abstract class PagingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    // View tag for paging view
    public static int VIEW_TYPE_PAGING = -100;

    // Empty ViewHolder for paging view
    static class LoadingViewHolder extends RecyclerView.ViewHolder{
        public LoadingViewHolder(View itemView) {
            super(itemView);
        }
    }

    // Are we currently paging?
    protected boolean paging = false;

    /**
     * @see PagingDelegate
     */
    private PagingDelegate pagingDelegate;

    public PagingAdapter() {}

    /**
     * Provide the layout you wish to inflate when the adapter is loading
     * @return R.layout.your_loading_layout
     */
    public abstract int getPagingLayout();

    /**
     * Provide a count of items in your adapter. Use this instead of getItemCount();
     * @return itemCount
     */
    public abstract int getPagingItemCount();

    public void setPagingDelegate(PagingDelegate pagingDelegate) {
        this.pagingDelegate = pagingDelegate;
    }

    /**
     *  Notify the adapter that it is paging by inserting an new item at the index of
     *  @see android.support.v7.widget.RecyclerView.Adapter getItemCount() + 1
     */
    public void setPaging(){
        if(!paging) {
            paging = true;
            notifyItemInserted(getItemCount() + 1);
        }
    }

    /**
     * If the adapter is paging, return the original item size + 1 to account for the new paging view
     * in the RecyclerView. Otherwise, return item amount.
     * @return int currentItemCount
     */
    @Override
    public int getItemCount() {
        return paging ? getPagingItemCount() + 1: getPagingItemCount();
    }

    /**
     *  Notify the adapter that it is done paging by removing the previously added item
     *  at the index of @see android.support.v7.widget.RecyclerView.Adapter getItemCount() + 1
     */
    public void donePaging(){
        paging = false;

        notifyItemRemoved(getItemCount() + 1);
    }

    /**
     * Get the appropriate viewType for the recycler. If we are paging we return @see VIEW_TYPE_PAGING,
     * otherwise super it out.
     * @param position position in adapter
     * @return viewType
     */
    @Override
    public int getItemViewType(int position) {

        if(paging){
            return VIEW_TYPE_PAGING;
        }

        return super.getItemViewType(position);
    }

    /**
     * If there is a LoadingViewHolder in this position, inflate the view from getPagingLayout()
     * @param parent
     * @param viewType
     * @return LoadingViewHolder
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == VIEW_TYPE_PAGING){
            View itemView = LayoutInflater.from(parent.getContext()).inflate(getPagingLayout(), parent, false);

            return new PagingAdapter.LoadingViewHolder(itemView);
        }
        return null;
    }


    /**
     * If the PagingDelegate isFullSpanLoadingView(), then we should check to make sure its a StaggeredGrid, and then set
     * the span to full size.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == VIEW_TYPE_PAGING){

            if(pagingDelegate != null) {
                if(pagingDelegate.isFullspanLoadingView()) {
                    if (pagingDelegate.getRecyclerView().getLayoutManager() instanceof StaggeredGridLayoutManager) {
                        StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();

                        layoutParams.setFullSpan(true);
                    }
                }
            }
        }
    }
}
