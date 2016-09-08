## Header-Footer-Adapter
A Recycler View adapter with optional Header and Footer views

### Usage

Extend `BaseHeaderAndFooterAdapter` and implement the abstract methods.

The Header and Footer are both optional, 
```
protected abstract boolean shouldShowHeader();
protected abstract boolean shouldShowFooter();
```
You may return `true` or false from those methods to indicate whether or not the header or footer should appear.

Create each type of `View` with these methods:
```
protected abstract View onCreateHeaderView(final ViewGroup parent);
protected abstract View onCreateFooterView(final ViewGroup parent);
protected abstract View onCreateRegularView(final ViewGroup parent, final int viewType);
```
`Regular` views are the ones between your header and footer.  

Bind your views as they are scrolled on and off the screen:
```
protected abstract void onBindHeaderViewHolder(final RecyclerView.ViewHolder viewHolder);
protected abstract void onBindFooterViewHolder(final RecyclerView.ViewHolder viewHolder);
protected abstract void onBindRegularViewHolder(final RecyclerView.ViewHolder viewHolder, final int position);
```
