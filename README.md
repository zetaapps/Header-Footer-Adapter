[![JitPack](https://jitpack.io/v/zetaapps/header-footer-adapter.svg)](https://jitpack.io/#zetaapps/header-footer-adapter)
[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
![SDK](https://img.shields.io/badge/SDK-15%2B-green.svg)
![Release](https://img.shields.io/badge/release-0.2-green.svg)
[![Build Status](https://travis-ci.org/zetaapps/header-footer-adapter.svg?branch=master)](https://travis-ci.org/zetaapps/header-footer-adapter)
[![CircleCI](https://circleci.com/gh/zetaapps/header-footer-adapter.svg?style=svg)](https://circleci.com/gh/zetaapps/header-footer-adapter)


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

### Install

Add jitpack to your root `build.gradle`.

```
allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

Then add the dependency.

```
dependencies {
	        compile 'com.github.zetaapps:Header-Footer-Adapter:0.2'
	}
```
