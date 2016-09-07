package zeta.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * A {@link android.support.v7.widget.RecyclerView.Adapter} that handles the optional addition
 * of a header and footer.  The header will be shown as the first item, and the footer as the last.
 * <br>
 * <br>
 * {@// TODO: 9/7/16 Figure out how subclasses should define their custom ViewHolder implementation. }
 * {@// TODO: 9/7/16 Are the header and footer view holders supposed to be different than a regular view holder? }
 * {@// TODO: 9/7/16 Make sure this works with DiffUtil, and that subclasses won't have difficulty animating }
 * {@// TODO: 9/7/16 Check the sticky header implementation for some ideas about how to do this with an }
 * {@// TODO:        item decoration.  The lib (https://github.com/timehop/sticky-headers-recyclerview). }
 */
public abstract class BaseHeaderAndFooterAdapter extends RecyclerView.Adapter<BaseHeaderAndFooterAdapter.ViewHolder> {

    public static final int HEADER = 1000000000;
    public static final int FOOTER = 1000000001;
    private static final int HEADER_COUNT = 1;
    private static final int FOOTER_COUNT = 1;
    private static final int NO_ITEM_COUNT = 0;
    private static final int HEADER_POSITION_INDEX = 0;

    /**
     * Return {@code true} if the header should be shown, otherwise return {@code false}
     */
    protected abstract boolean shouldShowHeader();

    /**
     * Return {@code true} if the footer should be shown, otherwise return {@code false}
     */
    protected abstract boolean shouldShowFooter();

    /**
     * Return the item type just as you would normally for {@link RecyclerView.Adapter#getItemViewType(int)}
     */
    protected abstract int getRegularItemViewType(final int position);

    /**
     * Return the number of inner items in the {@link RecyclerView}, not counting your header or footer.
     */
    protected abstract int getRegularItemCount();

    /**
     * Create a new {@link View} that will be the header.
     */
    protected abstract View onCreateHeaderView(final ViewGroup parent);

    /**
     * Create a new {@link View} that will be the footer.
     */
    protected abstract View onCreateFooterView(final ViewGroup parent);

    /**
     * Create a new regular item view, just as you would for {@link RecyclerView.Adapter#onCreateViewHolder(ViewGroup, int)}
     *
     * @param parent   : parent view
     * @param viewType : type of view within regular items
     */
    protected abstract View onCreateRegularView(final ViewGroup parent, final int viewType);

    /**
     * Base class contract to bind the header view
     *
     * @param viewHolder : recycler view holder
     * @param position   : position of the item in the recycler view
     */
    protected abstract void onBindHeaderViewHolder(final RecyclerView.ViewHolder viewHolder, final int position);

    /**
     * Base class contact to bind the footer view
     *
     * @param viewHolder : recycler view holder
     * @param position   : position of the item in the recycler view
     */
    protected abstract void onBindFooterViewHolder(final RecyclerView.ViewHolder viewHolder, final int position);

    /**
     * Base class contact to bind the regular item view
     *
     * @param viewHolder : recycler view holder
     * @param position   : position of the item in the recycler view
     */
    protected abstract void onBindRegularViewHolder(final RecyclerView.ViewHolder viewHolder, final int position);

    @Override
    public final int getItemCount() {
        //header + regular item + footer
        return getRegularItemHeaderCount() + getRegularItemCount() + getRegularItemFooterCount();
    }

    @Override
    public final int getItemViewType(int position) {
        //Check for header position index
        if ((position == getHeaderPositionIndex()) && shouldShowHeader()) {
            return HEADER;
        }

        //Check for footer position index
        if ((position == getFooterPositionIndex()) && shouldShowFooter()) {
            return FOOTER;
        }

        //Regular item
        return getRegularItemViewType(getRegularItemPosition(position));
    }

    @Override
    public final ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view;
        switch (viewType) {
            case HEADER:
                view = onCreateHeaderView(parent);
                break;
            case FOOTER:
                view = onCreateFooterView(parent);
                break;
            default:
                view = onCreateRegularView(parent, viewType);
                break;
        }
        return new ViewHolder(view);
    }

    @Override
    public final void onBindViewHolder(final ViewHolder holder, final int position) {
        int itemType = getItemViewType(position);
        switch (itemType) {
            case HEADER:
                onBindHeaderViewHolder(holder, position);
                break;
            case FOOTER:
                onBindFooterViewHolder(holder, position);
                break;
            default:
                onBindRegularViewHolder(holder, getRegularItemPosition(position));
                break;
        }
    }

    /**
     * Helper method to get the header count
     *
     * @return : header count, currently we are supporting only one header count
     */
    private int getRegularItemHeaderCount() {
        return shouldShowHeader() ? HEADER_COUNT : NO_ITEM_COUNT;
    }

    /**
     * Helper method to get the footer count
     *
     * @return : footer count, currently we are supporting only one footer count
     */
    private int getRegularItemFooterCount() {
        return shouldShowFooter() ? FOOTER_COUNT : NO_ITEM_COUNT;
    }

    /**
     * Helper method to get the header position index.
     *
     * @return : header position index, currently we are treating our header to be at `0`th position
     */
    private int getHeaderPositionIndex() {
        return HEADER_POSITION_INDEX;
    }

    /**
     * Helper method to get the footer position index.
     *
     * @return : header position index, currently we are last but one position to be footer
     */
    private int getFooterPositionIndex() {
        return (getItemCount() - 1);
    }

    /**
     * Helper method to get the position for the regular item
     * this considers header offsets
     *
     * @return : position for the regular item
     */
    private int getRegularItemPosition(int originalPosition) {
        return originalPosition - getRegularItemPositionOffset();
    }

    /**
     * Helper method to get the position offset because of header
     *
     * @return : position offset for the regular item
     */
    private int getRegularItemPositionOffset() {
        return shouldShowHeader() ? HEADER_COUNT : NO_ITEM_COUNT;
    }

    /**
     * Recycler View holder class
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            // All views that would normally be contained in this class can be found in the custom
            // view class used instead.
            super(v);
        }
    }

}


