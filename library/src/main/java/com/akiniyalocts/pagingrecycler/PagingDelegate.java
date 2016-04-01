package com.akiniyalocts.pagingrecycler;

import android.support.v7.widget.RecyclerView;

/**
 * Intermediates between your @see PagingAdapter and your Activity.
 *
 * Create a new PagingDelegate with @see PagingDelegate.Builder();
 */
public class PagingDelegate extends RecyclerView.OnScrollListener{

    // Callbacks for when to page
    private OnPageListener onPageListener;

    // RecyclerView adapter attached
    private PagingAdapter pagingAdapter;


    private RecyclerView recyclerView;

    // Should the paging view span the entire list?
    private boolean fullspanLoadingView;

    // Automatically show the paging view
    private boolean autoPage;

    // Automatically hide the paging view
    private boolean autoCancel;

    private PagingDelegate() {}

    public void attach(RecyclerView recyclerView, PagingAdapter pagingAdapter){
        recyclerView.addOnScrollListener(this);
        this.pagingAdapter = pagingAdapter;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if(!recyclerView.canScrollVertically(1)){
            onPageListener.onPage(pagingAdapter.getItemCount());
        }
    }

    public void setPaging(){
        pagingAdapter.setPaging();
    }

    public void donePaging(){
        pagingAdapter.donePaging();
    }


    private void setOnPageListener(OnPageListener onPageListener) {
        this.onPageListener = onPageListener;
    }

    private void setPagingAdapter(PagingAdapter pagingAdapter) {
        this.pagingAdapter = pagingAdapter;
    }

    private void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        recyclerView.addOnScrollListener(this);
    }

    private void setAutoPage(boolean autoPage) {
        this.autoPage = autoPage;
    }

    private void setAutoCancel(boolean autoCancel) {
        this.autoCancel = autoCancel;
    }

    private void setFullspanLoadingView(boolean fullspanLoadingView) {
        this.fullspanLoadingView = fullspanLoadingView;
    }

    public boolean isFullspanLoadingView() {
        return fullspanLoadingView;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public interface OnPageListener{
        void onPage(int offset);

        void onDonePaging();
    }

    public static class Builder{


        private OnPageListener onPageListener;
        private PagingAdapter pagingAdapter;
        private RecyclerView recyclerView;

        private boolean autoPage = false;
        private boolean autoCancel = false;
        private boolean fullspanLoadingView = false;

        public Builder(PagingAdapter pagingAdapter){
            this.setPagingAdapter(pagingAdapter);
        }

        public Builder listenWith(OnPageListener listener){
            this.setOnPageListener(listener);
            return this;
        }

        public Builder attachTo(RecyclerView recyclerView){
            this.setRecyclerView(recyclerView);
            return this;
        }

        public Builder spanLoadingView(boolean fullspanLoadingView){
            this.fullspanLoadingView = fullspanLoadingView;
            return this;
        }

        public Builder autoPage(boolean autoPage){
            this.setAutoPage(autoPage);
            return this;
        }

        public Builder autoStop(boolean autoStop){
            this.setAutoCancel(autoStop);
            return this;
        }

        public PagingDelegate build(){
            PagingDelegate  pagingDelegate= new PagingDelegate();

            pagingDelegate.setOnPageListener(this.onPageListener);
            pagingDelegate.setRecyclerView(this.recyclerView);
            pagingDelegate.setPagingAdapter(this.pagingAdapter);
            pagingDelegate.setAutoCancel(this.autoCancel);
            pagingDelegate.setAutoPage(this.autoPage);
            pagingDelegate.setFullspanLoadingView(this.fullspanLoadingView);

            return pagingDelegate;
        }


        private void setOnPageListener(OnPageListener onPageListener) {
            this.onPageListener = onPageListener;
        }

        private void setPagingAdapter(PagingAdapter pagingAdapter) {
            this.pagingAdapter = pagingAdapter;
        }

        private void setRecyclerView(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
        }

        private void setAutoPage(boolean autoPage) {
            this.autoPage = autoPage;
        }

        private void setAutoCancel(boolean autoCancel) {
            this.autoCancel = autoCancel;
        }

    }
}
